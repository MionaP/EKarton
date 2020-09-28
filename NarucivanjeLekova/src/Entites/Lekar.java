package Entites;

public class Lekar extends Osoba {
    
    // pored korisnickog imena i prezimena koje naskedjuje klasa lekar ima jos tri atributa 
    // getere i setere i konstruktore

    private String ime, prezime, radnoVreme;

    public Lekar() {
    }

    public Lekar(String ime, String prezime, String radnoVreme, String korisnickoIme, String sifra) {
        super(korisnickoIme, sifra);
        this.ime = ime;
        this.prezime = prezime;
        this.radnoVreme = radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    @Override
    public String toString() {
        return "Lekar{" + "ime=" + ime + ", prezime=" + prezime + ", radnoVreme=" + radnoVreme + '}';
    }

}
