
        package gydatainput.ui.plotpackage;

        import gydatainput.models.plotpackage.Package;
        import gydatainput.models.plotpackage.Visit;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListCell;
        import javafx.scene.control.ListView;
        import javafx.scene.control.TitledPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;

        import java.io.IOException;
        import java.util.ArrayList;

public class PlotPackageController extends ListCell<Package> {
    @FXML VBox plotPackage;
    @FXML Label lblPlotKey;
    @FXML Label lblPlotName;
    @FXML Label lblApproachCode;
    @FXML Label lblCoOpMethod;
    @FXML Label lblStartYear;
    @FXML ListView<Visit> listVisits;
    @FXML TitledPane paneVisits;

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

        // Set the cell factory to link the UI component for a visit.
        listVisits.setCellFactory(visitListView -> new VisitController());
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

    public void updateSelected() {

    }

    @Override
    protected void updateItem(Package item, boolean empty) {
        super.updateItem(item, empty);

        listVisits.getItems().clear();

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
            short approachCode = (short) pPackage.getFromFields("ApproachCode");
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
            for(Visit vst: pPackage.getVisits()) {
                listVisits.getItems().add(vst);
            }

            setGraphic(plotPackage);
        }
    }

    private void updateItemSelection(Package item, boolean selected) {

    }
}