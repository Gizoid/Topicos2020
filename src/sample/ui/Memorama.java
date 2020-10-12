package sample.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.events.EventosMemorama;

public class Memorama extends Stage implements EventHandler
{
    private String[] arImagenes = {"Aurora","Avia","Chrysalia","Cragspur","Dragnor","Fontaine","Greenleaf","Illusio","Nixtorm","Pugilis","Spectra","Terrera","Valora","Violight","Viperia","Yaksha"};

    private Label lblTarjetas, lblMensaje, lblIntentos;
    private TextField txtNoTarjetas;
    private Button btnAceptar;
    private HBox hBox;
    private VBox vBox;
    private GridPane gdpMesa, gdpMesa2;
    private Button[][] arTarjetas;
    private String[][] arAsignacion;
    private boolean[][] oculto;

    private int noPares=0, intentos=0, i1, j1, i2, j2, correctos=0;
    private boolean carta1=true, error=false;

    private Scene escena;

    public Memorama()
    {
        CrearUI();
        this.setTitle("Memorama :)");
        this.setScene(escena);
        this.setResizable(false);
        this.show();
    }

    private void CrearUI()
    {
        lblTarjetas = new Label("Número de pares (2 - 16)");
        lblMensaje = new Label("Debe ingresar un número entre 2 y 16");
        lblIntentos = new Label("Intentos: "+intentos);
        txtNoTarjetas = new TextField();
        btnAceptar = new Button("Voltear y revolver");
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        //btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventosMemorama(noPares));
        /*btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("Tercer evento desde una clase anónima");
            }
        });*/

        hBox = new HBox();
        hBox.getChildren().addAll(lblTarjetas, txtNoTarjetas, btnAceptar);
        hBox.setSpacing(10);

        gdpMesa = new GridPane();
        gdpMesa2 = new GridPane();

        vBox = new VBox();
        vBox.getChildren().addAll(hBox);

        escena = new Scene(vBox,918,484);
    }

    @Override
    public void handle(Event event)
    {
        error=false;
        noPares=0;
        intentos=0;
        vBox.getChildren().clear();
        try
        {
            noPares = Integer.parseInt(txtNoTarjetas.getText());
        }
        catch (Exception e)
        {

        }
        if(noPares>1 && noPares<17)
        {
            gdpMesa.getChildren().clear();
            gdpMesa2.getChildren().clear();
            arAsignacion = new String[2][noPares];
            revolver();
            arTarjetas = new Button[2][noPares];
            oculto = new boolean[2][noPares];
            if(noPares<9)
            {
                for(int i=0; i<2; i++)
                {
                    for(int j=0; j<noPares; j++)
                    {
                        Image img = new Image("sample/assets/X.jpg");
                        ImageView imv = new ImageView(img);
                        imv.setFitHeight(100);
                        imv.setFitWidth(100);
                        imv.setPreserveRatio(true);

                        arTarjetas[i][j] = new Button();
                        int finalI = i;
                        int finalJ = j;
                        arTarjetas[i][j].setOnAction(event1->verTarjeta(finalI, finalJ));
                        arTarjetas[i][j].setGraphic(imv);
                        arTarjetas[i][j].setPrefSize(100,100);
                        oculto[i][j]=true;
                        gdpMesa.add(arTarjetas[i][j], j, i);
                    }
                }
            }
            else
            {
                for(int i=0; i<2; i++)
                {
                    for(int j=0; j<(noPares+1)/2; j++)
                    {
                        Image img = new Image("sample/assets/X.jpg");
                        ImageView imv = new ImageView(img);
                        imv.setFitHeight(100);
                        imv.setFitWidth(100);
                        imv.setPreserveRatio(true);

                        arTarjetas[i][j] = new Button();
                        int finalI = i;
                        int finalJ = j;
                        arTarjetas[i][j].setOnAction(event1->verTarjeta(finalI, finalJ));
                        arTarjetas[i][j].setGraphic(imv);
                        arTarjetas[i][j].setPrefSize(100,100);
                        oculto[i][j]=true;
                        gdpMesa.add(arTarjetas[i][j], j, i);
                    }
                }
                for(int i=0; i<2; i++)
                {
                    for(int j=(noPares+1)/2; j<noPares; j++)
                    {
                        Image img = new Image("sample/assets/X.jpg");
                        ImageView imv = new ImageView(img);
                        imv.setFitHeight(100);
                        imv.setFitWidth(100);
                        imv.setPreserveRatio(true);

                        arTarjetas[i][j] = new Button();
                        int finalI = i;
                        int finalJ = j;
                        arTarjetas[i][j].setOnAction(event1->verTarjeta(finalI, finalJ));
                        arTarjetas[i][j].setGraphic(imv);
                        arTarjetas[i][j].setPrefSize(100,100);
                        oculto[i][j]=true;
                        gdpMesa2.add(arTarjetas[i][j], (j-((noPares+1)/2)), i);
                    }
                }
            }
            vBox.getChildren().addAll(gdpMesa, gdpMesa2, lblIntentos);
        }
        else
        {
            lblMensaje.setText("Debe ingresar un número entre 2 y 16");
            vBox.getChildren().addAll(hBox, lblMensaje);
        }
        txtNoTarjetas.clear();
    }

