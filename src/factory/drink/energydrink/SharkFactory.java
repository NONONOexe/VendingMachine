package factory.drink.energydrink;

import item.drink.Drink;
import item.drink.energydrink.Shark;

/**
 * SHARK生産工場
 * @author keisuke
 *
 */
public class SharkFactory extends AbstractEnergyDrinkFactory {

	/**
	 * SHARK生産工場を構築します。
	 */
	public SharkFactory() {
		super("SHARK生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Shark();
	}

}
