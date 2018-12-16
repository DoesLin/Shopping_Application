package fr.polytech.tours.jdbc.application.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.polytech.tours.jdbc.application.model.*;
/**
 * Cette classe permet d'extraire les methodes necessaires a la creation des
 * objects definis/crees dans notre model concernant les categories, puis en
 * mettre a jour la base de donnees.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class CategorieDAO extends DAO<Categorie> {
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
	public Categorie findCategorie(String nomCategorie) {

		String sql = "SELECT * FROM Categorie WHERE Nom = ?";
		Categorie categorie = new Categorie();

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, nomCategorie);
			rs = ps.executeQuery();
			if (rs.first()) {
				categorie = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
				return categorie;
			}

			System.out.println("La categorie a ete trouve avec succes !");

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
	 * Fonction qui retourne toutes les categories.
	 * @return result
	 */
	public List<String> listCategories() {

		List<String> result = new ArrayList<String>();

		try {

			String sql = "SELECT IDCategorie FROM Categorie";
			List<Integer> listID = new ArrayList<Integer>();

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				listID.add(rs.getInt(1));
			}

			for (int id : listID) {
				result.add(find(id).toString());
			}

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

		return result;
	}
	/**
	 * Fonction permettant d'inserer une categorie dans la base de donnes.
	 */
	@Override
	public boolean insert(Categorie categorie) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Categorie(IDCategorie, Nom, DescriptionCategorie) VALUES (?,?,?)";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, categorie.getIDCategorie());
			ps.setString(2, categorie.getNom());
			ps.setString(3, categorie.getDescriptionCategorie());
			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Categorie avec succes !");
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
	 * Fonction permettant de supprimer une categorie dans la base de donnes.
	 */
	@Override
	public boolean delete(Categorie categorie) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Categorie WHERE IDCategorie = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, categorie.getIDCategorie());
			ps.executeUpdate();
			System.out.println("Les donnees ont ete supprimes de la table Categorie avec succes !");
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
	 * Fonction permettant de mettre a jour une categorie dans la base de donnes.
	 */
	@Override
	public boolean update(Categorie categorie) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Categorie SET Nom = ? , DescriptionCategorie = ? WHERE IDCategorie = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, categorie.getNom());
			ps.setString(2, categorie.getDescriptionCategorie());
			ps.setInt(3, categorie.getIDCategorie());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Categorie ont ete mises a jour avec succes !");
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
	 * Fonction permettant de chercher une categorie dans la base de donnes par son identifiant.
	 */
	@Override
	public Categorie find(int iDCategorie) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Categorie WHERE IDCategorie = ?";
		Categorie categorie = new Categorie();

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, iDCategorie);
			rs = ps.executeQuery();
			if (rs.first()) {
				categorie = new Categorie(iDCategorie, rs.getString(2), rs.getString(3));
				// System.out.println(categorie.getIDCategorie() + categorie.getNom() +
				// categorie.getDescriptionCategorie());
				return categorie;
			}

			System.out.println("La categorie a ete trouve avec succes !");

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
