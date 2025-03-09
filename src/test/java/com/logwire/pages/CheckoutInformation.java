package com.logwire.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutInformation {
    
    WebDriver driver;
    By firstnameBy  = By.name("firstName");
    By lastnameBy  = By.name("lastName"); 
    By codepostalBy  = By.name("postalCode");   
    By continueButton = By.name("continue");
   
    
  
    public  CheckoutInformation (WebDriver driver) {
        this.driver = driver ;
    }

    public void saisirFirstname (String firstname ){
    WebElement firstnameByElement = driver.findElement(firstnameBy); 
    firstnameByElement.sendKeys(firstname);  
    }

    public void saisirLasttname ( String lastname ){
    WebElement lastnameByElement = driver.findElement(lastnameBy);
    lastnameByElement.sendKeys(lastname);

    }

    public void saisirCodepostal( String codepostal ){
    WebElement codepostalByElement = driver.findElement(codepostalBy);
    codepostalByElement.sendKeys(codepostal);
    
        }

    public void cliqueSurBoutonContinuer (){
    WebElement continueButtonElement = driver.findElement(continueButton);
    continueButtonElement.click();
    
    
        }
        
    }
