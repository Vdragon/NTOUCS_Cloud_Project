package test;

import imgDownloader.ImageDownload;
import html.HTMLPaser;

public class HTMLPasertest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HTMLPaser test=new HTMLPaser("http://www.cthouse.com.tw/showRoom/housedetail.aspx?houseId=707337");
			
		try {
			test.paseHTML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
		
		System.out.print("exit");
		
	}

}
