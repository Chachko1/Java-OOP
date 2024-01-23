package vehicleShop.core;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private WorkerRepository workerRepository;
    private VehicleRepository vehicleRepository;
    private Shop shop;

    public ControllerImpl() {
        workerRepository=new WorkerRepository();
        vehicleRepository=new VehicleRepository();
        shop=new ShopImpl();
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        if (type.equals("FirstShift")){
            worker= new FirstShift(workerName);

        } else if (type.equals("SecondShift")) {
            worker= new SecondShift(workerName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }
        workerRepository.add(worker);

        return String.format("Successfully added %s with name %s.",type,workerName);

    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle=new VehicleImpl(vehicleName,strengthRequired);
        vehicleRepository.add(vehicle);
        return String.format("Successfully added Vehicle: %s.",vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Tool tool=new ToolImpl(power);
        if (workerRepository.findByName(workerName)==null){
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        workerRepository.findByName(workerName).addTool(tool);
        return String.format("Successfully added tool with power %d to worker %s.",power,workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> fittedWorkers = workerRepository.getWorkers().stream()
                .filter(worker -> worker.getStrength() > 70)
                .collect(Collectors.toList());
        if (fittedWorkers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }
        Vehicle vehicle= vehicleRepository.findByName(vehicleName);
        shop.make(vehicle,fittedWorkers.get(0));

        StringBuilder str=new StringBuilder();
        if (vehicle.reached()){
            str.append(String.format("Vehicle %s is done. ",vehicleName));
        }else {
            str.append(String.format("Vehicle %s is not done. ",vehicleName));
        }
        int brokenTools=0;
        for (Worker worker:fittedWorkers) {
            for (Tool tool:worker.getTools()) {
                if (tool.isUnfit()){
                    brokenTools++;
                }
            }
        }
        if (brokenTools==1){
            str.append(String.format("%d tool have been unfit while working on it!",brokenTools));
        }else {
            str.append(String.format("%d tools have been unfit while working on it!",brokenTools));
        }
        return str.toString();



    }

    @Override
    public String statistics() {
        StringBuilder str=new StringBuilder();
        int vehiclesReady=0;
        for (Vehicle vehicle:vehicleRepository.getVehicles()) {
            if (vehicle.reached()){
                vehiclesReady++;
            }
        }
        str.append(String.format("%d vehicles are ready!%n",vehiclesReady));
        str.append("Info for workers:");
        str.append(System.lineSeparator());
        for (Worker worker: workerRepository.getWorkers()) {
            str.append(worker.toString());
            str.append(System.lineSeparator());
        }
        return str.toString();
    }
}
