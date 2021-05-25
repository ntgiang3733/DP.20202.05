package entity.db;

import utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

// singleton: do nghiep vu quy dinh
public class AIMSDB {

    private static final Logger LOGGER = Utils.getLogger(Connection.class.getName());
    private static AIMSDB instance;
    private static Connection connect;

    private AIMSDB() {
    }

    public static AIMSDB getInstance() {
        if (instance == null) {
            instance = new AIMSDB();
        }
        return instance;
    }

    // TODO: refactor Utils -> limit connections
    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/assets/db/aims.db";
            connect = DriverManager.getConnection(url);
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return connect;
    }


    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}