package factory.drink.soda;

import item.drink.Drink;
import item.drink.soda.CCLemon;

/**
 * C.C.レモン生産工場
 * @author keisuke
 *
 */
public class CCLemonFactory extends AbstractSodaFactory {

	/**
	 * C.C.レモン生産工場を構築します。
	 */
	public CCLemonFactory() {
		super("C.C.レモン生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new CCLemon();
	}

}
