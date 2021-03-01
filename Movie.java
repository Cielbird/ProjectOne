// --== CS400 File Header Information ==--
// Name: jacob larget
// Email: jlarget@wisc.edu
// Team: jd blue
// Role: data wrangler
// TA: xinyi
// Lecturer: florian heimerl
import java.util.List;

public class Movie implements MovieInterface{
	
    private String title;
    private Integer year;
    private List<String> genres;
    private String director;
    private String description;
    private Float avg_vote;

    public Movie() {
        this.title = null;
        this.year = null;
        this.genres = null;
        this.director = null;
        this.description = null;
        this.avg_vote = null;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setYear(Integer year){
        this.year = year;
    }
    public void setGenres(List<String> genres){
        this.genres = genres;
    }
	public void setDirector(String director){
        this.director = director;
    }
	public void setDescription(String description){
        this.description = description;
    }
	public void setAvgVote(Float avg_vote){
        this.avg_vote = avg_vote;
    }

	public String getTitle(){
        return this.title;
    }
	public Integer getYear(){
        return this.year;
    }
	public List<String> getGenres(){
        return this.genres;
    }
	public String getDirector(){
        return this.director;
    }
	public String getDescription(){
        return this.description;
    }
	public Float getAvgVote(){
        return this.avg_vote;
    }

    public String toString(){
        String output = "";
        output += this.getTitle();
        output += ","+this.getYear();
        output += ",\"";
        for(int i = 0; i<this.getGenres().size(); ++i){
            output += this.getGenres().get(i);
            if(i<this.getGenres().size()-1){
                output +=",";
            }
        }
        output += "\"";
        output += ","+this.getDirector();
        output += ","+this.getDescription();
        output += ","+this.getAvgVote();
        return output;
    }

	// from super interface Comparable
	public int compareTo(MovieInterface otherMovie){
        if(otherMovie.getAvgVote().equals(this.getAvgVote())){
            return 0;
        } else if (otherMovie.getAvgVote().compareTo(this.getAvgVote()) > 0){
            return 1;
        } else {
            return -1;
        }
    }
}
