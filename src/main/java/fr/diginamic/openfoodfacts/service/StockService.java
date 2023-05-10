/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import fr.diginamic.openfoodfacts.dao.AdditifDAO;
import fr.diginamic.openfoodfacts.dao.AllergeneDAO;
import fr.diginamic.openfoodfacts.dao.CategorieDAO;
import fr.diginamic.openfoodfacts.dao.IngredientDAO;
import fr.diginamic.openfoodfacts.dao.MarqueDAO;
import fr.diginamic.openfoodfacts.dao.ProduitDAO;
import fr.diginamic.openfoodfacts.model.Additif;
import fr.diginamic.openfoodfacts.model.Allergene;
import fr.diginamic.openfoodfacts.model.Categorie;
import fr.diginamic.openfoodfacts.model.Ingredient;
import fr.diginamic.openfoodfacts.model.Marque;
import fr.diginamic.openfoodfacts.model.Produit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmouchagues Classe qui parse le fichier CSV, tout en créant une liste
 * de tous les produits prêts à être insérés en base
 */
public class StockService {

    private final static StockService INSTANCE = new StockService();
    private AdditifDAO additifDao = AdditifDAO.getInstance();
    private AllergeneDAO allergeneDao = AllergeneDAO.getInstance();
    private IngredientDAO ingredientDao = IngredientDAO.getInstance();
    private MarqueDAO marqueDao = MarqueDAO.getInstance();
    private CategorieDAO categorieDao = CategorieDAO.getInstance();
    private ProduitDAO produitDao = ProduitDAO.getInstance();

