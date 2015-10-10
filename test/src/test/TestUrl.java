package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.beanutils.converters.URLConverter;
import org.apache.lucene.document.Document;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestUrl {
	public static void main(String[] args) {
		URL url = null;
		HttpURLConnection urlCon = null;
		StringBuffer sb = new StringBuffer();
		String urlstr = "https://item.taobao.com/item.htm?spm=a230r.1.14.8.thg1Sq&id=15514828246&ns=1&abbucket=13#detail";
		String info = null;
		
		try {
			url = new URL(urlstr);

			urlCon = (HttpURLConnection) url.openConnection();
			// urlCon.setReadTimeout(60000);

			urlCon.setRequestProperty("User-Agent",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			urlCon.setRequestProperty(
			"Accept",
			"image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
			urlCon.setRequestProperty("Accept-Language", "zh-cn");
			urlCon.setRequestProperty("UA-CPU", "x86");
			urlCon.setRequestProperty("Accept-Encoding", "utf-8");// Ϊʲôû��deflate��
			urlCon.setRequestProperty("Content-type", "text/html");
			urlCon.setRequestProperty("Connection", "close"); // keep-Alive����ʲô���أ��㲻���ڷ�����վ�������ڲɼ����ٺ١�������˵�ѹ����Ҳ�Ǽ����Լ���
			urlCon.setUseCaches(false);// ��Ҫ��cache������Ҳû��ʲô�ã���Ϊ���ǲ��ᾭ����һ������Ƶ�����ʡ�����Գ���
			urlCon.setConnectTimeout(6 * 1000);
			urlCon.setReadTimeout(6 * 1000);
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.connect();
			
			InputStreamReader in = new InputStreamReader(urlCon.getInputStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(in);

			String readLine = null;
			while ((readLine = bufferedReader.readLine()) != null && info == null) {
				if(readLine.trim().startsWith("g_page_config")){
					info = readLine.split("=")[1];
				}
			}
			
			info = info.substring(0,info.length() -1);
			
			JSONObject j = JSONObject.fromObject(info);
			System.out.println(j.toString());
			in.close();
			urlCon.disconnect();
			
			
		} catch (MalformedURLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
}
