package fr.polytech.tours.jdbc.application.controller.utilisateur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.IndexControlleur;
import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.AnnonceDAO;
import fr.polytech.tours.jdbc.application.controller.dao.CategorieDAO;
import fr.polytech.tours.jdbc.application.controller.dao.OffreDAO;
import fr.polytech.tours.jdbc.application.controller.dao.PersonneDAO;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionAccepControlleur;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionAnnoControlleur;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionOffreControlleur;
import fr.polytech.tours.jdbc.application.controller.gestion.GestionPersControlleur;
import fr.polytech.tours.jdbc.application.model.Annonce;
import fr.polytech.tours.jdbc.application.model.Offre;
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
 * Controlleur permettant de gerer les actions lors de la recherche d'une annonce.
 * @author Moutas Ribeiro et Lin
 *
 */
public class ResultatCherControlleur {

	private VenteControlleur venteControlleur;
	private AnnonceDAO annonceDAO = new AnnonceDAO();
	private CategorieDAO categorieDAO = new CategorieDAO();
	private PersonneDAO personneDAO = new PersonneDAO();
	private OffreDAO offreDAO = new OffreDAO();

	private String curCategorie = "Tout";

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="texteCate"
	private TextField texteCate; // Value injected by FXMLLoader

	@FXML // fx:id="categorie"
	private ChoiceBox<String> categorie; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="gererAnnonce"
	private Button gererAnnonce; // Value injected by FXMLLoader

	@FXML // fx:id="gererAcceptation"
	private Button gererAcceptation; // Value injected by FXMLLoader

	@FXML // fx:id="chercher"
	private Button chercher; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	@FXML // fx:id="voirOffres"
	private Button voirOffres; // Value injected by FXMLLoader

	@FXML // fx:id="commander"
	private Button commander; // Value injected by FXMLLoader

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
	private TableColumn<Annonce, String> desc; // Value injected by FXMLLoader

	public ResultatCherControlleur(VenteControlleur venteControlleur) {
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
	void annulerAffi(ActionEvent event) {

	}

	@FXML
	void confirmerAffi(ActionEvent event) {

	}

	@FXML
	void commencerAffi(ActionEvent event) {

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
	void gererAnno(ActionEvent event) {
		venteControlleur.setVendeurCourant(personneDAO.genererVendeur(venteControlleur.getPersonneCourant()));

		ObservableList<Annonce> items = FXCollections
				.observableArrayList(annonceDAO.listPrononceAnnonces(venteControlleur.getVendeurCourant()));

		((GestionAnnoControlleur) venteControlleur.getControlleur("gestAnno")).getAffiche().setItems(items);
		((GestionAnnoControlleur) venteControlleur.getControlleur("gestAnno")).getTexteCompte()
				.setText(getTexteCompte().getText());

		System.out.println("gererAnno");
		venteControlleur.activer("gestAnno");
	}

	@FXML
	void gererAcpt(ActionEvent event) {
		venteControlleur.setVendeurCourant(personneDAO.genererVendeur(venteControlleur.getPersonneCourant()));

		ObservableList<Offre> items = FXCollections
				.observableArrayList(offreDAO.listOffres(venteControlleur.getVendeurCourant()));

		((GestionAccepControlleur) venteControlleur.getControlleur("gestAccep")).getAffiche().setItems(items);
		((GestionAccepControlleur) venteControlleur.getControlleur("gestAccep")).getTexteCompte()
				.setText(getTexteCompte().getText());

		System.out.println("gererAcpt");
		venteControlleur.activer("gestAccep");
	}

	@FXML
	void voirOffres(ActionEvent event) {
		venteControlleur.setVendeurCourant(personneDAO.genererVendeur(venteControlleur.getPersonneCourant()));

		ObservableList<Offre> items = FXCollections
				.observableArrayList(offreDAO.listMesOffres(venteControlleur.getUtilisateurCourant()));

		((GestionOffreControlleur) venteControlleur.getControlleur("gestOffr")).getAffiche().setItems(items);
		((GestionOffreControlleur) venteControlleur.getControlleur("gestOffr")).getTexteCompte()
				.setText(getTexteCompte().getText());

		System.out.println("voirOffres");
		venteControlleur.activer("gestOffr");
	}

	@FXML
	void commander(ActionEvent event) {
		Annonce annonce = affiche.getSelectionModel().getSelectedItem();

		FaireOffreControlleur commander = ((FaireOffreControlleur) venteControlleur.getControlleur("faireOffre"));
		commander.getTexteCompte().setText(getTexteCompte().getText());
		commander.getNumRue().setText(Integer.toString(annonce.getPosition().getNRue()));
		commander.getNomRue().setText(annonce.getPosition().getNomRue());
		commander.getCp().setText(Integer.toString(annonce.getPosition().getCodePostal()));
		commander.getVille().setText(annonce.getPosition().getVille());
		commander.getPays().setText(annonce.getPosition().getPays());
		commander.getDescription().setText(annonce.getDescriptionAnnonce());
		commander.getDescription().setWrapText(true);
		commander.getTypeTransaction().setText(annonce.getTypeTransaction());

		Offre offreDemandee = new Offre(annonce, venteControlleur.getUtilisateurCourant());
		commander.setOffreDemandee(offreDemandee);

		System.out.println("commander");
		venteControlleur.activer("faireOffre");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert texteCate != null : "fx:id=\"texteCate\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert categorie != null : "fx:id=\"categorie\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert gererAnnonce != null : "fx:id=\"gererAnnonce\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert gererAcceptation != null : "fx:id=\"gererAcceptation\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert chercher != null : "fx:id=\"chercher\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'ResultatCher.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert commander != null : "fx:id=\"commander\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert affiche != null : "fx:id=\"affiche\" was not injected: check your FXML file 'Index.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Index.fxml'.";
		assert cate != null : "fx:id=\"cate\" was not injected: check your FXML file 'Index.fxml'.";
		assert post != null : "fx:id=\"post\" was not injected: check your FXML file 'Index.fxml'.";
		assert tpTran != null : "fx:id=\"tpTran\" was not injected: check your FXML file 'Index.fxml'.";
		assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'Index.fxml'.";
		assert voirOffres != null : "fx:id=\"voirOffres\" was not injected: check your FXML file 'Utilisateur.fxml'.";

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

		nom.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Nom"));
		prix.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Prix"));
		cate.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Categorie"));
		post.setCellValueFactory(new PropertyValueFactory<Annonce, String>("Position"));
		tpTran.setCellValueFactory(new PropertyValueFactory<Annonce, String>("TypeTransaction"));
		desc.setCellValueFactory(new PropertyValueFactory<Annonce, String>("DescriptionAnnonce"));

	}

}
