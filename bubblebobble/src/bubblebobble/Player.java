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
	
	private PlayerDirection playerDirection;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean jumping;
	
	private boolean upInProgress = false;
	
	public Player() {
		initObject();
		initSetting();
		initBackgroundService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 60;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		playerDirection = PlayerDirection.RIGHT;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	
	private void initBackgroundService() {
		new Thread(new BackgroundMap(this)).start();
	}

	
	@Override
	public void left() {
		playerDirection = PlayerDirection.LEFT;
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
		playerDirection = PlayerDirection.RIGHT;
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
	    if (upInProgress) {
	        return;
	    }
	    upInProgress = true;
	    up = true;
	    new Thread(() -> {
	        synchronized (this) {
	        	jumping = true;
	            for (int i = 0; i < 130; i++) {
	                try {
	                    y = y - 1;
	                    setLocation(x, y);
	                    Thread.sleep(2);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            up = false;
	            upInProgress = false;
	            jumping = false;
	        }
	        down();
	    }).start();
	}

	@Override
	public void down() {
		down = true;
		new Thread(() -> {
			while(down) {
				try {
					y = y + 1;
					setLocation(x, y);
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
	
}