    private void verTarjeta(int i, int j)
    {
        if(oculto[i][j])
        {
            System.out.println(arAsignacion[i][j]);
            Image img = new Image("sample/assets/" + arAsignacion[i][j] + ".png");
            ImageView imv = new ImageView(img);
            imv.setFitHeight(100);
            imv.setFitWidth(100);
            imv.setPreserveRatio(true);
            arTarjetas[i][j].setGraphic(imv);
            oculto[i][j]=false;
            if(carta1)
            {
                carta1=false;
                if(error)
                {
                    error=false;
                    Image imgX = new Image("sample/assets/X.jpg");
                    ImageView imvX1 = new ImageView(imgX);
                    ImageView imvX2 = new ImageView(imgX);
                    imvX1.setFitHeight(100);
                    imvX1.setFitWidth(100);
                    imvX1.setPreserveRatio(true);
                    imvX2.setFitHeight(100);
                    imvX2.setFitWidth(100);
                    imvX2.setPreserveRatio(true);
                    arTarjetas[i1][j1].setGraphic(imvX1);
                    oculto[i1][j1]=true;
                    arTarjetas[i2][j2].setGraphic(imvX2);
                    oculto[i2][j2]=true;
                }
                i1=i;
                j1=j;
            }
            else
            {
                carta1 = true;
                i2 = i;
                j2 = j;
                intentos++;
                vBox.getChildren().remove(lblIntentos);
                lblIntentos.setText("Intentos: " + intentos);
                vBox.getChildren().add(lblIntentos);
                if(arAsignacion[i][j].equalsIgnoreCase(arAsignacion[i1][j1]))
                {
                    correctos++;
                    if(correctos == noPares)
                    {
                        lblMensaje.setText("¡¡Felicidades!! Has encontrado todos los pares.");
                        vBox.getChildren().clear();
                        if(noPares < 9)
                        {
                            vBox.getChildren().addAll(hBox, gdpMesa, lblIntentos, lblMensaje);
                        }
                        else
                        {
                            vBox.getChildren().addAll(hBox, gdpMesa, gdpMesa2, lblIntentos, lblMensaje);
                        }
                    }
                }
                else
                {
                    error = true;
                }
            }
        }
    }
    private void revolver()
    {
        correctos=0;
        carta1=true;
        error=false;
        int[] carta = new int[16];
        for(int i=0; i<16; i++)
        {
            carta[i]=0;
        }
        int random;
        boolean ban=true;
        for(int i=1; i<16; i++)
        {
            random=(int)(Math.random()*15);
            do
            {
                if(carta[random]==0)
                {
                    carta[random]=i;
                    ban=false;
                }
                else
                {
                    if(random<15)
                    {
                        random++;
                    }
                    else
                    {
                        random=0;
                    }
                }
            }
            while(ban);
            ban=true;
        }
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<noPares; j++)
            {
                arAsignacion[i][j]="";
            }
        }
        int posx, posy, cont=0;
        for(int i=0;i<noPares;)
        {
            posx = (int)(Math.random()*2);
            posy = (int)(Math.random()*noPares);
            if(arAsignacion[posx][posy].equals(""))
            {
                arAsignacion[posx][posy]=arImagenes[carta[i]];
                cont++;
            }
            if(cont == 2)
            {
                i++;
                cont=0;
            }
        }
    }
}