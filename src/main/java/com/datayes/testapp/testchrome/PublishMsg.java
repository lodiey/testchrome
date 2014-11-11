package com.datayes.testapp.testchrome;

import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PublishMsg {
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
	// 通联云平台发布信息
	public void test1() throws Exception {
		// driver.get("https://sns01-dev.datayes.com:8443/crm/dy_index.action");
		driver.get("https://cloud-as-stg.datayes.com/sns");
		System.out.println("\"" + driver.getTitle() + "\"" + "的网址是："
				+ driver.getCurrentUrl() + ",句柄是" + driver.getWindowHandle());
		driver.findElement(By.id("account")).clear();
		driver.findElement(By.id("account")).sendKeys("ying.zhao@datayes.com");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("datayes@123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.id("newMssg")).sendKeys("selenium测试!@#$%^&*()_+");

		// 上传图片
		// driver.findElement(By.cssSelector("i.mssgT_img")).click();
		// WebElement
		// fileUpload=driver.findElement(By.id("autoInput1400811222286963200119"));
		// String filePath="V:\\test_point\\测试图片&空文档\\图片\\1.jpg";
		// fileUpload.sendKeys(filePath);
		// wait(2000);
		for (int i = 1; i < 10; i++) {
			String s = "V:\\test_point\\测试图片&文档\\图片\\" + i + ".jpg";
			driver.findElement(By.id("uploadImg")).sendKeys(s);
		}

		// 上传文档
		for (int i = 1; i < 6; i++) {
			String s = "V:\\test_point\\测试图片&文档\\文档\\" + i + ".docx";
			driver.findElement(By.id("uploadFile")).sendKeys(s);
		}
		for (int i = 1; i < 3; i++) {
			String s = "V:\\test_point\\测试图片&文档\\文档\\" + i + ".ppt";
			driver.findElement(By.id("uploadFile")).sendKeys(s);
		}

		// @ someone
		driver.findElement(By.xpath("//div[@id='mssgToolPnl']/div[1]/i[3]"))
				.click();
		driver.findElement(
				By.cssSelector("#mssgToolPnl > div.mssgRsltPnl.mssgGryBox > div.mssgAtPnl > div > input"))
				.sendKeys("林紫嫣");
		// 回车
		driver.findElement(
				By.cssSelector("#mssgToolPnl > div.mssgRsltPnl.mssgGryBox > div.mssgAtPnl > div > input"))
				.sendKeys(Keys.ENTER);

		// 发表情
		driver.findElement(
				By.cssSelector("div.mssgToolPnl > div.mssgToolbar > i.mssgT_face"))
				.click();
		// driver.findElement(By.xpath("//div[@id='mssgToolPnl']/div[1]/i[4]")).click();
		// driver.findElement(By.cssSelector("div.mssgFacePnl > ul.mssgFace > li:nth-child(11) > span")).click();
		for (int i = 1; i < 21; i++) {
			String s = "div.mssgFacePnl > ul.mssgFace > li:nth-child(" + i
					+ ") > span";
			driver.findElement(By.cssSelector(s)).click();
		}

		// 智能链接
		driver.findElement(By.xpath("//div[@id='mssgToolPnl']/div[1]/i[5]"))
				.click();
		driver.findElement(By.cssSelector("input.urlMssg")).sendKeys(
				"http://www.baidu.com");
		driver.findElement(
				By.cssSelector("#mssgToolPnl > div.mssgRsltPnl.mssgGryBox > div.mssgUrlPnl > span"))
				.click();
		// wait(2000);

		// 发布
		driver.findElement(
				By.cssSelector("#mssgToolPnl > div.mssgToolbar > span"))
				.click();

		// // 验证
		// // wait(2000);
		// WebElement webElement = driver.findElement(By
		// .cssSelector("div.snsMsgbox > div:nth-child(1)"));
		//
		// // 验证文字
		// System.out
		// .println("文字是否显示："
		// + webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsInfoTxt > p"))
		// .getText().contains("selenium测试!@#$%^&*()_+"));
		//
		// // 验证网页抽取
		// System.out
		// .println("网页是否显示："
		// + webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.share-link.intelligence-link.clearfix > div.share-link-con > p:nth-child(1) > a"))
		// .getText().equals("百度一下，你就知道"));
		//
		// // 验证@
		// System.out.println("@是否显示："
		// + webElement.findElement(By.cssSelector("p > a")).getText()
		// .equals("@林紫嫣"));
		//
		// // 验证Attribute
		// System.out.println("Attribute为："
		// + webElement.findElement(By.cssSelector("div.snsInfoR > img"))
		// .getAttribute("src"));

		// String
		// text=driver.findElement(By.cssSelector("div.infoFlow > div.snsMsgbox > div:nth-child(1) > div.snsInfoCnt > div.snsInfoTxt > p")).getText();
		// System.out.println(text);

		// 验证表情
		// MsgBox mesBox=new MsgBox();
		// mesBox.setI(1);
		// System.out.println(mesBox.getI());
		// System.out.println(mesBox.getMsg(1).getSize());
		// WebElement emotion =
		// mesBox.getMsg().findElement(By.cssSelector("span.emoji.datayes-emoji-24.datayes-emoji-24-bye"));

		// WebElement emotion =
		// driver.findElement(By.cssSelector("div.snsMsgbox > div:nth-child(1)")).findElement(By.cssSelector("span.emoji.datayes-emoji-24.datayes-emoji-24-dizzy"));
		// boolean visibility=emotion.isDisplayed();
		// System.out.println("表情是否显示："+visibility);

		// try {
		// System.out
		// .println("表情是否显示："
		// + webElement
		// .findElement(
		// By.cssSelector("span.emoji.datayes-emoji-24.datayes-emoji-24-bye"))
		// .isDisplayed());
		// } catch (Exception e) {
		// System.out.println("表情是否显示：false");
		// // e.printStackTrace();
		// }
		//
		// // 收藏
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.fav"))
		// .click();
		// String shouCang = webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.fav"))
		// .getCssValue("color");
		// boolean shouCangBool = shouCang.equals("rgba(224, 113, 130, 1)");
		// System.out.println("收藏是否勾选：" + shouCangBool);
		//
		// wait(2000);
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.fav"))
		// .click();
		// String shouCang1 = webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.fav"))
		// .getCssValue("color");
		// boolean shouCangBool1 = shouCang1.equals("rgba(51, 51, 51, 1)");
		// System.out.println("收藏是否取消勾选：" + shouCangBool1);
		//
		// // 赞
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.like"))
		// .click();
		// String zan = webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.like"))
		// .getCssValue("color");
		// boolean zanBool = zan.equals("rgba(83, 137, 210, 1)");
		// System.out.println("赞是否勾选：" + zanBool);
		//
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.like"))
		// .click();
		// String zan1 = webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.like"))
		// .getCssValue("color");
		// boolean zanBool1 = zan1.equals("rgba(51, 51, 51, 1)");
		// System.out.println("赞是否取消勾选：" + zanBool1);
		//
		// // 评论
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.snsInfoCompli > span.lmsg"))
		// .click();
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.commentContainer > div.snsInfoCmmPost > input"))
		// .sendKeys("hello你好");
		// webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCnt > div.snsCmmtContainer > div.commentContainer > div.snsInfoCmmPost > span"))
		// .click();
		// // 验证评论
		// System.out
		// .println("评论是否显示："
		// + webElement
		// .findElement(
		// By.cssSelector("div.snsInfoCmmRcvd > ul > li > div.snsInfoCmmCnt > p"))
		// .getText().equals("hello你好"));
		wait(5000);
		driver.quit();
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
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
