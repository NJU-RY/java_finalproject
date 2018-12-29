package renyi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import renyi.Creature.Field;
import renyi.Creature.Monstors;
import renyi.Creature.Soldiers;
import renyi.Replay.Replay;

import java.util.List;
import java.util.Random;

public class Main extends Application {
    private static Field f = new Field();
    private static Replay r=new Replay(f);
    private Pane board=new Pane();
    final boolean TRUE=true;
    final boolean FALSE=false;
    private int brothersarray=1;
    private int monstorsarray=1;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try
        {

            Scene scene=new Scene(board,f.M*f.element_size+200,f.N*f.element_size);
            ImageView background=new ImageView();
            background.setImage(new Image("/Image/background.png"));
            background.setFitHeight(f.N*f.element_size);
            background.setFitWidth(f.M*f.element_size);
            background.setX(100);
            background.setY(0);
            board.getChildren().add(background);

            f.makefield(true,brothersarray);
            f.makefield(false,monstorsarray);

            Button button1=new Button("葫芦娃阵型");
            button1.setLayoutX(0);
            button1.setLayoutY(400);
            button1.setPrefWidth(100);
            button1.setFocusTraversable(false);
            button1.setOnAction(new EventHandler<ActionEvent>() {
                //@Override
                public void handle(ActionEvent event) {
                    if(!r.isGamestart())
                    {
                        RemoveSoldiers(f.getSoldiers());
                        f.makefield(true,(++brothersarray)%8+1);
                        AddSoldiers(f.getSoldiers());
                    }
                }
            });
            board.getChildren().add(button1);

            Button button2=new Button("妖怪阵型");
            button2.setLayoutX(100+f.M*f.element_size);
            button2.setLayoutY(400);
            button2.setPrefWidth(100);
            button2.setFocusTraversable(false);
            button2.setVisible(true);
            button2.setOnAction(new EventHandler<ActionEvent>() {
                //@Override
                public void handle(ActionEvent event) {
                    if(!r.isGamestart())
                    {
                        RemoveMonstors(f.getMonstors());
                        f.makefield(false,(++monstorsarray)%8+1);
                        AddMonstors(f.getMonstors());
                    }
                }
            });
            board.getChildren().add(button2);

            AddSoldiers(f.getSoldiers());
            AddMonstors(f.getMonstors());
            primaryStage.setTitle("葫芦娃大战蛇精    (SPACE: START   L: READFILE   A: SAVEFILE)");
            primaryStage.setScene(scene);

            primaryStage.show();
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                //@Override
                public void handle(KeyEvent event) {
                    if(event.getCode()== KeyCode.SPACE&&r.endgame())
                    {
                        if(r.isEverreadfile()||r.isEverstart())
                        {
                            if(r.isEverreadfile())
                            {
                                for(int i=0;i<r.getALLIMAGE().size();i++)
                                    board.getChildren().remove(r.getALLIMAGE().get(i));
                                for(int i=0;i<r.getALLBLOODIMAGE().size();i++)
                                {
                                    board.getChildren().remove(r.getALLBLOODIMAGE().get(i).getImage1());
                                    board.getChildren().remove(r.getALLBLOODIMAGE().get(i).getImage2());
                                }
                                r.setEverreadfile(FALSE);
                            }
                            else
                            {
                                RemoveSoldiers(f.getSoldiers());
                                RemoveMonstors(f.getMonstors());
                                r.setEverstart(FALSE);
                            }
                            f.makefield(false,new Random().nextInt(7)+1);
                            f.makefield(true,new Random().nextInt(7)+1);
                            AddSoldiers(f.getSoldiers());
                            AddMonstors(f.getMonstors());
                        }
                        r.setGamestart(TRUE);
                        r.setEverstart(TRUE);
                        r.allthread();
                        for(int i=0;i<r.getALLTHREAD().size();i++)
                            r.getALLTHREAD().get(i).start();
                    }
                    else if(event.getCode()==KeyCode.A&&r.isEverstart())
                    {
                        r.endgame();
                        if(!r.isGamestart()&&r.isEverstart())
                        {
                            f.saveFile();
                        }
                    }
                    else if(event.getCode()==KeyCode.L)
                    {
                        if(r.isGamestart())
                            r.endgame();
                        if(!r.isGamestart())
                        {
                            if(r.readFile())
                            {
                                RemoveSoldiers(f.getSoldiers());
                                RemoveMonstors(f.getMonstors());
                                for(int i=0;i<r.getALLIMAGE().size();i++)
                                    board.getChildren().remove(r.getALLIMAGE().get(i));
                                for(int i=0;i<r.getALLBLOODIMAGE().size();i++)
                                {
                                    board.getChildren().remove(r.getALLBLOODIMAGE().get(i).getImage1());
                                    board.getChildren().remove(r.getALLBLOODIMAGE().get(i).getImage2());
                                }
                                r.InitReplayImage();
                                for(int i=0;i<16;i++)
                                {
                                    board.getChildren().add(r.getALLIMAGE().get(i));
                                    board.getChildren().add(r.getALLBLOODIMAGE().get(i).getImage1());
                                    board.getChildren().add(r.getALLBLOODIMAGE().get(i).getImage2());
                                }
                                Thread thread=new Thread(r);
                                thread.start();
                                r.setEverreadfile(TRUE);
                                r.setEverstart(FALSE);
                            }
                        }
                    }
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void RemoveSoldiers(List<Soldiers> a)
    {
        for(int i=0;i<a.size();i++)
        {
            board.getChildren().remove(a.get(i).one.getImage());
            board.getChildren().remove(a.get(i).one.getBloodbackground());
            board.getChildren().remove(a.get(i).one.getBlood());
        }
    }
    private void AddSoldiers(List<Soldiers> a)
    {
        for(int i=0;i<a.size();i++)
        {
            board.getChildren().add(a.get(i).one.getImage());
            board.getChildren().add(a.get(i).one.getBloodbackground());
            board.getChildren().add(a.get(i).one.getBlood());
        }
    }
    private void RemoveMonstors(List<Monstors> a)
    {
        for(int i=0;i<a.size();i++)
        {
            board.getChildren().remove(a.get(i).one.getImage());
            board.getChildren().remove(a.get(i).one.getBloodbackground());
            board.getChildren().remove(a.get(i).one.getBlood());
        }
    }
    private void AddMonstors(List<Monstors> a)
    {
        for(int i=0;i<a.size();i++)
        {
            board.getChildren().add(a.get(i).one.getImage());
            board.getChildren().add(a.get(i).one.getBloodbackground());
            board.getChildren().add(a.get(i).one.getBlood());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
