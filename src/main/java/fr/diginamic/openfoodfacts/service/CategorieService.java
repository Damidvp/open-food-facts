/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import fr.diginamic.openfoodfacts.dao.CategorieDAO;
import fr.diginamic.openfoodfacts.model.Categorie;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class CategorieService implements IService<Categorie>{
    
    private final static CategorieService INSTANCE = new CategorieService();
    private final static CategorieDAO categorieDao = CategorieDAO.getInstance();
            
    private CategorieService(){}
    
    public static CategorieService getInstance(){
        return INSTANCE;
    }

    @Override
    public Categorie get(long id) {
        Categorie categorie = null;
        try{
            categorie = categorieDao.get(id);
            categorieDao.closeEM();
        } catch(Exception e){
            System.out.println("Catégorie inexistante");
        }
        return categorie;
    }

    @Override
    public List<Categorie> getAll() {
        List<Categorie> liste = categorieDao.getAll();
        categorieDao.closeEM();
        return liste;
    }

    @Override
    public void update(Categorie categorie, String[] params) {
        categorieDao.update(categorie, params);
        categorieDao.closeEM();
    }

    @Override
    public void save(Categorie categorie) {
        categorieDao.save(categorie);
        categorieDao.closeEM();
    }

    @Override
    public void delete(Categorie categorie) {
        categorieDao.delete(categorie);
        categorieDao.closeEM();
    }
    
    
}
