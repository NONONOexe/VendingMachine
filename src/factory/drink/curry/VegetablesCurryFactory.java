package factory.drink.curry;

import item.drink.Drink;
import item.drink.curry.VegetablesCurry;

/**
 * やさいカレー生産工場
 * @author keisuke
 *
 */
public class VegetablesCurryFactory extends AbstractCurryFactory {

	/**
	 * やさいカレー生産工場を構築します。
	 */
	public VegetablesCurryFactory() {
		super("やさいカレー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new VegetablesCurry();
	}

}
