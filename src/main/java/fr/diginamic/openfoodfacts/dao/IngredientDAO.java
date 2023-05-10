/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import fr.diginamic.openfoodfacts.model.Ingredient;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class IngredientDAO implements IDAO<Ingredient>{

    private final static IngredientDAO INSTANCE = new IngredientDAO();
    private EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private IngredientDAO(){}
    
    /**
     *
     * @return the instance of IngredientDAO
     */
    public static IngredientDAO getInstance(){
        return INSTANCE;
    }
    
    /**
     *
     * @param id of an Ingredient
     * @return the Ingredient which has been found
     */
    @Override
    public Ingredient get(long id) {
        return em.find(Ingredient.class, id);
    }
    
    /**
     *
     * @param name of an Ingredient
     * @return the Ingredient which has been found
     */
    public Ingredient getByName(String name){
        TypedQuery<Ingredient> query = em.createNamedQuery("Ingredient.findByName", Ingredient.class);
        query.setParameter("nom", "%"+name+"%");
        if(!query.getResultList().isEmpty()){
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @return the list of all occurences of Ingredient
     */
    @Override
    public List<Ingredient> getAll() {
        TypedQuery<Ingredient> query = em.createNamedQuery("Ingredient.findAll", Ingredient.class);
        return query.getResultList();
    }

    /**
     *
     * @param ingredient to save
     */
    @Override
    public void save(Ingredient ingredient) {
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
    }

    /**
     *
     * @param ingredient to update
     * @param params array which contains fields to update
     */
    @Override
    public void update(Ingredient ingredient, String[] params) {
        ingredient.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
    }

    /**
     *
     * @param ingredient to delete
     */
    @Override
    public void delete(Ingredient ingredient) {
        em.getTransaction().begin();
        em.remove(ingredient);
        em.getTransaction().commit();
    }

    /**
     * Closes the EntityManager
     */
    @Override
    public void closeEM() {
        em.close();
    }
    
}
