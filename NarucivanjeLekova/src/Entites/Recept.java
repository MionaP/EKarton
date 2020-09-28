package Entites;

public class Recept {

    private String recept;
    private int cena;
    private boolean jeUzet;
    private String vreme;
    private String podaciPacijenta;

    public Recept(String recept, int cena, boolean jeUzet, String vreme, String podaciPacijenta) {
        this.recept = recept;
        this.cena = cena;
        this.jeUzet = jeUzet;
        this.vreme = vreme;
        this.podaciPacijenta = podaciPacijenta;
    }
    private int id;

    public Recept() {
    }

    public Recept(String recept, int cena, boolean jeUzet, String vreme, String podaciPacijenta, int id) {
        this.recept = recept;
        this.cena = cena;
        this.jeUzet = jeUzet;
        this.vreme = vreme;
        this.podaciPacijenta = podaciPacijenta;
        this.id = id;
    }

    public void setRecept(String recept) {
        this.recept = recept;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setJeUzet(boolean jeUzet) {
        this.jeUzet = jeUzet;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public void setPodaciPacijenta(String podaciPacijenta) {
        this.podaciPacijenta = podaciPacijenta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecept() {
        return recept;
    }

    public int getCena() {
        return cena;
    }

    public boolean isJeUzet() {
        return jeUzet;
    }

    public String getVreme() {
        return vreme;
    }

    public String getPodaciPacijenta() {
        return podaciPacijenta;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Recept{" + "recept=" + recept + ", cena=" + cena + ", jeUzet=" + jeUzet + ", vreme=" + vreme + ", podaciPacijenta=" + podaciPacijenta + ", id=" + id + '}';
    }

}
