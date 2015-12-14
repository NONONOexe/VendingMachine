package item.drink.soup;

import item.drink.Drink;

/**
 * スープ
 * @author keisuke
 *
 */
public class Soup extends Drink {

	/**
	 * 指定された名前、価格のスープを構築します。
	 * @param name 構築するスープの名前
	 * @param value 構築するスープの価格
	 */
	public Soup(String name, int value) {
		super(name, value);
	}

}
