import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.geom.Rectangle2D;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel
					   implements ActionListener{
	private final int ROWS = 20, COLS = 10;
	private final String ENTERWORD = "KKKKKKKKKK";
	
	private Tetris game;
	private CharMatrix charMatrix;
	private FallingShape cube;
	private int[][] shape = new int[4][4];
	private ScoreDisplay scoreDisplay;
	
	private final int CUBESIZE = 30;
	private final int X_LEFT = CUBESIZE / 2;
	
	private Timer t;
	private final int DELAY = 30;
	private int yStep; //displacement in y per time cycle
	private int x, y;

	private static final int SCOREINCREMENT = 100;
	private int score;
	private Color[] colorPicker = {Color.green, Color.yellow, Color.red, Color.magenta, Color.pink, Color.orange};
	private Color color;
	
	private EasySound drop;
	private EasySound chomp;
	
	public GamePanel(Tetris game) {
		this.game = game;
		setPreferredSize(new Dimension(12*CUBESIZE, 21*CUBESIZE));
		setBackground(Color.BLUE);
		
		drop = new EasySound("drop.wav");
		chomp = new EasySound("chomp.wav");
		
		charMatrix = new CharMatrix(ROWS, COLS);
		cube = new FallingShape(CUBESIZE);
		t= new Timer(DELAY,this);
			
	}
	public void setScoreDisplay(ScoreDisplay display) {
		scoreDisplay = display;
		scoreDisplay.update(score);
	}
	
	public void newGame() {
		charMatrix.clear();
		stopCube();
		score = 0;
		scoreDisplay.update(score);
	}
	public void setSpeed(int speed)
	{
	  yStep = Math.max(1, speed * DELAY / 1000);
	}
	
	public void dropCube() {
		cube.setRandomShape();
		shape = cube.getShape();
		x =(int)(Math.random() * (charMatrix.cols()-3)) * CUBESIZE;
		y = -CUBESIZE;
		cube.move(x, y);
		t.start();
	}
	
	public void stopCube() {
		t.stop();
		cube.move(-CUBESIZE*3, -CUBESIZE*3);
		repaint();
		 if (game.soundEnabled())
		      drop.play();
	}
	
	public boolean moveCubeDown() {
		int x = cube.getX();
		int y = cube.getY();
		int row = (y + CUBESIZE + yStep)/CUBESIZE;
		int col = x/CUBESIZE;
		boolean movable = true;
		for (int w = 0; w < shape[0].length; w++) {
			for (int h = 0; h < shape.length; h++) {
					if (shape[h][w] == 1 && (h+1 >= shape.length || shape[h+1][w] != 1) //如果shape[h][w]是1，而且其下面一点是0或超出shape的行数
							 && (row+h >= charMatrix.rows() || !(charMatrix.isEmpty(row+h,col+w)))) {	    // 这点如果在charMatrix上不是空的则不能继续向下移动，故false											
						movable = false;                                 //方块不能继续下移，故在charMatrix上把这个方块上的点标记为“K”	 			
					}				
			}					
		}
		if (movable == false) {
			for (int w = 0; w < shape[0].length; w++) {
				for (int h = 0; h < shape.length; h++) {
					if (shape[h][w] == 1) {
						charMatrix.setCharAt(row+h-1, col+w,'K');
					}
				}
			}
			return false;
		}
		cube.move(x, y+yStep);
		return true;
	}
	
	public void moveCubeLeft() {
		int x = cube.getX();
		int y = cube.getY();
		int row = (y+CUBESIZE)/CUBESIZE;
		int col = x/CUBESIZE;
		if (row >= 0 && row <charMatrix.rows() && col >= 1) {
			for (int h = 0; h < shape.length; h++) {
				if (!charMatrix.isEmpty(row+h,col-1)) {
					return;
				}
			}
			cube.move(x - CUBESIZE, y);
			repaint();
		}
			
		
	}
	
	public void moveCubeRight() {
		int x = cube.getX();
		int y = cube.getY();
		int row = (y+CUBESIZE)/CUBESIZE;
		int col = x/CUBESIZE;
		if (row >= 0 && row <charMatrix.rows() && col + shape[0].length-1 < charMatrix.cols()-1) {
			for (int h = 0; h < shape.length; h++) {
				if (!charMatrix.isEmpty(row+h,col + shape[0].length)) {  //charMatrix有一个格不是空的直接return 
					return;
				}
			}
			cube.move(x + CUBESIZE, y);
			repaint();
		}
	}
	
	public void rotateLeft() {
		int x = cube.getX()/CUBESIZE + shape.length-1;
		if (x < charMatrix.cols()) {
			cube.rotateLeft();
		}
		shape = cube.getShape();
		repaint();
	}
	
	public void shiftRow(int row) {
		String word = charMatrix.rowToString(row).trim();
		int len = word.length();
		/*boolean empty = false;
		if (len > 0) {
			for (int i = 0; i < charMatrix.getGrid()[row].length; i++) {
				if (charMatrix.isEmpty(row,i))
					empty = true;
			}
			if (empty == false) {
				score += SCOREINCREMENT;
				scoreDisplay.update(score);
				charMatrix.shift(row);
				repaint();
				if (game.soundEnabled())
			        chomp.play();		
			}
		}*/	
		if (len > 0 && word.equals(ENTERWORD)) {
			score += SCOREINCREMENT;
			scoreDisplay.update(score);
			charMatrix.shift(row);
			repaint();
			if (game.soundEnabled())
		        chomp.play();		
		}
	}
	
	public void actionPerformed(ActionEvent e){
	    if (!moveCubeDown())
	    {
	      stopCube();
	      for (int r = 0; r < charMatrix.rows(); r++) {
	    	  shiftRow(r);
	    	  repaint();
	      }
	      dropCube();
	    }
	    repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);;
		drawCharMatrix(g);
		drawShape(g);
	}
	
	public void drawShape(Graphics g) {
		drawShape(g,cube.getX()+X_LEFT,cube.getY());
	}
	
	private void drawCharMatrix(Graphics g)
	  {
	    g.setColor(Color.GRAY);
	    int size = CUBESIZE;
	    int x1 = X_LEFT - 2;
	    int x2 = X_LEFT + COLS * size + 1;
	    int y1 = 2;
	    int y2 = ROWS * size - 1;
	    g.drawLine(x1, y1, x1, y2);
	    g.drawLine(x2, y1, x2, y2);
	    g.drawLine(x1, y2, x2, y2);

	    for (int row = 0; row < ROWS; row++)
	    {
	      for (int col = 0; col < COLS; col++)
	      {
	        if (charMatrix.charAt(row, col) == 'K')
	        {
	          int x = X_LEFT + col * CUBESIZE;
	          int y = row * CUBESIZE;
	      	  //g.setColor(Color.PINK);
	          g.fill3DRect(x,y,CUBESIZE-1,CUBESIZE-1,true);
	        }
	      }
	    }
	  }
	
	public void drawShape(Graphics g, int x, int y) {
		int size = CUBESIZE;
		//pickRandomColor();
		//g.setColor(color);
		g.setColor(Color.GREEN);
		for (int r = 0; r <shape.length; r++) {
			for (int c = 0; c <shape[r].length; c++) {
				if (shape[r][c] == 1) {
					g.fill3DRect(x+c*size, y+r*size, size-1, size-1, true);
				}
			}
		}
			
	}
	
	public void pickRandomColor() {
		int i = (int)(Math.random()*colorPicker.length);
		color = colorPicker[i];
	}
	
	
	
	
	
}
