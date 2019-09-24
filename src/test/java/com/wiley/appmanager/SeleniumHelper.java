package com.wiley.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SeleniumHelper {

    protected WebDriver wd;

    public SeleniumHelper(WebDriver driver) {
        this.wd = driver;
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        Assert.assertNotNull(element);
        element.click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void moveToElem(WebElement element) {
        Actions action = new Actions(wd);
        action.moveToElement(element).build().perform();
    }

}
