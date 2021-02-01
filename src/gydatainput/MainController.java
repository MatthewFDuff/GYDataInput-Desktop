package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.location.LocCoord;
import gydatainput.models.location.LocPlot;
import gydatainput.models.location.PlotAccess;
import gydatainput.models.note.Note;
import gydatainput.models.note.NoteFixup;
import gydatainput.models.note.NotePlot;
import gydatainput.models.photo.PhotoFeature;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.photo.PhotoRequired;
import gydatainput.models.plotpackage.Package;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.standinformation.*;
import gydatainput.models.tree.*;
import gydatainput.models.vegetation.*;
import gydatainput.ui.exportplotpackage.ExportPlotPackageController;
import gydatainput.ui.plotpackage.PlotPackageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *  The main controller class controls the main UI functionality and
 *  initializes all other controllers, including the database.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-11-28
 * */
public class MainController implements Initializable {
    @FXML ListView<Package> listCompleted;
    @FXML ListView<Package> listExports;
    @FXML Button btnImportPackage;
    @FXML Button btnDownloadPackage;
    @FXML TextField txtFilterCompleted;

    // A list of all plot packages that are currently in the database.
    public ObservableList<Package> packages = FXCollections.observableArrayList();
    // A list of all plot packages that are currently in the export list.
    public ObservableList<Package> exportPackages = FXCollections.observableArrayList();

    // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced in this file)
    DatabaseController database = DatabaseController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPackages(); // Gets the packages from the database and populates the completed packages list.

        // The FilteredList is built from the ObservableList so we can update it each time the filter changes.
        FilteredList<Package> filteredPackages = new FilteredList<>(packages);

