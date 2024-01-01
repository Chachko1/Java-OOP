package christmasPastryShop.entities.cocktails.interfaces;

public class Hibernation extends BaseCocktail{
    private static final double INITIALPRICE=4.50;

    public Hibernation(String name, int size, String brand) {
        super(name, size, INITIALPRICE, brand);
    }
}
