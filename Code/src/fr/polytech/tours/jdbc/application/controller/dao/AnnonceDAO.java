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
 * objects definis/crees dans notre model concernant les annonces, puis en
 * mettre a jour la base de donnees.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class AnnonceDAO extends DAO<Annonce> {
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

	public List<Annonce> listPrononceAnnonces(Vendeur vendeur) {

		List<Annonce> result = new ArrayList<Annonce>();

		try {

			if (vendeur == null) {
			} else {

				String sql = "SELECT DISTINCT(IDAnnonce) FROM Annonce WHERE Vendeur = ?";
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
	 * Fonction permettant d'avoir la liste des annonces par mot cle et par categorie.
	 * @param motCle
	 * @param nomCategorie
	 * @return result
	 */
	public List<Annonce> listResultat(String motCle, String nomCategorie) {

		List<Annonce> result = new ArrayList<Annonce>();
		motCle = motCle.toLowerCase();
		motCle = "%" + motCle + "%";

		try {

			if (nomCategorie.equals("Tout")) {

				String sql = "SELECT DISTINCT(IDAnnonce) FROM Annonce AS a INNER JOIN Categorie AS c "
						+ "WHERE (a.Categorie = c.IDCategorie) AND (LOWER(c.DescriptionCategorie) LIKE ? "
						+ "OR LOWER(a.Nom) LIKE ? OR LOWER(a.TypeTransaction) LIKE ? "
						+ "OR LOWER(a.DescriptionAnnonce) LIKE ?)";
				List<Integer> listID = new ArrayList<Integer>();

				ps = getConnection().prepareStatement(sql);
				ps.setString(1, motCle);
				ps.setString(2, motCle);
				ps.setString(3, motCle);
				ps.setString(4, motCle);
				rs = ps.executeQuery();
				while (rs.next()) {
					listID.add(rs.getInt(1));
				}

				for (int id : listID) {
					result.add(find(id));
				}
			} else {

				String sql = "SELECT DISTINCT(IDAnnonce) FROM Annonce AS a INNER JOIN Categorie AS c "
						+ "WHERE (a.Categorie = c.IDCategorie) AND(c.Nom = ?) "
						+ "AND (LOWER(c.DescriptionCategorie) LIKE ? OR LOWER(a.Nom) LIKE ? "
						+ "OR LOWER(a.TypeTransaction) LIKE ? OR LOWER(a.DescriptionAnnonce) LIKE ?)";
				List<Integer> listID = new ArrayList<Integer>();

				ps = getConnection().prepareStatement(sql);
				ps.setString(1, nomCategorie);
				ps.setString(2, motCle);
				ps.setString(3, motCle);
				ps.setString(4, motCle);
				ps.setString(5, motCle);
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
	 * fonction permettant d'avoir toute la liste des annonce.
	 * @return result
	 */
	public List<Annonce> listAnnonces() {

		List<Annonce> result = new ArrayList<Annonce>();

		try {

			String sql = "SELECT IDAnnonce FROM Annonce";
			List<Integer> listID = new ArrayList<Integer>();

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				listID.add(rs.getInt(1));
			}

			for (int id : listID) {
				result.add(find(id));
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
	 * Fonction permettant d'inserer une annonce dans la base de donnes.
	 */
	@Override
	public boolean insert(Annonce annonce) {

		try {
			if (annonce.getIDAnnonce() == -1) {
				String sql = "INSERT INTO Annonce(Nom, TypeTransaction, Position, Prix, DescriptionAnnonce, Categorie, Vendeur) VALUES (?,?,?,?,?,?,?)";

				ps = getConnection().prepareStatement(sql);
				ps.setString(1, annonce.getNom());
				ps.setString(2, annonce.getTypeTransaction());
				ps.setInt(3, annonce.getPositionInt());
				ps.setFloat(4, annonce.getPrix());
				ps.setString(5, annonce.getDescriptionAnnonce());
				ps.setInt(6, annonce.getCategorieInt());
				ps.setInt(7, annonce.getVendeurInt());

			} else {
				String sql = "INSERT INTO Annonce(IDAnnonce, Nom, TypeTransaction, Position, Prix, DescriptionAnnonce, Categorie, Vendeur) VALUES (?,?,?,?,?,?,?,?)";

				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, annonce.getIDAnnonce());
				ps.setString(2, annonce.getNom());
				ps.setString(3, annonce.getTypeTransaction());
				ps.setInt(4, annonce.getPositionInt());
				ps.setFloat(5, annonce.getPrix());
				ps.setString(6, annonce.getDescriptionAnnonce());
				ps.setInt(7, annonce.getCategorieInt());
				ps.setInt(8, annonce.getVendeurInt());
			}

			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Annonce avec succes !");
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
	 * Fonction permettantde supprimer une annonce dans la base de donnes.
	 */
	@Override
	public boolean delete(Annonce annonce) {
		// TODO Auto-generated method stub

		try {
			OffreDAO offreDAO = new OffreDAO();

			List<Offre> offresRelatives = offreDAO.listOffresAnnonce(annonce);
			if (!offresRelatives.isEmpty()) {

				for (Offre o : offresRelatives) {
					offreDAO.delete(o);
				}
			}
			String sql = "DELETE FROM Annonce WHERE IDAnnonce = ?";
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, annonce.getIDAnnonce());
			ps.executeUpdate();
			System.out.println("Les donnees ont ete supprimes de la table annonce avec succes !");

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
	 * Fonction permettant de mettre a jour une annonce dans la base de donnes.
	 */
	@Override
	public boolean update(Annonce annonce) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Annonce SET TypeTransaction = ? , Position = ?, Prix = ?, DescriptionAnnonce = ?, Categorie = ?, Vendeur = ? WHERE IDAnnonce = ? ";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, annonce.getTypeTransaction());
			ps.setInt(2, annonce.getPositionInt());
			ps.setFloat(3, annonce.getPrix());
			ps.setString(4, annonce.getDescriptionAnnonce());
			ps.setInt(5, annonce.getCategorieInt());
			ps.setInt(6, annonce.getVendeurInt());
			ps.setInt(7, annonce.getIDAnnonce());

			ps.executeUpdate();
			System.out.println("Les donnees de la table annonce ont ete mises a jour avec succes !");
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
	 * Fonction permettant de trouver une annonce dans la base de donnes par son identifiant.
	 */
	@Override
	public Annonce find(int iDAnnonce) {
		String sql = "SELECT * FROM Annonce WHERE IDAnnonce = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, iDAnnonce);
			rs = ps.executeQuery();
			if (rs.first()) {
				AdresseDAO adreDAO = new AdresseDAO();
				CategorieDAO cateDAO = new CategorieDAO();
				VendeurDAO vendDAO = new VendeurDAO();

				Annonce annonce = new Annonce(iDAnnonce, rs.getString(2), rs.getString(3), rs.getFloat(5),
						rs.getString(6));

				Adresse position = adreDAO.find(rs.getInt(4));
				Categorie categorie = cateDAO.find(rs.getInt(7));
				Vendeur vendeur = vendDAO.find(rs.getInt(8));

				annonce.setPosition(position);
				annonce.setCategorie(categorie);
				annonce.setVendeur(vendeur);

				// System.out.println(rs.getInt(iDAnnonce) + " " + rs.getString(2) + " " +
				// rs.getString(3) + " "
				// + rs.getFloat(5) + " " + rs.getString(6));
				return annonce;
			}

			System.out.println("L'annonce a ete trouve avec succes !");

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
