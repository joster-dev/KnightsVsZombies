import java.awt.*;
import javax.swing.*;

public class ScreenPanel {
	
	public static int cellSpaceFromRoom = ((Opening.myHeight / 5) / 8);				//space between panel buttons and the Room is equal to (1/8) of the vertical space.
	
	public static int shopWidth = 5;
	public static int shopButtonSize = ((3 * (Opening.myHeight / 5)) / 4);			//shop buttons take up 3/4 (6/8) of the of the vertical space allocated at the bottom of the screen. 
	public static int shopCellSpace = cellSpaceFromRoom;
	public static int[] shopButtonId = {0, 1, 2, 3, 7};
	public static int holdItemId;
	public static boolean holdItem = false;
	
	public static int menuLength = 3;
	public static int menuButtonSizeX = ((3 * (Opening.myHeight / 5)) / 4);			//menu buttons take up 3/4 (6/8) of allocated space horizontally. 
	public static int menuButtonSizeY = ((10 * (Opening.myHeight / 5)) / 48);		//menu buttons take up 5/24 (10/48) of allocated space vertically.
	public static int menuCellSpace = ((3 * (Opening.myHeight / 5)) / 48);			//space between the menu buttons and each other is equal to 1/16 (3/48) of the vertical space.
	
	public static int statusLength = 3;
	public static int statusButtonSizeX = (menuButtonSizeX * 2) + (cellSpaceFromRoom * 2);
	public static int statusButtonSizeY = menuButtonSizeY;
	public static int statusCellSpace = menuCellSpace;
	public static int statusIconSize = statusButtonSizeY;
	
	public static int infoSize = shopButtonSize;
	public static int infoIconLength = 3;
	public static int infoIconSize = menuButtonSizeY;
	public static int infoIconCellSpace = menuCellSpace;							//space between the infoIcon buttons and info Rectangle is equal to 1/16 of info Rectangle.
	public static int infoIconBetweenSpace = (menuCellSpace * (1/2));		//space between the infoIcon buttons and each other is equal to 1/32 of info Rectangle. 
	public static boolean showTowerInfo = false;
	public static boolean showEnemyInfo = false;
	
	public Rectangle[] shop = new Rectangle[shopWidth];
	public Rectangle[] menu = new Rectangle[menuLength];
	public Rectangle[] status = new Rectangle[statusLength];
	public Rectangle[] statusIcon = new Rectangle[statusLength];
	public Rectangle info;
	public Rectangle[] infoIcon = new Rectangle[infoIconLength];
	
	public static SpriteSheet sprites = new SpriteSheet();	
	
	public ScreenPanel() {
		for(int i = 0; i < shop.length; i++) {
			shop[i] = new Rectangle( ((Opening.myWidth) / 2) - ((shopWidth * (shopButtonSize+shopCellSpace)) / 2) + ((shopButtonSize+shopCellSpace) * i) + (shopCellSpace / 2), (4 * (Opening.myHeight / 5)) + shopCellSpace, shopButtonSize, shopButtonSize); //(Screen.room.block[Screen.room.worldHeight-1][0].y)
		}
		
		for(int j = 0; j < menu.length; j++) {
			menu[j] = new Rectangle( ((Opening.myWidth) / 2) + ((shopWidth * (shopButtonSize+shopCellSpace)) / 2) + cellSpaceFromRoom + (shopCellSpace / 2), ((4 * (Opening.myHeight / 5)) + cellSpaceFromRoom) + ((menuButtonSizeY + menuCellSpace) * j), menuButtonSizeX, menuButtonSizeY);
		}
		
		for(int k = 0; k < status.length; k++) {
			status[k] = new Rectangle( ((Opening.myWidth) / 2) - ((shopWidth * (shopButtonSize+shopCellSpace)) / 2) - statusButtonSizeX - cellSpaceFromRoom - (shopCellSpace / 2), ((4 * (Opening.myHeight / 5)) + cellSpaceFromRoom) + ((statusButtonSizeY + statusCellSpace) * k), statusButtonSizeX, statusButtonSizeY);
		}
		
		for(int m = 0; m < status.length; m++) {
			statusIcon[m] = new Rectangle( ((Opening.myWidth) / 2) - ((shopWidth*(shopButtonSize+shopCellSpace)) / 2) - statusButtonSizeX - cellSpaceFromRoom - (shopCellSpace / 2), ((4 * (Opening.myHeight / 5)) + cellSpaceFromRoom) + ((statusButtonSizeY + statusCellSpace) * m), statusIconSize, statusIconSize);
		}
		
		info = new Rectangle( ((Opening.myWidth) / 2) + ((shopWidth * (shopButtonSize+shopCellSpace)) / 2) + cellSpaceFromRoom * 3 + shopCellSpace / 2 + menuButtonSizeX, (4 * (Opening.myHeight / 5)) + shopCellSpace, infoSize, infoSize);
	
		for(int l = 0; l < infoIcon.length; l++) {
			infoIcon[l] = new Rectangle( ((Opening.myWidth) / 2) + ((shopWidth*(shopButtonSize+shopCellSpace)) / 2) + (cellSpaceFromRoom * 3) + (shopCellSpace / 2) + menuButtonSizeX + infoIconCellSpace, ((4 * (Opening.myHeight / 5)) + cellSpaceFromRoom) + ((infoIconSize + infoIconBetweenSpace) * l) + infoIconCellSpace, infoIconSize, infoIconSize);
		}
	}
	
