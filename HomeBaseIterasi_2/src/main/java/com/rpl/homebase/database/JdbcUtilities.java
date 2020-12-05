package com.rpl.homebase.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SuryaNKT
 */
public class JdbcUtilities {
    private List<String> columnNames;
    private List<String> columnLabels;
    private int rowCount;
    private int columnCount;
    /**
     * The connection to the database
     */
    protected Connection connection;
    /**
     * The SQL Query String
     */
    protected String query;
    /**
     * The MySqlJdbcUtilities object. <br/>This object acts as an interface b/w
     * the underlying Database and the layers above this Class
     */
    protected DBUtil jdbcUtilities;

    /**
     * The Constructor for JdbcUtilities class Instantiates some protected and
     * private fields of this class.
     *
     * @param jdbcParameters
     */
    public JdbcUtilities(){
        columnNames = new ArrayList();
        columnLabels = new ArrayList();
        jdbcUtilities = DBUtil.getInstance();
        connection = DBUtil.getConnection();
        query = null;
    }

    /**
     * Retrieves Original Column Names of ResultSet which are given in the
     * SELECT clause. <br/>Warning: Call setResultSetMetadata(resultSet) before
     * calling this method. Else null would be returned
     *
     * @return List of Column Names
     */
    public List<String> getColumnNames() {
        return columnNames;
    }

    /**
     * Gets number of rows from the given ResultSet. <br/>Warning: Call
     * setResultSetMetadata(resultSet) before calling this method. Else 0 would
     * be returned
     *
     * @return Number of rows in the given ResultSet
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Gets number of columns from the given ResultSet. <br/>Warning: Call
     * setResultSetMetadata(resultSet) before calling this method. Else 0 would
     * be returned
     *
     * @return Number of rows in the given ResultSet
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Extracts Column Names and Column Labels from the Database. This method
     * does not close the resultSet. The caller of this method is responsible
     * for closing the ResultSet. This method also calls the
     * <code>beforeFirst()</code> method of the
     * <code>ResultSet</code>.<br/>Note: This method must be called before
     * calling any getter method from this class.
     *
     * @param resultSet The resultSet object from which the column names and
     * labels are to be extracted
     * @throws SQLException If the ResultSet is already closed or null
     * @see ResultSet#beforeFirst()
     */
    protected void setResultSetMetadata(ResultSet resultSet) throws SQLException {
        if (resultSet == null || resultSet.isClosed()) {
            throw new SQLException();
        }
        try {
            resultSet.beforeFirst();
            ResultSetMetaData md = resultSet.getMetaData();
            columnCount = md.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(md.getColumnName(i));
                columnLabels.add(md.getColumnLabel(i));
            }
            resultSet.afterLast();
            this.rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException ex) {
//            StackTracer.printStackTrace(ex);
        }
    }

    /**
     * Retrieves Column Labels of ResultSet which are given in the SELECT Clause
     * using AS Clause. If AS clause is not used, the output of this method
     * would be same as of getColumnNames(). <br/>Warning: Call
     * setResultSetMetadata(resultSet) before calling this method. Else null
     * would be returned
     *
     * @return List of Column Labels
     */
    public List<String> getColumnLabels() {
        return columnLabels;
    }

    /**
     * Gets a CSV-format String from the given ResultSet. This method internally
     * calls
     * <code>setResultSetMetadata</code> method.<br/>Note: This method does not
     * close the resultSet. The caller of this method is responsible for closing
     * the ResultSet.
     * <code></code>
     *
     * @param resultSet The ResultSet object from which CSV formatted String is
     * to be returned
     * @param columnSeparator The column or field separator. Usually a
     * semi-colon (;) or comma (,) is used as a separator
     * @param includeColumnNames A flag indicating whether column names should
     * be included in the output String or not
     * @return The data from ResultSet in a CSV-format
     * @throws SQLException If the ResultSet is already closed or null
     */
    public String toCsvString(ResultSet resultSet, char columnSeparator, boolean includeColumnNames) throws SQLException {
        if (resultSet == null || resultSet.isClosed()) {
            throw new SQLException();
        }
        resultSet.beforeFirst();
        this.setResultSetMetadata(resultSet);
        int _columnCount = this.getColumnCount();
        String _columnNames;
        String lineSeperator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        if (includeColumnNames) {
            List<String> cols = this.getColumnNames();
            _columnNames = cols.toString().replaceAll(" ", "").replaceAll("\\[", "").replaceAll("\\]", "")
                    .replaceAll(",", String.valueOf(columnSeparator));
            sb = new StringBuilder(_columnNames);
            sb.append(lineSeperator);
        }
        while (resultSet.next()) {
            for (int i = 1; i <= _columnCount; i++) {
                sb.append(resultSet.getString(i));
                if (i != _columnCount) {
                    sb.append(columnSeparator);
                }
            }
            sb.append(lineSeperator);
        }
        return sb.toString();
    }

    /**
     * Saves the ResultSet to a file in a CSV format. This method internally
     * calls
     * <code>toCsvString</code> method.<br/>Note: This method does not close the
     * resultSet. The caller of this method is responsible for closing the
     * ResultSet.
     *
     * @param resultSet The ResultSet object from which CSV formatted String is
     * to be returned
     * @param columnSeparator The column or field separator. Usually a
     * semi-colon (;) or comma (,) is used as a separator
     * @param includeColumnNames A flag indicating whether column names should
     * be included in the output String or not
     * @return The newly created CSV File
     * @param filePath The destination path (including the file name and file
     * type) where file has to written
     * @return true If file is written successfully
     * @throws SQLException If the ResultSet is already closed or null
     * @throws IOException If an I/O error occurs
     * @see #toCsvString(java.sql.ResultSet, char, boolean)
     */
    public File toCsvFile(ResultSet resultSet, char columnSeparator, boolean includeColumnNames, String filePath)
            throws SQLException, IOException {
        String csvString = this.toCsvString(resultSet, columnSeparator, includeColumnNames);
        File file = new File(filePath);
        // write to file
        Writer writer = new FileWriter(file);
        try {
            writer.write(csvString);
        } finally {
            writer.flush();
            writer.close();
        }
        return file;
    }

    /**
     * Saves the ResultSet to a file in a CSV format. This overloaded function
     * takes the file object instead of the filePath.<br/>Note: This method
     * internally calls
     * <code>toCsvString</code> method.<br/>This method does not close the
     * resultSet. The caller of this method is responsible for closing the
     * ResultSet.
     *
     * @param resultSet The ResultSet object from which CSV formatted String is
     * to be returned
     * @param columnSeparator The column or field separator. Usually a
     * semi-colon (;) or comma (,) is used as a separator
     * @param includeColumnNames A flag indicating whether column names should
     * be included in the output String or not
     * @param file The file in which CSV String has to be written
     * @return The CSV File
     * @throws SQLException If the ResultSet is already closed or null
     * @throws IOException If an I/O error occurs
     * @see #toCsvString(java.sql.ResultSet, char, boolean)
     */
    public File toCsvFile(ResultSet resultSet, char columnSeparator, boolean includeColumnNames, File file)
            throws SQLException, IOException {
        String csvString = this.toCsvString(resultSet, columnSeparator, includeColumnNames);
        // write to file
        Writer writer = new FileWriter(file);
        try {
            writer.write(csvString);
        } finally {
            writer.flush();
            writer.close();
        }
        return file;
    }
}
