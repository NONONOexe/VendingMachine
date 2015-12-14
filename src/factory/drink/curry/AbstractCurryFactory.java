package factory.drink.curry;

import factory.drink.AbstractDrinkFactory;

/**
 * カレー生産工場
 * @author keisuke
 *
 */
public abstract class AbstractCurryFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前のカレー生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractCurryFactory(String factoryName) {
		super(factoryName);
	}

}
