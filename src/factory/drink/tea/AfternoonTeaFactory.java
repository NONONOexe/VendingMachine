package factory.drink.tea;

import item.drink.Drink;
import item.drink.tea.AfternoonTea;

/**
 * 午後の紅茶生産工場
 * @author keisuke
 *
 */
public class AfternoonTeaFactory extends AbstractTeaFactory {

	/**
	 * 午後の紅茶生産工場を構築します。
	 */
	public AfternoonTeaFactory() {
		super("午後の紅茶生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new AfternoonTea();
	}

}
