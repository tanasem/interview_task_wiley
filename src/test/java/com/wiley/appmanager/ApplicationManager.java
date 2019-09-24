package com.wiley.appmanager;

import com.wiley.pages.EducationPage;
import com.wiley.pages.MainPage;
import com.wiley.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver wd;
    private WebDriverWait wait;
    private MainPage mainPage;
    private SearchPage searchPage;
    private EducationPage educationPage;

    public ApplicationManager() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 30);

        mainPage = new MainPage(wd);
        searchPage = new SearchPage(wd);
        educationPage = new EducationPage(wd);
    }

    public MainPage mainPage() {
        return mainPage;
    }

    public SearchPage searchPage() {
        return searchPage;
    }

    public EducationPage educationPage() {
        return educationPage;
    }

    public void goToMainPage() {
        wd.get("https://www.wiley.com/en-us");
    }

    public void quit() {
        wd.quit();
    }

}
