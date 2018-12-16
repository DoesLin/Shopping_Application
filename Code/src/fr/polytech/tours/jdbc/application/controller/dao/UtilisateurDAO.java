package fr.polytech.tours.jdbc.application.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.polytech.tours.jdbc.application.model.*;
/**
 * Cette classe permet d'extraire les methodes necessaires a la creation des
 * objects definis/crees dans notre model concernant les utilisateurs, puis en
 * mettre a jour la base de donnees.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class UtilisateurDAO extends DAO<Utilisateur> {
	/**
	 * Attributs : connection, ps, rs ces derniers sont utlisees dans plusieurs
	 * endroits dans cette classe donc ils sont definis comme etant des
	 * attributs de la classe.
	 */
	private static ConnectionDAO connection;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	/**
	 * Methode permettant d'avoir la connexion.
	 * 
	 * @return conn
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionDAO.getInstance().getConnection();
		return conn;
	}

	/**
	 * Fonction permettant d'inserer un utilisateur dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean insert(Utilisateur utilisateur) {


		try {
			if(utilisateur.getIDUtilisateur() == -1 ) {
				String sql = "INSERT INTO Utilisateur(Personne) VALUES (?)";
				ps = getConnection().prepareStatement(sql);
				ps.setString(1, utilisateur.getIDPersonne());
			} else {
				String sql = "INSERT INTO Utilisateur(IdUtilisateur, Personne) VALUES (?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, utilisateur.getIDUtilisateur());
				ps.setString(2, utilisateur.getIDPersonne());
			}
			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Utilisateur avec succes !");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					getConnection().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * Fonction permettant de supprimer un utilisateur dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean delete(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Utilisateur WHERE IDUtilisateur = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, utilisateur.getIDUtilisateur());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Utilisateur ont ete supprime avec succes !");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					getConnection().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * Fonction permettant de mettre a jour un utilisateur dans la base de
	 * donnes. retourne true si insere, false sinon.
	 */
	@Override
	public boolean update(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Utilisateur SET Personne = ? WHERE IDUtilisateur = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, utilisateur.getIDPersonne());
			ps.setInt(2, utilisateur.getIDUtilisateur());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Utilisateur ont ete mises a jour  avec succes !");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					getConnection().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * Fonction permettant de chercher un utilisateur dans la base de donnes par
	 * son identifiant. retourne utilisateur
	 */
	@Override
	public Utilisateur find(int iDUtilisateur) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Utilisateur WHERE IDUtilisateur = ?" ;
		Utilisateur utilisateur = new Utilisateur();
		Personne personne = new Personne();
		PersonneDAO personneDAO = new PersonneDAO();
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, iDUtilisateur);
			rs = ps.executeQuery();
			if(rs.first()){
				utilisateur = new Utilisateur(iDUtilisateur);
				personne = personneDAO.find(rs.getString(2));
				utilisateur.setIDPersonne(personne);
				return utilisateur;
			}
		
			System.out.println("L'utilisateur a ete trouve avec succes !");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					getConnection().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Fonction permettant de chercher une personne par son identifiant, par le
	 * parametre en String qui represente son id.
	 * 
	 * @param iDPersonne
	 * @return Utilisateur
	 */
	public Utilisateur findPersonne(String iDPersonne) {

		String sql = "SELECT * FROM Utilisateur WHERE Personne = ?" ;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, iDPersonne);
			rs = ps.executeQuery();
			if(rs.first()){
				PersonneDAO personneDAO = new PersonneDAO();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIDUtilisateur(rs.getInt(1));
				utilisateur.setIDPersonne(personneDAO.find(rs.getString(2)));
				System.out.println(rs.getInt(1));
				return utilisateur;
			}
		
			System.out.println("L'utilisateur a ete trouve avec succes !");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					getConnection().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
