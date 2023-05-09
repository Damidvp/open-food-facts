/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import fr.diginamic.openfoodfacts.dao.AdditifDAO;
import fr.diginamic.openfoodfacts.model.Additif;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class AdditifService implements IService<Additif>{
    private final static AdditifService INSTANCE = new AdditifService();
    private final static AdditifDAO additifDao = AdditifDAO.getInstance();
    
    private AdditifService(){}
    
    /**
     *
     * @return
     */
    public static AdditifService getInstance(){
        return INSTANCE;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Additif get(long id) {
        Additif additif = null;
        try{
            additif = additifDao.get(id);
        } catch(Exception e){
            //System.out.println("Additif inexistant");
        }
        return additif;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Additif getByName(String name){
        Additif additif = null;
        try{
            additif = additifDao.getByName(name);
        } catch(Exception e){
            //System.out.println("Additif inexistant");
        }
        return additif;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Additif> getAll() {
        List<Additif> liste = additifDao.getAll();
        return liste;
    }

    /**
     *
     * @param additif
     * @param params
     */
    @Override
    public void update(Additif additif, String[] params) {
        additifDao.update(additif, params);
    }

    /**
     *
     * @param additif
     */
    @Override
    public void save(Additif additif) {
        additifDao.save(additif);
    }

    /**
     *
     * @param additif
     */
    @Override
    public void delete(Additif additif) {
        additifDao.delete(additif);
    }

    /**
     *
     */
    @Override
    public void closeEM() {
        additifDao.closeEM();
    }
}
