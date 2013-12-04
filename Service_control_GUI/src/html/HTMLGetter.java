package html;

import googleNeed.createXML;
import imgDownloader.ImageDownload;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import Execute.Command;
import ReadWrite.HouseTxt;

import mysql.PHPMysql;

public class HTMLGetter {

	/**
	 * @param args
	 */
	private ArrayList totalURL;
	private ArrayList newURL;
	private final String HouseAgence[] = {"cthouse","twhg","etwarm"};
	/* urlLost[0] twhouse
	 * urlLost[1] etwarm
	 * urlLost[2] cthouse */
	private int urlLost[];
	public HTMLGetter(ArrayList url)
	{
		totalURL=url;
		newURL=new ArrayList();
		urlLost = new int[4];
	}
	public int [] get_urlLost()
	{
		return this.urlLost;
	}

	public void totalHtmlPase()
	{
		for(int i=0;i<totalURL.size();i++)
		{
			System.out.println("now URL"+((ArrayList)this.totalURL.get(i)).get(0).toString());
			try
			{
				this.htmlPase(((ArrayList)this.totalURL.get(i)).get(0).toString(),i);
			}
			catch(Exception e)
			{
				System.err.println("Pass this URL!!");
			}
		}
	}
	
	public void htmlPase(String url,int i) throws Exception 
	{
		
		HTMLPaser t = new HTMLPaser(url);
		/*check url timeout*/
		try {
			t.html_try();
		} catch (Exception e){
			System.err.print("this URL: "+url+" time out!!\n");
		}
		/*check object exist */
		if(t.get_html_ok()==true&&t.checkURLExist(urlLost)==true)
		{
			t.paseHTML();
			this.houseImageDownload(t.getHouseImageURL());
			
			this.houseInfoDownload(t.getHouseInfo());
			
			int id =this.houseMysqlUpload(t.getHouseInfo());
			this.hosueHDFSUpload(id);
			this.houseXmlPaser(t.getHouseInfo());
		}
	}
	private void houseImageDownload(String [] tmp)
	{
		Command deleteTmp=new Command();
		deleteTmp.delete_TmpImage();
		ImageDownload t=new ImageDownload();
		for(int i=0;i<tmp.length;i++)
			t.saveImage(tmp[i],"/opt/nutch/tmp/image/"+(i+1)+".jpg");
	
		
	}
	private void houseInfoDownload(ArrayList tmp)
	{
		HouseTxt ht = new HouseTxt();
		ht.ToTxt(tmp);
		
		
	}
	private int houseMysqlUpload(ArrayList tmp)
	{
		PHPMysql t=new PHPMysql();
		String money="";
		String splitMoney []=((ArrayList)tmp.get(1)).get(1).toString().split("萬");
		((ArrayList)tmp.get(1)).set(1, splitMoney[0]);
		
		for(int i=0;i<((ArrayList)tmp.get(1)).get(1).toString().length();i++)
		{
			if((((ArrayList)tmp.get(1)).get(1).toString().toCharArray())[i]<58
					&&((ArrayList)tmp.get(1)).get(1).toString().toCharArray()[i]>47)
			{
				money+=(((ArrayList)tmp.get(1)).get(1).toString().toCharArray())[i];
			}
		}
		int real_money;
		try
		{
			real_money=Integer.parseInt(money)*10000;
		}catch(Exception e)
		{
			System.err.println("money crash");
			real_money=6666;
		}
		
		
		
		t.insertTable(((ArrayList)tmp.get(5)).get(1).toString(),
				((ArrayList)tmp.get(2)).get(1).toString().toString(),
				((ArrayList)tmp.get(9)).get(1).toString().toString(),
				real_money);
		int re=t.getTitleID(((ArrayList)tmp.get(5)).get(1).toString());
		t.close();
		return re;
	}
	private void hosueHDFSUpload(int id)
	{
		Command t =new Command();
		t.Upload_HDFS("/opt/nutch/tmp", "/HouseTmp/"+id);
	}
	private void houseXmlPaser(ArrayList tmp)
	{
		
		createXML xml = new createXML();
		xml.XML(tmp);		
		
		
		
	}
	

	public void create_URLS() 
	{
		PHPMysql t=new PHPMysql();
		for(int i = 0;i < 3;i++)
			t.get_URLS(newURL,HouseAgence[i], 5
					);
		System.out.print("");
		File saveFile=new File("/opt/nutch/urls/urls.txt");
		  try
		    {
		      FileWriter fwriter=new FileWriter(saveFile);
		      for(Iterator c = newURL.iterator(); c.hasNext();)
		      {
			    	  String temp;
			    	  temp = (String)c.next();
			    	  fwriter.write(temp);
			    	  fwriter.write("\n");
		      }
		      fwriter.close();
		    }
		    catch(Exception e)
		    {
		    System.err.print("url can't write~~~!\n");
		      e.printStackTrace();
		    }

	}
	
	
	

}
