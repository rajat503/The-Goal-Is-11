import greenfoot.*;
import java.awt.*;

public class Gameover extends Actor
{
    private String status;
    public Gameover(String status)
    {
        this.status=status;
        setImage(new GreenfootImage(status,72,Color.WHITE,Color.BLACK));
    }
}
