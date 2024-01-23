package vehicleShop.repositories;

import vehicleShop.models.worker.Worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WorkerRepository implements Repository<Worker>{
    private Collection<Worker> workers;

    public WorkerRepository() {
       workers=new ArrayList<>();
    }

    @Override
    public Collection<Worker> getWorkers() {
        return Collections.unmodifiableCollection(workers);
    }

    @Override
    public void add(Worker worker) {
        workers.add(worker);

    }

    @Override
    public boolean remove(Worker worker) {
        return workers.remove(worker);
    }

    @Override
    public Worker findByName(String name) {
        for (Worker worker:workers) {
            if (worker.getName().equals(name)){
                return worker;
            }
        }
        return null;
    }
}
