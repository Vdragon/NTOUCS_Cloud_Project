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
		
		String urls="";
		ArrayList checked_URL = new ArrayList();
		ArrayList<String> tmp=new ArrayList<String>();
		for(int i=381786;i<382086;i++){
			urls = "http://auto.8891.com.tw/usedauto-infos-"+i+".html";
			tmp.add(urls);
			checked_URL.add(tmp);
			tmp=new ArrayList();
		}
		/*for(int i=0;i<checked_URL.size();i++){
			System.out.println(((ArrayList)checked_URL.get(i)).get(0).toString());
		}*/
		
		HTMLGetter test=new HTMLGetter(checked_URL); 
		
		test.totalHtmlPase();
		/*try
		{
			test.htmlPase("http://auto.8891.com.tw/usedauto-infos-381786.html",1);
		}
		catch(Exception e)
		{
			System.err.print("HTMLGetterTest.main  error!!\n");
		}
		*/
	//	test.create_URLS();
		System.out.println("end getter!!");
		//System.err.print("台灣  "+test.get_urlLost()[0]+"\n東森  "+test.get_urlLost()[1]+"\n中信  "+test.get_urlLost()[2]);
		
		
	}

}
