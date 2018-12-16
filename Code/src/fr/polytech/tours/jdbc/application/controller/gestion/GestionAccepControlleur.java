package fr.polytech.tours.jdbc.application.controller.gestion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.IndexControlleur;
import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.AnnonceDAO;
import fr.polytech.tours.jdbc.application.controller.dao.CategorieDAO;
import fr.polytech.tours.jdbc.application.controller.dao.OffreDAO;
import fr.polytech.tours.jdbc.application.controller.utilisateur.ResultatCherControlleur;
import fr.polytech.tours.jdbc.application.controller.utilisateur.UtilisateurControlleur;
import fr.polytech.tours.jdbc.application.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * Classe permettant la gestion des offres acceptes par un vendeur, lors d'une
 * offre propose par un utilisateur.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class GestionAccepControlleur {

	private VenteControlleur venteControlleur;
	private CategorieDAO categorieDAO = new CategorieDAO();
	private OffreDAO offreDAO = new OffreDAO();
	private AnnonceDAO annonceDAO = new AnnonceDAO();

	private String curCategorie = "Tout";

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="texteCate"
	private TextField texteCate; // Value injected by FXMLLoader

	@FXML // fx:id="affiche"
	private TableView<Offre> affiche; // Value injected by FXMLLoader

	@FXML // fx:id="tpTran"
	private TableColumn<Offre, String> tpTran; // Value injected by FXMLLoader

	@FXML // fx:id="prix"
	private TableColumn<Offre, String> prix; // Value injected by FXMLLoader

	@FXML // fx:id="nom"
	private TableColumn<Offre, String> nom; // Value injected by FXMLLoader

	@FXML // fx:id="util"
	private TableColumn<Offre, String> util; // Value injected by FXMLLoader

	@FXML // fx:id="adre"
	private TableColumn<Offre, String> adre; // Value injected by FXMLLoader

	@FXML // fx:id="desc"
	private TableColumn<Offre, String> desc; // Value injected by FXMLLoader

	@FXML // fx:id="acpt"
	private TableColumn<Offre, String> acpt; // Value injected by FXMLLoader

	@FXML // fx:id="categorie"
	private ChoiceBox<String> categorie; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="confirmerAcceptation"
	private Button confirmerAcceptation; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="annulerAcceptation"
	private Button annulerAcceptation; // Value injected by FXMLLoader

	@FXML // fx:id="chercher"
	private Button chercher; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	public GestionAccepControlleur(VenteControlleur venteControlleur) {
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
	 * @return the affiche
	 */
	public TableView<Offre> getAffiche() {
		return affiche;
	}

	/**
	 * @param affiche
	 *            the affiche to set
	 */
	public void setAffiche(TableView<Offre> affiche) {
		this.affiche = affiche;
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

	@FXML
	void allerIndex(ActionEvent event) {
		ObservableList<Annonce> items = FXCollections.observableArrayList(annonceDAO.listAnnonces());
		((UtilisateurControlleur) venteControlleur.getControlleur("utilisateur")).getAffiche().setItems(items);
		
		System.out.println("allerIndex");
		venteControlleur.activer("utilisateur");
	}

	@FXML
	void annulerAffi(ActionEvent event) {

	}

	@FXML
	void confirmerAffi(ActionEvent event) {

	}

	@FXML
	void commencerAffi(ActionEvent event) {

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
	void allerResu(ActionEvent event) {
		ObservableList<Annonce> items = FXCollections
				.observableArrayList(annonceDAO.listResultat(texteCate.getText(), curCategorie));

		((ResultatCherControlleur) venteControlleur.getControlleur("resultatCher")).getAffiche().setItems(items);
		((ResultatCherControlleur) venteControlleur.getControlleur("resultatCher")).getTexteCompte()
				.setText(getTexteCompte().getText());
		
		System.out.println("allerResu");
		venteControlleur.activer("resultatCher");
	}

	@FXML
	void confirmerAcceptation(ActionEvent event) {

		Offre offreConfirmee = affiche.getSelectionModel().getSelectedItem();
		offreConfirmee.setAcceptation(true);
		offreDAO.update(offreConfirmee);

		ObservableList<Offre> items = FXCollections
				.observableArrayList(offreDAO.listOffres(venteControlleur.getVendeurCourant()));

		((GestionAccepControlleur) venteControlleur.getControlleur("gestAccep")).getAffiche().setItems(items);
		((GestionAccepControlleur) venteControlleur.getControlleur("gestAccep")).getTexteCompte()
				.setText(getTexteCompte().getText());

		System.out.println("confirmerAcceptation");
		venteControlleur.activer("gestAccep");
	}

	@FXML
	void annulerAcceptation(ActionEvent event) {
		
		Offre offreConfirmee = affiche.getSelectionModel().getSelectedItem();
		offreConfirmee.setAcceptation(false);
		offreDAO.update(offreConfirmee);

		ObservableList<Offre> items = FXCollections
				.observableArrayList(offreDAO.listOffres(venteControlleur.getVendeurCourant()));

		((GestionAccepControlleur) venteControlleur.getControlleur("gestAccep")).getAffiche().setItems(items);
		((GestionAccepControlleur) venteControlleur.getControlleur("gestAccep")).getTexteCompte()
				.setText(getTexteCompte().getText());

		System.out.println("annulerAcceptation");
		venteControlleur.activer("gestAccep");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert texteCate != null : "fx:id=\"texteCate\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert affiche != null : "fx:id=\"affiche\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert categorie != null : "fx:id=\"categorie\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert confirmerAcceptation != null : "fx:id=\"confirmerAcceptation\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert chercher != null : "fx:id=\"chercher\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert annulerAcceptation != null : "fx:id=\"annulerAcceptation\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'GestionAccep.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'GestionOffre.fxml'.";
		assert adre != null : "fx:id=\"adre\" was not injected: check your FXML file 'GestionOffre.fxml'.";
		assert util != null : "fx:id=\"util\" was not injected: check your FXML file 'GestionOffre.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'GestionOffre.fxml'.";
		assert prix != null : "fx:id=\"prix\" was not injected: check your FXML file 'GestionOffre.fxml'.";
		assert tpTran != null : "fx:id=\"tpTran\" was not injected: check your FXML file 'GestionOffre.fxml'.";
		assert acpt != null : "fx:id=\"acpt\" was not injected: check your FXML file 'GestionAccep.fxml'.";

		List<String> listCate = categorieDAO.listCategories();
		listCate.add("Tout");
		ObservableList<String> list = FXCollections.observableArrayList(listCate);

		categorie.setItems(list);
		categorie.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@SuppressWarnings("rawtypes")
			public void changed(ObservableValue ov, Number value, Number new_value) {
				curCategorie = listCate.get((int) new_value);
				System.out.println(curCategorie);
			}
		});

		nom.setCellValueFactory(new PropertyValueFactory<Offre, String>("nom"));
		prix.setCellValueFactory(new PropertyValueFactory<Offre, String>("prix"));
		util.setCellValueFactory(new PropertyValueFactory<Offre, String>("util"));
		adre.setCellValueFactory(new PropertyValueFactory<Offre, String>("adre"));
		tpTran.setCellValueFactory(new PropertyValueFactory<Offre, String>("tpTran"));
		desc.setCellValueFactory(new PropertyValueFactory<Offre, String>("desc"));
		acpt.setCellValueFactory(new PropertyValueFactory<Offre, String>("Acceptation"));

	}

}
