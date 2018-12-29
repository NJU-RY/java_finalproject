package renyi.Creature;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Creature implements Runnable {
    protected int x;
    protected int y;
    protected String name;
    protected String Imageurl;
    private boolean ifdead=false;
    protected ImageView image=new ImageView();
    protected ImageView bloodbackground=new ImageView();
    protected ImageView blood=new ImageView();
    protected int blood_volume;
    protected int blood_current_volumn;
    protected int Aggressivity;
    protected int Defense;
    protected boolean ifleft;
    protected Field f;
    protected List<Integer> bloodrecord=new ArrayList<Integer>();
    protected List<Point> path=new ArrayList<Point>();
    void setdead(Creature one)
    {
            one.ifdead= true;
            one.blood_current_volumn=0;
            System.out.println(one.name+"在"+one.x+" "+one.y+"死了！  步长："+one.path.size());
            if(one.isIfleft())
                one.Imageurl= "/Image/dead1.png";
            else
                one.Imageurl= "/Image/dead.png";
            one.image.setImage(new Image(one.Imageurl));
            one.bloodbackground.setVisible(false);
            one.blood.setVisible(false);
    }
    public synchronized void battle(Creature one)
    {
        synchronized(this)
        {
            System.out.println(this.name+": "+this.blood_current_volumn+"    "+one.name+": "+one.blood_current_volumn);
            //System.out.println(this.name+"   "+this.x+"   "+this.y);
            //System.out.println(one.name+"   "+one.x+"   "+one.y);
            if(this.Aggressivity<=one.Defense)
                one.blood_current_volumn-=1;
            else
                one.blood_current_volumn-=(this.Aggressivity-one.Defense)*1;
            if(one.blood_current_volumn<=0)
                setdead(one);
            else
                one.blood.setFitWidth(40*one.blood_current_volumn/one.blood_volume);
        }
    }
    public int getBlood_volume()
    {
        return this.blood_volume;
    }
    boolean isIfleft()
    {
        return this.ifleft;
    }
    public boolean isIfdead()
    {
        return ifdead;
    }
    synchronized void movecreature(int x, int y)
    {
        synchronized(this)
        {
            this.image.setX(f.element_size*y+100);
            this.image.setY(f.element_size*x+5);
            this.bloodbackground.setY(this.image.getY()-5);
            this.bloodbackground.setX(this.image.getX()+5);
            this.blood.setY(this.image.getY()-5);
            this.blood.setX(this.image.getX()+5);
            this.x=x;
            this.y=y;
            this.path.add(new Point(x,y));
            this.bloodrecord.add(this.blood_current_volumn);
        }
    }
    public List<Integer> getBloodrecord()
    {
        return this.bloodrecord;
    }
    public List<Point> getPath()
    {
        return this.path;
    }
    public ImageView getImage()
    {
        return this.image;
    }
    public ImageView getBlood()
    {
        return this.blood;
    }
    public ImageView getBloodbackground()
    {
        return this.bloodbackground;
    }
    public String getName()
    {
        return this.name;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public int getBlood_current_volumn()
    {
        return this.blood_current_volumn;
    }
    public Creature(String s)
    { }
}
