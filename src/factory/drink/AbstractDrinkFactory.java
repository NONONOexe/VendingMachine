package factory.drink;

import factory.Factory;
import item.drink.Drink;
import item.drink.DrinkBox;

/**
 * 飲み物生産工場
 * @author keisuke
 *
 */
public abstract class AbstractDrinkFactory extends Factory{

	protected String factoryName;

	/**
	 * 指定された名前の工場を構築します。
	 * @param factoryName 工場名
	 */
	public AbstractDrinkFactory(String factoryName) {
		super(factoryName);
	}

	/**
	 * 24本1ケースの箱詰めした飲み物を返します。
	 * @return 24本1ケースの箱詰めした飲み物
	 */
	public DrinkBox pack() {
		DrinkBox drinkBox = new DrinkBox();
		while (!drinkBox.isFull()) {
			drinkBox.addDrink(this.createDrink());
		}
		return drinkBox;
	}

	/**
	 * 新しい飲み物を生産します。
	 * @return 生産された飲み物
	 */
	protected abstract Drink createDrink();

}
