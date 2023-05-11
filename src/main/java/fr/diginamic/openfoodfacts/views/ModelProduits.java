/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.views;

import fr.diginamic.openfoodfacts.dao.ProduitDAO;
import fr.diginamic.openfoodfacts.model.Marque;
import fr.diginamic.openfoodfacts.model.Produit;
import fr.diginamic.openfoodfacts.utils.ComparatorScore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dmouchagues
 */
public class ModelProduits extends AbstractTableModel{
    
    private ProduitDAO produitDao = ProduitDAO.getInstance();
    private String[] columns;
    private Object data[][];
    
    /**
     *
     * @param rechCategorie selected in JComboBox
     * @param rechMarque selected in JComboBox
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
