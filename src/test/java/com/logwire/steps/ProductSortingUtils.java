package com.logwire.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortingUtils {
    public static void verifierTrie(WebDriver driver, String ordre) {
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdown);
        select.selectByValue(ordre);
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item"));
        List<String> listeNotTrieeCartProductNames = new ArrayList<>();
        List<String> listeTrieeCartProductNames;

        for (WebElement item : list) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            listeNotTrieeCartProductNames.add(name);
        }

        listeTrieeCartProductNames = new ArrayList<>(listeNotTrieeCartProductNames);

        if ("az".equals(ordre)) {
            listeTrieeCartProductNames.sort(String::compareTo);
        } else if ("za".equals(ordre)) {
            listeTrieeCartProductNames.sort(Collections.reverseOrder());
        }
        Assertions.assertEquals(listeTrieeCartProductNames, listeNotTrieeCartProductNames);
    }

    public static void verifierTrieprice(WebDriver driver, String ordre) {
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdown);
        select.selectByValue(ordre);
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item"));
        List<String> listeNotTrieeCartProductNames = new ArrayList<>();
        List<String> listeTrieeCartProductNames;

        for (WebElement item : list) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            listeNotTrieeCartProductNames.add(name);
        }

        listeTrieeCartProductNames = new ArrayList<>(listeNotTrieeCartProductNames);

        if ("lohi".equals(ordre)) {
            listeTrieeCartProductNames.sort(String::compareTo);
        } else if ("hilo".equals(ordre)) {
            listeTrieeCartProductNames.sort(Collections.reverseOrder());
        }
        Assertions.assertEquals(listeTrieeCartProductNames, listeNotTrieeCartProductNames);
    }
}
