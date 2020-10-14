package sample.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent>
{
    boolean banColor = false;

    private String[] arLblBtn1 = {"esc","f1","f2","f3","f4","f5","f6","f7","f8","f9","f10","f11","f12","imp pnt","supr"};//15
    private String[] arLblBtn2 = {"|","1","2","3","4","5","6","7","8","9","0","?","¿","<--"};//14
    private String[] arLblBtn3 = {"-->|","Q","W","E","R","T","Y","U","I","O","P","´","+"};//13
    private String[] arLblBtn4 = {"bloq mayús","A","S","D","F","G","H","J","K","L","Ñ","{","}"};//13
    private String[] arLblBtn5 = {"^","<","Z","X","C","V","B","N","M",",",".","-","^"};//13
    private String[] arLblBtn6 = {"ctrl","fn","[+]","alt","          ","alt gr","ctrl","<",">"};//9

    private ToolBar tlbMenu;
    private Button btnAbrir;

    private TextArea txtContenido, txtEscritura;

    private HBox[] arHBoxTeclas = new HBox[6];
    private VBox vBoxTeclado;
    private Button[] arTeclado1 = new Button[15];
    private Button[] arTeclado2 = new Button[14];
    private Button[] arTeclado3 = new Button[13];
    private Button[] arTeclado4 = new Button[13];
    private Button[] arTeclado5 = new Button[13];
    private Button[] arTeclado6 = new Button[9];

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
        vBoxPrincipal.getChildren().addAll(tlbMenu, txtContenido, txtEscritura, vBoxTeclado);
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
        txtContenido.setEditable(false);
        txtContenido.setPrefRowCount(6);
        txtEscritura = new TextArea();
        txtEscritura.setPrefRowCount(6);
        //txtEscritura.addEventHandler(KeyEvent.KEY_TYPED,this);
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);
    }

    private void CrearTeclado()
    {
        vBoxTeclado = new VBox();
        for(int i=0; i<arHBoxTeclas.length; i++)
        {
            arHBoxTeclas[i] = new HBox();
        }
        for(int i=0; i<arTeclado1.length; i++)
        {
            arTeclado1[i] = new Button(arLblBtn1[i]);
            arHBoxTeclas[0].getChildren().add(arTeclado1[i]);
        }
        for(int i=0; i<arTeclado2.length; i++)
        {
            arTeclado2[i] = new Button(arLblBtn2[i]);
            arHBoxTeclas[1].getChildren().add(arTeclado2[i]);
        }
        for(int i=0; i<arTeclado3.length; i++)
        {
            arTeclado3[i] = new Button(arLblBtn3[i]);
            arHBoxTeclas[2].getChildren().add(arTeclado3[i]);
            arTeclado4[i] = new Button(arLblBtn4[i]);
            arHBoxTeclas[3].getChildren().add(arTeclado4[i]);
            arTeclado5[i] = new Button(arLblBtn5[i]);
            arHBoxTeclas[4].getChildren().add(arTeclado5[i]);
        }
        for(int i=0; i<arTeclado6.length; i++)
        {
            arTeclado6[i] = new Button(arLblBtn6[i]);
            arHBoxTeclas[5].getChildren().add(arTeclado6[i]);
        }
        vBoxTeclado.getChildren().addAll(arHBoxTeclas[0],arHBoxTeclas[1],arHBoxTeclas[2],arHBoxTeclas[3],arHBoxTeclas[4],arHBoxTeclas[5]);
    }

    @Override
    public void handle(KeyEvent event)
    {
        switch(event.getCode().toString())
        {
            case "ESCAPE": break;
            case "F5": break;
            case "PRINTSCREEN": break;
            case "DIGIT1": break;
            case "DELETE": break;
            case "DIGIT2": break;
            case "DIGIT3": break;
            case "DIGIT4": break;
            case "DIGIT5": break;
            case "DIGIT6": break;
            case "DIGIT7": break;
            case "DIGIT8": break;
            case "DIGIT9": break;
            case "DIGIT0": break;
            case "QUOTE": break;
            case "BACK_SPACE": break;
            case "TAB": break;
            case "Q": break;
            case "W": break;
            case "E": break;
            case "R": break;
            case "T": break;
            case "Y": break;
            case "U": break;
            case "I": break;
            case "O": break;
            case "P": break;
            case "DEAD_ACUTE": break;
            case "PLUS": break;
            case "CAPS":
                if(banColor)
                {
                    arTeclado4[0].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[0].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "A":
                if(banColor)
                {
                    arTeclado4[1].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[1].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "S":
                if(banColor)
                {
                    arTeclado4[2].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[2].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "D":
                if(banColor)
                {
                    arTeclado4[3].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[3].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "F":
                if(banColor)
                {
                    arTeclado4[4].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[4].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "G":
                if(banColor)
                {
                    arTeclado4[5].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[5].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "H":
                if(banColor)
                {
                    arTeclado4[6].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[6].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "J":
                if(banColor)
                {
                    arTeclado4[7].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[7].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "K":
                if(banColor)
                {
                    arTeclado4[8].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[8].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "L":
                if(banColor)
                {
                    arTeclado4[9].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[9].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "BRACELEFT":
                if(banColor)
                {
                    arTeclado4[11].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[11].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "BRACERIGHT":
                if(banColor)
                {
                    arTeclado4[12].setStyle("-fx-background-color: #66DEDE;");
                }
                else
                {
                    arTeclado4[12].setStyle("-fx-background-color: #009999;");
                }
                break;
            case "ENTER": break;
            case "SHIFT": break;
            case "LESS": break;
            case "Z": break;
            case "X": break;
            case "C": break;
            case "V": break;
            case "B": break;
            case "N": break;
            case "M": break;
            case "COMMA": break;
            case "PERIOD": break;
            case "MINUS": break;
            case "CONTROL": break;
            case "WINDOWS": break;
            case "ALT": break;
            case "SPACE": break;
            case "ALT_GRAPH": break;
            case "LEFT": break;
            case "UP": break;
            case "DOWN": break;
            case "RIGHT": break;
        }
        banColor = !banColor;
    }
}
