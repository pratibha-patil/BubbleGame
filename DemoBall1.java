/*<applet code="DemoBall1.class" width=500 height=500></applet>*/
import java.util.Random;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class DemoBall1 extends Applet implements KeyListener
{
	Color cr;
	char []ch=new char[10];
	private AudioClip clip,cp;	
	int width,height;
	boolean flg=false;
	ball b[]=new ball[10];
	Random r=new Random();
	Image bk,k,i;
	int score;
	Graphics gph;
	int m,n;
	GraphicsDevice gd= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public void init()
	{
		width=gd.getDisplayMode().getWidth();
		height=gd.getDisplayMode().getHeight();
		setSize(width,height);
		 i=getImage(getDocumentBase(),"images/n2.jpg");
		 k=getImage(getDocumentBase(),"images/download.png");
		clip=getAudioClip(getDocumentBase(),"music/dance-zone.au");
		cp=getAudioClip(getDocumentBase(),"music/b1.au");

		width=getWidth();
		height=getHeight();
		
		
		addKeyListener(this);
		
	}
	public void start()
	{
		score=0;
		for(int i=0;i<10;i++)
			{	
				ch[i]=(char)(r.nextInt(74)+48);
				b[i]=new ball(r.nextInt(width-40),r.nextInt(20),width,height,ch[i],this,true);
				
			}
		if(clip!=null)
			clip.loop();
	
		for(int i=0;i<10;i++)
		{
			b[i].start();
		}

	}
	public void update(Graphics g)
	{
			bk=createImage(this.getSize().width,this.getSize().height);
			gph=bk.getGraphics();
	
			gph.fillRect(0,0,this.getSize().width,this.getSize().height);
	
			paint(gph);
		g.drawImage(bk,0,0,this);
	}
	public void keyTyped(KeyEvent k)
	{
		char c=k.getKeyChar();
		if(c!=KeyEvent.CHAR_UNDEFINED)
		for(int i=0;i<10;i++)
		{
			if(c==b[i].ch)
			{	
				cp.play();
				b[i].y=0;
				score+=10;
				
			}
			
		}

	}
	
	public void keyReleased(KeyEvent k){}
	public void keyPressed(KeyEvent k){}
	public void paint(Graphics g)
	{

		setForeground(Color.white);
		g.drawImage(i,0,0,width,height,this);
		for(int i=0;i<10;i++)
		{
			g.setColor(Color.white);
			
			
				//g.fillOval(b[i].x-30,b[i].y-30,2*30,2*30);
				g.drawImage(k,b[i].x,b[i].y,80,80,this);
				g.setColor(Color.white);
				g.setFont(new Font("Ariel",Font.BOLD,28));
				g.drawString(""+b[i].ch,b[i].x+40,b[i].y+40);
				g.drawString("SCORE:"+score,30,30);
				g.drawString("Loss:"+ball.loss,30,50);
				
		}
		if(ball.loss==40)
		{
			g.drawImage(i,0,0,width,height,this);
			g.setColor(new Color(0,0,255));
			g.drawString("SCORE:"+score,30,30);
			g.setFont(new Font("Ariel",Font.BOLD,100));
			
			g.drawString("GAME OVER",width/2-300,height/2);
			clip.stop();
		}
		
	}
	public void stop()
	{
		for(int i=0;i<10;i++)
			b[i]=null;
		
	}
	
}