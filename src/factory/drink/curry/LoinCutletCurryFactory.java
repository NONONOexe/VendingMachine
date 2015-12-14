package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.LoinCutletCurry;

/**
 * ロースカツカレー生産工場
 * @author keisuke
 *
 */
public class LoinCutletCurryFactory extends AbstractCurryFactory {

	/**
	 * ロースカツカレー生産工場を構築します。
	 */
	public LoinCutletCurryFactory() {
		super("ロースカツカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new LoinCutletCurry();
	}

}
