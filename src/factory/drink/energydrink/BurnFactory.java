package factory.drink.energydrink;

import item.drink.Drink;
import item.drink.energydrink.Burn;

/**
 * burn生産工場
 * @author keisuke
 *
 */
public class BurnFactory extends AbstractEnergyDrinkFactory {

	/**
	 * burn生産工場を構築します。
	 */
	public BurnFactory() {
		super("burn生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Burn();
	}

}
