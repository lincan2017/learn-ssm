package learn.ssm.config;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * @author : Lin Can
 * @date : 2019/1/17 11:44
 */
@Repository
public class DataBaseConnect {

    private final static String JDBC_DRIVER;
    private final static String USERNAME;
    private final static String PASSWORD;
    private final static String DB_URL;

    static {
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            JDBC_DRIVER = properties.getProperty("driver");
            DB_URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");

            Class.forName(JDBC_DRIVER);
        } catch (IOException e) {
            throw new RuntimeException("load jdbc prop exception:", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("获取连接失败");
        }
    }

    public List<Map> executeQuery(String query,String[] labels) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            List<Map> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String,Object> map = new HashMap<>();
                for (String label : labels) {
                    String value = resultSet.getString(label);
                    map.put(label,value);
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(conn);
        }
    }

    public long count (String sql) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            long count = 0;
            if (resultSet.next()) {
                count = resultSet.getLong(1);
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(conn);
        }
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }


    public int executeUpdate(String updateSqlBuilder) {
        Connection conn = null;
        Statement statement = null;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            return statement.executeUpdate(updateSqlBuilder);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(statement);
            close(conn);
        }
    }
}
