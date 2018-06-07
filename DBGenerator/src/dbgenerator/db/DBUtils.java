/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgenerator.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santhosh
 */
public class DBUtils {
    private static Connection con;
    
    public static Connection connect(String url) {
        try {
            Class.forName(com.mysql.jdbc.Driver.class.getCanonicalName());
            
            con = DriverManager.getConnection(url);
            
            if(con!=null && !con.isClosed()) {
                return con;
            } else
                return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static List<String[]> getTables() throws SQLException {
        final List<String[]> tables2 = new ArrayList<String[]>();
        
        DatabaseMetaData metadata = con.getMetaData();
        
        ResultSet tables = metadata.getTables(null, null, null, new String[] { "TABLE" });
        
        ResultSetMetaData meta = tables.getMetaData();
        
        String[] meta2 = new String[meta.getColumnCount()];
        
        for(int i=0;i<meta.getColumnCount();i++) {
            meta2[i] = meta.getColumnName(i+1);
        }
        
        tables2.add(meta2);
        
        while(tables.next()) {
            String[] values = new String[meta.getColumnCount()];
            
            for(int i=0;i<meta.getColumnCount();i++) {
                values[i] = tables.getString(i+1);
            }
            
            tables2.add(values);
        }
        
        for(int i=0;i<tables2.size();i++)
            System.out.println(Arrays.toString(tables2.get(i)));
        
        return tables2;
    }

    public static ResultSet select(String sql) {
        try {
            final Statement stmt = con.createStatement();
            
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
