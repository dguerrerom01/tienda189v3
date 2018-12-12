package prueba.pool;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class AccesoBD implements AutoCloseable {

	private Connection conexion;
	
	public AccesoBD() throws NamingException, SQLException {
		//Obtenemos una conexion del pool de conexiones: ver META_INF/context.xml
		Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource datasource = (DataSource)envCtx.lookup("jdbc/PoolDB");
        conexion = datasource.getConnection();
        System.out.println("Conexion correcta");
	}

	/**
	 * Este metodo se encarga de poner el AutoCommit a false
	 * Con esto evita que al ejecutar una update, delete or Insert lo cargo directamente en disco.
	 * @throws SQLException 
	 */
	public void iniciarTransaccion() throws SQLException{
		conexion.setAutoCommit(false);
	}

	/**
	 * Hace un coMmit de la consulta SQL que estaba en memoria cargada, es decir
	 * lo mandamos a memoria fisica  (HDD).
	 * @throws SQLException 
	 */
	public void aceptarTransaccion() throws SQLException{
		conexion.commit();
		conexion.setAutoCommit(true);
	}

	/**
	 * Metodo que cancela toda la trasaccion de datos de las ordenes SQL y lo pone todo en estado original
	 * @throws SQLException 
	 */
	public void cancelarTransaccion() throws SQLException{
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

	/**
	 * Ejecuta una Orden SQL (Insert, delete or Update)
	 * @param sSql
	 * @return 1 En caso de operaci�n realizada, 0 en caso contrario
	 * @throws SQLException
	 */
	public int ejecutarUpdate(String sSql) throws SQLException {
		PreparedStatement consulta = conexion.prepareStatement(sSql);
		return consulta.executeUpdate();
	}
	/**
	 * Entra una sql por parametros, el nombre de una clase
	 * Convierte cada FILA del resultset en un OBJECT instanciado a la clase que le entra por prmtros.
	 * return un arrayList de Objects(asociados a la clase parametrizada)
	 * @throws Errores
	 */
	public ArrayList<Object> ejecutarConsulta(String sSql, Class<?> Clase) throws Errores {
		ArrayList<Object> objetos = new ArrayList<Object>();
		try(
				PreparedStatement consulta = conexion.prepareStatement(sSql);
				ResultSet rs = consulta.executeQuery(sSql);)
		{
			while(rs.next()){
				//Instanciamos un objeto de la clase
				Object objeto = Clase.newInstance();
				//Obtenemos sus metodos
				HashMap<String, Method> mapaMetodos = obtenerSetters(Clase);
				
				for (Entry<String, Method> setter : mapaMetodos.entrySet()) {

					Class<?> paramClass = setter.getValue().getParameterTypes()[0];
					if(!paramClass.isAssignableFrom(List.class)) {
						if (paramClass.getName().equals("boolean"))
							setter.getValue().invoke(objeto, rs.getBoolean(setter.getKey()));
						else
							setter.getValue().invoke(objeto, rs.getObject(setter.getKey()));
					}
				}
				objetos.add(objeto);
			}
		} catch (InstantiationException | IllegalAccessException
				|  SecurityException | SQLException 
				| IllegalArgumentException | InvocationTargetException e) {
			throw new Errores(e);
		}
		return objetos;
	}

	private HashMap<String, Method> obtenerSetters(Class<?> clazz) {
		HashMap<String, Method>  setters = new HashMap<String, Method>();		
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().startsWith("set")){
				method.setAccessible(true);
				setters.put(method.getName().substring(3, method.getName().length()).toLowerCase(), method);
			}
		}
		return setters;
	}

	@Override
	public void close() throws Exception {
		if(!conexion.getAutoCommit())//Si se habia iniciado una transaccion hacemos roolback
			conexion.rollback();
		//Realmente no cierra la conexion, solo la devuelve al pool de conexiones
		conexion.close();
	}
}
