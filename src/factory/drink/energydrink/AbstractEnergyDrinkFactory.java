package factory.drink.energydrink;

import factory.drink.AbstractDrinkFactory;

/**
 * エナジードリンク生産工場
 * @author keisuke
 *
 */
public abstract class AbstractEnergyDrinkFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前のエナジードリンク生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractEnergyDrinkFactory(String factoryName) {
		super(factoryName);
	}
}
