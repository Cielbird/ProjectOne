import java.io.*;
import java.util.List;
import java.util.zip.DataFormatException;

public interface MovieDataReaderInterface {

    public List<MovieInterface> readDataSet(Reader inputFileReader)
	throws IOException, DataFormatException;

}
