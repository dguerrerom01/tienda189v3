package reflexion;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class RsTransferArraylist {

    public ArrayList<?> getListGenericObject(CallableStatement procedure, String clase) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, ParseException {

        ResultSet rs = procedure.executeQuery();

        ArrayList<Object> filas = new ArrayList<>();

        while (rs.next()) {

            HashMap<String, Object> datosFila = new HashMap<>();

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

               datosFila.put(rs.getMetaData().getColumnName(i).toString(),rs.getString(i)); // rs.getString(i)); // rs.getObject(i));
            }
        }
        return filas;
    }

    public Object getGenericObject(CallableStatement procedure, String clase) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, ParseException {
        return getListGenericObject(procedure,clase).get(0);
    }
}
