/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import fr.diginamic.openfoodfacts.model.Allergene;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class AllergeneDAO implements IDAO<Allergene> {
    
    private final static AllergeneDAO INSTANCE = new AllergeneDAO();
    private EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private AllergeneDAO(){}
    
    public static AllergeneDAO getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Allergene get(long id) {
        return em.find(Allergene.class, id);
    }

    @Override
    public List<Allergene> getAll() {
        TypedQuery<Allergene> query = em.createNamedQuery("Allergene.findAll", Allergene.class);
        return query.getResultList();
    }

    @Override
    public void save(Allergene allergene) {
        em.getTransaction().begin();
        em.persist(allergene);
        em.getTransaction().commit();
    }

    @Override
    public void update(Allergene allergene, String[] params) {
        allergene.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(allergene);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Allergene allergene) {
        em.getTransaction().begin();
        em.remove(allergene);
        em.getTransaction().commit();
    }

    @Override
    public void closeEM() {
        em.close();
    }
    
}
