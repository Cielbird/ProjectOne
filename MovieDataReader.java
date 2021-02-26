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

        // check for columns
        ch = inputFileReader.read();
        if((ch != 10 && ch != 13 &&  ch != -1) && !(ch >= 32 && ch <= 126)) {
            throw new IOException();
        }
        while(ch != 10){
            if(ch == 13){
                // avoid carriage returns
            } else if(ch != 44){
                buffer += (char)(ch);
            } else {
                // Part A
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
            }
            // Continues loop
            ch = inputFileReader.read();
            if((ch != 10 && ch != 13 &&  ch != -1) && !(ch >= 32 && ch <= 126)) {
                throw new IOException();
            }
        }
        // Part A copy
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

        // for movies
        ch = inputFileReader.read();
        if((ch != 10 && ch != 13 &&  ch != -1) && !(ch >= 32 && ch <= 126)) {
            throw new IOException();
        }
        while(ch != -1){
            while(ch != 10){
                if(ch == 13){
                    // be sad for carriage returns
                } else if(ch == 34){
                    quotation_count++;
                } else if(ch != 44 || quotation_count % 2 != 0){
                    buffer += (char)(ch);
                } else if (quotation_count % 2 == 0){
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
                            // System.out.println(buffer);
                            String[] pieces = buffer.split(",");
                            for(int i = 0;i<pieces.length;i++){
                                genres.add(pieces[i]);
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
                            try{
                            Float.valueOf(buffer);
                            } catch(NumberFormatException nfe) {
                                throw new DataFormatException("Bad Average Vote");
                            }
                            movie.setAvgVote(Float.valueOf(buffer));
                        }
                    input_elements.add(buffer);
                    buffer = "";
                    quotation_count = 0;
                }
                // to continue loop
                ch = inputFileReader.read();
                if((ch != 10 && ch != 13 &&  ch != -1) && !(ch >= 32 && ch <= 233)) {
                    throw new IOException();
                }
            }
            // catch the last bit
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
            buffer = "";
            output_list.add(movie);
            movie = new Movie();
            if(input_elements.size() != column_elements.size()){
                throw new DataFormatException("Movie had "+input_elements.size()+"elements, not "+column_elements.size());
            }
            input_elements.clear();
            // to continue loop
            ch = inputFileReader.read();
            if((ch != 10 && ch != 13 &&  ch != -1) && !(ch >= 32 && ch <= 126)) {
                throw new IOException();
            }
        }
        return output_list;
    }
}