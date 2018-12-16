package fr.polytech.tours.jdbc.application.controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.polytech.tours.jdbc.application.controller.gestion.*;
import fr.polytech.tours.jdbc.application.controller.invite.*;
import fr.polytech.tours.jdbc.application.controller.utilisateur.*;
import fr.polytech.tours.jdbc.application.model.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Classe controleur qui gere tous les controlleurs.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class VenteControlleur extends SceneControlleur {
	/**
	 * Attribut controllerMap qui est une liste contenant la liste de tous les
	 * controlleurs.
	 */
	private HashMap<String, Object> controlleurMap = new HashMap<>();
	/**
	 * Attribut VALID_EMAIL_ADDRESS_REGEX qui correspond au format qui doit
	 * avoir l'adresse mail de l'utilisateur, ce qui permet de controler si une
	 * adresse mailest valide ou pas.
	 */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	
	private Personne personneCourant;
	private Utilisateur utilisateurCourant;
	private Vendeur vendeurCourant;
	/**
	 * Constructeur par default de la classe qui fait appel a tous les
	 * controleurs de l'application, initialisation de tous les controleurs.
	 */
	public VenteControlleur() {
		controlleurMap.put("index", new IndexControlleur(this));

		// gestion
		controlleurMap.put("gestAccep", new GestionAccepControlleur(this));
		controlleurMap.put("gestAnno", new GestionAnnoControlleur(this));
		controlleurMap.put("gestPers", new GestionPersControlleur(this));
		controlleurMap.put("gestOffr", new GestionOffreControlleur(this));

		// invite
		controlleurMap.put("inscription", new InscriptionControlleur(this));
		controlleurMap.put("resultatCherNonConn", new ResultatCherNonConn(this));

		// utilisateur
		controlleurMap.put("acptOffre", new AccepterOffreControlleur(this));
		controlleurMap.put("ajouterAnno", new AjouterAnnoControlleur(this));
		controlleurMap.put("connexion", new ConnexionControlleur(this));
		controlleurMap.put("errAnnonce", new ErrAnnonceControlleur(this));
		controlleurMap.put("errConnexion", new ErrConnexionControlleur(this));
		controlleurMap.put("errCreation", new ErrCreationControlleur(this));
		controlleurMap.put("errModification", new ErrModificationControlleur(this));
		controlleurMap.put("faireOffre", new FaireOffreControlleur(this));
		controlleurMap.put("resultatCher", new ResultatCherControlleur(this));
		controlleurMap.put("utilisateur", new UtilisateurControlleur(this));

	}

	/**
	 * @return the personneCourant
	 */
	public Personne getPersonneCourant() {
		return personneCourant;
	}

	/**
	 * @param personneCourant the personneCourant to set
	 */
	public void setPersonneCourant(Personne personneCourant) {
		this.personneCourant = personneCourant;
	}

	/**
	 * @return the utilisateurCourant
	 */
	public Utilisateur getUtilisateurCourant() {
		return utilisateurCourant;
	}

	/**
	 * @param utilisateurCourant the utilisateurCourant to set
	 */
	public void setUtilisateurCourant(Utilisateur utilisateurCourant) {
		this.utilisateurCourant = utilisateurCourant;
	}

	/**
	 * @return the vendeurCourant
	 */
	public Vendeur getVendeurCourant() {
		return vendeurCourant;
	}

	/**
	 * @param vendeurCourant the vendeurCourant to set
	 */
	public void setVendeurCourant(Vendeur vendeurCourant) {
		this.vendeurCourant = vendeurCourant;
	}
	/**
	 * Accesseur du controleur.
	 * 
	 * @param nom
	 * @return le nom du controlleur
	 */
	public Object getControlleur(String nom) {
		return controlleurMap.get(nom);
	}

	public boolean verifierTextField(TextField tf) {
		if ((tf.getText() != null && !tf.getText().isEmpty())) {
			return true;
		}
		return false;
	}
	/**
	 * Verifier si un mot de pass a ete rentre par l'utilisateur.
	 * 
	 * @param pf
	 * @return true si oui, et false sinon
	 */
	public boolean verifierPwField(PasswordField pf) {
		if ((pf.getText() != null && !pf.getText().isEmpty())) {
			return true;
		}
		return false;
	}
	/**
	 * Cette fonction permet de verifier si le mot de pass rentre lors de son
	 * enregistrement est pareil que celui lors de sa connexion.
	 * 
	 * @param pf1
	 * @param pf2
	 * @return true si les deux mots de passe sont identiques, false sinon
	 */
	public boolean verifierPwPareil(PasswordField pf1, PasswordField pf2) {
		if (pf1.getText().equals(pf2.getText())) {
			return true;
		}
		return false;
	}

	/**
	 * Cette fonction permet de verifier si l'adresse mail rentre est valide ou
	 * pas.
	 * 
	 * @param email
	 * @return true si l'email rentre correspond a un format valide, false
	 *         sinon.
	 */
	public boolean verifierEmail(TextField email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
		return matcher.find();
	}

	public Offre faireOffre(Utilisateur utilisateur, Vendeur vendeur) {
		return null;
	}

}
