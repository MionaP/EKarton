package Prikaz;

import Entites.Tabela;
import Entites.Recept;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Baza.Database;
import narucivanjelekova.NarucivanjeLekovaStyle;

public class PrikazLekara implements Runnable {

    private Tabela objTabele;
    private NarucivanjeLekovaStyle style = new NarucivanjeLekovaStyle();

    public PrikazLekara(Stage s, Database db) {

        //tabela recept za prikaz svi recepta i unosa recepta izmene
        objTabele = new Tabela(6);
        TableView<Recept> tabela = objTabele.getTable();

        new Thread(this).start();

        GridPane root = new GridPane();
        root.setStyle(style.getFRAMESTYLE());
        root.setPadding(new Insets(10));
        root.getChildren().add(tabela);

        tabela.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {

                int id = tabela.getSelectionModel().getSelectedItem().getId();

                db.ukloni(id, 6);

                objTabele.refresh();

            }
        });

        Scene scene = new Scene(root);
        s.setScene(scene);

    }

    @Override
    public void run() {

        while (true) {
            objTabele.refresh();

            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrikazLekara.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
