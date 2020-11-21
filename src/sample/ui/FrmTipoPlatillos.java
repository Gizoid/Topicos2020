package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.TipoPlatilloDAO;

public class FrmTipoPlatillos extends Stage
{
    private TextField txtTipo;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private TipoPlatilloDAO objTipoPlatillo;
    private TableView<TipoPlatilloDAO> tbvTipoPlatillo;
    private boolean nuevo=false;

    public FrmTipoPlatillos(TableView<TipoPlatilloDAO> tbvTipoPlatillo, TipoPlatilloDAO objTipoPlatillo)
    {
        if(objTipoPlatillo != null)
        {
            this.objTipoPlatillo = objTipoPlatillo;
        }
        else
        {
            this.objTipoPlatillo = new TipoPlatilloDAO();
            nuevo=true;
        }
        CrearUI();
        this.setTitle("Gestión de tipos de platillo");
        this.setScene(escena);
        this.show();
        this.tbvTipoPlatillo = tbvTipoPlatillo;
    }

    private void CrearUI()
    {
        txtTipo = new TextField();
        txtTipo.setText(objTipoPlatillo.getDsc_tipo());
        btnGuardar = new Button("Guardar Tipo");
        btnGuardar.setOnAction(event -> Guardar());
        vBox = new VBox();
        vBox.getChildren().addAll(txtTipo, btnGuardar);
        escena = new Scene(vBox,250,250);
    }

    private void Guardar()
    {
        objTipoPlatillo.setDsc_tipo(txtTipo.getText());
        if(nuevo)
        {//NUEVO PLATILLO
            objTipoPlatillo.insTipo();
        }
        else
        {//EDITAR PLATILLO
            objTipoPlatillo.updTipo();
        }
        tbvTipoPlatillo.setItems(objTipoPlatillo.getAllTipo());
        tbvTipoPlatillo.refresh();
        this.close();
    }
}