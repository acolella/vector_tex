package Main;

import static org.junit.Assert.*;
import Framework.CapturarImagem;
import Framework.GravarVideo;



import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


@RunWith(JUnitParamsRunner.class)
public class login {
	
  
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  private CapturarImagem capturaImage = new CapturarImagem();
  private GravarVideo gravarVideo = new GravarVideo();


  @Before
  public void setUp() {
	  
	System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.get("http://portal.it3.odontoprev.com.br/odontoprev/home/index2.jsp");
    
  }

  @Test
  @FileParameters("MassaDados/login.csv")
  public void OdontoPrev_POC(String CD_ASSOCIADO, String SENHA, String NOME) throws Exception {
	  
	 gravarVideo.IniciarGravacao();
	
	  
	//Seleciona o frame login   
	driver.switchTo().frame(driver.findElement(By.name("login")));
	capturaImage.Capturar(driver);
	
	
	//Clicar e preencher os campos Login e Senha e selecionar o botão "OK"
	driver.findElement(By.name("login")).click();
	Thread.sleep(1000);
	
	
	driver.findElement(By.name("login")).sendKeys(CD_ASSOCIADO);
	Thread.sleep(1000);
	
	
    driver.findElement(By.name("password")).click();
    Thread.sleep(1000);
    
    driver.findElement(By.name("password")).sendKeys(SENHA);
    Thread.sleep(1000);
    
    driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td[3]/input")).click();
    
    
    //Verifica a exibição do nome do titular
    Thread.sleep(1000);
           
    //Funcionalidade - Imprimir Carteirinha
    //Clicar na botão Carteirinha
    Thread.sleep(3000);
    driver.switchTo().frame(0);
    Thread.sleep(3000);
    driver.findElement(By.linkText("Carteirinha")).click();
    Thread.sleep(3000);
    
    //Clicar no botão "Imprimir Carteirinha" Daniel Cesar Fiori
    driver.switchTo().frame(driver.findElement(By.id("idCrossframe")));
    Thread.sleep(5000);
    Actions builder = new Actions(driver);
    WebElement element = driver.findElement(By.xpath("/html/body/div/form/div[2]/table/tbody/tr[2]/td[4]/input"));
    builder.doubleClick(element).perform();
    Thread.sleep(5000);
    
    gravarVideo.FinalizarGravacao();
        
  }
  

  @After
  public void Finalizar() {
  	driver.quit();
  	String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
  
	   
   
  }
 }
}
