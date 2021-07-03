package serenitylabs.tutorials.vetclinic.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;
import serenitylabs.tutorials.vetclinic.model.Pet;
import serenitylabs.tutorials.vetclinic.model.PetHotel;
import serenitylabs.tutorials.vetclinic.screenplay.questions.TheRegisteredGuests;
import serenitylabs.tutorials.vetclinic.screenplay.tasks.CheckIn;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class BookAPetInToAHotel {

    @Test
    public void petra_books_her_pet_cat_in_to_the_hotel(){
        //Given
        Actor petra = Actor.named("Petra the pet owner");
        Pet ginger = Pet.cat().named("Ginger");
        PetHotel petHotel = new PetHotel("Pet-Hotel");

        //When
        //?
          petra.attemptsTo(
                  CheckIn.aPet(ginger).inTo(petHotel)
          );

        //Then
        assertThat(petHotel.getPets(), hasItem(ginger));
        petra.should(seeThat(TheRegisteredGuests.in(petHotel), hasItem(ginger)));



    }

}
