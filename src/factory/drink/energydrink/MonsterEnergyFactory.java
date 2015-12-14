package factory.drink.energydrink;

import item.drink.Drink;
import item.drink.energydrink.MonsterEnergy;

/**
 * モンスターエナジー生産工場
 * @author keisuke
 *
 */
public class MonsterEnergyFactory extends AbstractEnergyDrinkFactory {

	/**
	 * モンスターエナジー生産工場を構築します。
	 */
	public MonsterEnergyFactory() {
		super("モンスターエナジー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new MonsterEnergy();
	}

}
