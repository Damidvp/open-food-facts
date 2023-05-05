/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmouchagues
 * Classe Produit
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
})
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Marque> marques;
    @ManyToOne
    private Categorie categorie;
    private String nom;
    private Character score;
    @ManyToMany
    private List<Ingredient> listeIngredients = new ArrayList<>();
    private Float energie100g;
    private Float graisse100g;
    private Float sucres100g;
    private Float fibres100g;
    private Float proteines100g;
    private Float sel100g;
    private Float vitA100g;
    private Float vitD100g;
    private Float vitE100g;
    private Float vitK100g;
    private Float vitC100g;
    private Float vitB1100g;
    private Float vitB2100g;
    private Float vitPP100g;
    private Float vitB6100g;
    private Float vitB9100g;
    private Float vitB12100g;
    private Float calcium100g;
    private Float magnesium100g;
    private Float iron100g;
    private Float fer100g;
    private Float betaCarotene100g;
    private Boolean presenceHuilePalme;
    @ManyToMany
    private List<Allergene> listeAllergenes = new ArrayList<>();
    @ManyToMany
    private List<Additif> listeAdditifs = new ArrayList<>();

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public List<Marque> getMarques() {
        return marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = marques;
    }

    /**
     *
     * @return
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     *
     * @param categorie
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public Character getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(Character score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public List<Ingredient> getListeIngredients() {
        return listeIngredients;
    }

    /**
     *
     * @param listeIngredients
     */
    public void setListeIngredients(List<Ingredient> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    /**
     *
     * @return
     */
    public Float getEnergie100g() {
        return energie100g;
    }

    /**
     *
     * @param energie100g
     */
    public void setEnergie100g(Float energie100g) {
        this.energie100g = energie100g;
    }

    /**
     *
     * @return
     */
    public Float getGraisse100g() {
        return graisse100g;
    }

    /**
     *
     * @param graisse100g
     */
    public void setGraisse100g(Float graisse100g) {
        this.graisse100g = graisse100g;
    }

    /**
     *
     * @return
     */
    public Float getSucres100g() {
        return sucres100g;
    }

    /**
     *
     * @param sucres100g
     */
    public void setSucres100g(Float sucres100g) {
        this.sucres100g = sucres100g;
    }

    /**
     *
     * @return
     */
    public Float getFibres100g() {
        return fibres100g;
    }

    /**
     *
     * @param fibres100g
     */
    public void setFibres100g(Float fibres100g) {
        this.fibres100g = fibres100g;
    }

    /**
     *
     * @return
     */
    public Float getProteines100g() {
        return proteines100g;
    }

    /**
     *
     * @param proteines100g
     */
    public void setProteines100g(Float proteines100g) {
        this.proteines100g = proteines100g;
    }

    /**
     *
     * @return
     */
    public Float getSel100g() {
        return sel100g;
    }

    /**
     *
     * @param sel100g
     */
    public void setSel100g(Float sel100g) {
        this.sel100g = sel100g;
    }

    /**
     *
     * @return
     */
    public Float getVitA100g() {
        return vitA100g;
    }

    /**
     *
     * @param vitA100g
     */
    public void setVitA100g(Float vitA100g) {
        this.vitA100g = vitA100g;
    }

    /**
     *
     * @return
     */
    public Float getVitD100g() {
        return vitD100g;
    }

    /**
     *
     * @param vitD100g
     */
    public void setVitD100g(Float vitD100g) {
        this.vitD100g = vitD100g;
    }

    /**
     *
     * @return
     */
    public Float getVitE100g() {
        return vitE100g;
    }

    /**
     *
     * @param vitE100g
     */
    public void setVitE100g(Float vitE100g) {
        this.vitE100g = vitE100g;
    }

    /**
     *
     * @return
     */
    public Float getVitK100g() {
        return vitK100g;
    }

    /**
     *
     * @param vitK100g
     */
    public void setVitK100g(Float vitK100g) {
        this.vitK100g = vitK100g;
    }

    /**
     *
     * @return
     */
    public Float getVitC100g() {
        return vitC100g;
    }

    /**
     *
     * @param vitC100g
     */
    public void setVitC100g(Float vitC100g) {
        this.vitC100g = vitC100g;
    }

    /**
     *
     * @return
     */
    public Float getVitB1100g() {
        return vitB1100g;
    }

    /**
     *
     * @param vitB1100g
     */
    public void setVitB1100g(Float vitB1100g) {
        this.vitB1100g = vitB1100g;
    }

    /**
     *
     * @return
     */
    public Float getVitB2100g() {
        return vitB2100g;
    }

    /**
     *
     * @param vitB2100g
     */
    public void setVitB2100g(Float vitB2100g) {
        this.vitB2100g = vitB2100g;
    }

    /**
     *
     * @return
     */
    public Float getVitPP100g() {
        return vitPP100g;
    }

    /**
     *
     * @param vitPP100g
     */
    public void setVitPP100g(Float vitPP100g) {
        this.vitPP100g = vitPP100g;
    }

    /**
     *
     * @return
     */
    public Float getVitB6100g() {
        return vitB6100g;
    }

    /**
     *
     * @param vitB6100g
     */
    public void setVitB6100g(Float vitB6100g) {
        this.vitB6100g = vitB6100g;
    }

    /**
     *
     * @return
     */
    public Float getVitB9100g() {
        return vitB9100g;
    }

    /**
     *
     * @param vitB9100g
     */
    public void setVitB9100g(Float vitB9100g) {
        this.vitB9100g = vitB9100g;
    }

    /**
     *
     * @return
     */
    public Float getVitB12100g() {
        return vitB12100g;
    }

    /**
     *
     * @param vitB12100g
     */
    public void setVitB12100g(Float vitB12100g) {
        this.vitB12100g = vitB12100g;
    }

    /**
     *
     * @return
     */
    public Float getCalcium100g() {
        return calcium100g;
    }

    /**
     *
     * @param calcium100g
     */
    public void setCalcium100g(Float calcium100g) {
        this.calcium100g = calcium100g;
    }

    /**
     *
     * @return
     */
    public Float getMagnesium100g() {
        return magnesium100g;
    }

    /**
     *
     * @param magnesium100g
     */
    public void setMagnesium100g(Float magnesium100g) {
        this.magnesium100g = magnesium100g;
    }

    /**
     *
     * @return
     */
    public Float getIron100g() {
        return iron100g;
    }

    /**
     *
     * @param iron100g
     */
    public void setIron100g(Float iron100g) {
        this.iron100g = iron100g;
    }

    /**
     *
     * @return
     */
    public Float getFer100g() {
        return fer100g;
    }

    /**
     *
     * @param fer100g
     */
    public void setFer100g(Float fer100g) {
        this.fer100g = fer100g;
    }

    /**
     *
     * @return
     */
    public Float getBetaCarotene100g() {
        return betaCarotene100g;
    }

    /**
     *
     * @param betaCarotene100g
     */
    public void setBetaCarotene100g(Float betaCarotene100g) {
        this.betaCarotene100g = betaCarotene100g;
    }

    /**
     *
     * @return
     */
    public Boolean getPresenceHuilePalme() {
        return presenceHuilePalme;
    }

    /**
     *
     * @param presenceHuilePalme
     */
    public void setPresenceHuilePalme(Boolean presenceHuilePalme) {
        this.presenceHuilePalme = presenceHuilePalme;
    }

    /**
     *
     * @return
     */
    public List<Allergene> getListeAllergenes() {
        return listeAllergenes;
    }

    /**
     *
     * @param listeAllergenes
     */
    public void setListeAllergenes(List<Allergene> listeAllergenes) {
        this.listeAllergenes = listeAllergenes;
    }

    /**
     *
     * @return
     */
    public List<Additif> getListeAdditifs() {
        return listeAdditifs;
    }

    /**
     *
     * @param listeAdditifs
     */
    public void setListeAdditifs(List<Additif> listeAdditifs) {
        this.listeAdditifs = listeAdditifs;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", marques=" + marques + ", categorie=" + categorie + ", nom=" + nom + ", score=" + score + ", listeIngredients=" + listeIngredients + ", energie100g=" + energie100g + ", graisse100g=" + graisse100g + ", sucres100g=" + sucres100g + ", fibres100g=" + fibres100g + ", proteines100g=" + proteines100g + ", sel100g=" + sel100g + ", vitA100g=" + vitA100g + ", vitD100g=" + vitD100g + ", vitE100g=" + vitE100g + ", vitK100g=" + vitK100g + ", vitC100g=" + vitC100g + ", vitB1100g=" + vitB1100g + ", vitB2100g=" + vitB2100g + ", vitPP100g=" + vitPP100g + ", vitB6100g=" + vitB6100g + ", vitB9100g=" + vitB9100g + ", vitB12100g=" + vitB12100g + ", calcium100g=" + calcium100g + ", magnesium100g=" + magnesium100g + ", iron100g=" + iron100g + ", fer100g=" + fer100g + ", betaCarotene100g=" + betaCarotene100g + ", presenceHuilePalme=" + presenceHuilePalme + ", listeAllergenes=" + listeAllergenes + ", listeAdditifs=" + listeAdditifs + '}';
    }
   
}
