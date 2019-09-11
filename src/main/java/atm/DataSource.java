package atm;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {

    private String filename;

    /**
     * @param filename the name of the customer file
     */
    public DataSource(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the customer numbers and pins
     * and initializes the bank accounts.
     */
    public Map<Integer, Customer> readCustomers() throws IOException {
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
        String[] extension = filename.split("\\.");
        if (extension[1].equals("txt")){
            Scanner in = new Scanner(new FileReader(filename));
            while (in.hasNext()) {
                int number = in.nextInt();
                int pin = in.nextInt();
                double currentBalance = in.nextDouble();
                Customer c = new Customer(number, pin, currentBalance);
                customers.put(c.getCustomerNumber(), c);
            }
            in.close();
        } else if (extension[1].equals("db")){
            try {
                Class.forName("org.sqlite.JDBC");
                String dbURL = "jdbc:sqlite:customers.db";
                Connection conn = DriverManager.getConnection(dbURL);
                String query = "Select * from customers";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int number = resultSet.getInt(1);
                    int pin  = resultSet.getInt(2);
                    double currentBalance = resultSet.getDouble(3);
                    Customer c = new Customer(number, pin, currentBalance);
                    customers.put(c.getCustomerNumber(), c);
                }
                conn.close();
            } catch (Exception e){}
        }
        return customers;
    }
}
