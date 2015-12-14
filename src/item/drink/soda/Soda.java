package item.drink.soda;

import item.drink.Drink;

/**
 * 炭酸飲料
 * @author keisuke
 *
 */
public class Soda extends Drink {

	/**
	 * 指定された名前、価格の炭酸飲料を構築します。
	 * @param name 構築する炭酸飲料の名前
	 * @param value 構築する炭酸飲料の価格
	 */
	public Soda(String name, int value) {
		super(name, value);
	}

}
