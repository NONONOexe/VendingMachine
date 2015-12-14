package factory.drink.coffee;

import item.drink.Drink;
import item.drink.coffee.Fire;

/**
 * Fire生産工場
 * @author keisuke
 *
 */
public class FireFactory extends AbstractCoffeeFactory {

	/**
	 * Fire生産工場を構築します。
	 */
	public FireFactory() {
		super("Fire生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Fire();
	}

}
