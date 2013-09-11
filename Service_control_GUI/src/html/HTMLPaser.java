package html;

import imgDownloader.ImageDownload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLPaser 
{
	private String houseAgency=null;
	private String houseURL=null;
	private Document doc=null;
	private String[] imageURL;
	private double []LonLat;
	private ArrayList houseInfo;
	private boolean html_ok=false;
	
	public HTMLPaser(String webURL)
	{
		
		houseURL=webURL;
		
	}
	public void html_try()throws Exception
	{
		URL url=null;
		url = new URL(this.houseURL);
		
		doc=Jsoup.parse(url, 8000);
		
		LonLat=new double[2];
		houseInfo=new ArrayList();
		this.setHouseAngency(this.getRealtor(this.houseURL));  //search for house name
		this.html_ok=true;
		System.out.print("end doing!");
	}
	public boolean get_html_ok()
	{
		return this.html_ok;
	}
	public boolean checkURLExist(int []urlLost)
	{
		String tw=null;
		String etw = null;
		if(this.houseAgency.endsWith("台灣房屋"))
		{
			tw = doc.select("meta").get(2).absUrl("http-equiv").toString();
			if(tw.endsWith("http://www.twhg.com.tw/refresh"))
			{
				urlLost[0]++;
				return false;}
		}
		if(this.houseAgency.endsWith("東森房屋"))
		{
			org.jsoup.select.Elements eles = doc.getElementsByTag("script");
			//Element eles = doc.script();
			String tmp = null;
			for (Element ele : eles) 
			{   
				// 檢查是否有detailInfoObject字串
			   	String script = ele.toString();
			    if (script.indexOf("此物件不存在或是已下架！") > -1)
			    {
			    	urlLost[1]++;
			    	return false;
			    }
			}
		}	
		if(this.houseAgency.endsWith("中信房屋"))
		{
			org.jsoup.select.Elements eles = doc.getElementsByTag("script");
			//Element eles = doc.script();
			String tmp = null;
			for (Element ele : eles) 
			{   
				// 檢查是否有detailInfoObject字串
			   	String script = ele.toString();
			    if (script.indexOf("找不到物件資料!") > -1)
			    {
			    	urlLost[2]++;
			    	return false;
			    }
			}
			
	/*	
			etw = doc.select("script").get(0).toString();
			if(etw.endsWith("此物件不存在或是已下架！"))
				return false;*/
		}
		return true;
	}
	
	
	
	public void paseHTML() throws Exception
	{
		
		this.findHouseLonLat();
		this.findHouseAttitude();//detail of house
		this.findHouseImage();   //image of house
		ArrayList tmp=new ArrayList();
		tmp.add("OrginURL:");
		tmp.add(this.houseURL);
		this.houseInfo.add(tmp);
	
	}

	
	public String [] getHouseImageURL()
	{
		return imageURL;
	}
	public ArrayList getHouseInfo()
	{
		return this.houseInfo;
	}
	
	private String getRealtor(String url)
	{
		if(url.indexOf("etwarm")>0)
		{
			return "東森房屋";
		}
		else if(url.indexOf("twhg")>0)
		{
			return "台灣房屋";
		}
		else if(url.indexOf("cthouse")>0)
		{
			return "中信房屋";
		}
		return "";
	}

	private void setHouseAngency(String name)
	{
		this.houseAgency=name;
	}
	private void findHouseImage() throws Exception
	{
		
		ArrayList gg=new ArrayList();
		Elements table = doc.select("img");
		if(this.houseAgency.endsWith("東森房屋"))
		{
			
			for(int i=0;i<table.size();i++)
				if(table.get(i).toString().indexOf("width=\"114\" height=\"76\"") > 0)
					gg.add(table.get(i).absUrl("src").toString());
			/*for(int i=72;i<76;i++)
			{ 
				Element table = doc.select("div").get(i);

				Iterator<Element> image = table.select("img").iterator();
	       
				if(image.hasNext())
					imageURL[(i-72)]=image.next().absUrl("src").toString();		
			}*/
		}
		
		else if(this.houseAgency.endsWith("台灣房屋"))
		{
	
			
			for(int i = 0; i < table.size();i++)
			{
				if(table.get(i).toString().indexOf("width=\"400\" height=\"300\"") > -1 || table.get(i).toString().indexOf("width=\"100\" height=\"75\"") > 0)
					gg.add(table.get(i).absUrl("src").toString());
					
			}
			
			
		}
		
		else if(this.houseAgency.endsWith("中信房屋"))
		{
						
			for(int i=0;i<table.size();i++)
				if(table.get(i).toString().indexOf("b.jpg") > 0)
					gg.add(table.get(i).absUrl("src").toString());
				
		}
		
		imageURL=new String[gg.size()];
		for(int i=0;i<gg.size();i++)
			this.imageURL[i]=gg.get(i).toString();	
	}
	private ArrayList findHouseAttitude() throws Exception
	{
		Element table=null;
		Iterator<Element> ite =null;
		ArrayList<String> tmp=new ArrayList<String>();
		if(this.houseAgency.endsWith("東森房屋"))
		{
			int [][] rule={{19,21,20,23,36},{20,22,21,24,38}};
			int regex=0;
			table = doc.select("table").get(19);
			 ite = table.select("td").iterator();
			 while(ite.hasNext())
			 {
				 tmp.add(ite.next().text());      
			 }
			 
			 if(tmp.get(0).toString().equals("物件編號："))
			 {
				 regex=1;
			 }
			 tmp=new ArrayList();
			 
			//用途/類別
			 table = doc.select("table").get(rule[regex][0]);
			 ite = table.select("td").iterator();
			 while(ite.hasNext())
			 {
				 tmp.add(ite.next().text());      
			 }
			 
	          this.houseInfo.add(tmp);
	          tmp=new ArrayList();
	          
	        // 售價 (萬): 
	      	 table = doc.select("table").get(rule[regex][1]);
			ite = table.select("td").iterator();
	         
	          while(ite.hasNext())
	          {
	        	  	
	              tmp.add(ite.next().text().replace("新臺幣 TWD 人民幣 CNY 美金 USD 港幣 HKD", ""));      
	              
	          }
	
	          this.houseInfo.add(tmp);
	          tmp=new ArrayList();
         
          // 地址: 
	      	 table = doc.select("table").get(rule[regex][2]);
			ite = table.select("td").iterator();
			
	          while(ite.hasNext())
	          {
	              tmp.add(ite.next().text());      
	          }
	          this.houseInfo.add(tmp);
	          tmp=new ArrayList();
		          
          //  總坪數:
	      	 table = doc.select("table").get(rule[regex][3]);
			ite = table.select("td").iterator();
	          while(ite.hasNext())
	          {
	              tmp.add(ite.next().text());      
	          }
	          this.houseInfo.add(tmp);
	          tmp=new ArrayList();
	          
          //  土地坪數:
	      	 table = doc.select("table").get(rule[regex][4]);
			ite = table.select("td").iterator();
	          while(ite.hasNext())
	          {
	              tmp.add(ite.next().text());      
	          }
	          this.houseInfo.add(tmp); 
	          tmp=new ArrayList();
	     // title
	          table = doc.select("title").first();
	          tmp.add("title：");
	          String [] mysplit=table.text().toString().split("-");
	          mysplit[0] = mysplit[0].replace("<", "").replace(">", "").replace("&", "").replace("/", "");
	          tmp.add(mysplit[0]);
	          this.houseInfo.add(tmp); 
	          tmp=new ArrayList();
	    // 經緯度
	          tmp.add("經度：");
	          if(this.LonLat[0]!=0.0)
	        	  tmp.add(String.valueOf(this.LonLat[0]));
	          else
	          {
	        	  String []gg=AddressToLatLon.addressToCoordination(((ArrayList)houseInfo.get(2)).get(1).toString());
	        	  this.LonLat[0]=Double.parseDouble(gg[0]);
	        	  tmp.add(String.valueOf(this.LonLat[0]));
	          }
	          this.houseInfo.add(tmp); 
	          tmp=new ArrayList();
	          tmp.add("緯度：");
	          if(this.LonLat[1]!=0.0)
	        	  tmp.add(String.valueOf(this.LonLat[1]));
	          else
	          {
	        	  String []gg=AddressToLatLon.addressToCoordination(((ArrayList)houseInfo.get(2)).get(1).toString());
	        	  this.LonLat[1]=Double.parseDouble(gg[1]);
	        	  tmp.add(String.valueOf(this.LonLat[1]));
	          }
	          
	          this.houseInfo.add(tmp); 
	          tmp=new ArrayList();
	          tmp.add("房仲：");
	          tmp.add(this.houseAgency);
	          this.houseInfo.add(tmp);
	          tmp=new ArrayList();
	
		}
		if(this.houseAgency.endsWith("台灣房屋"))
		{
			 table = doc.select("table").get(0);
			 ite = table.select("td").iterator();
			 String[] str = new String[20];
			 int i = 0;
			 while(ite.hasNext())
			 {
				 if(i > 15)
					 break;
				 str[i++] = ite.next().text();
				 
			 }
			 tmp.add("用途/類別：");
			 tmp.add(str[11]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 tmp.add("售價 (萬)：");
			 tmp.add(str[3]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 tmp.add("地址：");
			 tmp.add(str[1]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 tmp.add("總坪數：");
			 tmp.add(str[5]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 tmp.add("主建物坪數：");
			 tmp.add(str[15]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList(); 
			 
			// title
	          table = doc.select("title").first();
	          tmp.add("title：");
	          String [] mysplit=table.text().toString().split(" ");
	          mysplit[0] = mysplit[0].replace("<", "").replace(">", "").replace("&", "").replace("/", "");
	          tmp.add(mysplit[0]);
	          this.houseInfo.add(tmp); 
	          tmp=new ArrayList();
			 
			 tmp.add("經度：");
			 if(this.LonLat[0]!=0.0)
	        	  tmp.add(String.valueOf(this.LonLat[0]));
	          else
	          {
	        	   String []gg=AddressToLatLon.addressToCoordination(((ArrayList)houseInfo.get(2)).get(1).toString());
	        	  this.LonLat[0]=Double.parseDouble(gg[0]);
	        	  tmp.add(String.valueOf(this.LonLat[0]));
	          }
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 tmp.add("緯度：");
			 if(this.LonLat[1]!=0.0)
	        	  tmp.add(String.valueOf(this.LonLat[1]));
	          else
	          {
	        	  String []gg=AddressToLatLon.addressToCoordination(((ArrayList)houseInfo.get(2)).get(1).toString());
	        	  this.LonLat[1]=Double.parseDouble(gg[1]);
	        	  tmp.add(String.valueOf(this.LonLat[1]));
	          }
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 tmp.add("房仲：");
	         tmp.add(this.houseAgency);
	         this.houseInfo.add(tmp);
	         tmp=new ArrayList();
			
		
			
		}
		if(this.houseAgency.endsWith("中信房屋"))
		{
			 table = doc.select("table").get(30);
			 ite = table.select("td").iterator();
			 String[] str = new String[20];
			 int i = 0;
			 while(ite.hasNext())
			 {
				 if(i > 12)
					 break;
				 str[i++] = ite.next().text();
			 }
			 tmp.add("用途/類別：");
			 tmp.add(str[3]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 
			 //售價
			 tmp.add("售價 (萬)：");
			 Element title=null;
			 title = doc.select("title").first();
			 String [] mysplit=title.text().toString().split(",");
	         tmp.add(mysplit[1]);
	         this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 
			 tmp.add("地址：");
			 table = doc.select("title").first();
	         String tt;
	         String adr[];
	         tt = table.text().toString();
	         adr = tt.split("[\\x28-\\x29,\\x2C]");
	 		 if(adr[2].equals(""))
	 			adr[2] = "1600 Pennsylvania Avenue Northwest, Washington, DC";
			 tmp.add(adr[2]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 
			 tmp.add("總坪數：");
			 tmp.add(str[8]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 
			 tmp.add("主建物坪數：");
			 tmp.add(str[10]);
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList(); 
			 
			 table = doc.select("title").first();
	         tmp.add("title：");
	         String st;
	         st = table.text().toString().replace("(", ",");
	         st = st.replace(")", ",");
	         String [] split = st.split(",");
	         split[1] = split[1].replace("<", "").replace(">", "").replace("&", "").replace("/", "");
	         tmp.add(split[1]);
	         this.houseInfo.add(tmp);
	         tmp=new ArrayList();
	         
	         tmp.add("經度：");
			 if(this.LonLat[0]!=0.0)
	        	  tmp.add(String.valueOf(this.LonLat[0]));
	          else
	          {
	        	  String []gg=AddressToLatLon.addressToCoordination(((ArrayList)houseInfo.get(2)).get(1).toString());
	        	  this.LonLat[0]=Double.parseDouble(gg[0]);
	        	  tmp.add(String.valueOf(this.LonLat[0]));
	          }
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 
			 tmp.add("緯度：");
			 if(this.LonLat[1]!=0.0)
	        	  tmp.add(String.valueOf(this.LonLat[1]));
	          else
	          {
	        	  String []gg=AddressToLatLon.addressToCoordination(((ArrayList)houseInfo.get(2)).get(1).toString());
	        	  this.LonLat[1]=Double.parseDouble(gg[1]);
	        	  tmp.add(String.valueOf(this.LonLat[1]));
	          }
			 this.houseInfo.add(tmp);
			 tmp=new ArrayList();
			 
			 tmp.add("房仲：");
	         tmp.add(this.houseAgency);
	         this.houseInfo.add(tmp);
	         tmp=new ArrayList();
			 
		}
		return houseInfo;	
		
	}
	private void findHouseLonLat()throws Exception
	{
		
		
		if(this.houseAgency.endsWith("中信房屋"))
		{
			org.jsoup.select.Elements eles = doc.getElementsByTag("script");
			//Element eles = doc.script();
			String tmp = null;
			for (Element ele : eles) 
			{   
				// 檢查是否有detailInfoObject字串
			   	String script = ele.toString();
			    if (script.indexOf("setGoogleMap") > -1)
			    {
	            // 只取得script的內容
			       script = ele.childNode(0).toString();
			       tmp = script;
			     //System.out.print(script);
			       break;
			   }
			}
			System.out.print("tmp = "+tmp);
			if(!(tmp == null||tmp.length() == 0))
			{
			String [] gg=tmp.split("'");
			this.LonLat[0]=Double.valueOf(gg[1]);
			this.LonLat[1]=Double.valueOf(gg[3]);
			}//	System.out.print("\ngg[1] = "+gg[1]);
		//	System.out.print("\ngg[3] = "+gg[3]);
		//	System.out.print("\n");
			
			
		}
		
		
		else if(this.houseAgency.endsWith("東森房屋"))
		{
			Element table = doc.select("div").get(67);
			Iterator<Element> li = table.select("li").iterator();
			while(li.hasNext())
			{
				String tmp=li.next().absUrl("onclick");
				String [] gg=tmp.split(",");
				
				//4 is LonLat location
				if(gg[1].endsWith("'4'"))
				{	
					for(int i=2;i<=3;i++)
						gg[i]=gg[i].replace("'", "0");
					gg[3]=gg[3].replace(")", "");
					this.LonLat[0]=Double.valueOf(gg[2]);
					this.LonLat[1]=Double.valueOf(gg[3]);
				}
			
			}
			
			
		}
		
		else if(this.houseAgency.endsWith("台灣房屋"))
		{
			Element table_2 = doc.select("div").get(74);
			Iterator<Element> onc = table_2.select("a").iterator();
			while(onc.hasNext())
			{
				String tmp=onc.next().absUrl("onclick");
				String [] gg=tmp.split(",");
				if(gg.length!=1)
				{	
					for(int i=3;i<=4;i++)
						gg[i]=gg[i].replace("'", "0");
					this.LonLat[0]=Double.valueOf(gg[4]);
					this.LonLat[1]=Double.valueOf(gg[3]);

				}
			}
		}

		
	}
	
}
