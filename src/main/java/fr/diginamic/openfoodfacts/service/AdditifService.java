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
    
    public static AdditifService getInstance(){
        return INSTANCE;
    }

    @Override
    public Additif get(long id) {
        Additif additif = null;
        try{
            additif = additifDao.get(id);
            additifDao.closeEM();
        } catch(Exception e){
            System.out.println("Additif inexistant");
        }
        return additif;
    }

    @Override
    public List<Additif> getAll() {
        List<Additif> liste = additifDao.getAll();
        additifDao.closeEM();
        return liste;
    }

    @Override
    public void update(Additif additif, String[] params) {
        additifDao.update(additif, params);
        additifDao.closeEM();
    }

    @Override
    public void save(Additif additif) {
        additifDao.save(additif);
        additifDao.closeEM();
    }

    @Override
    public void delete(Additif additif) {
        additifDao.delete(additif);
        additifDao.closeEM();
    }
}
