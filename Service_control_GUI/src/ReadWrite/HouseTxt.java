package ReadWrite;

import java.io.*;
import java.util.*;

public class HouseTxt
{
  public void ToTxt(ArrayList data) 
  {
	  File saveFile=new File("/tmp/houseinfo.txt");
	  try
	    {
	      FileWriter fwriter=new FileWriter(saveFile);
	      int count = 0;
	      
	      for(Iterator c = data.iterator(); c.hasNext();)
	      {
	    	    	
		      for(Iterator d = ((ArrayList)c.next()).iterator();d.hasNext();)
		      {
		    	  String temp;
		    	  temp = (String)d.next();
		    	  fwriter.write(temp);
		    //	  System.out.print(temp);
		      }
		    //  System.out.println();
		      fwriter.write("\n");
		      count++;
	      }
	      
	      fwriter.close();
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
  }
  
}
