package Reg_05_Embarque;

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
public class CT_ICS_0005 {
	
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
		  @FileParameters(value = "MassaDados/CT_ICS_0005.csv", mapper = CsvWithHeaderMapper.class)	
		  
		  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
		  public void Cadastro_Embarque ( 
			  
			  String DESTINO,
			  String TIPO,
			  String AWBM
			  			  
			  ) throws Exception  { 
	      
		  //Realizar Login
	      driver.findElement(By.id("username")).sendKeys("Teste.Vector");
		  driver.findElement(By.id("pwd")).sendKeys("Total@1234");
		  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
		  Thread.sleep(1000);
	
		  //********************************************************************************************************************************************
		  //	 	Caso de teste - ICS - 0005 - Verificar o cadastramento de Embarque
		  //
		  //Selecionar a opção do menu "Operações" e "Embarque"
		  Actions action = new Actions(driver);
		  WebElement mainMenu = driver.findElement(By.linkText("Operações"));
		  Thread.sleep(1000);
		  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem7\"]"))).click().build().perform();
		  Thread.sleep(2000);
		  
		  //Verificar se está presente a opção Cadastrar Novo Embarque
		  if(isElementPresent(By.linkText("Cadastrar Novo Embarque"))) {
			  System.out.println("Opção Cadastrar Novo Embarque Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Opção Cadastrar Novo Embarque Não Apresentado");
		   }
		  
		  Thread.sleep(1000);
		  
		  //Selecionar a opção Cadastrar Novo Embarque
		  driver.findElement(By.linkText("Cadastrar Novo Embarque")).click();
		  Thread.sleep(2000);
		  
		  //Veirifcar se está presente os campos Destino, Tipo e o botão Prosseguir
		  if(isElementPresent(By.name("agiddestino"))) {
			  System.out.println("Campo Destino Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Campo Destino Não Apresentado");
		   }		  
		  if(isElementPresent(By.name("tipo_transf"))) {
			  System.out.println("Campo Tipo Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Campo Tipo Não Apresentado");
		   }
		  if(isElementPresent(By.linkText("Prosseguir"))) {
			  System.out.println("Botão Prosseguir Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Botão Prosseguir Não Apresentado");
		   }
		  
		  //Preencher os campos Destino, Tipo e clicar no botão Prosseguir
		  driver.findElement(By.id("agiddestino")).sendKeys(DESTINO);
		  driver.findElement(By.id("tipo_transf")).sendKeys(TIPO);
		  driver.findElement(By.linkText("Prosseguir")).click();
		  Thread.sleep(2000);
		  
		  //Verifcar se está presente o campo AWB e o botão Prosseguir
		  if(isElementPresent(By.name("awb"))) {
			  System.out.println("Campo AWB Apresentado com Sucesso");
			  
		   }
		  else {
			  	 System.out.println("Campo AWB Não Apresentado");
			  	 }
		  
		  if(isElementPresent(By.linkText("prosseguir"))) {
			  System.out.println("Botão Prosseguir Apresentado com Sucesso");
			  
		   }
		  else {
			  	 System.out.println("Botão Prosseguir Não Apresentado");
			  	 }
		  
		  //Preencher o campo AWB e clicar no botão Prosseguir
		  driver.findElement(By.name("awb")).sendKeys(AWBM);
		  driver.findElement(By.linkText("prosseguir")).click();
		  Thread.sleep(2000);
		  
		  String msg = driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/form/table/tbody/tr[8]/td/div/p/font[2]/b/font")).getText();
		  System.out.println(msg);	  
		  
		  String StrRotVlr2 = driver.findElement(By.cssSelector("BODY")).getText();
		  System.out.println(StrRotVlr2);
		  
		  //Confirmar a finalização do Embarque
		  driver.findElement(By.linkText("finalizar")).click();
		  Thread.sleep(1000);
		  driver.switchTo().alert().accept();
		  
		  Thread.sleep(2000);
		  
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
