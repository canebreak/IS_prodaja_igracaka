package code;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static final int MAX_CON = 5;
    private static final Connection[] bafer = new Connection[MAX_CON];
    private static DB instance;
    private int first = 0, last = 0, free = MAX_CON;

    private DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < MAX_CON; i++) {
                bafer[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/rbp", "root", "");
            }
            System.out.println("proslo jee");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (free == 0) {
            return null;
        }
        free--;
        Connection con = bafer[first];
        first = (first + 1) % MAX_CON;
        return con;
    }

    public synchronized void putConnection(Connection con) {
        if (con == null) {
            return;
        }
        free++;
        bafer[last] = con;
        last = (last + 1) % MAX_CON;
    }
}