        // Update the list each time the filter TextField is updated by the user.
        // Reference: https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        txtFilterCompleted.textProperty().addListener((observable, oldValue, newValue) -> {
            // The predicate is what allows us to filter the list dynamically.
            filteredPackages.setPredicate(plotPackage -> {
                // If the filter field is empty, show all packages.
                if(newValue == null || newValue.isEmpty()) {
                    return true; // All items will appear in the list.
                }

                // Compare the filter with the name of the plot package (both lowercase).
                String lowerCaseFilter = newValue.toLowerCase();
                if(plotPackage.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // This item will appear in the list.
                }

                return false; // This item will not appear in the list.
            });
        });

        // Update the list of completed packages.
        listCompleted.setItems(filteredPackages);

        // The cell factory provides each plot package a list item UI element.
        listCompleted.setCellFactory(completedListView -> new PlotPackageController());
        listExports.setCellFactory(exportListView -> new ExportPlotPackageController());

        // This allows us to get the currently selected items from the completed package list.
        listCompleted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observableValue, Package oldPackage, Package newPackage) {
                System.out.println("Completed selection changed from oldValue = "
                        + oldPackage + " to newValue = " + newPackage);
            }
        });

        // This allows us to get the currently selected items from the export package list.
        listExports.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observableValue, Package oldPackage, Package newPackage) {
                System.out.println("Export selection changed from oldValue = "
                        + oldPackage + " to newValue = " + newPackage);
            }
        });

        System.out.println(System.getProperty("user.dir"));
    }

    /** Load Packages
     *      This function requests all plot packages from the database
     *      and adds them to the list of "Completed" packages.
     * @return void
     * @exception
     * */
    public void loadPackages() {
        if (DatabaseHelper.loadPlotPackages(packages)) {
            for(Package pPackage : packages) {
                listCompleted.getItems().add(pPackage);
            }

            System.out.println("Plot packages loaded.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to load plot packages.");
            alert.showAndWait();
        }
    }

    @FXML
    /** Export Plot Packages
     *      This function will export all plot packages from the exports list and
     *      create individual JSON files for each of them in a contained folder.
     * @param
     */
    void exportPlotPackages(ActionEvent event) {
        try {
            // TODO: Create a new timestamped folder for the exported packages.
            Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
            Files.createDirectory(path);

            // Iterate through each package in the exports list.
            for (Package pkg: listExports.getItems()) {
                // Retrieve all data for the plot package from the database
                downloadPlotPackage(pkg);

                // TODO: Save the plot package data as a JSON object/file in the new folder.

                // PACKAGE
                JSONObject pkgJSON = new JSONObject();
                pkgJSON.put("PackageKey", pkg.getPackageKey());
                pkgJSON.put("PlotKey", pkg.getPlotKey());
                pkgJSON.put("StartYear", pkg.getStartYear());
                pkgJSON.put("ApproachCode", pkg.getApproachCode());
                pkgJSON.put("CoOpMethod", pkg.isCoOpMethod());

                // PLOT
                JSONObject plotJSON = new JSONObject();
                plotJSON.put("PlotKey", pkg.getPlotKey());
                plotJSON.put("PlotName", pkg.getName());
                plotJSON.put("DatasetCode", pkg.getPlot().getDatasetCode());
                plotJSON.put("AdminRegionCode", pkg.getPlot().getAdminRegionCode());
                plotJSON.put("ProtectionNegotiable", pkg.getPlot().isProtectionNegotiable());

                pkgJSON.put("Plot", plotJSON);

                // VISIT
                JSONObject visitJSON = new JSONObject();
                JSONArray visits = new JSONArray();
                for (Visit visit: pkg.getVisits()) {
                    JSONObject n = new JSONObject();
                    n.put("VisitKey", visit.getVisitKey());
                    n.put("PackageKey", pkg.getPackageKey());
                    n.put("VisitTypeCode", visit.getVisitTypeCode());
                    n.put("ManualCode", visit.getManualCode());

                    // Stand Information Header
                    JSONObject standInfoHeaderJSON = new JSONObject();
                    StandInfoHeader standInfoHeader = visit.getStandInfoHeader();
                    if (standInfoHeader != null) {
                        standInfoHeaderJSON.put("StandInfoHeaderKey", standInfoHeader.getStandInfoHeaderKey());
                        standInfoHeaderJSON.put("VisitKey", visit.getVisitKey());
                        standInfoHeaderJSON.put("MsrDate", standInfoHeader.getMsrDate());
                        standInfoHeaderJSON.put("CanopyStructCode", standInfoHeader.getCanopyStructCode());
                        standInfoHeaderJSON.put("PlotUnifCode", standInfoHeader.getPlotUnifCode());
                        standInfoHeaderJSON.put("PlotUnifRationale", standInfoHeader.getPlotUnifRationale());
                        standInfoHeaderJSON.put("MainCanopyOriginCode", standInfoHeader.getMainCanopyOriginCode());
                        standInfoHeaderJSON.put("MaturClassCode", standInfoHeader.getMaturClassCode());
                        standInfoHeaderJSON.put("MaturClassRationale", standInfoHeader.getMaturClassRationale());
                        n.put("StandInfoHeader", standInfoHeaderJSON);
                    }

                    // Photo Header
                    JSONObject photoHeaderJSON = new JSONObject();
                    PhotoHeader photoHeader = visit.getPhotoHeader();
                    if (photoHeader != null) {
                        photoHeaderJSON.put("PhotoHeaderKey", photoHeader.getPhotoHeaderKey());
                        photoHeaderJSON.put("VisitKey", visit.getVisitKey());
                        photoHeaderJSON.put("MsrDate", photoHeader.getMsrDate());

                        // Photo Feature
                        JSONArray photoFeatures = new JSONArray();
                        for(PhotoFeature photoFeature: photoHeader.getPhotoFeatures()) {
                            JSONObject photoFeatureJSON = new JSONObject();
                            photoFeatureJSON.put("PhotoFeatureKey", photoFeature.getPhotoFeatureKey());
                            photoFeatureJSON.put("PhotoHeaderKey", photoHeader.getPhotoHeaderKey());
                            photoFeatureJSON.put("Description", photoFeature.getDescription());
                            photoFeatureJSON.put("FrameNum", photoFeature.getFrameNum());
                            photoFeatures.add(photoFeatureJSON);
                        }
                        photoHeaderJSON.put("PhotoFeature", photoFeatures);

                        // Photo Required
                        JSONArray photoRequireds = new JSONArray();
                        for(PhotoRequired photoRequired: photoHeader.getPhotoRequireds()) {
                            JSONObject photoRequiredJSON = new JSONObject();
                            photoRequiredJSON.put("PhotoRequiredKey", photoRequired.getPhotoRequiredKey());
                            photoRequiredJSON.put("PhotoHeaderKey", photoHeader.getPhotoHeaderKey());
                            photoRequiredJSON.put("PhotoTypeCode", photoRequired.getPhotoTypeCode());
                            photoRequiredJSON.put("FrameNum", photoRequired.getFrameNum());
                            photoRequireds.add(photoRequiredJSON);
                        }
                        photoHeaderJSON.put("PhotoRequired", photoRequireds);
                        n.put("PhotoHeader", photoHeaderJSON);
                    }

                    // Vegetation Header
                    JSONObject vegetationHeaderJSON = new JSONObject();
                    VegHeader vegetationHeader = visit.getVegHeader();
                    if (vegetationHeader != null) {
                        vegetationHeaderJSON.put("VegHeaderKey", vegetationHeader.getVegHeaderKey());
                        vegetationHeaderJSON.put("VisitKey", visit.getVisitKey());
                        vegetationHeaderJSON.put("MsrDate", vegetationHeader.getMsrDate());

                        // Veg V Type
                        JSONArray vegVTypes = new JSONArray();
                        for(VegVType vegVType: vegetationHeader.getVegVTypes()) {
                            JSONObject vegVTypeJSON = new JSONObject();
                            vegVTypeJSON.put("VegVTypeKey", vegVType.getVegVTypeKey());
                            vegVTypeJSON.put("VegHeaderKey", vegetationHeader.getVegHeaderKey());
                            vegVTypeJSON.put("GrowthPlotNum", vegVType.getGrowthPlotNum());
                            vegVTypeJSON.put("VegTypeCode", vegVType.getVegTypeCode());
                            vegVTypeJSON.put("ELCMethodCode", vegVType.getElcMethodCode());
                            vegVTypes.add(vegVTypeJSON);
                        }
                        vegetationHeaderJSON.put("VegVType", vegVTypes);

                        // Veg Plot
                        JSONArray vegPlots = new JSONArray();
                        for(VegPlot vegPlot: vegetationHeader.getVegPlots()) {
                            JSONObject vegPlotJSON = new JSONObject();
                            vegPlotJSON.put("VegPlotKey", vegPlot.getVegPlotKey());
                            vegPlotJSON.put("VegHeaderKey", vegetationHeader.getVegHeaderKey());
                            vegPlotJSON.put("GrowthPlotNum", vegPlot.getGrowthPlotNum());
                            vegPlotJSON.put("VegPlotNum", vegPlot.getVegPlotNum());
                            vegPlotJSON.put("ShrubPlotNum", vegPlot.getShrubPlotNum());
                            vegPlotJSON.put("RegenPlotNum", vegPlot.getRegenPlotNum());
                            vegPlotJSON.put("QuadCode", vegPlot.getQuadCode());
                            vegPlotJSON.put("LineNum", vegPlot.getLineNum());
                            vegPlotJSON.put("Azi", vegPlot.getAzi());
                            vegPlotJSON.put("Dist", vegPlot.getDist());

                            // Veg Cover
                            JSONArray vegCoverList = new JSONArray();
                            for(VegCover vegCover: vegPlot.getVegCovers()) {
                                JSONObject vegCoverJSON = new JSONObject();
                                vegCoverJSON.put("VegCoverKey", vegCover.getVegCoverKey());
                                vegCoverJSON.put("VegPlotKey", vegPlot.getVegPlotKey());
                                vegCoverJSON.put("VegGroupCode", vegCover.getVegGroupCode());
                                vegCoverJSON.put("CoverClass1To5Code", vegCover.getCoverClass1To5Code());
                                vegCoverJSON.put("CoverClass0To5Code", vegCover.getCoverClass0To5Code());
                                vegCoverJSON.put("CoverPct", vegCover.getCoverPct());
                                vegCoverList.add(vegCoverJSON);
                            }
                            vegPlotJSON.put("VegCover", vegCoverList);

                            // Veg Regen
                            JSONArray vegRegenList = new JSONArray();
                            for(VegRegen vegRegen: vegPlot.getVegRegens()) {
                                JSONObject vegRegenJSON = new JSONObject();
                                vegRegenJSON.put("VegRegenKey", vegRegen.getVegRegenKey());
                                vegRegenJSON.put("VegPlotKey", vegPlot.getVegPlotKey());
                                vegRegenJSON.put("RegenTypeCode", vegRegen.getRegenTypeCode());
                                vegRegenJSON.put("SpecCode", vegRegen.getSpecCode());
                                vegRegenJSON.put("RegenCount", vegRegen.getRegenCount());
                                vegRegenJSON.put("CoverPct", vegRegen.getCoverPct());
                                vegRegenList.add(vegRegenJSON);
                            }
                            vegPlotJSON.put("VegRegen", vegRegenList);

                            // Veg Spec Pres
                            JSONArray vegSpecPresList = new JSONArray();
                            for(VegSpecPres vegSpecPres: vegPlot.getVegSpecPres()) {
                                JSONObject vegSpecPresJSON = new JSONObject();
                                vegSpecPresJSON.put("VegSpecPresKey", vegSpecPres.getVegSpecPresKey());
                                vegSpecPresJSON.put("VegPlotKey", vegPlot.getVegPlotKey());
                                vegSpecPresJSON.put("SpecCode", vegSpecPres.getSpecCode());
                                vegSpecPresList.add(vegSpecPresJSON);
                            }
                            vegPlotJSON.put("VegSpecPres", vegSpecPresList);

                            // Veg Shrub Spec
                            JSONArray vegShrubSpecList = new JSONArray();
                            for(VegShrubSpec vegShrubSpec: vegPlot.getVegShrubSpecs()) {
                                JSONObject vegShrubSpecJSON = new JSONObject();
                                vegShrubSpecJSON.put("VegSpecPresKey", vegShrubSpec.getVegShrubSpecKey());
                                vegShrubSpecJSON.put("VegPlotKey", vegPlot.getVegPlotKey());
                                vegShrubSpecJSON.put("SpecCode", vegShrubSpec.getSpecCode());
                                vegShrubSpecJSON.put("LayerCode", vegShrubSpec.getLayerCode());
                                vegShrubSpecJSON.put("CoverPct", vegShrubSpec.getCoverPct());
                                vegShrubSpecList.add(vegShrubSpecJSON);
                            }
                            vegPlotJSON.put("VegShrubSpec", vegShrubSpecList);

                            vegPlots.add(vegPlotJSON);
                        }
                        vegetationHeaderJSON.put("VegPlot", vegPlots);
                        n.put("VegetationHeader", vegetationHeaderJSON);
                    }

                    // Tree Header
                    JSONObject treeHeaderJSON = new JSONObject();
                    TreeHeader treeHeader = visit.getTreeHeader();
                    if (treeHeader != null) {
                        treeHeaderJSON.put("TreeHeaderKey", treeHeader.getTreeHeaderKey());
                        treeHeaderJSON.put("VisitKey", visit.getVisitKey());
                        treeHeaderJSON.put("MsrDate", treeHeader.getMsrDate());

                        // Tree Growth Plot
                        JSONArray treeGrowthPlots = new JSONArray();
                        for(TreeGrowthPlot treeGrowthPlot: treeHeader.getTreeGrowthPlot()) {
                            JSONObject treeGrowthPlotJSON = new JSONObject();
                            treeGrowthPlotJSON.put("TreeGrowthPlotKey", treeGrowthPlot.getTreeGrowthPlotKey());
                            treeGrowthPlotJSON.put("TreeHeaderKey", treeHeader.getTreeHeaderKey());
                            treeGrowthPlotJSON.put("GrowthPlotNum", treeGrowthPlot.getGrowthPlotNum());
                            treeGrowthPlotJSON.put("CrownClsr", treeGrowthPlot.getCrownClsr());
                            treeGrowthPlotJSON.put("LocAzi", treeGrowthPlot.getLocAzi());
                            treeGrowthPlotJSON.put("LocDist", treeGrowthPlot.getLocDist());
                            treeGrowthPlotJSON.put("Radius", treeGrowthPlot.getRadius());
                            treeGrowthPlotJSON.put("Width", treeGrowthPlot.getWidth());
                            treeGrowthPlotJSON.put("Length", treeGrowthPlot.getLength());
                            treeGrowthPlotJSON.put("TreeRenumber", treeGrowthPlot.isTreeRenumber());

                            // Tree Msr
                            JSONArray treeMsrs = new JSONArray();
                            for(TreeMsr treeMsr: treeGrowthPlot.getTreeMsrs()) {
                                JSONObject treeMsrJSON = new JSONObject();
                                treeMsrJSON.put("TreeMsrKey", treeMsr.getTreeMsrKey());
                                treeMsrJSON.put("TreeGrowthPlotKey", treeGrowthPlot.getTreeGrowthPlotKey());
                                treeMsrJSON.put("TreeKey", treeMsr.getTreeKey());
                                treeMsrJSON.put("TreeStatusCode", treeMsr.getTreeStatusCode());
                                treeMsrJSON.put("HtToDBH", treeMsr.getHtToDBH());
                                treeMsrJSON.put("DBH", treeMsr.getDbh());
                                treeMsrJSON.put("QualClass2Code", treeMsr.getQualClass2Code());
                                treeMsrJSON.put("QualClass5Code", treeMsr.getQualClass5Code());
                                treeMsrJSON.put("QualClass6Code", treeMsr.getQualClass6Code());
                                treeMsrJSON.put("LiveCrownRatio", treeMsr.getLiveCrownRatio());
                                treeMsrJSON.put("CrownClassCode", treeMsr.getCrownClassCode());
                                treeMsrJSON.put("CrownPosnCode", treeMsr.getCrownPosnCode());
                                treeMsrJSON.put("CrownLight", treeMsr.getCrownLight());
                                treeMsrJSON.put("HtTree", treeMsr.isHtTree());
                                treeMsrJSON.put("HtTreeCircled", treeMsr.isHtTreeCircled());
                                treeMsrJSON.put("DecayClass", treeMsr.getDecayClass());
                                treeMsrJSON.put("OcularLength", treeMsr.getOcularLength());
                                treeMsrJSON.put("BrokenTop", treeMsr.isBrokenTop());
                                treeMsrJSON.put("CrownCondCode", treeMsr.getCrownCondCode());
                                treeMsrJSON.put("BarkRetentionCode", treeMsr.getBarkRetentionCode());
                                treeMsrJSON.put("WoodCondCode", treeMsr.getWoodCondCode());
                                treeMsrJSON.put("PrescripCode", treeMsr.getPrescripCode());
                                treeMsrJSON.put("Backfill", treeMsr.isBackfill());

                                // Tree Missed
                                JSONObject treeMissedJSON = new JSONObject();
                                TreeMissed treeMissed = treeMsr.getTreeMissed();
                                if(treeMissed != null) {
                                    treeMissedJSON.put("TreeMissedKey", treeMissed.getTreeMissedKey());
                                    treeMissedJSON.put("TreeMsrKey", treeMsr.getTreeMsrKey());
                                    treeMissedJSON.put("Missed", treeMissed.isMissed());
                                    treeMsrJSON.put("TreeMissed", treeMissedJSON);
                                }

                                // Tree
                                JSONObject treeJSON = new JSONObject();
                                Tree tree = treeMsr.getTree();
                                if (tree != null) {
                                    treeJSON.put("TreeKey", treeMsr.getTreeKey());
                                    treeJSON.put("PlotMapGrowthPlotKey", tree.getPlotMapGrowthPlotKey());
                                    treeJSON.put("Section", tree.getSection());
                                    treeJSON.put("TreeNum", tree.getTreeNum());
                                    treeJSON.put("TagTypeCode", tree.getTagTypeCode());
                                    treeJSON.put("SpecCode", tree.getSpecCode());
                                    treeJSON.put("TreeOriginCode", tree.getTreeOriginCode());
                                    treeJSON.put("PostNum", tree.getPostNum());
                                    treeJSON.put("Dist", tree.getDist());
                                    treeJSON.put("Azi", tree.getAzi());
                                    treeJSON.put("MortCauseCode", tree.getMortCauseCode());
                                    treeJSON.put("OrigTreeNum", tree.getOrigTreeNum());
                                    treeMsrJSON.put("Tree", treeJSON);
                                }

                                // Deformities
                                JSONArray treeDefms = new JSONArray();
                                for(TreeDefm treeDefm: treeMsr.getTreeDefms()) {
                                    JSONObject treeDefmJSON = new JSONObject();
                                    treeDefmJSON.put("TreeDefmKey", treeDefm.getTreeDefmKey());
                                    treeDefmJSON.put("TreeMsrKey", treeMsr.getTreeMsrKey());
                                    treeDefmJSON.put("DefmTypeCode", treeDefm.getDefmTypeCode());
                                    treeDefmJSON.put("DefmCauseCode", treeDefm.getDefmCauseCode());
                                    treeDefmJSON.put("HtFrom", treeDefm.getHtFrom());
                                    treeDefmJSON.put("HtTo", treeDefm.getHtTo());
                                    treeDefmJSON.put("QuadCode", treeDefm.getQuadCode());
                                    treeDefmJSON.put("Extent", treeDefm.getExtent());
                                    treeDefmJSON.put("DegreeLeanArch", treeDefm.getDegreeLeanArch());
                                    treeDefmJSON.put("Azi", treeDefm.getAzi());
                                    treeDefmJSON.put("Length", treeDefm.getLength());
                                    treeDefmJSON.put("Width", treeDefm.getWidth());
                                    treeDefmJSON.put("Scuff", treeDefm.getScuff());
                                    treeDefmJSON.put("Scrape", treeDefm.getScrape());
                                    treeDefmJSON.put("Gouge", treeDefm.getGouge());
                                    treeDefmJSON.put("NumLive", treeDefm.getNumLive());
                                    treeDefmJSON.put("NumDead", treeDefm.getNumDead());
                                    treeDefms.add(treeDefmJSON);
                                }
                                treeMsrJSON.put("Deformities", treeDefms);

                                // Cavities
                                JSONArray treeCavs = new JSONArray();
                                for(TreeCav treeCav: treeMsr.getTreeCavs()) {
                                    JSONObject treeCavJSON = new JSONObject();
                                    treeCavJSON.put("TreeCavKey", treeCav.getTreeCavKey());
                                    treeCavJSON.put("TreeMsrKey", treeMsr.getTreeMsrKey());
                                    treeCavJSON.put("CavTypeCode", treeCav.getCavTypeCode());
                                    treeCavs.add(treeCavJSON);
                                }
                                treeMsrJSON.put("Cavities", treeCavs);

                                treeMsrs.add(treeMsrJSON);
                            }
                            treeGrowthPlotJSON.put("TreeMsr", treeMsrs);
                            treeGrowthPlots.add(treeGrowthPlotJSON);
                        }
                        treeHeaderJSON.put("TreeGrowthPlot", treeGrowthPlots);
                        n.put("TreeHeader", treeHeaderJSON);
                    }

                    visits.add(n);
                }
                visitJSON.put("Visits", visits);


                pkgJSON.put("Visit", visitJSON);

                // NOTE
                JSONObject noteJSON = new JSONObject();
                // NOTES
                JSONArray notes = new JSONArray();
                for (Note note: pkg.getPlot().getNotes()) {
                    JSONObject nJSON = new JSONObject();
                    nJSON.put("NoteKey", note.getNoteKey());
                    nJSON.put("PlotKey", pkg.getPlot().getPlotKey());
                    nJSON.put("NoteTypeCode", note.getNoteTypeCode());
                    nJSON.put("Note", note.getNote());

                    notes.add(nJSON);
                }
                noteJSON.put("Notes", notes);

                // NOTE FIXUPS
                JSONArray noteFixups = new JSONArray();
                for(NoteFixup noteFixup: pkg.getPlot().getNoteFixups()) {
                    JSONObject n = new JSONObject();
                    n.put("NoteFixupKey", noteFixup.getNoteFixupKey());
                    n.put("PlotKey", pkg.getPlot().getPlotKey());
                    n.put("ElementCode", noteFixup.getElementCode());
                    n.put("RecordedYear", noteFixup.getRecordedYear());
                    n.put("Note", noteFixup.getNote());
                    n.put("Fixed", noteFixup.isFixed());

                    noteFixups.add(n);
                }
                noteJSON.put("NoteFixups", noteFixups);

                // NOTE PLOT
                JSONObject notePlotJSON = new JSONObject();
                NotePlot notePlot = pkg.getPlot().getNotePlot();
                notePlotJSON.put("NotePlotKey", notePlot.getNotePlotKey());
                notePlotJSON.put("PlotKey", pkg.getPlot().getPlotKey());
                notePlotJSON.put("PlotStatusCode", notePlot.getPlotStatusCode());
                notePlotJSON.put("PlotStatusExpln", notePlot.getPlotStatusExpln());
                notePlotJSON.put("Declination", notePlot.getDeclination());
                noteJSON.put("NotePlot", notePlotJSON);

                pkgJSON.put("Note", noteJSON);

                // Site Permissions
                JSONObject sitePermissionsJSON = new JSONObject();

                JSONObject sitePermRestJSON = new JSONObject();
                SitePermRest sitePermRest = pkg.getPlot().getSitePermRest();
                if (sitePermRest != null) {
                    sitePermRestJSON.put("SitePermRestKey", sitePermRest.getSitePermRestKey());
                    sitePermRestJSON.put("PlotKey", pkg.getPlot().getPlotKey());
                    sitePermRestJSON.put("RestTypeCode", sitePermRest.getRestTypeCode());
                    sitePermRestJSON.put("RestDetail", sitePermRest.getRestDetail());
                    sitePermissionsJSON.put("SitePermRest", sitePermRestJSON);
                }


                JSONObject sitePermPlotJSON = new JSONObject();
                SitePermPlot sitePermPlot = pkg.getPlot().getSitePermPlot();
                if (sitePermPlot != null) {
                    sitePermPlotJSON.put("SitePermPlotKey", sitePermPlot.getSitePermPlotKey());
                    sitePermPlotJSON.put("PlotKey", pkg.getPlotKey());
                    sitePermPlotJSON.put("LandAdminCode", sitePermPlot.getLandAdminCode());
                    sitePermPlotJSON.put("ConsultRequired", sitePermPlot.isConsultRequired());
                    sitePermPlotJSON.put("GrantedPerm", sitePermPlot.isGrantedPerm());
                    sitePermPlotJSON.put("PermSignature", sitePermPlot.isPermSignature());
                    sitePermPlotJSON.put("ConsultRequirement", sitePermPlot.getConsultRequirement());
                    sitePermPlotJSON.put("Obligation", sitePermPlot.getObligation());
                    sitePermissionsJSON.put("SitePermPlot", sitePermPlotJSON);
                }

                pkgJSON.put("SitePermissions", sitePermissionsJSON);

                // Location
                JSONObject locationJSON = new JSONObject();

                JSONObject locPlotJSON = new JSONObject();
                LocPlot locPlot = pkg.getPlot().getLocPlot();
                if (locPlot != null) {
                    locPlotJSON.put("LocPlotKey", locPlot.getLocPlotKey());
                    locPlotJSON.put("PlotKey", pkg.getPlotKey());
                    locPlotJSON.put("SpecCode", locPlot.getSpecCode());
                    locPlotJSON.put("PlotShapeCode", locPlot.getPlotShapeCode());
                    locPlotJSON.put("Zone", locPlot.getZone());
                    locPlotJSON.put("LatestLocatorMapYear", locPlot.getLatestLocatorMapYear());
                    locPlotJSON.put("LatestWalkInMapYear", locPlot.getLatestWalkInMapYear());
                    locationJSON.put("LocPlot", locPlotJSON);
                }
                // Plot Access
                JSONArray plotAccessList = new JSONArray();

                for(PlotAccess plotAccess: pkg.getPlot().getPlotAccesses()) {
                    JSONObject n = new JSONObject();
                    n.put("PlotKey", pkg.getPlotKey());
                    n.put("AccessCode", plotAccess.getAccessCode());

                    noteFixups.add(n);
                }
                locationJSON.put("PlotAccess", plotAccessList);

                // Loc Coord
                JSONArray locCoords = new JSONArray();
                for(LocCoord locCoord: pkg.getPlot().getLocCoords()) {
                    JSONObject n = new JSONObject();
                    n.put("LocCoordKey", locCoord.getLocCoordKey());
                    n.put("PlotKey", pkg.getPlotKey());
                    n.put("CoordTypeCode", locCoord.getCoordTypeCode());
                    n.put("PostNum", locCoord.getPostNum());
                    n.put("Easting", locCoord.getEasting());
                    n.put("Northing", locCoord.getNorthing());
                    n.put("Datum", locCoord.getDatum());
                    n.put("GPSClassCode", locCoord.getGpsClassCode());
                    n.put("Accurate", locCoord.isAccurate());

                    locCoords.add(n);
                }
                locationJSON.put("PlotAccess", locCoords);
                pkgJSON.put("Location", locationJSON);

                // Stand Information
                JSONObject standInformationJSON = new JSONObject();
                // Stand Info Plot
                JSONObject standInfoPlotJSON = new JSONObject();
                StandInfoPlot standInfoPlot = pkg.getPlot().getStandInfoPlot();
                if (standInfoPlot != null) {
                    standInfoPlotJSON.put("StandInfoPlotKey", standInfoPlot.getStandInfoPlotKey());
                    standInfoPlotJSON.put("PlotKey", pkg.getPlotKey());
                    standInfoPlotJSON.put("GeneticImproved", standInfoPlot.isGeneticImproved());
                    standInfoPlotJSON.put("SeedGeneration", standInfoPlot.getSeedGeneration());
                    standInfoPlotJSON.put("GeneticWorth", standInfoPlot.getGeneticWorth());
                    standInformationJSON.put("StandInfoPlot", standInfoPlotJSON);
                }

                // Stand Info Distb
                JSONArray standInfoDistbs = new JSONArray();
                for(StandInfoDistb standInfoDistb: pkg.getPlot().getStandInfoDistb()) {
                    JSONObject n = new JSONObject();
                    n.put("StandInfoDistbKey", standInfoDistb.getStandInfoDistbKey());
                    n.put("PlotKey", pkg.getPlotKey());
                    n.put("DistbTypeCode", standInfoDistb.getDistbTypeCode());
                    n.put("DistbYear", standInfoDistb.getDistbYear());
                    n.put("InfoSourceCode", standInfoDistb.getInfoSourceCode());
                    n.put("PctAffected", standInfoDistb.getPctAffected());
                    n.put("PctMort", standInfoDistb.getPctMort());
                    standInfoDistbs.add(n);
                }
                standInformationJSON.put("StandInfoDistb", standInfoDistbs);

                // Stand Info Treat
                JSONArray standInfoTreats = new JSONArray();
                for(StandInfoTreat standInfoTreat: pkg.getPlot().getStandInfoTreat()) {
                    JSONObject n = new JSONObject();
                    n.put("StandInfoTreatKey", standInfoTreat.getStandInfoTreatKey());
                    n.put("PlotKey", pkg.getPlotKey());
                    n.put("TreatTypeCode", standInfoTreat.getTreatTypeCode());
                    n.put("TreatYear", standInfoTreat.getTreatYear());
                    n.put("InfoSourceCode", standInfoTreat.getInfoSourceCode());
                    standInfoTreats.add(n);
                }
                standInformationJSON.put("StandInfoTreat", standInfoTreats);

                // Stand Info Compr
                JSONArray standInfoComprs = new JSONArray();
                for(StandInfoCompr standInfoCompr: pkg.getPlot().getStandInfoCompr()) {
                    JSONObject n = new JSONObject();
                    n.put("StandInfoComprKey", standInfoCompr.getStandInfoComprKey());
                    n.put("PlotKey", pkg.getPlotKey());
                    n.put("ComprTypeCode", standInfoCompr.getComprTypeCode());
                    n.put("ComprYear", standInfoCompr.getComprYear());
                    standInfoComprs.add(n);
                }
                standInformationJSON.put("StandInfoCompr", standInfoComprs);

                pkgJSON.put("StandInformation", standInformationJSON);

                // Create JSON file
                FileWriter file = new FileWriter(path + "/" + pkg.getName() + ".json");
                file.write(pkgJSON.toJSONString());
                file.close();
            }

            // TODO: Once completed, we need to save the location of the exports to easily access them.
            // This should be done at the press of a button, or automatically, upon completion.

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to create new exports directory.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error has occurred. Unable to export plot packages.\n\n"+e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    /** Download Plot Package
     *      This function downloads the provided plot package as a JSON
     *      object, which can then be sent to the field crew to be used
     *      in validation and flagging.
     * @param pkg The plot package which will be downloaded.
     * */
    void downloadPlotPackage(Package pkg) {
        try {
            // TODO: Download all information for the given package.

            int plotKey = pkg.getPlotKey();

            // Notes
            Note[] notes = DatabaseHelper.getNotes(plotKey);
            NoteFixup[] noteFixups = DatabaseHelper.getNoteFixups(plotKey);
            NotePlot notePlot = DatabaseHelper.getNotePlot(plotKey);

            pkg.getPlot().setNotes(notes);
            pkg.getPlot().setNoteFixups(noteFixups);
            pkg.getPlot().setNotePlot(notePlot);

            // Site Permissions
            SitePermRest sitePermRest = DatabaseHelper.getSitePermRest(plotKey);
            SitePermPlot sitePermPlot = DatabaseHelper.getSitePermPlot(plotKey);

            pkg.getPlot().setSitePermRest(sitePermRest);
            pkg.getPlot().setSitePermPlot(sitePermPlot);

            // Location
            LocPlot locPlot = DatabaseHelper.getLocPlot(plotKey);
            PlotAccess[] plotAccessList = DatabaseHelper.getPlotAccessList(plotKey);
            LocCoord[] locCoords = DatabaseHelper.getLocCoords(plotKey);

            pkg.getPlot().setLocPlot(locPlot);
            pkg.getPlot().setPlotAccesses(plotAccessList);
            pkg.getPlot().setLocCoords(locCoords);

            // Visit
            for(Visit visit: pkg.getVisits()) {
                int visitKey = visit.getVisitKey();
                // Stand Information
                visit.setStandInfoHeader(DatabaseHelper.getStandInfoHeader(visitKey));
                // Photo Header & Photo Required/Feature Lists
                visit.setPhotoHeader(DatabaseHelper.getPhotoHeader(visitKey));
                // Vegetation
                visit.setVegHeader(DatabaseHelper.getVegHeader(visitKey));
                // Tree
                visit.setTreeHeader(DatabaseHelper.getTreeHeader(visitKey));
                // Height
                visit.setHtHeader(DatabaseHelper.getHtHeader(visitKey));

                // TODO Plot Mapping

                // Down Woody Debris
                visit.setDWDHeader(DatabaseHelper.getDWDHeader(visitKey));

                // TODO Stocking

                // TODO Mortality

                // TODO Age

                // TODO Soil Site Temporal

                // TODO Self QA
            }

            // Stand Information
            pkg.getPlot().setStandInfoPlot(DatabaseHelper.getStandInfoPlot(plotKey));
            pkg.getPlot().setStandInfoDistb(DatabaseHelper.getStandInfoDistb(plotKey));
            pkg.getPlot().setStandInfoTreat(DatabaseHelper.getStandInfoTreat(plotKey));
            pkg.getPlot().setStandInfoCompr(DatabaseHelper.getStandInfoCompr(plotKey));

            // TODO Soil Sample -> Soil Horizon

            // TODO Soil Site Macro Meso Micro

            // TODO Specialist (Package and Plot)



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Updating info for package " + pkg.getName());
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to display package data.");
            alert.showAndWait();
        }
    }

    @FXML
    /** Add Plot Package To Export List
     *      This function adds the selected plot package in the completed list to the
     *      export list.
     * @param event The onClick event when the button is clicked.
     * */
    void addPackageToExportList(ActionEvent event) {
        // Note that the user can only select one plot package at a time
        // so multiple selections is not a case that needs to be covered.
        try {
            // Get the selected plot package from the Completed list.
            Package selected = listCompleted.getSelectionModel().getSelectedItem();

            // Check that the selection is not null.
            if (selected != null) {
                // Check if the plot package is already in the Exports list.
                if (!listExports.getItems().contains(selected))
                    // If it's not in the list, add it.
                    listExports.getItems().add(selected);
                else {
                    // Otherwise notify the user that it is already in the Exports list.
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("This plot package is already in the exports list.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to add plot package to export list.");
            alert.showAndWait();
        }
    }

    @FXML
    /** Remove Plot Package From Export List
     *      This function removes the selected plot package from the exports list.
     * @param event The onClick event when the button is clicked.
     * */
    void removePackageFromExportList(ActionEvent event) {
        // Note that the user can only select one plot package at a time
        // so multiple selections is not a case that needs to be covered.
        try {
            // Get the selected plot package from the Exports list.
            Package selected = listExports.getSelectionModel().getSelectedItem();
            // Remove it from the list.
            listExports.getItems().remove(selected);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to remove plot package from the export list.");
            alert.showAndWait();
        }
    }
}
