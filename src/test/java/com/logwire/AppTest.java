package com.logwire;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.logwire.pages.LoginPage;
import com.logwire.steps.Invontory;
import com.logwire.steps.ProductSortingUtils;
import com.logwire.pages.CheckoutInformation;

/**
 * Unit test for simple App.
 */
@Tag("regression")
@Tag("page1")
public class AppTest {

    WebDriver driver;
    LoginPage loginPage;
    CheckoutInformation checkoutInformation;

    @BeforeEach
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        checkoutInformation = new CheckoutInformation(driver);

    }

    @AfterEach
    public void tearDown() {
        try {
            driver.quit();
        } catch (NoSuchSessionException e) {
            System.out.println("Session already closed or not established");
        }
    }

    @Test
    @Tag("TC-001")
    public void testLoginCorrectPasswordIncorrect() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("incorrect");
        loginPage.cliqueSurBoutonLogin();
        WebElement errormsg = driver.findElement(By.cssSelector(".error-message-container.error"));

        assertTrue(errormsg.isDisplayed());

    }

    @Test
    @Tag("TC-002")
    public void testLogininCorrectPasswordcorrect() {
        loginPage.saisirUsername("exemple");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        WebElement errormsg = driver.findElement(By.cssSelector(".error-message-container.error"));

        assertTrue(errormsg.isDisplayed());

    }

    @Test
    @Tag("TC-003")
    public void testLogininCorrectPasswordIncorrect() {
        loginPage.saisirUsername("exemple");
        loginPage.saisirPassword("exemple");
        loginPage.cliqueSurBoutonLogin();
        WebElement errormsg = driver.findElement(By.cssSelector(".error-message-container.error"));

        assertTrue(errormsg.isDisplayed());

    }

    @Test
    @Tag("TC-004")
    public void testLoginCorrectPasswordcorrect() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        assertTrue(driver.getCurrentUrl().contains("/inventory"));
    }

    @Test
    @Tag("TC-005")
    public void testlistinventory() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item"));
        System.out.println(list);
        assertTrue(driver.getCurrentUrl().contains("/inventory"));

    }

    @Test
    @Tag("TC-006")
    public void verifie_leproduit_ajoute_au_panier() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement messagElement = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));

        messagElement.click();

        driver.get("https://www.saucedemo.com/cart.html");

        List<WebElement> listpanier = driver.findElements(By.cssSelector(".inventory_item"));

        for (WebElement item : listpanier) {
            String title = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            WebElement button = item.findElement(By.cssSelector("btn.btn_secondary.btn_small.cart_button\""));

            String buttonText = button.getText();
            System.out.println(buttonText);
            assertEquals("Sauce Labs Backpack", title);

        }
    }

    @Test
    @Tag("TC-007")
    public void tester_buttonRemove() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement messagElement = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));

        messagElement.click();

        driver.get("https://www.saucedemo.com/cart.html");

        WebElement messagElementRemove = driver.findElement(By.name("remove-sauce-labs-backpack"));

        messagElementRemove.click();

        List<WebElement> listpanier = driver.findElements(By.cssSelector(".inventory_item"));

        for (WebElement item : listpanier) {
            String title = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            WebElement button = item.findElement(By.cssSelector("btn.btn_secondary.btn_small.cart_button\""));

            String buttonText = button.getText();
            System.out.println(buttonText);
            assertEquals("Sauce Labs Backpack", title);

        }

    }

    @Test
    @Tag("TC-008")
    public void tester_button_checkout() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        driver.get("https://www.saucedemo.com/cart.html");

        WebElement messagElement = driver.findElement(By.name("checkout"));

        messagElement.click();
        String url = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", url);

    }

    @Test
    @Tag("TC-009")
    public void tester_formulaire_checkout() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        checkoutInformation.saisirFirstname("tarek");
        checkoutInformation.saisirLasttname("hallalel");
        checkoutInformation.saisirCodepostal("paris");
        checkoutInformation.cliqueSurBoutonContinuer();
        assertTrue(driver.getCurrentUrl().contains("/checkout-step-two"));
    }

    @Test
    @Tag("TC-010")
    public void testEmptylastname() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        checkoutInformation.saisirFirstname("tarek");
        checkoutInformation.saisirLasttname("");
        checkoutInformation.saisirCodepostal("paris");
        checkoutInformation.cliqueSurBoutonContinuer();
        WebElement messageError = driver.findElement(By.cssSelector(".error-message-container"));
        assertTrue(messageError.isDisplayed());
    }

    @Test
    @Tag("TC-011")
    public void testEmptyfirstname() {
        // Étape 1 : Localiser le champ "First Name"
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        checkoutInformation.saisirFirstname("");
        checkoutInformation.saisirLasttname("hallalel");
        checkoutInformation.saisirCodepostal("paris");
        checkoutInformation.cliqueSurBoutonContinuer();
        WebElement messageError = driver.findElement(By.cssSelector(".error-message-container"));
        assertTrue(messageError.isDisplayed());
    }

    @Test
    @Tag("TC-012")
    public void testEmptycodePostal() {
        // Étape 1 : Localiser le champ "First Name"
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        checkoutInformation.saisirFirstname("tarek");
        checkoutInformation.saisirLasttname("hallalel");
        checkoutInformation.saisirCodepostal("");
        checkoutInformation.cliqueSurBoutonContinuer();
        WebElement messageError = driver.findElement(By.cssSelector(".error-message-container"));
        assertTrue(messageError.isDisplayed());
    }

    @Test
    @Tag("TC-013")
    public void testbuttonpanieremove() {
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement messagElement = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));
        messagElement.click();

        WebElement messagElement2 = driver.findElement(By.name("remove-sauce-labs-backpack"));

        messagElement2.click();

        driver.get("https://www.saucedemo.com/cart.html");

        List<WebElement> listpanier = driver.findElements(By.cssSelector(".inventory_item"));

        for (WebElement item : listpanier) {
            String title = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            WebElement button = item.findElement(By.cssSelector("btn.btn_secondary.btn_small.cart_button\""));

            String buttonText = button.getText();
            System.out.println(buttonText);
            assertNotEquals("Sauce Labs Backpack", title);

        }

    }

    @Test
    @Tag("TC-014")

    public void comparer_panier() {

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement messagElement = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));
        messagElement.click();

        driver.get("https://www.saucedemo.com/cart.html");

        driver.findElement(By.cssSelector(".checkout_button")).click();
        assertTrue(driver.getCurrentUrl().contains("/checkout-step-one.html"),
                "La page checkout-step-one n'est pas affichée !");

        checkoutInformation.saisirFirstname("tarek");
        checkoutInformation.saisirLasttname("hallalel");
        checkoutInformation.saisirCodepostal("paris");
        checkoutInformation.cliqueSurBoutonContinuer();

        Invontory.comparer_le_panier_avec_les_produits_achats_finales(driver);
    }

    @Test
    @Tag("TC-015")
    public void tester_prix() {

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement messagElement = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));

        messagElement.click();

        driver.get("https://www.saucedemo.com/cart.html");

        driver.findElement(By.cssSelector(".checkout_button")).click();
        assertTrue(driver.getCurrentUrl().contains("/checkout-step-one.html"),
                "La page checkout-step-one n'est pas affichée !");

        checkoutInformation.saisirFirstname("tarek");
        checkoutInformation.saisirLasttname("hallalel");
        checkoutInformation.saisirCodepostal("paris");
        checkoutInformation.cliqueSurBoutonContinuer();

        Invontory.tester_prix(driver);

    }

    @Test
    @Tag("TC-016")
    public void testTriCroissant() {

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement dropdown = driver.findElement(By.className("product_sort_container"));

        Select select = new Select(dropdown);

        select.selectByValue("az");

        ProductSortingUtils.verifierTrie(driver, "az");
    }

    @Test
    @Tag("TC-017")
    public void testTridecroissant() {

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement dropdown = driver.findElement(By.className("product_sort_container"));

        Select select = new Select(dropdown);

        select.selectByValue("az");

        ProductSortingUtils.verifierTrie(driver, "za");
    }

    public void testTricroissantprix() {

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement dropdown = driver.findElement(By.className("product_sort_container"));

        Select select = new Select(dropdown);

        select.selectByValue("lohi");

        ProductSortingUtils.verifierTrieprice(driver, "lohi");

    }

    @Test
    @Tag("TC-018")
    public void testTridecroissantprix() {

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        WebElement dropdown = driver.findElement(By.className("product_sort_container"));

        Select select = new Select(dropdown);

        select.selectByValue("hilo");

        ProductSortingUtils.verifierTrieprice(driver, "hilo");

    }
}
