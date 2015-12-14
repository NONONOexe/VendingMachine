package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.SausageCurry;

/**
 * ソーセージカレー生産工場
 * @author keisuke
 *
 */
public class SausageCurryFactory extends AbstractCurryFactory {

	public SausageCurryFactory() {
		super("ソーセージカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new SausageCurry();
	}

}
