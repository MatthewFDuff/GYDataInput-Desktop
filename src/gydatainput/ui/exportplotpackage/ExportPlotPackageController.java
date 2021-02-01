package gydatainput.ui.exportplotpackage;

import gydatainput.models.plotpackage.Package;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

// 2020-12-05
public class ExportPlotPackageController extends ListCell<Package> {
    @FXML BorderPane plotPackage;
    @FXML Label packageName;

    private Package pPackage; // The list item's plot package.

    public ExportPlotPackageController() {
        loadFXML();
        updateItem(pPackage, false);
    }

    private void loadFXML() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExportPlotPackage.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Package item, boolean empty) {
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
