/**
 * 
 */
package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.IndexControlleur;
import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.*;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionPersControlleur;
import fr.polytech.tours.jdbc.application.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
/**
 * Controlleur permettant de gerer les actions de proposition d'offre.
 * @author Moutas Ribeiro et Lin
 *
 */
public class FaireOffreControlleur {

	private VenteControlleur venteControlleur;
	private OffreDAO offreDAO = new OffreDAO();
	private AnnonceDAO annonceDAO = new AnnonceDAO();

	private Offre offreDemandee;
	
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

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="confirmerOffre"
	private Button confirmerOffre; // Value injected by FXMLLoader

	@FXML // fx:id="description"
	private TextArea description; // Value injected by FXMLLoader

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

	public FaireOffreControlleur(VenteControlleur venteControlleur) {
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

	/**
	 * @return the offreDemandee
	 */
	public Offre getOffreDemandee() {
		return offreDemandee;
	}

	/**
	 * @param offreDemandee the offreDemandee to set
	 */
	public void setOffreDemandee(Offre offreDemandee) {
		this.offreDemandee = offreDemandee;
	}

	/**
	 * @return the texteCompte
	 */
	public TextField getTexteCompte() {
		return texteCompte;
	}

	/**
	 * @param texteCompte
	 *            the texteCompte to set
	 */
	public void setTexteCompte(TextField texteCompte) {
		this.texteCompte = texteCompte;
	}

	/**
	 * @return the typeTransaction
	 */
	public TextField getTypeTransaction() {
		return typeTransaction;
	}

	/**
	 * @param typeTransaction
	 *            the typeTransaction to set
	 */
	public void setTypeTransaction(TextField typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	/**
	 * @return the ville
	 */
	public TextField getVille() {
		return ville;
	}

	/**
	 * @param ville
	 *            the ville to set
	 */
	public void setVille(TextField ville) {
		this.ville = ville;
	}

	/**
	 * @return the numRue
	 */
	public TextField getNumRue() {
		return numRue;
	}

	/**
	 * @param numRue
	 *            the numRue to set
	 */
	public void setNumRue(TextField numRue) {
		this.numRue = numRue;
	}

	/**
	 * @return the description
	 */
	public TextArea getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(TextArea description) {
		this.description = description;
	}

	/**
	 * @return the cp
	 */
	public TextField getCp() {
		return cp;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(TextField cp) {
		this.cp = cp;
	}

	/**
	 * @return the nomRue
	 */
	public TextField getNomRue() {
		return nomRue;
	}

	/**
	 * @param nomRue
	 *            the nomRue to set
	 */
	public void setNomRue(TextField nomRue) {
		this.nomRue = nomRue;
	}

	/**
	 * @return the pays
	 */
	public TextField getPays() {
		return pays;
	}

	/**
	 * @param pays
	 *            the pays to set
	 */
	public void setPays(TextField pays) {
		this.pays = pays;
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
		GestionPersControlleur gestPers = (GestionPersControlleur) venteControlleur.getControlleur("gestPers");
		gestPers.getNom().setText(venteControlleur.getPersonneCourant().getNom());
		gestPers.getPrenom().setText(venteControlleur.getPersonneCourant().getPrenom());
		
		System.out.println("gererCompte");
		venteControlleur.activer("gestPers");
	}

	@FXML
	void allerIndex(ActionEvent event) {
		ObservableList<Annonce> items = FXCollections.observableArrayList(annonceDAO.listAnnonces());
		((UtilisateurControlleur) venteControlleur.getControlleur("utilisateur")).getAffiche().setItems(items);
		
		System.out.println("allerIndex");
		venteControlleur.activer("utilisateur");
	}

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("allerIndex");
		venteControlleur.activer("utilisateur");
	}

	@FXML
	void confirmerOffre(ActionEvent event) {
		offreDemandee.setAcceptation(false);
		offreDAO.insert(offreDemandee);
		
		System.out.println("confirmerOffre");
		venteControlleur.activer("utilisateur");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert typeTransaction != null : "fx:id=\"typeTransaction\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert nomRue != null : "fx:id=\"nomRue\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert ville != null : "fx:id=\"ville\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert numRue != null : "fx:id=\"numRue\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert confirmerOffre != null : "fx:id=\"confirmerOffre\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert cp != null : "fx:id=\"cp\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert pays != null : "fx:id=\"pays\" was not injected: check your FXML file 'FaireOffre.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";

	}
}
