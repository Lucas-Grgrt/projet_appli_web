package moteur_police;

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
	
	int photo = 0;
	
	String nom;
	String prenom;
	int numero;

	public void ajouterPersonne (String nom, String prenom, int numero){
		Identite p = new Identite();
		p.setPhoto(photo);
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setNumero(numero);
		em.persist(p);
		photo++;
	}
	
	public Identite retourSuspect(int numero) {
		Identite p = em.find(Identite.class, numero); // clé primaire
		return p;
	}
	
    //##############################################################
    // Code à ajouter pour rajouter la Login et Sign Up page

    // Retourne vrai si l'utilisateur est déjà présent dans la BD
    public Boolean isUserPresent(String username) {
        User potentialUser = em.find(User.class, username);
        return (potentialUser != null);
    }

    // Pour ajouter un utilisateur dans la BD
    public void addUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        em.persist(user);    // on push le nouveau user dans la BD
    }

    public String getUserPassword(String username) {
        User user = em.find(User.class, username); // On cherche l'utilisateur dans la BD
        return user.getPassword();
    }
    // Fin du code à rajouter pour la Login et Sign Up page
    //###############################################################
	
}
