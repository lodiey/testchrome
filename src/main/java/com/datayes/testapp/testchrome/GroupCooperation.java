package com.datayes.testapp.testchrome;

import static org.junit.Assert.fail;

import java.awt.Desktop.Action;
import java.io.BufferedReader;
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
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GroupCooperation {
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
	 public void test()
	 {
		 driver.get("https://sns01-dev.datayes.com:8443/crm/dy_index.action");
	     driver.findElement(By.id("account")).clear();
		 driver.findElement(By.id("account")).sendKeys("ying.zhao@datayes.com");
		 driver.findElement(By.name("password")).clear();
		 driver.findElement(By.name("password")).sendKeys("datayes@123");
		 driver.findElement(By.id("btnLogin")).click();
		 wait(2000);
		 
		 //创建私有组并操作
		 String teamName="测试测试";
		 driver.findElement(By.cssSelector("#hNav > div > ul > li:nth-child(2) > a")).click();
		 driver.findElement(By.cssSelector("div.orangeBtn.createNewGroup")).click();
		 driver.findElement(By.name("groupName")).sendKeys(teamName);
		 driver.findElement(By.cssSelector("div.creatLayer > div:nth-child(2) > div:nth-child(2) > p:nth-child(2) > input[type='radio']")).click();
		 driver.findElement(By.name("intro")).sendKeys(teamName);
		 driver.findElement(By.cssSelector("div.cnmAncCtr > div")).click();
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //邀请同事加入
	     driver.findElement(By.cssSelector("#content > div > div.idxLeft > div > ul > li:nth-child(3) > a")).click();	 
		 driver.findElement(By.linkText(teamName)).click();
		 driver.findElement(By.cssSelector("div.groupButtons > div")).click();
		 driver.findElement(By.cssSelector("div.creatLayer > div > ul > li > input")).sendKeys("林紫嫣"+" 张纪锋1");
		 driver.findElement(By.cssSelector("#content > div > div > div.modifyGroup > div.diaDataCon.creatCon > div > div > div.btnBlue.cnmAncBtn")).click();
		 
		 //管理小组
		 driver.findElement(By.cssSelector("#content > div > div > div.groupContain > div.groupButtons > div")).click();//返回小组
		 //查找成员是否存在
		 driver.findElement(By.cssSelector("div.groupIntro > div.groupInfo > div.groupHead > div.radiusButton.editGroup")).click(); //点击管理小组
		 
		 
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
