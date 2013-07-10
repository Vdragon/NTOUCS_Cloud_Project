package Execute;

import timer.TimerLabel;

public class AutoExec extends Thread
{
	private Command ex;
	private TimerLabel timer;
	public AutoExec(TimerLabel tmp)
	{
		ex=new Command();
		this.timer=tmp;
	
	}
	public void run()
	{
		timer.setText("更新中...");
		ex.scp("/opt/nutch/conf/regex-urlfilter.txt");
	}

}
