package test;

import imgDownloader.ImageDownload;
import html.HTMLPaser;

public class HTMLPasertest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//HTMLPaser test=new HTMLPaser("https://www.hotcar.com.tw/CWA/CWA060.aspx?TSEQNO=83058");
		HTMLPaser test=new HTMLPaser("https://www.hotcar.com.tw/CWA/CWA060.aspx?TSEQNO=86697");
		test.html_try();	
		try {
			test.paseHTML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//http://auto.8891.com.tw/usedauto-infos-340734.html
	
		
		System.out.print("exit");
		
	}

}
