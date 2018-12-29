package renyi.Battlearray;

import renyi.Creature.*;
import renyi.Creature.Field;

import java.util.ArrayList;

public class ForthBattlearray implements Battlearray {
    @Override
    public void createbattlearray(Field f, boolean left) {
        int N=f.N;
        int M=f.M;
        int numbers=1;
        Creature one;
        Soldiers two;
        Monstors three;
        if(left)
        {
            f.initSoldiers();
            f.setposition(N/2+3,M/6,one=new Grandpa(N/2+3,M/6,f));
            f.getSoldiers().add(two=new Soldiers(one));
            f.setposition(N/2,M/6,one=new Brothers(numbers++,N/2,M/6,f));
            f.getSoldiers().add(two=new Soldiers(one));
            for(int i=-1;i>-3;i--)
            {
                f.setposition(N/2+i,M/6,one=new Brothers(numbers++,N/2+i,M/6,f));
                f.getSoldiers().add(two=new Soldiers(one));
                f.setposition(N/2-i,M/6,one=new Brothers(numbers++,N/2-i,M/6,f));
                f.getSoldiers().add(two=new Soldiers(one));
            }
            f.setposition(N/2-3,M/6,one=new Brothers(numbers++,N/2-3,M/6,f));
            f.getSoldiers().add(two=new Soldiers(one));
            f.setposition(N/2-4,M/6,one=new Brothers(numbers,N/2-4,M/6,f));
            f.getSoldiers().add(two=new Soldiers(one));
        }
        else
        {
            f.initMonstors();
            f.setposition(N/2-4,5*M/6,one=new Snake_essence(N/2-4,5*M/6,f));
            f.getMonstors().add(three=new Monstors(one));
            f.setposition(N/2,5*M/6,one=new Scorpion(N/2,5*M/6,f));
            f.getMonstors().add(three=new Monstors(one));
            for(int i=-3;i<0;i++)
            {
                f.setposition(N/2+i,5*M/6,one=new Orchin(N/2+i,5*M/6,f));
                f.getMonstors().add(three=new Monstors(one));
                f.setposition(N/2-i,5*M/6,one=new Orchin(N/2-i,5*M/6,f));
                f.getMonstors().add(three=new Monstors(one));
            }
        }
    }
}
