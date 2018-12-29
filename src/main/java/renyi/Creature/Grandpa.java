package renyi.Creature;

import javafx.scene.image.Image;

import java.awt.*;

public class Grandpa extends Creature implements Runnable {
    public Grandpa(int x, int y, Field f)
    {
        super("/Image/10.png");
        this.Imageurl= "/Image/10.png";
        this.f=f;
        this.name="老头";
        this.ifleft=true;
        this.x=x;
        this.y=y;
        this.path.add(new Point(x,y));
        this.image.setImage(new Image(this.Imageurl));
        this.image.setVisible(true);
        this.image.setX(f.element_size*y+100);
        this.image.setY(f.element_size*x+5);
        this.image.setFitHeight(f.element_size-5);
        this.image.setFitWidth(f.element_size-5);
        this.bloodbackground.setImage(new Image("/Image/bloodbackground.png"));
        this.bloodbackground.setVisible(true);
        this.bloodbackground.setX(this.image.getX()+5);
        this.bloodbackground.setY(this.image.getY()-5);
        this.bloodbackground.setFitHeight(5);
        this.bloodbackground.setFitWidth(f.element_size-10);
        this.blood.setImage(new Image("/Image/blood.png"));
        this.blood.setVisible(true);
        this.blood.setX(this.image.getX()+5);
        this.blood.setY(this.image.getY()-5);
        this.blood.setFitHeight(5);
        this.blood.setFitWidth(f.element_size-10);
        this.blood_volume=40;
        this.blood_current_volumn=40;
        this.Aggressivity=8;
        this.Defense=7;
        this.bloodrecord.add(this.blood_current_volumn);
    }
    //@Override
    public void run() {
        while(!this.isIfdead() && this.f.isMonstorsalive() && this.f.isSoldiersalive())
        {
            try {
                f.go(this);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
