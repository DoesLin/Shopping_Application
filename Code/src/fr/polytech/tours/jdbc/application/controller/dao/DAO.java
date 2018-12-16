package fr.polytech.tours.jdbc.application.controller.dao;

import java.sql.Connection;
/**
 * Classe abstraite contenant la structure commune aux classes qui en derivent.
 * Chacune d'entre elles implementera les fonctions qui seront declares dans
 * cette classe les adaptant a leur cas specifique.
 * 
 * @author Catarina
 *
 * @param <T>
 */
public abstract class DAO<T> {
	protected Connection connect = null;
	 
	 public DAO(){
		 
	}

	 /**
	  * Methode de creation
	  * @param obj
	  * @return boolean 
	  */
	  public abstract boolean insert(T obj);

	  /**
	  * Methode pour effacer
	  * @param obj
	  * @return boolean 
	  */
	  public abstract boolean delete(T obj);

	  /**
	  * Methode de mise a jour
	  * @param obj
	  * @return boolean
	  */
	  public abstract boolean update(T obj1);

	  /**
	  * Methode de recherche des informations
	  * @param id
	  * @return T
	  */
	  public abstract T find(int id);

}
