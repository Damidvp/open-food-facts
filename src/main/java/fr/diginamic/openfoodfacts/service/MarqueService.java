/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import fr.diginamic.openfoodfacts.dao.MarqueDAO;
import fr.diginamic.openfoodfacts.model.Marque;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class MarqueService implements IService<Marque> {
    
    private final static MarqueService INSTANCE = new MarqueService();
    private final static MarqueDAO marqueDao = MarqueDAO.getInstance();
            
    private MarqueService(){}
    
    /**
     *
     * @return
     */
    public static MarqueService getInstance(){
        return INSTANCE;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Marque get(long id) {
        Marque marque = null;
        try{
            marque = marqueDao.get(id);
        } catch(Exception e){
            System.out.println("Marque inexistante");
        }
        return marque;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Marque getByName(String name){
        Marque marque = null;
        try{
            marque = marqueDao.getByName(name);
        } catch(Exception e){
            System.out.println("Marque inexistante");
        }
        return marque;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Marque> getAll() {
        List<Marque> liste = marqueDao.getAll();
        return liste;
    }

    /**
     *
     * @param marque
     * @param params
     */
    @Override
    public void update(Marque marque, String[] params) {
        marqueDao.update(marque, params);
    }

    /**
     *
     * @param marque
     */
    @Override
    public void save(Marque marque) {
        marqueDao.save(marque);
    }

    /**
     *
     * @param marque
     */
    @Override
    public void delete(Marque marque) {
        marqueDao.delete(marque);
    }

    /**
     *
     */
    @Override
    public void closeEM() {
        marqueDao.closeEM();
    }
}
