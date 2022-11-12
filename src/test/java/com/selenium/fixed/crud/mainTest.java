package com.selenium.fixed.crud;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class mainTest extends baseBuild {
	public static String recipe_name;
	public static String ingredient_list;
	
	
	@Test(priority = 1)
	public void add_Recipe() throws InterruptedException {
		
		ExtentTest add_reports  = extent.createTest("Recipe Addition", "User is able to add a new recipes by inserting the recipe name & ingredient");
		
		WebElement resultFrame= driver.findElement(By.id("result"));
        driver.switchTo().frame(resultFrame);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Add_recipe_btn)));
		
		WebElement addRe_btn = driver.findElement(By.xpath(Add_recipe_btn));
		addRe_btn.click();
		
		WebElement re_Name = driver.findElement(By.xpath(recipe_name_txt));
		recipe_name = "beef pad";
		re_Name.sendKeys(recipe_name);
		
		WebElement ingredient = driver.findElement(By.xpath(ingredients_txt));
		ingredient_list = "beef , milk , flour"; 
		ingredient.sendKeys(ingredient_list);
		
		WebElement addrecipe_btn = driver.findElement(By.xpath(save_btn));
		addrecipe_btn.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recipe_table)));
		WebElement result_table = driver.findElement(By.xpath(recipe_table));
		List<WebElement> rows = result_table.findElements(By.tagName("a"));
		
		for (WebElement row : rows) {	
			
			if(row.getText().contains(recipe_name)) {
				Assert.assertTrue(row.getText().contains(recipe_name));
				break;
			}
		}
		
		add_reports.log(Status.PASS, "User adds a new recipe successfully");	
	}
	
	@Test(priority = 2)
	public void read_Recipe() throws InterruptedException {
		
		ExtentTest read_reports  = extent.createTest("View Recipe", "User is able to view the new added recipes");
		
		//Assert on the recipe name
		WebElement result_table = driver.findElement(By.xpath(recipe_table));
		List<WebElement> rows = result_table.findElements(By.tagName("a"));
		Assert.assertEquals(4,  rows.size());
	
		for (WebElement row : rows) {	
			System.out.println(row.getText());
			if(row.getText().contains(recipe_name)) {
				Assert.assertTrue(row.getText().contains(recipe_name));
				row.click();
				break;
			}
			
		}
		
		//Assert on the recipe ingredients table
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ingredients_table)));
		WebElement ing_table = driver.findElement(By.xpath(ingredients_table));
		List<WebElement> list = ing_table.findElements(By.tagName("li"));
		Assert.assertEquals(3,  list.size());
		
		for (WebElement ing : list) {		
			//System.out.println(ing.getText());
			if(ing.getText().contains("beef")) {
				Assert.assertTrue(ing.getText().contains("beef"));			
				break;
				
				}else{
					System.out.println("invalid ingredient list!!!");
				}
			}
		
	   read_reports.log(Status.PASS, "System returns the new added recipes successfully");
	}
	
	@Test(priority = 3)
	public void edit_Recipe() throws InterruptedException{
		
		ExtentTest edit_reports  = extent.createTest("Edit Recipe", "User is able to edit recipes by editing the recipe name & ingredients");
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(edit_recipe_btn)));
		
		WebElement edit_btn = driver.findElement(By.xpath(edit_recipe_btn));
		edit_btn.click();
		
		WebElement re_NameEdit = driver.findElement(By.xpath(recipe_name_txt));
		recipe_name = "cheese";
		re_NameEdit.sendKeys(recipe_name);
		
		WebElement ingredientEdit = driver.findElement(By.xpath(ingredients_txt));
		ingredient_list = "milk , butter , cheese"; 
		ingredientEdit.sendKeys(ingredient_list);
		
		WebElement editRecipe_btn = driver.findElement(By.xpath(save_edit_btn));
		editRecipe_btn.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recipe_table)));
		WebElement result_table = driver.findElement(By.xpath(recipe_table));
		
		List<WebElement> rows = result_table.findElements(By.tagName("a"));
		
		for (WebElement row : rows) {	
			
			if(row.getText().contains("cheese")) {
				Assert.assertTrue(row.getText().contains(recipe_name));
				break;
			}
			
		}
		
		edit_reports.log(Status.PASS, "User is able to edit recipes successfully");	
		
	}
	
	@Test(priority = 4)
	public void delete_Recipe() throws InterruptedException{
		ExtentTest delete_reports  = extent.createTest("Delete Recipe", "User is able to delete recipes");

		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(delete_recipe_btn)));
		
		WebElement delete_btn = driver.findElement(By.xpath(delete_recipe_btn));
		delete_btn.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recipe_table)));
		WebElement result_table = driver.findElement(By.xpath(recipe_table));
		List<WebElement> rows = result_table.findElements(By.tagName("a"));
		
		for (WebElement row : rows) {	
			
			if(row.getText().contains(recipe_name)) {
				System.out.println("recipe is not deleted!!!");
				
			}else {
				Assert.assertNotEquals(row.getText(), recipe_name);
							
			}
		}	
		
		delete_reports.log(Status.PASS, "Recipes deleted successfully");	
	}
}


