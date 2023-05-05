/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import fr.diginamic.openfoodfacts.dao.IngredientDAO;
import fr.diginamic.openfoodfacts.model.Ingredient;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class IngredientService implements IService<Ingredient> {
    
    private final static IngredientService INSTANCE = new IngredientService();
    private final static IngredientDAO ingredientDao = IngredientDAO.getInstance().getInstance();
    
    private IngredientService(){}
    
    public static IngredientService getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Ingredient get(long id) {
        Ingredient ingredient = null;
        try{
            ingredient = ingredientDao.get(id);
        } catch(Exception e){
            System.out.println("Ingrédient inexistant");
        }
        return ingredient;
    }
    
    public Ingredient getByName(String name){
        Ingredient ingredient = null;
        try{
            ingredient = ingredientDao.getByName(name);
        } catch(Exception e){
            System.out.println("Ingrédient inexistant");
        }
        return ingredient;
    }

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> liste = ingredientDao.getAll();
        return liste;
    }

    @Override
    public void update(Ingredient ingredient, String[] params) {
        ingredientDao.update(ingredient, params);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientDao.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientDao.delete(ingredient);
    }

    @Override
    public void closeEM() {
        ingredientDao.closeEM();
    }
    
}
