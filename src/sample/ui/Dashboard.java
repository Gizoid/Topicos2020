package sample.ui;

import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class Dashboard extends Stage
{

    public Dashboard()
    {
        CrearUI();
        this.setTitle("Panel de administración del Restaurante El Antojito :)");
        this.show();
        new PlatilloCRUD();
        new TipoPlatilloCRUD();
    }

    private void CrearUI()
    {

    }

}