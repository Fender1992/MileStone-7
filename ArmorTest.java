package Milestone3;

import Milestone3.Armor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    Armor testArmor = new Armor(null, null, 0, 0);
    @Test
    void testArmorEqualsOriginalArmor(){
        var armor = new Armor();
        testArmor = armor;
        assertEquals(testArmor, armor);
    }
}