	public void click(int mouseClick) {
		if(mouseClick == 1) {										//Left click.
			for(int i = 0; i < shop.length; i++) {
				if(shop[i].contains(Opening.mse)) {
					if(i == 0) {
						shopButtonId[1] -= 1;
						shopButtonId[2] -= 1;
						shopButtonId[3] -= 1;
						if(shopButtonId[1] == 0) {
							shopButtonId[1] = 6;
						}
						if(shopButtonId[2] == 0) {
							shopButtonId[2] = 6;
						}
						if(shopButtonId[3] == 0) {
							shopButtonId[3] = 6;
						}
					}
					else if (i == 4) {
						shopButtonId[1] += 1;
						shopButtonId[2] += 1;
						shopButtonId[3] += 1;
						if(shopButtonId[1] == 7) {
							shopButtonId[1] = 1;
						}
						if(shopButtonId[2] == 7) {
							shopButtonId[2] = 1;
						}
						if(shopButtonId[3] == 7) {
							shopButtonId[3] = 1;
						}
					}
					else {
						holdItemId = shopButtonId[i];
						holdItem = true;
					}
				}
			}
			if (holdItem) {
				if (Screen.myGold >= Value.getTowerStats("cost", holdItemId)) {
					for (int y = 0; y < Screen.room.block.length; y++) {
						for (int x = 0; x < Screen.room.block[0].length; x++) {
							if (Screen.room.block[y][x].contains(Opening.mse)) {
								if (Screen.room.block[y][x].groundId == Value.groundOpen && Screen.room.block[y][x].airId == Value.airOpen) {
									Screen.room.block[y][x].addTower(holdItemId);
									Screen.myGold -= Value.getTowerStats("cost", holdItemId);
									holdItemId = -1;
									holdItem = false;
								}
							}
						}
					}
				}
			}
		}
		else if (mouseClick == 3) {
			holdItemId = -1;
			holdItem = false;
		}
	}
	
	static int towerCellX;
	static int towerCellY;
	  
	public static void displayTowerInfo(int blockY, int blockX) {
		if(showEnemyInfo == true) showEnemyInfo = false;
		showTowerInfo = true;
		
		towerCellY = blockY;
		towerCellX = blockX;
	}
	  
	static int enemyWave;
	static int enemyIndex;
	  
	public static void displayEnemyInfo(int wave, int enemy) {
	    if(showTowerInfo == true) showTowerInfo = false;
	    showEnemyInfo = true;
	    
	    enemyWave = wave;
	    enemyIndex = enemy;
	}
	
