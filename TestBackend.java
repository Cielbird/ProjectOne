import java.io.StringReader;
import java.util.List;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend {
	
	public static void main(String[] args) {
		(new TestBackend()).runTests();
	}


	public void runTests() {
		// add all tests to this method
		if (this.testInitialNumberOfMovies()) {
			System.out.println("Test initial number of movies: PASSED");
		} else {
			System.out.println("Test initial number of movies: FAILED");
		}
		if (this.testGetAllGenres()) {
			System.out.println("Test get all genres: PASSED");
		} else {
			System.out.println("Test get all genres: FAILED");
		}
		if (this.testGetThreeMoviesInitial()) {
			System.out.println("Test get three movies sorted by rating: PASSED");
		} else {
			System.out.println("Test get three movies sorted by rating: FAILED");
		}


		// These tests added by pnowakowski
		if (this.testGetAvgRatings()){
			System.out.println("Test get avg ratings: PASSED");
		}
		else{
			System.out.println("Test get avg ratings: FAILED");
		}
		if (this.testAddGenre()){
			System.out.println("Test add genre: PASSED");
		}
		else{
			System.out.println("Test add genre: FAILED");
		}
	}
	/**
	 * This test instantiates the back end with three movies and tests whether the
	 * initial selection is empty (getNumberOfMovies() returns 0). It passes when
	 * 0 is returned and fails in all other cases, including when an exception is
	 * thrown.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testInitialNumberOfMovies() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
			if (backendToTest.getNumberOfMovies() == 0) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether
	 * the getAllGenres method return the correct set of genres for those three
	 * movies.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testGetAllGenres() {
		try {
			// instantiate once BackendInterface is implemented
			Backend backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			)); 
			if (backendToTest.getAllGenres().size() == 5
					&& backendToTest.getAllGenres().contains("Horror")
					&& backendToTest.getAllGenres().contains("Action")
					&& backendToTest.getAllGenres().contains("Comedy")
					&& backendToTest.getAllGenres().contains("Musical")
					&& backendToTest.getAllGenres().contains("Romance")) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			
			return false;
		}
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether the
	 * initial list returned by getThreeMovies starting with the first movie (0)
	 * is empty. It passes when 0 is returned and fails in all other cases, including
	 * when an exception is thrown.
	 * @return true if the test passed, false if its failed
	 */
	public boolean testGetThreeMoviesInitial() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
			if (backendToTest.getThreeMovies(0).size() == 0) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	// TODO: Back End Developer, add at least 2 more tests
	
	/**
	 * This test instantiates a test Backend with the same three movies as above and tests
	 * whether the getAvgRatings method returns the correct list of ratings
	 * @return true if the test passes, false otherwise
	 */
	public boolean testGetAvgRatings() {
		try{
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));

			List<String> ratingList = backendToTest.getAvgRatings();

			if(ratingList.contains("3.5") 
					&& ratingList.contains("5.4") 
					&& ratingList.contains("2.9"))
			{
				// test passed
				return true;
			}
			else {
				// test failed
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This test instantiates a test Backend with a test set of movies, and tests the
	 * addGenre(0 method that the front end can use to select movies. The functionality
	 * of this test is verified using the getAllGenres method, so its test must pass for 
	 * this test to pass.
	 * @return true if functionality verified, false otherwise
	 */
	public boolean testAddGenre() {
		try{
//			if(!this.testGetAllGenres()){
//				return false;
//			}

			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
			
			backendToTest.addGenre("Adventure");
			
			if (backendToTest.getAllGenres().size() == 6
					&& backendToTest.getAllGenres().contains("Horror")
					&& backendToTest.getAllGenres().contains("Action")
					&& backendToTest.getAllGenres().contains("Adventure")
					&& backendToTest.getAllGenres().contains("Comedy")
					&& backendToTest.getAllGenres().contains("Musical")
					&& backendToTest.getAllGenres().contains("Romance")) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
