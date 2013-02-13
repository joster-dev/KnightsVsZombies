import java.awt.*;
import javax.swing.*;

public class ScreenPanel {
	
	public static int cellSpaceFromRoom = ((Opening.myHeight / 5) / 8);				//space between panel buttons and the Room is equal to (1/8) of the vertical space.
	
	public static int shopWidth = 5;
	public static int shopButtonSize = ((3 * (Opening.myHeight / 5)) / 4);			//shop buttons take up 3/4 (6/8) of the of the vertical space allocated at the bottom of the screen. 
	public static int shopCellSpace = cellSpaceFromRoom;				
	
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
	public static int infoIconCellSpace = menuCellSpace;							//space between the infoIcon buttons and info Rectangle is equal to 1/16 (3/48) of info Rectangle.
	public static int infoIconBetweenSpace = (menuCellSpace * (1/2) - (1/32));		//space between the infoIcon buttons and each other is equal to 1/32 of info of info Rectangle. 
	
	public Rectangle[] shop = new Rectangle[shopWidth];
	public Rectangle[] menu = new Rectangle[menuLength];
	public Rectangle[] status = new Rectangle[statusLength];
	public Rectangle[] statusIcon = new Rectangle[statusLength];
	public Rectangle info;
	public Rectangle[] infoIcon = new Rectangle[infoIconLength];
	
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
	
		for(int l = 0; l < status.length; l++) {
			infoIcon[l] = new Rectangle( ((Opening.myWidth) / 2) + ((shopWidth*(shopButtonSize+shopCellSpace)) / 2) + (cellSpaceFromRoom * 3) + (shopCellSpace / 2) + menuButtonSizeX + infoIconCellSpace, ((4 * (Opening.myHeight / 5)) + cellSpaceFromRoom) + ((infoIconSize + infoIconBetweenSpace)*l) + infoIconCellSpace, infoIconSize, infoIconSize);
		}
	}
	
	public void draw(Graphics g) {
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		
		for(int i = 0; i < shop.length; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
			g.setColor(Color.BLACK);
			g.drawRect(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
			if(shop[i].contains(Opening.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(shop[i].x, shop[i].y, shop[i].width, shop[i].height);
			}
		}
		
		for(int j = 0; j < menu.length; j++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(menu[j].x, menu[j].y, menu[j].width, menu[j].height);
			g.setColor(Color.BLACK);
			g.drawRect(menu[j].x, menu[j].y, menu[j].width, menu[j].height);
				
			if(menu[j].contains(Opening.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(menu[j].x, menu[j].y, menu[j].width, menu[j].height);
			}
		}
		
		for(int k = 0; k < status.length; k++) {
			g.setColor(Color.WHITE);
			g.fillRect(status[k].x, status[k].y, status[k].width, status[k].height);
			g.setColor(Color.BLACK);
			g.drawRect(status[k].x, status[k].y, status[k].width, status[k].height);
		}
		
		for(int m = 0; m < status.length; m++) {
			if(m == 0) {
				g.setColor(Color.RED);
				g.fillRect(statusIcon[m].x, statusIcon[m].y, statusIcon[m].width, statusIcon[m].height);
				g.setColor(Color.BLACK);
				g.drawRect(statusIcon[m].x, statusIcon[m].y, statusIcon[m].width, statusIcon[m].height);
				g.drawString("" + Screen.myHealth, statusIcon[m].x + (statusButtonSizeX / 3), statusIcon[m].y + ((3 * statusIconSize) / 4));
			} 
			else if(m == 1) {
				g.setColor(Color.YELLOW);
				g.fillRect(statusIcon[m].x, statusIcon[m].y, statusIcon[m].width, statusIcon[m].height);
				g.setColor(Color.BLACK);
				g.drawRect(statusIcon[m].x, statusIcon[m].y, statusIcon[m].width, statusIcon[m].height);
				g.drawString("" + Screen.myGold, statusIcon[m].x + (statusButtonSizeX / 3), statusIcon[m].y + ((3 * statusIconSize) / 4));
			} 
			else if(m == 2) {
				g.setColor(Color.GREEN);
				g.fillRect(statusIcon[m].x, statusIcon[m].y, statusIcon[m].width, statusIcon[m].height);
				g.setColor(Color.BLACK);
				g.drawRect(statusIcon[m].x, statusIcon[m].y, statusIcon[m].width, statusIcon[m].height);
				g.drawString("" + Screen.myWaves, statusIcon[m].x + (statusButtonSizeX / 3), statusIcon[m].y + ((3 * statusIconSize) / 4));
			}
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(info.x, info.y, info.width, info.height);
		g.setColor(Color.BLACK);
		g.drawRect(info.x, info.y, info.width, info.height);
		
		for(int l = 0; l < infoIcon.length; l++) {
			g.setColor(Color.MAGENTA);
			g.fillRect(infoIcon[l].x, infoIcon[l].y, infoIcon[l].width, infoIcon[l].height);
			g.setColor(Color.BLACK);
			g.drawRect(infoIcon[l].x, infoIcon[l].y, infoIcon[l].width, infoIcon[l].height);
		}
			/*g.drawImage(new ImageIcon("res/knights/tower" + buttonId[i] + ".png").getImage(), button[i].x + itemIn, button[i].y + itemIn, button[i].width - (itemIn*2), button[i].height - (itemIn*2), null);
			
			if (buttonPrice[buttonId[i]] > 0) {
				g.setColor(new Color(255,255,255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString(buttonPrice[buttonId[i]] + "", button[i].x + itemIn, button[i].y + itemIn + 10);
			}*/
			
			/*if (holdsItem) {
				g.drawImage(new ImageIcon("res/knights/tower" + heldId + ".png").getImage(), Screen.mse.x - ((button[i].width + (itemIn*2))/2), Screen.mse.y - ((button[i].height + (itemIn*2))/2), button[i].width, button[i].height - 18, null);
			}*/
	}
}
