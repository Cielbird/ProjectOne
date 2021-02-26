// --== CS400 File Header Information ==--
// Author: Patrick Nowakowski
// Email: pnowakowski@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes: 

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.FileReader;

public class Backend implements BackendInterface {
	
	// HashTable with Genre keys, each genre maps to an index of the HashTable
	// A reference to a List of Movie objects with that genre is found
	private HashTableMap<String, ArrayList<MovieInterface>>  genreTable = new HashTableMap<String, ArrayList<MovieInterface>>();

	// HashTable with Rating keys, each rating range maps to an index of the HashTable
	// A reference to a List of Movie objects with a rating in that range is found
	private HashTableMap<String, ArrayList<MovieInterface>>  ratingTable = new HashTableMap<String, ArrayList<MovieInterface>>();
	
	// selectedGenres is the list of genres user is currently filtering by
	private ArrayList<String> selectedGenres = new ArrayList<String>();
	// allGenres is the list of all genres associated with the whole group of movies, added to when the Backend is created
	private ArrayList<String> allGenres = new ArrayList<String>();
	
	// selectedRatings is the list of rating user is currently filtering by
	private ArrayList<String> selectedRatings = new ArrayList<String>();
	
	// currMovieList is the list of references to movies matching all currently active genre and rating filters
	// this list is updated whenever filters are added or removed
	private ArrayList<MovieInterface> currMatchesList = new ArrayList<MovieInterface>();
		
	/**
	* This constructor instantiates a backend with the command line arguments
	* passed to the app when started by the user. The arguments are expected
	* to contain the path to the data file that the backend will read in.
	* @param args list of command line arguments passed to front end
	*/
	public Backend(String[] args) {
		// Create a new FileReader to read data from the file at the path passed as args
		FileReader myFileReader;
    try {
      myFileReader = new FileReader(args[0]);
      
      // Create an instance of MovieDataReader to access its readDataSet() method
      MovieDataReader dataReader = new MovieDataReader();
      // Use readDataSet() to obtain a list of movies
      List<MovieInterface> movieList;
      try {
        movieList = dataReader.readDataSet(myFileReader);
        initializeBackend(movieList);
      } catch (Exception e) {

        e.printStackTrace();
      }
    } catch (Exception e) {

      e.printStackTrace();
    }
	}

	/**
	* A constructor that will create a backend from data that can be read in
	* with a FileReader object.
	* @param myFileReader A reader that contains the data in CSV format.
	*/
	public Backend(FileReader myFileReader) {
		// Create an instance of MovieDataReader to access its readDataSet() method
		MovieDataReader dataReader = new MovieDataReader();
		// Use readDataSet() to obtain a list of movies
		List<MovieInterface> movieList;
    try {
      movieList = dataReader.readDataSet(myFileReader);
      initializeBackend(movieList);
    } catch (Exception e) {
      e.printStackTrace();
    }
		

	}

