/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.views;

import fr.diginamic.openfoodfacts.controller.MainController;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dmouchagues
 */
public class ModelProduits extends DefaultTableModel{
    
    public ModelProduits(){
        MainController.getInstance().getModelFromListOfProduit();
    }
    
}
