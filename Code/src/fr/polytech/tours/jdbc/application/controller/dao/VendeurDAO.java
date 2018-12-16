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
public class VendeurDAO extends DAO<Vendeur> {
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
	 * Fonction permettant d'inserer un vendeur dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean insert(Vendeur vendeur) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Vendeur(IDVendeur, Personne) VALUES (?,?)";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vendeur.getIDVendeur());
			ps.setString(2, vendeur.getIDPersonne());
			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Vendeur avec succes !");
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
	 * Fonction permettant de supprimer un vendeur dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean delete(Vendeur vendeur) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Vendeur WHERE IDVendeur = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vendeur.getIDVendeur());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Vendeur ont ete supprime avec succes !");
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
	 * Fonction permettant de mettre a jour un vendeur dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean update(Vendeur vendeur) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Vendeur SET Personne = ? WHERE IDVendeur = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, vendeur.getIDPersonne());
			ps.setInt(2, vendeur.getIDVendeur());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Vendeur ont ete mises a jour  avec succes !");
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
	 * Fonction permettant de chercher un vendeur dans la base de donnes a pertir de son identifiant.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public Vendeur find(int iDVendeur) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Utilisateur WHERE IDUtilisateur = ?";
		Vendeur vendeur = new Vendeur();

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, iDVendeur);
			rs = ps.executeQuery();
			if (rs.first()) {
				vendeur = new Vendeur(iDVendeur);
				// System.out.println(rs.getInt(iDVendeur));
				return vendeur;
			}

			System.out.println("L'annonce a ete trouve avec succes !");

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

	public Vendeur findPersonne(String iDPersonne) {

		String sql = "SELECT * FROM Vendeur WHERE Personne = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, iDPersonne);
			rs = ps.executeQuery();
			if (rs.first()) {
				PersonneDAO personneDAO = new PersonneDAO();
				Vendeur vendeur = new Vendeur();
				vendeur.setIDVendeur(rs.getInt(1));
				vendeur.setIDPersonne(personneDAO.find(rs.getString(2)));
				// System.out.println(rs.getInt(1));
				return vendeur;
			}

			System.out.println("Le vendeur a ete trouve avec succes !");

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
