/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import fr.diginamic.openfoodfacts.model.Produit;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class ProduitDAO implements IDAO<Produit> {

    private final static ProduitDAO INSTANCE = new ProduitDAO();
    private final EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private ProduitDAO(){}
    
    /**
     *
     * @return the instance of ProduitDAO
     */
    public static ProduitDAO getInstance(){
        return INSTANCE;
    }
    
    /**
     *
     * @param id of a Produit
     * @return the Produit which has been found
     */
    @Override
    public Produit get(long id) {
        return em.find(Produit.class, id);
    }

    /**
     *
     * @return all occurences of Produit
     */
    @Override
    public List<Produit> getAll() {
        TypedQuery<Produit> query = em.createNamedQuery("Produit.findAll", Produit.class);
        return query.getResultList();
    }

    /**
     *
     * @param produit to save
     */
    @Override
    public void save(Produit produit) {
        em.getTransaction().begin();
        em.persist(produit);
        em.getTransaction().commit();
    }

    /**
     *
     * @param produit to update
     * @param params array of fields to update
     */
    @Override
    public void update(Produit produit, String[] params) {
        //TODO mise à jour produit
    }

    /**
     *
     * @param produit to delete
     */
    @Override
    public void delete(Produit produit) {
        em.getTransaction().begin();
        em.remove(produit);
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
