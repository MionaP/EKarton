package Prikaz;

import Entites.LekoviInfekcije;
import Entites.Tabela;
import Entites.Pacijent;
import Entites.LekoviBolesti;
import Entites.Lekovi;
import Entites.Lekar;
import Entites.Admin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Baza.Database;
import narucivanjelekova.NarucivanjeLekovaStyle;

public class PrikazAdmin {

    private int trenutnoIzabraniID;
    private NarucivanjeLekovaStyle style = new NarucivanjeLekovaStyle();

    public PrikazAdmin(Stage stage, Database db, boolean mozeDaMenjaAdmine,
            boolean mozeDaMenjaZaposlene, boolean mozeDaMenjaKupce) {

        List<Node> listaTrenutnoPostavljenihStvari = new ArrayList<>();

        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(15);
        root.setVgap(15);

        ObservableList<String> options = FXCollections.observableArrayList(
                "lekovi", "lekoviBolesti", "lekoviInfekcije");  // lista kojoj grup lek koji zelimo pripada
        // mogucnosti koje admin ima
        if (mozeDaMenjaAdmine && mozeDaMenjaZaposlene && mozeDaMenjaKupce) {
            options.add("admini");
            options.add("pacijenti");
            options.add("lekari");
        } else if (mozeDaMenjaAdmine && mozeDaMenjaZaposlene) {
            options.add("admini");
            options.add("lekari");
        } else if (mozeDaMenjaAdmine && mozeDaMenjaKupce) {
            options.add("admini");
            options.add("pacijenti");
        } else if (mozeDaMenjaZaposlene && mozeDaMenjaKupce) {
            options.add("lekari");
            options.add("pacijenti");
        } else if (mozeDaMenjaAdmine) {
            options.add("admini");
        } else if (mozeDaMenjaZaposlene) {
            options.add("zaposleni");
        } else if (mozeDaMenjaKupce) {
            options.add("kupci");
        }

        ComboBox<String> comboBox = new ComboBox<>(options);

        root.add(comboBox, 0, 0);

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);

        comboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            root.getChildren().removeAll(listaTrenutnoPostavljenihStvari);

            Button ubaci = new Button("Ubaci");
            ubaci.setStyle(style.getBUTTON());
            Button izbrisi = new Button("Izbrisi");
            izbrisi.setStyle(style.getBUTTON());
            Button izmeni = new Button("Izmeni");
            izmeni.setStyle(style.getBUTTON());

            listaTrenutnoPostavljenihStvari.add(ubaci);
            listaTrenutnoPostavljenihStvari.add(izbrisi);
            listaTrenutnoPostavljenihStvari.add(izmeni);

