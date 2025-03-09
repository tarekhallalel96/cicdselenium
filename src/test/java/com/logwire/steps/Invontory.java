package com.logwire.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class Invontory {

    public static void comparer_le_panier_avec_les_produits_achats_finales(WebDriver driver) {

        List<WebElement> list = driver.findElements(By.cssSelector(".cart_item"));

        List<String> cartProductNames = new ArrayList<>();
        List<String> cartProductPrices = new ArrayList<>();

        for (WebElement item : list) {

            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            String price = item.findElement(By.cssSelector(".inventory_item_price")).getText();
            cartProductNames.add(name);
            cartProductPrices.add(price);
        }

        List<WebElement> list2 = driver.findElements(By.cssSelector(".cart_item"));

        List<String> cartProductNames2 = new ArrayList<>();
        List<String> cartProductPrices2 = new ArrayList<>();

        for (WebElement item2 : list2) {

            String name2 = item2.findElement(By.cssSelector(".inventory_item_name")).getText();
            String price2 = item2.findElement(By.cssSelector(".inventory_item_price")).getText();
            cartProductNames2.add(name2);
            cartProductPrices2.add(price2);
        }
        assertEquals(cartProductNames, cartProductNames2);
        assertEquals(cartProductPrices, cartProductPrices2);

    }

    public static void tester_prix(WebDriver driver) {

        List<WebElement> list = driver.findElements(By.cssSelector(".cart_item"));
        System.out.println(list);
        double totalSum = 0;
        for (WebElement item : list) {

            String priceitemElement = item.findElement(By.cssSelector(".inventory_item_price")).getText();

            double priceItem = Double.parseDouble(priceitemElement.replace("$", ""));

            totalSum += priceItem;

        }

        WebElement itemTotalElement = driver.findElement(By.cssSelector(".summary_subtotal_label"));
        String itemTotalText = itemTotalElement.getText();
        String priceText = itemTotalText.replace("Item total: $", "").trim();
        double price = Double.parseDouble(priceText);
        assertEquals(price, totalSum);

        WebElement itemTaxElement = driver.findElement(By.cssSelector(".summary_tax_label"));
        String itemTaxtext = itemTaxElement.getText();
        String itemTax = itemTaxtext.replace("Tax: $", "").trim();
        double itemTaxDouble = Double.parseDouble(itemTax);
        WebElement itemTotalpriceElement = driver.findElement(By.cssSelector(".summary_total_label"));

        String itemTotalpriceText = itemTotalpriceElement.getText();
        String itemTotalprice = itemTotalpriceText.replace("Total: $", "").trim();

        double itemTotalpriceDouble = Double.parseDouble(itemTotalprice);

        assertEquals(itemTotalpriceDouble, totalSum + itemTaxDouble);

    }

}