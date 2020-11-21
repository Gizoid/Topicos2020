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
import sample.models.PlatillosDAO;

public class PlatilloCRUD extends Stage
{
    private VBox vBox;
    private TableView<PlatillosDAO> tbvPlatillos;
    private Button btnNuevo;
    private Scene escena;
    private PlatillosDAO objPDAO;

    public PlatilloCRUD()
    {
        objPDAO = new PlatillosDAO();
        CrearUI();
        this.setTitle("Administración de platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        tbvPlatillos = new TableView<>();
        CrearTabla();
        btnNuevo = new Button("Nuevo Platillo");
        btnNuevo.setOnAction(event -> { new FrmPlatillos(tbvPlatillos,null); });
        vBox = new VBox();
        vBox.getChildren().addAll(tbvPlatillos, btnNuevo);
        escena = new Scene(vBox);
    }

    private void CrearTabla()
    {
        TableColumn<PlatillosDAO, Integer> tbcIdPlatillo = new TableColumn<>("ID");
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("id_platillo"));

        TableColumn<PlatillosDAO, String> tbcNomPlatillo = new TableColumn<>("Nombre");
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nombre_platillo"));

        TableColumn<PlatillosDAO, Float> tbcPrecioPlatillo = new TableColumn<>("Precio");
        tbcPrecioPlatillo.setCellValueFactory(new PropertyValueFactory<>("precio_platillo"));

        TableColumn<PlatillosDAO, Integer> tbcTipoPlatillo = new TableColumn<>("Tipo de platillo");
        tbcTipoPlatillo.setCellValueFactory(new PropertyValueFactory<>("id_tipo"));

        TableColumn<PlatillosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>()
        {
            @Override
            public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param)
            {
                return new ButtonCustome(1);
            }
        });

        TableColumn<PlatillosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>()
        {
            @Override
            public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param)
            {
                return new ButtonCustome(2);
            }
        });

        tbvPlatillos.getColumns().addAll(tbcIdPlatillo, tbcNomPlatillo, tbcPrecioPlatillo, tbcTipoPlatillo,tbcEditar,tbcBorrar);
        tbvPlatillos.setItems(objPDAO.getAllPlatillo());
    }
}