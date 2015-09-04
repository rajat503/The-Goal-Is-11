import greenfoot.*;

public class Background extends World
{
    public Background()
    {    
        super(700, 660, 1);
        Counter counter=new Counter();
        addObject(counter,350,75);
        Initialize game= new Initialize();
        addObject(game,0,0);
        game.act();
        Play controls=new Play(game, counter);
        addObject(controls,0,0);

    }
}
