package renyi.Creature;


public class Nullcreature extends Creature implements Runnable{
    Nullcreature(int x,int y)
    {
        super(null);
        this.Imageurl=null;
        this.x=x;
        this.y=y;
        this.name="####";
    }

    @Override
    public void run() {

    }
}
