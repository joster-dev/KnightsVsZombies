import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	
	public static String title = "Knights vs. Zombies TD";
	
	public static int x = 896;
	public static int y = 512;
	public static Dimension size = new Dimension(x, y);

	public static SplashScreen splashScreen;
	public static Opening openingScreen;
	public static GameMode chooseGame;
	public static Screen gameScreen;
	public static Pause pauseScreen;
	public static HighScore highScores;
	public static Settings settings;
	
	public Frame() throws Exception {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		init();
	}
	
	public void init() throws Exception {
		setLayout(new GridLayout(1, 1, 0, 0));

		splashScreen = new SplashScreen(this);
		openingScreen = new Opening(this);
		chooseGame = new GameMode(this);
		gameScreen = new Screen(this);
		pauseScreen = new Pause(this);
		highScores = new HighScore(this);
		settings = new Settings(this);

		add(splashScreen);
		splashScreen.setVisible(true);
		
		openingScreen.setVisible(false);
		chooseGame.setVisible(false);
		gameScreen.setVisible(false);
		pauseScreen.setVisible(false);
		highScores.setVisible(false);
		settings.setVisible(false);
		
		setVisible(true);
	}

	public void updateFrame() {

		if(splashScreen.done) {
			remove(splashScreen);

			splashScreen.setVisible(false);
			add(openingScreen);

			openingScreen.setVisible(true);
			splashScreen.done = false;
		}
		else if(openingScreen.newGame) {
			remove(openingScreen);
			openingScreen.setVisible(false);
			
			add(chooseGame);
			chooseGame.setVisible(true);
			
			if(chooseGame.storyMode) {
				remove(chooseGame);
				chooseGame.setVisible(false);
				
				gameScreen.loadGame = false;
				gameScreen.infiniteGame = false;
				gameScreen.define();
				add(gameScreen);
				gameScreen.setVisible(true);
				
				chooseGame.storyMode = false;
				openingScreen.newGame = false;
			}
			else if(chooseGame.infiniteMode) {
				remove(chooseGame);
				chooseGame.setVisible(false);
				
				gameScreen.infiniteGame = true;
				add(gameScreen);
				gameScreen.setVisible(true);
				
				chooseGame.infiniteMode = false;
				openingScreen.newGame = false;
			}
			else if(chooseGame.back) {
				remove(chooseGame);
				chooseGame.setVisible(false);
				
				add(openingScreen);
				openingScreen.setVisible(true);
				
				chooseGame.back = false;
				openingScreen.newGame = false;
			}
		}
		else if(openingScreen.loadGame) {
			remove(openingScreen);
			openingScreen.setVisible(false);
			
			gameScreen.loadGame = true;
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
			
			if(settings.back) {
				remove(settings);
				settings.setVisible(false);
				
				add(openingScreen);
				openingScreen.setVisible(true);
				
				settings.back = false;
				openingScreen.settings = false;
			}
		}
		else if(gameScreen.questChainClear) {
			remove(gameScreen);
			gameScreen.setVisible(false);
			
			add(openingScreen);
			openingScreen.setVisible(true);
		}
		else if(ScreenPanel.isPaused) {
			remove(gameScreen);
			gameScreen.setVisible(false);
			
			add(pauseScreen);
			pauseScreen.setVisible(true);
			
			if(pauseScreen.backToGame) {
				remove(pauseScreen);
				pauseScreen.setVisible(false);
				
				add(gameScreen);
				gameScreen.setVisible(true);
				pauseScreen.backToGame = false;
			}
			else if(pauseScreen.backToOpening) {
				remove(pauseScreen);
				pauseScreen.setVisible(false);
				
				add(openingScreen);
				openingScreen.setVisible(true);
				pauseScreen.backToOpening = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Frame frame = new Frame();
	}
}