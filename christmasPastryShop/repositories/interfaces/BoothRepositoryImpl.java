package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoothRepositoryImpl implements BoothRepository<Booth>{
    private Collection<Booth> models;
    public BoothRepositoryImpl() {
        this.models=new ArrayList<>();
    }

    @Override
    public Booth getByNumber(int number) {
        for (Booth booth:models) {
            if (booth.getBoothNumber()==number){
                return booth;
            }
        }
        return null;
    }

    @Override
    public Collection<Booth> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Booth booth) {
        this.models.add(booth);
    }
}
