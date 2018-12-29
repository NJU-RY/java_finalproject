package Test;

import javafx.embed.swing.JFXPanel;
import org.testng.annotations.Test;
import renyi.Creature.Field;

public class BattlearrayTEST {
    @Test
    public void testmakefield()
    {
        new JFXPanel();
        Field f=new Field();
        for(int i=1;i<9;i++)
        {
            f.makefield(true,i);
            f.makefield(false,i);
            for(int x=0;x<f.N;x++)
            {
                for(int y=0;y<f.M;y++)
                    System.out.print(f.getField().get(x).get(y).getName()+" ");
                System.out.println();
            }
            System.out.println('\n');
        }
    }
}
