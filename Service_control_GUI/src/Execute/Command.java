package Execute;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;
import java.lang.Runtime;
import java.util.ArrayList;




public class Command
{
	private	 String c="",d="";
	private static String npath="",nsolar="";
	private Exec cmd =null;
	
	public Command()
	{
		cmd=new Exec();
	}
	public void scp(String filePath)
	{
		cmd.exex("hostname");
		String notScpNode=cmd.getOutputLine().get(0);
		for(int i=1;i<10;i++)
		{	
			if(!notScpNode.equals("node"+i))
				cmd.exex("scp -r "+filePath+" node"+i+":"+filePath);
		}
	}
	public void scp(String filePath ,String targerPath)
	{
		cmd.exex("hostname");
		String notScpNode=cmd.getOutputLine().get(0);
		for(int i=1;i<10;i++)
		{	
			if(!notScpNode.equals("node"+i))
				cmd.exex("scp -r "+filePath+" node"+i+":"+targerPath);
		}
	}
	public void Start_Nutch()
	{
		try {
			 String store;			
		     BufferedReader br = new BufferedReader(new FileReader("config.txt"));
		     String line;
		     line = br.readLine();
		     while(line != null)
		     {
		    	 if(line.length()>12){
		    	 store = line.substring(0,11);
		    	 if(store.compareTo("nutch_npath")==0) {
		    		 c=line;
		    		 npath = line.substring(12);
		    	 }
		    	 else if(store.compareTo("solar_npath")==0) {
		    		 d=line;
		    		 nsolar = line.substring(12);
		    	 }
		     }
		    	 line = br.readLine();
		     
		     }
		    	
		    	 c = c + "\n";
		    	 d = d + "\n";

			     br.close();
			    }
				catch (IOException e) {
			     e.printStackTrace();
			    }	
		
		cmd.exex(npath+"/bin/start-all.sh");
	}
	public void Stop_Nutch()
	{
		cmd.exex(npath+"/bin/stop-all.sh");
	}
	public void crawl_Nutch(int depth,int width) 
	{
		cmd.exex(npath+"/bin/nutch crawl urls -solr http://node1:8080/solr/ -depth "+depth+" -topN "+width);
	}
	public void Upload_HDFS(String filePath,String targetPath)
	{
		cmd.exex(npath+"/bin/hadoop fs -put "+filePath+" "+targetPath);
	}
	public void delete_HDFS(String filePath)
	{
		cmd.exex(npath+"/bin/hadoop fs -rmr "+filePath);
	}

	public void delete_TmpImage()
	{
		cmd.exex("rm -r /opt/tmp/image");
		cmd.exex("mkdir /opt/tmp/image");
	}
	public void C_Ctrl()
	{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
			    System.out.println("Ctrl C\n\n");
			}
		    });
	}
	
	public void start_Solr()
	{
		try {
			String store;			
		     BufferedReader br = new BufferedReader(new FileReader("config.txt"));
		     String line;
		     line = br.readLine();
		     while(line != null)
		     {
		    	 if(line.length()>12){
		    	 store = line.substring(0,11);
		    	 if(store.compareTo("nutch_npath")==0) {
		    		 c=line;
		    		 npath = line.substring(12);
		    	 }
		    	 else if(store.compareTo("solar_npath")==0) {
		    		 d=line;
		    		 nsolar = line.substring(12);
		    	 }
		     }
		    	 line = br.readLine();
		     
		     }
		    	
		    	 c = c + "\n";
		    	 d = d + "\n";

			     br.close();
			    }
				catch (IOException e) {
			     e.printStackTrace();
			    }
		
		cmd.exex(nsolar+"/bin/startup.sh");
	}
	public void stop_Solr()
	{
		cmd.exex(nsolar+"/bin/shutdown.sh");
	}
	public void delete_SolrData()
	{
		cmd.exex("rm -r "+nsolar+"/solr/data");
		cmd.exex("mkdir "+nsolar+"/solr/data");
	}
	public void CreateNewDir_Solr()
	{
		this.stop_Solr();
		this.delete_SolrData();
	
		this.start_Solr();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void gedit(String filePath)
	{
		cmd.exex("gedit "+filePath);
	}
	public void stop_SafeMode()
	{
		cmd.exex(npath+"/bin/hadoop dfsadmin -safemode leave");
	}
	
	public void reNameDir_Nutch(String sourceDir,String targetDir)
	{
		cmd.exex(npath+"/bin/hadoop fs -rmr /"+targetDir);
		cmd.exex(npath+"/bin/hadoop fs -mkdir /"+targetDir);
		cmd.exex(npath+"/bin/hadoop fs -mv /"+sourceDir+"/* /"+targetDir+"");
	}	
}
