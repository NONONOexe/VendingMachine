package factory.drink.energydrink;

import item.drink.Drink;
import item.drink.energydrink.RedBull;

/**
 * RedBull生産工場
 * @author keisuke
 *
 */
public class RedBullFactory extends AbstractEnergyDrinkFactory {

	/**
	 * RedBull生産工場を構築します。
	 */
	public RedBullFactory() {
		super("RedBull生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new RedBull();
	}

}
