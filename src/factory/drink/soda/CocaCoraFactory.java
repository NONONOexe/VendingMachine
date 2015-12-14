package factory.drink.soda;

import item.drink.Drink;
import item.drink.soda.CocaCora;

/**
 * コカ・コーラ生産工場
 * @author keisuke
 *
 */
public class CocaCoraFactory extends AbstractSodaFactory {

	/**
	 * コカ・コーラ生産工場を構築します。
	 */
	public CocaCoraFactory() {
		super("コカ・コーラ生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new CocaCora();
	}

}
