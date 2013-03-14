import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteSheet{

	// The sprite sheet of towers
	public static BufferedImage[][] towerSheet;
	private int towerSheetWidth;					// The max number of animation frames
	private int towerSheetHeight;					// The max number of towers
	
	// The sprite sheet of enemies
	public static BufferedImage[][] enemySheet;		
	private int enemySheetWidth;					// The max number of animation frames
	private int enemySheetHeight;					// The max number of enemies
	
	// The sprite sheet of blocks
	public static BufferedImage[][] blockSheet;
	private int blockSheetWidth;					// The max number of block tiles per level
	private int blockSheetHeight;					// The number of levels
	
	// Create one big sprite sheet composed of several separate sprite sheets
	public SpriteSheet(){
		
		try{
			
			// Get the image file for the sprite sheet of towers
			BufferedImage towerSheetBig = ImageIO.read(new File("res/tower_sheet.png"));
			
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
			
			//--------------------------------------------------------------------------------
			
			// Get the image file for the sprite sheet of enemies
			BufferedImage enemySheetBig = ImageIO.read(new File("res/enemy_sheet.png"));
			
			// Set the dimensions of the sprites
			final int esWidth = 64;
			final int esHeight = 64;
			
			// Set the number of rows and columns in the sheet
			final int esRows = ((enemySheetBig.getHeight())/64);
			final int esCols = ((enemySheetBig.getWidth())/64);
		
			// Initialize a new 2-dimensional array to store the individual sprite images
			enemySheet = new BufferedImage[esRows][esCols];
			
			// Get the individual sprites and store them to the array
			for(int i = 0; i < esRows; i++){
				for(int j = 0; j < esCols; j++){
					enemySheet[i][j] = enemySheetBig.getSubimage(
							j * esHeight,
							i * esWidth,
							esWidth,
							esHeight
						);
				}
			}
			
			// Save the maximum dimensions of the array (used to avoid OutOfBounds exceptions)
			enemySheetWidth = esCols;
			enemySheetHeight = esRows;
			
			//------------------------------------------------------------------------------
			
			// Get the image file for the sprite sheet of enemies
			BufferedImage blockSheetBig = ImageIO.read(new File("res/block_sheet.png"));
			
			// Set the dimensions of the spritbs
			final int bsWidth = 64;
			final int bsHeight = 64;
			
			// Set the number of rows and columns in the sheet
			final int bsRows = ((blockSheetBig.getHeight())/64);
			final int bsCols = ((blockSheetBig.getWidth())/64);
		
			// Initialize a new 2-dimensional array to store the individual sprite imagbs
			blockSheet = new BufferedImage[bsRows][bsCols];
			
			// Get the individual spritbs and store them to the array
			for(int i = 0; i < bsRows; i++){
				for(int j = 0; j < bsCols; j++){
					blockSheet[i][j] = blockSheetBig.getSubimage(
							j * bsHeight,
							i * bsWidth,
							bsWidth,
							bsHeight
						);
				}
			}
			
			// Save the maximum dimensions of the array (used to avoid OutOfBounds exceptions)
			blockSheetWidth = bsCols;
			blockSheetHeight = bsRows;
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
		
		// Otherwise, if the enemy sheet is specified...
		else if (sheet == "enemy"){
			
			// If the specified index exists within the sheet, return the sprite at that index
			if(row < enemySheetHeight && col < enemySheetWidth)
				return enemySheet [row][col];
			
			// Otherwise, return null
			return null;
		}
		
		// Otherwise, if the block sheet is specified...
		else if (sheet == "block"){
			
			// If the specified index exists within the sheet, return the sprite at that index
			if(row < blockSheetHeight && col < blockSheetWidth)
				return blockSheet [row][col];
			
			// Otherwise, return null
			return null;
		}
		
		// If all else fails, return null
		return null;
	}
}