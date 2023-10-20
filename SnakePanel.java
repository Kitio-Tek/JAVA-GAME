package SnakeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener{
    static final int UNIT_SIZE = 20;
    int width; // Non-final, set by constructor
    int height; // Non-final, set by constructor
    int NUMBER_OF_UNITS; // Non-static, set by constructor

    int[] x; //Array that stores the coordinates for the snake's position
    int[] y; 

	int length = 3; // initial length of the snake
	int foodEaten;
	char direction = 'D';
	boolean running = false;
	Random random;
	Timer timer;

    private RestartButton restartButton; 
    private StartButton startButton;
    private Apple apple;
    private boolean firstTime = true; //Used only with start button


    //Constructor of the game panel
    SnakePanel(int width, int height) {
        
        setLayout(new BorderLayout());  //to choose the location of the buttons

        this.width = width;
        this.height = height;
        this.NUMBER_OF_UNITS = (width * height) / (UNIT_SIZE * UNIT_SIZE);
        
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();
        
        x = new int[NUMBER_OF_UNITS]; 
        y = new int[NUMBER_OF_UNITS];

        apple = new Apple(UNIT_SIZE, this.width, this.height); //Initializing the apple object

        random = new Random();
        this.setPreferredSize(new Dimension(this.width, this.height)); // use this.width and this.height
        this.setBackground(new Color(0, 51, 80));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();

        //Restart Button!
        restartButton = new RestartButton();
        this.add(restartButton.getButton());

        restartButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame(); // This method would restart the game
            }
        });

        //Start button!
        startButton = new StartButton();
        this.add(startButton.getButton());

        startButton.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // This method would start the game
                startButton.getButton().setVisible(false); //Makes the start button invisible
            }
        });

        if (firstTime) {
            timer.stop(); // Stop the timer initially, so the game doesn't start until button is pressed
            startButton.getButton().setVisible(true); // Show the start button initially
        }
    }

    private void startGame() {
        if (firstTime) {
            timer.start(); // starts the game
            firstTime = false;
         } 
        }
    


    public void play() {
		addFood();
		running = true;
		
		timer = new Timer(100, this);
		timer.start();	
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		draw(graphics);
	}
	
	public void move() {
		for (int i = length; i > 0; i--) {
			//Each time a button is pressed the snakes moves one unit
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		if (direction == 'L') {
			x[0] = x[0] - UNIT_SIZE;
		} else if (direction == 'R') {
			x[0] = x[0] + UNIT_SIZE;
		} else if (direction == 'U') {
			y[0] = y[0] - UNIT_SIZE;
		} else {
			y[0] = y[0] + UNIT_SIZE;
		}	
	}
	
	public void checkFood() {
        if (x[0] == apple.getAppleX() && y[0] == apple.getAppleY()) {
            length++;
            foodEaten++;
            apple.randomApple();
            repaint();
        }
    }

	
	public void draw(Graphics graphics) {
		
		if (running) {
			apple.draw(graphics);

			graphics.setColor(new Color(0, 153, 153));
			graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);
			
			for (int i = 1; i < length; i++) {
				graphics.setColor(new Color(0, 120, 120));
				graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
			
			graphics.setColor(Color.white);
			graphics.setFont(new Font("Futura", Font.ROMAN_BASELINE, 18));
			FontMetrics metrics = getFontMetrics(graphics.getFont());
            int scoreX = (width - metrics.stringWidth("Score: " + foodEaten)) / 2; // Center the score horizontally based on the width of the game panel.
            int scoreY = height - 470; //The score is shown 30 pixels from the top of the game panel.
            graphics.drawString("Score: " + foodEaten, scoreX, scoreY); // Draw the score string at the new position.
            repaint();

		} else {
			gameOver(graphics);
		}
	}
	
    public void addFood() {
        apple.randomApple();
        setVisible(true);
    }
	
	public void checkHit() {
        // check if head runs into its body
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
		
		// check if head run into walls
		if (x[0] < 0 || x[0] > this.width || y[0] < 0 || y[0] > this.height) { // use this.width and this.height
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }
	
	public void gameOver(Graphics graphics) {
        graphics.setColor(new Color(153, 204, 225)); // RGB values for royal blue
        graphics.setFont(new Font("Impact", Font.ROMAN_BASELINE, 70));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        int gameOverX = (width - metrics1.stringWidth("Game Over")) / 2; // Center the "Game Over" text horizontally
        int gameOverY = height / 2; // Position the "Game Over" text vertically in the middle
        graphics.drawString("Game Over", gameOverX, gameOverY);
		
    
		graphics.setColor(Color.white);
        graphics.setFont(new Font("Futura", Font.ROMAN_BASELINE, 25));
        FontMetrics metrics2 = getFontMetrics(graphics.getFont());
        int scoreX = (width - metrics2.stringWidth("Your Score: " + foodEaten)) / 2; // Center the score horizontally
        int scoreY = gameOverY + 50; // Position the score just below the "Game Over" text, you can adjust the gap by changing this value
        graphics.drawString("Your Score: " + foodEaten, scoreX, scoreY);
        restartButton.getButton().setVisible(true); // Make the button visible when the game is over

    }
	
    public void restartGame() {
        // Ensure the current game (if any) is stopped
        if(timer != null) {
            timer.stop();
        }

        // Reset all game variables to their initial values
        x = new int[NUMBER_OF_UNITS];
        y = new int[NUMBER_OF_UNITS];
        length = 5;
        foodEaten = 0;
        direction = 'D';
        running = true;
        addFood();
    
        timer.start();  
        this.requestFocus();    //So it doesn't crash with for example the buttons
        this.requestFocusInWindow();
        restartButton.getButton().setVisible(false); //Button gets invisible again when the game restarts

    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (running) {
			move();
			checkFood();
			checkHit();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter {
        @Override
       //Connects to the actual keys pressed on the keyboard
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            if (keyCode == KeyEvent.VK_LEFT && direction != 'R') {
                direction = 'L';
            } else if (keyCode == KeyEvent.VK_RIGHT && direction != 'L') {
                direction = 'R';
            } else if (keyCode == KeyEvent.VK_UP && direction != 'D') {
                direction = 'U';
            } else if (keyCode == KeyEvent.VK_DOWN && direction != 'U') {
                direction = 'D';
            }
        }
    }
}    