	/**
	* Helper method called by constructor. Takes a list of movies and uses the attached genres and ratings
	* to add the movies where they belong in the respective genre and rating HashTables
	* @param movieList list of movies
	*/
	private void initializeBackend(List<MovieInterface> movieList){

		for(MovieInterface thisMovie: movieList){ // Goes through all movies in the List above

			for(String genreString: thisMovie.getGenres()){ // Goes through every genre of the current movie

				if(!genreTable.containsKey(genreString)){
					// If the genre list table does not already have a list of
					// movies for this genreString, create a new List
					genreTable.put(genreString, new ArrayList<MovieInterface>());
					
					// Also adds genre to the list of all genres
					allGenres.add(genreString);
				}
				
				// Once a list of movies for this genreString exists in genreTable,
				// add a reference to thisMovie to it
				try{
					genreTable.get(genreString).add(thisMovie);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			// Convert the Float rating (ex. 6.789) to a single integer string (ex. "6")
			String ratingString = "" + (thisMovie.getAvgVote().intValue());

			if(!ratingTable.containsKey(ratingString)){
				// If the rating list table does not already have a list of
				// movies for this rating value/range string, create one
				ratingTable.put(ratingString, new ArrayList<MovieInterface>());
			}

			// Once a list of movies for this ratingString exists in ratingTable,
			// add a reference to thisMovie to it
			try{
				ratingTable.get(ratingString).add(thisMovie);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		// Once this outer for loop finishes, all movies should be stored in every rating/genre list
		// they belong in (in both HashTables)
	}

	/**
	* Method to add a genre filter that the user entered.
	* @param genre to add
	*/
	@Override
	public void addGenre(String genre) {
		selectedGenres.add(genre);
		updateMatchesList();
	}

	/**
	* Method to add a rating (range) filter that the user entered.
	* @param rating (range, ex. 6.000 - 6.999) to add
	*/
	@Override
	public void addAvgRating(String rating) {
		selectedRatings.add(rating);
		// Keep selectedRatings sorted in descending order when new values are added
		Collections.sort(selectedRatings);
		Collections.reverse(selectedRatings);
		updateMatchesList();
	}

	/**
	* Method to remove a genre filter that the user selected.
	* @param genre to remove
	*/
	@Override
	public void removeGenre(String genre) {
		selectedGenres.remove(genre);
		updateMatchesList();
	}

	/**
	* Method to remove a rating filter that the user selected.
	* @param rating (range) to remove
	*/
	@Override
	public void removeAvgRating(String rating) {
		selectedRatings.remove(rating);
		updateMatchesList();
	}	
	
	/**
	* Helper method called when genre or rating filters are updated
	* Uses selectedGenres and selectedRatings to match movies using the respective HashTables
	* This method keeps the currMatchesList always correct and in order of descending rating
	*/
	private void updateMatchesList() {
		currMatchesList = new ArrayList<MovieInterface>();
		
		
		for(String rating: selectedRatings){
			try{
				currMatchesList.addAll(ratingTable.get(rating));
			}
			catch(Exception e){
				// Comment out debug message before submitting
				/*
				System.out.println("DEBUG: No movie list found, no 
					movies in the rating range " + rating + "-" + rating + ".999");
				*/
			}
		}
		
		Collections.sort(currMatchesList);
		
		// currMatchesList now contains all movies in the lists of the 
		// selected ratings, sorted in descending order
		
		// Now we must go through the sorted List and remove and movies that
		// do not have all of the selected genres
		
		for(MovieInterface thisMovie: currMatchesList){
				List<String> movieGenres = thisMovie.getGenres();
				
				for(String thisSelectedGenre: selectedGenres){
					if(!movieGenres.contains(thisSelectedGenre)){
						currMatchesList.remove(thisMovie);
					}	
				}
		}
		
	}

	/**
	* Return the genres currently filtering this backend object.
	* @return selectedGenres ArrayList
	*/
	@Override
	public List<String> getGenres() {
		return selectedGenres;
	}

	/**
	* Return the ratings currently filtering this backend object.
	* @return selectedRatings ArrayList
	*/
	@Override
	public List<String> getAvgRatings() {
		return selectedRatings;
	}

	/**
	* Returns the number of movies, the size of the current Matches List
	* @return size() of currMatchesList
	*/
	@Override
	public int getNumberOfMovies() {
		return currMatchesList.size();
	}

	/**
	* Returns all genres in the dataset.
	* @return list of allGenres
	*/
	@Override
	public List<String> getAllGenres() {
		return allGenres;
	}

	/**
	* Returns a list of three movies starting at (and including) the movie at the startingIndex 
	* of the resulting set ordered by average movie rating in descending order.  
	* @param startingIndex index of currMatchesList to start at
	* @return sub-list of Movies starting at specified index
	*/
	@Override
	public List<MovieInterface> getThreeMovies(int startingIndex) {
	  List<MovieInterface> ret;
		if(startingIndex > currMatchesList.size()-1){
			// If the starting index is too large, return empty list
			return new ArrayList<MovieInterface>();
		}
		
		try{
			// Try to return sublist of length 3 starting at startingIndex
			ret = currMatchesList.subList(startingIndex, startingIndex+3);
		}
		catch(IndexOutOfBoundsException e){
			// If that threw exception, then end index was too large
			// We must end the sublist before the end of currMatchesList
			ret = currMatchesList.subList(startingIndex, currMatchesList.size()); 
			// size(), not size()-1 because the end index is EXclusive
		}
		
		return ret;
    }
}