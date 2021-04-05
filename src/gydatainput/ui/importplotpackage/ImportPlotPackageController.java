package gydatainput.ui.importplotpackage;

import gydatainput.models.plotpackage.Package;
import gydatainput.models.plotpackage.Visit;
import gydatainput.ui.plotpackage.VisitController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ImportPlotPackageController extends ListCell<Package> {
    @FXML VBox plotPackage;
    @FXML Label lblPlotKey;
    @FXML Label lblPlotName;
    @FXML Label lblApproachCode;
    @FXML Label lblCoOpMethod;
    @FXML Label lblStartYear;
    @FXML ListView<Visit> listVisits;
    @FXML TitledPane paneVisits;

    private Package pPackage; // The list item's plot package.

    public ImportPlotPackageController() {
        loadFXML();
        updateItem(pPackage, false);

        // Set the cell factory to link the UI component for a visit.
        listVisits.setCellFactory(visitListView -> new VisitController());
    }

    private void loadFXML() {
        try{
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../exportplotpackage/ExportPlotPackage.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../plotpackage/PlotPackage.fxml"));
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

            // Set the labels for the plot package UI component.

            // Plot Key
            lblPlotKey.setText(pPackage.getPlot().getFromFields("PlotKey").toString());

            // Plot Name
            lblPlotName.setText(pPackage.getPlot().getFromFields("PlotName").toString());

            // Approach Code
            int approachCode = Math.toIntExact((long) pPackage.getFromFields("ApproachCode"));
            switch (approachCode) {
                case 1:
                    lblApproachCode.setText("1 - Great Lakes-St. Lawrence");
                    break;
                case 2:
                    lblApproachCode.setText("2 - Boreal");
                    break;
            }

            // CoOpMethod
            boolean coOpMethod = (boolean) pPackage.getFromFields("CoOpMethod");
            if (coOpMethod) {
                lblCoOpMethod.setText("Co-Op");
            } else {
                lblCoOpMethod.setText("");
            }

            // Start Year
            lblStartYear.setText(pPackage.getFromFields("StartYear").toString());

            // Visits List
            // Add all visits to the list, which will populate with UI components.
            ArrayList<Visit> visits = pPackage.getVisits();
            if (visits != null && !visits.isEmpty()) {
                for(Visit vst: visits) {
                    listVisits.getItems().add(vst);
                }
            }

            setGraphic(plotPackage);
        }
    }
}
