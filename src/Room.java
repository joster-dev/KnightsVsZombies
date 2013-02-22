import java.awt.Graphics;


public class Room {

	public static int worldWidth = 13;
	public static int worldHeight = 6;
	public static int blockSize = ((2 * Opening.myHeight) / 15);				//Room size equal to 4/5 of the height of the screen.
	
	public Block[][] block;
	
	public Room() {
		block = new Block[worldHeight][worldWidth];
		
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block((Opening.myWidth/2) - ((worldWidth*blockSize)/2) + (x * blockSize), y * blockSize, blockSize, blockSize, Value.groundOpen, Value.airOpen);
			}
		}
	}
	
	public void physic() {
		for(int y=0;y<block.length;y++) {
			for(int x=0;x<block[0].length;x++) {
				block[y][x].physic();
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x].draw(g);
			}
		}
	}
}