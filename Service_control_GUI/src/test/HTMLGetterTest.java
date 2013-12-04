package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import solr.Solr;
import googleNeed.PrecreateXML;
import html.HTMLGetter;

public class HTMLGetterTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		/*PrecreateXML pre = new PrecreateXML();
		pre.CreateFile();
		Solr tmp=new Solr();
		tmp.defaultRun();*/
		
		
		ArrayList checked_URL = new ArrayList();
		HTMLGetter test=new HTMLGetter(checked_URL); 
		
		//test.totalHtmlPase();
		try
		{
			test.htmlPase("https://www.hotcar.com.tw/CWA/CWA060.aspx?TSEQNO=86697",1);
		}
		catch(Exception e)
		{
			System.err.print("HTMLGetterTest.main  error!!\n");
		}
		
	//	test.create_URLS();
		System.out.println("end getter!!");
		System.err.print("台灣  "+test.get_urlLost()[0]+"\n東森  "+test.get_urlLost()[1]+"\n中信  "+test.get_urlLost()[2]);
		
		
	}

}
