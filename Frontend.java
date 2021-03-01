// --== CS400 File Header Information ==--
// Name: Vatsal Patel
// Email: vpatel43@wisc.edu
// Team: JD blue
// Role: Frontend Developer
// TA: Xinyi
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

/**
 * The Frontend class is responsible for instantiating the frontend of the application
 */
public class Frontend {
    
    /**
     * Helper method to determine whether the provided genre has been selected
     * or not
     * 
     * @param backend A reference to the backend object
     * @param genre The genre being checked
     * @return "SELECTED" if the genre has been selected, "NOT SELECTED" otherwise
     */
    public static String genreStatus (Backend backend, String genre) {
        List<String> selectedGenres = backend.getAvgRatings();
        
        if (selectedGenres.contains(genre)) {
            // genre found within the list of selected genres
            return "SELECTED";
        } else {
            // genre not found within the list
            return "NOT SELECTED";
        }
    }
    
    /**
     * Helper method to determine whether the provided rating has been selected
     * or not
     * 
     * @param backend A reference to the backend object
     * @param rating The rating being checked
     * @return "SELECTED" if the rating has been selected, "NOT SELECTED" otherwise
     */
    public static String ratingStatus (Backend backend, int rating) {
        List<String> selected = backend.getAvgRatings();
        for(int i = 0; i < selected.size(); i++) {
            if (selected.get(i).equals(rating + "")) {
                // rating has been found in the list of selected ratings
                return "SELECTED";
            }
        }
        // rating was not found in the list of selected ratings
        return "NOT SELECTED";
    }
    
    /**
     * Helper method that returns true if s is an integer value
     * 
     * @param s The string being checked
     * @return True if s is an Integer value, false otherwise
     */
    public static boolean isNumber(String s) {
        // Try to parse s to an Integer
        try {
            Integer.parseInt(s.strip());
            // No exception thrown if string can be parsed
            return true;
        } catch (NumberFormatException nfe) {
            // Exception thrown if string cannot be parsed
            return false;
        }
    }
    
    /**
     *  The genresMode method sets the Movie Mapper to the genres mode, which
     *  1) Prints a message indicating that the program is in the genres mode and a
     *  brief set of instructions
     *  2) Displays the list of genres and indicates whether or not each genre
     *  us currently selected
     *  3) Takes in user input to select and deselect ratings, or 'x' to return
     *  to the base mode
     *  4) Displays an updated list of genres
     * 
     *  @param backend A reference to the backend object
     */
    public void genresMode (Backend backend) {
        // Display a welcome message
        System.out.println("-------------------------------------------------");
        System.out.println("GENRES MODE\n");
        
        // Display Instructions
        System.out.println("INSTRUCTIONS:");
        System.out.println("A list of the movie genres is displayed");
        System.out.println("Each genre is listed as either selected or deselected");
        System.out.println("Only selected movies will be displayed in the base mode");
        System.out.println("Enter the number listed next to a genre to change whether or not it is selected");

        // Loop to display genres selection list + taking input
        boolean validInput = false;
        while (!validInput) {
            System.out.println(); // Spacing
            // Display list of genres and whether or not each genre is selected
            List<String> genres = backend.getAllGenres();
            for (int i = 0; i < genres.size(); i++) {
                System.out.println((i + 1) + ". " + genres.get(i) + ": " + genreStatus(backend, genres.get(i)));
            }
            System.out.println(); // Spacing

            // Take in user input 
            Scanner s = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Which Genre Would You Like To Select/Deselect:");
	    String choice;
	    if (s.hasNext()){
		    choice = s.next().strip();
	    } else {
		    // End of Input Stream reached - needed for running Test class
		    break;
	    }

	    if (isNumber(choice)){
                // Convert to number
                int genreChoice = Integer.parseInt(choice);
                if (genreChoice < 1 || genreChoice > genres.size()) {
                    // Input was not a valid genre
                    System.out.println("Invalid Input");
                    continue;
                } else {
                    // If genre was already selected, deselect it. Otherwise, select it.
                    String genreString = genres.get(genreChoice - 1);
                    if (genreStatus(backend, genreString).equals("SELECTED")) {
                        backend.removeGenre(genreString);
                    } else {
                        backend.addGenre(genreString);
                    }
                    continue;
                }
            } else if (choice.equals("x")) {
                // return to base mode
                this.baseMode(backend);
                break;
            } else {
                // Input was not a number and not 'x'
                System.out.println("Invalid Input");
                continue;
            }
        }
    }
    
