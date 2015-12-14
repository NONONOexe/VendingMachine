package item.drink.energydrink;

import item.drink.Drink;

/**
 * エナジードリンク
 * @author keisuke
 *
 */
public class EnergyDrink extends Drink {

	/**
	 * 指定された名前と価格のエナジードリンクを構築します。
	 * @param name 構築するエナジードリンクの名前
	 * @param value 構築するエナジードリンクの価格
	 */
	public EnergyDrink(String name, int value) {
		super(name, value);
	}

}
