package factory.drink.water;

import factory.drink.AbstractDrinkFactory;

/**
 * 水生産工場
 * @author keisuke
 *
 */
public abstract class AbstractWaterFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前の水生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractWaterFactory(String factoryName) {
		super(factoryName);
	}
}
