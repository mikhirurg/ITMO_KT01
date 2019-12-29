import ru.ifmo.se.pokemon.*;

class DoubleTeam extends StatusMove {
    final Type type = Type.PSYCHIC;
    final private int power = 0;
    final private int accuracy = 0;

    public DoubleTeam() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().stat(Stat.EVASION, 1);
        pokemon.addEffect(effect);
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class GrassWhistle extends StatusMove {
    final Type type = Type.GRASS;
    final private int power = 0;
    final private int accuracy = 55;

    public GrassWhistle() {
        super(Type.NORMAL, 0, 55);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.freeze(pokemon);
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class IceBeam extends SpecialMove {
    Type type = Type.ICE;
    int power = 90;
    int accuracy = 100;

    public IceBeam() {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) Effect.freeze(p);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class Rest extends StatusMove {
    final Type type = Type.PSYCHIC;
    final private int power = 0;
    final private int accuracy = 0;

    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.sleep(pokemon);
        Effect.sleep(pokemon);
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

}

class RockTomb extends PhysicalMove {
    final Type type = Type.PSYCHIC;
    final private int power = 60;
    final private int accuracy = 95;

    public RockTomb() {
        super(Type.PSYCHIC, 60, 95);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().stat(Stat.SPEED, -1);
        pokemon.addEffect(effect);
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class ShadowClaw extends PhysicalMove {
    Type type = Type.GHOST;
    int power = 70;
    int accuracy = 100;

    public ShadowClaw() {
        super(Type.GHOST, 70, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {

    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class SludgeBomb extends StatusMove {

    final Type type = Type.POISON;
    final private int power = 90;
    final private int accuracy = 100;

    public SludgeBomb() {
        super(Type.POISON, 90, 100);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() < 0.33) {
            Effect.poison(pokemon);
        }
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

}

class Swagger extends StatusMove {
    final private Type type = Type.NORMAL;
    final private int power = 0;
    final private int accuracy = 0;

    public Swagger() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().stat(Stat.ATTACK, 2);
        pokemon.addEffect(effect);
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class SweetScent extends StatusMove {
    final Type type = Type.NORMAL;
    final private int power = 0;
    final private int accuracy = 0;

    public SweetScent() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().stat(Stat.EVASION, -1);
        pokemon.addEffect(effect);
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

}

class Thunder extends SpecialMove {
    Type type = Type.ELECTRIC;
    int power = 110;
    int accuracy = 100;

    public Thunder() {
        super(Type.ELECTRIC, 110, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.33) Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}

class Thunderbolt extends SpecialMove {
    Type type = Type.ELECTRIC;
    int power = 90;
    int accuracy = 100;

    public Thunderbolt() {
        super(Type.ELECTRIC, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "uses attack " + this.getClass().getName() + "(Type=" + this.getType() + "|Power=" + this.getPower() + "|Accuracy=" + this.getAccuracy() + ")";
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}
