package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion
{
    private static String server = "localhost";//127.0.0.1
    private static String user = "root";
    private static String pwd = "B166ER12-1";
    private static String db = "restaurante";

    public static Connection con;
    public static void crearConexion()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+server+":3306/"+db,user,pwd);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
