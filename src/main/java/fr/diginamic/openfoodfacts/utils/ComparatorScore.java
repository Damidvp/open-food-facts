/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.utils;

import fr.diginamic.openfoodfacts.model.Produit;
import java.util.Comparator;

/**
 *
 * @author zdamy
 */
public class ComparatorScore implements Comparator<Produit>{

    @Override
    public int compare(Produit o1, Produit o2) {
        int result = 0;
        if(o1.getScore() > o2.getScore()){
            result = 1;
        }
        if(o1.getScore() < o2.getScore()){
            result = -1;
        }
        return result;
    }
    
}
