package factory.drink.coffee;

import item.drink.Drink;
import item.drink.coffee.BossCoffee;

/**
 * ボス生産工場
 * @author keisuke
 *
 */
public class BossCoffeeFactory extends AbstractCoffeeFactory {

	/**
	 * ボス生産工場を構築します。
	 */
	public BossCoffeeFactory() {
		super("ボス生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new BossCoffee();
	}

}
