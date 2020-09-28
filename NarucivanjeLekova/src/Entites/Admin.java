package Entites;

public class Admin extends Osoba {

    // klasa admin nasledjuje klasu osoba pa samim tim i atribute username i sifra
    // i ima dodatna tri atributa koji oznacavaju privilegije admina
    // u klasi imamo jos konstruktore ,getere i setere
    private boolean mozeDaMenjaAdmine;
    private boolean mozeDaMenjaPacijente;
    private boolean mozeDaMenjaLekare;

    public Admin() {
    }

    public Admin(String korisnickoIme, String sifra) {
        super(korisnickoIme, sifra);
    }

    public Admin(boolean mozeDaMenjaAdmine, boolean mozeDaMenjaPacijente, boolean mozeDaMenjaLekare, String korisnickoIme, String sifra) {
        super(korisnickoIme, sifra);
        this.mozeDaMenjaAdmine = mozeDaMenjaAdmine;
        this.mozeDaMenjaPacijente = mozeDaMenjaPacijente;
        this.mozeDaMenjaLekare = mozeDaMenjaLekare;
    }

    public void setMozeDaMenjaAdmine(boolean mozeDaMenjaAdmine) {
        this.mozeDaMenjaAdmine = mozeDaMenjaAdmine;
    }

    public void setMozeDaMenjaPacijente(boolean mozeDaMenjaPacijente) {
        this.mozeDaMenjaPacijente = mozeDaMenjaPacijente;
    }

    public void setMozeDaMenjaLekare(boolean mozeDaMenjaLekare) {
        this.mozeDaMenjaLekare = mozeDaMenjaLekare;
    }

    public boolean isMozeDaMenjaAdmine() {
        return mozeDaMenjaAdmine;
    }

    public boolean isMozeDaMenjaPacijente() {
        return mozeDaMenjaPacijente;
    }

    public boolean isMozeDaMenjaLekare() {
        return mozeDaMenjaLekare;
    }

    @Override
    public String toString() {
        return "Admin{" + "mozeDaMenjaAdmine=" + mozeDaMenjaAdmine + ", mozeDaMenjaPacijente=" + mozeDaMenjaPacijente + ", mozeDaMenjaLekare=" + mozeDaMenjaLekare + '}';
    }

}
