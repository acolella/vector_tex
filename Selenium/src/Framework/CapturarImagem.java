package Framework;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CapturarImagem {
	
	public void Capturar(WebDriver ldriver){
		   
		  File src= ((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
		  	try {
		  			FileUtils.copyFile(src, new File("C:/EvidÍncia/"+System.currentTimeMillis()+".png"));
		        }
		   	catch (IOException e)
		   		{
		   		System.out.println(e.getMessage());
		 	    }
	  		}
	}





