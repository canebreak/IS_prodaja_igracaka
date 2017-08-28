package code;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("IS za prodaju igracaka");

        Date today = Calendar.getInstance().getTime();
        DB db = DB.getInstance();
        ArrayList<String> listaRadnikaKojiSuPrijaviliProbleme = new ArrayList<>();
        Radnik radnik = new Radnik();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(today);
        System.out.println(date);
        Igracka igracka = new Igracka();

        Label username = new Label("ID radnika");
        TextField usernameTxt = new TextField();
        usernameTxt.setMaxWidth(100);
        Label pass = new Label("Sifra radnika");
        TextField passTxt = new TextField();
        passTxt.setMaxWidth(100);

        Button login = new Button("Uloguj se kao radnik");
        Button basicUser = new Button("Nastavi kao korisnik");


        VBox box = new VBox();

        box.getChildren().addAll(username, usernameTxt, pass, passTxt, login, basicUser);
        box.setSpacing(10);

        Scene scene = new Scene(box, 400, 375);
        window.setScene(scene);
        VBox ulogovanVBox = new VBox(10);
        Scene ulogovanUser = new Scene(ulogovanVBox, 400, 375);

        login.setOnAction(event -> {

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                con = db.getConnection();
                stmt = con.createStatement();
                String upit = "SELECT * FROM Zaposleni WHERE id_radnika='" + usernameTxt.getText() + "' AND password='" + passTxt.getText() + "';";
                rs = stmt.executeQuery(upit);
                if (rs.next()) {
                    radnik.setId(Integer.parseInt(usernameTxt.getText()));
                    radnik.setLokacija(rs.getString("lokacija"));
                    radnik.setPassword(passTxt.getText());

                    System.out.println(radnik.getLokacija());
                    System.out.println("kredencijali dobri");

                    window.setScene(ulogovanUser);
                } else {
                    System.out.println("Losi kredencijali");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });


        //scene for logged starts here


        TextArea opisProblema = new TextArea();
        opisProblema.setMinHeight(200);
        opisProblema.setMinWidth(200);
        opisProblema.setMaxWidth(250);
        opisProblema.setPromptText("Opis problema sa strujom, kasom, tehnikom...");

        Button posaljiProblem = new Button("Posalji problem");


        posaljiProblem.setOnAction(event -> {

            Connection con = null;
            Statement stmt = null;
            try {
                con = db.getConnection();
                stmt = con.createStatement();
                String upit = "INSERT INTO `Problemi`(`id_tiketa`, `id_prijavnika`, `datum`, `opis`, `id_lokacije`) VALUES (NULL, " + radnik.getId() + ",'" + date + "', '" + opisProblema.getText() + "', (" +
                        "SELECT id_lokacije from rbp.Lokacija where grad='" + radnik.getLokacija() + "'))";
                stmt.executeUpdate(upit);
                System.out.println("query prosao");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspesno prijavljen problem");
                alert.setHeaderText("Uspesno prijavljeno sluzbi u gradu " + radnik.getLokacija());
                alert.setContentText("Hvala na prijavi!");
                alert.showAndWait();

                window.setScene(scene);
                usernameTxt.setText("");
                passTxt.setText("");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });

        ulogovanVBox.getChildren().addAll(opisProblema, posaljiProblem);


        //scene for logged ends here


        //scene for basic user starts here
        VBox vBoxZaObicneKorisnike = new VBox(20);

        Label opis = new Label("Ukoliko zelite da vidite radnike iz posebnog grada, unesite u polje");

        TextField poljeZaGrad = new TextField();
        poljeZaGrad.setMaxWidth(100);
        poljeZaGrad.setPromptText("Polje za grad...");
        Button radniciProblemi = new Button("Prikazi radnike koji su prijavili probleme");

        Button najskupljaIgrackaButton = new Button("Prikazi najskuplju igracku");
        Button najjeftinijaIgrackaButton = new Button("Prikazi najjeftiniju igracku");

        Button prodajKupiIgracku = new Button("Prodaj/kupi igracku");
        vBoxZaObicneKorisnike.getChildren().addAll(opis, poljeZaGrad, radniciProblemi, najskupljaIgrackaButton, najjeftinijaIgrackaButton, prodajKupiIgracku);

        Scene scenaZaObicneKorisnike = new Scene(vBoxZaObicneKorisnike, 450, 350);
        //scene for basic user ends here

        // Scene for most expensive toy starts here

        TableView table = new TableView();


        Label label = new Label("Info o najskupljoj igracki");
        table.setEditable(false);
        table.setMaxWidth(500);
        TableColumn idIgracke = new TableColumn("ID Igracke");
        idIgracke.setMinWidth(100);
        TableColumn ime = new TableColumn("Ime");
        TableColumn kategorija = new TableColumn("Kategorija");
        TableColumn kolicina = new TableColumn("Kolicina");
        TableColumn cena = new TableColumn("Cena");
        TableColumn lokacija = new TableColumn("Lokacija");

        table.getColumns().addAll(idIgracke, ime, kategorija, kolicina, cena, lokacija);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label, table);

        Scene sceneZaSkupeIJeftine = new Scene(vbox, 520, 400);

        //scene for most expensive toy ends here


        // scene for sell/buy products starts here


        call dodajRadnika

        //
        // TASKS:
        //1. zavrsi DodajRadnika dugme...
        //2. Prodaj/Kupi proizvod
        //F-ja dodaj proizvod...
        //Pogled svi radnici koji su zaposleni u Beogradu i imaju prijave problema



        // scene for sell/buy products ends here

        basicUser.setOnAction(event -> {
            window.setScene(scenaZaObicneKorisnike);
        });
        radniciProblemi.setOnAction(event -> {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            String text = poljeZaGrad.getText();
            String upit = "";
            try {
                con = DB.getInstance().getConnection();
                stmt = con.createStatement();
                if (text.length() > 3) {
                    upit = "SELECT ime from Zaposleni WHERE id_radnika IN (SELECT id_prijavnika from Problemi)";
                } else
                    upit = "SELECT ime from Zaposleni WHERE lokacija like '%" + text + "%' AND id_radnika IN (SELECT id_prijavnika from Problemi)";
                rs = stmt.executeQuery(upit);
                while (rs.next()) {
                    listaRadnikaKojiSuPrijaviliProbleme.add(rs.getString("ime"));
                }
                for (int i = 0; i < listaRadnikaKojiSuPrijaviliProbleme.size(); i++) {
                    System.out.println(listaRadnikaKojiSuPrijaviliProbleme.get(i));
                }
                listaRadnikaKojiSuPrijaviliProbleme.clear();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        });
        najskupljaIgrackaButton.setOnAction(event -> {

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                con = DB.getInstance().getConnection();
                stmt = con.createStatement();
                String upit = "SELECT * FROM Igracke WHERE cena=(select max(cena) from Igracke);";
                rs = stmt.executeQuery(upit);
                if (rs.next()) {
                    igracka.setCena(rs.getInt("cena"));
                    igracka.setId(rs.getInt("id_igracke"));
                    igracka.setIme(rs.getString("ime"));
                    igracka.setKolicina(rs.getInt("kolicina"));
                    igracka.setKategorija(rs.getString("kategorija"));
                    igracka.setLokacija(rs.getString("lokacija"));
                    ime.setUserData(igracka.getIme());

                    window.setScene(sceneZaSkupeIJeftine);
                }
                System.out.println(igracka);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        });
        najjeftinijaIgrackaButton.setOnAction(event -> {

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                con = DB.getInstance().getConnection();
                stmt = con.createStatement();
                String upit = "SELECT * FROM Igracke WHERE cena=(select min(cena) from Igracke);";
                rs = stmt.executeQuery(upit);
                if (rs.next()) {
                    igracka.setCena(rs.getInt("cena"));
                    igracka.setId(rs.getInt("id_igracke"));
                    igracka.setIme(rs.getString("ime"));
                    igracka.setKolicina(rs.getInt("kolicina"));
                    igracka.setKategorija(rs.getString("kategorija"));
                    igracka.setLokacija(rs.getString("lokacija"));
                    label.setText("Info o najjeftinijoj igracki");
                    window.setScene(sceneZaSkupeIJeftine);

                }
                System.out.println(igracka);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });

        window.show();


    }


}
