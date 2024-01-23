package vehicleShop.repositories;

import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VehicleRepository implements Repository<Vehicle>{
    private Collection<Vehicle> vehicles;

    public VehicleRepository() {
        vehicles=new ArrayList<>();
    }


    public Collection<Vehicle> getVehicles() {
        return Collections.unmodifiableCollection(vehicles);
    }

    @Override
    public Collection<Vehicle> getWorkers() {
        return null;
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    @Override
    public Vehicle findByName(String name) {
        for (Vehicle vehicle:vehicles) {
            if (vehicle.getName().equals(name)){
                return vehicle;
            }
        }
        return null;
    }
}
