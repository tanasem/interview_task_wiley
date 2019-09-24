package com.wiley.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPage extends Page {

    private static final By MODAL_DIALOG_LOCATOR = By.id("selectCountryModalWnd");
    private static final By MODAL_FADE_IN_LOCATOR = By.xpath("//div[@class='modal-backdrop fade in']");
    private static final By YES_BUTTON_LOCATOR = By.xpath("//form[@id='country-location-form']//button[text()='YES']");
    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@type='search']");
    private static final By SEARCH_BUTTON_LOCATOR = By.className("input-group-btn");
    private static final By WHO_WE_SERVE_SUBHEADER_LOCATOR =
            By.xpath("//div[@class='row who-we-serve-blocks']//a[@class='who-we-serve-block-title']");
    private static final By AREA_WITH_RELATED_CONTENT_LOCATOR =
            By.xpath("//aside[contains(@class,'ui-autocomplete')][contains(@style,'top: -23px; left: 0px;')]");

    public MainPage(WebDriver wd) {
        super(wd);
    }

    public void chooseYes() {
        if (wd.findElement(MODAL_DIALOG_LOCATOR).isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(YES_BUTTON_LOCATOR));
            seleniumHelper.click(YES_BUTTON_LOCATOR);
            wait.until(ExpectedConditions.numberOfElementsToBe(MODAL_FADE_IN_LOCATOR, 0));
        }
    }

    public void checkRelatedContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AREA_WITH_RELATED_CONTENT_LOCATOR));
        Assert.assertTrue(wd.findElement(AREA_WITH_RELATED_CONTENT_LOCATOR).isDisplayed());
    }

    public Set<String> getTitlesOfItemsUnderWhoWeServe() {
        Set<String> titles = new HashSet<>();
        List<WebElement> currentTitles =
                wd.findElements(WHO_WE_SERVE_SUBHEADER_LOCATOR);
        for (WebElement currentTitle : currentTitles) {
            String title = currentTitle.getAttribute("innerText");
            titles.add(title);
        }
        return titles;
    }

    public void initSearch() {
        seleniumHelper.click(SEARCH_BUTTON_LOCATOR);
    }

    public void inputSearch(String text) {
        seleniumHelper.type(SEARCH_INPUT_LOCATOR, text);
    }

}
