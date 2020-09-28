package narucivanjelekova;

import Baza.Database;
import Prikaz.PrikazRegistracije;
import Prikaz.PrikazPacijenta;
import Prikaz.PrikazLekara;
import Entites.Pacijent;
import Entites.Admin;
import Prikaz.PrikazAdmin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class NarucivanjeLekova extends Application {

    private NarucivanjeLekovaStyle style = new NarucivanjeLekovaStyle();

    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {

        Database db = new Database();

        Label imeL = new Label("Username");
        imeL.setStyle(style.getNazad()); // Labela koja nam kaze da unesemo username u textfield-u
        TextField ime = new TextField();  // polje za unos teksta

        Label sifraL = new Label("Password");
        sifraL.setStyle(style.getNazad());
        // polje za unos sifre
        PasswordField sifra = new PasswordField();

        Button login = new Button("Login");
        login.setStyle(style.getBUTTON());
        login.setPrefWidth(180); // dugme login koje na kada kliknemo izbacuje dogadjaj
        login.setOnAction(e -> {

                 
            if(ime.getText().length() == 0 || sifra.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Polja(e) prazna!");
            }else{
                if(ime.getText().startsWith("admin") || sifra.getText().startsWith("admin")){

                    new PrikazAdmin(primaryStage, db, true,
                            true,true);
                   
                   
                }else if(ime.getText().startsWith("lekar ") || sifra.getText().startsWith("lekar")){
                    new PrikazLekara(primaryStage, db);
                }else{
                     try {
                        if(db.daLiPostojiTakvaOsoba(2, ime.getText(), sifra.getText())){
                            new PrikazPacijenta(primaryStage, (Pacijent) db.vratiTuOsobuKojaPostoji(2, ime.getText(), sifra.getText()));
                        }else
                            JOptionPane.showMessageDialog(null, "Ne postoji takav korisnik");
                    } catch (SQLException ex) {
                        Logger.getLogger(NarucivanjeLekova.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        Button signin = new Button("Registration");
        signin.setStyle(style.getBUTTON());
        signin.setPrefWidth(180);
        signin.setOnAction(e -> {

            new PrikazRegistracije(primaryStage, db); // ukoliko pritisnemo dugme registation prebacuje nas na PrikazRegistacije

        });

        VBox root = new VBox(10);

        root.setPadding(new Insets(20));
        root.setStyle(style.getFRAMESTYLE());
        root.getChildren().addAll(imeL, ime, sifraL, sifra, new Label(), login, signin);

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("EKarton");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
