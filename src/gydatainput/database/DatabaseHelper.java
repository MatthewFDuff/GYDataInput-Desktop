package gydatainput.database;

import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.downwoodydebris.*;
import gydatainput.models.height.Ht;
import gydatainput.models.height.HtHeader;
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
import gydatainput.models.plotpackage.Plot;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.standinformation.*;
import gydatainput.models.tree.*;
import gydatainput.models.vegetation.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Database Helper class provides the application with all database query functions.
 * The functions are static and can be referenced from other classes, making it easy to
 * perform queries and pass database results to any class that needs it.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-11-28
 */
public class DatabaseHelper {

    /** Load Plot Packages
     *
     *
     * */
    public static boolean loadPlotPackages(ObservableList<Package> packageList) {
        String query = "SELECT DISTINCT * FROM dbo.tblPackage"; // Only one entry per package.
        try {
            ResultSet result = DatabaseController.executeQuery(query);

            while(result.next()) {
                int packageKey = result.getInt("PackageKey");
                int plotKey = result.getInt("PlotKey");
                int startYear = result.getInt("StartYear");
                int approachCode = result.getInt("ApproachCode");
                int coOpMethod = result.getInt("CoOpMethod");

                Package pkg = new Package(packageKey, plotKey, startYear, approachCode, coOpMethod);

                packageList.add(pkg);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // TODO Create query which gets all data for the given plot package.
    // TODO The query will be called for each plot package in the export list.

//    public static ResultSet getPlotNotes(Package pkg) {
//
//        String query = "SELECT * FROM dbo.tblNote WHERE PlotKey = ?";
//        try (PreparedStatement statement = DatabaseController.getConnection().prepareStatement(query)){
//            statement.setString(1, pkg.getPlotKey());
//
//
//        } catch (SQLException e){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Unable to get Plot Notes");
//            alert.showAndWait();
//        }
//    }

    public static boolean getPlotData(Plot plot, int plotKey) {

        try {
            String query = "SELECT * FROM dbo.tblPlot WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            while(results.next()) {
                plot.setPlotKey(plotKey);
                plot.setPlotName(results.getString("PlotName"));
                plot.setDatasetCode(results.getInt("DatasetCode"));
                plot.setPlotAlias(results.getString("PlotAlias"));
                plot.setAdminRegionCode(results.getString("AdminRegionCode"));
                plot.setProtectionNegotiable(results.getInt("ProtectionNegotiable") == 1 ? true : false);
            }

            return true;
        } catch (SQLException e) {
            // TODO
            e.printStackTrace();
            return false;
        }
    }

    public static Visit[] getVisits(int packageKey) {
        try {
            String query = "SELECT COUNT(*) as total FROM dbo.tblVisit WHERE PackageKey = " + packageKey;
            ResultSet visitCount = DatabaseController.executeQuery(query);
            visitCount.next();
            int count = visitCount.getInt("total");

            query = "SELECT * FROM dbo.tblVisit WHERE PackageKey = " + packageKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                Visit[] visits = new Visit[count];

                // Create a Visit object for each visit in the list.
                while(results.next()) {
                    int visitKey = results.getInt("VisitKey");
                    int visitTypeCode = results.getInt("VisitTypeCode");
                    int fieldSeasonYear = results.getInt("FieldSeasonYear");
                    int manualCode = results.getInt("ManualCode");

                    Visit newVisit = new Visit(visitKey, visitTypeCode, fieldSeasonYear, manualCode);
                    visits[results.getRow() - 1] = newVisit;
                }

                return visits;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // TODO
            e.printStackTrace();
            return null;
        }
    }

    public static Note[] getNotes(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblNote WHERE PlotKey = " + plotKey;
            ResultSet noteCount = DatabaseController.executeQuery(query);
            noteCount.next();
            int count = noteCount.getInt("total");

            query = "SELECT * FROM dbo.tblNote WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                Note[] notes = new Note[count];

                while(results.next()) {
                    int noteKey = results.getInt("NoteKey");
                    int noteTypeCode = results.getInt("NoteTypeCode");
                    String note = results.getString("Note");

                    Note newNote = new Note(noteKey, noteTypeCode, note);
                    notes[results.getRow() - 1] = newNote;
                }

                return notes;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static NoteFixup[] getNoteFixups(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblNoteFixup WHERE PlotKey = " + plotKey;
            ResultSet noteFixupCount = DatabaseController.executeQuery(query);
            noteFixupCount.next();
            int count = noteFixupCount.getInt("total");

            query = "SELECT * FROM dbo.tblNoteFixup WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                NoteFixup[] noteFixups = new NoteFixup[count];

                while(results.next()) {
                    int noteFixupKey = results.getInt("NoteFixupKey");
                    int elementCode = results.getInt("ElementCode");
                    int recordedYear = results.getInt("RecordedYear");
                    String note = results.getString("Note");
                    boolean fixed = results.getInt("Fixed") == 1 ? true : false; // Convert bit to bool.

                    NoteFixup newNoteFixup = new NoteFixup(noteFixupKey, elementCode, recordedYear, note, fixed);
                    noteFixups[results.getRow() - 1] = newNoteFixup;
                }

                return noteFixups;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static NotePlot getNotePlot(int plotKey) {
        try {
            String query = "SELECT * FROM dbo.tblNotePlot WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                results.next();

                int notePlotKey = results.getInt("NotePlotKey");
                String plotStatusCode = results.getString("PlotStatusCode");
                String plotStatusExpln = results.getString("PlotStatusExpln");
                String declination = results.getString("Declination");

                NotePlot notePlot = new NotePlot(notePlotKey, plotStatusCode, plotStatusExpln, declination);
                return notePlot;

            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SitePermRest getSitePermRest(int plotKey) {
        try {
            String query = "SELECT * FROM dbo.tblSitePermRest WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {

                int sitePermRestKey = results.getInt("SitePermRestKey");
                int restTypeCode = results.getInt("RestTypeCode");
                String restDetail = results.getString("RestDetail");

                SitePermRest sitePermRest = new SitePermRest(sitePermRestKey, restTypeCode, restDetail);
                return sitePermRest;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SitePermPlot getSitePermPlot(int plotKey) {
        try {
            String query = "SELECT * FROM dbo.tblSitePermPlot WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int sitePermPlotKey = results.getInt("SitePermPlotKey");
                int landAdminCode = results.getInt("LandAdminCode");
                boolean consultRequired = results.getInt("ConsultRequired") == 1 ? true : false;
                boolean grantedPerm = results.getInt("GrantedPerm") == 1 ? true : false;
                boolean permSignature = results.getInt("PermSignature") == 1 ? true : false;
                String consultRequirement = results.getString("ConsultRequirement");
                String obligation = results.getString("Obligation");

                SitePermPlot sitePermPlot = new SitePermPlot(sitePermPlotKey, landAdminCode, consultRequired, grantedPerm, permSignature, consultRequirement, obligation);
                return sitePermPlot;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Loc Plot
    public static LocPlot getLocPlot(int plotKey) {
        try {
            String query = "SELECT * FROM dbo.tblLocPlot WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int locPlotKey = results.getInt("LocPlotKey");
                int specCode = results.getInt("SpecCode");
                String plotShapeCode = results.getString("PlotShapeCode");
                int zone = results.getInt("Zone");
                int latestLocatorMapYear = results.getInt("LatestLocatorMapYear");
                int latestWalkInMapYear = results.getInt("LatestWalkInMapYear");

                LocPlot locPlot = new LocPlot(locPlotKey, specCode, plotShapeCode, zone, latestLocatorMapYear, latestWalkInMapYear);
                return locPlot;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Plot Access List
    public static PlotAccess[] getPlotAccessList(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblPlotAccess WHERE PlotKey = " + plotKey;
            ResultSet plotAccessCount = DatabaseController.executeQuery(query);
            plotAccessCount.next();
            int count = plotAccessCount.getInt("total");

            query = "SELECT * FROM dbo.tblPlotAccess WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                PlotAccess[] plotAccessList = new PlotAccess[count];

                while(results.next()) {
                    int accessCode = results.getInt("AccessCode");

                    PlotAccess plotAccess = new PlotAccess(accessCode);
                    plotAccessList[results.getRow() - 1] = plotAccess;
                }

                return plotAccessList;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Loc Coords
    public static LocCoord[] getLocCoords(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblLocCoord WHERE PlotKey = " + plotKey;
            ResultSet locCoordCount = DatabaseController.executeQuery(query);
            locCoordCount.next();
            int count = locCoordCount.getInt("total");

            query = "SELECT * FROM dbo.tblLocCoord WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                LocCoord[] locCoords = new LocCoord[count];

                while(results.next()) {
                    int locCoordKey = results.getInt("LocCoordKey");
                    int coordTypeCode = results.getInt("CoordTypeCode");
                    int postNum = results.getInt("PostNum");
                    int easting = results.getInt("Easting");
                    int northing = results.getInt("Northing");
                    int datum = results.getInt("Datum");
                    String gpsClassCode = results.getString("GPSClassCode");
                    boolean accurate = results.getInt("Accurate") == 1; // Convert bit to bool.

                    LocCoord newLocCoord = new LocCoord(locCoordKey, coordTypeCode, postNum, easting, northing, datum, gpsClassCode, accurate);
                    locCoords[results.getRow() - 1] = newLocCoord;
                }

                return locCoords;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Stand Info Header
    public static StandInfoHeader getStandInfoHeader(int visitKey) {
        try {
            String query = "SELECT * FROM dbo.tblStandInfoHeader WHERE VisitKey = " + visitKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int standInfoHeaderKey = results.getInt("StandInfoHeaderKey");
                String msrDate = results.getString("MsrDate");
                String canopyStructCode = results.getString("CanopyStructCode");
                int plotUnifCode = results.getInt("PlotUnifCode");
                String plotUnifRationale = results.getString("PlotUnifRationale");
                int mainCanopyOriginCode = results.getInt("MainCanopyOriginCode");
                String maturClassCode = results.getString("MaturClassCode");
                String maturClassRationale = results.getString("MaturClassRationale");

                StandInfoHeader standInfoHeader = new StandInfoHeader(standInfoHeaderKey, msrDate, canopyStructCode, plotUnifCode, plotUnifRationale, mainCanopyOriginCode, maturClassCode, maturClassRationale);
                return standInfoHeader;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Stand Info Plot
    public static StandInfoPlot getStandInfoPlot(int plotKey) {
        try {
            String query = "SELECT * FROM dbo.tblStandInfoPlot WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int standInfoPlotKey = results.getInt("StandInfoPlotKey");
                boolean geneticImproved = results.getInt("GeneticImproved") == 1;
                int seedGeneration = results.getInt("SeedGeneration");
                int geneticWorth = results.getInt("geneticWorth");

                StandInfoPlot standInfoPlot = new StandInfoPlot(standInfoPlotKey, geneticImproved, seedGeneration, geneticWorth);
                return standInfoPlot;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Stand Info Distribution
    public static StandInfoDistb[] getStandInfoDistb(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblStandInfoDistb WHERE PlotKey = " + plotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblStandInfoDistb WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                StandInfoDistb[] standInfoDistbs = new StandInfoDistb[total];

                while(results.next()) {
                    int standInfoDistbKey = results.getInt("StandInfoDistbKey");
                    int distbTypeCode = results.getInt("DistbTypeCode");
                    int distbYear = results.getInt("DistbYear");
                    int infoSourceCode = results.getInt("InfoSourceCode");
                    int pctAffected = results.getInt("PctAffected");
                    int pctMort = results.getInt("PctMort");

                    StandInfoDistb newStandInfoDistb = new StandInfoDistb(standInfoDistbKey, distbTypeCode, distbYear, infoSourceCode, pctAffected, pctMort);
                    standInfoDistbs[results.getRow() - 1] = newStandInfoDistb;
                }

                return standInfoDistbs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Stand Info Treat
    public static StandInfoTreat[] getStandInfoTreat(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblStandInfoTreat WHERE PlotKey = " + plotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblStandInfoTreat WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                StandInfoTreat[] standInfoTreats = new StandInfoTreat[total];

                while(results.next()) {
                    int standInfoTreatKey = results.getInt("StandInfoTreatKey");
                    int treatTypeCode = results.getInt("TreatTypeCode");
                    int treatYear = results.getInt("TreatYear");
                    int infoSourceCode = results.getInt("InfoSourceCode");

                    StandInfoTreat newStandInfoTreat = new StandInfoTreat(standInfoTreatKey, treatTypeCode, treatYear, infoSourceCode);
                    standInfoTreats[results.getRow() - 1] = newStandInfoTreat;
                }

                return standInfoTreats;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Stand Info Compr
    public static StandInfoCompr[] getStandInfoCompr(int plotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblStandInfoCompr WHERE PlotKey = " + plotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblStandInfoCompr WHERE PlotKey = " + plotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                StandInfoCompr[] standInfoComprs = new StandInfoCompr[total];

                while(results.next()) {
                    int standInfoComprKey = results.getInt("StandInfoComprKey");
                    int comprTypeCode = results.getInt("ComprTypeCode");
                    int comprYear = results.getInt("ComprYear");

                    StandInfoCompr newStandInfoCompr = new StandInfoCompr(standInfoComprKey, comprTypeCode, comprYear);
                    standInfoComprs[results.getRow() - 1] = newStandInfoCompr;
                }

                return standInfoComprs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Photo Header
    public static PhotoHeader getPhotoHeader(int visitKey) {
        try {
            String query = "SELECT * FROM dbo.tblPhotoHeader WHERE VisitKey = " + visitKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int photoHeaderKey = results.getInt("PhotoHeaderKey");
                String msrDate = results.getString("MsrDate");

                PhotoHeader photoHeader = new PhotoHeader(photoHeaderKey, msrDate);

                photoHeader.setPhotoFeatures(getPhotoFeatures(photoHeaderKey));
                photoHeader.setPhotoRequireds(getPhotoRequireds(photoHeaderKey));

                return photoHeader;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Photo Feature
    private static PhotoFeature[] getPhotoFeatures(int photoHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblPhotoFeature WHERE PhotoHeaderKey = " + photoHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblPhotoFeature WHERE PhotoHeaderKey = " + photoHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                PhotoFeature[] photoFeatures = new PhotoFeature[total];

                while(results.next()) {
                    int photoFeatureKey = results.getInt("PhotoFeatureKey");
                    String description = results.getString("Description");
                    int frameNum = results.getInt("FrameNum");

                    PhotoFeature photoFeature = new PhotoFeature(photoFeatureKey, description, frameNum);
                    photoFeatures[results.getRow() - 1] = photoFeature;
                }

                return photoFeatures;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Photo Required
    private static PhotoRequired[] getPhotoRequireds(int photoHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblPhotoRequired WHERE PhotoHeaderKey = " + photoHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblPhotoRequired WHERE PhotoHeaderKey = " + photoHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                PhotoRequired[] photoRequireds = new PhotoRequired[total];

                while(results.next()) {
                    int photoRequiredKey = results.getInt("PhotoRequiredKey");
                    int photoTypeCode = results.getInt("PhotoTypeCode");
                    int frameNum = results.getInt("FrameNum");

                    PhotoRequired newPhotoRequired = new PhotoRequired(photoRequiredKey, photoTypeCode, frameNum);
                    photoRequireds[results.getRow() - 1] = newPhotoRequired;
                }

                return photoRequireds;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Vegetation Header
    public static VegHeader getVegHeader(int visitKey) {
        try {
            String query = "SELECT * FROM dbo.tblVegHeader WHERE VisitKey = " + visitKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int vegHeaderKey = results.getInt("VegHeaderKey");
                String msrDate = results.getString("MsrDate");

                VegHeader vegHeader = new VegHeader(vegHeaderKey, msrDate);

                vegHeader.setVegVTypes(getVegVTypes(vegHeaderKey));
                vegHeader.setVegPlots(getVegPlots(vegHeaderKey));

                return vegHeader;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Vegetation Plot Information
    private static VegPlot[] getVegPlots(int vegHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblVegPlot WHERE VegHeaderKey = " + vegHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblVegPlot WHERE VegHeaderKey = " + vegHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                VegPlot[] vegPlots = new VegPlot[total];

                while(results.next()) {
                    int vegPlotKey = results.getInt("VegPlotKey");
                    int growthPlotNum = results.getInt("GrowthPlotNum");
                    int vegPlotNum = results.getInt("VegPlotNum");
                    int shrubPlotNum = results.getInt("ShrubPlotNum");
                    int regenPlotNum = results.getInt("RegenPlotNum");
                    String quadCode = results.getString("QuadCode");
                    int lineNum = results.getInt("LineNum");
                    int azi = results.getInt("Azi");
                    double dist = results.getDouble("Dist");

                    VegPlot newVegPlot = new VegPlot(vegPlotKey, growthPlotNum, vegPlotNum, shrubPlotNum, regenPlotNum, quadCode, lineNum, azi, dist);

                    newVegPlot.setVegCovers(getVegCovers(vegPlotKey));
                    newVegPlot.setVegRegens(getVegRegens(vegPlotKey));
                    newVegPlot.setVegSpecPres(getVegSpecPres(vegPlotKey));
                    newVegPlot.setVegShrubSpecs(getVegShrubSpecs(vegPlotKey));

                    vegPlots[results.getRow() - 1] = newVegPlot;
                }

                return vegPlots;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Vegetation Shrub Species
    private static VegShrubSpec[] getVegShrubSpecs(int vegPlotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblVegShrubSpec WHERE VegPlotKey = " + vegPlotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblVegShrubSpec WHERE VegPlotKey = " + vegPlotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                VegShrubSpec[] vegRegens = new VegShrubSpec[total];

                while(results.next()) {
                    int vegShrubSpecKey = results.getInt("VegShrubSpecKey");
                    int specCode = results.getInt("SpecCode");
                    String layerCode = results.getString("LayerCode");
                    int coverPct = results.getInt("CoverPct");

                    VegShrubSpec newVegShrubSpec = new VegShrubSpec(vegShrubSpecKey, specCode, layerCode, coverPct);
                    vegRegens[results.getRow() - 1] = newVegShrubSpec;
                }

                return vegRegens;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Vegetation Species Presence
    private static VegSpecPres[] getVegSpecPres(int vegPlotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblVegSpecPres WHERE VegPlotKey = " + vegPlotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblVegSpecPres WHERE VegPlotKey = " + vegPlotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                VegSpecPres[] vegSpecPres = new VegSpecPres[total];

                while(results.next()) {
                    int vegSpecPresKey = results.getInt("VegSpecPresKey");
                    int specCode = results.getInt("SpecCode");

                    VegSpecPres newVegSpecPres = new VegSpecPres(vegSpecPresKey, specCode);
                    vegSpecPres[results.getRow() - 1] = newVegSpecPres;
                }

                return vegSpecPres;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get VegRegen
    private static VegRegen[] getVegRegens(int vegPlotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblVegRegen WHERE VegPlotKey = " + vegPlotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblVegRegen WHERE VegPlotKey = " + vegPlotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                VegRegen[] vegRegens = new VegRegen[total];

                while(results.next()) {
                    int vegRegenKey = results.getInt("VegRegenKey");
                    int regenTypeCode = results.getInt("RegenTypeCode");
                    int specCode = results.getInt("SpecCode");
                    int regenCount = results.getInt("RegenCount");
                    int coverPct = results.getInt("CoverPct");

                    VegRegen newVegRegen = new VegRegen(vegRegenKey, regenTypeCode, specCode, regenCount, coverPct);
                    vegRegens[results.getRow() - 1] = newVegRegen;
                }

                return vegRegens;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get VegCovers
    private static VegCover[] getVegCovers(int vegPlotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblVegCover WHERE VegPlotKey = " + vegPlotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblVegCover WHERE VegPlotKey = " + vegPlotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                VegCover[] vegCovers = new VegCover[total];

                while(results.next()) {
                    int vegCoverKey = results.getInt("VegCoverKey");
                    int vegGroupCode = results.getInt("VegGroupCode");
                    int coverClass1To5Code = results.getInt("CoverClass1To5Code");
                    int coverClass0To5Code = results.getInt("CoverClass0To5Code");
                    int coverPct = results.getInt("CoverPct");

                    VegCover newVegCover = new VegCover(vegCoverKey, vegGroupCode, coverClass1To5Code, coverClass0To5Code, coverPct);
                    vegCovers[results.getRow() - 1] = newVegCover;
                }

                return vegCovers;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get VegVType
    private static VegVType[] getVegVTypes(int vegHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblVegVType WHERE VegHeaderKey = " + vegHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblVegVType WHERE VegHeaderKey = " + vegHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                VegVType[] vegVTypes = new VegVType[total];

                while(results.next()) {
                    int vegVTypeKey = results.getInt("VegVTypeKey");
                    int growthPlotNum =  results.getInt("GrowthPlotNum");
                    int vegTypeCode = results.getInt("VegTypeCode");
                    int elcMethodCode = results.getInt("ELCMethodCode");

                    VegVType newVegVType = new VegVType(vegVTypeKey, growthPlotNum, vegTypeCode, elcMethodCode);
                    vegVTypes[results.getRow() - 1] = newVegVType;
                }

                return vegVTypes;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Tree Header
    public static TreeHeader getTreeHeader(int visitKey) {
        try {
            String query = "SELECT * FROM dbo.tblTreeHeader WHERE VisitKey = " + visitKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int treeHeaderKey = results.getInt("TreeHeaderKey");
                String msrDate = results.getString("MsrDate");

                TreeHeader treeHeader = new TreeHeader(treeHeaderKey, msrDate);

                treeHeader.setTreeGrowthPlot(getTreeGrowthPlots(treeHeaderKey));

                return treeHeader;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Tree Growth Plot
    private static TreeGrowthPlot[] getTreeGrowthPlots(int treeHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblTreeGrowthPlot WHERE TreeHeaderKey = " + treeHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblTreeGrowthPlot WHERE TreeHeaderKey = " + treeHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                TreeGrowthPlot[] treeGrowthPlots = new TreeGrowthPlot[total];

                while(results.next()) {
                    int treeGrowthPlotKey = results.getInt("TreeGrowthPlotKey");
                    int growthPlotNum = results.getInt("GrowthPlotNum");
                    int crownClsr = results.getInt("CrownClsr");
                    int locAzi = results.getInt("LocAzi");
                    double locDist = results.getDouble("LocDist");
                    double radius = results.getDouble("Radius");
                    double width = results.getDouble("Width");
                    double length = results.getDouble("Length");
                    boolean treeRenumber = results.getInt("TreeRenumber") == 1;

                    TreeGrowthPlot newTreeGrowthPlot = new TreeGrowthPlot(treeGrowthPlotKey, growthPlotNum, crownClsr, locAzi, locDist, radius, width, length, treeRenumber);
                    newTreeGrowthPlot.setTreeMsrs(getTreeMsrs(treeGrowthPlotKey));

                    treeGrowthPlots[results.getRow() - 1] = newTreeGrowthPlot;
                }

                return treeGrowthPlots;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Tree Measures
    private static TreeMsr[] getTreeMsrs(int treeGrowthPlotKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblTreeMsr WHERE TreeGrowthPlotKey = " + treeGrowthPlotKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblTreeMsr WHERE TreeGrowthPlotKey = " + treeGrowthPlotKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results != null) {
                TreeMsr[] treeMsrs = new TreeMsr[total];

                while(results.next()) {
                    int treeMsrKey = results.getInt("TreeMsrKey");
                    int treeKey = results.getInt("TreeKey");
                    String treeStatusCode = results.getString("TreeStatusCode");
                    double htToDBH = results.getDouble("HtToDBH");
                    double dbh = results.getDouble("DBH");
                    String qualClass2Code = results.getString("QualClass2Code");
                    String qualClass5Code = results.getString("QualClass5Code");
                    String qualClass6Code = results.getString("QualClass6Code");
                    int liveCrownRatio = results.getInt("LiveCrownRatio");
                    String crownClassCode = results.getString("CrownClassCode");
                    int crownPosnCode = results.getInt("CrownPosnCode");
                    int crownLight = results.getInt("CrownLight");
                    boolean htTree = results.getInt("HtTree") == 1;
                    boolean htTreeCircled = results.getInt("HtTreeCircled") == 1;
                    int decayClass = results.getInt("DecayClass");
                    double ocularLength = results.getInt("OcularLength");
                    boolean brokenTop = results.getInt("BrokenTop") == 1;
                    int crownCondCode = results.getInt("CrownCondCode");
                    int barkRetentionCode = results.getInt("BarkRetentionCode");
                    int woodCondCode = results.getInt("WoodCondCode");
                    String prescripCode = results.getString("PrescripCode");
                    boolean backfill = results.getInt("Backfill") == 1;

                    TreeMsr newTreeMsr = new TreeMsr(treeMsrKey, treeKey, treeStatusCode, htToDBH, dbh, qualClass2Code, qualClass5Code, qualClass6Code, liveCrownRatio, crownClassCode, crownPosnCode, crownLight, htTree, htTreeCircled, decayClass, ocularLength, brokenTop, crownCondCode, barkRetentionCode, woodCondCode, prescripCode, backfill);
                    newTreeMsr.setTreeMissed(getTreeMissed(treeMsrKey));
                    newTreeMsr.setTree(getTree(treeKey));
                    newTreeMsr.setTreeDefms(getTreeDefms(treeMsrKey));
                    newTreeMsr.setTreeCavs(getTreeCavs(treeMsrKey));

                    treeMsrs[results.getRow() - 1] = newTreeMsr;
                }

                return treeMsrs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Tree Cavities
    private static TreeCav[] getTreeCavs(int treeMsrKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblTreeCav WHERE TreeMsrKey = " + treeMsrKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblTreeCav WHERE TreeMsrKey = " + treeMsrKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                TreeCav[] treeCavs = new TreeCav[total];

                while(results.next()) {
                    int treeCavKey = results.getInt("TreeCavKey");
                    int cavTypeCode = results.getInt("CavTypeCode");

                    TreeCav newTreeCav = new TreeCav(treeCavKey, cavTypeCode);
                    treeCavs[results.getRow() -  1] = newTreeCav;
                }

                return treeCavs;

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Tree Deformities
    private static TreeDefm[] getTreeDefms(int treeMsrKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblTreeDefm WHERE TreeMsrKey = " + treeMsrKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblTreeDefm WHERE TreeMsrKey = " + treeMsrKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                TreeDefm[] treeDefms = new TreeDefm[total];

                while(results.next()) {
                    int treeDefmKey = results.getInt("TreeDefmKey");
                    int defmTypeCode = results.getInt("DefmTypeCode");
                    int defmCauseCode = results.getInt("DefmCauseCode");
                    double htFrom = results.getDouble("HtFrom");
                    double htTo = results.getDouble("HtTo");
                    String quadCode = results.getString("QuadCode");
                    int extent = results.getInt("Extent");
                    int degreeLeanArch = results.getInt("DegreeLeanArch");
                    int azi = results.getInt("Azi");
                    int length = results.getInt("Length");
                    int width = results.getInt("Width");
                    int scuff = results.getInt("Scuff");
                    int scrape = results.getInt("Scrape");
                    int gouge = results.getInt("Gouge");
                    int numLive = results.getInt("NumLive");
                    int numDead = results.getInt("NumDead");

                    TreeDefm newTreeDefm = new TreeDefm(treeDefmKey, defmTypeCode, defmCauseCode, htFrom, htTo, quadCode,extent, degreeLeanArch, azi, length, width, scuff, scrape, gouge, numLive, numDead);
                    treeDefms[results.getRow() -  1] = newTreeDefm;
                }

                return treeDefms;

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Tree from database
    private static Tree getTree(int treeKey) {
        try {
            String query = "SELECT * FROM dbo.tblTree WHERE TreeKey = " + treeKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int plotMapGrowthPlotKey = results.getInt("PlotMapGrowthPlotKey");
                int section = results.getInt("Section");
                int treeNum = results.getInt("TreeNum");
                String tagTypeCode = results.getString("TagTypeCode");
                int specCode = results.getInt("SpecCode");
                String treeOriginCode = results.getString("TreeOriginCode");
                int postNum = results.getInt("PostNum");
                double dist = results.getDouble("Dist");
                int azi = results.getInt("Azi");
                int mortCauseCode = results.getInt("MortCauseCode");
                String origTreeNum = results.getString("OrigTreeNum");

                Tree tree = new Tree(plotMapGrowthPlotKey, section, treeNum, tagTypeCode, specCode, treeOriginCode, postNum, dist, azi, mortCauseCode, origTreeNum);

                //tree.setTreeGrowthPlot(getTreeGrowthPlots(treeHeaderKey));

                return tree;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get TreeMissed
    private static TreeMissed getTreeMissed(int treeMsrKey) {
        try {
            String query = "SELECT * FROM dbo.tblTreeMissed WHERE TreeMissedKey = " + treeMsrKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int treeMissedKey = results.getInt("TreeMissedKey");
                boolean missed = results.getInt("Missed") == 1;

                TreeMissed treeMissed = new TreeMissed(treeMissedKey, missed);

                return treeMissed;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Height Header from tblHtHeader
    public static HtHeader getHtHeader(int visitKey) {
        try {
            String query = "SELECT * FROM dbo.tblHtHeader WHERE VisitKey = " + visitKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int htHeaderKey = results.getInt("HtHeaderKey");
                String msrDate = results.getString("MsrDate");

                HtHeader htHeader = new HtHeader(htHeaderKey, visitKey, msrDate);
                return htHeader;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all heights associated with a TreeMsr from tblHt
    public static Ht[] getHts(int treeMsrKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblHt WHERE TreeMsrKey = " + treeMsrKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblHt WHERE TreeMsrKey = " + treeMsrKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                Ht[] hts = new Ht[total];

                while(results.next()) {
                    int htKey = results.getInt("HtKey");
                    int htHeaderKey = results.getInt("HtHeaderKey");
                    double offsetDist = results.getDouble("OffsetDist");
                    double htTot = results.getDouble("HtTot");
                    double htLiveFoliage = results.getDouble("HtLiveFoliage");
                    double htLiveBranch = results.getDouble("HtLiveBranch");
                    double htMerch = results.getDouble("HtMerch");


                    Ht newHt = new Ht(htKey, htHeaderKey, treeMsrKey, offsetDist, htTot, htLiveFoliage, htLiveBranch, htMerch);
                    hts[results.getRow() -  1] = newHt;
                }

                return hts;

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get DWDHeader
    public static DWDHeader getDWDHeader(int visitKey) {
        try {
            String query = "SELECT * FROM dbo.tblDWDHeader WHERE VisitKey = " + visitKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if (results.next()) {
                int dwdHeaderKey = results.getInt("DWDHeaderKey");
                String msrDate = results.getString("MsrDate");

                DWDHeader dwdHeader = new DWDHeader(dwdHeaderKey, visitKey, msrDate);
                return dwdHeader;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get DWDs
    public static DWD[] getDWD(int dwdHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblDWD WHERE DWDHeaderKey = " + dwdHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblDWD WHERE DWDHeaderKey = " + dwdHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                DWD[] dwds = new DWD[total];

                while(results.next()) {
                    int dwdKey = results.getInt("DWDKey");
                    int cwdNum = results.getInt("CWDNum");
                    int specCode = results.getInt("SpecCode");
                    int decompClass5 = results.getInt("DecompClass5");
                    String cwdOriginCode = results.getString("CWDOriginCode");
                    double cwdLength = results.getDouble("CWDLength");
                    double smDia = results.getDouble("SmDia");
                    double lgDia = results.getDouble("LgDia");
                    boolean moss = results.getInt("Moss") == 1;
                    boolean burned = results.getInt("Burned") == 1;
                    boolean hollow = results.getInt("Hollow") == 1;
                    int decompClass3 = results.getInt("DecompClass3");
                    double distFirst = results.getDouble("DistFirst");
                    double distLast = results.getDouble("DistLast");
                    boolean wildlife = results.getInt("Wildlife") == 1;

                    DWD newDWD = new DWD(dwdKey, dwdHeaderKey, cwdNum, specCode, decompClass5, cwdOriginCode, cwdLength, smDia, lgDia, moss, burned, hollow, decompClass3, distFirst, distLast, wildlife);
                    dwds[results.getRow() - 1] = newDWD;
                }
                return dwds;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get DWD Line
    public static DWDLine[] getDWDLines(int dwdHeaderKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblDWDLine WHERE DWDHeaderKey = " + dwdHeaderKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblDWDLine WHERE DWDHeaderKey = " + dwdHeaderKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                DWDLine[] dwdLines = new DWDLine[total];

                while(results.next()) {
                    int dwdLineKey = results.getInt("DWDLineKey");
                    int lineNum = results.getInt("LineNum");
                    int azi = results.getInt("Azi");
                    double length = results.getDouble("Length");
                    boolean stumpPres = results.getInt("StumpPres") == 1;

                    DWDLine newDWDLine = new DWDLine(dwdLineKey, dwdHeaderKey, lineNum, azi, length, stumpPres);
                    dwdLines[results.getRow() -  1] = newDWDLine;
                }

                return dwdLines;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get DWDIntersect
    public static DWDIntersect[] getDWDIntersections(int dwdLineKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblDWDIntersect WHERE DWDLineKey = " + dwdLineKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblDWDIntersect WHERE DWDLineKey = " + dwdLineKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                DWDIntersect[] dwdIntersects = new DWDIntersect[total];

                while(results.next()) {
                    int dwdIntersectKey = results.getInt("DWDIntersectKey");
                    int dwdKey = results.getInt("DWDKey");
                    double dia = results.getDouble("Dia");
                    int tiltAngle = results.getInt("TiltAngle");

                    DWDIntersect newDWDIntersect = new DWDIntersect(dwdIntersectKey, dwdLineKey, dwdKey, dia, tiltAngle);
                    dwdIntersects[results.getRow() -  1] = newDWDIntersect;
                }

                return dwdIntersects;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get DWD Accum
    public static DWDAccum[] getDWDAccum(int dwdLineKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblDWDAccum WHERE DWDLineKey = " + dwdLineKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblDWDAccum WHERE DWDLineKey = " + dwdLineKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                DWDAccum[] dwdAccums = new DWDAccum[total];

                while(results.next()) {
                    int dwdAccumKey = results.getInt("DWDAccumKey");
                    int accumNum = results.getInt("AccumNum");
                    double length = results.getDouble("Length");
                    double depth = results.getDouble("Depth");
                    int decompClass5 = results.getInt("DecompClass5");
                    String cwdOriginCode = results.getString("CWDOriginCode");
                    boolean burned = results.getInt("Burned") == 1;
                    int pctConifer = results.getInt("PctConifer");
                    int pctHdwd = results.getInt("PctHdwd");

                    DWDAccum newDWDAccum = new DWDAccum(dwdAccumKey, dwdLineKey, accumNum, length, depth, decompClass5, cwdOriginCode, burned, pctConifer, pctHdwd);
                    dwdAccums[results.getRow() -  1] = newDWDAccum;
                }

                return dwdAccums;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get DWD Stumps
    public static DWDStump[] getDWDStumps(int dwdLineKey) {
        try {
            // Get the total number of notes.
            String query = "SELECT COUNT(*) as total FROM dbo.tblDWDStump WHERE DWDLineKey = " + dwdLineKey;
            ResultSet count = DatabaseController.executeQuery(query);
            count.next();
            int total = count.getInt("total");

            query = "SELECT * FROM dbo.tblDWDStump WHERE DWDLineKey = " + dwdLineKey;
            ResultSet results = DatabaseController.executeQuery(query);

            if(results != null) {
                DWDStump[] dwdStumps = new DWDStump[total];

                while(results.next()) {
                    int dwdStumpKey = results.getInt("DWDStumpKey");
                    int specCode = results.getInt("SpecCode");
                    int stumpCount = results.getInt("StumpCount");

                    DWDStump newDWDStump = new DWDStump(dwdStumpKey, dwdLineKey, specCode, stumpCount);
                    dwdStumps[results.getRow() -  1] = newDWDStump;
                }

                return dwdStumps;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
