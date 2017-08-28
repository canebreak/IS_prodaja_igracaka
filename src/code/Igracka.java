package code;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by cane on 8/27/17.
 */
public class Igracka {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty kolicina = new SimpleIntegerProperty();
    private SimpleIntegerProperty cena = new SimpleIntegerProperty();
    private SimpleStringProperty ime = new SimpleStringProperty();
    private SimpleStringProperty kategorija = new SimpleStringProperty();
    private SimpleStringProperty lokacija = new SimpleStringProperty();


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getKolicina() {
        return kolicina.get();
    }

    public SimpleIntegerProperty kolicinaProperty() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina.set(kolicina);
    }

    public int getCena() {
        return cena.get();
    }

    public SimpleIntegerProperty cenaProperty() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena.set(cena);
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getKategorija() {
        return kategorija.get();
    }

    public SimpleStringProperty kategorijaProperty() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija.set(kategorija);
    }

    public String getLokacija() {
        return lokacija.get();
    }

    public SimpleStringProperty lokacijaProperty() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija.set(lokacija);
    }





    @Override
    public String toString() {
        return "Ime Proizvoda\n- " + this.getIme() + "\n\nKategorija\n- " + this.getKategorija() + "\n\nCena\n" + this.getCena()+",00";
    }
}
