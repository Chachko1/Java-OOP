package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl implements DelicacyRepository<Delicacy> {
    private Collection<Delicacy> models;

    public DelicacyRepositoryImpl() {
        this.models=new ArrayList<>();
    }

    @Override
    public Delicacy getByName(String name) {

        for (Delicacy de:models) {
            if (de.getName().equals(name)){
                return de;
            }
        }
        return null;
    }

    @Override
    public Collection<Delicacy> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Delicacy delicacy) {
        this.models.add(delicacy);
    }
}
