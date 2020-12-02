package gydatainput.ui.plotpackage;

import gydatainput.models.plotpackage.PlotPackage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PlotPackageController extends ListCell<PlotPackage> {
    @FXML HBox plotPackage;
    @FXML Label packageName;
    @FXML Label lblDate;

    private static PlotPackageController selected; // Keeps track of which plot package is selected.
    public static void setSelected(PlotPackageController ppc) { selected = ppc; }
    public static PlotPackageController getSelected() { return selected; }
    public PlotPackage getPlotPackage() {
        return pPackage;
    }

    private PlotPackage pPackage; // The list item's plot package.

    public PlotPackageController() {
        loadFXML();

//        plotPackage.setOn(e -> {
//            PlotPackageController previousSelected = null;
//
//            // Save the previously selected item.
//            if (selected != null) {
//                previousSelected = selected;
//            }
//
//            // Change to the currently selected item.
//            selected = this;
//
            // Update the currently selected item.
            updateItem(pPackage, false);
//
//            System.out.println(selected.pPackage.getName() + " was selected.");
//
//        });

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
            lblDate.setText(pPackage.getDate());

            setGraphic(plotPackage);
        }

    }
}
