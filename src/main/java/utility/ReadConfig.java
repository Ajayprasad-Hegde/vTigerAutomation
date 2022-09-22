package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig 
{
	Properties p;
//------------------------------Constructor---------------------------------------------//	
public ReadConfig() throws IOException
{
	FileInputStream fip = new FileInputStream(new File("C:\\Ajay\\Java_Folder\\Java_Eclips\\"
			+ "vTiger\\src\\main\\java\\config\\vTiger_config.properties"));
	
	p = new Properties();
	p.load(fip);	
}

//----------------------------------URLs------------------------------------------//

public String getUrl()
{
	return p.getProperty("url");
	
}

//-----------------------User name and Passwords---------------------------------//
public String getAdminUserName()
{
	return p.getProperty("userName");
}
public String getAdminPassword()
{
	return p.getProperty("password");
}


//-----------------------------Page Titles------------------------------------//
public String getHomePageTitle()
{
	return p.getProperty("homePageTitle");
}
}