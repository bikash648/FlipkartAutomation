package com.flipkart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[2]/div/form/div[1]/input")
	WebElement username;
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[2]/div/form/div[2]/input")
	WebElement password;
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[2]/div/form/div[4]/button")
	WebElement loginButton;

	public void logInToFlipkart(String uid, String pws) {
		username.sendKeys(uid);
		password.sendKeys(pws);
		loginButton.click();
	}
}
