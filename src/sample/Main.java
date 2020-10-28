package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.ui.Memorama;
import sample.ui.Taquimecanografo;

public class Main extends Application implements EventHandler
{
    private VBox vBoxPrincipal;
    private MenuBar mnbPrincipal;
    private Menu menuCompetencia1, menuCompetencia2, menuSalir;
    private MenuItem itemMemorama, itemPractica2, terminar;
    private Scene escena;

    private ToolBar toolBar;
    private Button btnToolBar1;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        CrearUI();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Prácticas de Tópicos 2020");
        primaryStage.setMaximized(true);
        primaryStage.setScene(escena);
        //primaryStage.setScene(new Scene(new Button("Presiona"), 300, 275));
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,this);
        primaryStage.show();
    }

    private void CrearUI()
    {
        mnbPrincipal = new MenuBar();

        menuCompetencia1 = new Menu("Competencia 1");
        menuCompetencia2 = new Menu("Competencia 2");
        menuSalir = new Menu("Salir");

        mnbPrincipal.getMenus().addAll(menuCompetencia1, menuCompetencia2, menuSalir);

        itemMemorama = new MenuItem("Memorama");
        itemMemorama.setOnAction(event -> opcionMenu(1));
        itemPractica2 = new MenuItem("Taquimecanógrafo");
        itemPractica2.setOnAction(event -> opcionMenu(2));
        terminar = new MenuItem("Hasta pronto :)");
        terminar.setOnAction(event -> {System.exit(0);});

        menuCompetencia1.getItems().addAll(itemMemorama, itemPractica2);
        menuSalir.getItems().add(terminar);

        toolBar = new ToolBar();
        btnToolBar1 = new Button();
        btnToolBar1.setOnAction(event -> opcionMenu(1));
        Image img = new Image("sample/assets/Memorama.png");
        ImageView imv = new ImageView(img);
        imv.setFitHeight(20);
        imv.setFitWidth(20);
        imv.setPreserveRatio(true);
        btnToolBar1.setPrefSize(20,20);
        btnToolBar1.setGraphic(imv);

        toolBar.getItems().add(btnToolBar1);

        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(mnbPrincipal, toolBar);

        escena = new Scene(vBoxPrincipal);
        escena.getStylesheets().add("sample/assets/css/main_styles.css");
    }

    private void opcionMenu(int opc)
    {
        switch(opc)
        {
            case 1: new Memorama(); break;
            case 2: new Taquimecanografo(); break;
            case 3: break;
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void handle(Event event)
    {
        System.out.println("Se está cerrando la ventana");
    }
}