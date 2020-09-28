package Prikaz;

import Entites.LekoviInfekcije;
import Entites.Tabela;
import Entites.Recept;
import Entites.Pacijent;
import Entites.LekoviBolesti;
import Entites.Lekovi;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Baza.Database;
import narucivanjelekova.NarucivanjeLekovaStyle;

public class PrikazPacijenta {

    private int ukupnaCena = 0;
    private NarucivanjeLekovaStyle style = new NarucivanjeLekovaStyle();

    public PrikazPacijenta(Stage stage, Pacijent pacijent) {

        GridPane root = new GridPane();

        ListView<String> naruceneStvari = new ListView<>();
        naruceneStvari.setPrefHeight(120);
        // tabela lekova koje pacijent moze da naruci
        TableView<Lekovi> tabelaLekova = new Tabela(3).getTable();
        tabelaLekova.setPrefHeight(120);
        TableView<LekoviBolesti> tabelaLekovaBolesti = new Tabela(4).getTable();
        tabelaLekovaBolesti.setPrefHeight(120);
        TableView<LekoviInfekcije> tabelaLekovaInfekcije = new Tabela(5).getTable();
        tabelaLekovaInfekcije.setPrefHeight(120);

        Button clearAll = new Button("Izbrisi Sve");
        clearAll.setStyle(style.getBUTTON());

        clearAll.setOnAction(e -> {
            naruceneStvari.getItems().clear();
            ukupnaCena = 0;
        });

        CheckBox dostava = new CheckBox("Dostava");

        Button kupi = new Button("Kupi");
        // pritiskom dugmeta kupi racuna se ukupna cena i gleda da li je dostava obelezena
        kupi.setStyle(style.getBUTTON());
        kupi.setOnAction(e -> {
            if (dostava.isSelected() && ukupnaCena >= 500) {
                if (JOptionPane.showConfirmDialog(null, "Potvrdite"
                        + "\nTrenutna cena: " + ukupnaCena
                        + "\nDostavu mozete ocekivati za 30 min") == 0) {

                    String porudzbina = "";
                    for (String i : naruceneStvari.getItems()) {
                        porudzbina += i + ", ";
                    }

                    Calendar now = Calendar.getInstance();
                    Recept r = new Recept(porudzbina, ukupnaCena, true, now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE), pacijent.toString());
                    try {
                        new Database().ubaciRecept(r);

                    } catch (SQLException ex) {
                        Logger.getLogger(PrikazPacijenta.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PrikazPacijenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "HVALA VAM NA POVERENJU!");
                    System.exit(1);
                }

            } else if (dostava.isSelected() && ukupnaCena < 500) {
                JOptionPane.showMessageDialog(null, "Nije dovoljna cena za dostavu. \nTrenutna cena: " + ukupnaCena);
            } else if (ukupnaCena == 0) {
                JOptionPane.showMessageDialog(null, "Nista niste izabrali!");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Potvrdite Vasu narudzbinu."
                        + "\nTrenutna cena: " + ukupnaCena
                        + "\nMozete doci po porudzbinu za 30 min") == 0) {

                    String porudzbina = "";
                    for (String i : naruceneStvari.getItems()) {
                        porudzbina += i + ", ";
                    }

                    Calendar now = Calendar.getInstance();
                    Recept r = new Recept(porudzbina, ukupnaCena, false, now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE), pacijent.toString());
                    JOptionPane.showMessageDialog(null, r.toString());
                    try {
                        new Database().ubaciRecept(r);
                    } catch (SQLException ex) {
                        Logger.getLogger(PrikazPacijenta.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PrikazPacijenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "HVALA VAM NA POVERENJU!");
                    System.exit(1);
                }
            }
        });

        tabelaLekova.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Lekovi l = tabelaLekova.getSelectionModel().getSelectedItem();
                int kolona = tabelaLekova.getSelectionModel().getSelectedCells().get(0).getColumn();

            }
        });

        tabelaLekovaBolesti.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                LekoviBolesti lb = tabelaLekovaBolesti.getSelectionModel().getSelectedItem();
                naruceneStvari.getItems().add(lb.getIme() + " " + lb.getCena());
                ukupnaCena += lb.getCena();

            }
        });

        tabelaLekovaInfekcije.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                LekoviInfekcije li = tabelaLekovaInfekcije.getSelectionModel().getSelectedItem();
                naruceneStvari.getItems().add(li.getIme() + " " + li.getCena());
                ukupnaCena += li.getCena();

            }
        });

        root.setPadding(new Insets(12));
        root.setVgap(10);
        root.setHgap(10);
        root.setStyle(style.getFRAMESTYLE());

        root.add(new Label("Lekovi"), 0, 0);
        root.add(tabelaLekova, 0, 1);
        root.add(new Label("Lekovi za teske bolesti"), 0, 2);
        root.add(tabelaLekovaBolesti, 0, 3);
        root.add(new Label("Lekovi za infekcije"), 0, 4);
        root.add(tabelaLekovaInfekcije, 0, 5);
        GridPane.setRowSpan(naruceneStvari, 2);
        root.add(naruceneStvari, 1, 1);
        root.add(clearAll, 1, 3);
        root.add(new Label("Dostavu je moguce izvrsiti"), 1, 4);
        root.add(dostava, 1, 5);
        root.add(kupi, 1, 6);

        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);

    }

}
