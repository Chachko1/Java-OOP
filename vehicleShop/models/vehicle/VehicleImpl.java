package vehicleShop.models.vehicle;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;

public class VehicleImpl implements Vehicle{
    private String name;
    private int strengthRequired;



    public VehicleImpl(String name, int strengthRequired) {
       setName(name);
        setStrengthRequired(strengthRequired);


    }

    private void setName(String name) {
        if (name == null || name.equals("")){
            throw new IllegalArgumentException(ExceptionMessages.VEHICLE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setStrengthRequired(int strengthRequired) {
        if (strengthRequired<0){
            throw new IllegalArgumentException(ExceptionMessages.VEHICLE_STRENGTH_LESS_THAN_ZERO);
        }
        this.strengthRequired = strengthRequired;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrengthRequired() {
        return this.strengthRequired;
    }

    @Override
    public boolean reached() {
        if (getStrengthRequired()==0){
            return true;
        }
        return false;
    }

    @Override
    public void making() {

        setStrengthRequired(getStrengthRequired()-5);
        if (getStrengthRequired()<0){
            setStrengthRequired(0);
        }

    }
}
