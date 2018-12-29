package renyi.Creature;

import renyi.Battlearray.*;
import renyi.Main;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Field {
    public int N,M;
    public int element_size;
    private List<List<Creature>> field=new ArrayList<List<Creature>>();
    private List<Soldiers> soldiers=new ArrayList<Soldiers>();
    private List<Monstors> monstors=new ArrayList<Monstors>();
    public Field()
    {
        InputStream is= Main.class.getClassLoader().getResourceAsStream("Field.properties");
        Properties ps = new Properties();
        try {
            assert is != null;
            ps.load(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        N= Integer.valueOf(ps.getProperty("field_N"));
        M= Integer.valueOf(ps.getProperty("field_M"));
        element_size= Integer.valueOf(ps.getProperty("element_size"));
        Creature one;
        for(int i=0;i<N;i++)
        {
            List<Creature> line=new ArrayList<Creature>();
            for(int j=0;j<M;j++)
                line.add(one=new Nullcreature(i,j));
            field.add(line);
        }
    }
    public List<Soldiers> getSoldiers()
    {
        return soldiers;
    }
    public List<Monstors> getMonstors()
    {
        return monstors;
    }
    public void initSoldiers()
    {
        soldiers=new ArrayList<Soldiers>();
    }
    public void initMonstors()
    {
        monstors=new ArrayList<Monstors>();
    }
    public List<List<Creature>> getField()
    {
        return this.field;
    }
    public void setposition(int x, int y, Creature one)
    {
        field.get(x).set(y,one);
    }
    private synchronized void move(Creature one, int x, int y)
    {
        synchronized(this)
        {
            Creature two;
            field.get(one.x).set(one.y,two=new Nullcreature(one.x,one.y));
            one.movecreature(x,y);
            field.get(x).set(y,one);
        }
    }
    synchronized void go(Creature one)
    {
        synchronized(this)
        {
            int flag=0;int flag2=0;
            if(one.y > 0)
            {
                if(field.get(one.x).get(one.y-1).getClass()!=Nullcreature.class&&field.get(one.x).get(one.y-1).isIfleft()!=one.isIfleft()&& !field.get(one.x).get(one.y - 1).isIfdead())
                {
                    one.battle(field.get(one.x).get(one.y-1));
                    flag2=1;
                }
            }
            if(flag2==0&&one.x>0)
            {
                if(field.get(one.x-1).get(one.y).getClass()!=Nullcreature.class&&field.get(one.x-1).get(one.y).isIfleft()!=one.isIfleft()&& !field.get(one.x - 1).get(one.y).isIfdead())
                {
                    one.battle(field.get(one.x-1).get(one.y));
                    flag2=1;
                }
            }
            if(flag2==0&&one.x<N-1)
            {
                if(field.get(one.x+1).get(one.y).getClass()!=Nullcreature.class&&field.get(one.x+1).get(one.y).isIfleft()!=one.isIfleft()&& !field.get(one.x + 1).get(one.y).isIfdead())
                {
                    one.battle(field.get(one.x+1).get(one.y));
                    flag2=1;
                }
            }
            if(flag2==0&&one.y<M-1)
            {
                if(field.get(one.x).get(one.y+1).getClass()!=Nullcreature.class&&field.get(one.x).get(one.y+1).isIfleft()!=one.isIfleft()&& !field.get(one.x).get(one.y + 1).isIfdead())
                {
                    one.battle(field.get(one.x).get(one.y+1));
                }
            }
             if(one.x < N - 1 && one.y > 0)
            {
                if(field.get(one.x+1).get(one.y-1).getClass()!=Nullcreature.class&&field.get(one.x+1).get(one.y-1).isIfleft()!=one.isIfleft()&& !field.get(one.x + 1).get(one.y - 1).isIfdead())
                {
                    if(field.get(one.x+1).get(one.y).getClass()==Nullcreature.class||field.get(one.x+1).get(one.y).isIfdead())
                    {
                        move(one,one.x+1,one.y);
                        flag=1;
                    }
                }
            }
            if(flag==0&&one.x<N-1&&one.y<M-1)
            {
                if(field.get(one.x+1).get(one.y+1).getClass()!=Nullcreature.class&&field.get(one.x+1).get(one.y+1).isIfleft()!=one.isIfleft()&& !field.get(one.x + 1).get(one.y + 1).isIfdead())
                {
                    if(field.get(one.x+1).get(one.y).getClass()==Nullcreature.class||field.get(one.x+1).get(one.y).isIfdead())
                    {
                        move(one,one.x+1,one.y);
                        flag=1;
                    }
                }
            }
            if(flag==0&&one.x>0&&one.y>0)
            {
                if(field.get(one.x-1).get(one.y-1).getClass()!=Nullcreature.class&&field.get(one.x-1).get(one.y-1).isIfleft()!=one.isIfleft()&& !field.get(one.x - 1).get(one.y - 1).isIfdead())
                {
                    if(field.get(one.x-1).get(one.y).getClass()==Nullcreature.class||field.get(one.x-1).get(one.y).isIfdead())
                    {
                        move(one,one.x-1,one.y);
                        flag=1;
                    }
                }
            }
             if(flag==0&&one.x>0&&one.y<M-1)
            {
                if(field.get(one.x-1).get(one.y+1).getClass()!=Nullcreature.class&&field.get(one.x-1).get(one.y+1).isIfleft()!=one.isIfleft()&& !field.get(one.x - 1).get(one.y + 1).isIfdead())
                {
                    if(field.get(one.x-1).get(one.y).getClass()==Nullcreature.class||field.get(one.x-1).get(one.y).isIfdead())
                    {
                        move(one,one.x-1,one.y);
                        flag=1;
                    }
                }
            }
            if(flag==0)
            {
                if(!one.isIfdead())
                {
                    Random q=new Random();
                    int b=q.nextInt(3)-1;
                    int c=q.nextInt(3)-1;
                    if(b==-1&&(one.x+c<N&&one.x+c>-1)&&(field.get(one.x+c).get(one.y).getClass()==Nullcreature.class||field.get(one.x+c).get(one.y).isIfdead()))
                        move(one, one.x + c, one.y );
                    else if(b==1&&(one.y+c<M&&one.y+c>-1)&&(field.get(one.x).get(one.y+c).getClass()==Nullcreature.class||field.get(one.x).get(one.y+c).isIfdead()))
                        move(one, one.x, one.y+c);
                    else
                        move(one, one.x , one.y );
                }
            }
        }
    }
    boolean isSoldiersalive()
    {
        for (Soldiers soldier : soldiers) {
            if (!soldier.one.isIfdead())
                return true;
        }
        return false;
    }
    boolean isMonstorsalive()
    {
        for (Monstors monstor : monstors) {
            if (!monstor.one.isIfdead())
                return true;
        }
        return false;
    }
    public void saveFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        if (file == null)
            JOptionPane.showMessageDialog(null, "没有选择文件");
        else {
            try {
                PrintWriter out = new PrintWriter(new FileOutputStream(file, true));
                for (Soldiers soldier : soldiers) out.print(soldier.one.getPath().size() + " ");
                for (Monstors monstor : monstors) out.print(monstor.one.getPath().size() + " ");
                out.println();
                for (Soldiers soldier : soldiers) {
                    int j;
                    int ifdead = 0;
                    if (soldier.one.isIfdead())
                        ifdead = 1;
                    for (j = 0; j < soldier.one.getPath().size() - 1; j++)
                        out.println(soldier.one.getPath().get(j).x + " " + soldier.one.getPath().get(j).y + " " + soldier.one.getBloodrecord().get(j));
                    out.println(soldier.one.getPath().get(j).x + " " + soldier.one.getPath().get(j).y + " " + soldier.one.getBloodrecord().get(j) + " " + ifdead);
                }
                for (Monstors monstor : monstors) {
                    int j;
                    int ifdead = 0;
                    if (monstor.one.isIfdead())
                        ifdead = 1;
                    for (j = 0; j < monstor.one.getPath().size() - 1; j++)
                        out.println(monstor.one.getPath().get(j).x + " " + monstor.one.getPath().get(j).y + " " + monstor.one.getBloodrecord().get(j));
                    out.println(monstor.one.getPath().get(j).x + " " + monstor.one.getPath().get(j).y + " " + monstor.one.getBloodrecord().get(j) + " " + ifdead);
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void makefield(boolean left,int c)
    {
        if(left)
        {
            for (Soldiers soldier : soldiers)
                setposition(soldier.one.getX(), soldier.one.getY(), new Nullcreature(soldier.one.getX(), soldier.one.getY()));
        }
        else
        {
            for (Monstors monstor : monstors)
                setposition(monstor.one.getX(), monstor.one.getY(), new Nullcreature(monstor.one.getX(), monstor.one.getY()));
        }
        Battlearray a;
        switch (c) {
            case 1:
                a = new FirstBattlearray();
                a.createbattlearray(this, left);
                break;
            case 2:
                a=new SecondBattlearray();
                a.createbattlearray(this,left);
                break;
            case 3:
                a=new ThirdBattlearray();
                a.createbattlearray(this,left);
                break;
            case 4:
                a=new ForthBattlearray();
                a.createbattlearray(this,left);
                break;
            case 5:
                a=new FifthBattlearray();
                a.createbattlearray(this,left);
                break;
            case 6:
                a=new SixthBattlearray();
                a.createbattlearray(this,left);
                break;
            case 7:
                a=new SeventhBattlearray();
                a.createbattlearray(this,left);
                break;
            case 8:
                a=new EigthBattlearray();
                a.createbattlearray(this,left);
                break;
        }
    }
}
