package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.components.ButtonCustome;
import sample.components.ButtonCustomeTipo;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;

public class TipoPlatilloCRUD extends Stage
{
    private VBox vBox;
    private TableView<TipoPlatilloDAO> tbvTipoPlatillo;
    private Button btnNuevo;
    private Scene escena;
    private TipoPlatilloDAO objTPDAO;

    public TipoPlatilloCRUD()
    {
        objTPDAO = new TipoPlatilloDAO();
        CrearUI();
        this.setTitle("Administración de tipos de platillo");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        tbvTipoPlatillo = new TableView<>();
        CrearTabla();
        btnNuevo = new Button("Nuevo Tipo de Platillo");
        btnNuevo.setOnAction(event -> { new FrmTipoPlatillos(tbvTipoPlatillo, null); });
        vBox = new VBox();
        vBox.getChildren().addAll(tbvTipoPlatillo, btnNuevo);
        escena = new Scene(vBox);
    }

    private void CrearTabla()
    {
        TableColumn<TipoPlatilloDAO, Integer> tbcIdTipo = new TableColumn<>("ID");
        tbcIdTipo.setCellValueFactory(new PropertyValueFactory<>("id_tipo"));

        TableColumn<TipoPlatilloDAO, String> tbcTipo = new TableColumn<>("Tipo");
        tbcTipo.setCellValueFactory(new PropertyValueFactory<>("dsc_tipo"));

        TableColumn<TipoPlatilloDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>()
        {
            @Override
            public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param)
            {
                return new ButtonCustomeTipo(1);
            }
        });

        TableColumn<TipoPlatilloDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>()
        {
            @Override
            public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param)
            {
                return new ButtonCustomeTipo(2);
            }
        });

        tbvTipoPlatillo.getColumns().addAll(tbcIdTipo,tbcTipo,tbcEditar,tbcBorrar);
        tbvTipoPlatillo.setItems(objTPDAO.getAllTipo());
    }
}