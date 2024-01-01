package football.core;


import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;


import java.util.LinkedHashMap;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
   private LinkedHashMap<String,Field> fields;

    public ControllerImpl() {
        supplement=new SupplementRepositoryImpl();
        fields=new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
            if (fieldType.equals("ArtificialTurf")){
                field=new ArtificialTurf(fieldName);
            } else if (fieldType.equals("NaturalGrass")) {
                field=new NaturalGrass(fieldName);
            }else {
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
            }
            fields.put(fieldName,field);
            return String.format("Successfully added %s.",fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplementToAdd;
        if (type.equals("Powdered")){
            supplementToAdd=new Powdered();
        } else if (type.equals("Liquid")) {
            supplementToAdd=new Liquid();
        }else {
            throw new NullPointerException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplement.add(supplementToAdd);
        return String.format("Successfully added %s.",type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
           Supplement supplementToAdd =supplement.findByType(supplementType);
           if (supplementToAdd==null){
               throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND,fieldName));
           }else {
               fields.get(fieldName).getSupplements().add(supplementToAdd);
               supplement.remove(supplementToAdd);
               return String.format("Successfully added %s to %s.",supplementType,fieldName);
           }

    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;

        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
            if (fields.get(fieldName).getClass().getSimpleName().equals("NaturalGrass")){
                fields.get(fieldName).addPlayer(player);
                return String.format("Successfully added %s to %s.",playerType,fieldName);
            }else {
                return "The pavement of the terrain is not suitable.";
            }
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
            if (fields.get(fieldName).getClass().getSimpleName().equals("ArtificialTurf")){
                fields.get(fieldName).addPlayer(player);
                return String.format("Successfully added %s to %s.",playerType,fieldName);
            }else {
                return "The pavement of the terrain is not suitable.";
            }
        }else{
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

    }

    @Override
    public String dragPlayer(String fieldName) {
         Field field=fields.get(fieldName);
        field.drag();
        return String.format("Player drag: %d",field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        int value=0;
        Field field=fields.get(fieldName);
        for (Player player:field.getPlayers()) {
            value+=player.getStrength();
        }
        return String.format("The strength of Field %s is %d.",fieldName,value);
    }

    @Override
    public String getStatistics() {
        StringBuilder str=new StringBuilder();
        for (Field field:fields.values()) {
            str.append(field.getInfo());
            str.append(System.lineSeparator());
        }
        return str.toString();
    }
}
