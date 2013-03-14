import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteSheet{

	// The sprite sheet of towers
	public static BufferedImage[][] towerSheet;
	private int towerSheetWidth;					// The max number of animation frames
	private int towerSheetHeight;					// The max number of towers
	
	// Create one big sprite sheet composed of several separate sprite sheets
	public SpriteSheet(){
		
		try{
			
			// Get the image file for the sprite sheet of towers
			BufferedImage towerSheetBig = ImageIO.read(new File("res/knights_sheet.png"));
			
			// Set the dimensions of the sprites
			final int tsWidth = 64;
			final int tsHeight = 64;
			
			// Set the number of rows and columns in the sheet
			final int tsRows = ((towerSheetBig.getHeight())/64);
			final int tsCols = ((towerSheetBig.getWidth())/64);
		
			// Initialize a new 2-dimensional array to store the individual sprite images
			towerSheet = new BufferedImage[tsRows][tsCols];
			
			// Get the individual sprites and store them to the array
			for(int i = 0; i < tsRows; i++){
				for(int j = 0; j < tsCols; j++){
					towerSheet[i][j] = towerSheetBig.getSubimage(
							j * tsHeight,
							i * tsWidth,
							tsWidth,
							tsHeight
						);
				}
			}
			
			// Save the maximum dimensions of the array (used to avoid OutOfBounds exceptions)
			towerSheetWidth = tsCols;
			towerSheetHeight = tsRows;
		}
		
		catch(Exception e){}
		
	}
	
	public BufferedImage getSprite (String sheet, int row, int col){
	
		// If the row or column number is below 0, return null
		if (row < 0 || col < 0)
			return null;
		
		// Otherwise, if the tower sheet is specified...
		else if (sheet == "tower"){
			
			// If the specified index exists within the sheet, return the sprite at that index
			if(row < towerSheetHeight && col < towerSheetWidth)
				return towerSheet [row][col];
			
			// Otherwise, return null
			return null;
		}
		
		// If all else fails, return null
		return null;
	}
}