package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.BeefCutletCurry;

/**
 * ビーフカツカレー生産工場
 * @author keisuke
 *
 */
public class BeefCutletCurryFactory extends AbstractCurryFactory {

	/**
	 * ビーフカツカレー生産工場を構築します。
	 */
	public BeefCutletCurryFactory() {
		super("ビーフカツカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new BeefCutletCurry();
	}

}
