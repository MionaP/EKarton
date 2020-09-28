package Entites;

public class Osoba {

    private int id;
    private String korisnickoIme, sifra;

    public Osoba() {
    }

    public Osoba(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @Override
    public String toString() {
        return "Osoba{" + "korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + '}';
    }

}
