package factory.drink.energydrink;

import item.drink.Drink;
import item.drink.energydrink.Raizin;

/**
 * RAIZIN生産工場
 * @author keisuke
 *
 */
public class RaizinFactory extends AbstractEnergyDrinkFactory {

	/**
	 * RAIZIN生産工場を構築します。
	 */
	public RaizinFactory() {
		super("RAIZIN生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Raizin();
	}

}
