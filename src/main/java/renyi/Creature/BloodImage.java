package renyi.Creature;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BloodImage {
    private ImageView image1;
    private ImageView image2;
    public BloodImage()
    {
        image1=new ImageView();
        image2=new ImageView();
        image1.setImage(new javafx.scene.image.Image("/Image/bloodbackground.png"));
        image2.setImage(new Image("/Image/blood.png"));
        image1.setVisible(true);
        image2.setVisible(true);
    }
    public ImageView getImage1()
    {
        return this.image1;
    }
    public ImageView getImage2()
    {
        return this.image2;
    }
}
