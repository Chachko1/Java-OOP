package magicGame.core;

import magicGame.common.ExceptionMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepository;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepository;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ControllerImpl implements Controller{
    private MagicRepository<Magic> magics;
    private MagicianRepository<Magician> magicians;
    private Region region;

    public ControllerImpl() {
        this.magics=new MagicRepositoryImpl();
        this.magicians=new MagicianRepositoryImpl();
        this.region=new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        if (type.equals("RedMagic")){
            magic=new RedMagic(name,bulletsCount);
        } else if (type.equals("BlackMagic")) {
            magic=new BlackMagic(name,bulletsCount);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_TYPE);
        }
        magics.addMagic(magic);
        return String.format("Successfully added magic %s.",name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magician magician;
        Magic magic=magics.findByName(magicName);
        if (magic==null){
            throw new NullPointerException(ExceptionMessages.MAGIC_CANNOT_BE_FOUND);
        }
        if (type.equals("Wizard")){
            magician=new Wizard(username,health,protection,magic);
        } else if (type.equals("BlackWidow")) {
            magician=new BlackWidow(username,health,protection,magic);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_TYPE);
        }
        magicians.addMagician(magician);
        return String.format("Successfully added magician %s.",username);
    }

    @Override
    public String startGame() {
      return  region.start(magicians.getData());

    }

    @Override
    public String report() {
        Comparator<Magician> magicianComparator = Comparator
                .comparingInt(Magician::getHealth)
                .thenComparing(Magician::getUsername);
        List<Magician> magicianList = (List<Magician>) magicians.getData();
        Collections.sort(magicianList, magicianComparator);
        StringBuilder stringBuilder=new StringBuilder();
        for (Magician magician:magicianList) {
            stringBuilder.append(magician.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();

    }
}
