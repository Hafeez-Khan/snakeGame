package snake;
import java.awt.Dimension;
import java.awt.Point;
//import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
//Hafeez Khan
//Jan 23, 2017
//ICS 4U1
//Mrs.Kapustina
//Class : Snake
public class Snake implements ActionListener, KeyListener{
	public static JFrame jframe;
	int x,y;
	boolean right =false;
	public RenderPanel renderPanel;
	public Timer timer = new Timer(20, this);
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public int ticks = 0, direction = DOWN, score, time;
	public double tail,speed,tailLength;
	public Point head, cherry;
	public Random random;
	public boolean over = false, paused;
	public Dimension dim;

	//constructor
	public Snake(double x,double y){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(815, 710);
		jframe.setResizable(true);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		tail=x;
		speed=y;
		startGame();
	}

	//post: no variable is passed into the parameter
	//pre: nothing is returned
	//purpose: this method starts the game while initializing variables
	public void startGame(){
		//randomizing starting values for snake
		int headx=(int)(Math.random()*31+45);
		int heady=(int)(Math.random()*6+30);
		//randomizing cherry values
		int cherryX= (int)(Math.random()*8+1);
		int cherryY= (int)(Math.random()*66+1);
		over = false;
		paused = false;
		time = 0;
		score = 0;
		tailLength = tail;
		ticks = 0;
		direction = UP;
		head = new Point(headx,heady);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(cherryX, cherryY);
		timer.start();
		
	}

	public void actionPerformed(ActionEvent event){
		renderPanel.repaint();
		ticks++;
		//if statement checks if all conditions that should keep snake moving are met
		if (ticks % speed == 0 && head != null && !over && !paused){
			time++;
			//the array snakeParts adds a new point making it bigger
			snakeParts.add(new Point(head.x, head.y));
			
			if (direction == UP){
				//before a point is subtracted there are many conditions that must be met for the snake;
				//first, snake part is subtracted to the head only if the snake is under the lowest y value else the game ends
				//second, the snake can't touch its self after the point is subtracted, if there is collision between the tails game ends
				//third, there can't be collision between the stationary box nor the moving ghost
				//in order to move up the the y value of the head must be altered
				if (head.y -1 >= 0 && noTailAt(head.x, head.y - 1) && !checkBlockCollision(head.x, head.y - 1)&& checkCollisionWithGhost(head.x, head.y-1))
				{
					// if the snake is moving up a block is moved from the last part of the array to first continuously hence the y value is being subtracted because the value is getting closer to zero when the snake moves up 
					head = new Point(head.x, head.y - 1);
				} 
				else{
					over = true;
				}
			}

			if (direction == DOWN){
				//before a point is added there are many conditions that must be met for the snake;
				//first, snake part is added to the head only if the snake is under the highest y value else the game ends
				//second, the snake can't touch its self after the point is added if there is collision between the tails game ends
				//third, there can't be collision between the stationary box nor the moving ghost
				//in order to move down the the y value of the head must be altered
				
				if (head.y + 1 <67 && noTailAt(head.x, head.y + 1) && !checkBlockCollision(head.x, head.y + 1) && checkCollisionWithGhost(head.x, head.y+1)){
					//if the snake is moving down closer to the maximum point the head must be added to increases the head point because it the y value is increasing
					head = new Point(head.x, head.y + 1);
				}
				else{
					over = true;
				}
			}

			if (direction == LEFT)	{
				//before a point is subtracted there are many conditions that must be met for the snake;
				//first, snake part is subtracted to the head only if the snake is under the lowest x value else the game ends
				//second, the snake can't touch its self after the point is subtracted, if there is collision between the tails game ends
				//third, there can't be collision between the stationary box nor the moving ghost
				//in order to move left the the x value of the head must be altered
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y) && !checkBlockCollision(head.x - 1, head.y)&& checkCollisionWithGhost(head.x-1, head.y)){
					//if the snake is  moving to the left of the screen the x value is again decreasing hence the subtraction from x point of the head
					head = new Point(head.x - 1, head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction == RIGHT){
				//before a point is subtracted there are many conditions that must be met for the snake;
				//first, snake part is subtracted to the head only if the snake is under the highest y value else the game ends
				//second, the snake can't touch its self after the point is added, if there is collision between the tails game ends
				//third, there can't be collision between the stationary box nor the moving ghost
				//in order to move right the the x value of the head must be altered
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y) && !checkBlockCollision(head.x + 1, head.y)&& checkCollisionWithGhost(head.x+1, head.y)){
					//if the snake is  moving to the right of the screen the x value is increasing hence the addition from the x point of the head
					head = new Point(head.x + 1, head.y);
				}
				else
				{
					over = true;
				}
			}

