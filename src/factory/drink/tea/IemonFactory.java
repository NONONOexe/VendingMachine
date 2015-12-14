package factory.drink.tea;

import item.drink.Drink;
import item.drink.tea.Iemon;

/**
 * 伊右衛門生産工場
 * @author keisuke
 *
 */
public class IemonFactory extends AbstractTeaFactory {

	/**
	 * 伊右衛門生産工場を構築します。
	 */
	public IemonFactory() {
		super("伊右衛門生産工場");
	}

	/**
	 * 指定された名前の伊右衛門生産工場を構築します。
	 * @param factoryName 工場名
	 */
	public IemonFactory(String factoryName) {
		super(factoryName);
	}

	@Override
	protected Drink createDrink() {
		return new Iemon();
	}

}
