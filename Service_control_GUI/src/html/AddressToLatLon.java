package html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class AddressToLatLon {

	private String[] info;
	public static String [] addressToCoordination(String str){
		//其中str為地址
        byte[] buf = new byte[100];
        String [] gg= new String[2];
        URL url;
        try {
            String address = URLEncoder.encode(str, "UTF-8");
            String url0 = "http://maps.google.cn/maps/geo?q="
                    + address
                    + "&output=csv&oe=utf8\\&sensor=true_or_false&key=ABQIAAAAqaGKijD7euSpqDeVsNA85xTT3OL8VXjPlPTFW7n7OgOFwXoSnxT7IP1pHznaiGwWMvsEq_SkxvESLw";

            String addr = url0;
            url = new URL(addr);
            InputStream ins =  url.openStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(
                    ins));
            String content = bReader.readLine();
            if (content != null)
            {
                String[] info = content.split(",");
                String [] tmp=new String[2];
                tmp[0]=info[2];
                tmp[1]=info[3];
                return tmp;
            }
            return gg;
        } catch (Exception e)
        {
        	gg[0]="24";
        	gg[1]="121";
        	return gg;
        }
    }
	
	public static void main(String[] args) {
		AddressToLatLon.addressToCoordination("1600 Pennsylvania Avenue Northwest, Washington, DC");
		System.out.print("fsd"+AddressToLatLon.addressToCoordination("桃園縣桃園市金門二街")[0]);
	}

}
