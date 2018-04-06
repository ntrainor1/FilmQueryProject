package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.*;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private static final String username = "student";
	private static final String password = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load database driver. Exiting.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public Film getFilmById(int filmId) {
		Connection conn;
		Film filmByID = null;

		try {
			conn = DriverManager.getConnection(URL, username, password);
			String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int languageId = rs.getInt(5);
				int rentalDuration = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double replacementCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String specialFeatures = rs.getString(11);
				List<Actor> totalActors = getActorsByFilmId(id);
				String language = rs.getString(12);
				filmByID = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, totalActors, language);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("A SQLException has occurred, cannot return any film.");
			e.printStackTrace();
			System.exit(1);
		}

		return filmByID;
	}

	@Override
	public Actor getActorById(int actorId) {
		Connection conn;
		Actor actorByID = null;

		try {
			conn = DriverManager.getConnection(URL, username, password);
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				actorByID = new Actor(id, firstName, lastName);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("A SQLException has occurred, cannot return any actor.");
			e.printStackTrace();
			System.exit(1);
		}

		return actorByID;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		Connection conn;
		List<Actor> actorsByFilm = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(URL, username, password);
			String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor fa ON a.id = fa.actor_id JOIN film f ON fa.film_id = f.id WHERE f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor anActor = new Actor(id, firstName, lastName);
				actorsByFilm.add(anActor);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("A SQLException has occurred, cannot return any actor.");
			e.printStackTrace();
			System.exit(1);
		}

		return actorsByFilm;
	}

	@Override
	public List<Film> getFilmsByKeyword(String keyword) {
		Connection conn;
		List<Film> filmsByKeyword = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(URL, username, password);
			String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE (title LIKE ?) OR (description LIKE ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			String toBeSet = "%" + keyword + "%";
			stmt.setString(1, toBeSet);
			stmt.setString(2, toBeSet);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int languageId = rs.getInt(5);
				int rentalDuration = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double replacementCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String specialFeatures = rs.getString(11);
				List<Actor> totalActors = getActorsByFilmId(id);
				String language = rs.getString(12);
				Film filmByID = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, totalActors, language);
				filmsByKeyword.add(filmByID);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("A SQLException has occurred, cannot return any film.");
			e.printStackTrace();
			System.exit(1);
		}

		return filmsByKeyword;
	}
}
