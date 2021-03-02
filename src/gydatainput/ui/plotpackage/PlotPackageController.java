package gydatainput.ui.plotpackage;

import gydatainput.models.plotpackage.Package;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PlotPackageController extends ListCell<Package> {
    @FXML HBox plotPackage;
    @FXML Label packageName;
    @FXML Label lblDate;

    private static PlotPackageController selected; // Keeps track of which plot package is selected.
    public static void setSelected(PlotPackageController ppc) { selected = ppc; }
    public static PlotPackageController getSelected() { return selected; }
    public Package getPlotPackage() {
        return pPackage;
    }

    private Package pPackage; // The list item's plot package.

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
    protected void updateItem(Package item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            pPackage = item;
            packageName.setText(pPackage.getPlot().getFields().get("PlotName").toString());
//            lblDate.setText(pPackage.getDate());
            lblDate.setText("");

            setGraphic(plotPackage);
        }
    }
}
