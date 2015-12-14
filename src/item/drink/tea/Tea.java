package item.drink.tea;

import item.drink.Drink;

/**
 * お茶
 * @author keisuke
 *
 */
public class Tea extends Drink {

	/**
	 * 指定された名前、価格のお茶を構築します。
	 * @param name 構築するお茶の名前
	 * @param value 構築するお茶の価格
	 */
	public Tea(String name, int value) {
		super(name, value);
	}

}
