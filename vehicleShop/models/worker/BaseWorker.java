package vehicleShop.models.worker;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseWorker implements Worker{
    private String name;
    private int strength;
    private Collection<Tool> tools;

    public BaseWorker(String name, int strength) {
        setName(name);
        setStrength(strength);
        this.tools=new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.equals("")){
            throw new IllegalArgumentException(ExceptionMessages.WORKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setStrength(int strength) {
        if (strength<0){
            throw new IllegalArgumentException(ExceptionMessages.WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public void working() {
        setStrength(getStrength()-10);
        if (getStrength()<0){
            setStrength(0);
        }

    }

    @Override
    public void addTool(Tool tool) {
            tools.add(tool);
    }

    @Override
    public boolean canWork() {
        if (getStrength()>0){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public Collection<Tool> getTools() {
        return this.tools;
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        str.append(String.format("Name: %s, Strength: %d%n",getName(),getStrength()));
        int toolsFit=0;
        for (Tool tool:getTools()) {
            if (!tool.isUnfit()){
                toolsFit++;
            }
        }
        str.append(String.format("Tools: %d fit left",toolsFit));
        return str.toString();
    }
}
