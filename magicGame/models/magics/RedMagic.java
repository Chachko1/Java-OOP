package magicGame.models.magics;

public class RedMagic extends MagicImpl{
    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount()-1<0){
            return 0;
        }
        this.setBulletsCount(this.getBulletsCount()-1);
        return this.getBulletsCount();
    }
}
