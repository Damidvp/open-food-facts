/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.controller;

import fr.diginamic.openfoodfacts.dao.ProduitDAO;
import fr.diginamic.openfoodfacts.model.Produit;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author dmouchagues
 */
public class MainController {
    
    private final static MainController INSTANCE = new MainController();
    private ProduitDAO produitDao = ProduitDAO.getInstance();
    
    private MainController(){}
    
    /**
     *
     * @return instance of MainController
     */
    public static MainController getInstance(){
        return INSTANCE;
    }
    
}
