/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.diginamic.openfoodfacts.tests;

import fr.diginamic.openfoodfacts.model.Additif;
import fr.diginamic.openfoodfacts.model.Allergene;
import fr.diginamic.openfoodfacts.model.Ingredient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class TestParsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ing1 = new Ingredient();
        Ingredient ing2 = new Ingredient();
        Ingredient ing3 = new Ingredient();
        Ingredient ing4 = new Ingredient();
        
        ing1.setNom("eau");
        ing2.setNom("Eau");
        ing3.setNom("Sucre");
        ing4.setNom("sUcRe");
        
        ingredients.add(ing1);
        ingredients.add(ing2);
        ingredients.add(ing3);
        ingredients.add(ing4);
        
        System.out.println(formatStr("_un_ingredient_* 95% 70.6 %, *deux_ingr*_ (des infos) [d'autres infos]"));
    }
    
    private static String formatStr(String s){
        String chaineFormat = "";
        chaineFormat = s.replaceAll("\\*", "")
                .replaceAll("_", "")
                .replaceAll("\\(.*?\\)", "")
                .replaceAll("\\[.*?\\]", "")
                .replaceAll("\\s*\\d+(\\.\\d+)?%\\s*", "")
                .replaceAll("\\s*\\d+(\\.\\d+)? %\\s*", "");
        return chaineFormat;
    }
    
}
