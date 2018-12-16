package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlleur permettant de gerer le erreurs lies a la creation d'un compte.
 * @author Moutas Ribeiro et Lin
 *
 */
public class ErrCreationControlleur {

	private VenteControlleur venteControlleur;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="annuler"
    private Button annuler; // Value injected by FXMLLoader

    @FXML // fx:id="recreerCompte"
    private Button recreerCompte; // Value injected by FXMLLoader

    @FXML // fx:id="errMessage"
    private TextField errMessage; // Value injected by FXMLLoader

	public ErrCreationControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}
	
    @FXML
    void eb0404(ActionEvent event) {

    }

    @FXML
    void recreerCompte(ActionEvent event) {
		System.out.println("recreerCompte");
		venteControlleur.activer("inscription");
    }

    @FXML
    void annuler(ActionEvent event) {
		System.out.println("annuler");
		venteControlleur.activer("index");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'ErrEmail.fxml'.";
        assert recreerCompte != null : "fx:id=\"recreerCompte\" was not injected: check your FXML file 'ErrEmail.fxml'.";
        assert errMessage != null : "fx:id=\"errMessage\" was not injected: check your FXML file 'ErrEmail.fxml'.";

    }
}

