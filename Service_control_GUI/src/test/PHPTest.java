package test;

import java.util.ArrayList;

import html.HTMLPaser;
import mysql.PHPMysql;

public class PHPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PHPMysql t=new PHPMysql();
		t.change_TableName("House", "Houseold");
		t.change_TableName("HouseTmp", "House");
		t.change_TableName("Houseold", "HouseTmp");
		//t.delete_TableData();
	
	}

}
