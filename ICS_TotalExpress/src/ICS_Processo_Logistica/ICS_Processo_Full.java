package ICS_Processo_Logistica;

import Framework.GravarVideo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;

import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class ICS_Processo_Full {
	
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private GravarVideo gravarVideo = new GravarVideo();
	    	  
	  @Before
	  public void Pr�condi��o() throws Exception {
		  
	  //Abrir o Firefox
	  //System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
	  //driver = new FirefoxDriver();
	  
	  //Abrir o Google Chrome
	  System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  //Maximizar o Browser
	  driver.manage().window().maximize();
	  
	  //Inicia a grava��o do v�deo da evid�ncia do Teste 
	  gravarVideo.IniciarGravacao();
	  
	  //Acessar a URL de QA do ICS - http://ics.qa.totalexpress.com.br/   
	  driver.get("http://ics.qa.totalexpress.com.br/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	  @Test
	  @FileParameters(value = "MassaDados/Processo_Full.csv", mapper = CsvWithHeaderMapper.class)
	  
	  //Declarando a massa de dados associado ao arquivo CSV
	  public void ProcessoFull ( 
		  
	  //Massa de Teste do Caso de Teste 0001 - Verificar realiza��o do acesso v�lido ao sistema ICS
	  String USUARIO,
	  String SENHA,
		  
	  //Massa de Teste do Caso de Teste 0002 - Verificar o cadastro da encomenta de entrega Normal sendo 1 volume com Inscri��o Estadual Isento 
	  String PEDIDO,
	  String NATUREZA,
	  String CEP,
	  String CPF,
	  String NOME,
	  String NUMERO,
	  String NUM_DOCFISCAL,
	  String DT_EMISSAO,
	  String VLR_DOC,
	  String VLR_MERCAD,
	  
	  //Massa de Teste do Caso de teste - ICS - 0003 - Verificar recebimento do produtos pelo n�mero do pedido (Casamento AWB) - Coleta
	  String CLIENTE,  
	  String CAMPO,
	  String PESO,
	  
	  //Massa de Teste do Caso de teste - ICS - 0004 - Verificar o cadastramento de Transfer�ncia (AWB Master)
	  String AGENTE,
	  String ESPECIE,
	  String LACRE,
	  String TERMO,
	  	  
	  //Massa de Teste do Caso de teste - ICS - 0005 - Verificar o cadastramento de Embarque
	  String DESTINO,
	  String TIPO,
	  
	  //Massa de Teste do Caso de teste - ICS - 0006 - Verificar associa��o de Motorista e Carro ao Embarque
	  String MOTORISTA,
	  String VEICULO,
	  
	  //Massa de teste do Caso de teste - ICS - 0008 - Verificar cadastro da CAF (Rota Destinat�rio)
	  String USUARIOCD,
	  String SENHACD,
	  String ORIGEM,
	  
	  //Massa de Teste do Caso de teste - ICS - 0010 - Verificar baixa da CAF (Formaliza��o da Entrega)
	  String TOTNC, 
	  String DATATUAL,
	  String HORATUAL
	 
		  
 	  ) throws Exception  { 
      
	  //********************************************************************************************************************************************
	  //								Caso de Teste - ICS - 0001 - Verificar realiza��o do acesso v�lido ao sistema ICS 	  
	  //
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
		  if(isElementPresent(By.xpath("//*[@id=\"login\"]X/tbody/tr[6]/td/input"))) {
			  System.out.println("Bot�o Entrar Apresentado com Sucesso");
		   }
		  else {
			  	 System.out.println("Bot�o Entrar N�o Apresentado");
		   }	  
		  
		  
	  Thread.sleep(1000);
	  
	  //Preencher os campos Usu�rio e Senha e clicar no bot�o Entrar
	  driver.findElement(By.id("username")).sendKeys(USUARIO);
	  driver.findElement(By.id("pwd")).sendKeys(SENHA);
	  driver.findElement(By.xpath("//*[@id=\"login\"]/tbody/tr[6]/td/input")).click();
	  Thread.sleep(1000);
		  
	  //Verificar se est� sendo exibido os dados de usu�rio que acessou o sistema
	  assertTrue(driver.findElement(By.xpath("//*[@id=\"header_dados\"]/b")).isDisplayed());
	  
	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  //********************************************************************************************************************************************
	  
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0002 - Verificar o cadastro da encomenta de entrega Normal sendo 1 volume com Inscri��o Estadual Isento 
	  //									 para Estado de SP
	  //
	  //No Menu selecionar a op��o "Opera��es" e "e AWB"
	  Thread.sleep(3000);
	  Actions action = new Actions(driver);
	  WebElement mainMenu = driver.findElement(By.linkText("Opera��es"));
	  action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem2\"]/a"))).click().build().perform();
	  	  
	  //Verificar apresenta��o da tela E-AWB (Cadastro de Encomenda) pelo T�tulo da Tela
	  assertTrue(driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/div/h1")).isDisplayed());
	  Thread.sleep(2000);
	  
	  //Preencher o campo Cliente
	  new Select(driver.findElement(By.xpath("//table[@id='tabela']/tbody/tr[4]/td[2]/select"))).selectByVisibleText("ANJO DA GUARDA (1249)");
	  Thread.sleep(1000);
	  //Preencher o campo CNPJ da NF-e
	  new Select(driver.findElement(By.id("cnpj_reid"))).selectByVisibleText("11.380.294/0001-40 - ANJO DA GUARDA LOJA INFANTIL LTDA - S�o Paulo/SP");
	  Thread.sleep(1000);
	  //Preencher o campo Tipo da Entrega
	  new Select(driver.findElement(By.id("tipoEntrega"))).selectByVisibleText("Entrega Normal");
	  Thread.sleep(1000);
	  //Preencher o campo N�mero o Pedido
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
	  //Preencher o campo N�mero
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
	  //Preencher Data Emiss�o
	  driver.findElement(By.name("nfoDemi")).sendKeys(DT_EMISSAO);
	  //Thread.sleep(1000);
	  //Preencher Valor Documento
	  driver.findElement(By.name("nfoValor")).sendKeys(VLR_DOC);
	  //Thread.sleep(1000);
	  //Preencher Valor Mercadoria
	  driver.findElement(By.name("nfoValorProd")).sendKeys(VLR_MERCAD);
	  //Thread.sleep(1000);
	  //Preencher o campo N�mero
	  driver.findElement(By.id("registrar_encomenda")).click();
	  Thread.sleep(6000);
	  
	  //Verifica a exibi��o do n�mero da E-AWB
	  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > p > a")).isDisplayed());
	  if(isElementPresent(By.cssSelector("#borda > tbody > tr > td > div > p > a"))) {
		  System.out.println("N�mero da eAWB Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("N�mero da eAWB N�o Apresentado");
	   }	  
	  	  
	  //Grava na vari�el o n�mero da E-AWB e Apresenta no Console
	  String StrEAWB = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > p > a")).getText();
	  System.out.println(StrEAWB);
	  
	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  //********************************************************************************************************************************************
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0003 - Verificar recebimento do produtos pelo n�mero do pedido (Casamento AWB) - Coleta
	  //
	  //No Menu selecionar a op��o "Opera��es" e "Coleta"
	  Thread.sleep(2000);
	  WebElement mainMenu2 = driver.findElement(By.linkText("Opera��es"));
	  action.moveToElement(mainMenu2).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem5\"]"))).click().build().perform();
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
	  driver.findElement(By.id("scanfield")).sendKeys(StrEAWB);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  Thread.sleep(2000);
	  
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
	  
	  Thread.sleep(2000);
	  
	  //Selecionar o bot�o Finalizar	  
	  driver.findElement(By.linkText("finalizar")).click();
	  Thread.sleep(2000);
	  
	  if(isElementPresent(By.linkText("casamento de AWBs"))) {
		  System.out.println("Op��o Casamento de AWBs - Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Op��o Casamento de AWBs - N�o Apresentado");
	   }
	  
	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  //********************************************************************************************************************************************
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0004 - Verificar o cadastramento de Transfer�ncia (AWB Master)
	  //
	  //Selecionar a op��o do menu "Opera��es" e "AWB Master"
	  WebElement mainMenu3 = driver.findElement(By.linkText("Opera��es"));
	  action.moveToElement(mainMenu3).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem1\"]"))).click().build().perform();
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
	  Thread.sleep(2000);
	  
	  //Preencher o campos AWB e clicar no bot�o Prosseguir
	  driver.findElement(By.id("scanfield")).sendKeys(StrEAWB);
	  driver.findElement(By.xpath("//input[@value='prosseguir']")).click();
	  
	  Thread.sleep(2000);
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
	  
	  Thread.sleep(2000);
	  
	  //Clicar no bot�o Concluir
	  driver.findElement(By.xpath("//*[@id=\"boxmaster\"]/input[2]")).click();
	  Thread.sleep(1000);
	  
	  //Clicar no Voltar ao Sistema
	  driver.findElement(By.linkText("Voltar ao Sistema")).click();
	  
	  //Verificar se a tela Cadastro de AWB Master Transfer�ncia foi apresentada
	  if(isElementPresent(By.id("searchTerm"))) {
		  System.out.println("Tela AWB Master Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Tela AWB Master N�o Apresentado");
	   }
	  	  
	  Thread.sleep(1000);
	  
	  //Clicar na combo Term, Preencher AWB e clicar no bot�o Buscar Encomenda
	  new Select(driver.findElement(By.name("term"))).selectByVisibleText("AWB");
	  	  
	  driver.findElement(By.name("searchterm")).sendKeys(StrEAWB);
	  
	  driver.findElement(By.name("Submit")).click();
	  Thread.sleep(2000);
	  	  
	  //Verificar apresenta��o da AWB Master 
	  assertTrue(driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(6) > a")).isDisplayed());
	  
	  //Gravar o n�mero da AWB Master na Vari�vel
	  String StrAWBM = driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(6) > a")).getText();
	  System.out.println(StrAWBM);
	  Thread.sleep(2000);
	  
	  
	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  //********************************************************************************************************************************************
	  
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0005 - Verificar o cadastramento de Embarque
	  //
	  //Selecionar a op��o do menu "Opera��es" e "Embarque"
	  WebElement mainMenu4 = driver.findElement(By.linkText("Opera��es"));
	  action.moveToElement(mainMenu4).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem7\"]"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  //Verificar se est� presente a op��o Cadastrar Novo Embarque
	  if(isElementPresent(By.linkText("Cadastrar Novo Embarque"))) {
		  System.out.println("Op��o Cadastrar Novo Embarque Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Op��o Cadastrar Novo Embarque N�o Apresentado");
 	   }
	  
	  Thread.sleep(1000);
	  
	  //Selecionar a op��o Cadastrar Novo Embarque
	  driver.findElement(By.linkText("Cadastrar Novo Embarque")).click();
	  Thread.sleep(2000);
	  
	  //Veirifcar se est� presente os campos Destino, Tipo e o bot�o Prosseguir
	  if(isElementPresent(By.name("agiddestino"))) {
		  System.out.println("Campo Destino Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Destino N�o Apresentado");
	   }		  
	  if(isElementPresent(By.name("tipo_transf"))) {
		  System.out.println("Campo Tipo Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Campo Tipo N�o Apresentado");
	   }
	  if(isElementPresent(By.linkText("Prosseguir"))) {
		  System.out.println("Bot�o Prosseguir Apresentado com Sucesso");
	   }
	  else {
		  	 System.out.println("Bot�o Prosseguir N�o Apresentado");
	   }
	  
	  //Preencher os campos Destino, Tipo e clicar no bot�o Prosseguir
	  driver.findElement(By.id("agiddestino")).sendKeys(DESTINO);
	  Thread.sleep(1000);
	  driver.findElement(By.id("tipo_transf")).sendKeys(TIPO);
	  driver.findElement(By.linkText("Prosseguir")).click();
	  Thread.sleep(2000);
	  
	  //Verifcar se est� presente o campo AWB e o bot�o Prosseguir
	  if(isElementPresent(By.name("awb"))) {
		  System.out.println("Campo AWB Apresentado com Sucesso");
		  
	   }
	  else {
		  	 System.out.println("Campo AWB N�o Apresentado");
		  	 }
	  
	  if(isElementPresent(By.linkText("prosseguir"))) {
		  System.out.println("Bot�o Prosseguir Apresentado com Sucesso");
		  
	   }
	  else {
		  	 System.out.println("Bot�o Prosseguir N�o Apresentado");
		  	 }
	  
	  //Preencher o campo AWB e clicar no bot�o Prosseguir
	  driver.findElement(By.name("awb")).sendKeys(StrAWBM);
	  driver.findElement(By.linkText("prosseguir")).click();
	  Thread.sleep(2000);
	  
	  String msg = driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/form/table/tbody/tr[8]/td/div/p/font[2]/b/font")).getText();
	  System.out.println(msg);	  
	  
	  String StrRotVlr3 = driver.findElement(By.cssSelector("BODY")).getText();
	  System.out.println(StrRotVlr3);
	  
	  //Confirmar a finaliza��o do Embarque
	  driver.findElement(By.linkText("finalizar")).click();
	  Thread.sleep(1000);
	  driver.switchTo().alert().accept();
	  Thread.sleep(2000);
	  
	  //Verificar se apresentou a tela de embarque
	  assertTrue(driver.findElement(By.linkText("Cadastrar Novo Embarque")).isDisplayed());
	  Thread.sleep(1000);
	  
	  //Clicar na combo Term, Preencher AWB e clicar no bot�o Buscar Encomenda
	  new Select(driver.findElement(By.name("term"))).selectByVisibleText("AWB");
	  driver.findElement(By.name("searchterm")).sendKeys(StrEAWB);
	  driver.findElement(By.name("Submit")).click();
	  
	  //Clicar no n�mero da AWB
	  driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[2]/td[4]/a")).click();
	  
	  //Verificar apresenta��o do N�mero do Embarque
	  assertTrue(driver.findElement(By.cssSelector("#tabela > tbody > tr:nth-child(11) > td:nth-child(2) > font > a")).isDisplayed());
	  
	  //Gravar o n�mero da AWB Master na Vari�vel
	  String StrEmbarque = driver.findElement(By.cssSelector("#tabela > tbody > tr:nth-child(11) > td:nth-child(2) > font > a")).getText();
	  System.out.println(StrEmbarque);
	  
	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  
	  //********************************************************************************************************************************************
	  
	  //********************************************************************************************************************************************
	  //	 	Caso de teste - ICS - 0006 - Verificar associa��o de Motorista e Carro e Finalizar o Embarque
	  //
	  //Acessar Embarque e Verificar se o combo Busca e o campo buscar est�o sendo apresentado
	  WebElement mainMenu5 = driver.findElement(By.linkText("Opera��es"));
	  action.moveToElement(mainMenu5).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem7\"]"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  if(isElementPresent(By.name("selCodViaConhe"))) {
		  System.out.println("Combo Busca Apresentado com Sucesso");
	   }
	  
	  else {
		  	 System.out.println("Combo Busca N�o Apresentado");
	   }
	  
	  if(isElementPresent(By.name("txtDescricao"))) {
		  System.out.println("Campo Buscar Apresentado com Sucesso");
	   }
	  
	  else {
		  	 System.out.println("Campo Buscar N�o Apresentado");
	   }
	  
	  Thread.sleep(1000);
	  
	  //Preencher o combo Buscae o campo Descri��o e clicar no bot�o Filtrar
	  new Select(driver.findElement(By.name("selCodViaConhe"))).selectByVisibleText("# Embarque");
	  driver.findElement(By.name("txtDescricao")).sendKeys(StrEmbarque);
	  driver.findElement(By.xpath("//*[@id=\"filtro\"]/tbody/tr/td/input[5]")).click();
	  Thread.sleep(2000);
	  
	  //Verificar se o N�mero do Embarque foi apresentado
	  String StrRotVlr2 = driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_escura > td:nth-child(1)")).getText();
	  System.out.println(StrRotVlr2);
	  assertTrue(driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_escura > td:nth-child(1)")).isDisplayed());
	  
	  //Clicar no n�mero do Embarque
	  driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[2]/td[1]/a")).click();
	  Thread.sleep(4000);
	  
	  //Verificar apresenta��o da Tela Embarque de Transfer�ncia pelo n�mero do embarque
	  if(isElementPresent(By.xpath("//*[@id=\"sel_mot_id_chosen\"]"))) {
		  System.out.println("Combo Motorista Apresentado com Sucesso");
	   }
	  
	  else {
		  	 System.out.println("Combo Motorista N�o Apresentado");
	   }
	  
	  if(isElementPresent(By.xpath("//*[@id=\"sel_placa_chosen\"]"))) {
		  System.out.println("Combo Ve�culo Apresentado com Sucesso");
	   }
	  
	  else {
		  	 System.out.println("Combo Ve�culo N�o Apresentado");
	   }
	  
	  //Preencher a combo Motorista
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("selecione...")).click();	
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id=\"sel_mot_id_chosen\"]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id=\"sel_mot_id_chosen\"]/div/ul/li[2]")).click();
	  	  
	
	  //Preencher a combo Ve�culo
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("selecione...")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id=\"sel_placa_chosen\"]/div/ul/li[4]")).click();
	  Thread.sleep(1000);
	    
  
	  
	  //Verificar se est� sendo apresentado o Risco ao Embarque
	  assertTrue(driver.findElement(By.id("risco")).isDisplayed());
	  if(isElementPresent(By.id("risco"))) {
		  System.out.println("Risco Apresentado com Sucesso");
	   }
	  
	  else {
		  	 System.out.println("Risco N�o Apresentado");
	   }
	  	  
	  Thread.sleep(1000);
	  
	  //Clicar no Bot�o Marcar como Embarcado
	  driver.findElement(By.name("marcar_embarcado")).click();
	  Thread.sleep(1000);
	
	  //Confirmar Dados
	  driver.switchTo().alert().accept();
	  Thread.sleep(2000);
	  
	  //Verificar mensagem de Enbarcado com sucesso
	  assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > div:nth-child(9)")).isDisplayed());
	  String StrRotVlr4 = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > div:nth-child(9)")).getText();
	  System.out.println(StrRotVlr4);
	  
	  Thread.sleep(3000);
	  
	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  
	  //********************************************************************************************************************************************
	  
	  
	  //********************************************************************************************************************************************
	  //	 							Caso de teste - ICS - 0007 - Verificar recebimento do embarque CD
	  //
	  //
	  //Clicar na op��o Opera��es e na op��o Embarque
	  WebElement mainMenu6 = driver.findElement(By.linkText("Opera��es"));
	  action.moveToElement(mainMenu6).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem7\"]"))).click().build().perform();
	  Thread.sleep(2000);
	  
	  //Verificar se o campo Descri��o est� sendo apresentado
	  assertTrue(driver.findElement(By.name("txtDescricao")).isDisplayed());
	  
	  //Preencher o campo Descri��o com o N�mero do Embarque e clicar no bot�o Filtrar
	  Thread.sleep(2000);
	  driver.findElement(By.name("txtDescricao")).sendKeys(StrEmbarque);
	  driver.findElement(By.xpath("//*[@id=\"filtro\"]/tbody/tr/td/input[5]")).click();
	  
	  //Clicar no n�mero do Embarque
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_escura > td:nth-child(1) > a")).click();
	  
	  //Verificar se o campo Descri��o est� sendo apresentado
	  assertTrue(driver.findElement(By.linkText("Marcar como Recebido")).isDisplayed());
	  if(isElementPresent(By.linkText("Marcar como Recebido"))) {
		  System.out.println("Op��o Marcado como Recebido Apresentado com Sucesso");
	   }
	  
	  else {
		  	 System.out.println("Op��o Marcado como Recebido N�o Apresentado");
	   }
	  
	   //Clicar na op��o Marcado como Recebido
	   Thread.sleep(1000);
	   driver.findElement(By.linkText("Marcar como Recebido")).click();
	   
	   //Validar o Status da Emcomenda como Emarque Recebido no CD: TZS
	   Thread.sleep(2000);
	   String text = driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(8) > font > font")).getText();
	   System.out.println(text);
	   String expectedText = "EMBARQUE RECEBIDO NO CD: TZS";
       Assert.assertEquals(text,expectedText);
	  
       Thread.sleep(2000);
       
       //Fazer Logout no Sistema ICS
 	   driver.findElement(By.linkText("logout")).click();
 	  
 	   Thread.sleep(3000);
 	  
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
 	   assertTrue(driver.findElement(By.name("Submit")).isDisplayed());
 	   if(isElementPresent(By.name("Submit"))) {
 		  System.out.println("Bot�o Entrar Apresentado com Sucesso");
 	    }
 	   else {
 		  	 System.out.println("Bot�o Entrar N�o Apresentado");
 	    }	  
 	  
 	   Thread.sleep(2000);
 	   
 	  gravarVideo.FinalizarGravacao();
	  gravarVideo.IniciarGravacao();
	  
	  //********************************************************************************************************************************************
	
	  
	  //********************************************************************************************************************************************
	  //	 							Caso de teste - ICS - 0008 - Verificar recebimento do embarque - Disribuidor
	  //
	  //
	  //Preencher os campos Usu�rio e Senha e clicar no bot�o Entrar
      	driver.findElement(By.id("username")).clear();
      	driver.findElement(By.id("username")).sendKeys(USUARIOCD);
      	driver.findElement(By.id("pwd")).sendKeys(SENHACD);
      	driver.findElement(By.name("Submit")).click();
      	Thread.sleep(2000);
		  
      	//Verificar se est� sendo exibido os dados de usu�rio que acessou o sistema
      	assertTrue(driver.findElement(By.xpath("//*[@id=\"header_dados\"]/b")).isDisplayed());
	  
      	//Clicar na op��o Opera��es e na op��o Embarque
      	WebElement mainMenu7 = driver.findElement(By.linkText("Opera��es"));
      	action.moveToElement(mainMenu7).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem3\"]"))).click().build().perform();
      	Thread.sleep(2000);
	  
      	//Limpar e Preencher o campo Origem, Descri��o e clicar no bot�o Filtrar
      	driver.findElement(By.id("origem")).clear();
      	driver.findElement(By.id("origem")).sendKeys(ORIGEM);
      	driver.findElement(By.id("txtDescricao")).sendKeys(StrEmbarque);
      	driver.findElement(By.xpath("//input[@value='Filtrar']")).click();
	  
      	Thread.sleep(2000);
      	
      	//Verificar se est� apresentando o bot�o Receber
      	assertTrue(driver.findElement(By.xpath("//input[@value='Receber']")).isDisplayed());
   	   	if(isElementPresent(By.xpath("//input[@value='Receber']"))) {
   		  System.out.println("Bot�o Receber Apresentado com Sucesso");
   	   		}
   	   	else {
   		  	 System.out.println("Bot�o Receber N�o Apresentado");
   	   	}
   	     	   	
   	   	//Clicar no bot�o Receber
   	   	driver.findElement(By.xpath("//input[@value='Receber']")).click();
	   	Thread.sleep(4000);
	  
      	//Verificar se est� apresentando o campo N AWB (Lacre) e clicar bot�o Receber
	   	assertTrue(driver.findElement(By.id("scanLacreAwbMasterVolume")).isDisplayed());
   	   	if(isElementPresent(By.id("scanLacreAwbMasterVolume"))) {
   		  System.out.println("Campo N AWB (Lacre) Apresentado com Sucesso");
   	   		}
   	   	else {
   		  	 System.out.println("Campo N AWB (Lacre) N�o Apresentado");
   	   	}
   	   	assertTrue(driver.findElement(By.id("btn-awb-master-receber")).isDisplayed());
	   	if(isElementPresent(By.id("btn-awb-master-receber"))) {
		  System.out.println("Bot�o Receber Apresentado com Sucesso");
	   		}
	   	else {
		  	 System.out.println("Bot�o Receber N�o Apresentado");
	   	}
      	
	   	Thread.sleep(1000);
	   	
	   	
	   	//Preenhcer o campo N AWB (Lacre) e clicar no bot�o Receber
	   	driver.findElement(By.id("scanLacreAwbMasterVolume")).sendKeys(LACRE);
	   	driver.findElement(By.id("btn-awb-master-receber")).click();
	   	Thread.sleep(1000);
	   	
	   	
	   	//Verificar a mensagem de Embarque recebido com sucesso
	   	assertTrue(driver.findElement(By.xpath("//*[@id=\"alerta-retorno\"]")).isDisplayed());
      	
	   	//Clicar no bot�o Encerrar Recebimento das AWB Masters
	   	Thread.sleep(1000);
	    driver.findElement(By.id("btn-awb-master-encerrar")).click();
	    
	    //Clicar no bo�o Finalizar
	    Thread.sleep(1000);
	    driver.findElement(By.id("btn-awb-master-finalizar-success")).click();
	    
	    //Clicar no bot�o Encerrar Recebimento das AWB Masters
	    Thread.sleep(1000);
	    driver.findElement(By.id("btn-awb-encerrar-encomenda")).click();
	    
	    //Verificar apresenta��o do Recebimento de AWBs
	    Thread.sleep(2000);
	    assertTrue(driver.findElement(By.xpath("//*[@id=\"awb-error-ocorrencias\"]")).isDisplayed());
	    String ocor = driver.findElement(By.xpath("//*[@id=\"awb-error-ocorrencias\"]")).getText();
		System.out.println(ocor);
	    
	    //Clicar no bot�o Finalizar Recebimento de AWBs
		Thread.sleep(1000);
		driver.findElement(By.id("btn-awb-finalizar-error")).click();
	  
		//Verificar WizardHist�rico
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.id("wizardHistorico")).isDisplayed());
		if(isElementPresent(By.id("wizardHistorico"))) {
			  System.out.println("Wizard Hist�rico Apresentado com Sucesso");
		   		}
		   	else {
			  	 System.out.println("Wizard Hist�rico N�o Apresentado");
		   	}
		Thread.sleep(1000);
		
	    String hist = driver.findElement(By.cssSelector("#wizardHistorico > tr:nth-child(2) > td:nth-child(1)")).getText();
		System.out.println(hist);
		
		String hist1 = driver.findElement(By.cssSelector("#wizardHistorico > tr:nth-child(2) > td:nth-child(3)")).getText();
		System.out.println(hist1);
		
		String hist2 = driver.findElement(By.cssSelector("#wizardHistorico > tr:nth-child(2) > td:nth-child(5)")).getText();
		System.out.println(hist2);
		
      	
		Thread.sleep(2000);
      	
	 	gravarVideo.FinalizarGravacao();
		gravarVideo.IniciarGravacao();
		  
		//********************************************************************************************************************************************
		
		
		//********************************************************************************************************************************************
		//	 							Caso de teste - ICS - 0009 - Verificar cadastro da CAF (Rota Destinat�rio)
		//
		//
		//Clicar na op��o Opera��es e na op��o CAF
		WebElement mainMenu8 = driver.findElement(By.linkText("Opera��es"));
		action.moveToElement(mainMenu8).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem4\"]"))).click().build().perform();
		Thread.sleep(2000);
	    
		//Verificar se apresentou a aop��o Cadastrar Nova C.A.F
		assertTrue(driver.findElement(By.linkText("Cadastrar Nova C.A.F.")).isDisplayed());
		Thread.sleep(1000);
	    String tcaf = driver.findElement(By.linkText("Cadastrar Nova C.A.F.")).getText();
		System.out.println(tcaf);
		
		//Clicar na op��o Cadastrar Nova C.A.F
		driver.findElement(By.linkText("Cadastrar Nova C.A.F.")).click();
		Thread.sleep(2000);
		
		//Preencher a combo Motorista
		driver.findElement(By.linkText("Selecione")).click();	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"sel_mot_id_chosen\"]/div/ul/li[9]")).click();
		  
		//Preencher a combo Ve�culo
		Thread.sleep(1000);
		driver.findElement(By.linkText("Selecione")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"sel_vei_id_chosen\"]/div/ul/li[4]")).click();
		Thread.sleep(2000);
		
		//Clicar no bot�o Prosseguir
		driver.findElement(By.xpath("//table[@id='borda']/tbody/tr/td/div/form/table/tbody/tr[7]/td/div/font/a/b")).click();
		
		//Preencher o campo AWB e clicar no bot�o Prosseguir
		Thread.sleep(1000);
		driver.findElement(By.name("awb")).sendKeys(StrEAWB);
	    driver.findElement(By.xpath("//table[@id='borda']/tbody/tr/td/div/form/table/tbody/tr[8]/td/div/a/b")).click();
	    
	    //Clicar no bot�o Finalizar
	    Thread.sleep(1000);
	    driver.findElement(By.linkText("Finalizar")).click();
	    
	    //Preencher o campo TERMO e clicar no bot�o Buscar Encomenda
	    Thread.sleep(2000);
	    driver.findElement(By.id("searchterm")).sendKeys(StrEAWB);
	    driver.findElement(By.name("Submit")).click();
		
	    //Clicar na AWB
	    Thread.sleep(1000);
	    driver.findElement(By.linkText(StrEAWB)).click();
	    
	    //Verificar n�mero da CAF e gravar numa vari�vel
	    Thread.sleep(1000);
	    String ncaf = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(13) > td:nth-child(2) > font:nth-child(2) > a")).getText();
		System.out.println(ncaf);
		Thread.sleep(1000);
		
		//Acessar a tela de CAF
		WebElement mainMenu9 = driver.findElement(By.linkText("Opera��es"));
		action.moveToElement(mainMenu9).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem4\"]"))).click().build().perform();
		Thread.sleep(1000);			
		
	    //Pesquisar CAF
		driver.findElement(By.name("caf")).sendKeys(ncaf);
		driver.findElement(By.name("filtrar")).click();
		driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[2]/td[1]/font/a")).click();
		Thread.sleep(2000);
		
	    //Clicar na op��o Marcar Sa�da pra Rua
	    assertTrue(driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/table/tbody/tr[2]/td[2]/div/a[1]")).isDisplayed());
	    driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/table/tbody/tr[2]/td[2]/div/a[1]")).click();
	    Thread.sleep(3000);
	    
	    //Verificar apresenta��o da janela Controle Sa�da Pra Rua com os bot�es Sim e N�o
	    assertTrue(driver.findElement(By.id("nyroModalContent")).isDisplayed());
	    assertTrue(driver.findElement(By.id("btn-sim")).isDisplayed());
	    assertTrue(driver.findElement(By.id("btn-nao")).isDisplayed());
	    
	    //Clicar na op��o Sim
	    driver.findElement(By.id("btn-sim")).click();
	    Thread.sleep(1000);
	    
	    //Verificar apresenta��o da mensagem de Aviso! Sa�da para Rua realizada
	    assertTrue(driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/div[1]")).isDisplayed());
	    String aviso = driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > div:nth-child(12) > div")).getText();
		System.out.println(aviso);
		
	    	    
		Thread.sleep(4000);
		
		gravarVideo.FinalizarGravacao();
		
		//********************************************************************************************************************************************
		
		
		//********************************************************************************************************************************************
		//	 							Caso de teste - ICS - 0010 - Verificar baixa da CAF (Formaliza��o da Entrega)
		//
		//
		//Clicar na op��o Opera��es e na op��o Baixar CAF
		WebElement mainMenu10 = driver.findElement(By.linkText("Opera��es"));
		action.moveToElement(mainMenu10).moveToElement(driver.findElement(By.xpath("//*[@id=\"dhtmlgoodies_listItem6\"]"))).click().build().perform();
		Thread.sleep(2000);
		
		//Verificar apresenta��o do campo N�mero da C.A.F e o bot�o Prosseguir
		assertTrue(driver.findElement(By.name("numcaf")).isDisplayed());
		if(isElementPresent(By.name("numcaf"))) {
			  System.out.println("Campo N�mero da CAF Apresentado com Sucesso");
		   		}
		else {
			  	 System.out.println("Campo N�mero da CAF N�o Apresentado");
		   	     }
		assertTrue(driver.findElement(By.name("Button")).isDisplayed());
		if(isElementPresent(By.name("Button"))) {
			  System.out.println("Bot�o Prosseguir Apresentado com Sucesso");
		   		}
		else {
			  	 System.out.println("Bot�o Prosseguir N�o Apresentado");
		   	     }
		
		//Preenhcer o campo N�mero da C.A.F e clicar no bot�o Prosseguir
		driver.findElement(By.name("numcaf")).sendKeys(ncaf);
		driver.findElement(By.name("Button")).click();
		
		Thread.sleep(2000);
		
		//Verificar o campo Total com N�o-Conformidades e o bot�o Prosseguir
		assertTrue(driver.findElement(By.name("nc_baixar")).isDisplayed());
		if(isElementPresent(By.name("nc_baixar"))) {
			  System.out.println("campo Total com N�o-Conformidades Apresentado com Sucesso");
		   		}
		else {
			  	 System.out.println("campo Total com N�o-Conformidades N�o Apresentado");
		   	     }
		assertTrue(driver.findElement(By.name("bsubmit")).isDisplayed());
		if(isElementPresent(By.name("bsubmit"))) {
			  System.out.println("Bot�o Prosseguir Apresentado com Sucesso");
		   		}
		else {
			  	 System.out.println("Bot�o Prosseguir N�o Apresentado");
		   	     }
		Thread.sleep(5000);
		
		//Preenhcer o campo Total com N�o-Conformidades e clicar no bot�o Prosseguir
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("return document.getElementById('nc_baixar').value='0';");
		
		
		Thread.sleep(3000);
		//driver.findElement(By.name("nc_baixar")).click();
	    driver.findElement(By.xpath("//tr[5]/td[2]/input")).click();
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(TOTNC);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/form/table/tbody/tr[2]/td/div/input")).click();
		
		//Verificar apresenta�� da Mensagem Baixar Entregas Realizadas Automaticamente?
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#borda > tbody > tr > td > div > div")).isDisplayed();
		
		//Clicar no bot�o N�o
		driver.findElement(By.xpath("//*[@id=\"borda\"]/tbody/tr/td/div/div/button[2]")).click();
		
		//Preencher o campo Data, Hora e N�mero da AWB
		driver.findElement(By.name("data_baixa")).sendKeys(DATATUAL);
		driver.findElement(By.name("hora_baixa")).sendKeys(HORATUAL);
		driver.findElement(By.name("scanawb")).sendKeys(StrEAWB);
		driver.findElement(By.name("bSubmit")).click();
		
		Thread.sleep(2000);
		
		//Confirma Encerramento da CAF com sucesso
		driver.switchTo().alert().accept();
		
		Thread.sleep(3000);
		//********************************************************************************************************************************************
		
		
		//********************************************************************************************************************************************
		//	 							Caso de teste - ICS - 0010 - Consultar Status AWB
		//
		//
		//Preencher o combo Termo, campo Descri��o e clicar no bot�o Buscar Encomenda
		new Select(driver.findElement(By.name("term"))).selectByVisibleText("AWB");
		driver.findElement(By.name("searchterm")).sendKeys(StrEAWB);
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(2000);
		  
		//Verificar o n�mero da AWB est� sendo apresentado
		String StrRotVlr5 = driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(4)")).getText();
		System.out.println(StrRotVlr5);
		assertTrue(driver.findElement(By.cssSelector("#tabela > tbody > tr.cor_clara > td:nth-child(4)")).isDisplayed());
		 
		//Clicar no n�mero da AWB
		driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[2]/td[4]/a")).click();
		  
		//Verificar apresenta��o da Tela Embarque de Transfer�ncia pelo n�mero do embarque
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
		  
		//Verifica Status IN�CIO DE COLETA C/ N�O CONFORMIDADE
		assertTrue(driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(4) > td:nth-child(2)")).isDisplayed());
		String StrRotVlr10 = driver.findElement(By.cssSelector("#borda > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		System.out.println(StrRotVlr10);
		  		  
		//Verifica Status COLETA REALIZADA C/ N�O CONFORMIDADE
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