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
    
    public static MarqueDAO getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Marque get(long id) {
        return em.find(Marque.class, id);
    }

    @Override
    public List<Marque> getAll() {
        TypedQuery<Marque> query = em.createNamedQuery("Marque.findAll", Marque.class);
        return query.getResultList();
    }

    @Override
    public void save(Marque marque) {
        em.getTransaction().begin();
        em.persist(marque);
        em.getTransaction().commit();
    }

    @Override
    public void update(Marque marque, String[] params) {
        marque.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(marque);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Marque marque) {
        em.getTransaction().begin();
        em.remove(marque);
        em.getTransaction().commit();
    }

    @Override
    public void closeEM() {
        em.close();
    }
    
}
