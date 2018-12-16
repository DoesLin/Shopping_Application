package fr.polytech.tours.jdbc.application.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.polytech.tours.jdbc.application.model.*;
/**
 * Cette classe permet d'extraire les methodes necessaires a la creation des
 * objects definis/crees dans notre model concernant les personnes, puis en
 * mettre a jour la base de donnees.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class PersonneDAO extends DAO<Personne> {
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

	/*
	 * generer un nouveau s'il cette personne n'est pas encore un utilisateur
	 */
	public Utilisateur genererUtilisateur(Personne personne) {
		UtilisateurDAO utilDAO = new UtilisateurDAO();

		Utilisateur utilisateur = utilDAO.findPersonne(personne.getIDPersonne());

		if (utilisateur == null) {
			Utilisateur nouveauUtil = new Utilisateur(personne);
			utilDAO.insert(nouveauUtil);
			return utilDAO.findPersonne(personne.getIDPersonne());
		} else {
			return utilisateur;
		}

	}

	/*
	 * generer un nouveau s'il cette personne n'est pas encore un vendeur
	 */
	public Vendeur genererVendeur(Personne personne) {
		VendeurDAO vendDAO = new VendeurDAO();
		
		Vendeur vendeur = vendDAO.findPersonne(personne.getIDPersonne());

		if (vendeur == null) {
			Vendeur nouveauVend = new Vendeur(personne);
			vendDAO.insert(nouveauVend);
			return vendDAO.findPersonne(personne.getIDPersonne());
		} else {
			return vendeur;
		}
	}
	/**
	 * Fonction permettant d'inserer une personne dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean insert(Personne personne) {

		try {
			if (personne.getAdresse() == null) {
				String sql = "INSERT INTO Personne(iDPersonne, Mot2Passe, nom, prenom) VALUES (?,?,?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setString(1, personne.getIDPersonne());
				ps.setString(2, personne.getMot2Passe());
				ps.setString(3, personne.getNom());
				ps.setString(4, personne.getPrenom());
			} else {
				String sql = "INSERT INTO Personne(iDPersonne, Mot2Passe, nom, prenom, adresse) VALUES (?,?,?,?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setString(1, personne.getIDPersonne());
				ps.setString(2, personne.getIDPersonne());
				ps.setString(3, personne.getNom());
				ps.setString(4, personne.getPrenom());
				ps.setInt(5, personne.getAdresseInt());
			}

			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Personne avec succes !");
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
	 * Fonction permettant de supprimer une personne dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean delete(Personne personne) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Personne WHERE IDPersonne = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, personne.getIDPersonne());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Personne ont ete supprimes avec succes !");
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
	 * Fonction permettant de mettre a jour une personne dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean update(Personne personne) {

		try {
			if (personne.getAdresse() == null) {
				String sql = "UPDATE Personne SET nom = ? , prenom = ? WHERE iDPersonne = ? ";
				ps = getConnection().prepareStatement(sql);
				ps.setString(1, personne.getNom());
				ps.setString(2, personne.getPrenom());
				ps.setString(3, personne.getIDPersonne());
			} else {
				String sql = "UPDATE Personne SET nom = ? , prenom = ?, adresse = ? WHERE iDPersonne = ? ";
				ps = getConnection().prepareStatement(sql);
				ps.setString(1, personne.getNom());
				ps.setString(2, personne.getPrenom());
				ps.setInt(3, personne.getAdresseInt());
				ps.setString(4, personne.getIDPersonne());
			}
			
			ps.executeUpdate();
			System.out.println("Les donnees de la table Personne ont ete mises a jour avec succes !");
			return true;
		} catch (SQLException e) {
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
	 * Fonction permettant de chercher une personne dans la base de donnes par son identifiant.
	 * retourne personne
	 */
	public Personne find(String iDPersonne) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Personne WHERE IDPersonne = ?";
		Personne personne;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, iDPersonne);
			rs = ps.executeQuery();
			if (rs.first()) {

				int IDAdre = rs.getInt(5);
				if (IDAdre == 0) {
					personne = new Personne(iDPersonne, rs.getString(2), rs.getString(3), rs.getString(4));
				} else {
					AdresseDAO adreDAO = new AdresseDAO();
					personne = new Personne(iDPersonne, rs.getString(2), rs.getString(3), rs.getString(4),
							adreDAO.find(IDAdre));
				}

				System.out.println(personne.getIDPersonne() + " " + personne.getPrenom() + " " + personne.getNom());
				return personne;
			}

			System.out.println("La personne a ete trouve avec succes !");

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.polytech.tours.jdbc.application.controller.dao.DAO#find(int)
	 */
	@Override
	public Personne find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
