package com.selenium.fixed.crud;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class baseBuild extends variables {
	ExtentReports extent;
	ChromeDriver driver ;
	
	
	@BeforeSuite
	public void globalSetup() throws InterruptedException{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		//Create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test = extent.createTest("Navigate to the URL", "Navigate to codepen.io");
		
		String chromedriverPath = System.getProperty("user.dir")+"\\recourse\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromedriverPath);
		driver = new ChromeDriver();		
		driver.navigate().to(url);
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(change_view_btn)));
		
		WebElement changeView_mode = driver.findElement(By.xpath(change_view_btn));
		changeView_mode.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FullPage_btn)));
		WebElement Fullpage_mode = driver.findElement(By.xpath(FullPage_btn));
		Fullpage_mode.click();
		
		test.log(Status.PASS, "Navigated Successfully");	
	}
	
	@AfterSuite
	public void globalTearDown () {
		extent.flush();
		driver.close();
	}	
}
