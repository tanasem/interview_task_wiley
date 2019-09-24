package com.wiley.pages;

import com.wiley.appmanager.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Page {
    protected WebDriver wd;
    protected WebDriverWait wait;
    protected SeleniumHelper seleniumHelper;

    public Page(WebDriver wd) {
        this.wd = wd;
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 10);
        seleniumHelper = new SeleniumHelper(wd);
    }

}
