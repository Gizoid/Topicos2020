package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;

public class FrmPlatillos extends Stage
{
    private TextField txtPlatillo, txtPrecio;
    private ComboBox<TipoPlatilloDAO> cbxTipo;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private PlatillosDAO objPlatillo;
    private TableView<PlatillosDAO> tbvPlatillos;
    private boolean nuevo=false;

    public FrmPlatillos(TableView<PlatillosDAO> tbvPlatillos, PlatillosDAO objPlatillo)
    {
        if(objPlatillo != null)
        {
            this.objPlatillo = objPlatillo;
        }
        else
        {
            this.objPlatillo = new PlatillosDAO();
            nuevo=true;
        }
        CrearUI();
        this.setTitle("Gestión de platillos");
        this.setScene(escena);
        this.show();
        this.tbvPlatillos = tbvPlatillos;
    }

    private void CrearUI()
    {
        txtPlatillo = new TextField();
        txtPlatillo.setText(objPlatillo.getNombre_platillo());
        txtPrecio = new TextField();
        txtPrecio.setText(objPlatillo.getPrecio_platillo()+"");
        cbxTipo = new ComboBox<>();
        cbxTipo.setItems(new TipoPlatilloDAO().getAllTipo());
        btnGuardar = new Button("Guardar Platillo");
        btnGuardar.setOnAction(event -> Guardar());
        vBox = new VBox();
        vBox.getChildren().addAll(txtPlatillo, txtPrecio, cbxTipo, btnGuardar);
        escena = new Scene(vBox,250,250);
    }

    private void Guardar()
    {
        objPlatillo.setNombre_platillo(txtPlatillo.getText());
        objPlatillo.setPrecio_platillo(Float.parseFloat(txtPrecio.getText()));
        objPlatillo.setId_tipo(cbxTipo.getValue().getId_tipo());
        if(nuevo)
        {//NUEVO PLATILLO
            objPlatillo.insPlatillo();
        }
        else
        {//EDITAR PLATILLO
            objPlatillo.updPlatillo();
        }
        tbvPlatillos.setItems(objPlatillo.getAllPlatillo());
        tbvPlatillos.refresh();
        this.close();
    }
}
