import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * SpriteSheet
 * 
 * Acts as a global set of images files that can be loaded at the beginning of the game and accessed by the
 * program at any time later on. 
 * 
 * @author Nick Roberts
 *
 */
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
	
	// The sprite sheet of bosses
	public static BufferedImage[][] bossSheet;
	private int bossSheetWidth;						// The max number of animation frames
	private int bossSheetHeight;					// The max number of bosses
	
	// Icons used by the heads-up-display
	BufferedImage attackIcon;
	BufferedImage goldIcon;
	BufferedImage healthIcon;
	BufferedImage hudFrame;
	BufferedImage fastForwardIcon;
	BufferedImage pauseIcon;
	BufferedImage saveIcon;
	BufferedImage rangeIcon;
	BufferedImage speedIcon;
	BufferedImage baseIcon;
	BufferedImage wavesIcon;
	BufferedImage armorIcon = null;
	
	// The background used by all the main menus
	public static Image openingBackground = new ImageIcon("res/Graphics/opening_screen.png").getImage();

	// other images used in various menus
	public static Image scroll = new ImageIcon("res/Graphics/scroll.png").getImage();
	public static Image back_button = new ImageIcon("res/Graphics/back_button.png").getImage();
	public static Image music_button = new ImageIcon("res/Graphics/music_note.png").getImage();
	public static Image sound_button = new ImageIcon("res/Graphics/sound.png").getImage();
	public static Image cancel = new ImageIcon("res/Graphics/stop_icon.png").getImage();
	public static Image main_menu = new ImageIcon("res/Graphics/main_menu.png").getImage();
	
	/**
	 * spriteSheet()
	 * 
	 * A constructor that, when called, initializes an instance of the SpriteSheet class by populating it with
	 * the necessary image files. In the case of the files "tower_sheet.png", "enemy_sheet.png",
	 * "block_sheet.png", and "boss_sheet.png", the images are broken down into 2-dimensional an array of images 
	 * to be used later as frames of animation.
	 */
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
			
			// Set the dimensions of the sprites
			final int bsWidth = 64;
			final int bsHeight = 64;
			
			// Set the number of rows and columns in the sheet
			final int bsRows = ((blockSheetBig.getHeight())/64);
			final int bsCols = ((blockSheetBig.getWidth())/64);
		
			// Initialize a new 2-dimensional array to store the individual sprite images
			blockSheet = new BufferedImage[bsRows][bsCols];
			
			// Get the individual sprites and store them to the array
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
			
			//-----------------------------------------------------------------------------
			
			// Get the image file for the sprite sheet of bosses
			BufferedImage bossSheetBig = ImageIO.read(new File("res/boss_sheet.png"));
			
			// Set the dimensions of the sprites
			final int bssWidth = 128;
			final int bssHeight = 128;
			
			// Set the number of rows and columns in the sheet
			final int bssRows = ((bossSheetBig.getHeight())/128);
			final int bssCols = ((bossSheetBig.getWidth())/128);
		
			// Initialize a new 2-dimensional array to store the individual sprite images
			bossSheet = new BufferedImage[bssRows][bssCols];
			
			// Get the individual sprites and store them to the array
			for(int i = 0; i < bssRows; i++){
				for(int j = 0; j < bssCols; j++){
					bossSheet[i][j] = bossSheetBig.getSubimage(
							j * bssHeight,
							i * bssWidth,
							bssWidth,
							bssHeight
						);
				}
			}
			
			// Save the maximum dimensions of the array (used to avoid OutOfBounds exceptions)
			bossSheetWidth = bssCols;
			bossSheetHeight = bssRows;
			
			//-----------------------------------------------------------------------------
			
			//Get the remaining graphical resources
			attackIcon = ImageIO.read(new File("res/Graphics/Attack.png"));
			goldIcon = ImageIO.read(new File("res/Graphics/Gold.png"));
			healthIcon = ImageIO.read(new File("res/Graphics/Health.png"));
			hudFrame = ImageIO.read(new File("res/Graphics/hud_frame.png"));
			fastForwardIcon = ImageIO.read(new File("res/Graphics/MenuFF.png"));
			pauseIcon = ImageIO.read(new File("res/Graphics/MenuPause.png"));
			saveIcon = ImageIO.read(new File("res/Graphics/MenuSave.png"));
			rangeIcon = ImageIO.read(new File("res/Graphics/Range.png"));
			speedIcon = ImageIO.read(new File("res/Graphics/Speed.png"));
			baseIcon = ImageIO.read(new File("res/Graphics/towr_base.png"));
			wavesIcon = ImageIO.read(new File("res/Graphics/Waves.png"));
		}
		
		// Catch any exceptions that might be encountered in opening the files or generating the images
		catch(Exception e){}
		
	}
	
	/**
	 * getSprite (sheet, row, column)
	 * 
	 * A method that takes in the name of a sprite sheet 'sheet', the row of a sprite in the sheet 'row', and the
	 * column of a sprite in the sheet 'col' and returns the sprite at 'sheet'_sheet ['row']['col']
	 * 
	 * @param sheet	The name of the sprite sheet to access
	 * @param row The row of the sprite sheet to access
	 * @param col The column of the sprite sheet to access
	 * @return 'sheet'_sheet ['row']['col'] if it exists, otherwise null
	 */
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
		
		// Otherwise, if the boss sheet is specified...
		else if (sheet == "boss"){
			
			// If the specified index exists within the sheet, return the sprite at that index
			if(row < bossSheetHeight && col < bossSheetWidth)
				return bossSheet [row][col];
			
			// Otherwise, return null
			return null;
		}
		
		// If all else fails, return null
		return null;
	}
}