package factory.drink.coffee;

import item.drink.Drink;
import item.drink.coffee.EmeraldMountain;

/**
 * エメマン生産工場
 * @author keisuke
 *
 */
public class EmeraldMountainFactory extends AbstractCoffeeFactory {

	/**
	 * エメマン生産工場を構築します。
	 */
	public EmeraldMountainFactory() {
		super("エメマン生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new EmeraldMountain();
	}

}
