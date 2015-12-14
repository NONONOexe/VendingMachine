package factory.drink.water;

import item.drink.Drink;
import item.drink.water.SouthAlps;

/**
 * 南アルプスの天然水生産工場
 * @author keisuke
 *
 */
public class SouthAlpsFactory extends AbstractWaterFactory{

	/**
	 * 南アルプスの天然水生産工場を構築します。
	 */
	public SouthAlpsFactory() {
		super("南アルプスの天然水生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new SouthAlps();
	}

}
