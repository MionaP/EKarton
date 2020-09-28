/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import Entites.Admin;
import Baza.Database;
import Entites.Lekar;
import Entites.Pacijent;
import Entites.Recept;
import Entites.LekoviBolesti;
import  Entites.LekoviInfekcije;
import Entites.Lekovi;



public class Test1 {
    
     @Test
    public void testUbacivanjeAdmina() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje admina u bazu");
        boolean expected = true;
                
        Database db = new Database();
        Admin admin = new Admin("adminTEST", "test");
        
        assertEquals(expected, db.ubaciAdmina(admin));
    
    }
    
    @Test
    public void testPrevelikoImeAdmina() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje admina u bazu preveliko ime greska");
        boolean expected = true;
                
        Database db = new Database();
        Admin admin = new Admin("adminTESTtttttttttttttttttttttttt", "test");
        
        assertNotSame(expected, db.ubaciAdmina(admin));
    
    }
    
    @Test
    public void testUbacivanjePacijenta() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje pacijent u bazu");
        boolean expected = true;
                
        Database db = new Database();
        Pacijent pacijent = new Pacijent("test", "test", "test", "test", "test", "test");
        
        assertEquals(expected, db.ubaciPacijenta(pacijent));
    
    }
    
    @Test
    public void testUbacivanjePacijentaPrevelikoIme() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje pacijenta u bazu");
        boolean expected = true;
                
        Database db = new Database();
        Pacijent pacijent = new Pacijent("testtttttttttttttttttttttttttttttttttttttt", 
                "test", "test", "test", "test", "test");
        
        assertNotSame(expected, db.ubaciPacijenta(pacijent));
    
    }
    
    @Test
    public void testUbacivanjeLekara() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje lekara u bazu");
        boolean expected = true;
                
        Database db = new Database();
        Lekar lekar = new Lekar("test", "tes", "test", "zaposlenTest", "test");
            
        assertEquals(expected, db.ubaciLekara(lekar));
    
    }
    
    @Test
    public void testUbacivanjeLekova() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje lekova u bazu");
        boolean expected = true;
                
        Database db = new Database();
        Lekovi lekovi = new Lekovi("test", 1);
        
        assertEquals(expected, db.ubaciLekove(lekovi));
    
    }
    
    @Test
    public void testUbacivanjeLekovaInfekcije() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje lekova za infekcije u bazu");
        boolean expected = true;
                
        Database db = new Database();
        LekoviInfekcije li = new LekoviInfekcije("test", 1);
        
        assertEquals(expected, db.ubaciLekoviInfekcije(li));
    
    }
    
    @Test
    public void testUbacivanjeLekoviBolesti() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje lekova za teske bolesti u bazu");
        boolean expected = true;
                
        Database db = new Database();
        LekoviBolesti lb = new LekoviBolesti("test", 1);
        
        assertEquals(expected, db.ubaciLekoviBolesti(lb));
    
    }
    
    @Test
    public void testUbacivanjaRecepta() throws SQLException, ClassNotFoundException {
        System.out.println("ubacivanje racuna u bazu");
        boolean expected = true;
                
        Database db = new Database();
        Recept recept = new Recept("test", 1, true, "test", "test");
        
        assertEquals(expected, db.ubaciRecept(recept));
    
    }
    
    @Test
    public void testProvereDaLiAdminPostojiUBazi() throws SQLException, ClassNotFoundException {
        System.out.println("da li admin postoji");
        boolean expected = true;
                
        Database db = new Database();
        
        assertEquals(expected, db.daLiPostojiTakvaOsoba(0, "admin", "admin"));
    }
    
    @Test
    public void testVracanjeAdminaIzBaze() throws SQLException, ClassNotFoundException {
        System.out.println("vrati admina iz baze");
        boolean expected = true;
                
        Database db = new Database();
        
        assertNotNull(db.vratiTuOsobuKojaPostoji(0, "admin", "admin"));
    
    }
}
