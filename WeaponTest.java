package Milestone3;

import Milestone3.Armor;
import Milestone3.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    Weapon testWeapon = new Weapon(null, null, 0, 0);
    @Test
    void testArmorEqualsOriginalArmor(){
        var weapon = new Weapon();
        testWeapon = weapon;
        assertEquals(testWeapon, weapon);
    }
}