			//inorder to ensure that the size of the snake size is limited to it's tailLength, once the tailLength condition is met the rest of the snake parts is removed
			if (snakeParts.size() > tailLength){
				snakeParts.remove(0);
			}

			if (cherry != null){
				//if the cherry is equal to the head the score , tailLength, and cherry placement must be altered
				if (head.equals(cherry))
				{
					score= score + 10;
					tailLength++;
					int num= (int)(Math.random()*2+1);
					// in order to ensure that the cherry is never place under the stationary box there are two locations where the cherry can be produced
					// if num==1 then the cherry will be produced anywhere on the right side of the stationary box
					//if num==2 then the cherry will be produced anywhere on the left side of the stationary box
					if(num==1){
					cherry.setLocation((int)(Math.random()*8+1), (int)(Math.random()*66+1));
					}
					if (num==2){
						cherry.setLocation((int)(Math.random()*64+16), (int)(Math.random()*66+1));
					}				
				}
			}
		}
	}
	
	//pre: two integer values of the head are passed into the parameter
	//post: boolean is returned to determine whether the game continues or ends
	//purpose: to check if there is collision between the head of the snake and the tail
	public boolean noTailAt(int x, int y){
		for (Point point : snakeParts){
			if (point.equals(new Point(x, y))){
				return false;//GAME ENDS
			}
		}
		return true;
	}
	
	//pre:two integer values of the head are passed into the parameter
	//post: boolean is returned to check if there is collision between the moving ghost and snake
	//purpose: to check for collision with moving box and snake
	public boolean checkCollisionWithGhost(int x, int y){
		
		Point Ghostpoint;
		if (RenderPanel.ghost==1){
			Ghostpoint = new Point (RenderPanel.ghost1x/10, RenderPanel.ghost1y/10);
			
		}
		else{
			Ghostpoint = new Point (RenderPanel.ghost2x/10, RenderPanel.ghost2y/10);
			
		}
		
		for (Point point : snakeParts){
			if (point.equals(Ghostpoint)){
				return false;
			}
		}
		
		if (Ghostpoint.equals(new Point (x,y))){
			return false;
		}
		return true;		
	}
	
	//pre: two integer values of the snakes head are passed into the parameters 
	//post: boolean is returned
	//purpose: to check for collision between the stationary box and snake
	public boolean checkBlockCollision(int x, int y){
		
		//check if the first block collides 
		if (( x >= 10 && x <= 14 ) && (y>= 20 && y<=29)){
			
			return true;
		}
		
		
		return false;
	}
	
	//pre: KeyEvent e
	//post: nothing is returned
	//purpose: to check for which button is pressed
	public void keyPressed(KeyEvent e){
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT)
		{
			direction = LEFT;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
		{
			direction = RIGHT;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN)
		{
			direction = UP;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
		{
			direction = DOWN;
		}

		if (i == KeyEvent.VK_SPACE)
		{
			if (over)
			{
				startGame();
			}
			else
			{
				paused = !paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)	{
	}

	@Override
	public void keyTyped(KeyEvent e){
	}
	
	
}