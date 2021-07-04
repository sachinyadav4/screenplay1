package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.SearchResults;
import serenitylabs.tutorials.trains.questions.TheContactDetails;
import serenitylabs.tutorials.trains.questions.TheServiceLines;
import serenitylabs.tutorials.trains.tasks.EnterContactDetails;
import serenitylabs.tutorials.trains.tasks.Search;
import serenitylabs.tutorials.trains.tasks.SelectMenu;
import serenitylabs.tutorials.trains.ui.ContactForm;
import serenitylabs.tutorials.trains.ui.CookiesDialog;
import serenitylabs.tutorials.trains.ui.SearchResultsPage;
import serenitylabs.tutorials.trains.ui.TFLHomePage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.*;
import static serenitylabs.tutorials.trains.ui.Menubar.HELP_AND_CONTACTS;
import static serenitylabs.tutorials.trains.ui.Menubar.STATUS_UPDATES;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {
    Actor carrie = Actor.named("Carrie the commuter");

    @Managed
    WebDriver browser;

    @Before
    public void setTheStage(){
        carrie.can(BrowseTheWeb.with(browser));
        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class));
    }

    @Test
    public void planning_a_simple_trip(){
             Enter.theValue("Waterloo")
                     .into(TFLHomePage.SEARCH)
                     .thenHit(Keys.ENTER);
     carrie.should(seeThat(TheWebPage.title(), containsString("Transport for London")));
    }

    @Test
    public void status_updates_page_test(){
        carrie.attemptsTo(Open.url("https://tfl.gov.uk/tube-dlr-overground/status"));
        carrie.should(seeThat(TheWebPage.title(), containsString("status updates")));
    }

    @Test
    public void should_be_able_to_search_for_station_details(){
            carrie.attemptsTo(Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(CookiesDialog.DONE),
                Search.forStation("Waterloo"));

        carrie.should(seeThat(
                SearchResults.heading(),equalTo("Search: Waterloo")));
    }

    @Test
    public void should_list_all_relevant_station_information(){
        carrie.attemptsTo(
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(CookiesDialog.DONE),
                Enter.theValue("Jubilee")
                        .into(TFLHomePage.SEARCH)
                        .thenHit(Keys.ENTER));
        carrie.should(seeThat(TheTarget.textOf(SearchResultsPage.SEARCH_RESULTS_HEADING), equalTo("Search: jubilee")));
        carrie.should(seeThat(TheTarget.textOf(SearchResultsPage.FIRST_ARTICLE_HEADING), equalTo("Jubilee Greenway - Transport for London")));
    }

    @Test
    public void should_see_status_updates(){
        carrie.attemptsTo(
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(CookiesDialog.DONE),
                SelectMenu.option(STATUS_UPDATES));

        carrie.should(seeThat(
                TheServiceLines.displayed(),hasItems("Bakerloo", "Circle", "Central"),
                hasItems("Bakerloo", "Circle", "Central")));
    }

    @Test
    public void should_be_able_to_contact_tfl(){
        carrie.attemptsTo(
                Click.on(CookiesDialog.ACCEPT_ALL_COOKIES),
                Click.on(CookiesDialog.DONE),
                Click.on(HELP_AND_CONTACTS.menuOption()),
                Click.on(ContactForm.OYSTER_APP));

        carrie.attemptsTo(
                EnterContactDetails.forCustomer("Mrs", "Sarah Jane", "Smith"));

        carrie.should(seeThat(TheContactDetails.title(), equalTo("Mrs")));
        carrie.should(seeThat(TheContactDetails.firstName(),equalTo("Sarah Jane")));
        carrie.should(seeThat(TheContactDetails.lastName(), equalTo("Smith")));
    }
}
