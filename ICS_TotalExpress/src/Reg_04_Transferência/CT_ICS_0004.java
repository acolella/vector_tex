package Reg_04_Transfer�ncia;

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
public class CT_ICS_0004 {
	
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
	  @FileParameters(value = "MassaDados/CT_ICS_0004.csv", mapper = CsvWithHeaderMapper.class)
	  
	  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
	  public void Cad_Encomenda ( 
		  
		  String AGENTE,
		  String ESPECIE,
		  String AWB,
		  String LACRE,
		  String TERMO,
		  String DESCR 
		  
		  ) throws Exception  { 
      
	  //Realizar Login
      driver.findElement(By.id("username")).sendKeys("Teste.Vector");
	  driver.findElement(By.id("pwd")).sendKeys("Total@1234");
	  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
	  Thread.sleep(1000);
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0004 - Verificar o cadastramento de Transfer�ncia (AWB Master)
	  //
	  //Selecionar a op��o do menu "Opera��es" e "AWB Master"
	  Actions action = new Actions(driver);
	  WebElement mainMenu = driver.findElement(By.linkText("Opera��es"));
	  Thread.sleep(1000);
	  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem1\"]"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  //Na Tela de Cadastro de AWB Master - Transfer�ncia verificar os campos agente e esp�cie apresentados e o bot�o Criar AWB Master
	  if(isElementPresent(By.id("agid"))) {
		  System.out.println("Campo Agente Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Agente N�o Apresentado");
	   }
	  
	  if(isElementPresent(By.name("especie"))) {
		  System.out.println("Campo Esp�cie Apresentado com Sucesso");
	   }
	  
	  else {
		  
		  	 System.out.println("Campo Esp�cie Crit�rio N�o Apresentado");
	   }
	  
	  if(isElementPresent(By.xpath("//input[@value='Criar AWB Master']"))) {
		  System.out.println("Bot�o Criar ABW Master Apresentado com Sucesso");
	   }
	  
	  else {
		  
		  	 System.out.println("Bot�o Criar ABW Master N�o Apresentado");
	   }
	  
	  Thread.sleep(1000);
	  
	  //Preencher os campos Ag�ncia e Esp�cie e clicar no bot�o "Criar AWB Master"
	  driver.findElement(By.id("agid")).sendKeys(AGENTE);
	  driver.findElement(By.name("especie")).sendKeys(ESPECIE);
	  driver.findElement(By.xpath("//input[@value='Criar AWB Master']")).click();
	  Thread.sleep(3000);
	  
	  //Preencher o campos AWB e clicar no bot�o Prosseguir
	  driver.findElement(By.id("scanfield")).sendKeys(AWB);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  
	  
	  //Verificar se a AWB foi Adicionada a Transfer�ncia
	  if(isElementPresent(By.xpath("//*[@id=\"formCadMaster\"]/table/tbody/tr[1]/td/div/div/b/font/font/b"))) {
		  System.out.println("AWB Adicionada Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("AWB Adicionada N�o Apresentado");
	   }
	  
	  //Selecionar a op��o Fechar Master AWB
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("Fechar Master AWB")).click();	  
	  
	  //Preencher o campo Lacre 
	  driver.findElement(By.id("lacre")).sendKeys(LACRE);
	  Thread.sleep(1000);
	  
	  //Verificar informa��es apresentadas na Tela
	  String StrRotVlr = driver.findElement(By.cssSelector("BODY")).getText();
	  System.out.println(StrRotVlr);
	  
	  Thread.sleep(3000);
	  
	  //clicar no Voltar ao Sistema
	  driver.findElement(By.linkText("Voltar ao Sistema")).click();
	  
	  //Verificar se a tela Cadastro de AWB Master Transfer�ncia foi apresentada
	  if(isElementPresent(By.id("searchTerm"))) {
		  System.out.println("Tela AWB Master Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Tela AWB Master N�o Apresentado");
	   }
	  	  
	  //Clicar na combo Term, Preencher AWB e clicar no bot�o Buscar Encomenda
	  driver.findElement(By.name("term")).sendKeys(TERMO);
	  driver.findElement(By.name("searchterm")).sendKeys(DESCR);
	  driver.findElement(By.name("Submit")).click();
	  Thread.sleep(2000);
	  	  
	  //Gravar o n�mero da AWB Master na Vari�vel
	  String StrRotVlr2 = driver.findElement(By.xpath("//*[@id=\\\"tabela\\\"]/tbody/tr[2]/td[6]")).getText();
	  System.out.println(StrRotVlr2);
	  Thread.sleep(2000);
	  
	  //Verificar apresenta��o da AWB Master 
	  assertTrue(driver.findElement(By.xpath("//*[@id=\\\\\\\"tabela\\\\\\\"]/tbody/tr[2]/td[6]")).isDisplayed());
	  
	  
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

