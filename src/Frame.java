import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	
	public static String title = "Knights vs. Zombies TD";
	
	public static int x = 896;
	public static int y = 512;
	public static Dimension size = new Dimension(x, y);
	
	public static Opening openingScreen;
	public static GameMode chooseGame;
	public static Screen gameScreen;
	public static HighScore highScores;
	public static Settings settings;
	
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
		highScores = new HighScore(this);
		settings = new Settings(this);
		
		add(openingScreen);
		chooseGame.setVisible(false);
		gameScreen.setVisible(false);
		highScores.setVisible(false);
		settings.setVisible(false);
		
		setVisible(true);
	}
	
	public void updateFrame() {
		if(openingScreen.newGame) {
			remove(openingScreen);
			openingScreen.setVisible(false);
			
			add(chooseGame);
			chooseGame.setVisible(true);
			
			if(chooseGame.storyMode) {
				remove(chooseGame);
				chooseGame.setVisible(false);
				
				add(gameScreen);
				gameScreen.setVisible(true);
				
				chooseGame.storyMode = false;
				openingScreen.newGame = false;
			}
			else if(chooseGame.infiniteMode) {
				remove(chooseGame);
				chooseGame.setVisible(false);
				
				add(gameScreen);
				gameScreen.setVisible(true);
				
				chooseGame.infiniteMode = false;
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
			remove(openingScreen);
			openingScreen.setVisible(false);
			
			add(highScores);
			highScores.setVisible(true);
			
			if(highScores.back) {
				System.out.println("Beep");
				remove(highScores);
				highScores.setVisible(false);
				
				add(openingScreen);
				openingScreen.setVisible(true);
				
				highScores.back = false;
				openingScreen.highScores = false;
			}
		}
		else if(openingScreen.settings) {
			remove(openingScreen);
			openingScreen.setVisible(false);
			
			add(settings);
			settings.setVisible(true);
			
			openingScreen.settings = false;
		}
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();
	}
}