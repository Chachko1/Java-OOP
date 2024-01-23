package vehicleShop.models.tool;

import vehicleShop.common.ExceptionMessages;

public class ToolImpl implements Tool{
    private int power;

    public ToolImpl(int power) {
        setPower(power);
    }

    private void setPower(int power) {
        if (power<0){
            throw new IllegalArgumentException(ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void decreasesPower() {
        setPower(getPower()-5);
        if (getPower()<0){
            setPower(0);
        }
    }

    @Override
    public boolean isUnfit() {
        if (getPower()==0){
            return true;
        }
        return false;
    }
}
