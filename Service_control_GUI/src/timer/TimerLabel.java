package timer;
import googleNeed.PrecreateXML;
import googleNeed.createXML;
import html.HTMLGetter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.Timer;

import mysql.PHPMysql;
import solr.Solr;

import Execute.AutoExec;
import Execute.Command;

public class TimerLabel extends JLabel {

	private static final long serialVersionUID = 3483664317145400178L;
	private long millis;
	private long Storemillis;
	private Timer timer;
	private int nutch_width,nutch_depth;
	private Command crawl= new Command();
	private JLabel house_count;
	public TimerLabel(long millis,JLabel house_count) 
	{
		super(String.valueOf(millis));
		this.millis = millis;
		this.Storemillis=millis;
		setAlignmentX(Component.CENTER_ALIGNMENT);
		this.nutch_depth=3;
		this.nutch_width=100;
		this.house_count=house_count;
	}

	@Override
	public void setText(String text) 
	{
		
		if(text.equals("更新中..."))
		{
			super.setText("更新中...");
		}
		else if (text != null && text.length() > 0)
		{
			long millis = Long.parseLong(text);
			if(millis==0)
			{
				super.setText("更新中...");
			}
			else if (millis > -1000) 
			{
				super.setText(String.format(
						"%02d:%02d:%02d",
						TimeUnit.MILLISECONDS.toHours(millis),
						TimeUnit.MILLISECONDS.toMinutes(millis)
								- TimeUnit.HOURS
										.toMinutes(TimeUnit.MILLISECONDS
												.toHours(millis)),
						TimeUnit.MILLISECONDS.toSeconds(millis)
								- TimeUnit.MINUTES
										.toSeconds(TimeUnit.MILLISECONDS
												.toMinutes(millis))));
			} 
			else 
			{
				//crawl.CreateNewDir_Solr();
				
				this.resetTimer();
				crawl.delete_HDFS("/user/hadooper/urls");
				
				crawl.Upload_HDFS("/opt/nutch/urls", "urls");
				
				crawl.crawl_Nutch(this.nutch_depth, this.nutch_width);
				
				crawl.delete_HDFS("/HouseTmp");
				
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
				
				crawl.reNameDir_Nutch("HouseTmp","House");
				
				createXML xml = new createXML();
				xml.changeFile();
		
				
				
				this.startTimer();
				
			}
		}
		
	}

	public void setTimer()
	{
		if (timer == null)
		{
			timer = new Timer(1000, new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{	
					decrement();
				}
			});
		}
		
	}
	public void set_Parameter_nutch(int depth,int width)
	{
		this.nutch_depth=depth;
		this.nutch_width=width;
		System.out.println("timer: depth: "+this.nutch_depth+"width:"+this.nutch_width);
	}
	
	
	public void startTimer()
	{
		this.setTimer();
		timer.start();
	}

	public void stopTimer()
	{
		timer.stop();
	}
	public void resetTimer()
	{
		super.setText("更新中...");
		timer.stop();
		timer=null;
		this.millis=this.Storemillis;
		//this.setTimer();
	 
	}
	public void decrement() {
		millis -= 1000;
		setText(String.valueOf(millis));
	}
	public void set_Timer(long tmp)
	{
		this.millis=tmp*60000;
		this.Storemillis=this.millis;
	}

}