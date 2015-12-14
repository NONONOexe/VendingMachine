package money;

import nature.Fireble;

/**
 * 商品券
 * @author keisuke
 *
 */
public class Gift implements Fireble {

	public enum Color {
		WHITE, RED, BLUE, GREEN
	}

	protected int value;
	private Color color = Color.WHITE;

	/**
	 * 指定された金額の商品券オブジェクトを構築します。
	 * @param value 商品券の金額
	 */
	public Gift(int value) {
		if (value <= 0) {
			throw new IllegalArgumentException("商品券の金額に負数を指定することはできません");
		}
		this.value = value;
	}

	/**
	 * 指定された金額、色の商品券オブジェクトを構築します。
	 * @param value 商品券の金額
	 * @param color 商品券の色
	 */
	public Gift(int value, Color color) {
		this.value = value;
		this.color = color;
	}

	@Override
	public String toString() {
		String colorName = "";
		switch (color) {
		case WHITE:
			colorName = "白";
			break;
		case RED:
			colorName = "赤";
			break;
		case BLUE:
			colorName = "青";
			break;
		case GREEN:
			colorName = "緑";
			break;
		}
		return value + "円分の" + colorName + "色の商品券";
	}

	/**
	 * 金額を返します。
	 * @return 金額
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 商品券を燃やします。無価値になります
	 */
	@Override
	public void fire() {
		value = 0;
	}
}
