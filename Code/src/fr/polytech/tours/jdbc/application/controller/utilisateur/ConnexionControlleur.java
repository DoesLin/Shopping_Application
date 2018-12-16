package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.*;
import fr.polytech.tours.jdbc.application.model.Personne;
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
public class ConnexionControlleur {

	private VenteControlleur venteControlleur;
	private PersonneDAO personneDAO = new PersonneDAO();
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="seConnecter"
	private Button seConnecter; // Value injected by FXMLLoader

	@FXML // fx:id="mot2Passe"
	private PasswordField mot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="email"
	private TextField email; // Value injected by FXMLLoader

	public ConnexionControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}
	/**
	 * Fonction permettant de se connecter a l'application.
	 * @param event
	 */
	@FXML
	void seConnecter(ActionEvent event) {

		try {
			if (venteControlleur.verifierTextField(email) && venteControlleur.verifierPwField(mot2Passe)
					&& venteControlleur.verifierEmail(email)) {

				Personne personne = personneDAO.find(email.getText());
				if (personne == null) {
					throw new Exception("Fault personne");
				}

				if (!personneDAO.find(email.getText()).getMot2Passe().equals(mot2Passe.getText())) {
					throw new Exception("Fault mot2Passe");
				}

				if (utilisateurDAO.findPersonne(email.getText()) == null) {
					throw new Exception("Fault utilisateur");
				}


				UtilisateurControlleur utilControlleur = ((UtilisateurControlleur) venteControlleur
						.getControlleur("utilisateur"));
				utilControlleur.getTexteCompte().setText("Bonjour " + personne.getPrenom() + " !");
				venteControlleur.setPersonneCourant(personne);
				venteControlleur.setUtilisateurCourant(personneDAO.genererUtilisateur(personne));
				
				System.out.println("seConnecter");
				venteControlleur.activer("utilisateur");
			} else {
				throw new Exception("Fault entrees");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errConnexion");
			((ErrConnexionControlleur) venteControlleur.getControlleur("errConnexion")).getErrMessage()
					.setText(e.getMessage());
			venteControlleur.activer("errConnexion");
		}

	}

	@FXML
	void annulerConnexion(ActionEvent event) {
		System.out.println("annulerConnexion");
		venteControlleur.activer("index");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'Connexion.fxml'.";
		assert mot2Passe != null : "fx:id=\"mot2Passe\" was not injected: check your FXML file 'Connexion.fxml'.";
		assert seConnecter != null : "fx:id=\"seConnecter\" was not injected: check your FXML file 'Connexion.fxml'.";
		assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Connexion.fxml'.";

	}
}
