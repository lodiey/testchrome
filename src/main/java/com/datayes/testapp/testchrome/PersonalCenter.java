package com.datayes.testapp.testchrome;

import static org.junit.Assert.fail;

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
import org.openqa.selenium.remote.DesiredCapabilities;

public class PersonalCenter {
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
		 waitTimeT(3);
		 
		 //个人信息修改
		 String name="赵颖";
		 String englishName="Annie";
		 String positon="测试开发工程师";
		 String team="工程技术团队";
		 String sex="female";
		 String birthday="1989-6-11";
		 String address="陆家嘴西路99号";
		 String phone="60216592";
		 String mphone="13131313131";
		 String userRemark="one";
		 
		 
		 //姓名修改
		 driver.findElement(By.cssSelector("#head div.ush > img")).click();
		 driver.findElement(By.cssSelector("#head > div.hTop > div > div.hTop-userArea > div.ush > ul > li:nth-child(1) > a")).click();
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(1) > input:nth-child(1)")).clear();
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(1) > input:nth-child(1)")).sendKeys(name);
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(1) > input:nth-child(2)")).clear();
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(1) > input:nth-child(2)")).sendKeys(englishName);

         //团队修改
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(2) > input")).clear();
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(2) > input")).sendKeys(positon);
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(2) > div > p > input[type='text']")).click();
		 
		 WebElement selectTeam=driver.findElement(By.cssSelector("#account_info > dl:nth-child(2) > div > ul"));
		 List<WebElement> allTeams=selectTeam.findElements(By.tagName("li"));
		 for(int i=0;i<allTeams.size();i++)
		 {
			 if (allTeams.get(i).getText().equals(team)) {
				 allTeams.get(i).click();
			}
		 }
		 
		 //性别修改
		 driver.findElement(By.cssSelector("#account_info > dl:nth-child(3) > div.select.w100 > p > input[type='text']")).click();
		 WebElement selectSex=driver.findElement(By.cssSelector("#account_info > dl:nth-child(3) > div.select.w100 > ul"));
		 List<WebElement> allSex=selectSex.findElements(By.tagName("li"));
		 for (int i = 0; i < allSex.size(); i++) {
			 if (allSex.get(i).getText().equals(sex)) {
				allSex.get(i).click();
			}
		}
		 
		 //修改生日
		 driver.findElement(By.name("birthday")).clear();
		 driver.findElement(By.name("birthday")).sendKeys(birthday);
		 
		 //修改地址,电话,个人简介
		 driver.findElement(By.name("address")).clear();
		 driver.findElement(By.name("address")).sendKeys(address);
		 driver.findElement(By.name("phone")).clear();
		 driver.findElement(By.name("phone")).sendKeys(phone);
		 driver.findElement(By.name("mphone")).clear();
		 driver.findElement(By.name("mphone")).sendKeys(mphone);
		 driver.findElement(By.id("userRemark")).clear();
		 driver.findElement(By.id("userRemark")).click();
		 driver.findElement(By.id("userRemark")).sendKeys(userRemark);
		 
		 //save
		 driver.findElement(By.id("info_save")).click();
		 
		 //验证
		 driver.findElement(By.linkText("我的主页")).click();
		 boolean nameP=driver.findElement(By.cssSelector("#content > div > div > div.prsnlTop > div > h2")).getText().equals(name);
		 boolean englishNameP=driver.findElement(By.cssSelector("#content > div > div > div.prsnlTop > div > h3")).getText().equals(englishName);
		 boolean positonP=driver.findElement(By.cssSelector("#content > div > div > div.prsnlTop > div > h4")).getText().equals(positon);
		 boolean teamP=driver.findElement(By.cssSelector("#content > div > div > div.prsnlTop > div > h5")).getText().equals(team);
		 boolean sexP=driver.findElement(By.cssSelector("div.prsnlInfo.fr > div > dl:nth-child(1) > div")).getText().equals(sex);
		 boolean birthdayP=driver.findElement(By.cssSelector("div.prsnlInfo.fr > div > dl:nth-child(2) > div")).getText().equals(birthday);
		 boolean addressP=driver.findElement(By.cssSelector("div.prsnlInfo.fr > div > dl:nth-child(4) > div")).getText().equals(address);
		 boolean phoneP=driver.findElement(By.cssSelector("div.prsnlInfo.fr > div > dl:nth-child(5) > div")).getText().equals(phone);
		 boolean mphoneP=driver.findElement(By.cssSelector("div.prsnlInfo.fr > div > dl:nth-child(6) > div")).getText().equals(mphone);
		 boolean userRemarkP=driver.findElement(By.cssSelector("div.prsnlInfo.fr > div > dl:nth-child(7) > div")).getText().equals(userRemark);
		 
		 System.out.println("姓名修改成功:"+nameP);
		 System.out.println("英文名修改成功:"+englishNameP);
		 System.out.println("职位修改成功:"+positonP);
		 System.out.println("团队修改成功:"+teamP);
		 System.out.println("性别修改成功:"+sexP);
		 System.out.println("生日修改成功:"+birthdayP);
		 System.out.println("地址修改成功:"+addressP);
		 System.out.println("电话修改成功:"+phoneP);
		 System.out.println("手机修改成功:"+mphoneP);
		 System.out.println("个人简介修改成功:"+userRemarkP);
		 
		 
		 
		 
		 
		 
		 
		 
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
