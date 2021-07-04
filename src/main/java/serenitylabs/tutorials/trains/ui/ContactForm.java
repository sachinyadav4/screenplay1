package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactForm {

    public static final Target OYSTER_APP =Target.the("Title field")
            .located(By.linkText("Contact us about the TfL Oyster and contactless app"));

    public static final Target TITLE =Target.the("Title field")
                                      .located(By.id("PersonalDetails_Title"));
    public static final Target FIRST_NAME = Target.the("First Name")
                                             .located(By.id("PersonalDetails_FirstName"));
    public static Target Last_NAME = Target.the("Last Name")
            .located(By.id("PersonalDetails_LastName"));
}
