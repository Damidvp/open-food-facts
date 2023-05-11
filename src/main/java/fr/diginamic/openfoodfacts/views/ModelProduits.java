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
    private String[] columns = {"ID", "Marques", "Catégorie", "Name", "Score"};
    private Object data[][];
    
    public ModelProduits(String rechCategorie, String rechMarque){
        data = getData(rechCategorie, rechMarque);
    }
    
    private Object[][] getData(String rechCategorie, String rechMarque){
        List<Produit> allProduits = produitDao.getAll();
        Collections.sort(allProduits, new ComparatorScore());
        List<Object[]> data = new ArrayList<>();
        for(int i = 0; i<allProduits.size(); i++){
            if(rechCategorie.equals("***") || allProduits.get(i).getCategorie().getNom().equals(rechCategorie)){
                Object[] row = new Object[columns.length];
                String marques = "";
                for(Marque uneMarque : allProduits.get(i).getMarques()){
                    marques += uneMarque.getNom() + ", ";
                }
                if(marques.contains(rechMarque) || rechMarque.equals("***")){
                    row[0] = allProduits.get(i).getId();
                    row[1] = marques.trim().substring(0, marques.length()-2);
                    row[2] = allProduits.get(i).getCategorie().getNom();
                    row[3] = allProduits.get(i).getNom();
                    row[4] = allProduits.get(i).getScore();
                    data.add(row);
                }
            } 
        }
        Object[][] dataObject = new Object[data.size()][columns.length];
        for(int i=0; i<data.size(); i++){
            dataObject[i] = data.get(i);
        }
        return dataObject;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int col){
        return columns[col];
    }
    
}
