import java.io.*;
import java.util.List;
import java.util.zip.DataFormatException;

public interface MovieDataReaderInterface {

    public List<MovieInterface> readDataSet(FileReader inputFileReader)
	throws FileNotFoundException, IOException, DataFormatException;

}
