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
    private EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private ProduitDAO(){}
    
    public static ProduitDAO getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Produit get(long id) {
        return em.find(Produit.class, id);
    }

    @Override
    public List<Produit> getAll() {
        TypedQuery<Produit> query = em.createNamedQuery("Produit.findAll", Produit.class);
        return query.getResultList();
    }

    @Override
    public void save(Produit produit) {
        em.getTransaction().begin();
        em.persist(produit);
        em.getTransaction().commit();
    }

    @Override
    public void update(Produit produit, String[] params) {
        //TODO mise à jour produit
    }

    @Override
    public void delete(Produit produit) {
        em.getTransaction().begin();
        em.remove(produit);
        em.getTransaction().commit();
    }

    @Override
    public void closeEM() {
        em.close();
    }
    
}
