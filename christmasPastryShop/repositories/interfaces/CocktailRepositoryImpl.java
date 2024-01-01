package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private Collection<Cocktail> models;
    public CocktailRepositoryImpl() {
        this.models=new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        for (Cocktail cocktail:models) {
            if (cocktail.getName().equals(name)){
                return cocktail;
            }
        }
        return null;
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Cocktail cocktail) {
        this.models.add(cocktail);
    }
}
