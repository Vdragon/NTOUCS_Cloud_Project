package admin;

import Execute.Command;

public class AdminSystemHelper_Solr extends Thread
{
	private Command system;
	private boolean systemIsRun;
	public AdminSystemHelper_Solr()
	{
		system=new Command();
		this.systemIsRun=false;
	}
	public void run()
	{
		if(this.systemIsRun)
		{
			system.start_Solr();
		}
		else
		{
			system.start_Solr();
		}
	}
	public void set_solr_run(boolean tmp)
	{
		this.systemIsRun=tmp;
	}

}
