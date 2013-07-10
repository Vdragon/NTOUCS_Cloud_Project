package admin;

import java.io.IOException;

import googleNeed.PrecreateXML;
import html.HTMLGetter;

import javax.swing.JButton;
import javax.swing.JLabel;

import mysql.PHPMysql;

import Execute.Command;

import solr.Solr;
import timer.TimerLabel;

public class AdminSystemHelper_Timer extends Thread{
	
	private Command system;
	private JButton systemSwitchButton;
	private TimerLabel systemTimer;
	private long milles;
	private int width_Nutch,depth_Nutch;
	private JLabel house_count;
	public AdminSystemHelper_Timer(JButton systemSwitchButton,TimerLabel systemStat,int width,int depth,JLabel house_count)
	{
		this.system=new Command();
		this.systemSwitchButton=systemSwitchButton;
		this.systemTimer=systemStat;
		this.width_Nutch=width;
		this.depth_Nutch=depth;
		this.house_count=house_count;
	}
	//永續機
	public void run()
	{
		systemTimer.resetTimer();
		systemSwitchButton.setVisible(false);
	
		system.delete_HDFS("/user/hadooper/urls");
		
		system.Upload_HDFS("/opt/nutch/urls", "urls");
		
		system.crawl_Nutch(this.depth_Nutch, this.width_Nutch);
		
		system.delete_HDFS("/HouseTmp");
		
		PHPMysql t=new PHPMysql();
		t.delete_TableData();
		
		PrecreateXML pre = new PrecreateXML();
		try {
			pre.CreateFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Solr tmp=new Solr();
		tmp.defaultRun();
		HTMLGetter test=new HTMLGetter(tmp.getCheckedURL()); 
		test.totalHtmlPase();
		this.house_count.setText("台灣房屋："+test.get_urlLost()[0]+"\n東森房屋："+
				test.get_urlLost()[1]+"\n中信房屋："+test.get_urlLost()[2]);	
		test.create_URLS();
		
		t.change_TableName("House", "Houseold");
		t.change_TableName("HouseTmp", "House");
		t.change_TableName("Houseold", "HouseTmp");
		
		system.reNameDir_Nutch("HouseTmp","House");
		

		systemTimer.startTimer();
		systemSwitchButton.setVisible(true);
	}
	public void set_Timer(Long time)
	{
		this.milles=time;
		systemTimer.set_Timer(this.milles);
	}
	public void stop_Timer()
	{
		systemTimer.stopTimer();
	}
	public void start_Timer()
	{
		systemTimer.startTimer();
	}

}