    private StockService() {
        try {
            List<String> linesFile = new ArrayList<>();
            Path pathFile = Paths.get("target/open-food-facts.csv"); //Chemin du fichier CSV
            linesFile = Files.readAllLines(pathFile, StandardCharsets.UTF_8);

            for (int i = 1; i < linesFile.size(); i++) {
                String[] tokens = linesFile.get(i).split("\\|");
                Produit produit = new Produit();
                
                saveCategorie(tokens, produit);
                saveInfo(tokens, produit);
                saveLists(tokens, produit);

                produitDao.save(produit);

            }
            produitDao.closeEM();
        } catch (IOException ex) {
            Logger.getLogger(StockService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return the instance of StockService
     */
    public static StockService getInstance() {
        return INSTANCE;
    }

    /**
     * @return Float value of String. Return 0 if the String cannot be parsed
     */
    private Float getValueOf(String token) {
        try {
            Float result = Float.valueOf(token);
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String cleanCategorieName(String s) {
        if (s.length() > 0) {
            s = s.replaceAll("\"", "");
        }
        return s;
    }

    private String cleanIngredientName(String s) {
        if (s.length() > 0) {
            s = s.replaceAll("_", "") //Supprime _
                    .replaceAll("\\*", "") //Supprime *
                    .replaceAll("\\.", "") //Supprime .
                    .replaceAll("\\(.*?\\)", "") //Supprime (entre parenthèses)
                    .replaceAll("\\[.*?\\]", "") //Supprime [entre crochets]
                    .replaceAll("\\s*\\d+(\\.\\d+)?%\\s*", "") //Supprime pourcentage sans espace
                    .replaceAll("\\s*\\d+(\\.\\d+)? %\\s*", "") //Supprime pourcentage avec espace
                    .trim();
        }
        return s;
    }

    private String cleanAllergeneName(String s) {
        if (s.length() > 0) {
            s = s.replaceAll("_", "") //Supprime _
                    .replaceAll("\\*", "") //Supprime *
                    .replace("en:", "")
                    .replace("fr:", "")
                    .trim();
        }
        return s;
    }

    private String cleanAdditifName(String s) {
        s = s.trim();
        return s;
    }

    private void saveCategorie(String[] tokens, Produit produit) {
        Categorie categorieExistante = categorieDao.getByName(cleanCategorieName(tokens[0]));
        if (categorieExistante != null) {
            produit.setCategorie(categorieExistante);
        } else {
            Categorie categorie = new Categorie();
            categorie.setNom(cleanCategorieName(tokens[0]));
            categorieDao.save(categorie);
            produit.setCategorie(categorie);
        }
    }

    private void saveInfo(String[] tokens, Produit produit) {
        produit.setNom(tokens[2]);
        produit.setScore(tokens[3].charAt(0));

        if (!tokens[5].isBlank()) {
            produit.setEnergie100g(getValueOf(tokens[5])); //Si la valeur est une String vide, on affecte 0
        }
        if (!tokens[6].isBlank()) {
            produit.setGraisse100g(getValueOf(tokens[6]));
        }
        if (!tokens[7].isBlank()) {
            produit.setSucres100g(getValueOf(tokens[7]));
        }
        if (!tokens[8].isBlank()) {
            produit.setFibres100g(getValueOf(tokens[8]));
        }
        if (!tokens[9].isBlank()) {
            produit.setProteines100g(getValueOf(tokens[9]));
        }
        if (!tokens[10].isBlank()) {
            produit.setSel100g(getValueOf(tokens[10]));
        }
        if (!tokens[11].isBlank()) {
            produit.setVitA100g(getValueOf(tokens[11]));
        }
        if (!tokens[12].isBlank()) {
            produit.setVitD100g(getValueOf(tokens[12]));
        }
        if (!tokens[13].isBlank()) {
            produit.setVitE100g(getValueOf(tokens[13]));
        }
        if (!tokens[14].isBlank()) {
            produit.setVitK100g(getValueOf(tokens[14]));
        }
        if (!tokens[15].isBlank()) {
            produit.setVitC100g(getValueOf(tokens[15]));
        }
        if (!tokens[16].isBlank()) {
            produit.setVitB1100g(getValueOf(tokens[16]));
        }
        if (!tokens[17].isBlank()) {
            produit.setVitB2100g(getValueOf(tokens[17]));
        }
        if (!tokens[18].isBlank()) {
            produit.setVitPP100g(getValueOf(tokens[18]));
        }
        if (!tokens[19].isBlank()) {
            produit.setVitB6100g(getValueOf(tokens[19]));
        }
        if (!tokens[20].isBlank()) {
            produit.setVitB9100g(getValueOf(tokens[20]));
        }
        if (!tokens[21].isBlank()) {
            produit.setVitB12100g(getValueOf(tokens[21]));
        }
        if (!tokens[22].isBlank()) {
            produit.setCalcium100g(getValueOf(tokens[22]));
        }
        if (!tokens[23].isBlank()) {
            produit.setMagnesium100g(getValueOf(tokens[23]));
        }
        if (!tokens[24].isBlank()) {
            produit.setIron100g(getValueOf(tokens[24]));
        }
        if (!tokens[25].isBlank()) {
            produit.setFer100g(getValueOf(tokens[25]));
        }
        if (!tokens[26].isBlank()) {
            produit.setBetaCarotene100g(getValueOf(tokens[26]));
        }

        produit.setPresenceHuilePalme(Boolean.valueOf(tokens[27]));
    }

    private void saveLists(String[] tokens, Produit produit) {
        List<Marque> marques = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        List<Allergene> allergenes = new ArrayList<>();
        List<Additif> additifs = new ArrayList<>();

        String[] tokensMarques = tokens[1].split(",");
        String[] tokensIngredients = tokens[4].split(",|;");
        String[] tokensAllergenes = tokens[28].split(",|;");
        String[] tokensAdditifs = tokens[29].split(",|;");

        if (tokensMarques.length != 0) {
            for (String uneMarque : tokensMarques) {
                Marque marqueExistante = marqueDao.getByName(uneMarque);
                if (marqueExistante != null) {
                    marques.add(marqueExistante);
                } else {
                    Marque marque = new Marque();
                    marque.setNom(uneMarque);
                    marques.add(marque);
                    marqueDao.save(marque);
                }
            }
        }
        if (tokensIngredients.length != 0) {
            for (String unIngredient : tokensIngredients) {
                if (!unIngredient.equals("")) {
                    unIngredient = cleanIngredientName(unIngredient);
                    Ingredient ingredientExistant = ingredientDao.getByName(unIngredient);
                    if (ingredientExistant != null) {
                        ingredients.add(ingredientExistant);
                    } else {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setNom(unIngredient);
                        ingredients.add(ingredient);
                        ingredientDao.save(ingredient);
                    }
                }
            }
        }
        if (tokensAllergenes.length != 0) {
            for (String unAllergene : tokensAllergenes) {
                if (!unAllergene.equals("")) {
                    unAllergene = cleanAllergeneName(unAllergene);
                    Allergene allergeneExistant = allergeneDao.getByName(unAllergene);
                    if (allergeneExistant != null) {
                        allergenes.add(allergeneExistant);
                    } else {
                        Allergene allergene = new Allergene();
                        allergene.setNom(unAllergene);
                        allergenes.add(allergene);
                        allergeneDao.save(allergene);
                    }
                }
            }
        }
        if (tokensAdditifs.length != 0) {
            for (String unAdditif : tokensAdditifs) {
                if (!unAdditif.equals("")) {
                    unAdditif = cleanAdditifName(unAdditif);
                    Additif additifExistant = additifDao.getByName(unAdditif);
                    if (additifExistant != null) {
                        additifs.add(additifExistant);
                    } else {
                        Additif additif = new Additif();
                        additif.setNom(unAdditif);
                        additifs.add(additif);
                        additifDao.save(additif);
                    }
                }
            }
        }

        produit.setMarques(marques);
        produit.setListeIngredients(ingredients);
        produit.setListeAllergenes(allergenes);
        produit.setListeAdditifs(additifs);
    }

}
