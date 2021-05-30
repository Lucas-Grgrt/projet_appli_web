package moteur_police;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Identite {
	int id;
	String nom;
	String prenom;
	Image photo;
	
	// Constructeur vide (nécessaire pour JBoss)
		public Identite() {}

		// Setters & getters
		public int getId() {
			return id;
		}
		public void setId(int pId) {
			this.id = pId;
		}

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
		
		public Image getPhoto() {
			return photo;
		}
		public void setPhoto(Image pPhoto) {
			this.photo = pPhoto;
		}
}
