package com.datayes.testapp.testchrome;

import static org.junit.Assert.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DragAndDrop {
	 private WebDriver driver;
     private String baseUrl;
     private boolean acceptNextAlert = true;
	 private StringBuffer verificationErrors = new StringBuffer();
		
	 @BeforeClass
	  public static void setUpBeforeClass() throws Exception {
		// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
		String chromedriverpath="V:\\workspace\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",chromedriverpath);
		//System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		//System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");  
	}
		  
	 @Before
	  public void setUp() throws Exception {
	  driver = new ChromeDriver(); 
	  ChromeOptions options = new ChromeOptions();
	  //options.addArguments("--start-maximized");   //maximized
	  driver.manage().window().maximize();
	  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			 
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //driver = new FirefoxDriver();
      //baseUrl = "http://www.baidu.com";
      //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void test() {
		driver.get("https://sns01-dev.datayes.com:8443/crm/dy_index.action");
		driver.findElement(By.id("account")).clear();
		driver.findElement(By.id("account")).sendKeys("ying.zhao@datayes.com");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("datayes@123");
		driver.findElement(By.id("btnLogin")).click();
		wait(2000);
		
		//Scrolldown
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,25000)","");
//		wait(2000);
		//Scrollup
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,0)","");
//		wait(2000);
		
		//Window Switch
//		driver.get("http://www.baidu.com");
//		driver.get("https://www.google.com.hk");
		
		//拖拽
		driver.findElement(By.cssSelector("#dockOpenBtn > div.dockTopBg > p")).click();
		waitTimeT(3);
		driver.findElement(By.id("dockToolAddBtn")).click();
		WebElement source=driver.findElement(By.cssSelector("#dockList > div:nth-child(1) > div"));
	    //WebElement target=driver.findElement(By.cssSelector("#dockCardPnl > div.dockColumn.fl"));
		//WebElement target=driver.findElement(By.xpath("//*[@id='dockCardPnl']/div[1]"));
		List<WebElement> targetList=driver.findElements(By.cssSelector("#dockCardPnl > div.dockColumn.fl"));
		WebElement target=targetList.get(0);
	    //WebElement target=driver.findElement(By.cssSelector("#dockContainer > div.dockTopBar > div > span.dockCtrl_r"));
		Actions action=new Actions(driver);
		action.clickAndHold(source).moveToElement(target).dragAndDrop(source, target).perform(); 
		waitTimeT(10);
		action.release();
		
		//可以移动但是不能松开
//		action.clickAndHold(source).moveToElement(target).perform();
//		waitTimeT(2);
//		action.release();
		
	}
	
	public void waitTimeT(int sec){
		try {
		long getSec=(long) sec*1000;
			Thread.sleep(getSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	  public void tearDown() throws Exception {
	  //  driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  @AfterClass
		public static void tearDownAfterClass() throws Exception {
			
		}

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

	  public static void wait(int millisec)
		{
			try {
				Thread.sleep(millisec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("中断异常");
			}
		}

}
