package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.plotpackage.Package;
import gydatainput.ui.exportplotpackage.ExportPlotPackageController;
import gydatainput.ui.importplotpackage.ImportPlotPackageController;
import gydatainput.ui.plotpackage.PlotPackageController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;
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
    @FXML Button btnUploadSelected;

    @FXML Button btnDownloadPackage;
    @FXML Button btnAddPackageToExport;
    @FXML Button btnRemovePackageFromExport;

    @FXML TextField txtFilterCompleted;
    @FXML TextField txtFilterImported;
    @FXML Button btnOpenFileExportsFolder;
    @FXML ProgressBar progressBar;
    @FXML Label lblProgress;
    @FXML ProgressBar progressBarImport;
    @FXML Label lblProgressImport;

    // A list of all plot packages that are currently in the database.
    public ObservableList<Package> packages = FXCollections.observableArrayList();
    // A list of all plot packages that have not been submitted to the database.
    public ObservableList<Package> importPackages = FXCollections.observableArrayList();
    // A list of all plot packages that are currently in the export list.
    public ObservableList<Package> exportPackages = FXCollections.observableArrayList();

    // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced in this file)
    DatabaseController database = DatabaseController.getInstance();

    Path exportPath = Paths.get(System.getProperty("user.dir") + "/exports/");
    Path importPath = Paths.get(System.getProperty("user.dir") + "/imports/");

    static DoubleProperty progressUpdater = new SimpleDoubleProperty(0.0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bind the progress bar to a value
        progressBar.progressProperty().bind(progressUpdater);
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
        FilteredList<Package> filteredImportPackages = new FilteredList<>(importPackages);

        // Update the package list each time the filter TextField is updated by the user.
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

                if(plotPackage.getPlot().getFromFields("PlotName").toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // This item will appear in the list.
                }

                return false; // This item will not appear in the list.
            });
        });

        // Do the same with the import package list.
        txtFilterImported.textProperty().addListener((observable, oldValue, newValue) -> {
            // The predicate is what allows us to filter the list dynamically.
            filteredImportPackages.setPredicate(plotPackage -> {
                // If the filter field is empty, show all packages.
                if(newValue == null || newValue.isEmpty()) {
                    return true; // All items will appear in the list.
                }

                // Compare the filter with the name of the plot package (both lowercase).
                String lowerCaseFilter = newValue.toLowerCase();

                if(plotPackage.getPlot().getFromFields("PlotName").toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // This item will appear in the list.
                }

                return false; // This item will not appear in the list.
            });
        });

        // Update the list of completed packages.
        listCompleted.setItems(filteredPackages);
