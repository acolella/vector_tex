package Reg_09_Consultar_Status_AWB;

import Framework.GravarVideo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class CT_ICS_0011 {
	
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
		  driver.get("http://ics.qa.totalexpress.com.br/");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		  }

		  @Test
		  @FileParameters(value = "MassaDados/CT_ICS_0011.csv", mapper = CsvWithHeaderMapper.class)	
		  
		  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
		  public void Consutar_Status_AWB ( 
			  
			  String TERMO,	  
			  String AWB

			  
			  			  
			  ) throws Exception  { 
	      
		  //Realizar Login
	      driver.findElement(By.id("username")).sendKeys("Teste.Vector");
		  driver.findElement(By.id("pwd")).sendKeys("Total@1234");
		  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
		  Thread.sleep(1000);
	
		  //********************************************************************************************************************************************
		  //	 							Caso de teste - ICS - 0010 - Consultar Status AWB
		  //
		  //
		  //Preencher o combo Termo, campo Descrição e clicar no botão Buscar Encomenda
		  new Select(driver.findElement(By.name("term"))).selectByVisibleText("AWB");
		  driver.findElement(By.name("searchterm")).sendKeys(AWB);
		  driver.findElement(By.name("Submit")).click();
		  Thread.sleep(2000);
		  
		  //Verificar o número da AWB está sendo apresentado
		  String StrRotVlr5 = driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(4)")).getText();
		  System.out.println(StrRotVlr5);
		  assertTrue(driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(4)")).isDisplayed());
		  
		  //Clicar no número da AWB
		  driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[2]/td[4]/a")).click();
		  
		  //Verificar apresentação da Tela Embarque de Transferência pelo número do embarque
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > h1")).isDisplayed());
		  String StrRotVlr6 = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > h1")).getText();
		  System.out.println(StrRotVlr6);
		  
		  //Verificar a tabela de Status Apresentada
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody")).isDisplayed());
		  String StrRotVlr7 = driver.findElement(By.cssSelector("#borda > tbody")).getText();
		  System.out.println(StrRotVlr7);
		  
		  //Verifica Status Arquivo Recebido
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(2) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr8 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(2) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr8);
		  
		  //Verifica Status Arquivo Aprovado
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(3) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr9 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(3) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr9);
		  		  
		  Thread.sleep(2000);
		  
		  //Verifica Status INÍCIO DE COLETA C/ NÃO CONFORMIDADE
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(4) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr10 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr10);
		  		  
		  //Verifica Status COLETA REALIZADA C/ NÃO CONFORMIDADE
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(5) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr11 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr11);
		  
		  //Verifica Status COLETA RECEBIDA COM NC NO CD DE
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(6) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr12 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(6) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr12);
		  
		  Thread.sleep(2000);
		  
		  //Verifica Status RECEBIDA E PROCESSADA NO CD
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(7) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr13 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(7) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr13);
		  
		  //Verifica Status Embarcado para: TZS
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(8) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr14 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(8) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr14);
		  
		  //DESCARREGADO
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(11) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr15 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(11) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr15);
		  
		  //RECEBIDO CD DE: - TZS	
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(12) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr16 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(12) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr16);
		  
		  //EM ROTA	(CAF: 2079558)
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(13) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr17 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(13) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr17);
		  
		  //EM RUA
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(14) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr18 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(14) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr18);
		  
		  //ENTREGA REALIZADA
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(15) > td:nth-child(2)")).isDisplayed());
		  String StrRotVlr19 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(15) > td:nth-child(2)")).getText();
		  System.out.println(StrRotVlr19);
				  
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
			
}







