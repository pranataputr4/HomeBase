package com.rpl.homebase.database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SuryaNKT
 */
public class DBUtil {
    private static DBUtil dbutil = null;
    private static Connection conn = null;
    private PreparedStatement preparedStatement;

    private DBUtil(){
        this.dbConnect();
    }

    public static DBUtil getInstance(){
        if (dbutil == null) {
            dbutil = new DBUtil();
        }
        return dbutil;
    }
    
    public static Connection getConnection() {
        return conn;
    }

    private String getJDBC() {
        String urlDB = getUrlDB();
        String JDBC_DRIVER = null;
        if (urlDB != null) {
            JDBC_DRIVER = "jdbc:sqlite:" + urlDB;
        }

        return JDBC_DRIVER;
    }

    private String getUrlDB() {
        String os = System.getProperty("os.name");
//        try {
//            Properties prop = new Properties();
//            prop.load(getClass().getClassLoader().getResourceAsStream("aplikasi.properties"));
//            return prop.getProperty("data.dir");
//        } catch (IOException ex) {
//            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
//        String dbUrl = s+"GluTension.db";
        if(os.contains("Windows") || os.contains("windows")){
            return s+"\\src\\main\\resources\\com\\rpl\\homebase\\database\\homebase.db";
        }
        return s+"/src/main/resources/com/rpl/homebase/database/homebase.db";
    }

    public final void dbConnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(getJDBC());
            System.out.println("Current relative path database is: " + getJDBC());
        }catch (SQLException ex) {
            // In case of any SQLException
//            StackTracer.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        // In case of any SQLException
        // In case of any SQLException
        
    }

    public void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public PreparedStatement prepareStatement(String query, Object... values) throws SQLException {
        preparedStatement = conn.prepareStatement(query);
        this.setValues(preparedStatement, values);
        return preparedStatement;
    }
    
    public PreparedStatement prepareInsertStatement(String query, Object... values) throws SQLException {
        preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        this.setValues(preparedStatement, values);
        return preparedStatement;
    }
    
    private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
        }
    }
    
    public int executeInsert(String query, Object... values) throws SQLException {
        preparedStatement = this.prepareInsertStatement(query, values);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    public void executeUpdate(String query, Object... values) throws SQLException {
        preparedStatement = this.prepareStatement(query, values);
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }
    
    public ResultSet selectFromDatabase(String query, Object... ids) throws SQLException {
        ResultSet resultSet;
        preparedStatement = this.prepareStatement(query, ids);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    
    public int getIntFromDatabase(String query, Object... values) throws SQLException {
        preparedStatement = this.prepareStatement(query, values);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return resultSet.getInt(1);
    }
    
    public List<Integer> getIntsFromDatabase(String query, Object... values) throws SQLException {
        List<Integer> idsFromDatabase = new ArrayList();
        preparedStatement = this.prepareStatement(query, values);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            idsFromDatabase.add(resultSet.getInt(1));
        }
        return idsFromDatabase;
    }
    
    public void deleteFromDatabase(String tableName, String key, Object id) throws SQLException {
        String query = "DELETE FROM ? WHERE ? = ?";
        // preparedStatement.setString(1, tableName);
        // preparedStatement.setString(2, key);
        // preparedStatement.setString(3, id.toString());
        // preparedStatement.executeUpdate(query); // Executing the Query
        this.executeUpdate(query, tableName,key,id.toString());
    }
    
//    public void truncate(String tableName) throws SQLException {
//        String query = String.format("TRUNCATE %s;", tableName);
//        preparedStatement.executeUpdate(query); // Executing the Query
//    }
    
    public final void disconnect(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
//                StackTracer.printStackTrace(ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
//                StackTracer.printStackTrace(ex);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
//                StackTracer.printStackTrace(ex);
            }
        }
    }

//    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException,
//            ClassNotFoundException {
//        Statement stmt = null;
//        ResultSet resultSet = null;
//        CachedRowSet crs = null;
//
//        try {
//            dbConnect();
//            stmt = conn.createStatement();
//            resultSet = stmt.executeQuery(queryStmt);
//            RowSetFactory factory = RowSetProvider.newFactory();
//            crs = factory.createCachedRowSet();
//            crs.populate(resultSet);
//        } catch (SQLException e) {
//            System.out.println("Problem occurred at executeQuery operation : " + e);
//            throw e;
//        } finally {
//            if (resultSet != null) {
//                //Close resultSet
//                resultSet.close();
//            }
//            if (stmt != null) {
//                //Close Statement
//                stmt.close();
//            }
//            //Close connection
//            dbDisconnect();
//        }
//        return crs;
//    }
//
//    public void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
//        Statement stmt = null;
//        try {
//            dbConnect();
//            stmt = conn.createStatement();
//            stmt.executeUpdate(sqlStmt);
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//            dbDisconnect();
//        }
//    }
}
