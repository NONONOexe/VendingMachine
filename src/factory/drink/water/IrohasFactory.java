package factory.drink.water;

import item.drink.Drink;
import item.drink.water.Irohas;

/**
 * いろはす生産工場
 * @author keisuke
 *
 */
public class IrohasFactory extends AbstractWaterFactory {

	/**
	 * いろはす生産工場を構築します。
	 */
	public IrohasFactory() {
		super("いろはす生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Irohas();
	}

}