//        listImports.setItems(filteredImportPackages);

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
        if (pathnames != null && pathnames.length > 0) {
            for(String pathname : pathnames) {
                // Convert to a JSON object.

                Object obj = parser.parse(new FileReader(importPath + "/" + pathname));
                JSONObject json = (JSONObject) obj;
                Package pkg = new Package(json, true);

                listImports.getItems().add(pkg);
                importPackages.add(pkg);
            }
        }
    }

    /**
     * Opens the file with the System default file explorer.
     * @param
     */
    @FXML
    public void openExportFileLocation(ActionEvent actionEvent) throws IOException {
//        final String EXPLORER_EXE = "explorer.exe";
//        String command = "explorer.exe /select," + exportPath;
//        Runtime.getRuntime().exec(command);

        Desktop.getDesktop().open(new File(exportPath.toString()));
    }

    @FXML
    public void openImportFileLocation(ActionEvent actionEvent) throws IOException {
        Desktop.getDesktop().open(new File(importPath.toString()));
    }

    Service uploadService = new Service() {
        @Override
        protected Task createTask() {
            Task uploadTask = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    updateProgress(0, 100);
                    updateMessage("Uploading plot package...");
                    // Get the selected plot package
                    Package selected = listImports.getSelectionModel().getSelectedItem();

                    // The list element contains the plot package.
                    // So we just need to call upload from that instance and it will recursively upload all data.

                    // First we start a transaction.

                    // Then we call the upload method within the selected Package.
                    selected.upload();

                    // Then if everything is successful, we commit the changes of the transaction.

                    // Upload is complete.
                    updateProgress(100, 100);
                    updateMessage("Done");
                    return null;
                }
            };

            uploadTask.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
                if(newValue != null) {
                    Exception ex = (Exception) newValue;
                    ex.printStackTrace();
                }
            });

            uploadTask.setOnSucceeded((e) -> {
                // Commit changes.

                // Enable UI buttons
                btnUploadSelected.disableProperty().set(false);

                // Create alert for user.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Plot Package uploaded successfully");
                alert.showAndWait();
            });

            uploadTask.setOnFailed((e) -> {
                btnUploadSelected.disableProperty().set(false);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Upload Failed");
                alert.setContentText("Plot Package could not be uploaded." );
                alert.showAndWait();
            });

            progressBarImport.progressProperty().bind(uploadTask.progressProperty());
            lblProgressImport.textProperty().bind(uploadTask.messageProperty());

            return uploadTask;
        }
    };

    @FXML
    public void uploadPlotPackage(ActionEvent event) {
        btnUploadSelected.disableProperty().set(true);

        // Start the upload service
        if (uploadService.isRunning()) {
            uploadService.reset();
            uploadService.start();
        } else {
            uploadService.restart();
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

    Service exportService = new Service() {

        @Override
        protected Task createTask() {
            Task exportTask = new Task() {
                @Override
                protected Void call() throws Exception {
                    try {
                        // TODO check if the list is empty, and say no exports occurred rather than "success"
                        // Create a new timestamped folder for the exported packages.
                        Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
                        exportPath = path;
                        Files.createDirectory(path);

                        // Progress
                        System.out.println(listExports.getItems().size());
                        int numOfPackages = listExports.getItems().size();
                        // reset the progress bar and set text.
                        updateProgress(0, numOfPackages);
                        updateMessage("Exporting Plot Packages...");

                        int currentPackage = 0;
                        // Iterate through each package in the exports list.
                        for (Package pkg: listExports.getItems()) {
                            currentPackage++;
                            // Retrieve all data for the plot package from the database
                            pkg.loadPackage();

                            // Update the progress bar.
                            updateMessage("Generating " + pkg.getPlot().getFromFields("PlotName") + "...");
                            updateProgress(currentPackage- 0.5, numOfPackages);

                            JSONObject pkgJSON = pkg.getJSON();

                            updateMessage("Exporting " + pkg.getPlot().getFromFields("PlotName") + "...");

                            // Create JSON file
                            FileWriter file = new FileWriter(path + "/" + pkg.getPlot().getFromFields("PlotName") + ".json");
                            file.write(pkgJSON.toJSONString());
                            file.close();

                            // Update the progress bar.
                            updateProgress(currentPackage, numOfPackages);
                        }
                        updateMessage("Done");
                    } catch (IOException e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Unable to create new exports directory.");
//                alert.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("An unexpected error has occurred. Unable to export plot packages.\n\n"+e.getMessage());
//                alert.showAndWait();
                    }
                    return null;
                }
            };

            exportTask.setOnSucceeded((event -> {
                // Clear the list
                listExports.getItems().clear();

                btnDownloadPackage.disableProperty().set(false);
                btnAddPackageToExport.disableProperty().set(false);
                btnRemovePackageFromExport.disableProperty().set(false);

                // Notify the user of completion.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Plot Packages exported successfully.");
                alert.showAndWait();

                exportService.reset();
            }));

            progressBar.progressProperty().bind(exportTask.progressProperty());
            lblProgress.textProperty().bind(exportTask.messageProperty());

            return exportTask;
        }
    };

    @FXML
    void exportPlotPackages(ActionEvent e) {
        // If the user has not added a plot package to the export list, do nothing.
        if (listExports.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No Packages to Export");
            alert.setContentText("Please add a plot package to the export list and try again.");
            alert.showAndWait();
            return;
        }

        btnDownloadPackage.disableProperty().set(true);
        btnAddPackageToExport.disableProperty().set(true);
        btnRemovePackageFromExport.disableProperty().set(true);

        // Start the export service
        if (exportService.isRunning()) {
            exportService.reset();
            exportService.start();
        } else {
            exportService.restart();
        }
    }

    @FXML
    /** Export Plot Packages
     *      This function will export all plot packages from the exports list and
     *      create individual JSON files for each of them in a contained folder.
     * @param
     */
    void exportPlotPackagesOLD(ActionEvent event) {
        try {
            // TODO check if the list is empty, and say no exports occurred rather than "success"
            // Create a new timestamped folder for the exported packages.
            Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
            exportPath = path;
            Files.createDirectory(path);

            // Progress
            System.out.println(listExports.getItems().size());
            int numOfPackages = listExports.getItems().size();
            double progressPerPackage = (1/numOfPackages) / 2;
            // reset the progress bar and set text.
            progressUpdater.set(0);
            // TODO updateLabel(lblProgress, "Exporting Plot Packages");

            // Iterate through each package in the exports list.
            for (Package pkg: listExports.getItems()) {

                lblProgress.setText("Generating " + pkg.getPlot().getFromFields("PlotName"));

                // Retrieve all data for the plot package from the database
                pkg.loadPackage();
                // Update the progress bar.
                progressUpdater.set(progressBar.progressProperty().doubleValue() + progressPerPackage);

                JSONObject pkgJSON = pkg.getJSON();

                lblProgress.setText("Exporting " + pkg.getPlot().getFromFields("PlotName"));

                // Create JSON file
                FileWriter file = new FileWriter(path + "/" + pkg.getPlot().getFromFields("PlotName") + ".json");
                file.write(pkgJSON.toJSONString());
                file.close();

                // Update the progress bar.
                progressUpdater.set(progressBar.progressProperty().doubleValue() + progressPerPackage);
            }
            lblProgress.setText("Done");

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
    void importPlotPackage(ActionEvent event) throws IOException, ParseException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Plot Package");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.json")
        );
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        if (selectedFile != null) {

            // Copy file to imports folder.
            selectedFile.renameTo(new File(importPath + "/" + selectedFile.getName()));
            // Add the new json file to the imports list.
            loadImportedPackages();
        }
    }

    @FXML
    void clearExportList(ActionEvent event) {
        try {
            listExports.getItems().clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error clearing export list.");
            alert.showAndWait();
        }
    }

    @FXML
    public void quitApplication(ActionEvent event) {
        System.exit(0);
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
