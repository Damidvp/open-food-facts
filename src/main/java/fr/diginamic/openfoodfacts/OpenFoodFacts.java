/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.diginamic.openfoodfacts;

import fr.diginamic.openfoodfacts.service.StockService;

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
        StockService.getInstance();
    }
}
