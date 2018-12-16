package fr.polytech.tours.jdbc.application.controller;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/**
 * Gestion de toutes les fenetres de cette application.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class SceneControlleur {

	private HashMap<String, Pane> sceneMap = new HashMap<>();
	private Scene index;
	/*
	 * Fonction permettant d'ajouter un paneau dans l'interface de
	 * l'application.
	 */
	protected void ajouterPane(String nom, Pane pane) {
		sceneMap.put(nom, pane);
	}
	/**
	 * Fonction qui permet de supprimer un panneau dans une fenetre en passant
	 * en parametre le nom de ce panneau.
	 * 
	 * @param nom
	 */
	protected void supprimerPane(String nom) {
		sceneMap.remove(nom);
	}
	/**
	 * Activation de la fenetre en passant en parametre le nom de celle ci.
	 * 
	 * @param nom
	 */
	public void activer(String nom) {
		index.setRoot(sceneMap.get(nom));
	}

	/**
	 * Accesseur de SceneMap.
	 * 
	 * @return sceneMap
	 */
	public HashMap<String, Pane> getSceneMap() {
		return sceneMap;
	}

	/**
	 * Mutateur de SceneMap.
	 * 
	 * @param sceneMap
	 */
	public void setSceneMap(HashMap<String, Pane> sceneMap) {
		this.sceneMap = sceneMap;
	}

	/**
	 * Accesseur de l'attribut index.
	 * 
	 * @return index
	 */
	public Scene getMain() {
		return index;
	}

	/**
	 * mutateur permettant de modifier la valeur de l'index.
	 * 
	 * @param index
	 */
	public void setMain(Scene index) {
		this.index = index;
	}

}
