package org.group51.duncrawl.unittests;

import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.models.enemies.Jellyfish;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EnemyStatTests {

    @Test
    public void testJellyfish() {
        Entity entity = new Jellyfish();
        assertEquals(entity.getAttack(), 10);

        assertEquals(entity.getMovementTimer(), 20);
    }
//    @Test
//    public void testSeal() {
//        Entity entity = new Seal();
//        assertEquals(entity.getAttack(), 25);
//
//        assertEquals(entity.getMovementTimer(), 30);
//    }

//    @Test
//    public void testCrabfish() {
//        Entity entity = new Crab();
//        assertEquals(entity.getAttack(), 20);
//
//        assertEquals(entity.getMovementTimer(), 12);
//    }

//    @Test
//    public void testTurtle() {
//        Entity entity = new Turtle();
//        assertEquals(entity.getAttack(), 30);
//
//        assertEquals(entity.getMovementTimer(), 50);
//    }

}
