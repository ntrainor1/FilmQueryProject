package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		int userChoice = 0;

		while (true) {
			menu();

			try {
				userChoice = input.nextInt();
			} catch (InputMismatchException ime) {
				System.err.println("Input 1-3 next time!");
				System.exit(1);
			}

			if (userChoice == 1) {
				System.out.print("Type a film id:\t");
				int selectedFilmId = input.nextInt();
				Film film = db.getFilmById(selectedFilmId);
				System.out.println();

				if (film == null) {
					System.out.println("Film not found with that id.");
				}
				else {
					displayFilm(film);
				}
				
				System.out.println();
			}
			else if (userChoice == 2) {
				System.out.print("Type in a keyword:\t");
				String keyword = input.next();
				Film film = db.getFilmByKeyword(keyword);
				System.out.println();
				
				if (film == null) {
					System.out.println("Film not found with that keyword.");
				}
				else {
					displayFilm(film);
				}
				
				System.out.println();
			}
			else if (userChoice == 3) {
				System.out.println("Goodbye!");
				break;
			}
			else {
				System.out.println("Please put in a number between 1-3");
				System.out.println();
			}
			
			// Brief pause so that the user can read the details of the film before displaying the menu again.
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void menu() {
		System.out.println("***************************");
		System.out.println("*                         *");
		System.out.println("* WELCOME TO FILM SEARCH! *");
		System.out.println("*                         *");
		System.out.println("*   PRESS 1 TO LOOK FOR   *");
		System.out.println("*     A FILM BY ITS ID    *");
		System.out.println("*                         *");
		System.out.println("*   PRESS 2 TO LOOK FOR   *");
		System.out.println("*    A FILM BY KEYWORD    *");
		System.out.println("*                         *");
		System.out.println("*     PRESS 3 TO EXIT     *");
		System.out.println("*                         *");
		System.out.println("***************************");
		System.out.println();
	}
	
	private void displayFilm(Film toBeDisplayed) {
		System.out.println("TITLE: " + toBeDisplayed.getTitle() + "\t|| RELEASED: " + toBeDisplayed.getReleaseYear() + " || RATED " +  toBeDisplayed.getRating());
		System.out.println(toBeDisplayed.getDescription() + ".");
		System.out.print("CAST:\t");
		for (Actor castMember : toBeDisplayed.getTotalActors()) {
			System.out.println(castMember.getFirstName() + " " + castMember.getLastName());
			System.out.print("\t");
		}
	}

}
