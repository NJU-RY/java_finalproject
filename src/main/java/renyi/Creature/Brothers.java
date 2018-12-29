package renyi.Creature;

import javafx.scene.image.Image;

import java.awt.*;

public class Brothers extends Creature implements Runnable{
    public Brothers(int number, int x, int y, Field f)
    {
        super("/Image/" +number+".png");
        this.Imageurl= "/Image/" +number+".png";
        this.f=f;
        this.x=x;
        this.y=y;
        this.path.add(new Point(x,y));
        this.ifleft=true;
        switch(number)
        {
            case 1:
                this.name="老大";
                this.blood_volume=25;
                this.blood_current_volumn=25;
                this.Aggressivity=10;
                this.Defense=10;break;
            case 2:
                this.name="老二";
                this.blood_volume=20;
                this.blood_current_volumn=20;
                this.Aggressivity=8;
                this.Defense=5;break;
            case 3:
                this.name="老三";
                this.blood_volume=20;
                this.blood_current_volumn=20;
                this.Aggressivity=8;
                this.Defense=11;break;
            case 4:
                this.name="老四";
                this.blood_volume=20;
                this.blood_current_volumn=20;
                this.Aggressivity=12;
                this.Defense=6;break;
            case 5:
                this.name="老五";
                this.blood_volume=20;
                this.blood_current_volumn=20;
                this.Aggressivity=10;
                this.Defense=6;break;
            case 6:
                this.name="老六";
                this.blood_volume=30;
                this.blood_current_volumn=30;
                this.Aggressivity=9;
                this.Defense=9;break;
            case 7:this.name="老幺";
                this.blood_volume=30;
                this.blood_current_volumn=30;
                this.Aggressivity=6;
                this.Defense=6;break;
            default:break;
        }
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
