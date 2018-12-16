package fr.polytech.tours.jdbc.application;

import java.io.IOException;
import java.util.HashMap;

import fr.polytech.tours.jdbc.application.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'application.
 * @author Moutas Ribeiro et Lin
 *
 */
public class VenteApplication extends Application {

	private VenteControlleur controller;
	private Scene indexScene;
	private HashMap<String, Pane> sceneMap = new HashMap<>();

	public static void main(String[] args) {

		launch(args);
		
	}

	private void initScene() throws IOException {

		controller = new VenteControlleur();

		String cheminVue = "./view/";

		FXMLLoader indexLoader = new FXMLLoader(getClass().getResource(cheminVue + "Index.fxml"));
		indexLoader.setController(controller.getControlleur("index"));
		Pane indexPane = indexLoader.load();
		indexScene = new Scene((Parent) indexPane);
		sceneMap.put("index", indexPane);

		FXMLLoader loader;
		Pane pane;

		// gestion
		cheminVue = "./view/gestion/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "GestionAccep.fxml"));
		loader.setController(controller.getControlleur("gestAccep"));
		pane = loader.load();
		sceneMap.put("gestAccep", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "GestionAnno.fxml"));
		loader.setController(controller.getControlleur("gestAnno"));
		pane = loader.load();
		sceneMap.put("gestAnno", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "GestionPers.fxml"));
		loader.setController(controller.getControlleur("gestPers"));
		pane = loader.load();
		sceneMap.put("gestPers", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "GestionOffre.fxml"));
		loader.setController(controller.getControlleur("gestOffr"));
		pane = loader.load();
		sceneMap.put("gestOffr", pane);

		// invite
		cheminVue = "./view/invite/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "Inscription.fxml"));
		loader.setController(controller.getControlleur("inscription"));
		pane = loader.load();
		sceneMap.put("inscription", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ResultatCherNonConn.fxml"));
		loader.setController(controller.getControlleur("resultatCherNonConn"));
		pane = loader.load();
		sceneMap.put("resultatCherNonConn", pane);

		// utilisateur
		cheminVue = "./view/utilisateur/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "AccepterOffre.fxml"));
		loader.setController(controller.getControlleur("acptOffre"));
		pane = loader.load();
		sceneMap.put("acptOffre", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "AjouterAnno.fxml"));
		loader.setController(controller.getControlleur("ajouterAnno"));
		pane = loader.load();
		sceneMap.put("ajouterAnno", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "Connexion.fxml"));
		loader.setController(controller.getControlleur("connexion"));
		pane = loader.load();
		sceneMap.put("connexion", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrAnnonce.fxml"));
		loader.setController(controller.getControlleur("errAnnonce"));
		pane = loader.load();
		sceneMap.put("errAnnonce", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrConnexion.fxml"));
		loader.setController(controller.getControlleur("errConnexion"));
		pane = loader.load();
		sceneMap.put("errConnexion", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrCreation.fxml"));
		loader.setController(controller.getControlleur("errCreation"));
		pane = loader.load();
		sceneMap.put("errCreation", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrModification.fxml"));
		loader.setController(controller.getControlleur("errModification"));
		pane = loader.load();
		sceneMap.put("errModification", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "FaireOffre.fxml"));
		loader.setController(controller.getControlleur("faireOffre"));
		pane = loader.load();
		sceneMap.put("faireOffre", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ResultatCher.fxml"));
		loader.setController(controller.getControlleur("resultatCher"));
		pane = loader.load();
		sceneMap.put("resultatCher", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "Utilisateur.fxml"));
		loader.setController(controller.getControlleur("utilisateur"));
		pane = loader.load();
		sceneMap.put("utilisateur", pane);

		controller.setMain(indexScene);
		controller.setSceneMap(sceneMap);

	}

	/**
	 * Fonction de lancement de l'application, avec la mise en place du controlleur,
	 * la lecture du fichier fxml et affichage de l'application.
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Start");

			initScene();

			primaryStage.setTitle("Vente Appliction");
			primaryStage.setScene(indexScene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fermeture de l'application ainsi que des dependances dont elle avait besoin.
	 * 
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() {
		System.out.println("End");
	}

}