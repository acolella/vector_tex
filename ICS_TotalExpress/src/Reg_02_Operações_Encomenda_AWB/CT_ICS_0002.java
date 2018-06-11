package Reg_02_Operações_Encomenda_AWB;

import Framework.GravarVideo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class CT_ICS_0002 {
	
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private GravarVideo gravarVideo = new GravarVideo();
	
	    	  
	  @Before
	  public void Précondição() throws Exception {
			  			  
	  //Passo 1 - Abrir o Firefox e entrar na URL de QA do ICS - http://ics.qa.totalexpress.com.br/   
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
	  @FileParameters(value = "MassaDados/CT_ICS_0002.csv", mapper = CsvWithHeaderMapper.class)
	  
	  //Passo 2 - Declarando a massa de dados associado ao arquivo CSV
	  public void Cad_Encomenda ( 
		  
		  String PEDIDO,
		  String NATUREZA,
		  String CEP,
		  String CPF,
		  String NOME,
		  String NUMERO,
		  String NUM_DOCFISCAL,
		  String DT_EMISSAO,
		  String VLR_DOC,
		  String VLR_MERCAD
		  
		  ) throws Exception  { 
      
	  //Realizar Login
      driver.findElement(By.id("username")).sendKeys("Teste.Vector");
	  driver.findElement(By.id("pwd")).sendKeys("Total@1234");
	  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
	  Thread.sleep(1000);
	  
	  //No Menu selecionar a opção "Operações" e "e AWB"
	  Actions action = new Actions(driver);
	  WebElement mainMenu = driver.findElement(By.linkText("Operações"));
	  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem2\"]/a"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  //Verificar apresentação da tela E-AWB (Cadastro de Encomenda) pelo Título da Tela
	  String Strawb = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > h1")).getText();
	  System.out.println(Strawb); 
	  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > h1")).isDisplayed());
	  Thread.sleep(2000);
	  
	  //Preencher o campo Cliente
	  new Select(driver.findElement(By.xpath("//table[@id='tabela']/tbody/tr[4]/td[2]/select"))).selectByVisibleText("ANJO DA GUARDA (1249)");;
	  Thread.sleep(1000);
	  //Preencher o campo CNPJ da NF-e
	  new Select(driver.findElement(By.id("cnpj_reid"))).selectByVisibleText("11.380.294/0001-40 - ANJO DA GUARDA LOJA INFANTIL LTDA - São Paulo/SP");
	  Thread.sleep(1000);
	  //Preencher o campo Tipo da Entrega
	  new Select(driver.findElement(By.id("tipoEntrega"))).selectByVisibleText("Entrega Normal");
	  Thread.sleep(1000);
	  //Preencher o campo Número o Pedido
	  driver.findElement(By.name("pedido")).sendKeys(PEDIDO);
	  //Thread.sleep(1000);
	  //Preenhcer o campo Natureza da Mercadoria
	  driver.findElement(By.name("natureza")).sendKeys(NATUREZA);
	  //Thread.sleep(1000);
	  //Preencher o campo CEP
	  driver.findElement(By.id("cep")).sendKeys(CEP);
	  //Thread.sleep(1000);
	  //Preencher o campo CPF
	  driver.findElement(By.name("cpf")).sendKeys(CPF);
	  //Thread.sleep(1000);
	  //Preencher o campo Nome
	  driver.findElement(By.name("nome")).sendKeys(NOME);
	  //Thread.sleep(1000);
	  //Preencher checkbox I.E Isento como Sim.
	  driver.findElement(By.name("isento")).click();
	  //Thread.sleep(1000);
	  //Preencher o campo Número
	  driver.findElement(By.name("enderecoNum")).sendKeys(NUMERO);
	  //Thread.sleep(1000);
	  //Preencher o campo Estado
	  new Select(driver.findElement(By.id("campoEstado"))).selectByValue("SP");
	  //Thread.sleep(1000);
	  //Selecionar o Dado Fiscal igual "Outros Documentos Fiscais"
	  driver.findElement(By.id("tpDocFiscalNFo")).click();
	  //Thread.sleep(1000);
	  //Preencher Numero Documento Fiscal
	  driver.findElement(By.name("nfoNumero")).sendKeys(NUM_DOCFISCAL);
	  //Thread.sleep(1000);
	  //Preencher Data Emissão
	  driver.findElement(By.name("nfoDemi")).sendKeys(DT_EMISSAO);
	  //Thread.sleep(1000);
	  //Preencher Valor Documento
	  driver.findElement(By.name("nfoValor")).sendKeys(VLR_DOC);
	  //Thread.sleep(1000);
	  //Preencher Valor Mercadoria
	  driver.findElement(By.name("nfoValorProd")).sendKeys(VLR_MERCAD);
	  //Thread.sleep(1000);
	  //Preencher o campo Número
	  driver.findElement(By.id("registrar_encomenda")).click();
	  Thread.sleep(6000);
	  
	  //Verifica a exibição do número da E-AWB
	  assertTrue(driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/div/p/a")).isDisplayed());
	  String awbvlr = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > p > a")).getText();
	  System.out.println(awbvlr);
	  	  
	  //Grava na variável o número da E-AWB e Apresenta no Console
	  //String strLinkHref = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/div/p/a")).getAttribute("href");
	  //System.out.println(strLinkHref);
	  
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
}

