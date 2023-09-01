import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

	// Load image
	ImageIcon U = new ImageIcon("image/u.gif");
	ImageIcon D = new ImageIcon("image/d.gif");
	ImageIcon L = new ImageIcon("image/l.gif");
	ImageIcon R = new ImageIcon("image/r.gif");
	ImageIcon UD = new ImageIcon("image/ud.gif");
	ImageIcon DD = new ImageIcon("image/dd.gif");
	ImageIcon LD = new ImageIcon("image/ld.gif");
	ImageIcon RD = new ImageIcon("image/rd.gif");
	ImageIcon body = new ImageIcon("image/body.gif");
	ImageIcon blue_body = new ImageIcon("image/blue_body.gif");
	ImageIcon yellow_body = new ImageIcon("image/yellow_body.gif");
	ImageIcon red_body = new ImageIcon("image/red_body.gif");
	ImageIcon purple_body = new ImageIcon("image/purple_body.gif");
	ImageIcon orange_body = new ImageIcon("image/orange_body.gif");
	ImageIcon apple = new ImageIcon("image/apple.gif");
	ImageIcon grape = new ImageIcon("image/grape.gif");
	ImageIcon blueberry = new ImageIcon("image/blueberry.gif");
	ImageIcon watermelon = new ImageIcon("image/watermelon.gif");
	ImageIcon orange = new ImageIcon("image/orange.gif");
	ImageIcon banana = new ImageIcon("image/banana.gif");
	ImageIcon bomb = new ImageIcon("image/bomb.gif");
	ImageIcon Start = new ImageIcon("image/start.gif");
	ImageIcon Died = new ImageIcon("image/died1.gif");
	ImageIcon explosion = new ImageIcon("image/explosion.gif");
	ImageIcon score = new ImageIcon("image/score.gif");
	ImageIcon backdgound = new ImageIcon("image/background2.jpg");

	
	public void paint(Graphics g) {
		// Set background color
		// g.setColor(Color.decode("#2ecc71"));
		
		backdgound.paintIcon(this, g, 0, 0);
		g.setColor(Color.white);
		g.fillRect(30, 150, 920, 720);
		
		
		// snake head
		if (!isdied) {
			if (direction.equals("U")) {
				U.paintIcon(this, g, snakex[0], snakey[0]);
			}
			if (direction.equals("D")) {
				D.paintIcon(this, g, snakex[0], snakey[0]);
			}
			if (direction.equals("L")) {
				L.paintIcon(this, g, snakex[0], snakey[0]);
			}
			if (direction.equals("R")) {
				R.paintIcon(this, g, snakex[0], snakey[0]);
			}
		}

		// Snake body
		for (int i = 1; i < len; i++) {
			// body.paintIcon(this, g, snakex[i], snakey[i]);
			switch (snakecolor[i]) {
			case 0:
				red_body.paintIcon(this, g, snakex[i], snakey[i]);
				break;
			case 1:
				orange_body.paintIcon(this, g, snakex[i], snakey[i]);
				break;
			case 2:
				yellow_body.paintIcon(this, g, snakex[i], snakey[i]);
				break;
			case 3:
				body.paintIcon(this, g, snakex[i], snakey[i]);
				break;
			case 4:
				blue_body.paintIcon(this, g, snakex[i], snakey[i]);
				break;
			case 5:
				purple_body.paintIcon(this, g, snakex[i], snakey[i]);
				break;
			}
		}

		if (isdied) {
			if (direction.equals("U")) {
				UD.paintIcon(this, g, snakex[0], snakey[0]);
			}
			if (direction.equals("D")) {
				DD.paintIcon(this, g, snakex[0], snakey[0]);
			}
			if (direction.equals("L")) {
				LD.paintIcon(this, g, snakex[0], snakey[0]);
			}
			if (direction.equals("R")) {
				RD.paintIcon(this, g, snakex[0], snakey[0]);
			}
		}

		// put bomb
		for (int i = 0; i <= bombQ; i++) {
			bomb.paintIcon(this, g, bombx[i], bomby[i]);
		}

		// put food
		for (int i = 0; i <= 0; i++) {
			switch (food[i]) {
			case 0:
				apple.paintIcon(this, g, foodx[i], foody[i]);
				break;
			case 1:
				orange.paintIcon(this, g, foodx[i], foody[i]);
				break;
			case 2:
				banana.paintIcon(this, g, foodx[i], foody[i]);
				break;
			case 3:
				watermelon.paintIcon(this, g, foodx[i], foody[i]);
				break;
			case 4:
				blueberry.paintIcon(this, g, foodx[i], foody[i]);
				break;
			case 5:
				grape.paintIcon(this, g, foodx[i], foody[i]);
				break;
			}
		}

		if (!isStart) {

			g.setColor(Color.black);
			g.setFont(new Font("微軟正黑體", Font.BOLD, 30));
			g.drawString("Press WSAD key or Arrow key to Start", 220, 500);
			g.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			g.drawString("( Press Space key if you need to pause )", 340, 525);
		}

		// died message and put explosion image
		if (isdied) {
			/*
			 * g.setColor(Color.red); g.setFont(new Font("arial", Font.BOLD, 30));
			 * g.drawString("Game Over Press Enter to Restart", 200, 350);
			 */
			Died.paintIcon(this, g, -25, 475);

			if (isexplosion) {

				explosion.paintIcon(this, g, explosionx, explosiony);
			}
		}

		// score message
		score.paintIcon(this, g, 30, 30);

		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD, 32));
		g.drawString(total + "", 130, 95);
		g.drawString(ranktop + "", 240, 95);
		g.drawString(appletime + "", 350, 95);
		g.drawString(orangetime + "", 460, 95);
		g.drawString(bananatime + "", 570, 95);
		g.drawString(watermelontime + "", 670, 95);
		g.drawString(blueberrytime + "", 780, 95);
		g.drawString(grapetime + "", 880, 95);
	}
	
	
	// playMusic
	public void playMusic() {
		
	}
	// snake data
	int[] snakex = new int[1000];
	int[] snakey = new int[1000];
	int[] snakecolor = new int[1000];

	int len = 3;
	String direction = "R";

	// whether to Start
	boolean isStart = false;

	// whether to died
	boolean isdied = false;

	// whether to explosion
	boolean isexplosion = false;

	int Score = 0;

	// Set speed
	int speed = 125;
	Timer timer = new Timer(speed, this);

	// Set food position and which food
	Random random = new Random();
	int[] food = new int[3];
	int[] foodx = new int[3];
	int[] foody = new int[3];

	// Set bomb position
	int[] bombx = new int[1000];
	int[] bomby = new int[1000];
	int bombQ = 2;

	// Set foodtime and rank
	int appletime = 0;
	int orangetime = 0;
	int bananatime = 0;
	int watermelontime = 0;
	int blueberrytime = 0;
	int grapetime = 0;
	int total = 0;
	int ranktop = 0;

	// explosion position
	int explosionx;
	int explosiony;

	// initialization snake
	public void initSnake() {
		//playMusic("Racing_The_Clock.mp3");
		
		if (total > ranktop) {
			ranktop = total;
		}
		isStart = false;
		isdied = false;
		isexplosion = false;

		speed = 125;
		Score = 0;
		bombQ = 2;
		len = 3;
		appletime = 0;
		orangetime = 0;
		bananatime = 0;
		watermelontime = 0;
		blueberrytime = 0;
		grapetime = 0;
		total = 0;
		direction = "R";

		snakex[0] = 150;
		snakey[0] = 510;
		snakecolor[0] = 3;
		snakex[1] = 110;
		snakey[1] = 510;
		snakecolor[1] = 3;
		snakex[2] = 70;
		snakey[2] = 510;
		snakecolor[2] = 3;

		putfood();
		putbomb();

	}

	// 讓食物不重疊蛇身
	public void putfood() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 0; i++) {
			boolean check = false;
			food[i] = random.nextInt(6);

			while (!check) {
				check = true;
				int x = random.nextInt(23) * 40 + 30;
				int y = random.nextInt(18) * 40 + 150;
				
				//不重複身體
				for (int j = 0; j < len; j++) {
					if (snakex[j] == x && snakey[j] == y) {
						check = false;
						break;
					}
				}
				
				//不食物重複食物
				for (int j=0; j<i;j++) {
					if (foodx[j] == x && foody[j] == y) {
						check = false;
						break;
					}
				}
				
				if (check) {
					foodx[i] = x;
					foody[i] = y;
					break;
				}
			}
		}
	}

	//// 讓炸彈不重疊蛇和食物
	public void putbomb() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= bombQ; i++) {
			boolean check = false;

			while (!check) {
				check = true;

				int x = random.nextInt(23) * 40 + 30;
				int y = random.nextInt(18) * 40 + 150;

				boolean check1;
				boolean check2;
				boolean check3;
				boolean check4;
				boolean check5;
				boolean check6;
				boolean check7=false;
				boolean check8=false;

				// 不讓炸彈穿身體和破壞遊戲體驗
				for (int j = 0; j < len; j++) {

					check1 = snakex[j] == x && snakey[j] == y; // 炸彈不穿過身體
					// 不開始就吃炸彈
					check2 = direction == "U" && snakex[0] == x && (snakey[0] - 40 == y || snakey[0] - 80 == y);
					check3 = direction == "D" && snakex[0] == x && (snakey[0] + 40 == y || snakey[0] + 80 == y);
					check4 = direction == "L" && snakey[0] == y && (snakex[0] - 40 == x || snakex[0] - 80 == x);
					check5 = direction == "R" && snakey[0] == y && (snakex[0] + 40 == x || snakex[0] + 80 == x);
					// 不重疊炸彈和食物
					check6 = foodx[0] == x && foody[0] == y;
					//check7 = foodx[1] == x && foody[1] == y;
					//check8 = foodx[2] == x && foody[2] == y;
					if (check1 || check2 || check3 || check4 || check5 || check6 || check7 || check8) {
						check = false;
						break;
					}
				}

				if (check) {
					bombx[i] = x;
					bomby[i] = y;
				}
			}
		}
	}

	public SnakePanel() {
		this.setFocusable(true);

		// 靜態蛇
		initSnake();

		// add KeyListener
		this.addKeyListener(this);

		timer.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int keycode = e.getKeyCode();

		boolean WSAD = keycode == KeyEvent.VK_W || keycode == KeyEvent.VK_S || keycode == KeyEvent.VK_A
				|| keycode == KeyEvent.VK_D;
		boolean UDLR = keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_LEFT
				|| keycode == KeyEvent.VK_RIGHT;

		if (WSAD || UDLR) {
			isStart = true;
		}

		if (keycode == KeyEvent.VK_SPACE) {
			isStart = false;
		}

		if (isdied && keycode == KeyEvent.VK_ENTER) {
			initSnake();
		}

		if ((keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W) && direction != "D" && !isdied) {
			direction = "U";
		}

		if ((keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S) && direction != "U" && !isdied) {
			direction = "D";
		}

		if ((keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_A) && direction != "R" && !isdied) {
			direction = "L";
		}

		if ((keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_D) && direction != "L" && !isdied) {
			direction = "R";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 1. re timer 2. snake move 3. repaint

		timer.start();

		if (isStart && !isdied) {
			for (int i = len; i > 0; i--) {
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}

			if (direction.equals("U")) {
				snakey[0] -= 40;
			}
			if (direction.equals("D")) {
				snakey[0] += 40;
			}
			if (direction.equals("L")) {
				snakex[0] -= 40;
			}
			if (direction.equals("R")) {
				snakex[0] += 40;
			}

			// over border
			/*
			 * if (snakey[0] < 0) snakey[0] = 675;
			 * 
			 * if (snakey[0] > 675) snakey[0] = 0;
			 * 
			 * if (snakex[0] < 0) snakex[0] = 875;
			 * 
			 * if (snakex[0] > 875) snakex[0] = 0;
			 */

			// eat food
			for (int i = 0; i <= 0; i++) {
				if (snakex[0] == foodx[i] && snakey[0] == foody[i]) {
					len += 1;
					Score++;
					speed--;
					bombQ+=2;
					snakecolor[len - 1] = food[i];

					switch (food[i]) {
					case 0:
						appletime++;
						break;
					case 1:
						orangetime++;
						break;
					case 2:
						bananatime++;
						break;
					case 3:
						watermelontime++;
						break;
					case 4:
						blueberrytime++;
						break;
					case 5:
						grapetime++;
						break;
					}

					total++;
					putfood();
					putbomb();
					break;
				}
			}

			// die eat myself
			for (int i = 1; i <= len; i++) {
				if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
					isdied = true;
				}
			}

			// over border died
			boolean overU = snakey[0] < 150;
			boolean overD = snakey[0] > 830;
			boolean overL = snakex[0] < 30;
			boolean overR = snakex[0] > 920;
			if (overU || overD || overL || overR) {
				isdied = true;
			}

			// eat bomb
			for (int i = 0; i <= bombQ; i++) {
				if (snakex[0] == bombx[i] && snakey[0] == bomby[i]) {
					isdied = true;
					isexplosion = true;
					explosionx = bombx[i];
					explosiony = bomby[i];
				}
			}
		}

		repaint();
	}

}
