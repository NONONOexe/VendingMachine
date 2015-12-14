
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * おまけ。GUIの自販機
 * @author keisuke
 *
 */
public class VendingMachineGUI {

	public static final int COMMAND_NUM = 4;
	public static final int DRINK_NUM = 5;
	public static final int MONEY_TYPE = 1;
	public static final int MONSTER = 0;
	public static final int REDBULL = 1;
	public static final int SAMURIDE = 2;
	public static final int YEN100 = 0;
	public static final int BURN = 3;
	public static final int CURRY = 4;

	static int money = 0;
	static String message = "";
	static int left[] = new int[DRINK_NUM];
	static int price[] = new int[DRINK_NUM];
	static String name[] = new String[DRINK_NUM];

	private static JFrame frame = new JFrame();

	static BufferedImage[] drinkImage = new BufferedImage[DRINK_NUM];
	static BufferedImage[] moneyImage = new BufferedImage[MONEY_TYPE];

	static List<Integer> boughtDrink = new ArrayList<>();

	public static void main(String[] args) {

		left[MONSTER] = 5;
		left[REDBULL] = 5;
		left[SAMURIDE] = 5;
		left[BURN] = 5;
		left[CURRY] = 1000;

		price[MONSTER] = 200;
		price[REDBULL] = 200;
		price[SAMURIDE] = 200;
		price[BURN] = 200;
		price[CURRY] = 350;

		name[MONSTER] = "Monster";
		name[REDBULL] = "RedBull";
		name[SAMURIDE] = "SUMURIDE";
		name[BURN] = "burn";
		name[CURRY] = "CURRY";

		drinkImage[MONSTER] = getInputImage("./img/monster.gif");
		drinkImage[REDBULL] = getInputImage("./img/redbull.gif");
		drinkImage[SAMURIDE] = getInputImage("./img/samuride.gif");
		drinkImage[BURN] = getInputImage("./img/burn.gif");
		drinkImage[CURRY] = getInputImage("./img/curry.gif");

		moneyImage[YEN100] = getInputImage("./img/100yen.gif");

		frame = new JFrame("自動販売機");
		JButton[] drinkButton = new JButton[DRINK_NUM];
		for (int drink = 0; drink < DRINK_NUM; drink++) {
			drinkButton[drink] = new JButton("￥" + price[drink]);
		}
		JButton returnButton = new JButton("返金");
		JButton yen100Button = new JButton();
		JPanel panel = new JPanel() {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				drawVendingMachine(g);

				g.setColor(Color.GREEN);
				g.drawString(message, 40, 210);

				int margin = 50;
				int dx = 0;
				for (int drink = 0; drink < DRINK_NUM; drink++) {
					if (drink != CURRY) {
						g.drawImage(drinkImage[drink], margin + dx, 30, 50, 100, this);
					} else {
						g.drawImage(drinkImage[drink], margin + dx, 30, 100, 100, this);
					}
					dx += 100;
				}

				margin = 30;
				dx = 0;
				int dy = 0;
				for (int drink : boughtDrink) {
					if (drink != CURRY) {
						g.drawImage(drinkImage[drink], margin + dx, 300 + dy, 25, 50, this);
					} else {
						g.drawImage(drinkImage[drink], margin + dx, 300 + dy, 50, 50, this);
					}
					dx += 30;
					if (750 < dx) {
						dx = 0;
						dy += 30;
					}
				}
			}

			private void drawVendingMachine(Graphics g) {
				g.setColor(Color.RED);
				g.fillRect(10, 10, 670, 300);
				g.setColor(Color.WHITE);
				g.fillRect(20, 20, 560, 160);
				g.setColor(Color.BLACK);
				g.fillRect(630, 40, 5, 40);
				g.drawRect(600, 90, 60, 5);
				g.drawRect(50, 235, 550, 45);
				g.fillRect(30, 190, 540, 30);
			}
		};

