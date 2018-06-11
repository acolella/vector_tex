package Reg_03_Coleta;

import Framework.GravarVideo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class CT_ICS_0003 {
	
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private GravarVideo gravarVideo = new GravarVideo();

	    	  
	  @Before
	  public void Pr�condi��o() throws Exception {
		  
	  
	  			  
	  //****************************************************************************************************************************
	  //	  Passo 1 - Abrir o browser e entrar na URL de QA do ICS - http://ics.qa.totalexpress.com.br/
	  //	  
	  //Executar utilizando FireFox	  
	  //System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
	  //driver = new FirefoxDriver();
	  
	   //Executar utilizando Google Chrome 
	   System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
	   driver = new ChromeDriver();
	  
	  
	  //Inicia a grava��o do v�deo da evid�ncia do Teste 
	  gravarVideo.IniciarGravacao();
	  driver.manage().window().maximize();
	  driver.get("http://ics.qa.totalexpress.com.br/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	  @Test
	  @FileParameters(value = "MassaDados/CT_ICS_0003.csv", mapper = CsvWithHeaderMapper.class)
	  
	  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
	  public void Cad_Encomenda ( 
		  
		  String CLIENTE,  
		  String CAMPO,
		  String AWB,
		  String PESO 
		  
		  ) throws Exception  { 
      
	  //Realizar Login
      driver.findElement(By.id("username")).sendKeys("Teste.Vector");
	  driver.findElement(By.id("pwd")).sendKeys("Total@1234");
	  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
	  Thread.sleep(1000);
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0003 - Verificar recebimento do produtos pelo n�mero do pedido (Casamento AWB) - Coleta
	  //
	  //No Menu selecionar a op��o "Opera��es" e "e AWB"
	  Actions action = new Actions(driver);
	  WebElement mainMenu = driver.findElement(By.linkText("Opera��es"));
	  Thread.sleep(1000);
	  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem5\"]"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  //Verificar se a op��o "Casamento AWB" est� sendo apresentado
	  if(isElementPresent(By.linkText("casamento de AWBs"))) {
		  System.out.println("Bot�o Casamento de AWBs Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Bot�o Casamento de AWBs N�o Apresentado");}
		  	 
      //Selecionar a op��o "Casamento AWB"  	 
	  driver.findElement(By.linkText("casamento de AWBs")).click();
	  Thread.sleep(2000);  
	  
	  //Verificar se o campo Cliente est� sendo apresentado
	  if(isElementPresent(By.name("r"))) {
		  System.out.println("Campo Cliente Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Cliente N�o Apresentado");}
		  	 
	  if(isElementPresent(By.xpath("//input[@value='prosseguir']"))) {
				  System.out.println("Bot�o Prosseguir Apresentado com Sucesso");
	   }
	  else {
			 System.out.println("Bot�o Prosseguir N�o Apresentado");}	  	 
		  	 
	  //Selecionar a op��o ANJO DA GUARDA e clicar no bot�o "Prosseguir"
	  driver.findElement(By.name("r")).sendKeys(CLIENTE);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  
	  //Verificar se o campo Crit�rio, o campo Scanfild e o bot�o "Prosseguir" est�o sendo apresentados 
	  if(isElementPresent(By.id("scanfield"))) {
		  System.out.println("Campo Crit�rio Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Crit�rio N�o Apresentado");
	   }
	  
	  if(isElementPresent(By.xpath("//input[@value='prosseguir']"))) {
		  System.out.println("Bot�o Prosseguir Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Bot�o Prosseguir Crit�rio N�o Apresentado");
	   }
	  
 	  //Selecionar campo Crit�rio como "Pedido", preencher o campo Scanfild com o N�mero do Pedido e clicar no bot�o "Prosseguir"
	  Thread.sleep(1000);
	  driver.findElement(By.name("criterio")).sendKeys(CAMPO);
	  driver.findElement(By.id("scanfield")).sendKeys(AWB);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  Thread.sleep(3000);
	  
	  //Verificar se est� sendo apresentado texto "Pedido n�" e "Identifica��o Sist�mica"
	  String PedidoN = driver.findElement(By.cssSelector("BODY")).getText();
	  System.out.println(PedidoN);
	  Thread.sleep(2000);
	  
	  //Preencher o campo Peso e Clicar no bot�o Prosseguir
	  driver.findElement(By.name("pesoscan")).sendKeys(PESO);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  Thread.sleep(2000);
	  
	  //Verificar se est� sendo apresentado as informa��es do recebimento final com peso
	  String PedidoN1 = driver.findElement(By.cssSelector("BODY")).getText();
	  System.out.println(PedidoN1);
	  assertTrue(driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/h1")).isDisplayed());
	  String awbvlr = driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/h1")).getText();
	  System.out.println(awbvlr);
	  Thread.sleep(5000);
	  
	  //Selecionar o bot�o Finalizar	  
	  driver.findElement(By.linkText("finalizar")).click();
	  Thread.sleep(2000);
	  
	  if(isElementPresent(By.linkText("casamento de AWBs"))) {
		  System.out.println("Op��o Casamento de AWBs - Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Op��o Casamento de AWBs - N�o Apresentado");
	   }

	  //Finaliza o grava��o do v�deo da evid�ncia do Teste 
	  gravarVideo.FinalizarGravacao();

	  }  
	  

	@After
	public void P�scondi��o() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		}
	
	private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		    
		  }
}

