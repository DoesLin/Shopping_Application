/**
 * 
 */
package fr.polytech.tours.jdbc.application.controller.invite;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.polytech.tours.jdbc.application.controller.VenteControlleur;
import fr.polytech.tours.jdbc.application.controller.dao.*;
import fr.polytech.tours.jdbc.application.model.Annonce;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe permettant de chercher des resultats d'une recherche en utilisant, la
 * classe categorie pour trier la recherche, et la classe annonce pour afficher
 * les annonces correspondants a cette recherche.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class ResultatCherNonConn {
	/**
	 * Les attributs de la classe recherche.
	 */
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
	private TableColumn<Annonce, String> desc; // Value injected by FXMLLoader

	@FXML // fx:id="seConn"
	private Button seConn; // Value injected by FXMLLoader

	@FXML // fx:id="categorie"
	private ChoiceBox<String> categorie; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="chercher"
	private Button chercher; // Value injected by FXMLLoader

	@FXML // fx:id="sInsc"
	private Button sInsc; // Value injected by FXMLLoader
	/**
	 * Constructeur a un parametre.
	 * @param venteControlleur
	 */
	public ResultatCherNonConn(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}

	/**
	 * Accesseur de venteControlleur.
	 * @return  venteControlleur
	 */
	public VenteControlleur getVenteControlleur() {
		return venteControlleur;
	}

	/**
	 * mutateur de l'attribut venteControlleur.
	 * @param venteControlleur la valeur a rentrer
	 */
	public void setVenteControlleur(VenteControlleur venteControlleur) {
		this.venteControlleur = venteControlleur;
	}

	/**
	 * Accesseur de affiche.
	 * @return the affiche
	 */
	public TableView<Annonce> getAffiche() {
		return affiche;
	}


	/**
	 * mutateur de l'attribut affiche.
	 * @param affiche la valeur a rentrer
	 */
	public void setAffiche(TableView<Annonce> affiche) {
		this.affiche = affiche;
	}
	/**
	 * Activation du bouton index.
	 * @param event
	 */
	@FXML
	void allerIndex(ActionEvent event) {
		System.out.println("allerIndex");
		venteControlleur.activer("index");
	}

	/**
	 * Activation du bouton connexion.
	 * @param event
	 */
	@FXML
	void allerConn(ActionEvent event) {
		System.out.println("allerConn");
		venteControlleur.activer("connexion");
	}

	@FXML
	void allerInsc(ActionEvent event) {
		System.out.println("allerInsc");
		venteControlleur.activer("inscription");
	}
	/**
	 * Activation du bouton resultatCherNonConn.
	 * @param event
	 */
	@FXML
	void allerResu(ActionEvent event) {
		ObservableList<Annonce> items = FXCollections
				.observableArrayList(annonceDAO.listResultat(texteCate.getText(), curCategorie));

		((ResultatCherNonConn) venteControlleur.getControlleur("resultatCherNonConn")).getAffiche().setItems(items);
		System.out.println("allerResu");
		venteControlleur.activer("resultatCherNonConn");
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
	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
	@FXML 
	void initialize() {
		assert texteCate != null : "fx:id=\"texteCate\" was not injected: check your FXML file 'ResultatCherNonConn.fxml'.";
		assert seConn != null : "fx:id=\"seConn\" was not injected: check your FXML file 'ResultatCherNonConn.fxml'.";
		assert categorie != null : "fx:id=\"categorie\" was not injected: check your FXML file 'ResultatCherNonConn.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'ResultatCherNonConn.fxml'.";
		assert chercher != null : "fx:id=\"chercher\" was not injected: check your FXML file 'ResultatCherNonConn.fxml'.";
		assert sInsc != null : "fx:id=\"sInsc\" was not injected: check your FXML file 'ResultatCherNonConn.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Index.fxml'.";
		assert cate != null : "fx:id=\"cate\" was not injected: check your FXML file 'Index.fxml'.";
		assert post != null : "fx:id=\"post\" was not injected: check your FXML file 'Index.fxml'.";
		assert tpTran != null : "fx:id=\"tpTran\" was not injected: check your FXML file 'Index.fxml'.";
		assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'Index.fxml'.";
		assert affiche != null : "fx:id=\"affiche\" was not injected: check your FXML file 'Index.fxml'.";

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
