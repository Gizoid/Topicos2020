package sample.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent>
{
    private String[] arLblBtn1 = {"esc","f1","f2","f3","f4","f5","f6","f7","f8","f9","f10","f11","f12","imp pnt","supr"};//15
    private String[] arLblBtn2 = {"|","1","2","3","4","5","6","7","8","9","0","?","¿","                <--"};//14
    private String[] arLblBtn3 = {"|<-            \n->|","Q","W","E","R","T","Y","U","I","O","P","´","+"};//13
    private String[] arLblBtn4 = {"bloq mayús","A","S","D","F","G","H","J","K","L","Ñ","{","}"};//13
    private String[] arLblBtn5 = {"^   "," <","Z","X","C","V","B","N","M",",",".","-","                    ^"};//13
    private String[] arLblBtn6 = {"ctrl","fn","[+]","alt","","alt gr","ctrl","<",">"};//9
    private String texto="", txtTiempo="Tiempo: 00:00";
    private boolean tiempoIniciado=false;
    private char[] arTexto, arEscritura;
    private int errores=0, palabras=0, min=0, seg=0;
    Timeline tiempo;

    private ToolBar tlbMenu;
    private Button btnAbrir, btnEnter, btnArriba, btnAbajo;

    private TextArea txtContenido, txtEscritura;

    private Label lblErrores, lblPalabras, lblTiempo;

    private HBox[] arHBoxTeclas = new HBox[6];
    private HBox hBoxTeclado3y4, estadisticas;
    private VBox vBoxTeclado, vBoxTeclado3y4, vBoxArrAb;
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
        this.setTitle("Tutor de taquimecanografía (｡◕‿◕｡)");
        this.setScene(escena);
        this.setResizable(false);
        this.show();
    }

    private void CrearUI()
    {
        CrearToolbar();
        CrearEscritura();
        CrearTeclado();
        lblErrores = new Label("Errores: "+errores/2);
        lblPalabras = new Label("Palabras: "+palabras/2);
        lblTiempo = new Label(txtTiempo);
        estadisticas = new HBox();
        estadisticas.setSpacing(10);
        estadisticas.getChildren().addAll(lblErrores, lblPalabras, lblTiempo);
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(tlbMenu, txtContenido, txtEscritura, estadisticas, vBoxTeclado);
        vBoxPrincipal.setSpacing(5);
        vBoxPrincipal.setPadding(new Insets(5));
        escena = new Scene(vBoxPrincipal, 777,600);
        escena.getStylesheets().add("sample/assets/css/taqui_styles.css");
    }

    private void CrearToolbar()
    {
        tlbMenu = new ToolBar();
        btnAbrir = new Button();
        btnAbrir.setOnAction(event -> eventoTaqui(1));
        btnAbrir.setPrefSize(30,20);
        btnAbrir.setStyle("-fx-background-color: #AABBCC;");
        Image img = new Image("sample/assets/abrir.png");
        ImageView imv = new ImageView(img);
        imv.setFitHeight(20);
        imv.setFitWidth(30);
        imv.setPreserveRatio(true);
        btnAbrir.setGraphic(imv);
        tlbMenu.getItems().add(btnAbrir);
    }

    private void eventoTaqui(int opc)
    {
        errores=0;
        switch(opc)
        {
            case 1:
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Abrir archivo...");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));
                File file = fileChooser.showOpenDialog(this);

                if(file != null)
                {
                    if(tiempoIniciado)
                    {
                        tiempo.stop();
                    }
                    errores=0;
                    palabras=0;
                    min=0;
                    seg=0;
                    txtTiempo="Tiempo: 00:00";
                    txtEscritura.setEditable(true);
                    txtEscritura.setText("");
                    lblErrores.setText("Errores: "+errores);
                    lblPalabras.setText("Palabras: "+palabras);
                    lblTiempo.setText(txtTiempo);
                    FileReader fr;
                    BufferedReader br;
                    texto="";
                    try
                    {
                        fr = new FileReader(file);
                        br = new BufferedReader(fr);
                        String st = br.readLine();
                        while(st != null)
                        {
                            texto = texto + st + "\n";
                            st = br.readLine();
                        }
                        txtContenido.setText(texto);
                        fr.close();
                        arTexto = texto.toCharArray();
                        iniciarTiempo();
                    }
                    catch (Exception e)
                    {

                    }
                }
                break;
        }
    }

    private void CrearEscritura()
    {
        txtContenido = new TextArea();
        txtContenido.setEditable(false);
        //txtContenido.setMouseTransparent(true);
        txtContenido.setPrefRowCount(6);
        txtEscritura = new TextArea();
        txtEscritura.setPrefRowCount(6);
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);
    }

    private void CrearTeclado()
    {
        vBoxTeclado = new VBox();

        for(int i=0; i<arHBoxTeclas.length; i++)
        {
            arHBoxTeclas[i] = new HBox();
            arHBoxTeclas[i].setSpacing(3);
        }

        for(int i=0; i<arTeclado1.length; i++)
        {
            arTeclado1[i] = new Button(arLblBtn1[i]);
            arTeclado1[i].setId("fila1");
            arHBoxTeclas[0].getChildren().add(arTeclado1[i]);
        }

        for(int i=0; i<arTeclado2.length; i++)
        {
            arTeclado2[i] = new Button(arLblBtn2[i]);
            arTeclado2[i].setId("btn");
            arHBoxTeclas[1].getChildren().add(arTeclado2[i]);
        }
        arTeclado2[0].setId("l");
        arTeclado2[13].setId("backspace");

        for(int i=0; i<arTeclado3.length; i++)
        {
            arTeclado3[i] = new Button(arLblBtn3[i]);
            arTeclado3[i].setId("btn");
            arHBoxTeclas[2].getChildren().add(arTeclado3[i]);
            arTeclado4[i] = new Button(arLblBtn4[i]);
            arTeclado4[i].setId("btn");
            arHBoxTeclas[3].getChildren().add(arTeclado4[i]);
            arTeclado5[i] = new Button(arLblBtn5[i]);
            arTeclado5[i].setId("btn");
            arHBoxTeclas[4].getChildren().add(arTeclado5[i]);
        }
        arTeclado3[0].setId("tab");
        arTeclado4[0].setId("mayus");
        arTeclado5[12].setId("shift");

        vBoxTeclado3y4 = new VBox();
        vBoxTeclado3y4.setSpacing(3);
        vBoxTeclado3y4.getChildren().addAll(arHBoxTeclas[2],arHBoxTeclas[3]);

        btnEnter = new Button("<--/");
        btnEnter.setId("enter");

        hBoxTeclado3y4 = new HBox();
        hBoxTeclado3y4.setSpacing(3);
        hBoxTeclado3y4.getChildren().addAll(vBoxTeclado3y4,btnEnter);

        btnArriba = new Button("^");
        btnArriba.setId("fila1");
        btnAbajo = new Button("v");
        btnAbajo.setId("fila1");
        vBoxArrAb = new VBox();
        vBoxArrAb.getChildren().addAll(btnArriba,btnAbajo);

        for(int i=0; i<arTeclado6.length; i++)
        {
            arTeclado6[i] = new Button(arLblBtn6[i]);
            arTeclado6[i].setId("btn");
            if(i==arTeclado6.length-1)
            {
                arHBoxTeclas[5].getChildren().add(vBoxArrAb);
            }
            arHBoxTeclas[5].getChildren().add(arTeclado6[i]);
        }
        arTeclado6[3].setId("alt");
        arTeclado6[4].setId("espacio");
        arTeclado6[5].setId("alt");

        vBoxTeclado.setSpacing(3);
        vBoxTeclado.getChildren().addAll(arHBoxTeclas[0],arHBoxTeclas[1],hBoxTeclado3y4,arHBoxTeclas[4],arHBoxTeclas[5]);
    }

    @Override
    public void handle(KeyEvent event)
    {
        switch(event.getCode().toString())
        {
            case "ESCAPE": ColorTeclado(event,1,0); break;
            case "F1": ColorTeclado(event,1,1); break;
            case "F2": ColorTeclado(event,1,2); break;
            case "F3": ColorTeclado(event,1,3); break;
            case "F4": ColorTeclado(event,1,4); break;
            case "F5": ColorTeclado(event,1,5); break;
            case "F6": ColorTeclado(event,1,6); break;
            case "F7": ColorTeclado(event,1,7); break;
            case "F8": ColorTeclado(event,1,8); break;
            case "F9": ColorTeclado(event,1,9); break;
            case "F10": ColorTeclado(event,1,10); break;
            case "F11": ColorTeclado(event,1,11); break;
            case "F12": ColorTeclado(event,1,12); break;
            case "PRINTSCREEN": ColorTeclado(event,1,13); break;
            case "DELETE": ColorTeclado(event,1,14); break;

            case "DIGIT1": ColorTeclado(event,2,1); break;
            case "DIGIT2": ColorTeclado(event,2,2); break;
            case "DIGIT3": ColorTeclado(event,2,3); break;
            case "DIGIT4": ColorTeclado(event,2,4); break;
            case "DIGIT5": ColorTeclado(event,2,5); break;
            case "DIGIT6": ColorTeclado(event,2,6); break;
            case "DIGIT7": ColorTeclado(event,2,7); break;
            case "DIGIT8": ColorTeclado(event,2,8); break;
            case "DIGIT9": ColorTeclado(event,2,9); break;
            case "DIGIT0": ColorTeclado(event,2,10); break;
            case "QUOTE": ColorTeclado(event,2,11); break;
            case "BACK_SPACE": ColorTeclado(event,2,13); break;

            case "TAB": ColorTeclado(event,3,0); break;
            case "Q": ColorTeclado(event,3,1); break;
            case "W": ColorTeclado(event,3,2); break;
            case "E": ColorTeclado(event,3,3); break;
            case "R": ColorTeclado(event,3,4); break;
            case "T": ColorTeclado(event,3,5); break;
            case "Y": ColorTeclado(event,3,6); break;
            case "U": ColorTeclado(event,3,7); break;
            case "I": ColorTeclado(event,3,8); break;
            case "O": ColorTeclado(event,3,9); break;
            case "P": ColorTeclado(event,3,10); break;
            case "DEAD_ACUTE": ColorTeclado(event,3,11); break;
            case "PLUS": ColorTeclado(event,3,12); break;

            case "CAPS": ColorTeclado(event,4,0); break;
            case "A": ColorTeclado(event,4,1); break;
            case "S": ColorTeclado(event,4,2); break;
            case "D": ColorTeclado(event,4,3); break;
            case "F": ColorTeclado(event,4,4); break;
            case "G": ColorTeclado(event,4,5); break;
            case "H": ColorTeclado(event,4,6); break;
            case "J": ColorTeclado(event,4,7); break;
            case "K": ColorTeclado(event,4,8); break;
            case "L": ColorTeclado(event,4,9); break;
            case "BRACELEFT": ColorTeclado(event,4,11); break;
            case "BRACERIGHT": ColorTeclado(event,4,12); break;

            case "ENTER":
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    btnEnter.setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    btnEnter.setStyle("-fx-background-color: #5577AA;");
                }
                break;

            case "SHIFT": ColorTeclado(event,5,0); ColorTeclado(event,5,12); break;
            case "LESS": ColorTeclado(event,5,1); break;
            case "Z": ColorTeclado(event,5,2); break;
            case "X": ColorTeclado(event,5,3); break;
            case "C": ColorTeclado(event,5,4); break;
            case "V": ColorTeclado(event,5,5); break;
            case "B": ColorTeclado(event,5,6); break;
            case "N": ColorTeclado(event,5,7); break;
            case "M": ColorTeclado(event,5,8); break;
            case "COMMA": ColorTeclado(event,5,9); break;
            case "PERIOD": ColorTeclado(event,5,10); break;
            case "MINUS": ColorTeclado(event,5,11); break;

            case "CONTROL": ColorTeclado(event,6,0); ColorTeclado(event,6,6); break;
            case "WINDOWS": ColorTeclado(event,6,2); break;
            case "ALT": ColorTeclado(event,6,3); break;
            case "SPACE": ColorTeclado(event,6,4); break;
            case "ALT_GRAPH": ColorTeclado(event,6,5); break;
            case "LEFT": ColorTeclado(event,6,7); break;
            case "UP":
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    btnArriba.setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    btnArriba.setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case "DOWN":
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    btnAbajo.setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    btnAbajo.setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case "RIGHT": ColorTeclado(event,6,8); break;
        }

        switch(event.getText())
        {
            case "|": ColorTeclado(event,2,0); break;
            case "¿": ColorTeclado(event,2,12); break;
            case "ñ":
            case "Ñ": ColorTeclado(event,4,10); break;
        }

        if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
        {
            arTeclado3[11].setStyle("-fx-background-color: #5577AA;");
            Comparar();
        }

    }

    private void ColorTeclado(KeyEvent event, int fila, int tecla)
    {
        switch(fila)
        {
            case 1:
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    arTeclado1[tecla].setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    arTeclado1[tecla].setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case 2:
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    arTeclado2[tecla].setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    arTeclado2[tecla].setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case 3:
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    arTeclado3[tecla].setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    arTeclado3[tecla].setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case 4:
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    arTeclado4[tecla].setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    arTeclado4[tecla].setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case 5:
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    arTeclado5[tecla].setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    arTeclado5[tecla].setStyle("-fx-background-color: #5577AA;");
                }
                break;
            case 6:
                if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
                {
                    arTeclado6[tecla].setStyle("-fx-background-color: #335588;");
                }
                if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
                {
                    arTeclado6[tecla].setStyle("-fx-background-color: #5577AA;");
                }
                break;
        }

        if((fila == 0 && tecla == 14) || (fila == 1 && tecla == 13))
        {
            if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
            {
                errores++;
                lblErrores.setText("Errores: "+errores);
            }
        }
    }

    private void Comparar()
    {
        if(!txtEscritura.getText().equals("") && !txtContenido.getText().equals(""))
        {
            if(txtEscritura.getText().length()<=texto.length())
            {
                arEscritura = txtEscritura.getText().toCharArray();
                if(arEscritura[arEscritura.length-1] != arTexto[arEscritura.length-1])
                {
                    errores++;
                    lblErrores.setText("Errores: "+errores);
                }
                palabras = txtEscritura.getText().split(" ").length;
                lblPalabras.setText("Palabras: "+palabras);
                if(txtEscritura.getText().length()==texto.length()-1)
                {
                    txtEscritura.appendText("\n");
                    if(txtEscritura.getText().equals(texto))
                    {
                        tiempo.stop();
                        tiempoIniciado=false;
                        txtEscritura.setEditable(false);
                        lblTiempo.setText(lblTiempo.getText()+"   Texto terminado.");
                    }
                    else
                    {
                        txtEscritura.deletePreviousChar();
                    }
                }
            }
            else
            {
                errores++;
                lblErrores.setText("Errores: "+errores);
            }
        }
    }

    private void iniciarTiempo()
    {
        tiempoIniciado=true;
        tiempo = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                seg++;
                if(seg==60)
                {
                    seg=0;
                    min++;
                }
                txtTiempo="Tiempo: ";
                if(min<10)
                {
                    txtTiempo+="0"+min;
                }
                else
                {
                    txtTiempo+=min;
                }
                if(seg<10)
                {
                    txtTiempo+=":0"+seg;
                }
                else
                {
                    txtTiempo+=":"+seg;
                }
                lblTiempo.setText(txtTiempo);
            }
        }));
        tiempo.setCycleCount(Timeline.INDEFINITE);
        tiempo.play();
    }
}