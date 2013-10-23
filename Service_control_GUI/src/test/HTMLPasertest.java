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
		
		HTMLPaser test=new HTMLPaser("http://www.twhg.com.tw/object.php?obj=TA09941517");
		test.html_try();	
		try {
			test.paseHTML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
		
		System.out.print("exit");
		
	}

}
