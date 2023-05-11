/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.controller;

import fr.diginamic.openfoodfacts.dao.CategorieDAO;
import fr.diginamic.openfoodfacts.dao.MarqueDAO;
import fr.diginamic.openfoodfacts.dao.ProduitDAO;
import fr.diginamic.openfoodfacts.model.Categorie;
import fr.diginamic.openfoodfacts.model.Marque;
import fr.diginamic.openfoodfacts.model.Produit;
import fr.diginamic.openfoodfacts.utils.ComparatorScore;
import fr.diginamic.openfoodfacts.views.ModelCategories;
import fr.diginamic.openfoodfacts.views.ModelMarques;
import fr.diginamic.openfoodfacts.views.ModelProduits;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class MainController {
    
    private final static MainController INSTANCE = new MainController();
    private final static ProduitDAO produitDao = ProduitDAO.getInstance();
    private final static CategorieDAO categorieDao = CategorieDAO.getInstance();
    private final static MarqueDAO marqueDao = MarqueDAO.getInstance();
    
    private final static List<Categorie> allCategories = categorieDao.getAll();
    private final static List<Marque> allMarques = marqueDao.getAll();
    
    private final static String[] columns = {"ID", "Marques", "Catégorie", "Name", "Score"};
    
    private MainController(){}
    
    /**
     *
     * @return instance of MainController
     */
    public static MainController getInstance(){
        return INSTANCE;
    }
    
    public static ModelCategories createModelCategories(){
        ModelCategories model = new ModelCategories();
        model.addElement("***");
        for(Categorie categorie : allCategories){
            model.addElement(categorie.getNom());
        }
        return model;
    }
    
    public static ModelMarques createModelMarques(){
        ModelMarques model = new ModelMarques();
        model.addElement("***");
        for(Marque marque : allMarques){
            model.addElement(marque.getNom());
        }
        return model;
    }
    
    public static ModelProduits createModelProduits(String categorie, String marque){
        List<Produit> allProduits = produitDao.getAll();
        Collections.sort(allProduits, new ComparatorScore());
        List<Object[]> data = new ArrayList<>();
        for(int i = 0; i<allProduits.size(); i++){
            if(categorie.equals("***") || allProduits.get(i).getCategorie().getNom().equals(categorie)){
                Object[] row = new Object[columns.length];
                String marques = "";
                for(Marque uneMarque : allProduits.get(i).getMarques()){
                    marques += uneMarque.getNom() + ", ";
                }
                if(marques.contains(marque) || marque.equals("***")){
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
        return new ModelProduits(columns, dataObject);
    }
    
}
