package renyi.Replay;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import renyi.Creature.BloodImage;
import renyi.Creature.Field;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Replay implements Runnable{
    private Field f;
    private boolean gamestart=false;
    private boolean everstart=false;
    private boolean everreadfile=false;
    private List<Thread> ALLTHREAD=new ArrayList<Thread>();
    private List<List<Point>> ALLPATH=new ArrayList<List<Point>>();
    private List<ImageView> ALLIMAGE=new ArrayList<ImageView>();
    private List<Boolean> ALLDEATH=new ArrayList<Boolean>();
    private List<List<Integer>> ALLBLOOD=new ArrayList<List<Integer>>();
    private List<BloodImage> ALLBLOODIMAGE=new ArrayList<BloodImage>();
    private int maxpathsize;
    public Replay(Field f)
    {
        this.f=f;
    }
    //@Override
    public void run() {
        int i=0;
        while(i<=maxpathsize)
        {
            try{
                for (int j = 0; j < 16; j++) {
                    if (i <ALLPATH.get(j).size()) {
                        ALLIMAGE.get(j).setX(f.element_size* ALLPATH.get(j).get(i).y + 100);
                        ALLIMAGE.get(j).setY(f.element_size* ALLPATH.get(j).get(i).x+5);
                        ALLBLOODIMAGE.get(j).getImage1().setX(ALLIMAGE.get(j).getX()+5);
                        ALLBLOODIMAGE.get(j).getImage1().setY(ALLIMAGE.get(j).getY()-5);
                        ALLBLOODIMAGE.get(j).getImage2().setX(ALLIMAGE.get(j).getX()+5);
                        ALLBLOODIMAGE.get(j).getImage2().setY(ALLIMAGE.get(j).getY()-5);
                        if(j<8)
                            ALLBLOODIMAGE.get(j).getImage2().setFitWidth(40*ALLBLOOD.get(j).get(i)/f.getSoldiers().get(j).one.getBlood_volume());
                        else
                            ALLBLOODIMAGE.get(j).getImage2().setFitWidth(40*ALLBLOOD.get(j).get(i)/f.getMonstors().get(j-8).one.getBlood_volume());
                    } else if (i == ALLPATH.get(j).size()&&ALLDEATH.get(j)) {
                        if (j < 8)
                        {
                            ALLIMAGE.get(j).setImage(new Image("/Image/dead1.png"));
                            ALLBLOODIMAGE.get(j).getImage1().setVisible(false);
                            ALLBLOODIMAGE.get(j).getImage2().setVisible(false);
                        }
                        else
                        {
                            ALLIMAGE.get(j).setImage(new Image("/Image/dead.png"));
                            ALLBLOODIMAGE.get(j).getImage1().setVisible(false);
                            ALLBLOODIMAGE.get(j).getImage2().setVisible(false);
                        }
                    }
                }
                i++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public List<BloodImage> getALLBLOODIMAGE()
    {
        return this.ALLBLOODIMAGE;
    }
    public List<ImageView> getALLIMAGE()
    {
        return this.ALLIMAGE;
    }
    public void InitReplayImage()
    {
        ALLIMAGE=new ArrayList<ImageView>();
        ALLBLOODIMAGE=new ArrayList<BloodImage>();
        for(int i=0;i<16;i++)
        {
            ALLIMAGE.add(new ImageView());
            ALLBLOODIMAGE.add(new BloodImage());
        }
        ALLIMAGE.get(0).setImage(new Image("/Image/10.png"));
        ALLIMAGE.get(1).setImage(new Image("Image/1.png"));
        ALLIMAGE.get(2).setImage(new Image("Image/2.png"));
        ALLIMAGE.get(3).setImage(new Image("Image/3.png"));
        ALLIMAGE.get(4).setImage(new Image("Image/4.png"));
        ALLIMAGE.get(5).setImage(new Image("Image/5.png"));
        ALLIMAGE.get(6).setImage(new Image("Image/6.png"));
        ALLIMAGE.get(7).setImage(new Image("Image/7.png"));
        ALLIMAGE.get(8).setImage(new Image("Image/9.png"));
        ALLIMAGE.get(9).setImage(new Image("Image/11.png"));
        ALLIMAGE.get(10).setImage(new Image("Image/8.png"));
        ALLIMAGE.get(11).setImage(new Image("Image/8.png"));
        ALLIMAGE.get(12).setImage(new Image("Image/8.png"));
        ALLIMAGE.get(13).setImage(new Image("Image/8.png"));
        ALLIMAGE.get(14).setImage(new Image("Image/8.png"));
        ALLIMAGE.get(15).setImage(new Image("Image/8.png"));
        for(int i=0;i<16;i++)
        {
            ALLIMAGE.get(i).setVisible(true);
            ALLIMAGE.get(i).setX(f.element_size*ALLPATH.get(i).get(0).y+100);
            ALLIMAGE.get(i).setY(f.element_size*ALLPATH.get(i).get(0).x+5);
            ALLIMAGE.get(i).setFitWidth(f.element_size-5);
            ALLIMAGE.get(i).setFitHeight(f.element_size-5);
            ALLBLOODIMAGE.get(i).getImage1().setX(ALLIMAGE.get(i).getX()+5);
            ALLBLOODIMAGE.get(i).getImage1().setY(ALLIMAGE.get(i).getY()-5);
            ALLBLOODIMAGE.get(i).getImage1().setFitHeight(5);
            ALLBLOODIMAGE.get(i).getImage1().setFitWidth(f.element_size-10);
            ALLBLOODIMAGE.get(i).getImage2().setX(ALLIMAGE.get(i).getX()+5);
            ALLBLOODIMAGE.get(i).getImage2().setY(ALLIMAGE.get(i).getY()-5);
            ALLBLOODIMAGE.get(i).getImage2().setFitHeight(5);
            ALLBLOODIMAGE.get(i).getImage2().setFitWidth(f.element_size-10);
        }
    }
    public void allthread()
    {
        ALLTHREAD=new ArrayList<Thread>();
        Thread S_one=new Thread(f.getSoldiers().get(0).one);
        Thread S_two=new Thread(f.getSoldiers().get(1).one);
        Thread S_three=new Thread(f.getSoldiers().get(2).one);
        Thread S_four=new Thread(f.getSoldiers().get(3).one);
        Thread S_five=new Thread(f.getSoldiers().get(4).one);
        Thread S_six=new Thread(f.getSoldiers().get(5).one);
        Thread S_seven=new Thread(f.getSoldiers().get(6).one);
        Thread S_eight=new Thread(f.getSoldiers().get(7).one);
        Thread M_one=new Thread(f.getMonstors().get(0).one);
        Thread M_two=new Thread(f.getMonstors().get(1).one);
        Thread M_three=new Thread(f.getMonstors().get(2).one);
        Thread M_four=new Thread(f.getMonstors().get(3).one);
        Thread M_five=new Thread(f.getMonstors().get(4).one);
        Thread M_six=new Thread(f.getMonstors().get(5).one);
        Thread M_seven=new Thread(f.getMonstors().get(6).one);
        Thread M_eight=new Thread(f.getMonstors().get(7).one);
        ALLTHREAD.add(S_one);
        ALLTHREAD.add(S_two);
        ALLTHREAD.add(S_three);
        ALLTHREAD.add(S_four);
        ALLTHREAD.add(S_five);
        ALLTHREAD.add(S_six);
        ALLTHREAD.add(S_seven);
        ALLTHREAD.add(S_eight);
        ALLTHREAD.add(M_one);
        ALLTHREAD.add(M_two);
        ALLTHREAD.add(M_three);
        ALLTHREAD.add(M_four);
        ALLTHREAD.add(M_five);
        ALLTHREAD.add(M_six);
        ALLTHREAD.add(M_seven);
        ALLTHREAD.add(M_eight);
    }
    public List<Thread> getALLTHREAD()
    {
        return this.ALLTHREAD;
    }
    public boolean readFile()
    {
        maxpathsize=0;
        ALLDEATH=new ArrayList<Boolean>();
        ALLBLOOD=new ArrayList<List<Integer>>();
        ALLPATH=new ArrayList<List<Point>>();
        List<Point> s1 = new ArrayList<Point>();
        List<Point> s2 = new ArrayList<Point>();
        List<Point> s3 = new ArrayList<Point>();
        List<Point> s4 = new ArrayList<Point>();
        List<Point> s5 = new ArrayList<Point>();
        List<Point> s6 = new ArrayList<Point>();
        List<Point> s7 = new ArrayList<Point>();
        List<Point> s8 = new ArrayList<Point>();
        List<Point> m1 = new ArrayList<Point>();
        List<Point> m2 = new ArrayList<Point>();
        List<Point> m3 = new ArrayList<Point>();
        List<Point> m4 = new ArrayList<Point>();
        List<Point> m5 = new ArrayList<Point>();
        List<Point> m6 = new ArrayList<Point>();
        List<Point> m7 = new ArrayList<Point>();
        List<Point> m8 = new ArrayList<Point>();
        ALLPATH.add(s1);
        ALLPATH.add(s2);
        ALLPATH.add(s3);
        ALLPATH.add(s4);
        ALLPATH.add(s5);
        ALLPATH.add(s6);
        ALLPATH.add(s7);
        ALLPATH.add(s8);
        ALLPATH.add(m1);
        ALLPATH.add(m2);
        ALLPATH.add(m3);
        ALLPATH.add(m4);
        ALLPATH.add(m5);
        ALLPATH.add(m6);
        ALLPATH.add(m7);
        ALLPATH.add(m8);
        List<Integer> s1b = new ArrayList<Integer>();
        List<Integer> s2b = new ArrayList<Integer>();
        List<Integer> s3b = new ArrayList<Integer>();
        List<Integer> s4b = new ArrayList<Integer>();
        List<Integer> s5b = new ArrayList<Integer>();
        List<Integer> s6b = new ArrayList<Integer>();
        List<Integer> s7b = new ArrayList<Integer>();
        List<Integer> s8b = new ArrayList<Integer>();
        List<Integer> m1b = new ArrayList<Integer>();
        List<Integer> m2b = new ArrayList<Integer>();
        List<Integer> m3b = new ArrayList<Integer>();
        List<Integer> m4b = new ArrayList<Integer>();
        List<Integer> m5b = new ArrayList<Integer>();
        List<Integer> m6b = new ArrayList<Integer>();
        List<Integer> m7b = new ArrayList<Integer>();
        List<Integer> m8b = new ArrayList<Integer>();
        ALLBLOOD.add(s1b);
        ALLBLOOD.add(s2b);
        ALLBLOOD.add(s3b);
        ALLBLOOD.add(s4b);
        ALLBLOOD.add(s5b);
        ALLBLOOD.add(s6b);
        ALLBLOOD.add(s7b);
        ALLBLOOD.add(s8b);
        ALLBLOOD.add(m1b);
        ALLBLOOD.add(m2b);
        ALLBLOOD.add(m3b);
        ALLBLOOD.add(m4b);
        ALLBLOOD.add(m5b);
        ALLBLOOD.add(m6b);
        ALLBLOOD.add(m7b);
        ALLBLOOD.add(m8b);
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        if (file == null)
        {
            JOptionPane.showMessageDialog(null, "没有选择文件");
            return false;
        }
        else {
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                int linenumber=1;
                int[] size={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                String temp;
                String[] arrs;
                int m=0;
                while((temp=br.readLine())!=null)
                {
                    arrs=temp.split(" ");
                    if(linenumber==1)
                    {
                        for(int i=0;i<16;i++)
                        {
                            if(i==0)
                                size[0]=1+Integer.parseInt(arrs[0]);
                            else
                                size[i]=size[i-1]+Integer.parseInt(arrs[i]);
                        }
                        for(int i=0;i<16;i++)
                        {
                            if(Integer.parseInt(arrs[i])>maxpathsize)
                                maxpathsize=Integer.parseInt(arrs[i]);
                            //System.out.println(size[i]);
                        }
                        linenumber++;
                    }
                    else
                    {
                        ALLPATH.get(m).add(new Point(Integer.parseInt(arrs[0]),Integer.parseInt(arrs[1])));
                        ALLBLOOD.get(m).add(Integer.parseInt(arrs[2]));
                        if(linenumber==size[m])
                        {
                            if(Integer.parseInt(arrs[3])==1)
                                ALLDEATH.add(true);
                            else
                                ALLDEATH.add(false);
                            m++;
                        }
                        linenumber++;
                    }
                    arrs=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean isGamestart()
    {return this.gamestart;}
    public boolean isEverstart()
    {return this.everstart; }
    public boolean isEverreadfile()
    {return this.everreadfile;}
    public boolean endgame()
    {
        if(ALLTHREAD.size()==0)
            return true;
        for (Thread thread : ALLTHREAD) {
            if (thread.isAlive())
                return false;
        }
        gamestart=false;
        return true;
    }
    public void setGamestart(boolean a)
    {
        this.gamestart=a;
    }
    public void setEverstart(boolean a)
    {
        this.everstart=a;
    }
    public void setEverreadfile(boolean a)
    {
        this.everreadfile=a;
    }
}
