package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.TipoPlatilloDAO;
import sample.ui.FrmTipoPlatillos;

import java.util.Optional;

public class ButtonCustomeTipo extends TableCell<TipoPlatilloDAO,String>
{
    private Button btnCelda;
    private TipoPlatilloDAO objTipoPlatillo;
    public ButtonCustomeTipo(int opc)
    {
        if(opc==1)
        {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->
            {
                objTipoPlatillo = ButtonCustomeTipo.this.getTableView().getItems().get(ButtonCustomeTipo.this.getIndex());
                new FrmTipoPlatillos(ButtonCustomeTipo.this.getTableView(),objTipoPlatillo);
            });
        }
        else
        {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->
            {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del sistema :)");
                alerta.setHeaderText("Confirmación");
                alerta.setContentText("¿Realmente deseas borrar el registro?");
                Optional<ButtonType> result = alerta.showAndWait();
                if(result.get() == ButtonType.OK)
                {
                    objTipoPlatillo = ButtonCustomeTipo.this.getTableView().getItems().get(ButtonCustomeTipo.this.getIndex());
                    objTipoPlatillo.delTipo();
                    ButtonCustomeTipo.this.getTableView().setItems(objTipoPlatillo.getAllTipo());
                    ButtonCustomeTipo.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item,boolean empty)
    {
        super.updateItem(item,empty);
        if(!empty)
        {
            setGraphic(btnCelda);
        }
    }
}
