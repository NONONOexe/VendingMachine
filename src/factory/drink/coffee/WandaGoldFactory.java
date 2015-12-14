package factory.drink.coffee;

import item.drink.Drink;
import item.drink.coffee.WandaGold;

/**
 * 金の微糖生産工場
 * @author keisuke
 *
 */
public class WandaGoldFactory extends AbstractCoffeeFactory {

	/**
	 * 金の微糖生産工場を構築します。
	 */
	public WandaGoldFactory() {
		super("金の微糖生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new WandaGold();
	}

}
