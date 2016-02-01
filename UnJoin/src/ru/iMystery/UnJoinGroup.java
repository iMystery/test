package ru.iMystery;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class UnJoinGroup implements Runnable{
	
	String token;
	
	public UnJoinGroup(String token)
	{
		this.token = token;
		run();
	}
	
	public void run()
	{
		//parsing vk group and exit
		
		try {
			URL parseGroup = new URL("https://api.vk.com/method/groups.get"
												+ "?user_id=260763350"
												+ "&count=1000"
												+ "&access_token=6288e285f89c9c580a8720c952ad057f2e74494dc06fb82a9e364969df919ad39458b204ac36b69706ef8");
			URLConnection connect = parseGroup.openConnection();
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(readToString(connect.getInputStream()));
			JSONArray array = (JSONArray) object.get("response");
			System.out.print(array.size());
			if( array.size() > 1 )
			{
				for(int i = 0; i < array.size(); i++)
				{
					exitGroup(array.get(i));
					Thread.sleep(2000);//sleep 2s anti Flood post api xD
				}
			}
			
		} catch (IOException | ParseException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readToString(InputStream in) throws IOException, ParseException
	{
		StringBuffer b = new StringBuffer();
		int line;
		
		InputStreamReader reader = new InputStreamReader(in);
		while((line = reader.read()) != -1)
		{
			b.append((char)line);
		}
		
		return (String) b.toString();
	}
	
	public void exitGroup(Object id)
	{
		try 
		{
			URL url = new URL("https://api.vk.com/method/groups.leave"
								+ "?group_id=" + id
								+ "&access_token=6288e285f89c9c580a8720c952ad057f2e74494dc06fb82a9e364969df919ad39458b204ac36b69706ef8");
			URLConnection connect = url.openConnection();
			
			//if()
			System.out.print(readToString(connect.getInputStream()));
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
