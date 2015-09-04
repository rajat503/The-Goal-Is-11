import greenfoot.*;

public class Initialize extends Actor
{
    private boolean first=true;
    public Block gamearray[][]=new Block[5][5];
    
    public void act() 
    {
        int i,j; 
        World w=getWorld();
        if(first)
        {
            for(i=0;i<5;i++)
            {
                for(j=0;j<5;j++)
                {
                    gamearray[i][j]=new Block((int)(Math.ceil(Math.random()*2)));
                    gamearray[i][j].setImage("numbers/"+gamearray[i][j].number+".png");
                    w.addObject(gamearray[i][j],150+j*100,200+i*100);
                }
            }
            first=false;
        }    
    }
}
