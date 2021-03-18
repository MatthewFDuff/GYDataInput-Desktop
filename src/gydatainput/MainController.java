package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.age.AgeHeader;
import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.downwoodydebris.DWDHeader;
import gydatainput.models.downwoodydebris.DWDLine;
import gydatainput.models.height.HtHeader;
import gydatainput.models.location.LocCoord;
import gydatainput.models.location.LocPlot;
import gydatainput.models.location.PlotAccess;
import gydatainput.models.mortality.MortHeader;
import gydatainput.models.note.Note;
import gydatainput.models.note.NoteFixup;
import gydatainput.models.note.NotePlot;
import gydatainput.models.photo.PhotoFeature;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.photo.PhotoRequired;
import gydatainput.models.plotmapping.PlotMapGrowthPlot;
import gydatainput.models.plotmapping.PlotMapHeader;
import gydatainput.models.plotmapping.PlotMapMort;
import gydatainput.models.plotpackage.Package;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.selfqa.SelfQAHeader;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.soilsitetemporal.SoilEcositeHeader;
import gydatainput.models.soilsitetemporal.SoilHeader;
import gydatainput.models.standinformation.*;
import gydatainput.models.stocking.StkgHeader;
import gydatainput.models.tree.*;
import gydatainput.models.vegetation.*;
import gydatainput.ui.exportplotpackage.ExportPlotPackageController;
import gydatainput.ui.importplotpackage.ImportPlotPackageController;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
    @FXML ListView<Package> listImports;
    @FXML ListView<Package> listExports;
    @FXML Button btnImportPackage;
    @FXML Button btnDownloadPackage;
    @FXML TextField txtFilterCompleted;
    @FXML Button btnOpenFileExportsFolder;

    // A list of all plot packages that are currently in the database.
    public ObservableList<Package> packages = FXCollections.observableArrayList();
    // A list of all plot packages that have not been submitted to the database.
    public ObservableList<Package> importPackages = FXCollections.observableArrayList();
    // A list of all plot packages that are currently in the export list.
    public ObservableList<Package> exportPackages = FXCollections.observableArrayList();

    // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced in this file)
    DatabaseController database = DatabaseController.getInstance();

    Path exportPath = Paths.get(System.getProperty("user.dir") + "/exports/");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadImportedPackages();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
                if(plotPackage.getPlot().getFields().get("PlotName").toString().toLowerCase().contains(lowerCaseFilter)) {
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
        listImports.setCellFactory(importListView -> new ImportPlotPackageController());

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

    /**
     * Gets the imported plot packages from the file /imports
     */
    @FXML
    private void loadImportedPackages() throws IOException, ParseException {
        listImports.getItems().clear();
        String[] pathnames;
        String importPath = System.getProperty("user.dir") + "/imports";
        File f = new File(importPath);

        pathnames = f.list();

        JSONParser parser = new JSONParser();
        // For each file in the directory..
        for(String pathname : pathnames) {
            // Convert to a JSON object.

            Object obj = parser.parse(new FileReader(importPath + "/" + pathname));
            JSONObject json = (JSONObject) obj;
            Package pkg = new Package(json, true);

            listImports.getItems().add(pkg);
        }
    }

    /**
     * Opens the file with the System default file explorer.
     * @param
     */
    @FXML
    public void openExportFileLocation(ActionEvent actionEvent) throws IOException {
        final String EXPLORER_EXE = "explorer.exe";
        String command = "explorer.exe /select," + exportPath;
        Runtime.getRuntime().exec(command);
    }

    /**
     * Opens the file with the System default file explorer.
     * @param
     */
    @FXML
    public void uploadPlotPackage(ActionEvent actionEvent) throws IOException {
        Package selected = listImports.getSelectionModel().getSelectedItem();

        // Upload from each table.
        JSONObject json = selected.getImportJSON();

        // Upload each table
        if (json.size() > 0) {
            // Package
            JSONObject packageFields = (JSONObject) json.get("fields");
            if (packageFields.size() > 0) {
                DatabaseHelper.uploadTableData("tblPackage", packageFields);
            }

            // Plot
            JSONObject plotJSON = (JSONObject) json.get("Plot");
            if (plotJSON != null && plotJSON.size() > 0 ) {
                JSONObject plotFields = (JSONObject) plotJSON.get("fields");
                DatabaseHelper.uploadTableData("tblPlot", plotFields);

                // Note
                JSONArray notes = (JSONArray) plotJSON.get("Note");
                if (notes  != null && !notes.isEmpty()) {
                    Iterator<JSONObject> noteKeys = notes.iterator();
                    while(noteKeys.hasNext()) {
                        JSONObject noteKey = (JSONObject) noteKeys.next();
                        // TODO Note does not have fields
                        DatabaseHelper.uploadTableData("tblNote", noteKey);
                    }
                }

                // Note Fixup
                JSONArray noteFixups = (JSONArray) plotJSON.get("NoteFixup");
                if (noteFixups != null && !noteFixups.isEmpty()) {
                    Iterator<JSONObject> noteFixupKeys = noteFixups.iterator();
                    while(noteFixupKeys.hasNext()) {
                        JSONObject noteFixupKey = (JSONObject) noteFixupKeys.next();
                        JSONObject noteFixupFields = (JSONObject) noteFixupKey.get("fields");
                        DatabaseHelper.uploadTableData("tblNoteFixup", noteFixupFields);
                    }
                }

                // NotePlot
                JSONObject notePlot = (JSONObject) plotJSON.get("NotePlot");
                if (notePlot != null && notePlot.size() > 0) {
                    JSONObject notePlotFields = (JSONObject) notePlot.get("fields");
                    // TODO Note Plot does not have fields
                    DatabaseHelper.uploadTableData("tblNotePlot", notePlot);
                }

                // Site Perm Rest
                JSONObject sitePermRest = (JSONObject) plotJSON.get("SitePermRest");
                if (sitePermRest != null && sitePermRest.size() > 0) {
                    JSONObject sitePermRestFields = (JSONObject) sitePermRest.get("fields");
                    // TODO SitePermRest does not have fields
                    DatabaseHelper.uploadTableData("tblNotePlot", sitePermRest);
                }

                // Site Perm Plot
                JSONObject sitePermPlot = (JSONObject) plotJSON.get("SitePermPlot");
                if (sitePermPlot != null && sitePermPlot.size() > 0) {
                    JSONObject sitePermPlotFields = (JSONObject) sitePermPlot.get("fields");
                    // TODO SitePermPlot does not have fields.
                    DatabaseHelper.uploadTableData("tblSitePermPlot", sitePermPlot);
                }

                // Loc Plot
                JSONObject locPlot = (JSONObject) plotJSON.get("LocPlot");
                if (locPlot != null && locPlot.size() > 0) {
                    JSONObject locPlotFields = (JSONObject) locPlot.get("fields");
                    // TODO LocPlot does not have fields.
                    DatabaseHelper.uploadTableData("tblLocPlot", locPlot);
                }

                // Plot Access
                JSONArray plotAccess = (JSONArray) plotJSON.get("PlotAccess");
                if (plotAccess != null && !plotAccess.isEmpty()) {
                    Iterator<JSONObject> plotAccessKeys = plotAccess.iterator();
                    while(plotAccessKeys.hasNext()) {
                        JSONObject plotAccessKey = (JSONObject) plotAccessKeys.next();
                        JSONObject plotAccessFields = (JSONObject) plotAccessKey.get("fields");
                        // TODO PlotAccess does not have fields
                        DatabaseHelper.uploadTableData("tblPlotAccess", plotAccessKey);
                    }
                }

                // Loc Coord
                JSONArray locCoord = (JSONArray) plotJSON.get("LocCoord");
                if (locCoord != null && !locCoord.isEmpty()) {
                    Iterator<JSONObject> locCoordKeys = locCoord.iterator();
                    while(locCoordKeys.hasNext()) {
                        JSONObject locCoordKey = (JSONObject) locCoordKeys.next();
                        JSONObject plotAccessFields = (JSONObject) locCoordKey.get("fields");
                        // TODO LocCoord does not have fields
                        DatabaseHelper.uploadTableData("tblLocCoord", locCoordKey);
                    }
                }

                // Stand Info Plot
                handleUpload("StandInfoPlot", plotJSON);

                // Stand Info Distb
                handleArrayUpload("StandInfoDistb", plotJSON);

                // Stand Info Treat
                handleArrayUpload("StandInfoTreat", plotJSON);

                // Stand Info Compr
                handleArrayUpload("StandInfoCompr", plotJSON);

                // Plot Map Mort
                handleUpload("PlotMapMort", plotJSON);

                // Plot Map Growth Plot
                JSONArray plotMapGrowthPlot = (JSONArray) plotJSON.get("PlotMapGrowthPlot");
                if (plotMapGrowthPlot != null && !plotMapGrowthPlot.isEmpty()) {
                    Iterator<JSONObject> plotMapGrowthPlotKeys = plotMapGrowthPlot.iterator();
                    while(plotMapGrowthPlotKeys.hasNext()) {
                        // Plot Map Growth Plot fields
                        JSONObject plotMapGrowthPlotKey = (JSONObject) plotMapGrowthPlotKeys.next();
                        JSONObject plotMapGrowthPlotFields = (JSONObject) plotMapGrowthPlotKey.get("fields");
                        DatabaseHelper.uploadTableData("tblPlotMapGrowthPlot", plotMapGrowthPlotFields);

                        handleArrayUpload("Tree", plotMapGrowthPlotKey);
                    }
                }

                // Soil Sample
                JSONArray soilSample = (JSONArray) plotJSON.get("SoilSample");
                if (soilSample != null && !soilSample.isEmpty()) {
                    Iterator<JSONObject> soilSampleKeys = soilSample.iterator();
                    while(soilSampleKeys.hasNext()) {
                        // Soil Sample fields
                        JSONObject soilSampleKey = (JSONObject) soilSampleKeys.next();
                        JSONObject soilSampleFields = (JSONObject) soilSampleKey.get("fields");
                        DatabaseHelper.uploadTableData("tblLocCoord", soilSampleFields);

                        // SoilDepMode
                        handleArrayUpload("SoilDepMode", soilSampleKey);
                        // SoilPhoto
                        handleArrayUpload("SoilPhoto", soilSampleKey);
                        // SoilHor
                        handleArrayUpload("SoilHor", soilSampleKey);
                    }
                }

                // Soil Site Macro Meso Micro
                handleUpload("SoilPlot", plotJSON);
                handleArrayUpload("SoilGrowthPlot", plotJSON);

                // Specialist
                handleArrayUpload("SpecAssoc", plotJSON);
            }

            // Visits
            JSONArray visits = (JSONArray) json.get("Visit");
            if (!visits.isEmpty()) {
                Iterator<JSONObject> visitKeys = visits.iterator();
                while(visitKeys.hasNext()) {
                    // Visit
                    JSONObject key = (JSONObject) visitKeys.next();
                    JSONObject visitFields = (JSONObject) key.get("fields");
                    DatabaseHelper.uploadTableData("tblVisit", visitFields);

                    // StandInfoHeader
                    handleUpload("StandInfoHeader", key);
                    // PhotoHeader
                    JSONObject photoHeaderJSON = (JSONObject) key.get("PhotoHeader");
                    if (photoHeaderJSON != null && photoHeaderJSON.size() > 0) {
                        // PhotoHeader fields
                        JSONObject fields = (JSONObject) photoHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblPhotoHeader", fields);

                        // PhotoRequired
                        handleArrayUpload("PhotoRequired", photoHeaderJSON);

                        // PhotoFeature
                        handleArrayUpload("PhotoFeature", photoHeaderJSON);
                    }

                    // VegHeader
                    JSONObject vegHeaderJSON = (JSONObject) key.get("VegHeader");
                    if (vegHeaderJSON != null && vegHeaderJSON.size() > 0) {
                        // VegHeader fields
                        JSONObject fields = (JSONObject) vegHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblVegHeader", fields);

                        // VegVType
                        handleArrayUpload("VegVType", vegHeaderJSON);

                        // VegPlot
                        JSONArray vegPlot = (JSONArray) vegHeaderJSON.get("VegPlot");
                        if (vegPlot != null && !vegPlot.isEmpty()) {
                            Iterator<JSONObject> vegPlotKeys = vegPlot.iterator();
                            while(vegPlotKeys.hasNext()) {
                                // VegPlot fields
                                JSONObject vegPlotKey = (JSONObject) vegPlotKeys.next();
                                JSONObject vegPlotFields = (JSONObject) vegPlotKey.get("fields");
                                DatabaseHelper.uploadTableData("tblVegPlot", vegPlotFields);

                                // VegCover
                                handleArrayUpload("VegCover", vegPlotKey);

                                // VegRegen
                                handleArrayUpload("VegRegen", vegPlotKey);

                                // VegSpecPres
                                handleArrayUpload("VegSpecPres", vegPlotKey);

                                // VegShrubPres
                                handleArrayUpload("VegShrubPres", vegPlotKey);
                            }
                        }
                    }

                    // TreeHeader
                    JSONObject treeHeaderJSON = (JSONObject) key.get("TreeHeader");
                    if (treeHeaderJSON != null && treeHeaderJSON.size() > 0) {
                        // PhotoHeader fields
                        JSONObject fields = (JSONObject) treeHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblTreeHeader", fields);

                        // TreeGrowthPlot
                        JSONArray treeGrowthPlot = (JSONArray) treeHeaderJSON.get("TreeGrowthPlot");
                        if (treeGrowthPlot != null && !treeGrowthPlot.isEmpty()) {
                            Iterator<JSONObject> treeGrowthPlotKeys = treeGrowthPlot.iterator();
                            while(treeGrowthPlotKeys.hasNext()) {
                                // TreeGrowthPlot fields
                                JSONObject treeGrowthPlotKey = (JSONObject) treeGrowthPlotKeys.next();
                                JSONObject treeGrowthPlotFields = (JSONObject) treeGrowthPlotKey.get("fields");
                                DatabaseHelper.uploadTableData("tblTreeGrowthPlot", treeGrowthPlotFields);

                                // TreeMsr
                                JSONArray treeMsr = (JSONArray) treeHeaderJSON.get("TreeMsr");
                                if (treeMsr != null && !treeMsr.isEmpty()) {
                                    Iterator<JSONObject> treeMsrKeys = treeMsr.iterator();
                                    while(treeMsrKeys.hasNext()) {
                                        // TreeMsr fields
                                        JSONObject treeMsrKey = (JSONObject) treeMsrKeys.next();
                                        JSONObject treeMsrFields = (JSONObject) treeMsrKey.get("fields");
                                        DatabaseHelper.uploadTableData("tblTreeMsr", treeMsrFields);

                                        // TreeMissed
                                        handleUpload("TreeMissed", treeMsrKey);

                                        // TreeDefm
                                        handleArrayUpload("TreeDefm", treeMsrKey);

                                        // TreeCav
                                        handleArrayUpload("TreeCav", treeMsrKey);

                                        // Ht
                                        handleUpload("Ht", treeMsrKey);
                                    }
                                }
                            }
                        }
                    }

                    // HtHeader
                    JSONObject htHeaderJSON = (JSONObject) key.get("HtHeader");
                    if (htHeaderJSON != null && htHeaderJSON.size() > 0) {
                        // PlotMapHeader fields
                        JSONObject fields = (JSONObject) htHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblHtHeader", fields);

                        // Ht
                        handleArrayUpload("Ht", htHeaderJSON);
                    }

                    // DWDHeader
                    JSONObject dwdHeaderJSON = (JSONObject) key.get("DWDHeader");
                    if (dwdHeaderJSON != null && dwdHeaderJSON.size() > 0) {
                        // DWDHeader fields
                        JSONObject fields = (JSONObject) dwdHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblDWDHeader", fields);

                        // DWDLine
                        JSONArray dwdLine = (JSONArray) dwdHeaderJSON.get("DWDLine");
                        if (dwdLine != null && !dwdLine.isEmpty()) {
                            Iterator<JSONObject> dwdLineKeys = dwdLine.iterator();
                            while(dwdLineKeys.hasNext()) {
                                // DWDLine fields
                                JSONObject dwdLineKey = (JSONObject) dwdLineKeys.next();
                                JSONObject dwdLineFields = (JSONObject) dwdLineKey.get("fields");
                                DatabaseHelper.uploadTableData("tblDWDLine", dwdLineFields);

                                // DWDIntersect
                                handleArrayUpload("DWDIntersect", dwdLineKey);

                                // DWDStump
                                handleArrayUpload("DWDStump", dwdLineKey);

                                // DWDAccum
                                handleArrayUpload("DWDAccum", dwdLineKey);
                            }
                        }

                        // DWD
                        JSONArray dwd = (JSONArray) treeHeaderJSON.get("DWD");
                        if (dwd != null && !dwd.isEmpty()) {
                            Iterator<JSONObject> dwdKeys = dwd.iterator();
                            while(dwdKeys.hasNext()) {
                                // DWD fields
                                JSONObject dwdKey = (JSONObject) dwdKeys.next();
                                JSONObject dwdFields = (JSONObject) dwdKey.get("fields");
                                DatabaseHelper.uploadTableData("tblDWD", dwdFields);

                                // DWDIntersect
                                handleArrayUpload("DWDIntersect", dwdKey);
                            }
                        }
                    }

                    // PlotMapHeader
                    handleUpload("PlotMapHeader", key);

                    // StkgHeader
                    JSONObject stkgHeaderJSON = (JSONObject) key.get("StkgHeader");
                    if (stkgHeaderJSON != null && stkgHeaderJSON.size() > 0) {
                        // StkgHeader fields
                        JSONObject fields = (JSONObject) stkgHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblStkgHeader", fields);

                        // StkgLine
                        JSONArray stkgLine = (JSONArray) treeHeaderJSON.get("StkgLine");
                        if (stkgLine != null && !stkgLine.isEmpty()) {
                            Iterator<JSONObject> stkgLineKeys = stkgLine.iterator();
                            while(stkgLineKeys.hasNext()) {
                                // StkgLine fields
                                JSONObject stkgLineKey = (JSONObject) stkgLineKeys.next();
                                JSONObject stkgLineFields = (JSONObject) stkgLineKey.get("fields");
                                DatabaseHelper.uploadTableData("tblStkgLine", stkgLineFields);

                                // Stkg
                                handleArrayUpload("Stkg", stkgLineKey);
                            }
                        }
                    }

                    // MortHeader
                    JSONObject mortHeaderJSON = (JSONObject) key.get("MortHeader");
                    if (mortHeaderJSON != null && mortHeaderJSON.size() > 0) {
                        // MortHeader fields
                        JSONObject fields = (JSONObject) mortHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblMortHeader", fields);

                        // MortTreeMsr
                        JSONArray mortTreeMsr = (JSONArray) mortHeaderJSON.get("MortTreeMsr");
                        if (mortTreeMsr != null && !mortTreeMsr.isEmpty()) {
                            Iterator<JSONObject> mortTreeMsrKeys = mortTreeMsr.iterator();
                            while(mortTreeMsrKeys.hasNext()) {
                                // MortTreeMsr fields
                                JSONObject mortTreeMsrKey = (JSONObject) mortTreeMsrKeys.next();
                                JSONObject mortTreeMsrFields = (JSONObject) mortTreeMsrKey.get("fields");
                                DatabaseHelper.uploadTableData("tblMortTreeMsr", mortTreeMsrFields);

                                // MortTree
                                handleArrayUpload("MortTree", mortTreeMsrKey);
                            }
                        }
                    }

                    // AgeHeader
                    JSONObject ageHeaderJSON = (JSONObject) key.get("AgeHeader");
                    if (ageHeaderJSON != null && ageHeaderJSON.size() > 0) {
                        // AgeHeader fields
                        JSONObject fields = (JSONObject) ageHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblAgeHeader", fields);

                        // AgeTree
                        JSONArray ageTree = (JSONArray) ageHeaderJSON.get("AgeTree");
                        if (ageTree != null && !ageTree.isEmpty()) {
                            Iterator<JSONObject> ageTreeKeys = ageTree.iterator();
                            while(ageTreeKeys.hasNext()) {
                                // AgeTree fields
                                JSONObject ageTreeKey = (JSONObject) ageTreeKeys.next();
                                JSONObject ageTreeFields = (JSONObject) ageTreeKey.get("fields");
                                DatabaseHelper.uploadTableData("tblAgeTree", ageTreeFields);

                                // AgeSample
                                handleArrayUpload("AgeSample", ageTreeKey);
                            }
                        }
                    }

                    // SoilEcositeHeader
                    JSONObject soilEcositeHeaderJSON = (JSONObject) key.get("SoilEcositeHeader");
                    if (soilEcositeHeaderJSON != null && soilEcositeHeaderJSON.size() > 0) {
                        // SoilEcositeHeader fields
                        JSONObject fields = (JSONObject) soilEcositeHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblSoilEcositeHeader", fields);

                        // SoilEcosite
                        handleArrayUpload("SoilEcosite", soilEcositeHeaderJSON);
                    }

                    // SoilHeader
                    JSONObject soilHeaderJSON = (JSONObject) key.get("SoilHeader");
                    if (soilHeaderJSON != null && soilHeaderJSON.size() > 0) {
                        // SoilHeader fields
                        JSONObject fields = (JSONObject) soilHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblSoilHeader", fields);

                        // SoilForestFloor
                        handleArrayUpload("SoilForestFloor", soilHeaderJSON);

                        // SoilGroundCover
                        handleArrayUpload("SoilGroundCover", soilHeaderJSON);
                    }

                    // SelfQAHeader
                    JSONObject selfQAHeaderJSON = (JSONObject) key.get("SelfQAHeader");
                    if (selfQAHeaderJSON != null && selfQAHeaderJSON.size() > 0) {
                        // SelfQAHeader fields
                        JSONObject fields = (JSONObject) selfQAHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblSelfQAHeader", fields);

                        // SelfQAHt
                        handleArrayUpload("SelfQAHt", selfQAHeaderJSON);

                        // SelfQATree
                        JSONArray selfQATree = (JSONArray) ageHeaderJSON.get("SelfQATree");
                        if (selfQATree != null && !selfQATree.isEmpty()) {
                            Iterator<JSONObject> selfQATreeKeys = selfQATree.iterator();
                            while(selfQATreeKeys.hasNext()) {
                                // AgeTree fields
                                JSONObject selfQATreeKey = (JSONObject) selfQATreeKeys.next();
                                JSONObject selfQATreeFields = (JSONObject) selfQATreeKey.get("fields");
                                DatabaseHelper.uploadTableData("tblSelfQATree", selfQATreeFields);

                                // SelfQADeform
                                handleArrayUpload("SelfQADeform", selfQATreeKey);
                            }
                        }
                    }
                }
            }

            // SpecGYHeader
            JSONObject specGYHeaderJSON = (JSONObject) json.get("SpecGYHeader");
            if (specGYHeaderJSON != null && specGYHeaderJSON.size() > 0) {
                JSONObject specGYHeaderFields = (JSONObject) specGYHeaderJSON.get("fields");
                DatabaseHelper.uploadTableData("tblSpecGYHeader", specGYHeaderFields);
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Plot Package (" + selected.getPlot().getFields().get("PlotName") + ") Uploaded Successfully");
        alert.showAndWait();
    }

    public void handleUpload(String name, JSONObject refJSON) {
        JSONObject json = (JSONObject) refJSON.get(name);
        if (json != null && json.size() > 0) {
            JSONObject fields = (JSONObject) json.get("fields");
            DatabaseHelper.uploadTableData("tbl" + name, json);
        }
    }

    public void handleArrayUpload(String name, JSONObject refJSON) {
        JSONArray json = (JSONArray) refJSON.get(name);
        if (json != null && !json.isEmpty()) {
            Iterator<JSONObject> keys = json.iterator();
            while(keys.hasNext()) {
                JSONObject key = (JSONObject) keys.next();
                JSONObject fields = (JSONObject) key.get("fields");
                // If fields is not null, then the object has children.

                DatabaseHelper.uploadTableData("tbl"+ name, key);
            }
        }
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
            // Create a new timestamped folder for the exported packages.
            Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
            exportPath = path;
            Files.createDirectory(path);

            // Iterate through each package in the exports list.
            for (Package pkg: listExports.getItems()) {
                // Retrieve all data for the plot package from the database
                pkg.loadPackage();

                JSONObject pkgJSON = pkg.getJSON();

                // Create JSON file
                FileWriter file = new FileWriter(path + "/" + pkg.getPlot().getFields().get("PlotName") + ".json");
                file.write(pkgJSON.toJSONString());
                file.close();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("All Plot Packages exported successfully.");
            alert.showAndWait();

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Updating info for package " + pkg.getPlot().getFields().get("PlotName"));
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
