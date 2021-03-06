package GUI;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Execute.Command;
import admin.AdminSystem;

import timer.TimerLabel;
public class MasterFrame extends JFrame{

	
	

private	 String store_depth="",store_width="",store_npath="",store_solar="";

private JPanel contentPane;
private FlowLayout layout;

private final String[] labelStr={"Nutch狀態","Solr狀態","距離下次更新剩",};
private final String[] labelSwtichStr={"開啟","關閉"};
private JTabbedPane tabbedPane;

//save
File saveFile=new File("config.txt");
private int save_width = 0;
private String npath="";
private String nsolar="";
	


//nutch
private final String[] Stat_Nutch_Str={"Nutch狀態","抓取深廣度"};
private JPanel contentPane_nutch;
private JButton nutchSwitch;
private JLabel nutchStat;
private JButton nutchParameterSwitch;

private JComboBox depth,width;
private final String [] depthParameter={"2","3","4","5"};
private final String [] widthParameter={"100","1000","10000"};
private int nutch_width = 100;
private int nutch_depth = 5;

//end nutch

//solr
private JPanel contentPane_solr;
private JButton solrSwitch;
private JLabel SolrStat;

//end solr

//endless
private final String[] Stat_Endless_Str={"自動更新","更改自動更新時間","目前nutch狀態"};
private JPanel contentPane_endless;
private JButton counterSwitch;
private TimerLabel counterStat;
private JButton counterUpdate;
private final String [] set_Timer_Str_hour={"0","1","3","6","12","24","48"};
private final String [] set_Timer_Str_min={"0","15","30","45"};
private JComboBox set_Timer_hour,set_Timer_min;
private JPanel timer_Counter_panel;
private JPanel timer_Changer_panel;
private JButton timer_Chager_Button;
private long get_Changer_time_hour=1;
private long get_Changer_time_min=0;
private long get_Changer_time;
private JPanel nutch_parameter_stat_panel;
private JLabel nutch_width_stat;
private JLabel nutch_depth_stat;
/*show how many hosue sold*/
private JLabel  sold_out_count;

//end endless

//system
private JPanel contentPane_system;

//path
private JPanel contentPane_path;
private JTextField  pathField;
private JButton pathSwitchOn;
private JButton pathSwitchClose;


//solar
private JPanel contentPane_Solar;
private JTextField  solarField;
private JButton solarSwitchOn;
private JButton solarSwitchClose;

//end system
private AdminSystem system;


public 	MasterFrame() 
{

	try {
		String store;
		int i=0;
		String num="";
	     BufferedReader br = new BufferedReader(new FileReader("config.txt"));
	     String line;
	     line = br.readLine();
	     while(line != null)
	     {
	    	 if(line.length()>12){
	    	 store = line.substring(0,11);
	    	 if(store.compareTo("nutch_depth")==0 ) {
	    		 store_depth=line;
	    		 num = line.substring(12);
		    	 nutch_depth=Integer.parseInt(num);
		     }
	    	 else if(store.compareTo("nutch_width")==0 ) {
	    		 store_width=line;
	    		 num = line.substring(12);
		    	 nutch_width=Integer.parseInt(num);		    	 
		    	 for(i=1;i<(nutch_width/100);i=i*10){
		    		 save_width++;		    		 
		    	 }
	    	 }
	    	 else if(store.compareTo("nutch_npath")==0) {
	    		 store_npath=line;
	    		 npath = line.substring(12);
	    	 }
	    	 else if(store.compareTo("solar_npath")==0) {
	    		 store_solar=line;
	    		 nsolar = line.substring(12);
	    	 }
	     }
	    	 line = br.readLine();
	     
	     }
	     
	     System.out.println(store_depth);
	     System.out.println(store_width);
	     System.out.println(store_npath);
	     System.out.println(store_solar);
	     
	     
	     /*
	     line = br.readLine();
	     store = line.substring(0,11);
	    	 if(store.compareTo("nutch_depth")==0) {
	    		 a=line;
	    		 num = line.substring(12);
		    	 nutch_depth=Integer.parseInt(num);
		     }
	    	 else if(store.compareTo("nutch_width")==0) {
	    		 b=line;
	    		 num = line.substring(12);
		    	 nutch_width=Integer.parseInt(num);		    	 
		    	 for(i=1;i<(nutch_width/100);i=i*10){
		    		 save_width++;		    		 
		    	 }
	    	 }
	    	 else if(store.compareTo("nutch_npath")==0) {
	    		 c=line;
	    		 npath = line.substring(12);
	    	 }
	    	 else if(store.compareTo("solar_npath")==0) {
	    		 d=line;
	    		 nsolar = line.substring(12);
	    	 }
	    	 */
	    	 
	     store_depth = store_depth + "\n";
	     store_width = store_width + "\n";
	     store_npath = store_npath + "\n";
	     store_solar = store_solar + "\n";
	    	 
		    
		     br.close();
		    }
		catch (IOException e) {
			nutch_width = 100;
		     nutch_depth = 5;
		    }	
			
		setTitle("Cloud 管理介面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(400, 400, 400, 400);
		
		layout=new FlowLayout();
		this.setLayout(layout);
		
		
		//nutch
		//panel
		contentPane_nutch= new JPanel(new GridLayout(3,1));
		contentPane_nutch.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		
		nutchSwitch=new JButton("開啟");
		nutchStat=new JLabel("關閉",SwingConstants.CENTER);
		
		nutchParameterSwitch=new JButton("編輯");
		depth=new JComboBox(depthParameter);
		width=new JComboBox(widthParameter);
		depth.setSelectedIndex(nutch_depth-2);
		width.setSelectedIndex(save_width);
		
		JPanel panel_Nutch_Switch = new JPanel(new GridLayout(1,3));
		panel_Nutch_Switch.add(new JLabel("",SwingConstants.CENTER));
		panel_Nutch_Switch.add(nutchStat);
		panel_Nutch_Switch.add(nutchSwitch);
		
		TitledBorder titled;
		titled = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), this.Stat_Nutch_Str[0]);
		titled.setTitleJustification(TitledBorder.LEFT);
		titled.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		titled.setTitleColor(Color.red);
		
