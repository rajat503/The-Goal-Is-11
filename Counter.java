import greenfoot.*;
import java.awt.*;

public class Counter extends Actor
{
    private int score;
    public Counter()
    {
        score=0;
        update();
    }
    public void update()
    {
        setImage(new GreenfootImage(" Score: "+score+" ",72,Color.WHITE,Color.BLACK));
    }   
    public void setscore(int score)
    {
        this.score=score;
        update();
    }
    public int getscore()
    {
        return score;
    }
}
