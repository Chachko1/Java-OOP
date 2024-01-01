package magicGame.models.region;

import magicGame.models.magicians.Magician;

import java.util.ArrayList;
import java.util.Collection;

public class RegionImpl implements Region{


    @Override
    public String start(Collection<Magician> magicians) {
        Collection<Magician> wizards=new ArrayList<>();
        Collection<Magician> blackWidows=new ArrayList<>();
        for (Magician magician:magicians) {
            if (magician.getClass().getSimpleName().equals("Wizard")){
                wizards.add(magician);
            }else {
                blackWidows.add(magician);
            }
        }

        while (!wizards.isEmpty()||!blackWidows.isEmpty()){
            Magician firstWizard=wizards.iterator().next();
            Magician firstBlackWidow=blackWidows.iterator().next();
            firstBlackWidow.takeDamage(firstWizard.getMagic().getBulletsCount());
            firstWizard.takeDamage(firstBlackWidow.getMagic().getBulletsCount());
            if (!firstBlackWidow.isAlive()){
                blackWidows.remove(firstBlackWidow);

            }
            if (!blackWidows.isEmpty()){
                firstBlackWidow=blackWidows.iterator().next();
            }



            if (!firstWizard.isAlive()){
                wizards.remove(firstWizard);
            }
            if (wizards.isEmpty()){
                return "Black widows win!";
            } else if (blackWidows.isEmpty()) {
                return "Wizards win!";
            }

        }
        return "problem with the start method";
    }
}