            if (newValue.equals("admini")) {

                Tabela objTabele = new Tabela(0);
                TableView<Admin> tabela = objTabele.getTable();

                TextField korisnickoIme = new TextField();
                korisnickoIme.setPromptText("Korisnicko ime");

                TextField sifra = new TextField();
                sifra.setPromptText("Sifra");

                CheckBox cb1 = new CheckBox("Moze da menja admine");
                cb1.setMinWidth(170);
                CheckBox cb2 = new CheckBox("Moze da menja lekare");
                CheckBox cb3 = new CheckBox("Moze da menja pacijente");
// dugme za ubacivanje admina, unosimo username i password ukoliko su oni jednaki adminu ubacujemo admina u bazu
                ubaci.setOnAction(e -> {
                    if (!korisnickoIme.getText().isEmpty() && !sifra.getText().isEmpty()) {
                        if (!cb1.isSelected() && !cb2.isSelected() && !cb3.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Niste selektovali nista od admin kontrola");
                        } else {
                            if (korisnickoIme.getText().startsWith("admin")) {
                                try {
                                    db.ubaciAdmina(new Admin(cb1.isSelected(), cb3.isSelected(),
                                            cb2.isSelected(), korisnickoIme.getText(), sifra.getText()));
                                    objTabele.refresh();
                                } catch (SQLException ex) {
                                    Logger.getLogger(PrikazAdmin.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Username treba poceti sa admin");
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });
// menajmo admina
                izmeni.setOnAction(e -> {
                    if (!korisnickoIme.getText().isEmpty() && !sifra.getText().isEmpty()) {
                        if (!cb1.isSelected() && !cb2.isSelected() && !cb3.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Niste selektovali nista od admin kontrola");
                        } else {
                            if (korisnickoIme.getText().startsWith("admin")) {
                                db.izmeniAdmina(new Admin(cb1.isSelected(), cb3.isSelected(),
                                        cb2.isSelected(), korisnickoIme.getText(), sifra.getText()), trenutnoIzabraniID);
                                objTabele.refresh();
                                trenutnoIzabraniID = 0;
                            } else {
                                JOptionPane.showMessageDialog(null, "Username treba poceti sa admin");
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izbrisi.setOnAction(e -> {

                    db.ukloni(trenutnoIzabraniID, 0);
                    objTabele.refresh();
                    trenutnoIzabraniID = 0;

                });

                tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        Admin admin = tabela.getSelectionModel().getSelectedItem();
                        korisnickoIme.setText(admin.getKorisnickoIme());
                        sifra.setText(admin.getSifra());
                        cb1.setSelected(admin.isMozeDaMenjaAdmine());
                        cb2.setSelected(admin.isMozeDaMenjaLekare());
                        cb3.setSelected(admin.isMozeDaMenjaPacijente());
                        trenutnoIzabraniID = admin.getId();
                    }
                });
                root.add(korisnickoIme, 0, 1);
                root.add(sifra, 0, 2);
                root.add(cb1, 0, 3);
                root.add(cb2, 0, 4);
                root.add(cb3, 0, 5);
                root.add(ubaci, 0, 6);
                root.add(izbrisi, 0, 7);
                root.add(izmeni, 0, 8);
                root.setStyle(style.getFRAMESTYLE());

                GridPane.setRowSpan(tabela, 9);
                root.add(tabela, 1, 1);

                listaTrenutnoPostavljenihStvari.add(korisnickoIme);
                listaTrenutnoPostavljenihStvari.add(sifra);
                listaTrenutnoPostavljenihStvari.add(cb1);
                listaTrenutnoPostavljenihStvari.add(cb2);
                listaTrenutnoPostavljenihStvari.add(cb3);
                listaTrenutnoPostavljenihStvari.add(tabela);

            } else if (newValue.equals("Lekari")) {
                Tabela objTabele = new Tabela(1);
                TableView<Lekar> tabela = objTabele.getTable();

                TextField ime = new TextField();
                ime.setPromptText("Ime");

                TextField prezime = new TextField();
                prezime.setPromptText("Prezime");

                TextField radnoVreme = new TextField();
                radnoVreme.setPromptText("Radno vreme");

                TextField korisnickoIme = new TextField();
                korisnickoIme.setPromptText("Korisnicko ime");

                TextField sifra = new TextField();
                sifra.setPromptText("Sifra");
                // sada sve sto smo radili za admina ponavljamo za lekara tj imamo tabelu lekara i dugme ubaci ili izmeni 
                // dugme ubaci koristimo da izbaci dogadjaj kojim ubacujemo admina u bazu
                // a dugme izmeni menjamo podatke o lekaru i refresujemo tabelu koja se menja
                tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        Lekar lekar = tabela.getSelectionModel().getSelectedItem();
                        ime.setText(lekar.getIme());
                        prezime.setText(lekar.getPrezime());
                        radnoVreme.setText(lekar.getRadnoVreme());
                        korisnickoIme.setText(lekar.getKorisnickoIme());
                        sifra.setText(lekar.getSifra());
                        trenutnoIzabraniID = lekar.getId();

                    }
                });
                ubaci.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !prezime.getText().isEmpty()
                            && !radnoVreme.getText().isEmpty()
                            && !korisnickoIme.getText().isEmpty()
                            && !sifra.getText().isEmpty()) {

                        if (korisnickoIme.getText().startsWith("Lekar")) {
                            db.ubaciLekara(new Lekar(ime.getText(),
                                    prezime.getText(),
                                    radnoVreme.getText(),
                                    korisnickoIme.getText(),
                                    sifra.getText()));

                            objTabele.refresh();

                        } else {
                            JOptionPane.showMessageDialog(null, "Username mora poceti sa zaposlen");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izmeni.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !prezime.getText().isEmpty()
                            && !radnoVreme.getText().isEmpty()
                            && !korisnickoIme.getText().isEmpty()
                            && !sifra.getText().isEmpty()) {

                        if (korisnickoIme.getText().startsWith("Lekar")) {
                            db.izmeniLekara(new Lekar(ime.getText(),
                                    prezime.getText(),
                                    radnoVreme.getText(),
                                    korisnickoIme.getText(),
                                    sifra.getText()), trenutnoIzabraniID);

                            objTabele.refresh();
                            trenutnoIzabraniID = 0;

                        } else {
                            JOptionPane.showMessageDialog(null, "Username mora poceti sa zaposlen");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izbrisi.setOnAction(e -> {

                    db.ukloni(trenutnoIzabraniID, 1);
                    objTabele.refresh();
                    trenutnoIzabraniID = 0;

                });

                root.add(ime, 0, 1);
                root.add(prezime, 0, 2);
                root.add(radnoVreme, 0, 3);
                root.add(korisnickoIme, 0, 4);
                root.add(sifra, 0, 5);
                root.add(ubaci, 0, 6);
                root.add(izbrisi, 0, 7);
                root.add(izmeni, 0, 8);

                GridPane.setRowSpan(tabela, 9);
                root.add(tabela, 1, 1);

                listaTrenutnoPostavljenihStvari.add(ime);
                listaTrenutnoPostavljenihStvari.add(prezime);
                listaTrenutnoPostavljenihStvari.add(radnoVreme);
                listaTrenutnoPostavljenihStvari.add(korisnickoIme);
                listaTrenutnoPostavljenihStvari.add(sifra);
                listaTrenutnoPostavljenihStvari.add(tabela);
            } else if (newValue.equals("Pacijenti")) {
                Tabela objTabele = new Tabela(2);
                TableView<Pacijent> tabela = objTabele.getTable();

                TextField ime = new TextField();
                ime.setPromptText("Ime");

                TextField prezime = new TextField();
                prezime.setPromptText("Prezime");

                TextField mobilni = new TextField();
                mobilni.setPromptText("Mobilni");

                TextField adresa = new TextField();
                adresa.setPromptText("Adresa");

                TextField korisnickoIme = new TextField();
                korisnickoIme.setPromptText("Korisnicko ime");

                TextField sifra = new TextField();
                sifra.setPromptText("Sifra");
// sada sve sto smo radili za admina ponavljamo za pacijenta tj imamo tabelu  i dugme ubaci ili izmeni 
                // dugme ubaci koristimo da izbaci dogadjaj kojim ubacujemo pacijenta u bazu
                // a dugme izmeni menjamo podatke o pacijentu i refresujemo tabelu
                tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        Pacijent pacijent = tabela.getSelectionModel().getSelectedItem();
                        ime.setText(pacijent.getIme());
                        prezime.setText(pacijent.getPrezime());
                        mobilni.setText(pacijent.getMobilni());
                        adresa.setText(pacijent.getAdresa());
                        korisnickoIme.setText(pacijent.getKorisnickoIme());
                        sifra.setText(pacijent.getSifra());

                        trenutnoIzabraniID = pacijent.getId();
                    }
                });
                ubaci.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !prezime.getText().isEmpty()
                            && !mobilni.getText().isEmpty()
                            && !adresa.getText().isEmpty()
                            && !korisnickoIme.getText().isEmpty()
                            && !sifra.getText().isEmpty()) {

                        db.ubaciPacijenta(new Pacijent(ime.getText(),
                                prezime.getText(),
                                adresa.getText(),
                                mobilni.getText(),
                                korisnickoIme.getText(),
                                sifra.getText()));

                        objTabele.refresh();

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izmeni.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !prezime.getText().isEmpty()
                            && !mobilni.getText().isEmpty()
                            && !adresa.getText().isEmpty()
                            && !korisnickoIme.getText().isEmpty()
                            && !sifra.getText().isEmpty()) {

                        db.izmeniPacijenta(new Pacijent(ime.getText(),
                                prezime.getText(),
                                adresa.getText(),
                                mobilni.getText(),
                                korisnickoIme.getText(),
                                sifra.getText()), trenutnoIzabraniID);

                        objTabele.refresh();
                        trenutnoIzabraniID = 0;

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izbrisi.setOnAction(e -> {

                    db.ukloni(trenutnoIzabraniID, 2);
                    objTabele.refresh();
                    trenutnoIzabraniID = 0;

                });

                root.add(ime, 0, 1);
                root.add(prezime, 0, 2);
                root.add(mobilni, 0, 3);
                root.add(adresa, 0, 4);
                root.add(korisnickoIme, 0, 5);
                root.add(sifra, 0, 6);
                root.add(ubaci, 0, 7);
                root.add(izbrisi, 0, 8);
                root.add(izmeni, 0, 9);

                GridPane.setRowSpan(tabela, 10);
                root.add(tabela, 1, 1);

                listaTrenutnoPostavljenihStvari.add(ime);
                listaTrenutnoPostavljenihStvari.add(prezime);
                listaTrenutnoPostavljenihStvari.add(mobilni);
                listaTrenutnoPostavljenihStvari.add(adresa);
                listaTrenutnoPostavljenihStvari.add(korisnickoIme);
                listaTrenutnoPostavljenihStvari.add(sifra);
                listaTrenutnoPostavljenihStvari.add(tabela);

            } else if (newValue.equals("Lekovi")) {

                Tabela objTabele = new Tabela(4);
                TableView<Lekovi> tabela = objTabele.getTable();

                TextField ime = new TextField();
                ime.setPromptText("Ime");

                TextField cena = new TextField();
                cena.setPromptText("Cena");

                tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        Lekovi l = tabela.getSelectionModel().getSelectedItem();
                        ime.setText(l.getIme());
                        cena.setText(l.getCena() + "");

                        trenutnoIzabraniID = l.getId();
                    }
                });
                ubaci.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !cena.getText().isEmpty()) {

                        if (cena.getText().matches(".*\\d+.*")) {
                            db.ubaciLekove(new Lekovi(ime.getText(),
                                    Integer.parseInt(cena.getText())));

                            objTabele.refresh();
                        } else {
                            JOptionPane.showMessageDialog(null, "Polje za cenu ne moze sadrzati"
                                    + "nista sem brojeva!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izmeni.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !cena.getText().isEmpty()) {

                        if (cena.getText().matches(".*\\d+.*")) {
                            db.izmeniLekove(new Lekovi(ime.getText(),
                                    Integer.parseInt(cena.getText())), trenutnoIzabraniID);

                            objTabele.refresh();
                            trenutnoIzabraniID = 0;
                        } else {
                            JOptionPane.showMessageDialog(null, "Polje za cenu ne moze sadrzati"
                                    + "nista sem brojeva!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izbrisi.setOnAction(e -> {

                    db.ukloni(trenutnoIzabraniID, 4);
                    objTabele.refresh();
                    trenutnoIzabraniID = 0;

                });
                root.add(ime, 0, 1);
                root.add(cena, 0, 2);
                root.add(ubaci, 0, 3);
                root.add(izbrisi, 0, 4);
                root.add(izmeni, 0, 5);

                GridPane.setRowSpan(tabela, 6);
                root.add(tabela, 1, 1);

                listaTrenutnoPostavljenihStvari.add(ime);
                listaTrenutnoPostavljenihStvari.add(cena);
                listaTrenutnoPostavljenihStvari.add(tabela);

            } else if (newValue.equals("Lekovi za bolesti")) {
                Tabela objTabele = new Tabela(4);
                TableView<LekoviBolesti> tabela = objTabele.getTable();

                TextField ime = new TextField();
                ime.setPromptText("Ime");

                TextField cena = new TextField();
                cena.setPromptText("Cena");

                tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        LekoviBolesti lb = tabela.getSelectionModel().getSelectedItem();
                        ime.setText(lb.getIme());
                        cena.setText(lb.getCena() + "");

                        trenutnoIzabraniID = lb.getId();
                    }
                });
                ubaci.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !cena.getText().isEmpty()) {

                        if (cena.getText().matches(".*\\d+.*")) {
                            db.ubaciLekoviBolesti(new LekoviBolesti(ime.getText(),
                                    Integer.parseInt(cena.getText())));

                            objTabele.refresh();
                        } else {
                            JOptionPane.showMessageDialog(null, "Polje za cenu ne moze sadrzati"
                                    + "nista sem brojeva!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izmeni.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !cena.getText().isEmpty()) {

                        if (cena.getText().matches(".*\\d+.*")) {
                            db.izmeniLekoviBolesti(new LekoviBolesti(ime.getText(),
                                    Integer.parseInt(cena.getText())), trenutnoIzabraniID);

                            objTabele.refresh();
                            trenutnoIzabraniID = 0;
                        } else {
                            JOptionPane.showMessageDialog(null, "Polje za cenu ne moze sadrzati"
                                    + "nista sem brojeva!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izbrisi.setOnAction(e -> {

                    db.ukloni(trenutnoIzabraniID, 4);
                    objTabele.refresh();
                    trenutnoIzabraniID = 0;

                });
                root.add(ime, 0, 1);
                root.add(cena, 0, 2);
                root.add(ubaci, 0, 3);
                root.add(izbrisi, 0, 4);
                root.add(izmeni, 0, 5);

                GridPane.setRowSpan(tabela, 6);
                root.add(tabela, 1, 1);

                listaTrenutnoPostavljenihStvari.add(ime);
                listaTrenutnoPostavljenihStvari.add(cena);
                listaTrenutnoPostavljenihStvari.add(tabela);
            } else if (newValue.equals("LekoviInfekcije")) {
                Tabela objTabele = new Tabela(5);
                TableView<LekoviInfekcije> tabela = objTabele.getTable();

                TextField ime = new TextField();
                ime.setPromptText("Ime");

                TextField cena = new TextField();
                cena.setPromptText("Cena");

                tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        LekoviInfekcije lekoviInfekcije = tabela.getSelectionModel().getSelectedItem();
                        ime.setText(lekoviInfekcije.getIme());

                        cena.setText(lekoviInfekcije.getCena() + "");

                        trenutnoIzabraniID = lekoviInfekcije.getId();
                    }
                });
                ubaci.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !cena.getText().isEmpty()) {

                        if (cena.getText().matches(".*\\d+.*")) {
                            db.ubaciLekoviInfekcije(new LekoviInfekcije(ime.getText(),
                                    Integer.parseInt(cena.getText())));

                            objTabele.refresh();
                        } else {
                            JOptionPane.showMessageDialog(null, "Polja "
                                    + "mogu sadrzati samo brojeve!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izmeni.setOnAction(e -> {
                    if (!ime.getText().isEmpty()
                            && !cena.getText().isEmpty()) {

                        if (cena.getText().matches(".*\\d+.*")) {
                            db.izmeniLekoviInfekcije(new LekoviInfekcije(ime.getText(),
                                    Integer.parseInt(cena.getText())),
                                    trenutnoIzabraniID);

                            objTabele.refresh();
                            trenutnoIzabraniID = 0;
                        } else {
                            JOptionPane.showMessageDialog(null, "Polja"
                                    + "mogu sadrzati samo brojeve!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Polja(e) prazna");
                    }
                });

                izbrisi.setOnAction(e -> {

                    db.ukloni(trenutnoIzabraniID, 5);
                    objTabele.refresh();
                    trenutnoIzabraniID = 0;

                });
                root.add(ime, 0, 1);
                root.add(cena, 0, 2);
                root.add(ubaci, 0, 3);
                root.add(izbrisi, 0, 4);
                root.add(izmeni, 0, 5);

                GridPane.setRowSpan(tabela, 6);
                root.add(tabela, 1, 1);

                listaTrenutnoPostavljenihStvari.add(ime);
                listaTrenutnoPostavljenihStvari.add(cena);
                listaTrenutnoPostavljenihStvari.add(tabela);
            }
        });

    }

}
