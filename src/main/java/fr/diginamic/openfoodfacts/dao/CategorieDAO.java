/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import fr.diginamic.openfoodfacts.model.Categorie;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class CategorieDAO implements IDAO<Categorie>{
    
    private final static CategorieDAO INSTANCE = new CategorieDAO();
    private EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private CategorieDAO(){}
    
    /**
     *
     * @return the instance of CategorieDAO
     */
    public static CategorieDAO getInstance(){
        return INSTANCE;
    }
    
    /**
     *
     * @param id of a Categorie
     * @return the Categorie which has been found
     */
    @Override
    public Categorie get(long id) {
        return em.find(Categorie.class, id);
    }
    
    /**
     *
     * @param name of a Categorie
     * @return the Categorie which has been found
     */
    public Categorie getByName(String name){
        TypedQuery<Categorie> query = em.createNamedQuery("Categorie.findByName", Categorie.class);
        query.setParameter("nom", name);
        return query.getResultList().get(0);
    }

    /**
     *
     * @return the list of all occurences of Categorie
     */
    @Override
    public List<Categorie> getAll() {
        TypedQuery<Categorie> query = em.createNamedQuery("Categorie.findAll", Categorie.class);
        return query.getResultList();
    }

    /**
     *
     * @param categorie to save
     */
    @Override
    public void save(Categorie categorie) {
        em.getTransaction().begin();
        em.persist(categorie);
        em.getTransaction().commit();
    }

    /**
     *
     * @param categorie to update
     * @param params array which contains fields to update
     */
    @Override
    public void update(Categorie categorie, String[] params) {
        categorie.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(categorie);
        em.getTransaction().commit();
    }

    /**
     *
     * @param categorie
     */
    @Override
    public void delete(Categorie categorie) {
        em.getTransaction().begin();
        em.remove(categorie);
        em.getTransaction().commit();
    }

    /**
     *
     */
    @Override
    public void closeEM() {
        em.close();
    }
    
}
