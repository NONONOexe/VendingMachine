package item.drink.coffee;

import item.drink.Drink;

/**
 * コーヒー
 * @author keisuke
 *
 */
public class Coffee extends Drink {

	/**
	 * 指定された名前、価格のコーヒーを構築します。
	 * @param name 構築するコーヒーの名前
	 * @param value 構築するコーヒーの価格
	 */
	public Coffee(String name, int value) {
		super(name, value);
	}

}
