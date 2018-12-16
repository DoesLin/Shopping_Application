package fr.polytech.tours.jdbc.application.controller.gestion;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.PersonneDAO;
import fr.polytech.tours.jdbc.application.controller.utilisateur.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Classe permettant de faire la gestion des personnes utilisant le site internet.
 * @author Moutas Ribeiro et Lin
 *
 */
public class GestionPersControlleur {

	private VenteControlleur venteControlleur;
	private PersonneDAO personneDAO = new PersonneDAO();

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="modifierCompte"
	private Button modifierCompte; // Value injected by FXMLLoader

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

	public GestionPersControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}

	/**
	 * @return the prenom
	 */
	public TextField getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(TextField prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the nom
	 */
	public TextField getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(TextField nom) {
		this.nom = nom;
	}

	@FXML
	void modifierCompte(ActionEvent event) {

		try {
			if (venteControlleur.verifierTextField(prenom) && venteControlleur.verifierTextField(nom)
					&& venteControlleur.verifierPwField(mot2Passe) && venteControlleur.verifierPwField(reMot2Passe)
					&& venteControlleur.verifierPwPareil(mot2Passe, reMot2Passe)) {

				venteControlleur.getPersonneCourant().setPrenom(prenom.getText());
				venteControlleur.getPersonneCourant().setNom(nom.getText());
				venteControlleur.getPersonneCourant().setMot2Passe(mot2Passe.getText());

				personneDAO.update(venteControlleur.getPersonneCourant());
				System.out.println("modifierCompte");
				venteControlleur.activer("utilisateur");
			} else {
				throw new Exception("Fault entrees");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errModifierCompte");
			((ErrModificationControlleur) venteControlleur.getControlleur("errModification")).getErrMessage()
					.setText(e.getMessage());
			venteControlleur.activer("errModification");
		}

	}

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("annuler");
		venteControlleur.activer("utilisateur");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert modifierCompte != null : "fx:id=\"modifierCompte\" was not injected: check your FXML file 'GestionPers.fxml'.";
		assert reMot2Passe != null : "fx:id=\"reMot2Passe\" was not injected: check your FXML file 'GestionPers.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'GestionPers.fxml'.";
		assert mot2Passe != null : "fx:id=\"mot2Passe\" was not injected: check your FXML file 'GestionPers.fxml'.";
		assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'GestionPers.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'GestionPers.fxml'.";

	}
}
