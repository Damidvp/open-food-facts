/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import fr.diginamic.openfoodfacts.model.Marque;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class MarqueDAO implements IDAO<Marque>{

    private final static MarqueDAO INSTANCE = new MarqueDAO();
    private EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private MarqueDAO(){}
    
    /**
     *
     * @return the instance of MarqueDAO
     */
    public static MarqueDAO getInstance(){
        return INSTANCE;
    }
    
    /**
     *
     * @param id of a Marque
     * @return the Marque which has been found
     */
    @Override
    public Marque get(long id) {
        return em.find(Marque.class, id);
    }
    
    /**
     *
     * @param name of a Marque
     * @return the Marque which has been found
     */
    public Marque getByName(String name){
        TypedQuery<Marque> query = em.createNamedQuery("Marque.findByName", Marque.class);
        query.setParameter("nom", name);
        return query.getResultList().get(0);
    }

    /**
     *
     * @return all occurences of Marque
     */
    @Override
    public List<Marque> getAll() {
        TypedQuery<Marque> query = em.createNamedQuery("Marque.findAll", Marque.class);
        return query.getResultList();
    }

    /**
     *
     * @param marque to save
     */
    @Override
    public void save(Marque marque) {
        em.getTransaction().begin();
        em.persist(marque);
        em.getTransaction().commit();
    }

    /**
     *
     * @param marque to update
     * @param params array of fields to update
     */
    @Override
    public void update(Marque marque, String[] params) {
        marque.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(marque);
        em.getTransaction().commit();
    }

    /**
     *
     * @param marque to delete
     */
    @Override
    public void delete(Marque marque) {
        em.getTransaction().begin();
        em.remove(marque);
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
