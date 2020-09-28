package Baza;

import Entites.LekoviInfekcije;
import Entites.Recept;
import Entites.Pacijent;
import Entites.LekoviBolesti;
import Entites.Lekovi;
import Entites.Lekar;
import Entites.Admin;
import java.sql.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TableView;

public class Database {

    private Connection connection;
    private String url = "jdbc:mysql://localhost/";
    private String username = "root";
    private String password = "";
    private String databaseName = "ekarton";

    private String tabelaAdmin = "admini";
    private String tabelaPacijent = "pacijenti";
    private String tabelaLekar = "lekari";

    private String tabelaLekovi = "lekovi";
    private String tabelaLekoviBolesti = "lekoviBolesti";
    private String tabelaLekoviInfekcije = "lekoviInfekcije";

    private String tabelaRecept = "recepti";

    public Database() throws SQLException, ClassNotFoundException {

        System.out.println(daLiPostojiTakvaOsoba(1, "p", "p"));

      //kreirajBazuAkoNePostoji();
        // kreirajTabeluAdminaAkoNePostoji();
        // kreirajTabeluPacijentaAkoNePostoji();
        //  kreirajTabeluLekaraAkoNePostoji();
        // kreirajTabeluLekoviBolestiAkoNePostoji();
        // kreirajTabeluLekoviInfekcijeAkoNePostoji();
        //kreirajTabeluLekoviAkoNePostoji();
        // kreirajTabeluReceptAkoNepostoji();
        // kreirajGlavnogAdminaAkoNePostoji();
    }

    private void kreirajBazuAkoNePostoji() throws SQLException {
        // metod za kreiranje baze ako ona ne postoji

        connection = DriverManager.getConnection(url + databaseName, username, password);
        System.out.println("Connected");

        try {

            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS\" + databaseName;");
            connection.close();
        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        connection.close();

    }

    // metod za kreiranje tabele admina ukoliko ne postoji

