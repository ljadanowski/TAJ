package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Nauczyciel;


public class NauczycielManager 
{
	private Connection connection;

	private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private static final String CREATE_TABLE_NAUCZYCIEL = "CREATE TABLE Nauczyciel(id bigint GENERATED BY DEFAULT AS IDENTITY, nazwisko varchar(20), zawod varchar(20), id_uczen bigint";

	private PreparedStatement addNauczycielStmt;
	private PreparedStatement deleteAllNauczycielStmt;
	private PreparedStatement getAllNauczycielStmt;
	private PreparedStatement getNauczycielByIdStmt;

	private Statement statement;

	public NauczycielManager() 
	{
		try {
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Nauczyciel".equalsIgnoreCase(rs.getString("TABLE_NAME"))) 
				{
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(CREATE_TABLE_NAUCZYCIEL);

			addNauczycielStmt = connection
					.prepareStatement("INSERT INTO Nauczyciel (nazwisko, zawod) VALUES (? ?)");
			deleteAllNauczycielStmt = connection
					.prepareStatement("DELETE FROM Nauczyciel");
			getAllNauczycielStmt = connection
					.prepareStatement("SELECT id, nazwisko, zawod, id_uczen FROM Nauczyciel");
			getNauczycielByIdStmt = connection
					.prepareStatement("SELECT id, nazwisko, zawod, id_uczen FROM Nauczyciel where id = ?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() 
	{
		return connection;
	}

	void clearNauczyciel() 
	{
		try {
			deleteAllNauczycielStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addNauczyciel(Nauczyciel nauczyciel) {
		int count = 0;
		try {
			addNauczycielStmt.setString(1, nauczyciel.getNazwisko());
			addNauczycielStmt.setString(2, nauczyciel.getZawod());
			count = addNauczycielStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Nauczyciel> getAllNauczyciel() {
		List<Nauczyciel> nauczyciele = new ArrayList<Nauczyciel>();

		try {
			ResultSet rs = getAllNauczycielStmt.executeQuery();

			while (rs.next()) {
				Nauczyciel n = new Nauczyciel();
				n.setId_uczen(rs.getInt("id"));
				n.setNazwisko(rs.getString("nazwa"));
				nauczyciele.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nauczyciele;
	}

	public Nauczyciel getNauczyciel(Long id) {
		Nauczyciel n = new Nauczyciel();
		try {
			getNauczycielByIdStmt.setLong(1, id);
			ResultSet rs = getNauczycielByIdStmt.executeQuery();

			while (rs.next()) {
				n.setId(rs.getLong("id"));
				n.setNazwisko(rs.getString("nazwisko"));
				n.setZawod(rs.getString("nazwisko"));
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n;
	}

}
