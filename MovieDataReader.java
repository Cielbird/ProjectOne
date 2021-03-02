// --== CS400 File Header Information ==--
// Name: jacob larget
// Email: jlarget@wisc.edu
// Team: jd blue
// Role: data wrangler
// TA: xinyi
// Lecturer: florian heimerl

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class MovieDataReader implements MovieDataReaderInterface{
	public List<MovieInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException{
        List<MovieInterface> output_list = new ArrayList<MovieInterface>();
        List<String> column_elements = new ArrayList<String>();
        List<String> input_elements = new ArrayList<String>();
        int[] column_index;
        column_index = new int[6];
        String buffer = "";
        Integer quotation_count = 0;
        int ch;
        Movie movie = new Movie();
        String title;
        Integer year;
        String director;
        String description;
        Float avg_vote;

        // parse the header line to decode the columns

        // store character from reader as int to evaluate
        // .read() will throw IOException if one exists
        ch = inputFileReader.read();

        // while the character is not a newline
        while(ch != 10){
            if(ch == 13){
                // avoid carriage returns
            } else if(ch != 44){
                // character isn't a comma, so we should add it
                buffer += (char)(ch);
            } else {
                // the character is a comma, so let's see if this is an important column
                // if so, set the corresponding value in column_index[] to our current size to mark it
                if(buffer.equals("title")){
                    column_index[0] = column_elements.size();
                }
                if(buffer.equals("year")){
                    column_index[1] = column_elements.size();
                }
                if(buffer.equals("genre")){
                    column_index[2] = column_elements.size();
                }
                if(buffer.equals("director")){
                    column_index[3] = column_elements.size();
                }
                if(buffer.equals("description")){
                    column_index[4] = column_elements.size();
                }
                if(buffer.equals("avg_vote")){
                    column_index[5] = column_elements.size();
                }
                // store buffer in column list to help with counting and debugging
                column_elements.add(buffer);
                // reset buffer
                buffer = "";
            }
            // Continues loop
            // .read() will throw IOException if one exists
            ch = inputFileReader.read();
        }
        // after the while loops exits, we still need to evaluate the buffer.
        // this is a copy from above.
        if(buffer.equals("title")){
            column_index[0] = column_elements.size();
        }
        if(buffer.equals("year")){
            column_index[1] = column_elements.size();
        }
        if(buffer.equals("genre")){
            column_index[2] = column_elements.size();
        }
        if(buffer.equals("director")){
            column_index[3] = column_elements.size();
        }
        if(buffer.equals("description")){
            column_index[4] = column_elements.size();
        }
        if(buffer.equals("avg_vote")){
            column_index[5] = column_elements.size();
        }
        column_elements.add(buffer);
        buffer = "";

        // now, parse the rest of the file , converting each line into a Movie
        // start onto the next line
        // .read() will throw IOException if one exists
        ch = inputFileReader.read();
        // while the character isn't the end of the file, there are movies to list
        while(ch != -1){
            // while the character isn't a newline, we have a row to evaluate
            while(ch != 10){
                if(ch == 13){
                    // be sad for carriage returns
                } else if(ch == 34){
                    // if we are a quotation mark, note that we should *open* the quotation
                    quotation_count++;
                } else if(ch != 44 || quotation_count % 2 != 0){
                    // if we aren't a comma, or a quotation is open, we should add to the buffer
                    buffer += (char)(ch);
                } else if (quotation_count % 2 == 0){
                    // if we have closed the quotation and we got this far, we are a comma
                    // so we need to evaluate the buffer
                    // if we are in the important columns, we will update the Movie to include us
                        if(column_index[0] == input_elements.size()){
                            movie.setTitle(buffer);
                        }
                        if(column_index[1] == input_elements.size()){
                            // since this might not be an integer, use try/catch to ensure we have proper data format
                            try{
                                Integer.valueOf(buffer);
                            } catch(NumberFormatException nfe) {
                                throw new DataFormatException("Bad Year");
                            }
                            movie.setYear(Integer.valueOf(buffer));
                        }
                        if(column_index[2] == input_elements.size()){
                            // initialize new list to wipe from previous values
                            List<String> genres = new ArrayList<String>();
                            // this buffer contains all of the genres, but we need them in a list
                            // separate by commas
                            String[] pieces = buffer.split(",");
                            for(int i = 0;i<pieces.length;i++){
                                // add to the list, with no whitespace on either side
                                genres.add(pieces[i].strip());
                            }
                            movie.setGenres(genres);
                        }
                        if(column_index[3] == input_elements.size()){
                            movie.setDirector(buffer);
                        }
                        if(column_index[4] == input_elements.size()){
                            movie.setDescription(buffer);
                        }
                        if(column_index[5] == input_elements.size()){
                            // since this might not be a float, use try/catch to ensure we have proper data format
                            try{
                            Float.valueOf(buffer);
                            } catch(NumberFormatException nfe) {
                                throw new DataFormatException("Bad Average Vote");
                            }
                            movie.setAvgVote(Float.valueOf(buffer));
                        }
                    // add buffer to list to track movement (and help debug)
                    input_elements.add(buffer);

                    // clear buffer and reset quotation check
                    buffer = "";
                    quotation_count = 0;
                }
                // to continue loop
                // .read() will throw IOException if one exists
                ch = inputFileReader.read();
            }
            
            // after the while loops exits, we still need to evaluate the buffer.
            // this is a copy from above.
            if(column_index[0] == input_elements.size()){
                            movie.setTitle(buffer);
                        }
                        if(column_index[1] == input_elements.size()){
                            try{
                                Integer.valueOf(buffer);
                            } catch(NumberFormatException nfe) {
                                throw new DataFormatException("Bad Year");
                            }
                            movie.setYear(Integer.valueOf(buffer));
                        }
                        if(column_index[2] == input_elements.size()){
                            List<String> genres = new ArrayList<String>();
                            genres.add(buffer);
                            movie.setGenres(genres);
                            genres.clear();
                        }
                        if(column_index[3] == input_elements.size()){
                            movie.setDirector(buffer);
                        }
                        if(column_index[4] == input_elements.size()){
                            movie.setDescription(buffer);
                        }
                        if(column_index[5] == input_elements.size()){
                            try{
                            Float.valueOf(buffer);
                            } catch(NumberFormatException nfe) {
                                throw new DataFormatException("Bad Average Vote");
                            }
                            movie.setAvgVote(Float.valueOf(buffer));
                        }
            input_elements.add(buffer);
            
            // now that the row has been parsed, see if we get the same size as our header
            if(input_elements.size() != column_elements.size()){
                // if we haven't, there's a data format problem
                throw new DataFormatException("Movie had "+input_elements.size()+"elements, not "+column_elements.size());
            }

            // if we passed that test, we can add the movie to our output list
            output_list.add(movie);

            // reset all variables for next line
            buffer = "";
            movie = new Movie();
            input_elements.clear();

            // to continue loop
            // .read() will throw IOException if one exists
            ch = inputFileReader.read();
        }

        // since we've reached the end of the file, return the full list of Movies
        return output_list;
    }
}