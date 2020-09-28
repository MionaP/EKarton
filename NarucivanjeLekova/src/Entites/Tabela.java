package Entites;

import Entites.Recept;
import Entites.Pacijent;
import Entites.LekoviBolesti;
import Entites.Lekovi;
import Entites.Lekar;
import Entites.Admin;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Baza.Database;

public class Tabela {

    // u klasi tabela kreiramo tabele sa svim podacima i kolonama koje ce kasnije da 
    // nam trebaju za prikaz i odabir, ono sto ce sigurno da nam treba jeste tabela lekovi
    // kako bi mogli da vidimo sve lekove i da odaberemo zeljeni
    private TableView<Admin> tabelaAdmina = null;
    private TableView<Lekar> tabelaLekara = null;
    private TableView<Pacijent> tabelaPacijenta = null;
    private TableView<Lekovi> tabelaLekova = null;
    private TableView<LekoviBolesti> tabelaLekovaBolesti = null;
    private TableView<LekoviInfekcije> tabelaLekovaInfekcije = null;
    private TableView<Recept> tabelaRecepta = null;

    private int currentTableNumber;

    private Database database;

    public Tabela(int number) {
        this.currentTableNumber = number;

        try {
            database = new Database();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        if (number == 0) {
            tabelaAdmina = new TableView<>();
            TableColumn<Admin, String> korisnickoIme = new TableColumn<>("Korisnicko ime");
            korisnickoIme.setCellValueFactory(
                    new PropertyValueFactory<>("korisnickoIme"));

            TableColumn<Admin, String> sifra = new TableColumn<>("Sifra");
            sifra.setCellValueFactory(
                    new PropertyValueFactory<>("sifra"));

            TableColumn<Admin, Boolean> mozeDaMenjaAdmine = new TableColumn<>("mozeDaMenjaAdmine");
            mozeDaMenjaAdmine.setCellValueFactory(
                    new PropertyValueFactory<>("mozeDaMenjaAdmine"));

            TableColumn<Admin, Boolean> mozeDaMenjaKupce = new TableColumn<>("mozeDaMenjaKupce");
            mozeDaMenjaKupce.setCellValueFactory(
                    new PropertyValueFactory<>("mozeDaMenjaKupce"));

            TableColumn<Admin, Boolean> mozeDaMenjaZaposlene = new TableColumn<>("mozeDaMenjaZaposlene");
            mozeDaMenjaZaposlene.setCellValueFactory(
                    new PropertyValueFactory<>("mozeDaMenjaZaposlene"));

            tabelaAdmina.getColumns().addAll(korisnickoIme, sifra, mozeDaMenjaAdmine,
                    mozeDaMenjaKupce, mozeDaMenjaZaposlene);

            refresh();

        } else if (number == 1) {
            tabelaLekara = new TableView<>();

            TableColumn<Lekar, String> korisnickoIme = new TableColumn<>("Korisnicko ime");
            korisnickoIme.setCellValueFactory(
                    new PropertyValueFactory<>("korisnickoIme"));

            TableColumn<Lekar, String> sifra = new TableColumn<>("Sifra");
            sifra.setCellValueFactory(
                    new PropertyValueFactory<>("sifra"));

            TableColumn<Lekar, String> ime = new TableColumn<>("Ime");
            ime.setCellValueFactory(
                    new PropertyValueFactory<>("ime"));

            TableColumn<Lekar, String> prezime = new TableColumn<>("Prezime");
            prezime.setCellValueFactory(
                    new PropertyValueFactory<>("prezime"));

            TableColumn<Lekar, String> radnoVreme = new TableColumn<>("Radno vreme");
            radnoVreme.setCellValueFactory(
                    new PropertyValueFactory<>("radnoVreme"));

            tabelaLekara.getColumns().addAll(korisnickoIme, sifra, ime, prezime, radnoVreme);

            refresh();
        } else if (number == 2) {
            tabelaPacijenta = new TableView<>();

            TableColumn<Pacijent, String> korisnickoIme = new TableColumn<>("Korisnicko ime");
            korisnickoIme.setCellValueFactory(
                    new PropertyValueFactory<>("korisnickoIme"));

            TableColumn<Pacijent, String> sifra = new TableColumn<>("Sifra");
            sifra.setCellValueFactory(
                    new PropertyValueFactory<>("sifra"));

            TableColumn<Pacijent, String> ime = new TableColumn<>("Ime");
            ime.setCellValueFactory(
                    new PropertyValueFactory<>("ime"));

            TableColumn<Pacijent, String> prezime = new TableColumn<>("Prezime");
            prezime.setCellValueFactory(
                    new PropertyValueFactory<>("prezime"));

            TableColumn<Pacijent, String> adresa = new TableColumn<>("Adresa");
            adresa.setCellValueFactory(
                    new PropertyValueFactory<>("adresa"));

            TableColumn<Pacijent, String> mobilni = new TableColumn<>("Mobilni");
            mobilni.setCellValueFactory(
                    new PropertyValueFactory<>("mobilni"));

            tabelaPacijenta.getColumns().addAll(korisnickoIme, sifra, ime, prezime, adresa, mobilni);

            //refresh();
        } else if (number == 3) {

            tabelaLekova = new TableView<>();

            TableColumn<Lekovi, String> ime = new TableColumn<>("Ime");
            ime.setCellValueFactory(
                    new PropertyValueFactory<>("ime"));

            TableColumn<Lekovi, Integer> cena = new TableColumn<>("Cena");
            cena.setCellValueFactory(
                    new PropertyValueFactory<>("sastojci"));
            ;

            tabelaLekova.getColumns().addAll(ime, cena);

            refresh();
        } else if (number == 4) {
            tabelaLekovaBolesti = new TableView<>();

            TableColumn<LekoviBolesti, String> ime = new TableColumn<>("Ime");
            ime.setCellValueFactory(
                    new PropertyValueFactory<>("ime"));

            TableColumn<LekoviBolesti, Integer> cena = new TableColumn<>("Cena");
            cena.setCellValueFactory(
                    new PropertyValueFactory<>("cena"));

            tabelaLekovaBolesti.getColumns().addAll(ime, cena);

            refresh();
        } else if (number == 5) {
            tabelaLekovaInfekcije = new TableView<>();
            tabelaLekovaInfekcije.setPrefHeight(150);

            TableColumn<LekoviInfekcije, String> ime = new TableColumn<>("Ime");
            ime.setCellValueFactory(
                    new PropertyValueFactory<>("ime"));

            TableColumn<LekoviInfekcije, Integer> cena = new TableColumn<>("Cena");
            cena.setCellValueFactory(
                    new PropertyValueFactory<>("cena"));

            tabelaLekovaInfekcije.getColumns().addAll(ime, cena);

            refresh();

        } else if (number == 6) {
            tabelaRecepta = new TableView<>();

            TableColumn<Recept, String> vreme = new TableColumn<>("Vreme");
            vreme.setCellValueFactory(
                    new PropertyValueFactory<>("vreme"));

            TableColumn<Recept, String> recept = new TableColumn<>("Recept");
            recept.setCellValueFactory(
                    new PropertyValueFactory<>("Recept"));

            TableColumn<Recept, String> podaciPacijenta = new TableColumn<>("Podaci pacijenta");
            podaciPacijenta.setCellValueFactory(
                    new PropertyValueFactory<>("podaci pacijenta"));

            TableColumn<Recept, Boolean> jeUzet = new TableColumn<>("Da li je lek preuzet");
            jeUzet.setCellValueFactory(
                    new PropertyValueFactory<>("jeUzet"));

            TableColumn<Recept, Integer> cena = new TableColumn<>("Cena");
            cena.setCellValueFactory(
                    new PropertyValueFactory<>("ukupnaCena"));

            tabelaRecepta.getColumns().addAll(vreme, recept, jeUzet, podaciPacijenta, cena);

            refresh();

        }
    }

    public TableView getTable() {
        if (currentTableNumber == 0) {
            return tabelaAdmina;
        } else if (currentTableNumber == 1) {
            return tabelaLekara;
        } else if (currentTableNumber == 2) {
            return tabelaPacijenta;
        } else if (currentTableNumber == 3) {
            return tabelaLekova;
        } else if (currentTableNumber == 4) {
            return tabelaLekovaBolesti;
        } else if (currentTableNumber == 5) {
            return tabelaLekovaInfekcije;
        } else {
            return tabelaRecepta;
        }
    }

    public void refresh() {
        try {
            if (currentTableNumber == 0) {
                database.updateTableView(tabelaAdmina, currentTableNumber);
            } else if (currentTableNumber == 1) {
                database.updateTableView(tabelaLekara, currentTableNumber);
            } else if (currentTableNumber == 2) {
                database.updateTableView(tabelaPacijenta, currentTableNumber);
            } else if (currentTableNumber == 3) {
                database.updateTableView(tabelaLekova, currentTableNumber);
            } else if (currentTableNumber == 4) {
                database.updateTableView(tabelaLekovaBolesti, currentTableNumber);
            } else if (currentTableNumber == 5) {
                database.updateTableView(tabelaLekovaInfekcije, currentTableNumber);
            } else {
                database.updateTableView(tabelaRecepta, currentTableNumber);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
