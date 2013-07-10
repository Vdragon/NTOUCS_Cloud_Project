package solr;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;  
import org.apache.solr.common.SolrDocumentList;  
import org.apache.solr.common.SolrDocument;  
import org.apache.solr.common.SolrInputDocument;

import org.apache.solr.client.solrj.SolrQuery;  
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;  
	
public class Solr 
{
	private ArrayList checked_URL;
	private ArrayList total_URL;
	private CommonsHttpSolrServer server;
	private SolrQuery query;
	private QueryResponse qr;
	private SolrDocumentList solrResult;
	
	public Solr()
	{
		checked_URL=new ArrayList();
		total_URL=new ArrayList();
		server=null;
		query=null;
		qr=null;
		solrResult=null;
	}
	public ArrayList getCheckedURL()
	{
		return this.checked_URL;
	}
	
	/**連接server端的solr
	 * */
	public void connectHttpSolrServer(String serverLocation)
	{
		 try{  
	            server = new CommonsHttpSolrServer(serverLocation);  
	      }catch(Exception e){  
	    	  // TODO Auto-generated catch block
            e.printStackTrace();  
        }  
	}
	
	/**設定solr查詢範圍(起始位置,最多位置,查詢關鍵字) 
	 * */
	public void solrQuerySetting(Integer i,Integer j,String keyWord)
	{
		query = new SolrQuery(keyWord);
		
		query.setStart(i);
		query.setRows(j);
	}
	
	/**設定solr server給的物件 及 創造solr文件
	 * */
	public void solrResponseSetting()
	{
		 try {
			qr = server.query(query);
		} 
		 catch (SolrServerException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        solrResult = qr.getResults();
        getSolrSearchRespose();
        deleteRepeatTitle();
        
	}
	public void print()
	{
		for(int i=0;i<checked_URL.size();i++)
		{
			ArrayList tmp=(ArrayList) checked_URL.get(i);
			System.out.println("第"+(i+1)+"個:\n網址名稱:"+tmp.get(1)+"\nURL: "+tmp.get(0));
			
		}
		
		
	}
	
	public void deleteRepeatTitle()
	{
		if(!total_URL.isEmpty())
		{
			if(checked_URL.isEmpty())
				checked_URL.add(total_URL.get(0));
			for(int i=0;i<total_URL.size();i++)
			{
				if(findSame(i)==false)
				{
					int twhg,etwarm,cthouse1,cthouse2,cthouse3,cthouse4,spec_twhg;
					twhg = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("object.php?obj=");
					etwarm = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("object_A");
					cthouse1 = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("HouseDetail.aspx?houseId=");
					cthouse2 = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("housedetail.aspx?houseId=");
					cthouse3 = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("housedetail.aspx?houseid=");
					cthouse4 = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("HouseDetail.aspx?houseid=");
					spec_twhg = ((ArrayList)total_URL.get(i)).get(0).toString().indexOf("&cit=");
					//台灣
					//if(((ArrayList)total_URL.get(i)).get(0).toString().indexOf("object.php?obj=")>0)
					//東森
					//if(((ArrayList)total_URL.get(i)).get(0).toString().indexOf("object_")>0)
					//中信
					//if(((ArrayList)total_URL.get(i)).get(0).toString().indexOf("houseId")>0)
					if(spec_twhg > 0)
						continue;
					if((twhg > 0) || (etwarm > 0) || (cthouse1 > 0)|| (cthouse2 > 0)|| (cthouse3 > 0)|| (cthouse4 > 0))
						checked_URL.add(total_URL.get(i));
				}
			}
			this.checked_URL.remove(0);
		}
	}
	
	private boolean findSame(int i)
	{
		for(int j=0;j<checked_URL.size();j++)
		{
			if(compareWord((ArrayList)total_URL.get(i),(ArrayList)checked_URL.get(j)))
			{
				return true;
			}
		}
		return false;
	}
	
	/**當字母相同 return true
	 * */
	private boolean compareWord(ArrayList a,ArrayList b)
	{
		if(((String) a.get(1)).compareToIgnoreCase((String) b.get(1))==0)
			return true;
		return false;
	}
	
	/**拿到此次查詢最多的result
	 * */
	public int getSolrNumFound()
	{
		return (int)solrResult.getNumFound();
	}
	
	/**拿到此次查詢的網頁參數
	 * */
	private void getSolrSearchRespose()
	{
		for(int i=0;i<this.getSolrNumFound();i++)
		{
			ArrayList tmp=new ArrayList();
			if(solrResult.get(i).get("id")!=null&&solrResult.get(i).get("title")!=null)
			{	
				tmp.add((String) solrResult.get(i).get("id"));
				tmp.add((String) solrResult.get(i).get("title"));
				total_URL.add(tmp);
			}
			
		}
	}
	
	public void defaultRun()
	{
		
		this.connectHttpSolrServer("http://127.0.0.1:8080/solr/");
				
		this.solrQuerySetting(new Integer(0), new Integer(10000000), "*:*");
				
		this.solrResponseSetting();
		
		this.getSolrNumFound();
		this.print();
	}
	public void defaultRun(String findobject)
	{
		
		this.connectHttpSolrServer("http://127.0.0.1:8080/solr/");
				
		this.solrQuerySetting(new Integer(0), new Integer(1000000), findobject);
				
		this.solrResponseSetting();
		
		this.getSolrNumFound();
		this.print();
	}
	public static void main(String[] args) {
		
	}


    
}
