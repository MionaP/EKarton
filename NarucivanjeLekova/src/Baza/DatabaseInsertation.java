package Baza;

import Baza.Database;
import Entites.LekoviInfekcije;
import Entites.Pacijent;
import Entites.LekoviBolesti;
import Entites.Lekovi;
import Entites.Lekar;
import java.sql.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TableView;

public class DatabaseInsertation {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new DatabaseInsertation();
    }
    
    
    //metod koji sam dodaje u bazu informacije tj podatke
    public DatabaseInsertation() throws SQLException, ClassNotFoundException {
        Database db = new Database();

        db.ubaciLekove(new Lekovi("Rapten-k", 80));
        db.ubaciLekove(new Lekovi("Kafetin", 650));

        db.ubaciPacijenta(new Pacijent("Marko", "Markovic", "Adresa", "06025353", "Marko", "123"));

        db.ubaciLekara(new Lekar("Milan", "Milanovic", "14-22", "zaposlenMilan", "123"));
        db.ubaciLekara(new Lekar("Srdjan", "Sretenovic", "08-14", "zaposlenSrdjan", "12345"));

        db.ubaciLekoviBolesti(new LekoviBolesti("Paracetamol", 75));
        db.ubaciLekoviBolesti(new LekoviBolesti("Kliacil", 75));
        db.ubaciLekoviBolesti(new LekoviBolesti("Brufen", 75));

        db.ubaciLekoviInfekcije(new LekoviInfekcije("Trial", 75));

    }

}
