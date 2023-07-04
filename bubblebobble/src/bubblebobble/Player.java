package bubblebobble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private ImageIcon playerR, playerL;
	
	private int x;
	private int y;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	public Player() {
		initObject();
		initSetting();
	}
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 55;
		y = 535;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		setIcon(playerL);
		left = true;
		new Thread(() -> {
			while(left) {
				try {
					x = x - 1;
					setLocation(x, y);
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		setIcon(playerR);
		right = true;
		new Thread(() -> {
			while(right) {
				try {
					x = x + 1;
					setLocation(x, y);
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		y = y - 10;
		setLocation(x, y);
	}

	@Override
	public void down() {
		y = y + 10;
		setLocation(x, y);
	}
	
}
