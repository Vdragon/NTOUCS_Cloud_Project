package imgDownloader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownload 
{
	private URL myimageURL; 
	private String outputLocation;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	
		
		public ImageDownload()
		{ 
			myimageURL=null;
			in=null;
			out=null;	
			
		}
		private void setImageURL(String imgURL)
		{

			try {
				myimageURL = new URL(imgURL);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
			
				System.out.print("URL unworking!!\n");
			}
		
		}
		public void saveImage(String imageURL,String savepath) 
		{
			boolean getImage=false;
			while(getImage==false)
			{
				this.setImageURL(imageURL);
				try 
				{
					this.letSDownLoad(savepath);
					getImage=true;
				} catch (Exception e)
				{
					getImage=false;
					imageURL="http://www.etwarm.com.tw/images/pic_error.jpg";
					System.out.print("image can not found!!\n");
				}
			}
		}
		
		
		private void letSDownLoad(String path) throws Exception
		{
			URLConnection urlConnection = null;
			
			try {
				urlConnection = myimageURL.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("URL unworking!!\n");
			}
			
			in = new BufferedInputStream(urlConnection.getInputStream());
	       
	
			out = new BufferedOutputStream(new FileOutputStream(path));
		

	       // until the end of data, keep saving into file.
	       int i;
	       while ((i = in.read()) != -1)
	           out.write(i);
	       
	       out.flush();

	       // closing all the shits
	       out.close();
	       in.close();
				
		}
	
}
