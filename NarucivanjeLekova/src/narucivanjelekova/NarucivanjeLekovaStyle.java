package narucivanjelekova;

public class NarucivanjeLekovaStyle {

    private final String TITLE;
    private final String FRAME_STYLE;
    private final String TEXT;
    private final String LIST_BORDER;
    private final String BUTTON;
    private final String Nazad;
    private final String ORDER;
    private final String BORDER;

    /*
     * U konstruktoru klase svaka promenljiva dobija svoju vrednost, 
     * tj.tekst koji sadrzi CSS stil za izgled aplikacije
     */
    public NarucivanjeLekovaStyle() {
        TITLE = "-fx-font: 22px Italic;\n"
                + "-fx-text-fill: #AB2E6F;\n"
                + "-fx-font-weight: bold;";
        FRAME_STYLE = "-fx-background-color: #AB2E6F;\n";
        TEXT = "-fx-font: 15px Helvetica;\n"
                + "-fx-text-fill: #AB2E6F;\n";
        BUTTON = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"
                + "-fx-background-radius: 8;"
                + "-fx-background-insets: 0,1,1;"
                + "-fx-font: 16px Italic;\n"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #AB2E6F;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 ); ";
        LIST_BORDER = "-fx-background-color: #DE90B7;\n"
                + "-fx-font: 14px Helvetica;"
                + "-fx-effect: dropshadow( one-pass-box , white , -1, -1 , -1 , -1 );";
        Nazad = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%);"
                + "-fx-background-radius: 8,7,6;"
                + "-fx-background-insets: 0,1,2;"
                + "-fx-text-fill: AB2E6F;"
                + "-fx-font: 10px Helvetica;"
                + "-fx-font-weight: bold;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );";
        ORDER = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"
                + "-fx-background-radius: 10;"
                + "-fx-background-insets: 0,1,1;"
                + "-fx-font: 16px Helvetica;\n"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #AB2E6F;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 ); ";
        BORDER = "-fx-border-style: solid;"
                + "-fx-border-color: #B8406C;"
                + "-fx-border-width: 1px;"
                + "-fx-hgap: 10;"
                + "-fx-vgap: 10;";
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getBUTTON() {
        return BUTTON;
    }

    public String getFRAMESTYLE() {
        return FRAME_STYLE;
    }

    public String getTEXT() {
        return TEXT;
    }

    public String getLIST_BORDER() {
        return LIST_BORDER;
    }

    public String getNazad() {
        return Nazad;
    }

    public String getORDER() {
        return ORDER;
    }

    public String getBORDER() {
        return BORDER;
    }
}