    private void kreirajTabeluAdminaAkoNePostoji() throws ClassNotFoundException, SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS" + tabelaAdmin
                    + "(id INTEGER NOT NULL AUTO_INCREMENT,"
                    + " username VARCHAR(20),"
                    + " password VARCHAR(20),"
                    + "adminPrivilegy TINYINT(1), "
                    + "pacijentPrivilegy TINYINT(1),"
                    + "lekarPrivilegy TINYINT(1),"
                    + "PRIMARY KEY (id))");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    // metod za kreiranje tabele pacijent ukoliko ne postoji

    private void kreirajTabeluPacijentaAkoNePostoji() throws ClassNotFoundException, SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS" + tabelaPacijent
                    + "(id INTEGER NOT NULL AUTO_INCREMENT,"
                    + "ime VARCHAR(35),"
                    + "prezime VARCHAR(35)"
                    + "adresa VARCHAR(35)"
                    + "mobilni VARCHAR(20)"
                    + "username VARCHAR(20)"
                    + "password VARCHAR(20)"
                    + "PRIMARY KEY(id))");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    // metod za kreiranje tabele lekara ukoliko ne postoji

    private void kreirajTabeluLekaraAkoNePostoji() throws ClassNotFoundException, SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS" + tabelaLekar
                    + "(id INTEGER NOT NULL AUTO_INCREMENT,"
                    + " ime VARCHAR(15),"
                    + "prezime VARCHAR(35),"
                    + "radnoVreme VARCHAR(15),"
                    + "username VARCHAR(20),"
                    + "password VARCHAR(20),"
                    + "PRIMARY KEY (id))");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    // metod za kreiranje tabele lekova ukoliko ne postoji

    private void kreirajTabeluLekoviBolestiAkoNePostoji() throws ClassNotFoundException, SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS" + tabelaLekar
                    + "(id INTEGER NOT NULL AUTO_INCREMENT,"
                    + " ime VARCHAR(15),"
                    + "prezime VARCHAR(35),"
                    + "radnoVreme VARCHAR(15),"
                    + "username VARCHAR(20),"
                    + "password VARCHAR(20),"
                    + "PRIMARY KEY (id))");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    // metod za kreiranje tabele lekova infekcije ukoliko ne postoji

    private void kreirajTabeluLekoviInfekcijeAkoNePostoji() throws ClassNotFoundException, SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS " + tabelaLekoviInfekcije
                    + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                    + " ime VARCHAR(35), "
                    + " cena INTEGER (35), "
                    + " PRIMARY KEY (id))");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    // metod za kreiranje tabele lekovi ukoliko ne postoji

    private void kreirajTabeluLekoviAkoNePostoji() throws ClassNotFoundException, SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS " + tabelaLekovi
                    + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                    + " ime VARCHAR(35), "
                    + " cena INTEGER (35), "
                    + " PRIMARY KEY (id))");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    // metod za kreiranje glavnog admin

    private void kreirajGlavnogAdminaAkoNePostoji() throws SQLException {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement st = (Statement) connection.createStatement();
            st.execute("SELECT * FROM " + tabelaAdmin);
            ResultSet rs = st.executeQuery("SELECT * FROM admini");
            int size = 0;
            while (rs.next()) {
                size++;
            }

            if (size == 0) {
                ubaciAdmina(new Admin(true, true, true, "admin", "admin"));
            }
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    // metod za ubacivanje admina u bazu

    public boolean ubaciAdmina(Admin admin) throws SQLException {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);

            String query = "INSERT INTO " + tabelaAdmin + " (username, password,"
                    + " adminPrivilegy, pacijentPrivilegy, lekarPrivilegy) VALUES(?, ?, ?, ?, ?) ";

            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);

            stmt.setString(1, admin.getKorisnickoIme());
            stmt.setString(2, admin.getSifra());
            stmt.setInt(3, admin.isMozeDaMenjaAdmine() ? 1 : 0);
            stmt.setInt(4, admin.isMozeDaMenjaPacijente() ? 1 : 0);
            stmt.setInt(5, admin.isMozeDaMenjaLekare() ? 1 : 0);
            stmt.execute();

            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // metod za unacivanje pacijenta

    public boolean ubaciPacijenta(Pacijent pacijent) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);

            String query = "INSERT INTO " + tabelaPacijent + " (ime, prezime, "
                    + "adresa, mobilni, username, password) VALUES(?, ?, ?, ?, ?, ?) ";

            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);

            stmt.setString(1, pacijent.getIme());
            stmt.setString(2, pacijent.getPrezime());
            stmt.setString(3, pacijent.getAdresa());
            stmt.setString(4, pacijent.getMobilni());
            stmt.setString(5, pacijent.getKorisnickoIme());
            stmt.setString(6, pacijent.getSifra());
            stmt.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;

    }

    // metod za ubacivanje leakra u bazu

    public boolean ubaciLekara(Lekar lekar) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);

            String query = "INSERT INTO " + tabelaLekar + " (ime, prezime, "
                    + "radnoVreme, username, password) VALUES(?, ?, ?, ?, ?) ";

            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);

            stmt.setString(1, lekar.getIme());
            stmt.setString(2, lekar.getPrezime());
            stmt.setString(3, lekar.getRadnoVreme());
            stmt.setString(4, lekar.getKorisnickoIme());
            stmt.setString(5, lekar.getSifra());
            stmt.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;

    }

    // meod za izmenuj vec postojaceg pacijenta

    public boolean izmeniPacijenta(Pacijent pacijent, int id) {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            PreparedStatement st = connection.prepareStatement("UPDATE " + tabelaPacijent + " SET ime = ?, prezime = ?,"
                    + " adresa = ?, mobilni = ?, username = ?, password = ?"
                    + " WHERE id = ");
            st.setString(1, pacijent.getIme());
            st.setString(2, pacijent.getPrezime());
            st.setString(3, pacijent.getAdresa());
            st.setString(4, pacijent.getMobilni());
            st.setString(5, pacijent.getKorisnickoIme());
            st.setString(6, pacijent.getSifra());

            connection.close();

        } catch (SQLException ex) {

            return false;
        }
        return true;
    }

    // metod za izmenu vec postojace admina tj podataaka o njemu

    public boolean izmeniAdmina(Admin admin, int id) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);

            String query = "UPDATE " + tabelaAdmin + " SET username = ?, password = ?,"
                    + " adminPrivilegy = ?, pacijentPrivilegy = ?, lekarPrivilegy = ?"
                    + " WHERE id = " + id;

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getKorisnickoIme());
            preparedStatement.setString(2, admin.getSifra());
            preparedStatement.setInt(3, admin.isMozeDaMenjaAdmine() ? 1 : 0);
            preparedStatement.setInt(4, admin.isMozeDaMenjaPacijente() ? 1 : 0);
            preparedStatement.setInt(5, admin.isMozeDaMenjaLekare() ? 1 : 0);

            preparedStatement.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    // metoda za ubaqcivanje lekova i podataka o lekovima u bazu

    public boolean ubaciLekove(Lekovi lekovi) {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            PreparedStatement st = connection.prepareStatement("INSERT INTO " + tabelaLekovi + " (ime, cena) VALUES(?, ?) ");
            st.setString(1, lekovi.getIme());
            st.setInt(2, lekovi.getCena());

            connection.close();

        } catch (SQLException ex) {

            return false;
        }
        return true;

    }

    public boolean ubaciLekoviBolesti(LekoviBolesti lb) {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            PreparedStatement st = connection.prepareStatement("INSERT INTO " + tabelaLekovi + " (ime, cena) VALUES(?, ?) ");

            st.setString(1, lb.getIme());
            st.setInt(2, lb.getCena());
            st.execute();

            connection.close();

        } catch (SQLException ex) {

            return false;
        }
        return true;
    }

    public boolean ubaciLekoviInfekcije(LekoviInfekcije li) {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            PreparedStatement st = connection.prepareStatement("INSERT INTO " + tabelaLekovi + " (ime, cena) VALUES(?, ?) ");

            st.setString(1, li.getIme());
            st.setInt(2, li.getCena());
            st.execute();

            connection.close();

        } catch (SQLException ex) {

            return false;
        }
        return true;
    }

    public boolean ubaciRecept(Recept recept) {

        try {

            connection = DriverManager.getConnection(url + databaseName, username, password);
            PreparedStatement st = connection.prepareStatement("INSERT INTO " + tabelaRecept + " (narudzbina, ukupnaCena, "
                    + "isDostava, vreme, podaciKupca) VALUES(?, ?, ?, ?, ?) ");

            st.setString(1, recept.getRecept());
            st.setInt(2, recept.getCena());
            st.setInt(3, recept.isJeUzet() ? 1 : 0);
            st.setString(4, recept.getVreme());
            st.setString(5, recept.getPodaciPacijenta());

            st.execute();
            connection.close();

        } catch (SQLException ex) {

            return false;
        }
        return true;
    }

    // metoda za uklanjanje jedne tabele iz baze u zavisnosti od broja tabele
    public boolean ukloni(int id, int kojaTabela) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Connected");
            String imeTabeleZaBrisanje = "";

            if (kojaTabela == 0) {
                imeTabeleZaBrisanje = tabelaAdmin;
            } else if (kojaTabela == 1) {
                imeTabeleZaBrisanje = tabelaPacijent;
            } else if (kojaTabela == 2) {
                imeTabeleZaBrisanje = tabelaLekar;
            } else if (kojaTabela == 3) {
                imeTabeleZaBrisanje = tabelaLekovi;
            } else if (kojaTabela == 4) {
                imeTabeleZaBrisanje = tabelaLekoviBolesti;
            } else if (kojaTabela == 5) {
                imeTabeleZaBrisanje = tabelaLekoviInfekcije;
            } else if (kojaTabela == 6) {
                imeTabeleZaBrisanje = tabelaRecept;
            } else {
                return false;
            }

            String query = "DELETE FROM " + imeTabeleZaBrisanje + " WHERE id = " + id;

            Statement st = (Statement) connection.createStatement();

            st.execute(query);

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean izmeniLekara(Lekar lekar, int id) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Connected");
            String query = "UPDATE " + tabelaLekar + " SET ime = ?, prezime = ?,"
                    + " radnoVreme = ?, username = ?, password = ?"
                    + " WHERE id = " + id;

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, lekar.getIme());
            preparedStatement.setString(2, lekar.getPrezime());
            preparedStatement.setString(3, lekar.getRadnoVreme());
            preparedStatement.setString(4, lekar.getKorisnickoIme());
            preparedStatement.setString(5, lekar.getSifra());

            preparedStatement.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean izmeniLekove(Lekovi lekovi, int id) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Connected");
            String query = "UPDATE " + tabelaLekovi + " SET ime = ?, sastojci = ?,"
                    + " mala = ?, srednja = ?, porodicna = ?"
                    + " WHERE id = " + id;

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, lekovi.getIme());
            preparedStatement.setInt(5, lekovi.getCena());

            preparedStatement.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean izmeniLekoviBolesti(LekoviBolesti lb, int id) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Connected");
            String query = "UPDATE " + tabelaLekoviBolesti + " SET ime = ?, cena = ?"
                    + " WHERE id = " + id;

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, lb.getIme());
            preparedStatement.setInt(2, lb.getCena());

            preparedStatement.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean izmeniLekoviInfekcije(LekoviInfekcije li, int id) {

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Connected");
            String query = "UPDATE " + tabelaLekoviInfekcije + " SET ime = ?, sastojci = ?,"
                    + " cena = ? WHERE id = " + id;

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setString(1, li.getIme());

            preparedStatement.setInt(2, li.getCena());

            preparedStatement.execute();

            connection.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    // metod koji proverava da li postoji takva osoba u bazi
    public boolean daLiPostojiTakvaOsoba(int i, String username, String password) throws SQLException {

        connection = DriverManager.getConnection(url + databaseName, this.username, this.password);
        System.out.println("Connected");
        String imeTabele = "";

        if (i == 0) {
            imeTabele = tabelaAdmin;
        } else if (i == 1) {
            imeTabele = tabelaPacijent;
        } else {
            imeTabele = tabelaLekar;
        }

        String query = "SELECT * FROM " + imeTabele + " WHERE username = '" + username
                + "' AND password = '" + password + "'";

        Statement st = (Statement) connection.createStatement();

        ResultSet rs = st.executeQuery(query);
        int size = 0;

        while (rs.next()) {
            size++;
        }

        connection.close();

        return size >= 1;

    }

    // metod koji vraca osobu koja postoji u bazi

    public Object vratiTuOsobuKojaPostoji(int i, String username, String password) throws SQLException {

        connection = DriverManager.getConnection(url + databaseName, this.username, this.password);
        System.out.println("Connected");
        String imeTabele = "";

        if (i == 0) {
            imeTabele = tabelaAdmin;
        } else if (i == 1) {
            imeTabele = tabelaPacijent;
        } else {
            if (i == 2) {
                imeTabele = tabelaLekar;
            }
        }

        Statement st = connection.createStatement();
        st.execute("SELECT * FROM " + imeTabele + " WHERE username = '" + username
                + "' AND password = '" + password + "'");

        ResultSet rs = st.executeQuery("SELECT * FROM " + imeTabele + " WHERE username = '" + username
                + "' AND password = '" + password + "'");

        while (rs.next()) {

            Object forReturn = null;

            if (i == 0) {
                forReturn = new Admin(rs.getInt("adminPrivilegy") == 1,
                        rs.getInt("lekarPrivilegy") == 1,
                        rs.getInt("pacijentPrivilegy") == 1,
                        rs.getString("username"),
                        rs.getString("password"));

            } else if (i == 1) {
                forReturn = new Pacijent(rs.getString("ime"), rs.getString("prezime"),
                        rs.getString("adresa"), rs.getString("mobilni"),
                        rs.getString("username"), rs.getString("password"));

            } else {
                if (i == 2) {
                    forReturn = new Lekar(rs.getString("ime"),
                            rs.getString("prezime"), rs.getString("radnoVreme"),
                            rs.getString("username"), rs.getString("password"));
                }
            }

            connection.close();
            return forReturn;
        }

        connection.close();

        return null;

    }

    //updatuje tabelu tj prikaz u tabeli ,dodaje nove informacije
    public void updateTableView(TableView tableView, int i) throws SQLException {

        tableView.getItems().clear();

        String imeTabele = "";

        if (i == 0) {
            imeTabele = tabelaAdmin;
        } else if (i == 1) {
            imeTabele = tabelaPacijent;
        } else if (i == 2) {
            imeTabele = tabelaLekar;
        } else if (i == 3) {
            imeTabele = tabelaLekovi;
        } else if (i == 4) {
            imeTabele = tabelaLekoviBolesti;
        } else if (i == 5) {
            imeTabele = tabelaLekoviInfekcije;
        } else if (i == 6) {
            imeTabele = tabelaRecept;
        }

        connection = DriverManager.getConnection(url + databaseName, username, password);
        System.out.println("Connected");
        String query = "SELECT * FROM " + imeTabele;
        if (i == 0) {
            query = "SELECT * FROM " + imeTabele + " WHERE id != 1";
        }
        Statement st = (Statement) connection.createStatement();

        ResultSet rs = st.executeQuery(query);

        if (i == 0) {
            while (rs.next()) {

                Admin admin = new Admin(rs.getInt("adminPrivilegy") == 1, rs.getInt("lekarPrivilegy") == 1, rs.getInt("pacijentPrivilegy") == 1, rs.getString("username"), rs.getString("password"));
                admin.setId(rs.getInt("id"));
                tableView.getItems().add(admin);

            }
        } else if (i == 2) {
            while (rs.next()) {
                Lekar lekar = new Lekar(rs.getString("ime"),
                        rs.getString("prezime"), rs.getString("radnoVreme"),
                        rs.getString("username"), rs.getString("password"));

                lekar.setId(rs.getInt("id"));
                tableView.getItems().add(lekar);

            }
        } else if (i == 1) {
            while (rs.next()) {

                Pacijent pacijent = new Pacijent(rs.getString("ime"), rs.getString("prezime"),
                        rs.getString("adresa"), rs.getString("mobilni"),
                        rs.getString("username"), rs.getString("password"));

                pacijent.setId(rs.getInt("id"));
                tableView.getItems().add(pacijent);

            }
        } else if (i == 3) {
            while (rs.next()) {

                Lekovi lekovi = new Lekovi(rs.getString("ime"), rs.getInt("cena"));
                lekovi.setId(rs.getInt("id"));
                tableView.getItems().add(lekovi);

            }
        } else if (i == 4) {
            while (rs.next()) {

                LekoviBolesti lb = new LekoviBolesti(rs.getString("ime"), rs.getInt("cena"));
                lb.setId(rs.getInt("id"));
                tableView.getItems().add(lb);

            }
        } else if (i == 5) {
            while (rs.next()) {

                LekoviInfekcije li = new LekoviInfekcije(rs.getString("ime"), rs.getInt("cena"));
                li.setId(rs.getInt("id"));
                tableView.getItems().add(li);

            }
        } else if (i == 6) {
            while (rs.next()) {

                Recept recept = new Recept(rs.getString("recept"), rs.getInt("Cena"),
                        rs.getInt("jeUzet") == 1, rs.getString("vreme"), rs.getString("podaciPacijenta"));
                recept.setId(rs.getInt("id"));
                tableView.getItems().add(recept);

            }
        }

        connection.close();

    }

}
