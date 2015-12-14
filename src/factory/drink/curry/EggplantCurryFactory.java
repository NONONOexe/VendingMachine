package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.EggplantCurry;

/**
 * なすカレー生産工場
 * @author keisuke
 *
 */
public class EggplantCurryFactory extends AbstractCurryFactory {

	/**
	 * なすカレー生産工場を構築します。
	 */
	public EggplantCurryFactory() {
		super("なすカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new EggplantCurry();
	}

}
