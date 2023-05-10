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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    
    Map<String, Categorie> cacheCategories = new HashMap<>();
    Map<String, Marque> cacheMarques = new HashMap<>();
    Map<String, Additif> cacheAdditifs = new HashMap<>();
    Map<String, Allergene> cacheAllergenes = new HashMap<>();
    Map<String, Ingredient> cacheIngredients = new HashMap<>();

    private StockService() {
        try {
            List<String> linesFile = new ArrayList<>();
            Path pathFile = Paths.get("target/open-food-facts.csv"); //Chemin du fichier CSV
            linesFile = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
            
            
            
            for (int i = 1; i < linesFile.size(); i++) {
                String[] tokens = linesFile.get(i).split("\\|");
                if(tokens.length < 30){
                    continue;
                }
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
    
    private String cleanMarqueName(String s){
        s = s.trim();
        return s;
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
        Categorie categorieExistante = cacheCategories.get(cleanCategorieName(tokens[0]));
        if (categorieExistante != null) {
            produit.setCategorie(categorieExistante);
        } else {
            Categorie categorie = new Categorie();
            categorie.setNom(cleanCategorieName(tokens[0]));
            categorieDao.save(categorie);
            cacheCategories.put(cleanCategorieName(tokens[0]), categorie);
            produit.setCategorie(categorie);
        }
    }

    private void saveInfo(String[] tokens, Produit produit) {
        produit.setNom(tokens[2]);
        produit.setScore(tokens[3].charAt(0));

            produit.setEnergie100g(getValueOf(tokens[5])); //Si la valeur est une String vide, on affecte 0
            produit.setGraisse100g(getValueOf(tokens[6]));
            produit.setSucres100g(getValueOf(tokens[7]));
            produit.setFibres100g(getValueOf(tokens[8]));
            produit.setProteines100g(getValueOf(tokens[9]));
            produit.setSel100g(getValueOf(tokens[10]));
            produit.setVitA100g(getValueOf(tokens[11])); 
            produit.setVitD100g(getValueOf(tokens[12]));
            produit.setVitE100g(getValueOf(tokens[13]));
            produit.setVitK100g(getValueOf(tokens[14]));
            produit.setVitC100g(getValueOf(tokens[15]));
            produit.setVitB1100g(getValueOf(tokens[16]));
            produit.setVitB2100g(getValueOf(tokens[17]));
            produit.setVitPP100g(getValueOf(tokens[18]));
            produit.setVitB6100g(getValueOf(tokens[19]));
            produit.setVitB9100g(getValueOf(tokens[20]));
            produit.setVitB12100g(getValueOf(tokens[21]));
            produit.setCalcium100g(getValueOf(tokens[22]));
            produit.setMagnesium100g(getValueOf(tokens[23]));
            produit.setIron100g(getValueOf(tokens[24]));
            produit.setFer100g(getValueOf(tokens[25]));
            produit.setBetaCarotene100g(getValueOf(tokens[26]));

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
                uneMarque = cleanMarqueName(uneMarque);
                Marque marqueExistante = cacheMarques.get(uneMarque);
                if (marqueExistante != null) {
                    marques.add(marqueExistante);
                } else {
                    Marque marque = new Marque();
                    marque.setNom(uneMarque);
                    marques.add(marque);
                    cacheMarques.put(uneMarque, marque);
                    marqueDao.save(marque);
                }
            }
        }
        if (tokensIngredients.length != 0) {
            for (String unIngredient : tokensIngredients) {
                if (!unIngredient.equals("")) {
                    unIngredient = cleanIngredientName(unIngredient);
                    Ingredient ingredientExistant = cacheIngredients.get(unIngredient);
                    if (ingredientExistant != null) {
                        ingredients.add(ingredientExistant);
                    } else {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setNom(unIngredient);
                        ingredients.add(ingredient);
                        cacheIngredients.put(unIngredient, ingredient);
                        ingredientDao.save(ingredient);
                    }
                }
            }
        }
        if (tokensAllergenes.length != 0) {
            for (String unAllergene : tokensAllergenes) {
                if (!unAllergene.equals("")) {
                    unAllergene = cleanAllergeneName(unAllergene);
                    Allergene allergeneExistant = cacheAllergenes.get(unAllergene);
                    if (allergeneExistant != null) {
                        allergenes.add(allergeneExistant);
                    } else {
                        Allergene allergene = new Allergene();
                        allergene.setNom(unAllergene);
                        allergenes.add(allergene);
                        cacheAllergenes.put(unAllergene, allergene);
                        allergeneDao.save(allergene);
                    }
                }
            }
        }
        if (tokensAdditifs.length != 0) {
            for (String unAdditif : tokensAdditifs) {
                if (!unAdditif.equals("")) {
                    unAdditif = cleanAdditifName(unAdditif);
                    Additif additifExistant = cacheAdditifs.get(unAdditif);
                    if (additifExistant != null) {
                        additifs.add(additifExistant);
                    } else {
                        Additif additif = new Additif();
                        additif.setNom(unAdditif);
                        additifs.add(additif);
                        cacheAdditifs.put(unAdditif, additif);
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
