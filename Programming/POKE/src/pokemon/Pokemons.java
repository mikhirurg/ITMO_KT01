import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

class Camerupt extends Pokemon {
    public Camerupt(String name, int level) {
        super(name, level);
        setStats(70, 100, 70, 105, 75, 40);
        setType(Type.FIRE, Type.GROUND);
        setMove(new Swagger(), new GrassWhistle(), new SludgeBomb(), new SweetScent());
    }
}

class Groudon extends Pokemon {
    public Groudon(String name, int level) {
        super(name, level);
        setStats(100, 150, 140, 100, 90, 90);
        setType(Type.GROUND);
        setMove(new Rest(), new IceBeam(), new ShadowClaw());
    }
}

class Numel extends Pokemon {
    public Numel(String name, int level) {
        super(name, level);
        setStats(60, 60, 40, 65, 45, 35);
        setType(Type.FIRE, Type.GROUND);
        setMove(new Rest(), new IceBeam(), new ShadowClaw(), new Thunder());
    }
}

class Poliwag extends Pokemon {
    public Poliwag(String name, int level) {
        super(name, level);
        setStats(40, 50, 40, 40, 40, 90);
        setType(Type.WATER);
        setMove(new Thunderbolt(), new DoubleTeam());
    }
}

class Poliwhirl extends Pokemon {
    public Poliwhirl(String name, int level) {
        super(name, level);
        setStats(65, 65, 65, 50, 50, 90);
        setType(Type.WATER);
        setMove(new Thunderbolt(), new DoubleTeam(), new RockTomb());
    }
}

class Poliwrath extends Pokemon {
    public Poliwrath(String name, int level) {
        super(name, level);
        setStats(90, 95, 95, 70, 90, 70);
        setType(Type.WATER, Type.FIGHTING);
        setMove(new Thunderbolt(), new DoubleTeam(), new RockTomb(), new ShadowClaw());
    }
}
