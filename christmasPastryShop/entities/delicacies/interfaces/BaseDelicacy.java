package christmasPastryShop.entities.delicacies.interfaces;

import christmasPastryShop.common.ExceptionMessages;

public abstract class BaseDelicacy implements Delicacy{
    private String name;
    private double portion;
    private double price;

    public BaseDelicacy(String name, double portion, double price) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    private void setPrice(double price) {
        if (price<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    private void setPortion(double portion) {
        if (portion<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
    }

    private void setName(String name) {
        if (name==null||name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
    public String toString(){
        return String.format("%s: %.2fg - %.2f",this.getClass().getSimpleName(),this.getPortion(),this.getPrice());
    }
}
