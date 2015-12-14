package factory.drink.water;

import item.drink.Drink;
import item.drink.water.CrystalGeyser;

/**
 * クリスタルガイザー生産工場
 * @author keisuke
 *
 */
public class CrystalGeyserFactory extends AbstractWaterFactory {

	/**
	 * クリスタルガイザー生産工場を構築します。
	 */
	public CrystalGeyserFactory() {
		super("クリスタルガイザー生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new CrystalGeyser();
	}

}
