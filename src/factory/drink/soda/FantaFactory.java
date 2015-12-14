package factory.drink.soda;

import item.drink.Drink;
import item.drink.soda.Fanta;

/**
 * ファンタ生産工場
 * @author keisuke
 *
 */
public class FantaFactory extends AbstractSodaFactory {

	/**
	 * ファンタ生産工場を構築します。
	 */
	public FantaFactory() {
		super("ファンタ生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Fanta();
	}

}
