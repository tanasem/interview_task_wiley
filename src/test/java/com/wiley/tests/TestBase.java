package com.wiley.tests;

import com.wiley.appmanager.ApplicationManager;
import org.testng.annotations.AfterTest;

public class TestBase {
    protected final ApplicationManager app = new ApplicationManager();

    @AfterTest
    public void tearDown() {
        app.quit();
    }

}
