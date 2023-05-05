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
    
    public static IngredientDAO getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Ingredient get(long id) {
        return em.find(Ingredient.class, id);
    }

    @Override
    public List<Ingredient> getAll() {
        TypedQuery<Ingredient> query = em.createNamedQuery("Ingredient.findAll", Ingredient.class);
        return query.getResultList();
    }

    @Override
    public void save(Ingredient ingredient) {
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
    }

    @Override
    public void update(Ingredient ingredient, String[] params) {
        ingredient.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Ingredient ingredient) {
        em.getTransaction().begin();
        em.remove(ingredient);
        em.getTransaction().commit();
    }

    @Override
    public void closeEM() {
        em.close();
    }
    
}
