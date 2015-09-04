import greenfoot.*;

public class Play extends Actor
{
    private Block gamearray[][];
    private boolean betweenClicks;
    private int value;
    private Counter counter;
    public Play(Initialize game, Counter counter)
    {
        gamearray=game.gamearray;
        betweenClicks=false;
        value=0;
        this.counter=counter;
    }

    public void converttozero(int x,int y)
    {
        if(gamearray[x][y].number==value)
        {
            gamearray[x][y].number=0;
        }
        else
        {
            return;
        }
        if(x+1!=5)
        {
            converttozero(x+1,y);
        }
        if(x!=0)
        {
            converttozero(x-1,y);

        }
        if(y+1!=5)
        {
            converttozero(x,y+1);
        }
        if(y!=0)
        {
            converttozero(x,y-1);
        }
        return;
    }

    public int countzeros()
    {
        int i,j,noz=0;
        for(i=0;i<5;i++)
        {
            for(j=0;j<5;j++)
            {
                if(gamearray[i][j].number==0)
                {
                    noz++;
                }
            }
        }
        return noz;
    }

    public void merge(int x2, int y2)
    {
        gamearray[x2][y2].number=value+1;
    }

    public void revert()
    {
        int i,j;
        for(i=0;i<5;i++)
        {
            for(j=0;j<5;j++)
            {
                if(gamearray[i][j].number==0)
                {
                    gamearray[i][j].number=value;
                    gamearray[i][j].setImage("numbers/"+value+".png");
                }
            }
        }
    }

    public void pushdown()
    {
        int i,j;
        for(i=0;i<5;i++)
        {
            for(j=0;j<5;j++)
            {
                if(gamearray[j][i].number==0)
                {
                    for(int k=j;k>0;k--)
                    {
                        gamearray[k][i].number=gamearray[k-1][i].number;
                    }
                    gamearray[0][i].number=0;
                }
            }
        }
    }

    public void fillboard()
    {
        int i,j;
        for(i=4;i>=0;i--)
        {
            for(j=0;j<5;j++)
            {
                if(gamearray[i][j].number==0)
                {
                    int max=0;
                    for(int k=0;k<5;k++)
                    {
                        for(int l=0;l<5;l++)
                        {
                            if(gamearray[k][l].number>max)
                            {
                                max=gamearray[k][l].number;
                            }
                        }
                    }
                    gamearray[i][j].number=(int)(Math.random() *(max-1)) + 1;
                }
            }
        }
    }

    public boolean checkstatus()
    {
        boolean gameover=true;
        int i,j;
        for(i=0;i<5;i++)
        {
            for(j=0;j<5;j++)
            {
                if(gamearray[i][j].number==11)
                {
                    World w=getWorld();
                    w.addObject(new Gameover(" You Win! "),w.getWidth()/2,w.getHeight()/2);
                    Greenfoot.stop();
                }
                if(i!=4 && gamearray[i+1][j].number==gamearray[i][j].number)
                {
                    gameover=false;
                }
                if(i!=0 && gamearray[i-1][j].number==gamearray[i][j].number)
                {
                    gameover=false;
                }
                if(j!=0 && gamearray[i][j-1].number==gamearray[i][j].number)
                {
                    gameover=false;
                }
                if(j!=4 && gamearray[i][j+1].number==gamearray[i][j].number)
                {
                    gameover=false;
                }
            }
        }
        return gameover;

    }

    public void updateSelection()
    {
        int i,j;
        for(i=0;i<5;i++)
        {
            for(j=0;j<5;j++)
            {
                if(gamearray[i][j].number==0)
                {
                    gamearray[i][j].setImage("numbers/"+value+"s.png");
                }
            }
        }       
    }

    public void updateBoard()
    {
        int i,j;
        for(i=0;i<5;i++)
        {
            for(j=0;j<5;j++)
            {
                gamearray[i][j].setImage("numbers/"+gamearray[i][j].number+".png");
            }
        }
        
    }
    public void computeScore()
    {
        if(value<5)
        {
            counter.setscore(counter.getscore()+countzeros()*(value+1)*(value+1));
        }
        else
        {
        counter.setscore(counter.getscore()+countzeros()*value*value*value);
        }
        
        
    }
    
    public void act() 
    {
        int x1=0,y1=0,x2=0,y2=0;

        if (!betweenClicks && Greenfoot.mouseClicked(null))
        {
            MouseInfo mouse=Greenfoot.getMouseInfo();
            if(mouse.getX()>100 && mouse.getX()<600 && mouse.getY()>150 && mouse.getY()<650)
            {
                betweenClicks = true;
                y1=(mouse.getX()-100)/100;
                x1=(mouse.getY()-150)/100;
                value=gamearray[x1][y1].number;
                converttozero(x1,y1);
                if(countzeros()==1)
                {
                    gamearray[x1][y1].number=value;
                    betweenClicks=false;
                }
                else
                {
                    updateSelection();
                }
            }
            return;
        }
        if (betweenClicks && Greenfoot.mouseClicked(null))
        {
            betweenClicks = false;
            MouseInfo mouse2=Greenfoot.getMouseInfo();
            if(mouse2.getX()>100 && mouse2.getX()<600 && mouse2.getY()>150 && mouse2.getY()<650)
            {
                y2=(mouse2.getX()-100)/100;
                x2=(mouse2.getY()-150)/100;
                if(gamearray[x2][y2].number==0)
                {
                    computeScore();
                    merge(x2,y2);
                    pushdown();
                    fillboard();
                    updateBoard();
                    if(checkstatus())
                    {
                        World w=getWorld();
                        w.addObject(new Gameover(" You Lose! "),w.getWidth()/2,w.getHeight()/2);
                        Greenfoot.stop();
                    }
                    
                }
                else
                {
                    revert();
                }
            }
            else
            {
                revert();
            } 
            return;
        }

    }
}