package factory.drink.tea;

import item.drink.Drink;
import item.drink.tea.Wanraoji;

/**
 * 王老吉生産工場。...in Chinaなのかな？
 * @author keisuke
 *
 */
public class WanraojiFactory extends AbstractTeaFactory {

	/**
	 * 王老元生産工場を構築します。
	 */
	public WanraojiFactory() {
		super("王老元生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Wanraoji();
	}

}
