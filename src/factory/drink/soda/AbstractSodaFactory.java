package factory.drink.soda;

import factory.drink.AbstractDrinkFactory;

/**
 * 炭酸飲料生産工場
 * @author keisuke
 *
 */
public abstract class AbstractSodaFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前の炭酸飲料生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractSodaFactory(String factoryName) {
		super(factoryName);
	}
}
