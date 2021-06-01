package moteur_police;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.swing.JFrame;
import javax.swing.JPanel;

@Entity
public class Identite {
	
	int photo;
	String nom;
	String prenom;
	@Id
	int numero;
	
	@ManyToMany
	Collection<Affaire> affaires;
	
	// Constructeur vide (nécessaire pour JBoss)
		public Identite() {}

		// Setters & getters
		public String getNom() {
			return nom;
		}
		public void setNom(String pNom) {
			this.nom = pNom;
		}

		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String pPrenom) {
			this.prenom = pPrenom;
		}
		
		public int getPhoto() {
			return photo;
		}
		public void setPhoto(int pPhoto) {
			this.photo = pPhoto;
		}
		
		public int getNumero() {
			return numero;
		}
		public void setNumero(int pNumero) {
			this.numero = pNumero;
		}
		
		public Collection<Affaire> getAffaires() {
			return this.affaires;
		}

		public void ajouterUneAffaire(Affaire new_affaire) {
			this.affaires.add(new_affaire);
			new_affaire.ajouterUnSuspect(this);
		}

		public void supprimerUneAffaire(Affaire rem_affaire) {
			affaires = this.affaires;
			for (Affaire affaire : affaires) {
				if (affaire.getNom() == rem_affaire.getNom()) {
					this.affaires.remove(affaire);
					rem_affaire.supprimerUnSuspect(this);
				}
			}
		}
		
}
