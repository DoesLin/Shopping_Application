/**
 * 
 */
package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.IndexControlleur;
import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.AnnonceDAO;
import fr.polytech.tours.jdbc.application.model.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
/**
 * Controlleur permettant de gerer lorsqu'un vendeur veut accepter une offre.
 * @author Moutas Ribeiro et Lin
 *
 */
public class AccepterOffreControlleur {

	private VenteControlleur venteControlleur;
	private AnnonceDAO annonceDAO = new AnnonceDAO(); 

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="typeTransaction"
	private TextField typeTransaction; // Value injected by FXMLLoader

	@FXML // fx:id="ville"
	private TextField ville; // Value injected by FXMLLoader

	@FXML // fx:id="numRue"
	private TextField numRue; // Value injected by FXMLLoader

	@FXML // fx:id="prix"
	private TextField prix; // Value injected by FXMLLoader

	@FXML // fx:id="personne"
	private TextField personne; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="accpterOffre"
	private Button accpterOffre; // Value injected by FXMLLoader

	@FXML // fx:id="description"
	private TextField description; // Value injected by FXMLLoader

	@FXML // fx:id="cp"
	private TextField cp; // Value injected by FXMLLoader

	@FXML // fx:id="nomRue"
	private TextField nomRue; // Value injected by FXMLLoader

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="pays"
	private TextField pays; // Value injected by FXMLLoader

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	public AccepterOffreControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}

	/**
	 * @return the venteControlleur
	 */
	public VenteControlleur getVenteControlleur() {
		return venteControlleur;
	}

	/**
	 * @param venteControlleur
	 *            the venteControlleur to set
	 */
	public void setVenteControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}

	@FXML
	void allerIndex(ActionEvent event) {
		ObservableList<Annonce> items = FXCollections.observableArrayList(annonceDAO.listAnnonces());
		((UtilisateurControlleur) venteControlleur.getControlleur("utilisateur")).getAffiche().setItems(items);
		
		System.out.println("allerIndex");
		venteControlleur.activer("utilisateur");
	}

	@FXML
	void seDeconnecter(ActionEvent event) {
		venteControlleur.setPersonneCourant(null);
		venteControlleur.setUtilisateurCourant(null);
		venteControlleur.setVendeurCourant(null);
		ObservableList<Annonce> items = FXCollections.observableArrayList(annonceDAO.listAnnonces());
		((IndexControlleur) venteControlleur.getControlleur("index")).getAffiche().setItems(items);
		
		System.out.println("seDeconnecter");
		venteControlleur.activer("index");
	}

	@FXML
	void gererCompte(ActionEvent event) {
		System.out.println("gererCompte");
		venteControlleur.activer("gestPers");
	}

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("allerIndex");
		venteControlleur.activer("utilisateur");
	}

	@FXML
	void accpterOffre(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert ville != null : "fx:id=\"ville\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert numRue != null : "fx:id=\"numRue\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert prix != null : "fx:id=\"prix\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert personne != null : "fx:id=\"personne\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert cp != null : "fx:id=\"cp\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert typeTransaction != null : "fx:id=\"typeTransaction\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert nomRue != null : "fx:id=\"nomRue\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert accpterOffre != null : "fx:id=\"accpterOffre\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert pays != null : "fx:id=\"pays\" was not injected: check your FXML file 'AccepterOffre.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";

	}
}
