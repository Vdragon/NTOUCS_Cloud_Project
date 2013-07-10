package test;

import java.util.ArrayList;

import ReadWrite.HouseTxt;
public class HouseTxtTest
{
	public static void main(String[] args) 
	  {
		 
		ArrayList data=new ArrayList();
		ArrayList tem=new ArrayList();
		data.add(tem);
		tem.add(0, "qwe");
		tem.add(1,"asd");
		ArrayList tem2=new ArrayList();
		tem2.add(0, "22qwe");
		tem2.add(1,"22asd");
		data.add(tem2);
		
		HouseTxt qwe= new HouseTxt();
		qwe.ToTxt(data);
		
	  }
}