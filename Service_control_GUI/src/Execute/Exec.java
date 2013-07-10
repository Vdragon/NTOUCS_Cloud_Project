package Execute;
import java.util.*;
import java.io.*;
public class Exec 
{
	private ArrayList<String> errLine;
    private ArrayList<String> outLine;
	public Exec()
	{
		errLine=new ArrayList<String>();
		outLine=new ArrayList<String>();
	}
	
	public void exex(String input)
	{
		errLine=new ArrayList<String>();
		outLine=new ArrayList<String>();
		String tmp;
	try {        
		
        Runtime rt = Runtime.getRuntime ();
        System.out.print("\n\n"+input+"\n\n");
        Process proc = rt.exec (input);
        

        InputStream stderr = proc.getErrorStream ();
        InputStreamReader esr = new InputStreamReader (stderr);
        BufferedReader ebr = new BufferedReader (esr);
       System.out.println ("<error>");
        while ( (tmp = ebr.readLine ()) != null)
        {
        	this.errLine.add(tmp);
        	System.out.println(errLine);
        }System.out.println ("</error>");
         
        InputStream stdout = proc.getInputStream ();
        InputStreamReader osr = new InputStreamReader (stdout);
        BufferedReader obr = new BufferedReader (osr);
        System.out.println ("<output>");
        
        while ( (tmp = obr.readLine ()) != null)
        {
        	this.outLine.add(tmp);
        	System.out.println(outLine);
        }System.out.println ("</output>");

        int exitVal = proc.waitFor ();
        System.out.println ("Process exitValue: " + exitVal);
    }
    catch (Exception e) {
    	System.out.print("ERROR!!!!");
        e.printStackTrace();
    }
	}
	public ArrayList<String> getOutputLine()
	{
		return this.outLine;
	}
	public static void print(String tmp)
	{
		System.out.println(tmp);
		
	}

}
