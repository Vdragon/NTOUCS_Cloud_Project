package Execute;
import java.lang.System;
import java.lang.Runtime;
import java.util.ArrayList;

public class Command
{
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
		cmd.exex("/opt/nutch/bin/start-all.sh");
	}
	public void Stop_Nutch()
	{
		cmd.exex("/opt/nutch/bin/stop-all.sh");
	}
	public void crawl_Nutch(int depth,int width) 
	{
		cmd.exex("/opt/nutch/bin/nutch crawl urls -solr http://node1:8080/solr/ -depth "+depth+" -topN "+width);
	}
	public void Upload_HDFS(String filePath,String targetPath)
	{
		cmd.exex("/opt/nutch/bin/hadoop fs -put "+filePath+" "+targetPath);
	}
	public void delete_HDFS(String filePath)
	{
		cmd.exex("/opt/nutch/bin/hadoop fs -rmr "+filePath);
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
		cmd.exex("/opt/tomcat7/bin/startup.sh");
	}
	public void stop_Solr()
	{
		cmd.exex("/opt/tomcat7/bin/shutdown.sh");
	}
	public void delete_SolrData()
	{
		cmd.exex("rm -r /opt/tomcat7/solr/data");
		cmd.exex("mkdir /opt/tomcat7/solr/data");
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
		cmd.exex("/opt/nutch/bin/hadoop dfsadmin -safemode leave");
	}
	
	public void reNameDir_Nutch(String sourceDir,String targetDir)
	{
		cmd.exex("/opt/nutch/bin/hadoop fs -rmr /"+targetDir);
		cmd.exex("/opt/nutch/bin/hadoop fs -mkdir /"+targetDir);
		cmd.exex("/opt/nutch/bin/hadoop fs -mv /"+sourceDir+"/* /"+targetDir+"");
		
}	
	
}