	public void draw(Graphics g) {
		
		//*Info Box*//
		
		g.setColor(Color.WHITE);
		g.fillRect(info.x, info.y, info.width, info.height);
		g.setColor(Color.BLACK);
		g.drawRect(info.x, info.y, info.width, info.height);
		
		g.setFont(new Font("Arial", Font.BOLD, 13));
		if(showTowerInfo) {
		    g.drawImage(new ImageIcon("res/Graphics/Attack.png").getImage(), infoIcon[0].x, infoIcon[0].y, infoIcon[0].width, infoIcon[0].height, null);
		    g.drawImage(new ImageIcon("res/Graphics/Speed.png").getImage(), infoIcon[1].x, infoIcon[1].y, infoIcon[1].width, infoIcon[1].height, null);
		    g.drawImage(new ImageIcon("res/Graphics/Range.png").getImage(), infoIcon[2].x, infoIcon[2].y, infoIcon[2].width, infoIcon[2].height, null);
		    g.drawString(Integer.toString(Room.block[towerCellY][towerCellX].tower.towerDamage), infoIcon[0].x + (shopCellSpace / 2) + infoIconSize, infoIcon[0].y + ((infoIconSize * 3)/ 4));
		    g.drawString(Integer.toString(Room.block[towerCellY][towerCellX].tower.towerRate), infoIcon[1].x + (shopCellSpace / 2) + infoIconSize, infoIcon[1].y + ((infoIconSize * 3)/ 4));
		    g.drawString(Integer.toString(Room.block[towerCellY][towerCellX].tower.towerRange), infoIcon[2].x + (shopCellSpace / 2) + infoIconSize, infoIcon[2].y + ((infoIconSize * 3)/ 4));
		}
		else if(showEnemyInfo) {
			g.drawImage(new ImageIcon("res/Graphics/Health.png").getImage(), infoIcon[0].x, infoIcon[0].y, infoIcon[0].width, infoIcon[0].height, null);
			g.drawImage(new ImageIcon("res/Graphics/Armor.png").getImage(), infoIcon[1].x, infoIcon[1].y, infoIcon[1].width, infoIcon[1].height, null);
			g.drawImage(null, infoIcon[2].x, infoIcon[2].y, infoIcon[2].width, infoIcon[2].height, null);
			g.drawString(Integer.toString(Screen.levelEnemyList.get(enemyWave)[enemyIndex].health), infoIcon[0].x + (shopCellSpace / 2) + infoIconSize, infoIcon[0].y + ((infoIconSize * 3)/ 4));
			g.drawString(Integer.toString(Screen.levelEnemyList.get(enemyWave)[enemyIndex].armor), infoIcon[1].x + (shopCellSpace / 2) + infoIconSize, infoIcon[1].y + ((infoIconSize * 3)/ 4));
			g.drawString("", infoIcon[2].x + (shopCellSpace / 2) + infoIconSize, infoIcon[2].y + ((infoIconSize * 3)/ 4));
		}
		else {
			for(int i = 0; i < infoIcon.length; i++) {
		        g.drawImage(null, infoIcon[i].x, infoIcon[i].y, infoIcon[i].width, infoIcon[i].height, null);
		        g.drawString("", infoIcon[i].x + (shopCellSpace / 2) + infoIconSize, infoIcon[i].y + ((infoIconSize * 3)/ 4));
			}
		}
		
		//*//
		
		//*Shop*//
		
		for(int i = 0; i < shop.length; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
			g.setColor(Color.BLACK);
			g.drawRect(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
			if(shopButtonId[i] != 0 && shopButtonId[i] != 7) {
				g.drawImage(new ImageIcon("res/Graphics/Gold.png").getImage(), shop[i].x, shop[i].y, statusIconSize, statusIconSize, null);
				g.setFont(new Font("Arial", Font.BOLD, 13));
				g.drawString(Integer.toString(Value.getTowerStats("cost", shopButtonId[i])), shop[i].x + (shopCellSpace / 4), shop[i].y + ((shopCellSpace * 5) / 4));
				g.drawImage(sprites.getSprite("tower", (shopButtonId[i] - 1), 0), shop[i].x + ((shopButtonSize - Room.blockSize) / 2), shop[i].y + + ((shopButtonSize - Room.blockSize) / 2), Room.blockSize, Room.blockSize, null);
			}
			if(i == 0 || i == shop.length-1) {
				g.setColor(Color.BLACK);
				g.fillOval(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
			}
			if(shop[i].contains(Opening.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
				g.setColor(Color.BLACK);
				
				//*Info Box information*//
				if(shopButtonId[i] != 0 && shopButtonId[i] != 7 && !holdItem) {
					showEnemyInfo = false;
					showTowerInfo = false;
					g.drawImage(new ImageIcon("res/Graphics/Attack.png").getImage(), infoIcon[0].x, infoIcon[0].y, infoIcon[0].width, infoIcon[0].height, null);
					g.drawImage(new ImageIcon("res/Graphics/Speed.png").getImage(), infoIcon[1].x, infoIcon[1].y, infoIcon[1].width, infoIcon[1].height, null);
					g.drawImage(new ImageIcon("res/Graphics/Range.png").getImage(), infoIcon[2].x, infoIcon[2].y, infoIcon[2].width, infoIcon[2].height, null);
					g.drawString(Integer.toString(Value.getTowerStats("attack", shopButtonId[i])), infoIcon[0].x + (shopCellSpace / 2) + infoIconSize, infoIcon[0].y + ((infoIconSize * 3)/ 4));
					g.drawString(Integer.toString(Value.getTowerStats("speed", shopButtonId[i])), infoIcon[1].x + (shopCellSpace / 2) + infoIconSize, infoIcon[1].y + ((infoIconSize * 3)/ 4));
					g.drawString(Integer.toString(Value.getTowerStats("range", shopButtonId[i])), infoIcon[2].x + (shopCellSpace / 2) + infoIconSize, infoIcon[2].y + ((infoIconSize * 3)/ 4));
				}
				//*//
			}
		}
		
		//*//
		
		//*Menu (Fast Forward, Options, Save)*//
		
		for(int j = 0; j < menu.length; j++) {
			
			if(j == 0) {
				g.drawImage(new ImageIcon("res/Graphics/MenuFF.png").getImage(), menu[j].x, menu[j].y, menu[j].width, menu[j].height, null);
			}
			else if(j == 1) {
				g.drawImage(new ImageIcon("res/Graphics/MenuPause.png").getImage(), menu[j].x, menu[j].y, menu[j].width, menu[j].height, null);
			}
			else if(j == 2) {
				g.drawImage(new ImageIcon("res/Graphics/MenuSave.png").getImage(), menu[j].x, menu[j].y, menu[j].width, menu[j].height, null);
			}
				
			if(menu[j].contains(Opening.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(menu[j].x, menu[j].y, menu[j].width, menu[j].height);
			}
		}
		
		//*//
		
		//*Status (Health, Gold, Waves Remaining)*//
		
		for(int k = 0; k < status.length; k++) {
			g.setColor(Color.WHITE);
			g.fillRect(status[k].x, status[k].y, status[k].width, status[k].height);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			
			g.drawRect(status[k].x, status[k].y, status[k].width, status[k].height);
			
			if(k == 0) {
				g.drawString("" + Screen.myHealth, statusIcon[k].x + (statusButtonSizeX / 3), statusIcon[k].y + ((3 * statusIconSize) / 4));
				g.drawImage(new ImageIcon("res/Graphics/Health.png").getImage(), statusIcon[k].x, statusIcon[k].y, statusIcon[k].width, statusIcon[k].height, null);
			} 
			else if(k == 1) {
				g.drawString("" + Screen.myGold, statusIcon[k].x + (statusButtonSizeX / 3), statusIcon[k].y + ((3 * statusIconSize) / 4));
				g.drawImage(new ImageIcon("res/Graphics/Gold.png").getImage(), statusIcon[k].x, statusIcon[k].y, statusIcon[k].width, statusIcon[k].height, null);
			} 
			else if(k == 2) {
				g.drawString("" + Screen.myWaves, statusIcon[k].x + (statusButtonSizeX / 3), statusIcon[k].y + ((3 * statusIconSize) / 4));
				g.drawImage(new ImageIcon("res/Graphics/Waves.png").getImage(), statusIcon[k].x, statusIcon[k].y, statusIcon[k].width, statusIcon[k].height, null);
			}
		}
		
		//*//
		
		if(holdItem) {
			//g.drawImage(new ImageIcon("res/Towers/tower" + holdItemId + ".png").getImage(), Opening.mse.x - (Room.blockSize / 2), Opening.mse.y - (Room.blockSize / 2), Room.blockSize, Room.blockSize, null);
			g.drawImage(sprites.getSprite("tower", (holdItemId - 1), 0), Opening.mse.x - (Room.blockSize / 2), Opening.mse.y - (Room.blockSize / 2), Room.blockSize, Room.blockSize, null);
		}
	}
}
