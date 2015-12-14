package factory.drink.energydrink;

import item.drink.Drink;
import item.drink.energydrink.Rockstar;

/**
 * ロックスター生産工場
 * @author keisuke
 *
 */
public class RocksterFactory extends AbstractEnergyDrinkFactory {

	/**
	 * ロックスター生産工場を構築します。
	 */
	public RocksterFactory() {
		super("ロックスター生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Rockstar();
	}

}
