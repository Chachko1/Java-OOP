package christmasPastryShop.entities.booths.interfaces;

public class PrivateBooth extends BaseBooth{
    private static final double INITIALPRICEPERPERSON=3.50;
    public PrivateBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, INITIALPRICEPERPERSON);
    }
}
