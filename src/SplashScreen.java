import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SplashScreen extends JPanel {

	AudioHandler audioHandler;

	public Frame myFrame;

	public static int myWidth;
	public static int myHeight;
	
	public static int opacity;
	public static int timer;
	
	public static boolean done;

	public SplashScreen (Frame frame) {
		myFrame = frame;
		opacity = 0;
		timer = 0;
		done = false;
		audioHandler = new AudioHandler();
		try {
			audioHandler.soundHandler.playSound("res/Sounds/zombiegrowl.wav");
		} catch (Exception e) { }
	}

	public boolean isDone() { return done; }

	public void paintComponent(Graphics g) {

		myWidth = getWidth();
		myHeight = getHeight();

		// this is the image we will have as the title screen/intro screen...
		// P.S., a screen that introduces a game is called a "splash screen"
		// temporary image here...
		// replace this with an appropriate titlescreen/splashscreen image...
		ImageIcon image = new ImageIcon("res/Graphics/sample_title (1.75-1 ratio to fit- so 896-512).png");
		g.drawImage(image.getImage(),0, 0, myWidth, myHeight, null);

		// this portion of code needs to be left as is, because it will
		// create the black image that makes the image appear to fade in
		// and then out
		String temp = "res/Graphics/black" + opacity + ".png";
		ImageIcon fade = new ImageIcon(temp);
		g.drawImage(fade.getImage(),0, 0, myWidth, myHeight, null);

		// the 'fade' screen makes the image fade in...
		if((opacity < 9)&&(timer < 9)) {
			opacity++;
			try {
				Thread.sleep(60);
			} catch(Exception e) { }
		}
		// ...this is when the image is displayed for a moment...
		else if(timer == 10) {
			try {
				Thread.sleep(1000);
			} catch(Exception e) { }
		}
		// ...then the screen fades out.
		else if ((opacity > 0)&&(timer > 10)) {
			opacity--;
			try {
				Thread.sleep(60);
			} catch(Exception e) { }
		}
		// Finally, the screen remains black
		if(timer >= 22) {
			try {
				Thread.sleep(250);
			} catch(Exception e) { }
			done = true;
			myFrame.updateFrame();
		}
		
		timer++;
		repaint();
	}
}
