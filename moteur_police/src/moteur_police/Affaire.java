package moteur_police;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.python.bouncycastle.asn1.cms.Evidence;

// Serializable pour que ça marche en réparti
@Entity
public class Affaire implements Serializable {

@Id
private String nom;

@ManyToMany
private Collection<Identite> suspects;


public Affaire() {}

public String getNom() {
	return this.nom;
}

public void setNom(String new_nom) {
	this.nom = new_nom;
}

public Collection<Identite> getSuspects(){
	return this.suspects;
}

public void ajouterUnSuspect(Identite new_suspect) {
	this.suspects.add(new_suspect);
}

public void supprimerUnSuspect(Identite rem_suspect) {
	suspects = this.suspects;
	for (Identite suspect : suspects) {
		if (suspect.getNom() == rem_suspect.getNom() && suspect.getPrenom() == rem_suspect.getPrenom() && suspect.getNumero() == rem_suspect.getNumero() ) {
			this.suspects.remove(suspect);
		}
	}
}

}