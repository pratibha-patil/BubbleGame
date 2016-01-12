import java.util.Random;
import java.awt.*;
import java.applet.*;
class ball extends Thread
{
	int x,y,chgX,chgY;
	int w,h;
	char ch;
	DemoBall1 ap;
	static int loss=0;
	boolean flg;
	ball(int a,int b,int wd,int ht,char r,DemoBall1 app,boolean flg)
	{
		x=a;
		y=ht;
		chgY=b;
		w=wd;
		h=ht;
		ap=app;
		ch=r;
		this.flg=flg;
	}
	
	public void run()
	{
		
		while(flg)
		{	
			y-=chgY;
			if(y<=-40)
			{
				y=h;
				ch=(char)(65+ap.r.nextInt(57));
				loss+=1;
				ap.score-=1;
			}
			ap.repaint();

			try
			{
				Thread.sleep(200);
			}
			catch (Exception e)
			{
			}
			if(loss==20)
			{
					ap.repaint();
					flg=false;
			}
		}
	}
}