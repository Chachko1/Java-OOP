package christmasPastryShop.entities.booths.interfaces;

public class OpenBooth extends BaseBooth{
    private static final double INITIALPRICEPERPERSON=2.50;
    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, INITIALPRICEPERPERSON);
    }
}
