//package com.datayes.testapp.testchrome;
//
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class MsgBox {
//	private int i;
//	WebDriver driver=new ChromeDriver(); 
//	public int getI() {
//		return i;
//	}
//
//	public void setI(int i) {
//		this.i = i;
//	}
//
//	public WebElement getMsg()
//	{
//		WebElement msgs = driver.findElement(By.cssSelector("div.snsMsgbox"));
//		List<WebElement> msg = msgs.findElements(By.cssSelector("div.infoFlowBox"));
//		System.out.println(msg.size());
//		return msg.get(i);
//	}
//	
//	
	
	  
//	String name = msg.get(0).findElement(By.cssSelector("div.snsInfoName h3")).getText();
//	String time = msg.get(0).findElement(By.cssSelector("div.snsInfoName p")).getText();
//	String text;
//	try{
//	text = msg.get(0).findElement(By.cssSelector("p.snsCardCnt-p")).getText();
//	}catch(Exception e){
//		  text="";
//	}
//	System.out.println(name+"  "+time+"  "+text);
//
//}
