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
    @NamedQuery(name = "Allergene.findAll", query = "SELECT a FROM Allergene a"),
    @NamedQuery(name = "Allergene.findByName", query = "SELECT a FROM Allergene a WHERE nom LIKE :nom")
})
public class Allergene {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String nom;
    @ManyToMany(mappedBy = "listeAllergenes")
    private List<Produit> produits = new ArrayList<>();

    /**
     *
     * @return l'id d'un Allergene
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id d'un Allergene
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom d'un Allergene
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom d'un Allergene
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return la liste de Produit d'un Allergene
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     *
     * @param produits d'un Allergene
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

}
