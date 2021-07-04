package serenitylabs.tutorials.trains.ui;


import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class CookiesDialog extends PageObject {
    public static final By ACCEPT_ALL_COOKIES = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    public static final By DONE = By.xpath("//*[@id=\"cb-done-button\"]");
}
