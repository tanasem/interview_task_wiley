package com.wiley.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WileyUITest extends TestBase {

    @Test
    public void wileyTest() {
        app.goToMainPage();

        //Click "YES" on "Undetected location" form
        app.mainPage().chooseYes();

        //1. Check items under Who We Serve for sub-header
        Set<String> currentTitlesUnderWhoWeServe = app.mainPage().getTitlesOfItemsUnderWhoWeServe();

        //There are 12 items under Who We Serve sub-header
        Assert.assertEquals(currentTitlesUnderWhoWeServe.size(), 12);

        //Check titles
        Set<String> defTitles = Stream.of("Students", "Instructors", "Book Authors", "Professionals", "Researchers",
                "Institutions", "Librarians", "Corporations", "Societies", "Journal Editors", "Bookstores",
                "Government")
                .collect(Collectors.toCollection(HashSet::new));
        Assert.assertEquals(currentTitlesUnderWhoWeServe, defTitles);

        //2. Check search functionality:
        //Enter “Java” in the search input and do not press the search button (with magnifying glass icon)
        app.mainPage().inputSearch("Java");

        //Area with related content is displayed right under the search header
        app.mainPage().checkRelatedContent();

        //3. Enter “Java” in the search input and press the search button
        app.mainPage().inputSearch("Java");
        app.mainPage().initSearch();

        //Only titles containing “Java” are displayed
        //There are 10 titles on the page
        Assert.assertEquals(app.searchPage().getNumberOfSearchResults(), 10);
        Assert.assertEquals(app.searchPage().getNumberOfSearchResultsJava(), 10);

        //Each title has at least one “Add to Cart” button for E-Book/Print version
        //and “VIEW ON WILEY ONLINE LIBRARY” for O-BOOK version
        app.searchPage().checkButtonsOnSearchResults();

        //4. Go to “Subjects” top menu, select “Education”
        app.searchPage().goToEducation();

        //Check that “Education” header is displayed
        app.educationPage().checkEducationHeader();

        //13 items are displayed under “Subjects” on the left side of the screen
        Set<String> currentTitlesUnderSubjects = app.educationPage().getTitlesOfItemsUnderSubjects();
        Assert.assertEquals(currentTitlesUnderSubjects.size(), 13);

        //Check titles
        Set<String> defTextOfSubjects = Stream.of("Information & Library Science", "Education & Public Policy",
                "K-12 General", "Higher Education General", "Vocational Technology",
                "Conflict Resolution & Mediation (School settings)", "Curriculum Tools- General",
                "Special Educational Needs", "Theory of Education", "Education Special Topics",
                "Educational Research & Statistics", "Literacy & Reading", "Classroom Management")
                .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(currentTitlesUnderSubjects, defTextOfSubjects);
    }

}
