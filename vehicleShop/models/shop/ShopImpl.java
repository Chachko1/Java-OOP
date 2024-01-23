package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.Worker;

public class ShopImpl implements Shop{


    @Override
    public void make(Vehicle vehicle, Worker worker) {
        while (!vehicle.reached()){

            for (Tool workerTool: worker.getTools()) {
                 while (worker.canWork()){
                     if (workerTool.isUnfit()){
                         break;
                     }
                     worker.working();
                     workerTool.decreasesPower();
                     vehicle.making();
                     if (vehicle.reached()){
                         break;
                     }
                 }
                if (vehicle.reached()){
                    break;
                }

            }

        }
    }
}
