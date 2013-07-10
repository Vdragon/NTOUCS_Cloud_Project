package admin;

import javax.swing.JButton;
import javax.swing.JLabel;

import timer.TimerLabel;

import Execute.Command;

public class AdminSystem 
{
	
	private JLabel systemStat;
	private JButton systemOnOffButton;
	private JButton systemUpdateButton;
	private TimerLabel timeCounter; 
	private AdminSystemHelper_Nutch systemHelper_nutch;
	private AdminSystemHelper_Timer systemHelper_timer;
	private int depth_Nutch;
	private int width_Nutch;
	private JLabel urlLost;
	public AdminSystem(JButton systemOnOffButton,JLabel systemStat,
			JButton systemUpdateButton,TimerLabel timeCounter,
			JLabel urlLost)
	{
		this.systemOnOffButton=systemOnOffButton;
		this.systemUpdateButton=systemUpdateButton;
		this.systemStat=systemStat;
		this.timeCounter=timeCounter;
		this.depth_Nutch=3;
		this.width_Nutch=100;
		this.urlLost= urlLost; 
		//開始計時
		this.timeCounter.startTimer();
		
	}
	public void set_Parameter_Nutch(int depth,int width)
	{
		this.width_Nutch=width;
		this.depth_Nutch=depth;
		this.timeCounter.set_Parameter_nutch(this.depth_Nutch, this.width_Nutch);
	}
	public void start_Nutch()
	{
		systemHelper_nutch=new AdminSystemHelper_Nutch(systemOnOffButton, systemStat);
		
		systemHelper_nutch.setSystemIsRun(false);
		
		systemHelper_nutch.start();		
	}
	public void stop_Nutch()
	{
		systemHelper_nutch=new AdminSystemHelper_Nutch(systemOnOffButton,systemStat);

		systemHelper_nutch.setSystemIsRun(true);
		systemHelper_nutch.start();
		
	}
	public void Update_DataSystem()
	{
		System.out.print("ad depth:"+this.depth_Nutch+"width+"+this.width_Nutch);
		systemHelper_timer=new AdminSystemHelper_Timer(systemUpdateButton,timeCounter,width_Nutch,depth_Nutch,urlLost);
		systemHelper_timer.start();
		
	}
	
	
	public void set_Timer(Long tmp)
	{
		systemHelper_timer=new AdminSystemHelper_Timer(systemUpdateButton,timeCounter,width_Nutch,depth_Nutch,urlLost);
		systemHelper_timer.set_Timer(tmp);
	}
	public void stop_Timer()
	{
		systemHelper_timer=new AdminSystemHelper_Timer(systemUpdateButton,timeCounter,width_Nutch,depth_Nutch,urlLost);
		systemHelper_timer.stop_Timer();
	}
	public void start_Timer()
	{
		systemHelper_timer=new AdminSystemHelper_Timer(systemUpdateButton,timeCounter,width_Nutch,depth_Nutch,urlLost);
		systemHelper_timer.start_Timer();
	}
	

}
