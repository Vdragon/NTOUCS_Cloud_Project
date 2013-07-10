package test;

import java.util.ArrayList;

import solr.Solr;

public class secondTop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solr solrData=new Solr();
		solrData.defaultRun();
		ArrayList tmp =solrData.getCheckedURL();
		
		for(int i=0;i<tmp.size();i++)
		{
			
		}
		
		
	}

}
