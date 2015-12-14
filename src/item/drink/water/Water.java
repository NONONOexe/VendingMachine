package item.drink.water;

import item.drink.Drink;

/**
 * 水
 * @author keisuke
 *
 */
public class Water extends Drink {

	/**
	 * 指定された名前、価格の水を構築します。
	 * @param name 構築する水の名前
	 * @param value 構築する水の価格
	 */
	public Water(String name, int value) {
		super(name, value);
	}

}
