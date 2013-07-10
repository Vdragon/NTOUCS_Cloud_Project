package googleNeed;
import java.io.*;

public class PrecreateXML
{
	public void CreateFile() throws IOException 
	{
		FileWriter out = new FileWriter(new File("/var/www/house/tab/outputtmp.xml"));
		FileWriter tout = new FileWriter(new File("/var/www/house/tab/outputtmp.txt"));
		out.write("<MARKERS>\n");
		out.write("</MARKERS>");
		out.close();
		tout.close();
	}
}