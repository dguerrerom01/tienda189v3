package prueba.pool.pool2;
/*
import prueba.pool3.DBCPDataSource;

/**
 * Created by user on 12/12/2018.
 */
/*
public class DataSourceService{

    private static DBCPDataSource.BasicDataSource basicDataSource=null;
    private String user = "root";
    private String password = "";

    public DataSourceService(){
        if (null==basicDataSource){
            basicDataSource = new DBCPDataSource.BasicDataSource();
            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(password);
            basicDataSource.setUrl("jdbc:mysql<://localhost/tienda_harnina20189vistas?useInformationSchema=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UT");
            basicDataSource.setMaxTotal(10);
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(100);
            System.out.println(basicDataSource.getUsername());
        }
    }

    public DBCPDataSource.BasicDataSource getDataSource(){
        return basicDataSource;
    }

    } */