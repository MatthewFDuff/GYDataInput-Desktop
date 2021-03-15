package gydatainput.ui.importplotpackage;

import gydatainput.models.plotpackage.Package;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ImportPlotPackageController extends ListCell<Package> {
    @FXML
    BorderPane plotPackage;
    @FXML
    Label packageName;

    private Package pPackage; // The list item's plot package.

    public ImportPlotPackageController() {
        loadFXML();
        updateItem(pPackage, false);
    }

    private void loadFXML() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../exportplotpackage/ExportPlotPackage.fxml"));
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
            if (pPackage != null) {
                packageName.setText(pPackage.getPlot().getFields().get("PlotName").toString());
            } else {
                System.out.println("Unable to display package information: Plot Package is null");
            }

            setGraphic(plotPackage);
        }
    }
}
