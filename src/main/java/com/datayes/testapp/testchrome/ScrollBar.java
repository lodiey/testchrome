package com.datayes.testapp.testchrome;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ScrollBar {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
		String chromedriverpath = "V:\\workspace\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		// System.setProperty("webdriver.ie.driver",
		// "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	}

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--start-maximized"); //maximized
		driver.manage().window().maximize();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver = new FirefoxDriver();
		// baseUrl = "http://www.baidu.com";
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		((JavascriptExecutor) driver)
				.executeScript("document.documentElement.scrollTop=10000");
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

	public static void wait(int millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("中断异常");
		}
	}

}
