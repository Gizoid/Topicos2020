package sample.models;

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

    public void insPlatillo(){}
    public void updPlatillo(){}
    public void delPlatilo(){}
    public void getAllPlatillo(){}
    public void getPlatillo(){}

}
