/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.views;

import fr.diginamic.openfoodfacts.dao.MarqueDAO;
import fr.diginamic.openfoodfacts.model.Marque;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author zdamy
 */
public class ModelMarques extends DefaultComboBoxModel<String> {
    private MarqueDAO marqueDao = MarqueDAO.getInstance();
    private List<Marque> allMarques = marqueDao.getAll();
    
    public ModelMarques(){
        this.addElement("***");
        for(Marque marque : allMarques){
            this.addElement(marque.getNom());
        }
    }
}
