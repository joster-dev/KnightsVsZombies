import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	
	public static String title = "Knights vs. Zombies TD";
	
	public static int x = 896, y = 512;
	
	public static Dimension size = new Dimension(x, y);
	
	public static Opening openingScreen;
	public static GameMode chooseGame;
	public static Screen gameScreen;
	
	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		openingScreen = new Opening(this);
		chooseGame = new GameMode(this);
		gameScreen = new Screen(this);
		
		add(openingScreen);
		chooseGame.setVisible(false);
		gameScreen.setVisible(false);
		
		setVisible(true);
	}
	
	public void updateFrame() {
		if(openingScreen.newGame) {
			remove(openingScreen);
			openingScreen.setVisible(false);
			openingScreen.mse.setLocation(0, 0);
			
			add(chooseGame);
			chooseGame.setVisible(true);
			
			if(chooseGame.storyMode) {
				remove(chooseGame);
				chooseGame.setVisible(true);
				
				add(gameScreen);
				gameScreen.setVisible(true);
				
				chooseGame.storyMode = false;
				openingScreen.newGame = false;
			}
			else if(chooseGame.infiniteMode) {
				remove(chooseGame);
				chooseGame.setVisible(true);
				
				add(gameScreen);
				gameScreen.setVisible(true);
				
				chooseGame.storyMode = false;
				openingScreen.newGame = false;
			}
		}
		else if(openingScreen.loadGame) {
			remove(openingScreen);
			openingScreen.setVisible(false);
			
			add(gameScreen);
			gameScreen.setVisible(true);
			
			openingScreen.loadGame = false;
		}
		else if(openingScreen.highScores) {
			
		}
		else if(openingScreen.settings) {
			
		}
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();
	}
}