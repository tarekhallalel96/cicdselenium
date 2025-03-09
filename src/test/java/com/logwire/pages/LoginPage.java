package com.logwire.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    
    WebDriver driver;
    By usernameBy  = By.id("user-name");
    By passwordBy  = By.name("password");    
    By loginButton = By.name("login-button");
   
    
  
    public  LoginPage (WebDriver driver) {
        this.driver = driver ;
    }

    public void saisirUsername (String username ){
    WebElement usernameByElement = driver.findElement(usernameBy); 
    usernameByElement.sendKeys(username);  
    }

    public void saisirPassword ( String password ){
    WebElement passwordByElement = driver.findElement(passwordBy);
    passwordByElement.sendKeys(password);

    }
    public void cliqueSurBoutonLogin (){
    WebElement loginButtonElement = driver.findElement(loginButton);
    loginButtonElement.click();
    
    
        }
        
    }
