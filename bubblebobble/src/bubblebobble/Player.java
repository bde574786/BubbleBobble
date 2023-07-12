package bubblebobble;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private GameFrame mContext;
	private List<Bubble> bubbleList;
	
	private ImageIcon playerR, playerL;

	private ImageIcon leftAttackMotion1, leftAttackMotion2, leftAttackMotion3, leftAttackMotion4, leftAttackMotion5;
	private ImageIcon rightAttackMotion1, rightAttackMotion2, rightAttackMotion3, rightAttackMotion4,
			rightAttackMotion5;

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

	public Player(GameFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
		leftAttackMotion1 = new ImageIcon("image/leftAttackMotion1.png");
		leftAttackMotion2 = new ImageIcon("image/leftAttackMotion2.png");
		leftAttackMotion3 = new ImageIcon("image/leftAttackMotion3.png");
		leftAttackMotion4 = new ImageIcon("image/leftAttackMotion4.png");
		leftAttackMotion5 = new ImageIcon("image/leftAttackMotion5.png");
		rightAttackMotion1 = new ImageIcon("image/rightAttackMotion1.png");
		rightAttackMotion2 = new ImageIcon("image/rightAttackMotion2.png");
		rightAttackMotion3 = new ImageIcon("image/rightAttackMotion3.png");
		rightAttackMotion4 = new ImageIcon("image/rightAttackMotion4.png");
		rightAttackMotion5 = new ImageIcon("image/rightAttackMotion5.png");
		
		bubbleList = new ArrayList<>();

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

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayer(this)).start();
	}

	@Override
	public void attack() {
		new Thread(() -> {
			Bubble bubble = new Bubble(mContext);
			if (playerDirection == PlayerDirection.LEFT) {
				attackMotion(leftAttackMotion1, leftAttackMotion2, leftAttackMotion3, leftAttackMotion4,
						leftAttackMotion5, playerL);
				mContext.add(bubble);
				bubbleList.add(bubble);
				bubble.left();
			} else {
				attackMotion(rightAttackMotion1, rightAttackMotion2, rightAttackMotion3, rightAttackMotion4,
						leftAttackMotion5, playerR);
				mContext.add(bubble);
				bubble.right();
			}
		}).start();
	}

	@Override
	public void left() {
		playerDirection = PlayerDirection.LEFT;
		setIcon(playerL);
		left = true;
		new Thread(() -> {
			while (left) {
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
			while (right) {
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
			while (down) {
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

	private void attackMotion(ImageIcon motion1, ImageIcon motion2, ImageIcon motion3, ImageIcon motion4,
			ImageIcon motion5, ImageIcon resetImage) {
		try {
			setIcon(motion1);
			Thread.sleep(20);
			setIcon(motion2);
			Thread.sleep(20);
			setIcon(motion3);
			Thread.sleep(20);
			setIcon(motion4);
			Thread.sleep(20);
			setIcon(motion5);
			Thread.sleep(20);
			setIcon(resetImage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
