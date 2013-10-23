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
		PrecreateXML pre = new PrecreateXML();
		pre.CreateFile();
		Solr tmp=new Solr();
		tmp.defaultRun();
		HTMLGetter test=new HTMLGetter(tmp.getCheckedURL()); 
		
		
		//test.totalHtmlPase();
		try
		{
			test.htmlPase("http://www.twhg.com.tw/object.php?obj=TF01251976",1);
		}
		catch(Exception e)
		{
			System.err.print("153");
		}
		
	//	test.create_URLS();
		System.out.println("end getter!!");
		System.err.print("台灣  "+test.get_urlLost()[0]+"\n東森  "+test.get_urlLost()[1]+"\n中信  "+test.get_urlLost()[2]);
		
		
	}

}
