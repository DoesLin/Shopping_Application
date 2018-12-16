package fr.polytech.tours.jdbc.application.controller.gestion;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.IndexControlleur;
import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.AnnonceDAO;
import fr.polytech.tours.jdbc.application.controller.dao.CategorieDAO;
import fr.polytech.tours.jdbc.application.controller.utilisateur.*;
import fr.polytech.tours.jdbc.application.model.Annonce;
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
 * Classe permettant la gestion des annonces publies sur le site internet.
 * @author Moutas Ribeiro et Lin
 *
 */
public class GestionAnnoControlleur {

	private VenteControlleur venteControlleur;
	private AnnonceDAO annonceDAO = new AnnonceDAO();
	private CategorieDAO categorieDAO = new CategorieDAO();

	private String curCategorie = "Tout";

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="texteCate"
	private TextField texteCate; // Value injected by FXMLLoader

	@FXML // fx:id="affiche"
	private TableView<Annonce> affiche; // Value injected by FXMLLoader

	@FXML // fx:id="cate"
	private TableColumn<Annonce, String> cate; // Value injected by FXMLLoader

	@FXML // fx:id="tpTran"
	private TableColumn<Annonce, String> tpTran; // Value injected by FXMLLoader

	@FXML // fx:id="prix"
	private TableColumn<Annonce, String> prix; // Value injected by FXMLLoader

	@FXML // fx:id="nom"
	private TableColumn<Annonce, String> nom; // Value injected by FXMLLoader

	@FXML // fx:id="post"
	private TableColumn<Annonce, String> post; // Value injected by FXMLLoader

	@FXML // fx:id="desc"
	private TableColumn<Annonce, String> desc; // Value injected by FXMLLoaderr

	@FXML // fx:id="categorie"
	private ChoiceBox<String> categorie; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="annulerAnnonce"
	private Button annulerAnnonce; // Value injected by FXMLLoader

	@FXML // fx:id="creerAnnonce"
	private Button creerAnnonce; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="chercher"
	private Button chercher; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	public GestionAnnoControlleur(VenteControlleur venteControlleur) {
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

	/**
	 * @return the affiche
	 */
	public TableView<Annonce> getAffiche() {
		return affiche;
	}

	/**
	 * @param affiche
	 *            the affiche to set
	 */
	public void setAffiche(TableView<Annonce> affiche) {
		this.affiche = affiche;
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
	void annulerAnnonce(ActionEvent event) {
		Annonce annonce = affiche.getSelectionModel().getSelectedItem();
		try {
			if (!annonceDAO.delete(annonce)) {
				throw new Exception("Il y a deja des offres proposees");
			}
			ObservableList<Annonce> items = FXCollections
					.observableArrayList(annonceDAO.listPrononceAnnonces(venteControlleur.getVendeurCourant()));

			affiche.setItems(items);
			
			System.out.println("annulerAnnonce");
			venteControlleur.activer("gestAnno");

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errAnnonce");
			((ErrAnnonceControlleur) venteControlleur.getControlleur("errAnnonce")).getErrMessage()
					.setText(e.getMessage());
			venteControlleur.activer("errAnnonce");
		}

	}

	@FXML
	void creerAnnonce(ActionEvent event) {
		((AjouterAnnoControlleur) venteControlleur.getControlleur("ajouterAnno")).getTexteCompte()
				.setText(getTexteCompte().getText());

		System.out.println("creerAnnonce");
		venteControlleur.activer("ajouterAnno");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert texteCate != null : "fx:id=\"texteCate\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert affiche != null : "fx:id=\"affiche\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert annulerAnnonce != null : "fx:id=\"annulerAnnonce\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert creerAnnonce != null : "fx:id=\"creerAnnonce\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert categorie != null : "fx:id=\"categorie\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert chercher != null : "fx:id=\"chercher\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'GestionAnno.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Index.fxml'.";
		assert cate != null : "fx:id=\"cate\" was not injected: check your FXML file 'Index.fxml'.";
		assert post != null : "fx:id=\"post\" was not injected: check your FXML file 'Index.fxml'.";
		assert tpTran != null : "fx:id=\"tpTran\" was not injected: check your FXML file 'Index.fxml'.";
		assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'Index.fxml'.";

		ObservableList<String> list = FXCollections.observableArrayList(categorieDAO.listCategories());
		list.add("Tout");

		categorie.setItems(list);
		categorie.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@SuppressWarnings("rawtypes")
			public void changed(ObservableValue ov, Number value, Number new_value) {
				System.out.println("test");
				System.out.println(ov);
				System.out.println(value);
				System.out.println(new_value);
			}
		});

		ObservableList<Annonce> items = FXCollections.observableArrayList(annonceDAO.listAnnonces());

		affiche.setItems(items);

		nom.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Nom"));
		prix.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Prix"));
		cate.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Categorie"));
		post.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Position"));
		tpTran.setCellValueFactory(new PropertyValueFactory<Annonce, String>("TypeTransaction"));
		desc.setCellValueFactory(new PropertyValueFactory<Annonce, String>("DescriptionAnnonce"));
	}

}
