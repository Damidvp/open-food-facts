/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.views;

import fr.diginamic.openfoodfacts.dao.CategorieDAO;
import fr.diginamic.openfoodfacts.model.Categorie;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author dmouchagues
 */
public class ModelCategories extends DefaultComboBoxModel<String> {

    private CategorieDAO categorieDao = CategorieDAO.getInstance();
    private List<Categorie> allCategories = categorieDao.getAll();
    
    /**
     * Create a new model for JComboBox of Categorie
     */
    public ModelCategories(){
        this.addElement("***");
        for(Categorie categorie : allCategories){
            this.addElement(categorie.getNom());
        }
    }
    
}
