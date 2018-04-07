# Film Query Project

## Skill Distillery - Week 6

### Project Description

This program is designed to search through an SQL database of films and actors and return information to the user based on the film's id number or a keyword which searches for in the film's title and description.

### Project Operation

This program accesses different SQL entities through a series of keys and ids as displayed in ERDiagram.png.

The program displays a menu, welcoming the user and giving them options to choose from. If they use an incorrect input, the program asks that they choose a correct option (1, 2, or 3), before returning the user to the main menu.

Pressing 1 informs the program to search through the film database by film id (1-1000). If no film is found by a given number, the program informs the user that no film was found. Otherwise, the film's title, release year, rating, language, description, and cast list is displayed, briefly pausing before returning the user to the main menu.

Pressing 2 informs the program to search through the film database by a given keyword, searching both the films' titles and descriptions. If no film is found by a given keyword, the program informs the user that no film was found. Otherwise, the program displays a list of matching films, with each film's title, release year, rating, language, description, and cast list. A closing statement tells the user precisely how many matching films were found, briefly pausing before returning the user to the main menu.

Pressing 3 ends the program while wishing the user goodbye.

### Lessons Learned

- JUnit tests for assertEquals or assertNull are difficult when working with complex Objects like Films and Actors. A helpful method around this issue is to break up an Object into its fields, such as id, title, or last_name to determine if the Object is null or if the Object is the correct Object for a given id.
- It is highly advisable to check your SQL statements in a terminal to determine that your statement has proper syntax and that it does give you the results you want, before plugging it into your Java program.
- Although SQL is a whole other language that uses different data types (SMALLINT, TINYINT, etc.), Java is pretty flexible in allowing simple conversion to its own data types.
