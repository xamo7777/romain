package com.mycompany.tennis.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class TestDeConnexion {
	public static void main(String... args) {
		Connection conn = null;
		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			conn = dataSource.getConnection(); // dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode");
			dataSource.setServerName("localhost");
			dataSource.setDatabaseName("tennis");
			dataSource.setUseSSL(false);
			dataSource.setUser("root");
			dataSource.setPassword("root");

			// MySQL driver MySQL Connector
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris",
//					"root", "root");

			conn.setAutoCommit(false);

			// permet de mettre à jour ou de creer dans la base de données
			PreparedStatement pStatementUpdate = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");
			int identifiant = 28;
			String nom = "Errani";
			String prenom = "Sara";
			String sexe = "F";
			pStatementUpdate.setString(1, nom);
			pStatementUpdate.setString(2, prenom);
			pStatementUpdate.setInt(3, identifiant);

			int nbEnregistrementsModifies = pStatementUpdate.executeUpdate();

			PreparedStatement pStatementInsert = conn
					.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES (?,?,?)");
			String nom1 = "Capriati";
			String prenom1 = "Jennifer";
			String sexe1 = "F";
			pStatementInsert.setString(1, nom1);
			pStatementInsert.setString(2, prenom1);
			pStatementInsert.setString(3, sexe1);
			int nbEnregistrementsCrees = pStatementInsert.executeUpdate();

			// Permet de creer

			conn.commit();

			System.out.println(nbEnregistrementsModifies);
			System.out.println(nbEnregistrementsCrees);

			// pour recuperer toute la liste des id
			// ResultSet resultSet = pstatement.executeQuery("SELECT NOM, PRENOM, ID FROM
			// JOUEUR");

//			while (resultSet.next()) {
//
//				final int id = resultSet.getInt("ID");
//				final String nom = resultSet.getString("PRENOM");
//				final String prenom = resultSet.getString("NOM");
//				System.out.println("le joueur qui possède l'id " + id + " est" + prenom + "" + nom);
//			}
//
//			resultSet.close();
//			
//			
//			pstatement.close();

			System.out.println("success");

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();

			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e1) {
					e.printStackTrace();
				}

			}
		}
	}
}
