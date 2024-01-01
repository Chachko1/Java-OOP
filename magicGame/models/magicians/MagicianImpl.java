package magicGame.models.magicians;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

import java.util.Collections;
import java.util.Comparator;

public class MagicianImpl implements Magician{
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public MagicianImpl(String username, int health, int protection,  Magic magic) {
       this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
       this.setMagic(magic);
        this.isAlive=true;
    }

    private void setUsername(String username) {
        if (username==null||username.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health<0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    private void setProtection(int protection) {
        if (protection<0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    private void setMagic(Magic magic) {
        if (magic==null){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC);
        }
        this.magic = magic;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void takeDamage(int points) {
        if (this.protection-points<0){
            points=points-protection;
            this.setProtection(0);
            if (health-points<=0){
                this.isAlive=false;
                this.setHealth(0);
            }else {
                this.setHealth(health-points);
            }


        }else {
            this.setProtection(this.getProtection()-points);
        }


    }

    @Override
    public String toString() {

        StringBuilder str=new StringBuilder();
        str.append(String.format("%s: %s",this.getClass().getSimpleName(),this.username));
        str.append(System.lineSeparator());
        str.append(String.format("Health: %d",this.health));
        str.append(System.lineSeparator());
        str.append(String.format("Protection: %d",this.protection));
        str.append(System.lineSeparator());
        str.append(String.format("Magic: %s",this.magic.getName()));
        return str.toString().trim();
    }
}
