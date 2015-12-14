package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.FishFryCurry;

/**
 * フィッシュフライカレー生産工場
 * @author keisuke
 *
 */
public class FishFryCurryFactory extends AbstractCurryFactory {

	/**
	 * フィッシュフライカレー生産工場を構築します。
	 */
	public FishFryCurryFactory() {
		super("フィッシュフライカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new FishFryCurry();
	}

}
