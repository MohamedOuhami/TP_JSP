/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.ouhami.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import java.util.Date;

/**
 *
 * @author moham
 */
@Entity
public class Client extends User {

    private String nom;
    private String prenom;
   
    private Date dateNaissance;

    public Client() {
    }

    public Client(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Client(String nom, String prenom, Date dateNaissance, String email, String password) {
        super(email, password);
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    
    
    
}