    /**
     *  The ratingsMode method sets the Movie Mapper to the ratings mode, which
     *  1) Prints a message indicating that the program is in ratings mode and a
     *  brief set of instructions 
     *  2) Displays the list of ratings and indicates whether or not each 
     *  rating is currently selected
     *  3) Takes in user input to select and deselect ratings, or 'x' to return
     *  to the base mode
     *  4) Displays an updated list of the ratings selection
     * 
     *  @param backend A reference to the backend object
     */
    public void ratingsMode(Backend backend){
        // Display a welcome message
        System.out.println("-------------------------------------------------");
        System.out.println("RATINGS MODE\n");
        
        // Display Instructions
        System.out.println("INSTRUCTIONS:");
        System.out.println("A list of the movie ratings is displayed");
        System.out.println("Each rating is listed as either selected or deselected");
        System.out.println("Only selected movies will be displayed in the base mode");
        System.out.println("Enter a rating number to change whether or not it is selected");

        // Loop to display ratings selection list + taking input
        boolean validInput = false;
        while (!validInput) {
            System.out.println(); // Spacing
            // Display list of ratings and whether or not each rating is selected
            for (int i = 0; i <= 10; i++) {
                System.out.println(i + ": " + ratingStatus(backend, i));
            }
            System.out.println(); // Spacing

            // Take in user input 
            Scanner s = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Which Rating Would You Like To Select/Deselect:");
	    String choice;
	    if(s.hasNext()){
		    choice = s.next();
	    } else {
		    // End of Input Stream reached - needed to run Test class
		    break;
	    }

            if (isNumber(choice)){
                // Convert to number
                int rating = Integer.parseInt(choice);
                if (rating < 0 || rating > 10) {
                    // Input was not a valid rating
                    System.out.println("Invalid Input");
                    continue;
                } else {
                    // If rating was already selected, deselect it. Otherwise, select it.
                    if (ratingStatus(backend, rating).equals("SELECTED")) {
                        backend.removeAvgRating("" + rating);
                    } else {
                        backend.addAvgRating("" + rating);
                    }
                    continue;
                }
            } else if (choice.equals("x")) {
                // return to base mode
                this.baseMode(backend);
                break;
            } else {
                // Input was not a number and not 'x'
                System.out.println("Invalid Input");
                continue;
            }
        }
    }
    
    /**
     *  The baseMode method sets the Movie Mapper to the base mode, which 
     *  1) Prints a message indicating that the program is in base mode 
     *  2) Lists the top 3 (by average rating) selected movies, which may be 
     *  empty (in which case no movies are printed)
     *  3) Prints instructions for how to navigate the program and allows the 
     *  user to navigate to the genre selection program ('g') or ratings 
     *  selection mode ('r'), or exit the program ('x'). The user can also 
     *  navigate the list of movies displayed by entering the number of the rank
     *  they wish to scroll to.
     * 
     *  @param backend A reference to the backend object
     */
    public void baseMode(Backend backend) {
        // Display a welcome message
        System.out.println("-------------------------------------------------");
        System.out.println("BASE MODE");
        System.out.println("\nTOP SELECTED MOVIES:");
        
        // Display upto the top 3 selected movies, or a message indicating that
        // there are no movies in the selection set
        String out;
        List<MovieInterface> display = backend.getThreeMovies(0);
        if (display.size() == 0) {
            out = "(There Are No Movies With The Selected Ratings and Genres)\n";
        } else {
            out = "";
            // Display enumerated list of the top 3 movies
            for (int i = 0; i < display.size(); i++) {
                out += (i + 1) + ". " + display.get(i).getTitle() + "\n";
            }
        }
        System.out.println(out);

        // Display Instruction Menu
        System.out.println("INSTRUCTIONS:");
        System.out.println("- Enter a Number to scroll to that rank");
        System.out.println("- Press 'r' to go to the ratings selection mode");
        System.out.println("- Press 'g' to go to the genre selection mode");
        System.out.println("- Press 'x' to exit the program\n");

        // User Input Loop
        boolean validInput = false;
        while(!validInput) {
            // Take in user input
            Scanner s = new Scanner(System.in);
            System.out.println("What Would You Like To Do:");
	    String choice = s.next().strip();

            if (choice.equals("r")){
                // Switch to ratings mode
                this.ratingsMode(backend);
                break;
            } else if (choice.equals("g")) {
                // Switch to genres mode
                this.genresMode(backend);
                break;
            } else if (choice.equals("x")) {
                // Exit Program
                System.out.println("Exiting...");
                s.close();
                break;
            } else if (isNumber(choice)) {
                // If choice is a number, check if it is a valid rank and if so,
                // scroll to that 
                int rank = Integer.parseInt(choice);
                if (rank > backend.getNumberOfMovies() || rank <= 0) {
                    System.out.println("(No movies at that rank)\n");
                } else {
                    // Display the movies at that rank
                    String output; 
                    List<MovieInterface> ranked = backend.getThreeMovies(rank - 1);
                    if (ranked.size() == 0) {
                        output = "(No movies at that rank)\n";
                    } else {
                        output = "";
                        // Display enumerated list of the top 3 movies
                        for (int i = rank - 1; i < ranked.size(); i++) {
                            output += (i + 1) + ". " + ranked.get(i).getTitle() + "\n";
                        }
                    }
                    System.out.println(output);
                }
                continue;
            } else {
                // Otherwise input is not valid
                System.out.println("Invalid Input");
                continue;
            }
        }
    }
    
    /**
     *	The run method prints a welcome message to the program and sets the 
     *  program to the base mode
     * 
     *  @param backend A reference to the backend object
     */
    public void run (Backend backend) {
    	// Display Welcome Message
        System.out.println("\nWelcome to the Movie Mapper Program!");

        // Begin in base mode
        this.baseMode(backend);
    }

    /**
     *	The main method instantiates the backend using the command line arguments,
     *	instantiates the frontend, and runs the Movie Mapper program
     *
     *	@param args Contains the command line argument for a CSV file
     */
    public static void main (String[] args) {
   	// Create Backend Object using the command line argument
	Backend backend = new Backend(args);

	// Select all of the average ratings
	for (int i = 0; i <= 10; i++) {
            backend.addAvgRating(i + ""); // Selects ratings "0" to "10"
        }
	
	// Create Frontend Object
	Frontend frontend = new Frontend();

	// Call the run() method, passing in the constructed Backend object
	frontend.run(backend);
    }
}
