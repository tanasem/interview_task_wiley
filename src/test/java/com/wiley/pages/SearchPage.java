package com.wiley.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchPage extends Page {

    private static final By SEARCH_RESULTS_CONTENT_LOCATOR = By.className("search-result-content");
    private static final By SEARCH_RESULT_ITEM_LOCATOR = By.className("product-item");
    private static final By SEARCH_RESULT_ITEM_JAVA_LOCATOR = By.xpath("//section[@class='product-item']//span[text()='Java']");
    private static final By SUBJECTS_MENU_LOCATOR = By.xpath("//a[contains(text(),'SUBJECTS')]");
    private static final By EDUCATION_SUBMENU_LOCATOR = By.xpath("//a[contains(text(),'Education')]");

    public SearchPage(WebDriver wd) {
        super(wd);
    }

    public void goToEducation() {
        seleniumHelper.moveToElem(wd.findElement(SUBJECTS_MENU_LOCATOR));
        seleniumHelper.click(EDUCATION_SUBMENU_LOCATOR);
    }

    public int getNumberOfSearchResults() {
        List<WebElement> searchResults = wd.findElement(SEARCH_RESULTS_CONTENT_LOCATOR)
                .findElements(SEARCH_RESULT_ITEM_LOCATOR);
        return searchResults.size();
    }

    public int getNumberOfSearchResultsJava() {
        List<WebElement> searchResults = wd.findElement(SEARCH_RESULTS_CONTENT_LOCATOR)
                .findElements(SEARCH_RESULT_ITEM_JAVA_LOCATOR);
        return searchResults.size();
    }

    public void checkButtonsOnSearchResults() {
        for (int i = 1; i <= 10; i++) {
            List<WebElement> searchResults =
                    wd.findElements(By.xpath("//section[@class='product-item'][" + i + "]//button[text()='Add to cart']|//section[@class='product-item'][\" + i + \"]//a[contains(text(),'View on Wiley Online Library')]"));
            Assert.assertTrue(searchResults.size() > 0);
        }
    }

}
