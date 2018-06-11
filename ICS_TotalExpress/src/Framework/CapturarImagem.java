package Framework;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



 
public class CapturarImagem {
	
	public static void CapturarTela(WebDriver driver, String NomeEVD) {
		
		try
		{
			 TakesScreenshot ts=(TakesScreenshot)driver;
			    
			 File source=ts.getScreenshotAs(OutputType.FILE);
			 
			 FileUtils.copyFile(source, new File("C://OdontoPrev/Evidencia/"+NomeEVD+".png"));
		}
		
		catch (Exception e)
		{
			
			System.out.println("Exception while take "+e.getMessage());
		}

	
	 
	 
	}
}