package fr.polytech.tours.jdbc.application.controller.invite;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.*;
import fr.polytech.tours.jdbc.application.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * Cette classe permet de gerer toutes les personnes qui ne sont pas enregistres
 * dans le site internet mais qui peuvent consulter les annonces.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class InscriptionControlleur {

	private VenteControlleur venteControlleur;
	private PersonneDAO personneDAO = new PersonneDAO();
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="creerComp"
	private Button creerComp; // Value injected by FXMLLoader

	@FXML // fx:id="reMot2Passe"
	private PasswordField reMot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="mot2Passe"
	private PasswordField mot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="prenom"
	private TextField prenom; // Value injected by FXMLLoader

	@FXML // fx:id="nom"
	private TextField nom; // Value injected by FXMLLoader

	@FXML // fx:id="email"
	private TextField email; // Value injected by FXMLLoader

	public InscriptionControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}
	/**
	 * Fonction permettant de s'enregistrer dans le site internet et devenir un utilisateur.
	 * @param event
	 */
	@FXML
	void creerCompte(ActionEvent event) {
		try {
			if (venteControlleur.verifierTextField(prenom) && venteControlleur.verifierTextField(nom)
					&& venteControlleur.verifierTextField(email) 
					&& venteControlleur.verifierPwField(mot2Passe)
					&& venteControlleur.verifierPwField(reMot2Passe)
					&& venteControlleur.verifierEmail(email)
					&& venteControlleur.verifierPwPareil(mot2Passe, reMot2Passe)) {
				Personne personne = new Personne(email.getText(), nom.getText(), prenom.getText(), mot2Passe.getText());
				if (personneDAO.insert(personne) == false) {
					throw new Exception("Duplicata personne");
				}

				Utilisateur utilisateur = new Utilisateur(personne);
				if (utilisateurDAO.insert(utilisateur) == false) {
					throw new Exception("Duplicata utilisateur");
				}

				venteControlleur.setPersonneCourant(personne);
				System.out.println("creerCompte");
				venteControlleur.activer("utilisateur");
			} else {
				throw new Exception("Fault entrees");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errCreerCompte");
			venteControlleur.activer("errCreation");
		}

	}
	/**
	 * Fonction permettant d'annuler le compte utilisateur.
	 * @param event
	 */
	@FXML
	void annulerCompte(ActionEvent event) {
		System.out.println("annulerCompte");
		venteControlleur.activer("index");
	}
	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
	@FXML 
	void initialize() {
		assert creerComp != null : "fx:id=\"creerComp\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert reMot2Passe != null : "fx:id=\"reMot2Passe\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert mot2Passe != null : "fx:id=\"mot2Passe\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Inscription.fxml'.";

	}
}
