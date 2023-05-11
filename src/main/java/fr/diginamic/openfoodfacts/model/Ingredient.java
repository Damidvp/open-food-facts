/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
@Entity
@Table(indexes = {@Index(columnList = "nom")})
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE nom = :nom")
})
public class Ingredient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String nom;
    @ManyToMany(mappedBy = "listeIngredients")
    private List<Produit> produits = new ArrayList<>();

    /**
     *
     * @return l'id d'un Ingredient
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id d'un Ingredient
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom d'un Ingredient
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom d'un Ingredient
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return la liste de Produit d'un Ingredient
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     *
     * @param produits d'un Ingredient
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
}