		panel.setBackground(Color.white);
		panel.setBounds(0, 0, 800, 600);
		int margin = 40;
		int dx = 0;
		for (int drink = 0; drink < DRINK_NUM; drink++) {
			drinkButton[drink].setName(String.valueOf(drink));
			drinkButton[drink].addActionListener(e -> {
				JButton pushedDrinkButton = (JButton) e.getSource();
				int pushedDrink = Integer.valueOf(pushedDrinkButton.getName());
				buyDrink(pushedDrink);
				if (left[pushedDrink] <= 0) {
					pushedDrinkButton.setEnabled(false);
				}
			});
			drinkButton[drink].setBounds(margin + dx, 140, 80, 30);
			frame.add(drinkButton[drink]);
			if (drink != CURRY - 1) {
				dx += 100;
			} else {
				dx += 120;
			}
		}
		returnButton.setBounds(600, 110, 60, 30);
		returnButton.addActionListener(e -> {
			returnMoney();
			display();
		});
		yen100Button.setBounds(710, 40, 40, 40);
		yen100Button.setIcon(new ImageIcon(moneyImage[YEN100]));
		yen100Button.addActionListener(e -> {
			insertMoney(100);
			display();
		});
		frame.add(returnButton);
		frame.add(yen100Button);
		frame.add(panel);
		frame.setLayout(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);

		while (true) {
			display();
			System.out.print("0:無し 1:100円 2:飲み物を買う 3:返金ボタン > ");
			int command = getInputNumber();
			if (command == 0) {
				continue;
			} else if (command == 1) {
				insertMoney(100);
			} else if (command == 2) {
				displayDrink();
				int drink = getInputNumber();
				if (drink < DRINK_NUM) {
					buyDrink(drink);
				} else {
					System.out.println("0から" + (DRINK_NUM - 1) + "で選択してください");
				}
			} else if (command == 3) {
				returnMoney();
			} else {
				System.out.println("0から" + (COMMAND_NUM - 1) + "のいづれかを入力してください。");
			}
		}
	}

	private static void display() {
		System.out.printf("# 自販機: %d円\n", money);
		message = "投入金額 : " + money;
	}

	/**
	 * 飲み物を購入したときの処理です。
	 * @param drink
	 */
	private static void buyDrink(int drink) {
		if (0 < left[drink]) {
			if (price[drink] <= money) {
				message = "購入ありがとうございます : " + name[drink] + " 投入金額 : " + money;
				System.out.println(name[drink] + "を出しました");
				money -= price[drink];
				left[drink]--;
				boughtDrink.add(drink);
			} else {
				message = "お金が足りません。投入金額 : " + money;
				System.out.println(message);
			}
		} else {
			System.out.println("商品がありません");
		}
		frame.repaint();
	}

	/**
	 * 返却ボタンを押したときの処理です。
	 */
	private static void returnMoney() {
		message = "おつりを返却しました。";
		System.out.println("お金を返却しました。");
		money = 0;
		frame.repaint();
	}

	/**
	 * 指定された金額を投入します。
	 * @param in 投入する金額
	 */
	private static void insertMoney(int in) {
		money += in;
		frame.repaint();
	}

	private static void displayDrink() {
		for (int drink = 0; drink < DRINK_NUM; drink++) {
			System.out.printf("\t%d:%s %d円-のこり%d個\n", drink, name[drink], price[drink], left[drink]);
		}
		System.out.print("\t> ");
	}

	/**
	 * 指定されたアドレスにある画像ファイルのイメージを返します
	 * @param fileAddress 画像ファイルのアドレス
	 * @return 画像ファイルのイメージ
	 */
	private static BufferedImage getInputImage(String fileAddress) {
		try {
			FileInputStream in = new FileInputStream(fileAddress);
			BufferedImage img = ImageIO.read(in);
			in.close();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 入力した数字を返します。
	 * @return 入力した数字
	 * @author keisuke
	 */
	private static int getInputNumber() {
		int num = 0;
		String str = getInputString();
		try {
			num = Integer.valueOf(str);
		} catch (NumberFormatException ife) {
			System.err.println("数字を入力してください。");
		}
		return num;
	}

	/**
	 * 入力した文字列を返します。
	 * @return 入力した文字列
	 * @author keisuke
	 */
	private static String getInputString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
