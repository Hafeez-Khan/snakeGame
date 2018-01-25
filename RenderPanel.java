package snake;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import javax.swing.JPanel;
//Hafeez Khan
//Jan 23, 2017
//ICS 4U1
//Mrs.Kapustina
//Class : RenderPanel
public class RenderPanel extends JPanel{
	public static final Color GREEN = new Color(1666073);
	//the start values of the ghost are initialized
	public static int ghost1x =-20;
	public static int ghost1y=400;
	public static int ghost2x = 300;
	public static int ghost2y= -20;
	public static boolean dirR = true;
	public static boolean dirU = true;
	static Menu GUI;
	//ghost value is randomized to place a vertical or a horizontal ghost
	public static int ghost=(int)(Math.random()*2+1);
	
	//Post: the variable passed into the parameter is graphics g
	//Pre:there is no return value
	//Purpose:the method pains the background, snake, and boxes
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Snake snake = Menu.snake;
		//the score, tailLength and time are set to the variable string
		String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time / 20;
		g.setColor(GREEN);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.BLUE);
		
		//this for loop fills the rectangle of the snake which is snakeParts which is a Point
		//the for loop constantly paints the snakes body until the whole body is filled (body = snakeParts)
		//scale set to 10
		for (Point point : snake.snakeParts){
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		//the for loop fills the snakeParts while the head of the snake which is always being changed isn't apart of the snakeParts till after the fact hence it has to filled separately
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.RED);
		
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		g.setColor(Color.white);
		//the string is added to the game
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2), 10);
		
		//every time the snake game ends this if statement results in the menu screen to appear
		if (snake.over){
			setVisible (false);
			GUI = new Menu ();
			GUI.setFocusable (true);
			GUI.setBounds (0,0,820,800);
			GUI.requestFocus();
		}
		//string initialized to paused
		string = "Paused!";
		//if snake is paused the string is drawn
		if (snake.paused){
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 5.0), (int) snake.dim.getHeight() / 4);
		}

		g.setColor (Color.BLACK);
		g.fillRect(100, 200, 50, 100);
		g.drawRect(100, 200, 50, 100);

		//if the random generator generates 1 then the ghost moving from left to right is initialized
		if (ghost==1){
			//the ghost is set to its initialized value when the game ends 
			if(snake.over==true){
				ghost1x=-20;
			}
			//if the x value of the ghost equals to 0 then dirR is set to true meaning the ghost must move right now
			if(ghost1x == 0){
				dirR = true;
			}
			//if the x value of the ghost equals to 785 then dirL is set to true meaning the ghost must move left now
			if(ghost1x == 785){
				dirR = false;
			}
			//if the conditions are met then the ghost moves right
			if (dirR == true && snake.paused == false && snake.over==false) {
				ghost1x=ghost1x+5;

			}
			//if the conditions are met then the ghost moves left
			if (dirR == false && snake.paused == false && snake.over==false){
				ghost1x=ghost1x-5;

			}
		}

		if (ghost==2){
			//if the game is over then the y value of the second ghost is reset out of the screen
			if(snake.over==true){
				ghost2y=-20;
			}
			//if the second ghost is at the top of the screen then dirU is set to true
			if(ghost2y == 0){
				dirU = true;
			}
			//if second ghost is at the bottom of the screen then dirU is set to false
			if(ghost2y == 670){
				dirU = false;
			}
			
			//if dirU is true then the y value is being added by 5 hence the snake moves down
			if (dirU == true && snake.paused == false && snake.over==false) {
				ghost2y=ghost2y+5;

			}
			//if dirU is false then the y value is being subtracted hence getting closer to 0
			if (dirU == false && snake.paused == false && snake.over==false){
				ghost2y=ghost2y-5;

			}
		}
		
		//these ghost are always drawn not matter which ghost is initialized but the x or y values are altered in a way that if the ghost is not initialized it is put off the screen

		//the first ghost is drawn but the x and y values are altered hence the ghost moves
		g.fillRect(ghost1x, ghost1y, snake.SCALE, snake.SCALE);
		g.drawRect(ghost1x, ghost1y, snake.SCALE, snake.SCALE);
		
		//the second ghost is drawn but the x and y values are altered hence the ghost moves
		g.fillRect(ghost2x, ghost2y, snake.SCALE, snake.SCALE);
		g.drawRect(ghost2x, ghost2y, snake.SCALE, snake.SCALE);

	}

}




