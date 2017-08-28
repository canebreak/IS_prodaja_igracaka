package code;

/**
 * Created by cane on 8/25/17.
 */
public class Radnik {
    private int id, brojPrijava;
    private String lokacija;
    private String password;

    public Radnik() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
