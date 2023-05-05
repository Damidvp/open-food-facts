/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.diginamic.openfoodfacts;

import fr.diginamic.openfoodfacts.controller.MainController;

/**
 *
 * @author dmouchagues
 */
public class OpenFoodFacts {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MainController.getInstance().insertData();
    }
}
