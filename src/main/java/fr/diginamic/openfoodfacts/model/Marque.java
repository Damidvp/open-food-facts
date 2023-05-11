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
    @NamedQuery(name = "Marque.findAll", query = "SELECT m FROM Marque m"),
    @NamedQuery(name = "Marque.findByName", query = "SELECT m FROM Marque m WHERE nom = :nom")
})
public class Marque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToMany(mappedBy = "marques")
    private List<Produit> produits = new ArrayList<>();

    /**
     *
     * @return l'id d'une Marque
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id d'une Marque
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom d'une Marque
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom d'une Marque
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return la liste des Produit d'une Marque
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     *
     * @param produits d'une Marque
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
}
