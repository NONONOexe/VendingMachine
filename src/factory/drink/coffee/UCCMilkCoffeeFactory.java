package factory.drink.coffee;

import item.drink.Drink;
import item.drink.coffee.UCCMilkCoffee;

/**
 * UCCミルクコーヒー生産工場
 * @author keisuke
 *
 */
public class UCCMilkCoffeeFactory extends AbstractCoffeeFactory {

	/**
	 * UCCミルクコーヒー生産工場を構築します。
	 */
	public UCCMilkCoffeeFactory() {
		super("UCCミルクコーヒー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new UCCMilkCoffee();
	}

}
