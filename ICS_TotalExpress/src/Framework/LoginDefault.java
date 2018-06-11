package Framework;
import Framework.GravarVideo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginDefault {
	
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private GravarVideo gravarVideo = new GravarVideo();   
	   
	@Before
	public void Pr�condi��o() throws Exception {
		   	
		  //Executar com o Fireix 
		  //System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
		  //driver = new FirefoxDriver();
				    
				    
		  //Executar com Google Chrome 
		  System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
		  driver = new ChromeDriver();
		  
		  //Iniciar a grava��o do Teste
		  gravarVideo.IniciarGravacao();
				   
		  //Maximizar navegar a acessar o ambiente de QA
		  driver.manage().window().maximize();
		  driver.get("http://ics.qa.totalexpress.com.br/");
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
    
	  }
	   
	  @Test
	  @FileParameters(value = "MassaDados/CT_ICS_0001.csv", mapper = CsvWithHeaderMapper.class) 
	  
	  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
	  public void LoginICS (
	  
		  String USUARIO, 
		  String SENHA) throws Exception {
		  
		  //Verifica se est� exibindo o campo Usu�rio
		  assertTrue(driver.findElement(By.id("username")).isDisplayed());
		  if(isElementPresent(By.id("username"))) {
			  System.out.println("Campo Usu�rio Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Campo Usu�rio N�o Apresentado");
		   }
		  
		  //Verifica se est� exibindo o campo Senha
		  assertTrue(driver.findElement(By.id("pwd")).isDisplayed());
		  if(isElementPresent(By.id("pwd"))) {
			  System.out.println("Campo Senha Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Campo Senha N�o Apresentado");
		   }
		  
		  //Verifica se est� exibindo o bot�o Entrar
		  assertTrue(driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).isDisplayed());
		  if(isElementPresent(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input"))) {
			  System.out.println("Bot�o Entrar Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Bot�o Entrar N�o Apresentado");
		   }
		  
		  
		  //Passo 3 - Preencher os campos Usu�rio e Senha conforme massa de dados e clicar no bot�o "Entrar"  
		  driver.findElement(By.id("username")).sendKeys(USUARIO);
		  driver.findElement(By.id("pwd")).sendKeys(SENHA);
		  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
		  Thread.sleep(2000);
		  
		  //Passo 4 - Verificar se est� sendo exibido os dados de usu�rio que acessou o sistema
		  assertTrue(driver.findElement(By.xpath("//*[@id=\"header_dados\"]/b")).isDisplayed());
		  if(isElementPresent(By.xpath("//*[@id=\"header_dados\"]/b"))) {
			  System.out.println("Cabe�alho Usu�rio Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Cabe�alho Usu�rio N�o Apresentado");
		   }

		  
		  //Finalizar Grava��o do Teste
		  gravarVideo.FinalizarGravacao();
		  
	}
	  
	  @After
	  public void P�scondi��o() {
	  	
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
	  
