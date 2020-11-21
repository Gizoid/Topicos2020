package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlatillosDAO
{
    private int id_platillo;
    private String nombre_platillo;
    private float precio_platillo;
    private int id_tipo;

    public int getId_platillo() { return id_platillo; }
    public void setId_platillo(int id_platillo) { this.id_platillo = id_platillo; }
    public String getNombre_platillo() { return nombre_platillo; }
    public void setNombre_platillo(String nombre_platillo) { this.nombre_platillo = nombre_platillo; }
    public float getPrecio_platillo() { return precio_platillo; }
    public void setPrecio_platillo(float precio_platillo) { this.precio_platillo = precio_platillo; }
    public int getId_tipo() { return id_tipo; }
    public void setId_tipo(int id_tipo) { this.id_tipo = id_tipo; }

    public void insPlatillo()
    {
        try
        {
            String query = "insert into tbl_platillos(nombre_platillo,precio_platillo,id_tipo) "+
                           "values('"+nombre_platillo+"',"+precio_platillo+","+id_tipo+")";
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e) { e.printStackTrace(); }
    }
    public void updPlatillo()
    {
        try
        {
            String query = "update tbl_platillos set nombre_platillo='"+nombre_platillo+"'," +
                           "precio_platillo="+precio_platillo+", id_tipo="+id_tipo+" where id_platillo="+id_platillo;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e) { e.printStackTrace(); }
    }
    public void delPlatilo()
    {
        try
        {
            String query = "delete from tbl_platillos where id_platillo = "+id_platillo;
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e) { e.printStackTrace(); }
    }
    public ObservableList<PlatillosDAO> getAllPlatillo()
    {
        ObservableList<PlatillosDAO> listaP = FXCollections.observableArrayList();
        try
        {
            PlatillosDAO objP;
            String query = "select * from tbl_platillos order by nombre_platillo";//asc = a->z | desc = z->a
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next())
            {
                objP = new PlatillosDAO();
                objP.setId_platillo(res.getInt("id_platillo"));
                objP.setNombre_platillo(res.getString("nombre_platillo"));
                objP.setPrecio_platillo(res.getFloat("precio_platillo"));
                objP.setId_tipo(res.getInt("id_tipo"));
                listaP.add(objP);
            }
        }
        catch(Exception e) { e.printStackTrace(); }
        return listaP;
    }
    public void getPlatillo()
    {

    }

}
