package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;

public class MagicRepositoryImpl implements MagicRepository<Magic>{
    private Collection<Magic> data;

    public MagicRepositoryImpl() {
        this.data=new ArrayList<>();
    }

    @Override
    public Collection<Magic> getData() {
       return this.data;
    }

    @Override
    public void addMagic(Magic model) {

        if (model==null){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_REPOSITORY);
        }
        this.data.add(model);
    }

    @Override
    public boolean removeMagic(Magic model) {
        return this.data.remove(model);
    }

    @Override
    public Magic findByName(String name) {
        for (Magic magic:this.data) {
            if (magic.getName().equals(name)){
                return magic;
            }
        }
        return null;
    }
}
