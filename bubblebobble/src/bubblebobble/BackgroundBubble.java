package bubblebobble;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBubble {

	private BufferedImage image;
	private Bubble bubble;

	public BackgroundBubble(Bubble bubble) {
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean leftWall() {
		Color leftColor = new Color(image.getRGB(bubble.getX() - 5, bubble.getY() + 25));
		if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bubble.getX() + 65, bubble.getY() + 25));
		  if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				return true;
			}
		return false;
	}
	
	public boolean topWall() {
		Color topColor = new Color(image.getRGB(bubble.getX()+25, bubble.getY()-10));
		 if (topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
				return true;
			}
		return false;
	}
}
