package Entites;

import Entites.Osoba;

public class Pacijent extends Osoba {

    private String ime, prezime, adresa, mobilni;

    public Pacijent() {
    }

    public Pacijent(String ime, String prezime, String adresa, String mobilni, String username, String password) {
        super(username, password);
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.mobilni = mobilni;

    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setMobilni(String mobilni) {
        this.mobilni = mobilni;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getMobilni() {
        return mobilni;
    }

    @Override
    public String toString() {
        return "Pacijent{" + "ime=" + ime + ", prezime=" + prezime + ", adresa=" + adresa + ", mobilni=" + mobilni + '}';
    }

}
