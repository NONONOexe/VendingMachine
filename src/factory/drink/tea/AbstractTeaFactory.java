package factory.drink.tea;

import factory.drink.AbstractDrinkFactory;

/**
 * お茶生産工場
 * @author keisuke
 *
 */
public abstract class AbstractTeaFactory extends AbstractDrinkFactory {

	/**
	 * 指定された名前のお茶生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractTeaFactory(String factoryName) {
		super(factoryName);
	}
}
