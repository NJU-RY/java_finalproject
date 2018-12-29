package Test;

import javafx.embed.swing.JFXPanel;
import org.testng.Assert;
import org.testng.annotations.Test;
import renyi.Creature.Field;
import renyi.Creature.Grandpa;
import renyi.Creature.Scorpion;

public class SetdeadTEST implements Runnable {
    final static Field f=new Field();
    boolean ifdead=false;
    public void init()
    {
        new JFXPanel();
        f.setposition(1,1,new Grandpa(1,1,f));
        f.setposition(1,2,new Scorpion(1,2,f));
    }

    @Override
    public void run() {
        init();
        while(!f.getField().get(1).get(1).isIfdead())
        {
            try {
                f.getField().get(1).get(2).battle(f.getField().get(1).get(1));
                f.getField().get(1).get(1).battle(f.getField().get(1).get(2));
                System.out.println(f.getField().get(1).get(1).getBlood_current_volumn());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test()
    {
        Thread one=new Thread(new SetdeadTEST());
        one.start();
        while(one.isAlive())
        { }
        if(f.getField().get(1).get(1).getBlood_current_volumn()==0)
            ifdead=true;
        Assert.assertEquals(f.getField().get(1).get(1).isIfdead(),ifdead);
    }
}
