import ru.ifmo.se.pokemon.Battle;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemons.Camerupt p1 = new Pokemons.Camerupt("Чужой", 1);
        Pokemons.Poliwrath p2 = new Pokemons.Poliwrath("Хищник", 1);
        b.addAlly(p1);
        b.addFoe(p2);
        b.go();
    }
}
