package Entites;

public class Lekovi {

    // klasa lekovi ima tri atributa geter i seter metodu
    private int id;
    private String ime;
    private int cena;

    public Lekovi(String ime, int cena) {
        this.ime = ime;
        this.cena = cena;
    }

    public Lekovi() {
    }

    public Lekovi(int id, String ime, int cena) {
        this.id = id;
        this.ime = ime;
        this.cena = cena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return "LekoviOcno{" + "id=" + id + ", ime=" + ime + ", cena=" + cena + '}';
    }

}
