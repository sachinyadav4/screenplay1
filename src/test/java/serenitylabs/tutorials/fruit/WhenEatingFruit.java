package serenitylabs.tutorials.fruit;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class WhenEatingFruit {

    Actor eddie = Actor.named("Eddie");

    @Test
    public void eating_apples() {
        eddie.attemptsTo(Eat.anApple(), Eat.aLargePeer(), Eat.aSmallPeer());
    }

    public static class Eat {
        public static Performable anApple() { return new EatsAnApple(); }

        public static Performable aLargePeer() {   return new EatsAPeer("Large"); }
        public static Performable aSmallPeer() {   return new EatsAPeer( "Small"); }
    }

    public static class EatsAnApple implements Performable {
        @Override
        public <T extends Actor> void performAs(T actor) {
        }
    }

    public static class EatsAPeer implements Performable {
        private String size ="";

        EatsAPeer(){ }

        EatsAPeer(String size){
            this.size = size;
        }

        @Override
        @Step("{0} eats a #size peer")
        public <T extends Actor> void performAs(T actor) {

        }
    }
}

