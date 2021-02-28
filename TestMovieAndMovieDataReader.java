import java.io.StringReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Reader;

/**
 * This class contains a set of tests for the MovieInterface and MovieDataReaderInterface
 * implementation of the Movie Mapper project.
 */
public class TestMovieAndMovieDataReader {
	
	MovieDataReaderInterface readerToTest;
	
	public static void main(String[] args) {
		(new TestMovieAndMovieDataReader()).runTests();
	}

	/**
	 * This method calls all of the test methods in the class and ouputs pass / fail
	 * for each test.
	 */
	public void runTests() {
		// instantiate reader to test once it is implemented
		readerToTest = new MovieDataReader();
		
		// add all tests to this method
		if (this.testReaderNumberOfMovies()) {
			System.out.println("Test number of movies: PASSED");
		} else {
			System.out.println("Test number of movies: FAILED");
		}
		if (this.testReaderMovieTitles()) {
			System.out.println("Test movie titles: PASSED");
		} else {
			System.out.println("Test movie titles: FAILED");
		}
		if (this.testMovieOrder()) {
			System.out.println("Test movie order: PASSED");
		} else {
			System.out.println("Test movie order: FAILED");
		}
		// Data Wrangler Implemented Tests
		if (this.testReaderMovieGenres()) {
			System.out.println("Test movie genres: PASSED");
		} else {
			System.out.println("Test movie genres: FAILED");
		}
		if (this.testReaderMovieDescriptions()) {
			System.out.println("Test movie descriptions: PASSED");
		} else {
			System.out.println("Test movie descriptions: FAILED");
		}
	}
	
	/**
	 * This test reads in 3 movies and tests whether the list of movies
	 * returned is of size 3. It fails if the size is not 3 or if an
	 * exception occurs while reading in the movies.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testReaderNumberOfMovies() {
		List<MovieInterface> movieList = new ArrayList<MovieInterface>();
		Reader string_reader;
		try {
			movieList = readerToTest.readDataSet(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
		if (movieList.size() == 3) {
			// test passed
			return true;
		} else {
			// test failed
			return false;
		}
	}

    /**
	 * This test reads in 3 movies and tests whether the list of movies
	 * contains all 3 titles of the source data in any order. It fails
	 * if the list returned is missing one or more titles or if an
	 * exception occurs while reading in the data.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testReaderMovieTitles() {
		List<MovieInterface> movieList;
		try {
			movieList = readerToTest.readDataSet(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
		String title1 = "The Source of Shadows";
		String title2 = "The Insurrection";
		String title3 = "Valley Girl";
		boolean equalOne = true;
		// check if first movie is has of the above titles
		equalOne = equalOne && (title1.equals(movieList.get(0).getTitle()) ||
								title2.equals(movieList.get(0).getTitle()) ||
								title3.equals(movieList.get(0).getTitle()));
		// check if second movie is has of the above titles
		equalOne = equalOne && (title1.equals(movieList.get(1).getTitle()) ||
								title2.equals(movieList.get(1).getTitle()) ||
								title3.equals(movieList.get(1).getTitle()));
		// check if third movie is has of the above titles
		equalOne = equalOne && (title1.equals(movieList.get(2).getTitle()) ||
								title2.equals(movieList.get(2).getTitle()) ||
								title3.equals(movieList.get(2).getTitle()));
		// true if the three movies have the right titles
		return equalOne;
	}

	/**
	 * This test reads in 3 movies, then sorts them. It then checks whether
	 * the default sorting order is descending based on the average ratings.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testMovieOrder() {
		List<MovieInterface> movieList;
		try {
			movieList = readerToTest.readDataSet(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
		Collections.sort(movieList);
		double lastRating = 11.0;
		for (MovieInterface movie : movieList) {
			if (movie.getAvgVote() > lastRating) {
				// test fails
				return false;
			}
			lastRating = movie.getAvgVote();
		}
		// test passes
		return true;
	}
	
	// Data Wrangler 2 Test Implementations

	// This test is important because it indirectly tests for proper management of
	// the varying formats (list versus a single element) of genres.
	/**
	 * This test reads in 3 movies and tests whether the list of movies
	 * contains all 3 genres of the source data in any order. It fails
	 * if the list returned is missing one or more genres or if an
	 * exception occurs while reading in the data.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testReaderMovieGenres() {
		List<MovieInterface> movieList = new ArrayList<MovieInterface>();
		try {
			movieList = readerToTest.readDataSet(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
		List<String> genre1 = new ArrayList<String>();
		genre1.add("Horror");
		List<String> genre2 = new ArrayList<String>();
		genre2.add("Action");
		List<String> genre3 = new ArrayList<String>();
		genre3.add("Comedy");
		genre3.add("Musical");
		genre3.add("Romance");
		boolean equalOne = true;
		// check if first movie has any of the above genres
		equalOne = equalOne && (genre1.equals(movieList.get(0).getGenres()) ||
								genre2.equals(movieList.get(0).getGenres()) ||
								genre3.equals(movieList.get(0).getGenres()));
		// check if second movie has any of the above genres
		equalOne = equalOne && (genre1.equals(movieList.get(1).getGenres()) ||
								genre2.equals(movieList.get(1).getGenres()) ||
								genre3.equals(movieList.get(1).getGenres()));
		// check if third movie has any of the above genres
		equalOne = equalOne && (genre1.equals(movieList.get(2).getGenres()) ||
								genre2.equals(movieList.get(2).getGenres()) ||
								genre3.equals(movieList.get(2).getGenres()));
		// true if the three movies have the right genres
		return equalOne;
	}

	// This test is important because it indirectly tests for proper management of
	// odd punctuation like in-property commas and apostrophes.
	/**
	 * This test reads in 3 movies and tests whether the list of movies
	 * contains all 3 descriptions of the source data in any order. It fails
	 * if the list returned is missing one or more descriptions or if an
	 * exception occurs while reading in the data.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testReaderMovieDescriptions() {
		List<MovieInterface> movieList;
		try {
			movieList = readerToTest.readDataSet(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
		String description1 = "A series of stories woven together by one of our most primal fears, the fear of the unknown.";
		String description2 = "The director of the largest media company wants to expose how left-wing powers use film to control populations.";
		String description3 = "Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.";
		boolean equalOne = true;
		// check if first movie has any of the above descriptions
		equalOne = equalOne && (description1.equals(movieList.get(0).getDescription()) ||
								description2.equals(movieList.get(0).getDescription()) ||
								description3.equals(movieList.get(0).getDescription()));
		// check if second movie has any of the above descriptions
		equalOne = equalOne && (description1.equals(movieList.get(1).getDescription()) ||
								description2.equals(movieList.get(1).getDescription()) ||
								description3.equals(movieList.get(1).getDescription()));
		// check if third movie has any of the above descriptions
		equalOne = equalOne && (description1.equals(movieList.get(2).getDescription()) ||
								description2.equals(movieList.get(2).getDescription()) ||
								description3.equals(movieList.get(2).getDescription()));
		// true if the three movies have the right descriptions
		return equalOne;
	}

}
