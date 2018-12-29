package Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import renyi.Creature.Field;
public class FieldTEST {

    @Test
    public void testField()
    {
        Field f=new Field();
        Assert.assertEquals(f.N,16);
        Assert.assertEquals(f.M,20);
        Assert.assertEquals(f.element_size,50);
        System.out.println("空白棋盘");
        for(int i=0;i<f.N;i++)
        {
            for(int j=0;j<f.M;j++)
                System.out.print(f.getField().get(i).get(j).getName()+" ");
            System.out.println();
        }
    }

}
