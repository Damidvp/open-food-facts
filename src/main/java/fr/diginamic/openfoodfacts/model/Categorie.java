/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByName", query = "SELECT c FROM Categorie c WHERE nom = :nom")
})
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits = new ArrayList<>();

    /**
     *
     * @return l'id d'une Categorie
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id d'une Categorie
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom d'une Categorie
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom d'une Categorie
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return la liste de Produit d'une Categorie
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     *
     * @param produits d'une Categorie
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
    
}
