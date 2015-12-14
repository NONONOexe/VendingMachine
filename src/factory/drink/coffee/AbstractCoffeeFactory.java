package factory.drink.coffee;

import factory.drink.AbstractDrinkFactory;

/**
 * コーヒー生産工場
 * @author keisuke
 *
 */
public abstract class AbstractCoffeeFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前のコーヒー生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractCoffeeFactory(String factoryName) {
		super(factoryName);
	}
}
