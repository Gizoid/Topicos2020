package sample.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Taquimecanografo extends Stage
{
    private ToolBar tlbMenu;
    private Button btnAbrir;

    private TextArea txtContenido, txtEscritura;

    private HBox[] arHBoxTeclas = new HBox[6];
    private VBox vBoxTeclado;
    private Button[] arTeclado1 = new Button[15];

    private VBox vBoxPrincipal;
    private Scene escena;

    public Taquimecanografo()
    {
        CrearUI();
        this.setTitle("Tutor de taquimecanografía");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        CrearToolbar();
        CrearEscritura();
        CrearTeclado();
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(tlbMenu, txtContenido, txtEscritura);
        vBoxPrincipal.setSpacing(5);
        vBoxPrincipal.setPadding(new Insets(5));
        escena = new Scene(vBoxPrincipal, 500,600);
    }

    private void CrearToolbar()
    {
        tlbMenu = new ToolBar();
        btnAbrir = new Button();
        btnAbrir.setOnAction(event -> eventoTaqui(1));
        btnAbrir.setPrefSize(20,20);
        Image img = new Image("sample/assets/abrir.png");
        ImageView imv = new ImageView(img);
        imv.setFitHeight(20);
        imv.setFitWidth(20);
        imv.setPreserveRatio(true);
        btnAbrir.setGraphic(imv);
        tlbMenu.getItems().add(btnAbrir);
    }

    private void eventoTaqui(int opc)
    {
        switch(opc)
        {
            case 1:
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Abrir archivo...");
                File file = fileChooser.showOpenDialog(this);
        }
    }

    private void CrearEscritura()
    {
        txtContenido = new TextArea();
        txtContenido.setPrefRowCount(6);
        txtEscritura = new TextArea();
        txtEscritura.setPrefRowCount(6);
    }

    private void CrearTeclado()
    {

    }

}