		panel_Nutch_Switch.setBorder(titled);
		
		this.contentPane_nutch.add(panel_Nutch_Switch);
		
		JPanel panel_Nutch_WidthDepth = new JPanel(new GridLayout(2,5));
		panel_Nutch_WidthDepth.add(new JLabel("深度",SwingConstants.CENTER));
		panel_Nutch_WidthDepth.add(new JLabel("廣度",SwingConstants.CENTER));
		panel_Nutch_WidthDepth.add(nutchParameterSwitch);
		panel_Nutch_WidthDepth.add(depth);
		depth.setVisible(false);
		
		panel_Nutch_WidthDepth.add(width);
		width.setVisible(false);
		
	
		titled = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), this.Stat_Nutch_Str[1]);
		titled.setTitleJustification(TitledBorder.LEFT);
		titled.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		titled.setTitleColor(Color.red);
		
		panel_Nutch_WidthDepth.setBorder(titled);
		
		this.contentPane_nutch.add(panel_Nutch_WidthDepth);
		
		//listener
		depth.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				nutch_depth=Integer.parseInt(jcmbType.getSelectedItem().toString());
			}
		});
		width.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				nutch_width=Integer.parseInt(jcmbType.getSelectedItem().toString());
			}
		});
		nutchParameterSwitch.addActionListener(new ActionListener()
	    {
				// handle button event
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent event) {
					if(nutchParameterSwitch.getText().toString().equals("編輯"))
					{	
						nutchParameterSwitch.setText("完成");
						
						depth.setVisible(true);
						width.setVisible(true);
						
					}else{
						system.set_Parameter_Nutch(nutch_depth, nutch_width);
						nutch_depth_stat.setText(String.valueOf(nutch_depth));
						nutch_width_stat.setText(String.valueOf(nutch_width));
						nutchParameterSwitch.setText("編輯");
						depth.setVisible(false);
						width.setVisible(false);
						
						store_depth = "nutch_depth:"+Integer.toString(nutch_depth) + "\n";
						store_width = "nutch_width:"+Integer.toString(nutch_width) + "\n";
						
						try
					    {
					      FileWriter fwriter=new FileWriter(saveFile);
					      fwriter.write(store_depth);
					      fwriter.write(store_width);
					      fwriter.write(store_npath);
					      fwriter.write(store_solar);
					      fwriter.close();
					    }
					    catch(Exception e)
					    {
					      e.printStackTrace();
					    }
						
					}
				}
			}

	    );	
		nutchSwitch.addActionListener(new ActionListener()
	    {
				// handle button event
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent event) {
					if(nutchSwitch.getText().toString().equals("開啟"))
					{	
						nutchSwitch.setText("關閉");
						system.start_Nutch();
						Command solr=new Command();
						solr.start_Solr();
						
						
					}else{
						nutchSwitch.setText("開啟");
						system.stop_Nutch();
						Command solr=new Command();
						solr.stop_Solr();
					}
				}
			}

	    );
		// end nutch
		
		//system panel
				contentPane_system= new JPanel(new GridLayout(3,1));
				
				contentPane_system.setVisible(false);
				sold_out_count=new JLabel();
				titled = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), " 在吾等雲端已售出之房屋計數");
				titled.setTitleJustification(TitledBorder.LEFT);
				titled.setTitlePosition(TitledBorder.DEFAULT_POSITION);
				titled.setTitleColor(Color.red);
				contentPane_system.setBorder(titled);
				contentPane_system.add(sold_out_count);
				
				
		//path panel
				contentPane_path= new JPanel(new GridLayout(3,1));
				contentPane_path.setBorder(new EmptyBorder(15, 15, 15, 15));
		
				pathField = new JTextField(npath,25);
				pathField.setEditable(false);
				pathSwitchOn=new JButton("更改");
				pathSwitchClose=new JButton("完成");
				pathSwitchClose.setEnabled(false);
								
				JPanel panel_Path = new JPanel(new GridLayout(2,1));	
				JPanel panel_Path1 = new JPanel(new GridLayout(1,2));
				panel_Path1.add(pathSwitchOn);
				panel_Path1.add(pathSwitchClose);
				panel_Path.add(panel_Path1);
				panel_Path.add(pathField);
				
				TitledBorder titled1;
				titled1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "NUTCH_PATH");
				titled1.setTitleJustification(TitledBorder.LEFT);
				titled1.setTitlePosition(TitledBorder.DEFAULT_POSITION);
				titled1.setTitleColor(Color.red);
				
				panel_Path.setBorder(titled1);
				
				this.contentPane_path.add(panel_Path);
				
				
				pathSwitchOn.addActionListener(new ActionListener()
			    {
						// handle button event						
						public void actionPerformed(ActionEvent event) {
							pathSwitchClose.setEnabled(true);
							pathSwitchOn.setEnabled(false);
							pathField.setEditable(true);
						}
					}
			    );
				
				pathSwitchClose.addActionListener(new ActionListener()
			    {
						// handle button event						
						public void actionPerformed(ActionEvent event) {
							pathSwitchOn.setEnabled(true);
							pathSwitchClose.setEnabled(false);
							pathField.setEditable(false);
							
							store_npath = "nutch_npath:"+pathField.getText() + "\n";
							
							try
						    {
						      FileWriter fwriter=new FileWriter(saveFile);
						      fwriter.write(store_depth);
						      fwriter.write(store_width);
						      fwriter.write(store_npath);
						      fwriter.write(store_solar);
						      fwriter.close();
						    }
						    catch(Exception e)
						    {
						      e.printStackTrace();
						    }
							
						}
					}
			    );
				
				
		//solar path
				solarField = new JTextField(nsolar,25);
				solarField.setEditable(false);
				solarSwitchOn=new JButton("更改");
				solarSwitchClose=new JButton("完成");
				solarSwitchClose.setEnabled(false);
								
				JPanel solar_Path = new JPanel(new GridLayout(2,1));	
				JPanel solar_Path1 = new JPanel(new GridLayout(1,2));
				solar_Path1.add(solarSwitchOn);
				solar_Path1.add(solarSwitchClose);
				solar_Path.add(solar_Path1);
				solar_Path.add(solarField);
				
				TitledBorder titled2;
				titled2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "SOLAR_PATH");
				titled2.setTitleJustification(TitledBorder.LEFT);
				titled2.setTitlePosition(TitledBorder.DEFAULT_POSITION);
				titled2.setTitleColor(Color.red);
				
				solar_Path.setBorder(titled2);
				
				this.contentPane_path.add(solar_Path);
				
				
				solarSwitchOn.addActionListener(new ActionListener()
			    {
						// handle button event						
						public void actionPerformed(ActionEvent event) {
							solarSwitchClose.setEnabled(true);
							solarSwitchOn.setEnabled(false);
							solarField.setEditable(true);
						}
					}
			    );
				
				solarSwitchClose.addActionListener(new ActionListener()
			    {
						// handle button event						
						public void actionPerformed(ActionEvent event) {
							solarSwitchOn.setEnabled(true);
							solarSwitchClose.setEnabled(false);
							solarField.setEditable(false);
							
							store_solar = "solar_npath:"+solarField.getText() + "\n";
							
							try
						    {
						      FileWriter fwriter=new FileWriter(saveFile);
						      fwriter.write(store_depth);
						      fwriter.write(store_width);
						      fwriter.write(store_npath);
						      fwriter.write(store_solar);
						      fwriter.close();
						    }
						    catch(Exception e)
						    {
						      e.printStackTrace();
						    }
							
						}
					}
			    );
				
				
				//end system panel
		//endless
		contentPane_endless= new JPanel(new GridLayout(3,1));
		contentPane_endless.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		set_Timer_hour=new JComboBox(this.set_Timer_Str_hour);
		set_Timer_min=new JComboBox(this.set_Timer_Str_min);
		
		counterSwitch=new JButton("停止");
		counterUpdate=new JButton("立即更新");
		timer_Chager_Button=new JButton("更動");
		counterUpdate.setEnabled(false);
		
		counterStat=new TimerLabel(86400000,sold_out_count);
		
		timer_Counter_panel = new JPanel(new GridLayout(1,3));
		timer_Counter_panel.add(new JLabel(labelStr[2],SwingConstants.CENTER));
		timer_Counter_panel.add(counterStat);
		timer_Counter_panel.add(counterSwitch);
		timer_Counter_panel.add(counterUpdate);
		
		titled1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), this.Stat_Endless_Str[0]);
		titled1.setTitleJustification(TitledBorder.LEFT);
		titled1.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		titled1.setTitleColor(Color.red);
		
		timer_Counter_panel.setBorder(titled1);
		contentPane_endless.add(timer_Counter_panel);
		
		timer_Changer_panel = new JPanel(new GridLayout(1,5));
		timer_Changer_panel.add(set_Timer_hour);
		timer_Changer_panel.add(new JLabel("時"));
		timer_Changer_panel.add(set_Timer_min);
		timer_Changer_panel.add(new JLabel("分"));
		timer_Changer_panel.add(timer_Chager_Button);
		timer_Changer_panel.setVisible(false);
		
		contentPane_endless.add(timer_Changer_panel);
		
		titled1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), this.Stat_Endless_Str[1]);
		titled1.setTitleJustification(TitledBorder.LEFT);
		titled1.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		titled1.setTitleColor(Color.red);
		timer_Changer_panel.setBorder(titled1);
		
		nutch_parameter_stat_panel= new JPanel(new GridLayout(1,4));
		this.nutch_depth_stat=new JLabel(Integer.toString(nutch_depth));
		this.nutch_width_stat=new JLabel(Integer.toString(nutch_width));
		
		nutch_parameter_stat_panel.add(new JLabel("深度"));
		nutch_parameter_stat_panel.add(nutch_depth_stat);
		nutch_parameter_stat_panel.add(new JLabel("廣度"));
		nutch_parameter_stat_panel.add(nutch_width_stat);
		
		contentPane_endless.add(nutch_parameter_stat_panel);
		
		titled1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), this.Stat_Endless_Str[2]);
		titled1.setTitleJustification(TitledBorder.LEFT);
		titled1.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		titled1.setTitleColor(Color.red);
		nutch_parameter_stat_panel.setBorder(titled1);
		
		//listener
		
		timer_Chager_Button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				get_Changer_time=get_Changer_time_hour*60+get_Changer_time_min;
				system.set_Timer(get_Changer_time);
				timer_Changer_panel.setVisible(false);
				counterSwitch.setText("停止");
				system.start_Timer();
			}
		});
		
		
		set_Timer_hour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				get_Changer_time_hour=Long.parseLong(jcmbType.getSelectedItem().toString());	
			}
		});
		set_Timer_min.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				get_Changer_time_min=Long.parseLong(jcmbType.getSelectedItem().toString());	
			}
		});
		counterSwitch.addActionListener(new ActionListener()
	    {
				// handle button event
				public void actionPerformed(ActionEvent event) {
						if(counterSwitch.getText().equals("停止"))
						{
							counterSwitch.setText("繼續");
							counterUpdate.setEnabled(true);
							system.stop_Timer();
							timer_Changer_panel.setVisible(true);
						}
						else
						{
							counterSwitch.setText("停止");
							counterUpdate.setEnabled(false);
							system.start_Timer();
							timer_Changer_panel.setVisible(false);
							//system.systemUpdate();
						}
						
					
						
				}
			}

	    );
		counterUpdate.addActionListener(new ActionListener()
	    {
				// handle button event
				public void actionPerformed(ActionEvent event) {
						
							system.Update_DataSystem();
							counterUpdate.setEnabled(false);
							timer_Changer_panel.setVisible(false);
				}
			}

	    );
		//solr panel
		contentPane_solr= new JPanel(new GridLayout(3,1));
		contentPane_solr.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane_solr.setVisible(false);
		
		
		
		
		
		
		
		//end solr panel
		
		
		//total panel
		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		tabbedPane.addTab("Endless", null, this.contentPane_endless, null);
		tabbedPane.addTab("Nutch", null, this.contentPane_nutch, null);
		//tabbedPane.addTab("Solr", null, this.contentPane_solr, null);
		tabbedPane.addTab("System", null, this.contentPane_system, null);
		tabbedPane.addTab("Path", null, this.contentPane_path, null);
		tabbedPane.setSelectedIndex(0);
		this.setContentPane(tabbedPane);
		 //layout.layoutContainer( tabbedPane);
		
		
		
		
		
		system = new AdminSystem(nutchSwitch,nutchStat,counterSwitch,counterStat,sold_out_count);
		
		setSize(600, 400);
		// create and show the Login form
		new MyLogin(this);
	}
	
	
	// to test the whole thing
	public static void main(String[] args) {
		
		new MasterFrame();
		
		
	}

}

