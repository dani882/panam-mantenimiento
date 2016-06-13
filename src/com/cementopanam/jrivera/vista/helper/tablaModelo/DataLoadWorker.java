/**
 * 
 */
package com.cementopanam.jrivera.vista.helper.tablaModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.cementopanam.jrivera.modelo.ConeccionBD;

public class DataLoadWorker extends SwingWorker<TableModel, TableModel> {

    private final JTable table;
    ConeccionBD cbd;
    Connection con = null;

    public DataLoadWorker(JTable table) {
        this.table = table;
    }

    @Override
    protected TableModel doInBackground() throws Exception {
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        Vector<String> columns = new Vector<String>();
        cbd = ConeccionBD.getInstance();
        con = cbd.conectarABaseDatos();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String stmt = "SELECT * FROM mantenimientodb.area";
            ps = con.prepareStatement(stmt);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            //store column names  
            for (int i = 1; i <= columnCount; i++) {
                columns.add(md.getColumnName(i));
            }

            columns.ensureCapacity(columnCount);

            Vector<String> row;
            while (rs.next()) {

                row = new Vector<String>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);

                //Debugging                
            }

            // List.setModel(tableModel);

        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                rs.close();
            } catch (Exception e) {
            }
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        return tableModel;
    }

    @Override
    protected void done() {
        try {
            TableModel model = get();
            table.setModel(model);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }
}