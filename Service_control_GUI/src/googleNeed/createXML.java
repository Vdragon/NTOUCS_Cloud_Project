package googleNeed;
import java.io.*;
import java.util.*;

public class createXML
{
	private String name,address,lat,lng,type;
	
  public void XML(ArrayList data) 
  {
   
	String saveFile = "/var/www/house/tab/outputtmp.xml";
	int count = -1;
	for(Iterator c = data.iterator(); c.hasNext();)
    {
	  count++;
  	  if(count > 8)
  		  break;
  	  
  	      for(Iterator d = ((ArrayList)c.next()).iterator();d.hasNext();)
	      {
	    	  if(count < 5)
	    		  break;
	    	  d.hasNext();
	    	  String temp;
	    	  temp = (String)d.next();
	    	  if(count == 5)
	    		  name = temp;
	    	  else if(count == 6)
	    		  lng = temp;
	    	  else if(count == 7)
	    		  lat = temp; 
	    	  else if(count == 8)
	    	  {
	    		 if(temp.equals("中信房屋"))
	    			 type = "cthouse";
	    		 else if(temp.equals("東森房屋"))
	    			 type = "etwarm";
	    		 else if(temp.equals("台灣房屋"))
	    			 type = "twhg";
	    		 
	    	  }
	    //	  System.out.print(temp);
	      
	      }
	    //  System.out.println();
	      
    }
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(saveFile));
      BufferedWriter out = new BufferedWriter(new FileWriter("/var/www/house/tab/outputtmp.txt"));
      String buf;
	    
		while ((buf = in.readLine()) != null)
		{
				out.write(buf.replaceAll("</MARKERS>","<MARKER>\n<NAME>"+name+"</NAME>\n<LNG>"+lng+"</LNG>\n<LAT>"+lat+"</LAT>\n<TYPE>"+type+"</TYPE>\n</MARKER>"));
				out.newLine();
				
		}
		out.write("</MARKERS>");
		out.close();
		in.close();
		
		BufferedReader tin = new BufferedReader(new FileReader("/var/www/house/tab/outputtmp.txt"));
		BufferedWriter tout = new BufferedWriter(new FileWriter(saveFile));
		String tbuf;
		while ((tbuf = tin.readLine()) != null)
		{
			tout.write(tbuf);
			tout.newLine();
		}
		tout.close();
		tin.close();
   
    }
    catch(Exception e)
    {
      System.out.println("Error!");
      e.printStackTrace();
    }
  }
  
  public void changeFile()
  {
	
	  File defile = new File("/var/www/house/tab/output.xml");
	  File file = new File("/var/www/house/tab/outputtmp.xml");
	  File file2 = new File("/var/www/house/tab/output.xml");
	  file.renameTo(file2);
	  
  }
 }