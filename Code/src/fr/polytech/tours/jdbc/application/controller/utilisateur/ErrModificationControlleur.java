package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Controlleur permettant de gerer le erreurs lies aux modifications faite sur un compte utilisateur.
 * @author Moutas Ribeiro et Lin
 *
 */
public class ErrModificationControlleur {

	private VenteControlleur venteControlleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="remodifierCompte"
	private Button remodifierCompte; // Value injected by FXMLLoader

	@FXML // fx:id="errMessage"
	private TextField errMessage; // Value injected by FXMLLoader

	/**
	 * @return the errMessage
	 */
	public TextField getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage
	 *            the errMessage to set
	 */
	public void setErrMessage(TextField errMessage) {
		this.errMessage = errMessage;
	}

	public ErrModificationControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}

	@FXML
	void eb0404(ActionEvent event) {

	}

	@FXML
	void remodifierCompte(ActionEvent event) {
		System.out.println("remodifierCompte");
		venteControlleur.activer("gestPers");
	}

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("annuler");
		venteControlleur.activer("utilisateur");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'ErrEmail.fxml'.";
		assert remodifierCompte != null : "fx:id=\"remodifierCompte\" was not injected: check your FXML file 'ErrModifier.fxml'.";
		assert errMessage != null : "fx:id=\"errMessage\" was not injected: check your FXML file 'ErrEmail.fxml'.";

	}
}
