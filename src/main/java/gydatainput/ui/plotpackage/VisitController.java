package gydatainput.ui.plotpackage;

import gydatainput.models.plotpackage.Visit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class VisitController extends ListCell<Visit> {
    @FXML HBox visit;
    @FXML Label lblVisitKey;
    @FXML Label lblVisitType;
    @FXML Label lblFieldSeasonYear;
    @FXML Label lblManual;

    private Visit vst; // The list item's visit.

    VisitController() {
        loadFXML();
        updateItem(vst, false);
    }

    private void loadFXML() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Visit.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Visit item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            vst = item;

            lblVisitKey.setText(vst.getFromFields("VisitKey").toString());

            short visitTypeCode = (short) vst.getFromFields("VisitTypeCode");
            switch (visitTypeCode) {
                case 1:
                    lblVisitType.setText(visitTypeCode + " - Locate");
                    break;
                case 2:
                    lblVisitType.setText(visitTypeCode + " - Establishment");
                    break;
                case 3:
                    lblVisitType.setText(visitTypeCode + " - Reconnaissance");
                    break;
                case 4:
                    lblVisitType.setText(visitTypeCode + " - Remeasure");
                    break;
                case 5:
                    lblVisitType.setText(visitTypeCode + " - Post-Treatment");
                    break;
                case 6:
                    lblVisitType.setText(visitTypeCode + " - Correction");
                    break;
                case 7:
                    lblVisitType.setText(visitTypeCode + " - Confirmation");
                    break;
                case 8:
                    lblVisitType.setText(visitTypeCode + " - Quality Assurance");
                    break;
            }
            //lblVisitType.setText(vst.getFromFields("VisitTypeCode").toString());



            lblFieldSeasonYear.setText(vst.getFromFields("FieldSeasonYear").toString());
            lblManual.setText(vst.getFromFields("ManualCode").toString());

            setGraphic(visit);
        }
    }
}
