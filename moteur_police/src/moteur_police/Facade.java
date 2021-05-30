package moteur_police;

import java.awt.Image;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Facade {
	
	@PersistenceContext
	private EntityManager em;
	
	public Facade() {}
	
	@PostConstruct
	public void initialiser() {}
	
	//Map<Integer, Identite> personnes = new Hashtable<Integer, Identite>();
	int idp = 0;

	public void ajouterPersonne (String nom, String prenom, Image photo){
		Identite p = new Identite();
		p.setId(idp);
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setPhoto(photo);
		//personnes.put(idp, p);
		idp++;
		em.persist(p);
	}
	
	public Identite retourSuspect(Image photo) {
		Identite p = em.find(Identite.class, photo);
		//Identite p = new Identite();
		return p;
	}
	
}
