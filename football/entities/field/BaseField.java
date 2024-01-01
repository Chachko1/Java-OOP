package football.entities.field;

import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseField implements Field{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.supplements=new ArrayList<>();
        this.players=new ArrayList<>();
    }

    public void setName(String name) {
        if (name==null||name.equals("")){
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumEnergy() {
        int sum=supplements.stream()
                .mapToInt(Supplement::getEnergy) // Assuming getEnergy returns an int value
                .sum();
       return sum;
    }

    @Override
    public void addPlayer(Player player) {
        if (capacity<=players.size()){
            throw new IllegalStateException("Not enough capacity.");
        }
        switch (this.getClass().getSimpleName()){
            case "ArtificialTurf":
                    if (player.getClass().getSimpleName().equals("Women")){
                        players.add(player);
                    }
                break;

            case "NaturalGrass":
                if (player.getClass().getSimpleName().equals("Men")){
                    players.add(player);
                }
                break;
        }

    }

    @Override
    public void removePlayer(Player player) {
       this.players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player player:players) {
            player.stimulation();
        }
    }

    @Override
    public String getInfo() {
//        Returns a String with information about the Field in the format below. If the Field doesn't have a player, print "none" instead.
//        "{fieldName} ({fieldType}):
//        Player: {playerName1} {playerName2} {playerName3} (…) / Player: none
//        Supplement: {supplementsCount}
//        Energy: {sumEnergy}"
        StringBuilder str=new StringBuilder();
        str.append(String.format("%s (%s):%n",this.name,this.getClass().getSimpleName()));
        if (players.isEmpty()){
            str.append("Player: none");
            str.append(System.lineSeparator());
        }else {
            for (Player player:players) {
                str.append(player.getName()+" ");
            }
        }
        str.append(System.lineSeparator());
        str.append(String.format("Supplement: %s%n",supplements.size()));
        str.append(String.format("Energy: %s",this.sumEnergy()));
        return str.toString();


    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
