package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.IndexControlleur;
import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.*;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionAnnoControlleur;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionPersControlleur;
import fr.polytech.tours.jdbc.application.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * Controlleur permettant de gerer l'action d'ajouter une annonce.
 * @author Moutas Ribeiro et Lin
 *
 */
public class AjouterAnnoControlleur {

	private VenteControlleur venteControlleur;

	private CategorieDAO categorieDAO = new CategorieDAO();
	private AdresseDAO adresseDAO = new AdresseDAO();
	private AnnonceDAO annonceDAO = new AnnonceDAO();
	private PersonneDAO personneDAO = new PersonneDAO();

	private String curCategorie = "Tout";

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="nom"
	private TextField nom; // Value injected by FXMLLoader

	@FXML // fx:id="ville"
	private TextField ville; // Value injected by FXMLLoader

	@FXML // fx:id="categorie"
	private ChoiceBox<String> categorie; // Value injected by FXMLLoader

	@FXML // fx:id="tpTran"
	private TextField tpTran; // Value injected by FXMLLoader

	@FXML // fx:id="numRue"
	private TextField numRue; // Value injected by FXMLLoader

	@FXML // fx:id="prix"
	private TextField prix; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="creerAnnonce"
	private Button creerAnnonce; // Value injected by FXMLLoader

	@FXML // fx:id="description"
	private TextField description; // Value injected by FXMLLoader

	@FXML // fx:id="cp"
	private TextField cp; // Value injected by FXMLLoader

	@FXML // fx:id="nomRue"
	private TextField nomRue; // Value injected by FXMLLoader

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="pays"
	private TextField pays; // Value injected by FXMLLoader

	public AjouterAnnoControlleur(VenteControlleur venteControlleur) {
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
	void creerAnnonce(ActionEvent event) {

		try {
			Adresse position = new Adresse(Integer.parseInt(numRue.getText()), nomRue.getText(),
					Integer.parseInt(cp.getText()), ville.getText(), pays.getText());
			Categorie cate = categorieDAO.findCategorie(curCategorie);
			
			if (cate == null) {
				throw new Exception("Vous devez choisir une categorie");
			}
			
			if (adresseDAO.findID(position) != null) {
			} else {
				if(!adresseDAO.insert(position)) {
					throw new Exception("Fault position");
				}
			}
			position = adresseDAO.findID(position);
			
			venteControlleur.getPersonneCourant().setAdresse(position);
			if(!personneDAO.update(venteControlleur.getPersonneCourant())) {
				throw new Exception("Echec a mettre a jour l'adresse de personne");
			}
			
			Annonce annonce = new Annonce(nom.getText(), tpTran.getText(), position, Float.parseFloat(prix.getText()),
					description.getText(), cate, venteControlleur.getVendeurCourant());

			if(!annonceDAO.insert(annonce)) {
				throw new Exception("Fault annonce");
			}
			ObservableList<Annonce> items = FXCollections
					.observableArrayList(annonceDAO.listPrononceAnnonces(venteControlleur.getVendeurCourant()));

			((GestionAnnoControlleur) venteControlleur.getControlleur("gestAnno")).getAffiche().setItems(items);
			((GestionAnnoControlleur) venteControlleur.getControlleur("gestAnno")).getTexteCompte()
					.setText(getTexteCompte().getText());

			System.out.println("creerAnnonce");
			venteControlleur.activer("gestAnno");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errAnnonce");
			((ErrAnnonceControlleur) venteControlleur.getControlleur("errAnnonce")).getErrMessage()
					.setText(e.getMessage());
			venteControlleur.activer("errAnnonce");
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert ville != null : "fx:id=\"ville\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert categorie != null : "fx:id=\"categorie\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert tpTran != null : "fx:id=\"tpTran\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert numRue != null : "fx:id=\"numRue\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert prix != null : "fx:id=\"prix\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert creerAnnonce != null : "fx:id=\"creerAnnonce\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert cp != null : "fx:id=\"cp\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert nomRue != null : "fx:id=\"nomRue\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'AjouterAnno.fxml'.";
		assert pays != null : "fx:id=\"pays\" was not injected: check your FXML file 'AjouterAnno.fxml'.";

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
	}
}
