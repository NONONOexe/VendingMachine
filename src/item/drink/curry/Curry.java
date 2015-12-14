package item.drink.curry;

import item.drink.Drink;

/**
 * カレー。人によっては飲み物
 * @author keisuke
 *
 */
public class Curry extends Drink{

	/**
	 * 指定された価格、名前のカレーを構築します。
	 * @param name 構築するカレーの名前
	 * @param value 構築するカレーの価格
	 */
	public Curry(String name, int value) {
		super(name, value);
	}
}
