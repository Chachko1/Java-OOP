package christmasPastryShop.entities.cocktails.interfaces;

public class MulledWine extends BaseCocktail{
    private static final double INITIALPRICE=3.50;

    public MulledWine(String name, int size,  String brand) {
        super(name, size, INITIALPRICE, brand);
    }
}
