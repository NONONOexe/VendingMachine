package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.PorkCurry;

/**
 * ポークカレー生産工場
 * @author keisuke
 *
 */
public class PorkCurryFactory extends AbstractCurryFactory {

	public PorkCurryFactory() {
		super("ポークカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new PorkCurry();
	}

}
