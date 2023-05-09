/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import fr.diginamic.openfoodfacts.dao.ProduitDAO;
import fr.diginamic.openfoodfacts.model.Produit;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class ProduitService implements IService<Produit> {
    private final static ProduitService INSTANCE = new ProduitService();
    private final static ProduitDAO produitDao = ProduitDAO.getInstance();
    
    private ProduitService(){}
    
    /**
     *
     * @return the instance of ProduitService
     */
    public static ProduitService getInstance(){
        return INSTANCE;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Produit get(long id) {
        Produit produit = null;
        try{
            produit = produitDao.get(id);
        } catch(Exception e){
            System.out.println("Produit inexistant");
        }
        return produit;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Produit> getAll() {
        List<Produit> liste = produitDao.getAll();
        return liste;
    }

    /**
     *
     * @param produit
     * @param params
     */
    @Override
    public void update(Produit produit, String[] params) {
        produitDao.update(produit, params);
    }

    /**
     *
     * @param produit
     */
    @Override
    public void save(Produit produit) {
        produitDao.save(produit);
    }

    /**
     *
     * @param produit
     */
    @Override
    public void delete(Produit produit) {
        produitDao.delete(produit);
    }

    /**
     *
     */
    @Override
    public void closeEM() {
        produitDao.closeEM();
    }
}
