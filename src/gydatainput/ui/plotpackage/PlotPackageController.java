package gydatainput.ui.plotpackage;

import gydatainput.models.plotpackage.PlotPackage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PlotPackageController extends ListCell<PlotPackage> {

    @FXML HBox plotPackage;
    @FXML Label packageName;

    private PlotPackage pPackage;

    public PlotPackageController() {
        loadFXML();

        updateItem(pPackage, false);
    }

    private void loadFXML() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlotPackage.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(PlotPackage item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            pPackage = item;
            packageName.setText(pPackage.getName());

            setGraphic(plotPackage);
        }

    }
}
