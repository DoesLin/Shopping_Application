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
 * objects definis/crees dans notre model concernant les offres, puis en
 * mettre a jour la base de donnees.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class OffreDAO extends DAO<Offre> {
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
	
	public List<Offre> listOffresAnnonce(Annonce annonce) {

		List<Offre> result = new ArrayList<Offre>();

		try {

			if (annonce == null) {
			} else {

				String sql = "SELECT DISTINCT(IDOffre) FROM Offre WHERE Annonce = ?";
				List<Integer> listID = new ArrayList<Integer>();

				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, annonce.getIDAnnonce());
				rs = ps.executeQuery();
				while (rs.next()) {
					listID.add(rs.getInt(1));
				}

				for (int id : listID) {
					result.add(find(id));
				}
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

	public List<Offre> listMesOffres(Utilisateur utilisateur) {

		List<Offre> result = new ArrayList<Offre>();

		try {

			if (utilisateur == null) {
			} else {

				String sql = "SELECT DISTINCT(IDOffre) FROM Offre WHERE Utilisateur = ?";
				List<Integer> listID = new ArrayList<Integer>();

				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, utilisateur.getIDUtilisateur());
				rs = ps.executeQuery();
				while (rs.next()) {
					listID.add(rs.getInt(1));
				}

				for (int id : listID) {
					result.add(find(id));
				}
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

	public List<Offre> listOffres(Vendeur vendeur) {

		List<Offre> result = new ArrayList<Offre>();

		try {

			if (vendeur == null) {
			} else {

				String sql = "SELECT DISTINCT(IDOffre) FROM Offre AS o INNER JOIN Annonce AS a "
						+ "WHERE (o.Annonce = a.IDAnnonce) AND a.Vendeur = ?";
				List<Integer> listID = new ArrayList<Integer>();

				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, vendeur.getIDVendeur());
				rs = ps.executeQuery();
				while (rs.next()) {
					listID.add(rs.getInt(1));
				}

				for (int id : listID) {
					result.add(find(id));
				}
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
	 * Fonction permettant d'inserer une offre dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean insert(Offre offre) {
		// TODO Auto-generated method stub

		try {
			if (offre.getIDOffre() == -1) {
				String sql = "INSERT INTO Offre(Annonce, Utilisateur, Acceptation) VALUES (?,?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, offre.getIDAnnonceInt());
				ps.setInt(2, offre.getIDUtilisateurInt());
				ps.setBoolean(3, offre.isAcceptation());
			} else {
				String sql = "INSERT INTO Offre(IDOffre, Annonce, Utilisateur, Acceptation) VALUES (?,?,?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, offre.getIDOffre());
				ps.setInt(2, offre.getIDAnnonceInt());
				ps.setInt(3, offre.getIDUtilisateurInt());
				ps.setBoolean(4, offre.isAcceptation());
			}
			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Offre avec succes !");
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
	 * Fonction permettant de supprimer une offre dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean delete(Offre offre) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Offre WHERE IDOffre = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, offre.getIDOffre());
			ps.executeUpdate();
			System.out.println("Les donnees ont ete supprimes de la table Offre avec succes !");
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
	 * Fonction permettant de mettre a jour une offre dans la base de donnes.
	 * retourne true si insere, false sinon.
	 */
	@Override
	public boolean update(Offre offre) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Offre SET Annonce = ? , Utilisateur = ?, Acceptation = ? WHERE IDOffre = ? ";

		// IDOffre, Annonce, Utilisateur, Vendeur
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, offre.getIDAnnonceInt());
			ps.setInt(2, offre.getIDUtilisateurInt());
			ps.setBoolean(3, offre.isAcceptation());
			ps.setInt(4, offre.getIDOffre());

			ps.executeUpdate();
			System.out.println("Les donnees de la table Offre ont ete mises a jour avec succes !");
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
	 * Fonction permettant de cherhcer une offre dans la base de donnes par son identifiant.
	 * retourne offre
	 */
	@Override
	public Offre find(int iDOffre) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Offre WHERE IDOffre = ?";
		Offre offre = new Offre();
		Annonce annonce = new Annonce();
		Utilisateur utilisateur = new Utilisateur();

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, iDOffre);
			rs = ps.executeQuery();
			if (rs.first()) {
				AnnonceDAO annonceDAO = new AnnonceDAO();
				UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
				
				offre.setIDOffre(rs.getInt(1));
				offre.setAcceptation(rs.getBoolean(4));
				annonce = annonceDAO.find(rs.getInt(2));
				int idUtil = rs.getInt(3);
				System.out.println(idUtil);
				utilisateur = utilisateurDAO.find(idUtil);
				offre.setIDUtilisateur(utilisateur);
				offre.setIDAnnonce(annonce);
				return offre;
			}

			System.out.println("L'offre a ete trouve avec succes !");

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
		return null;
	}

}
