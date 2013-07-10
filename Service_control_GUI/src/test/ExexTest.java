package test;
import Execute.Exec;
import ReadWrite.RW;
import solr.Solr;


public class ExexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Exec test =new Exec();
	//	test.exex("curl -o /home/hadooper/Desktop/test.txt http://www.etwarm.com.tw/object_A0860_60860AAA22128");
	//test.exex("/opt/nutch/bin/start-all.sh");
	//	test.exex("/opt/tomcat7/bin/startup.sh");
	//	Exec.print("startall final~~");
	//	test.exex("/opt/nutch/bin/nutch crawl urls -solr http://140.121.198.146:8080/solr/ -depth 3 -topN 100 ");
	//	Exec.print("crawl final~~");
		test.exex("/opt/nutch/bin/stop-all.sh");
		test.exex("/opt/tomcat7/bin/shutdown.sh");
	//	test.exex("scp -r /opt/test.txt node4:/opt");
	
		//test.exex("/opt/nutch/bin/hadoop dfsadmin -safemode leave");
	//	Solr test2=new Solr(); 
	//	test2.defaultRun("*:*");
			
	}

}
