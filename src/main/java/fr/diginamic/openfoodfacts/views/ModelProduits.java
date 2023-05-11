/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.views;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dmouchagues
 */
public class ModelProduits extends AbstractTableModel{
    
    private final String[] columns;
    private final Object data[][];
    
    /**
     *
     * @param columns of Model
     * @param data of Model
     */
    public ModelProduits(String[] columns, Object[][] data){
        super();
        this.columns = columns;
        this.data = data;
    }
    /**
     *
     * @return number of rows in the model
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     *
     * @return number of columns in the model
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     *
     * @param rowIndex of value searched
     * @param columnIndex of value searched
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    /**
     *
     * @param col index
     * @return name of column
     */
    @Override
    public String getColumnName(int col){
        return columns[col];
    }
    
}
