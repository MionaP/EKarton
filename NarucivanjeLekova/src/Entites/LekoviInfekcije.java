package Entites;

public class LekoviInfekcije {

    private int id;
    private String ime;
    private int cena;

    public LekoviInfekcije(String ime, int cena) {
        this.ime = ime;
        this.cena = cena;
    }

    public LekoviInfekcije() {
    }

    public LekoviInfekcije(int id, String ime, int cena) {
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
