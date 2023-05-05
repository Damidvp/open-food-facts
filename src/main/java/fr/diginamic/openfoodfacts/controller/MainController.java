/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.controller;

/**
 *
 * @author dmouchagues
 */
public class MainController {
    
    private final static MainController INSTANCE = new MainController();
    
    private MainController(){}
    
    public static MainController getInstance(){
        return INSTANCE;
    }
    
    public void insertData(){
        //TODO insertion des données dans la BDD
    }
    
}
