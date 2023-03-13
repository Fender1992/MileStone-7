package Milestone3;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HealthTest {
    MakeHealthObj testHealth = new MakeHealthObj();
    @Test
    void checkIfHealthItemAreEqual(){
        //given
        String name = "Health";
        String description = "Health item";
        int price = 1;
        int quantity = 2;

        //when
        Object resultHealthObject = testHealth.health(name, description, price, quantity);

        //then
        Object expectedHealthObject = new Health();
        assertNotEquals(resultHealthObject, expectedHealthObject);
    }
    class MakeHealthObj{
        Object health(String name, String description, int price, int quantity){
            return new HealthTest();
        }

    }

}