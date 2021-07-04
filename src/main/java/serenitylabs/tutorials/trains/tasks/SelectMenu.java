package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import serenitylabs.tutorials.trains.ui.Menubar;

public class SelectMenu {

    public static Performable option(Menubar menuOption) {
        return Click.on(menuOption.menuOption());
    }
}
