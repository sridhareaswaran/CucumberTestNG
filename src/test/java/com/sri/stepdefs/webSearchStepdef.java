package com.sri.stepdefs;

import com.sri.pages.homePage;
import com.sri.pages.resultsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class webSearchStepdef {

    homePage homePage = new homePage();
    resultsPage resultsPage =new resultsPage();

    @Given("I am on Google homepage")
    public void i_am_on_Google_homepage() throws Throwable {
        homePage.open();
    }

    @When("I search for '(.*)'")
    public void i_search_for_something(String text) throws Throwable {
        homePage.searchFor(text);
    }

    @Then("title should contain text '(.*)'")
    public void i_should_see_results_with_text(String text) throws Throwable {
        resultsPage.verifyTitleContains(text);
    }

}
