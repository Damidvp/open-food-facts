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
 * @author dmouchagues
 */
public class ModelMarques extends DefaultComboBoxModel<String> {
    
    /**
     * Create a model for JComboBox of Marque
     */
    public ModelMarques(){
        super();
    }
}
