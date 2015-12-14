package factory.drink.soup;

import factory.drink.AbstractDrinkFactory;

/**
 * スープ生産工場
 * @author keisuke
 *
 */
public abstract class AbstractSoupFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前のスープ生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractSoupFactory(String factoryName) {
		super(factoryName);
	}
}
