package factory.drink.tea;

import item.drink.Drink;
import item.drink.tea.TokuCha;

/**
 * 伊右衛門特茶生産工場。
 * @author keisuke
 *
 */
public class TokuChaFactory extends IemonFactory {

	/**
	 * 伊右衛門特茶生産工場を構築します。
	 */
	public TokuChaFactory() {
		super("伊右衛門特茶生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new TokuCha();
	}

}
