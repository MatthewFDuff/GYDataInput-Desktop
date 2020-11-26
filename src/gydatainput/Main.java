package gydatainput;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Growth & Yield Desktop Application");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

        String connectionString = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
                + "database=gyPSPPGP;"
                //+ "user=matthew;" Username and password are only required if integratedSecurity=false
                //+ "password=;"
                + "encrypt=false;"
                + "integratedSecurity=true;" // Automatically uses windows credentials (requires .dll in System32)
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        try (Connection connection = DriverManager.getConnection((connectionString))) {
            System.out.println("Connected to Growth & Yield Database");

            String query = "SELECT TOP (20) * FROM dbo.tblPlot";

            try (Statement statement = connection.createStatement()){
                ResultSet result = statement.executeQuery(query);
                while(result.next()) {
                    System.out.println(result.getString("PlotName"));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
