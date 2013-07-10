package test;

import ReadWrite.RW;
public class RWTest{
	public static void main( String args[] ) throws Exception
	{
		
		String oldstr[] = {"+^http://([a-z0-9A-Z]*\\.)*www.etwarm.com.tw/[\\s\\S]*/",
		"+^http://www.century21.com.tw/index.aspx?fn=item&no=[0-9]*/"};
		String oldstr2[] = {"#+^http://www.century21.com.tw/index.aspx?fn=item&no=[0-9]*/"};
		RW wf = new RW();
		String path = "/opt/nutch/conf/regex-urlfilter.txt";
		wf.WriteFile(oldstr2, path);
		
	}
}