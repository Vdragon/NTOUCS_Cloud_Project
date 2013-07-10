package admin;

import javax.swing.JButton;
import javax.swing.JLabel;

import timer.TimerLabel;

import Execute.Command;

public class AdminSystemHelper_Nutch extends Thread
{
	private Command system;
	private boolean systemisRun;
	private JButton systemSwitchButton;
	private JLabel systemStat;
	private TimerLabel systemTimer;
	private int depth, width;
	public AdminSystemHelper_Nutch(JButton systemSwitchButton,JLabel systemStat)
	{
		system=new Command();
		systemisRun=false;
		this.systemSwitchButton=systemSwitchButton;
		this.systemStat=systemStat;
	}
	
	public void run()
	{
		
		if(this.systemisRun==false)
		{
			systemStat.setText("啟動中...");
			systemSwitchButton.setVisible(false);
			system.Start_Nutch();
			systemStat.setText("已開啟");
			systemSwitchButton.setVisible(true);
		}
		else
		{
			systemStat.setText("關閉中...");
			systemSwitchButton.setVisible(false);
			system.Stop_Nutch();
			systemStat.setText("已關閉");
			systemSwitchButton.setVisible(true);
		}
		
		
		
	}
	public void setSystemIsRun(boolean tmp)
	{
		this.systemisRun=tmp;
	}
	

}
