package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public enum Menubar {

    STATUS_UPDATES(By.linkText("Status updates")),
    HELP_AND_CONTACTS(By.linkText("Help & contacts"));

    private final By byLocator;

    Menubar(By by){
        byLocator = by;
    }

    public Target menuOption(){
        return Target.the(this.name()).located(byLocator);
    }
}
