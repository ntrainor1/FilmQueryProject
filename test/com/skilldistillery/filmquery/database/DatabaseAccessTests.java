package com.skilldistillery.filmquery.database;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.*;

class DatabaseAccessTests {
	private DatabaseAccessor db;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

	@Test
	void test_getFilmById_with_invalid_id_returns_null() {
		Film f = db.getFilmById(-42);
		assertNull(f);
	}

	@Test
	void test_getActorById_with_invalid_id_returns_null() {
		Actor a = db.getActorById(-42);
		assertNull(a);
	}
	
	@Test
	void test_getActorsByFilmId_with_invalid_id_returns_null() {
		List<Actor> castList = db.getActorsByFilmId(-42);
		assertEquals(castList.size(), 0);
	}
	
	@Test
	void test_getActorsByFilmId_with_keyword_42_returns_empty_list() {
		List<Film> filmList = db.getFilmsByKeyword("42");
		assertEquals(filmList.size(), 0);
	}
	
	@Test
	void test_getFilmsByKeyword_with_keyword_easy_returns_BASIC_EASY() {
		List<Film> filmList = db.getFilmsByKeyword("easy");
		assertEquals(filmList.get(0).getTitle(), "BASIC EASY");
	}
	
	@Test
	void test_getFilmsByKeyword_with_keyword_drama_returns_106_results() {
		List<Film> filmList = db.getFilmsByKeyword("drama");
		assertEquals(filmList.size(), 106);
	}
	
	@Test
	void test_getActorById_with_id_1_returns_Penelope() {
		Actor a = db.getActorById(1);
		assertEquals(a.getFirstName(), "Penelope");
	}
	
	@Test
	void test_getFilmById_with_id_1_returns_ACADEMY_DINOSAUR() {
		Film f = db.getFilmById(1);
		assertEquals(f.getTitle(), "ACADEMY DINOSAUR");
	}

}
