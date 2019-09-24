package com.wiley.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EducationPage extends Page {

    private static final By EDUCATION_HEADER_LOCATOR = By.xpath("//h1/span[text()='Education']");
    private static final By SUBJECTS_ITEM_LOCATOR = By.xpath("//div[@class='side-panel']/ul/li/a");

    public EducationPage(WebDriver wd) {
        super(wd);
    }

    public Set<String> getTitlesOfItemsUnderSubjects() {
        Set<String> titles = new HashSet<>();
        List<WebElement> currentTitles = wd.findElements(SUBJECTS_ITEM_LOCATOR);
        for (WebElement currentTitle : currentTitles) {
            String title = currentTitle.getAttribute("innerText");
            titles.add(title);
        }
        return titles;
    }

    public void checkEducationHeader() {
        Assert.assertTrue(wd.findElement(EDUCATION_HEADER_LOCATOR).isDisplayed());
    }

}
