package Prikaz;

import Entites.Pacijent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Baza.Database;
import narucivanjelekova.NarucivanjeLekova;
import narucivanjelekova.NarucivanjeLekovaStyle;

/**
 * Prikaz registarcije
 *
 * @author Miona Pavlovic
 */
public class PrikazRegistracije {

    private NarucivanjeLekovaStyle style = new NarucivanjeLekovaStyle(); // Klasa za oblik boju dugmeta itd

    public PrikazRegistracije(Stage stage, Database db) {

        Label imeL = new Label("Ime");
        TextField ime = new TextField();

        Label prezimeL = new Label("Prezime");
        TextField prezime = new TextField();

        Label adresaL = new Label("Adresa");
        TextField adresa = new TextField();

        Label mobilniL = new Label("Mobilni");
        TextField mobilni = new TextField();

        Label usernameL = new Label("Username");
        TextField username = new TextField();

        Label passwordL = new Label("Password");
        PasswordField password = new PasswordField();

        Label passwordRL = new Label("Repeat Password");
        PasswordField passwordR = new PasswordField();

        Button registrujSe = new Button("Registruj se"); // Labele i polja za tekst i dugme za registraciju
        registrujSe.setStyle(style.getBUTTON());
        registrujSe.setPrefWidth(200);
        registrujSe.setOnAction(e -> {  // izbacivanje dogadjaja pritiskom na dugme registruj se

            if (ime.getText().length() == 0 || prezime.getText().length() == 0
                    || adresa.getText().length() == 0 || mobilni.getText().length() == 0
                    || username.getText().length() == 0 || password.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Neko polje je nepopunjeno!");
            } else {
                if (password.getText().equals(passwordR.getText())) {
                    if (!username.getText().startsWith("admin")
                            && !username.getText().startsWith("l")) {
                        db.ubaciPacijenta(new Pacijent(ime.getText(), prezime.getText(), adresa.getText(),
                                mobilni.getText(), username.getText(), password.getText()));
                        JOptionPane.showMessageDialog(null, "Uspesna registracija!");
                        try {
                            new NarucivanjeLekova().start(stage);
                        } catch (SQLException ex) {
                            Logger.getLogger(PrikazRegistracije.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(PrikazRegistracije.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username ne moze pocinjati sa admin ili l");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Sifre nisu iste!");
                }

            }

        });

        Button nazad = new Button("Nazad");
        nazad.setStyle(style.getBUTTON());
        nazad.setPrefWidth(200);
        nazad.setOnAction(e -> {

            try {
                new NarucivanjeLekova().start(stage);
            } catch (SQLException ex) {
                Logger.getLogger(PrikazRegistracije.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PrikazRegistracije.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
        root.setStyle(style.getFRAMESTYLE());

        root.add(imeL, 0, 0);
        root.add(ime, 1, 0);
        root.add(prezimeL, 0, 1);
        root.add(prezime, 1, 1);
        root.add(adresaL, 0, 2);
        root.add(adresa, 1, 2);
        root.add(mobilniL, 0, 3);
        root.add(mobilni, 1, 3);
        root.add(usernameL, 0, 4);
        root.add(username, 1, 4);
        root.add(passwordL, 0, 5);
        root.add(password, 1, 5);
        root.add(passwordRL, 0, 6);
        root.add(passwordR, 1, 6);
        root.add(registrujSe, 0, 7);
        root.add(nazad, 1, 7);

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
