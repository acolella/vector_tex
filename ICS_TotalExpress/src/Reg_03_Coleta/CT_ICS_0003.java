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
	  public void Précondição() throws Exception {
		  
	  
	  			  
	  //****************************************************************************************************************************
	  //	  Passo 1 - Abrir o browser e entrar na URL de QA do ICS - http://ics.qa.totalexpress.com.br/
	  //	  
	  //Executar utilizando FireFox	  
	  //System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
	  //driver = new FirefoxDriver();
	  
	   //Executar utilizando Google Chrome 
	   System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
	   driver = new ChromeDriver();
	  
	  
	  //Inicia a gravação do vídeo da evidência do Teste 
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
	  //	 	Caso de teste - ICS - 0003 - Verificar recebimento do produtos pelo número do pedido (Casamento AWB) - Coleta
	  //
	  //No Menu selecionar a opção "Operações" e "e AWB"
	  Actions action = new Actions(driver);
	  WebElement mainMenu = driver.findElement(By.linkText("Operações"));
	  Thread.sleep(1000);
	  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem5\"]"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  //Verificar se a opção "Casamento AWB" está sendo apresentado
	  if(isElementPresent(By.linkText("casamento de AWBs"))) {
		  System.out.println("Botão Casamento de AWBs Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Botão Casamento de AWBs Não Apresentado");}
		  	 
      //Selecionar a opção "Casamento AWB"  	 
	  driver.findElement(By.linkText("casamento de AWBs")).click();
	  Thread.sleep(2000);  
	  
	  //Verificar se o campo Cliente está sendo apresentado
	  if(isElementPresent(By.name("r"))) {
		  System.out.println("Campo Cliente Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Cliente Não Apresentado");}
		  	 
	  if(isElementPresent(By.xpath("//input[@value='prosseguir']"))) {
				  System.out.println("Botão Prosseguir Apresentado com Sucesso");
	   }
	  else {
			 System.out.println("Botão Prosseguir Não Apresentado");}	  	 
		  	 
	  //Selecionar a opção ANJO DA GUARDA e clicar no botão "Prosseguir"
	  driver.findElement(By.name("r")).sendKeys(CLIENTE);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  
	  //Verificar se o campo Critério, o campo Scanfild e o botão "Prosseguir" estão sendo apresentados 
	  if(isElementPresent(By.id("scanfield"))) {
		  System.out.println("Campo Critério Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Critério Não Apresentado");
	   }
	  
	  if(isElementPresent(By.xpath("//input[@value='prosseguir']"))) {
		  System.out.println("Botão Prosseguir Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Botão Prosseguir Critério Não Apresentado");
	   }
	  
 	  //Selecionar campo Critério como "Pedido", preencher o campo Scanfild com o Número do Pedido e clicar no botão "Prosseguir"
	  Thread.sleep(1000);
	  driver.findElement(By.name("criterio")).sendKeys(CAMPO);
	  driver.findElement(By.id("scanfield")).sendKeys(AWB);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  Thread.sleep(3000);
	  
	  //Verificar se está sendo apresentado texto "Pedido nº" e "Identificação Sistêmica"
	  String PedidoN = driver.findElement(By.cssSelector("BODY")).getText();
	  System.out.println(PedidoN);
	  Thread.sleep(2000);
	  
	  //Preencher o campo Peso e Clicar no botão Prosseguir
	  driver.findElement(By.name("pesoscan")).sendKeys(PESO);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  Thread.sleep(2000);
	  
	  //Verificar se está sendo apresentado as informações do recebimento final com peso
	  String PedidoN1 = driver.findElement(By.cssSelector("BODY")).getText();
	  System.out.println(PedidoN1);
	  assertTrue(driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/h1")).isDisplayed());
	  String awbvlr = driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/h1")).getText();
	  System.out.println(awbvlr);
	  Thread.sleep(5000);
	  
	  //Selecionar o botão Finalizar	  
	  driver.findElement(By.linkText("finalizar")).click();
	  Thread.sleep(2000);
	  
	  if(isElementPresent(By.linkText("casamento de AWBs"))) {
		  System.out.println("Opção Casamento de AWBs - Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Opção Casamento de AWBs - Não Apresentado");
	   }

	  //Finaliza o gravação do vídeo da evidência do Teste 
	  gravarVideo.FinalizarGravacao();

	  }  
	  

	@After
	public void Póscondição() {
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

