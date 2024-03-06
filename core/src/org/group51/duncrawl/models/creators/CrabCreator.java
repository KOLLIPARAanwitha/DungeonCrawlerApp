package org.group51.duncrawl.models.creators;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.abstracts.EnemyCreator;
import org.group51.duncrawl.models.enemies.Crab;

public class CrabCreator extends EnemyCreator {
    public Enemy createEnemy() {
        return new Crab();
    }
}
