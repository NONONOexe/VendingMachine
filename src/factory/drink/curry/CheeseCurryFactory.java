package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.CheeseCurry;

/**
 * チーズカレー生産工場
 * @author keisuke
 *
 */
public class CheeseCurryFactory extends AbstractCurryFactory {

	/**
	 * チーズカレー生産工場を構築します。
	 */
	public CheeseCurryFactory() {
		super("チーズカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new CheeseCurry();
	}

}
