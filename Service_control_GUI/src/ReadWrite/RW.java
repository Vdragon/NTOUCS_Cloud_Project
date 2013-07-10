package ReadWrite;

import java.io.*;

public class RW{
	public void WriteFile(String str[] ,String path) throws Exception
	{

		BufferedReader in = new BufferedReader(new FileReader(path));
		BufferedWriter out = new BufferedWriter(new FileWriter("/opt/nutch/conf/reg-temp.txt"));
	    String buf;
	    
		while ((buf = in.readLine()) != null)
		{
			out.write(buf);
			if(buf.equals("# accept anything else"))
			{
				for(String tem : str)
				out.write("\n"+tem);
				out.write("\n# accept URLs containing certain characters as probable queries, etc.");
				out.write("\n+[?*!@%&_=\\-]");
				break;
			}
			out.newLine();
		}
		System.out.println("END");
		out.close();
		in.close();
		Write(path);
		
	}
	public void Write(String path) throws Exception
	{
		BufferedReader tin = new BufferedReader(new FileReader("/opt/nutch/conf/reg-temp.txt"));
		BufferedWriter tout = new BufferedWriter(new FileWriter(path));
		String tbuf;
		while ((tbuf = tin.readLine()) != null)
		{
			tout.write(tbuf);
			
			tout.newLine();
		}
		System.out.println("END");
		tout.close();
		tin.close();
	}
	
}
