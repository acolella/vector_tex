package Reg_05_Embarque;

import Framework.GravarVideo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class CT_ICS_0006 {
	
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
		  @FileParameters(value = "MassaDados/CT_ICS_0006.csv", mapper = CsvWithHeaderMapper.class)	
		  
		  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
		  public void Associar_Motoris_Carro_Embarque ( 
			  
			  String EMBARQUE,
			  String MOTORISTA,
			  String VEICULO
			  
			  			  
			  ) throws Exception  { 
	      
		  //Realizar Login
	      driver.findElement(By.id("username")).sendKeys("Teste.Vector");
		  driver.findElement(By.id("pwd")).sendKeys("Total@1234");
		  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
		  Thread.sleep(1000);
	
		  //********************************************************************************************************************************************
		  //	 	Caso de teste - ICS - 0006 - Verificar associação de Motorista e Carro ao Embarque
		  //
		  //Selecionar a opção do menu "Operações" e "Embarque"
		  Actions action = new Actions(driver);
		  WebElement mainMenu = driver.findElement(By.linkText("Operações"));
		  Thread.sleep(1000);
		  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem7\"]"))).click().build().perform();
		  Thread.sleep(2000);
		  
		  //Verificar se o combo Busca e o campo buscar estão sendo apresentado
		  if(isElementPresent(By.name("selCodViaConhe"))) {
			  System.out.println("Combo Busca Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Combo Busca Não Apresentado");
		   }
		  
		  if(isElementPresent(By.name("txtDescricao"))) {
			  System.out.println("Campo Buscar Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Campo Buscar Não Apresentado");
		   }
		  
		  Thread.sleep(1000);
		  
		  //Preencher o combo Buscae o campo Descrição e clicar no botão Filtrar
		  new Select(driver.findElement(By.name("selCodViaConhe"))).selectByVisibleText("# Embarque");
		  driver.findElement(By.name("txtDescricao")).sendKeys(EMBARQUE);
		  driver.findElement(By.xpath("//*[@id=\"filtro\"]/tbody/tr/td/input[5]")).click();
		  Thread.sleep(3000);
		  
		  //Verificar se o Número do Embarque foi apresentado
		  String StrRotVlr2 = driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_escura > td:nth-child(1)")).getText();
		  System.out.println(StrRotVlr2);
		  assertTrue(driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_escura > td:nth-child(1)")).isDisplayed());
		  
		  //Clicar no número do Embarque
		  driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[2]/td[1]/a")).click();
		  
		  //Verificar apresentação da Tela Embarque de Transferência pelo número do embarque
		  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > table > tbody > tr:nth-child(1) > td > table:nth-child(1) > tbody > tr > td > fieldset > legend > b")).isDisplayed());
		  assertTrue(driver.findElement(By.name("sel_mot_id")).isDisplayed());
		  assertTrue(driver.findElement(By.name("sel_placa")).isDisplayed());
		  
		  //Preencher os campos Motorista e Veículo e Verificar apresentaçaõ do RISCO VERDE
		  driver.findElement(By.name("sel_mot_id")).sendKeys(MOTORISTA);
		  driver.findElement(By.name("sel_placa")).sendKeys(VEICULO);
		  assertTrue(driver.findElement(By.id("risco")).isDisplayed());
		  
		  //Clicar no Botão Marcar como Embarcado
		  assertTrue(driver.findElement(By.name("marcar_embarcado")).isDisplayed());
		  driver.findElement(By.name("marcar_embarcado")).click();
		
		  //Confirmar Dados
		  driver.switchTo().alert().accept();
		  
		  //Verificar mensagem de Enbarcado com sucesso
		  assertTrue(driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/div[1]/div/b")).isDisplayed());
		  String StrRotVlr3 = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > div:nth-child(9) > div > b")).getText();
		  System.out.println(StrRotVlr3);
				  
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
