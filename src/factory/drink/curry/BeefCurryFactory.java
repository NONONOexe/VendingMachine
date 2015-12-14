package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.BeefCurry;

/**
 * ビーフカレー生産工場
 * @author keisuke
 *
 */
public class BeefCurryFactory extends AbstractCurryFactory {

	/**
	 * ビーフカレー生産工場を構築します。
	 */
	public BeefCurryFactory() {
		super("ビーフカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new BeefCurry();
	}

}
