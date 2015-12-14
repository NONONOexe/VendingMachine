package factory.drink.tea;

import item.drink.Drink;
import item.drink.tea.Ayataka;

/**
 * 綾鷹生産工場
 * @author keisuke
 *
 */
public class AyatakaFactory extends AbstractTeaFactory {

	/**
	 * 綾鷹生産工場を構築します。
	 */
	public AyatakaFactory() {
		super("綾鷹生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Ayataka();
	}

}
