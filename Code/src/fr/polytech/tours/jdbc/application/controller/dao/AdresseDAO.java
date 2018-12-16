package fr.polytech.tours.jdbc.application.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.polytech.tours.jdbc.application.model.*;
/**
 * Cette classe permet d'extraire les methodes necessaires a la creation des
 * objects definis/crees dans notre model pour la gestion des adresses, puis en
 * mettre a jour la base de donnees.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class AdresseDAO extends DAO<Adresse> {
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
	 * Fonction permettant d'inserer une adresse dans la base de donnes.
	 */
	@Override
	public boolean insert(Adresse adresse) {


		try {
			if(adresse.getIDAdresse() == -1) {
				String sql = "INSERT INTO Adresse(NRue, NomRue, CodePostal, Ville, Pays ) VALUES (?,?,?,?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, adresse.getNRue());
				ps.setString(2, adresse.getNomRue());
				ps.setInt(3, adresse.getCodePostal());
				ps.setString(4, adresse.getVille());
				ps.setString(5, adresse.getPays());

			} else {
				String sql = "INSERT INTO Adresse(IDAdresse, NRue, NomRue, CodePostal, Ville, Pays ) VALUES (?,?,?,?,?,?)";
				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, adresse.getIDAdresse());
				ps.setInt(2, adresse.getNRue());
				ps.setString(3, adresse.getNomRue());
				ps.setInt(4, adresse.getCodePostal());
				ps.setString(5, adresse.getVille());
				ps.setString(6, adresse.getPays());
			}
			
			ps.executeUpdate();
			System.out.println("Les donnees ont ete ajoutes a la table Adresse avec succes !");
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
	 * Fonction permettant de supprimer une adresse dans la base de donnes.
	 */
	@Override
	public boolean delete(Adresse adresse) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Adresse WHERE IDAdresse = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, adresse.getIDAdresse());
			ps.executeUpdate();
			System.out.println("Les donnees ont ete supprimes de la table Adresse avec succes !");
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
	 * Fonction permettant de mettre a jour une adresse dans la base de donnes.
	 */
	@Override
	public boolean update(Adresse adresse) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Adresse SET NRue = ?, NomRue = ?, CodePostal = ?, Ville = ? , Pays = ? WHERE IDAdresse = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, adresse.getNRue());
			ps.setString(2, adresse.getNomRue());
			ps.setInt(3, adresse.getCodePostal());
			ps.setString(4, adresse.getVille());
			ps.setString(5, adresse.getPays());
			ps.setInt(6, adresse.getIDAdresse());
			ps.executeUpdate();
			System.out.println("Les donnees de la table Adresse ont ete mises a jours  avec succes !");
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
	 * Fonction permettant cherhcer une adresse dans la base de donnes.
	 */
	@Override
	public Adresse find(int iDAdresse) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Adresse WHERE IDAdresse = ?";
		Adresse adresse = new Adresse();

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, iDAdresse);
			rs = ps.executeQuery();
			if (rs.first()) {
				// String ville, String pays
				adresse = new Adresse(iDAdresse, rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));
				// System.out.println(rs.getInt(iDAdresse) + " " + rs.getInt(2) + " " +
				// rs.getString(3) + " "
				// + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getString(6));
				return adresse;
			}

			System.out.println("L'adresse a ete trouve avec succes !");

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


	public Adresse findID(Adresse adresseParam) {

		String sql = "SELECT IDAdresse FROM Adresse WHERE NRue = ? AND NomRue = ? AND CodePostal = ? AND Ville = ? AND Pays = ?";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, adresseParam.getNRue());
			ps.setString(2, adresseParam.getNomRue());
			ps.setInt(3, adresseParam.getCodePostal());
			ps.setString(4, adresseParam.getVille());
			ps.setString(5, adresseParam.getPays());
			rs = ps.executeQuery();
			if (rs.first()) {
				adresseParam.setIDAdresse(rs.getInt(1));
				return adresseParam;
			}

			System.out.println("L'adresse a ete trouve avec succes !");

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
