/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import fr.diginamic.openfoodfacts.model.Additif;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class AdditifDAO implements IDAO<Additif> {

    private final static AdditifDAO INSTANCE = new AdditifDAO();
    private EntityManager em = JPAUtils.getInstance().getEntityManager();
    
    private AdditifDAO(){}
    
    /**
     *
     * @return the instance of AdditifDAO
     */
    public static AdditifDAO getInstance(){
        return INSTANCE;
    }
    
    /**
     *
     * @param id of an Additif
     * @return the Additif which has been found
     */
    @Override
    public Additif get(long id) {
        return em.find(Additif.class, id);
    }
    
    /**
     *
     * @param name of an Additif
     * @return the Additif which has been found
     */
    public Additif getByName(String name){
        TypedQuery<Additif> query = em.createNamedQuery("Additif.findByName", Additif.class);
        query.setParameter("nom", "%"+name+"%");
        if(!query.getResultList().isEmpty()){
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @return the list of all occurences of Additif
     */
    @Override
    public List<Additif> getAll() {
        TypedQuery<Additif> query = em.createNamedQuery("Additif.findAll", Additif.class);
        return query.getResultList();
    }

    /**
     *
     * @param additif to save
     */
    @Override
    public void save(Additif additif) {
        em.getTransaction().begin();
        em.persist(additif);
        em.getTransaction().commit();
    }

    /**
     *
     * @param additif to update
     * @param params which contains fields to update
     */
    @Override
    public void update(Additif additif, String[] params) {
        additif.setNom(params[0]);
        em.getTransaction().begin();
        em.persist(additif);
        em.getTransaction().commit();
    }

    /**
     *
     * @param additif to delete
     */
    @Override
    public void delete(Additif additif) {
        em.getTransaction().begin();
        em.remove(additif);
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
