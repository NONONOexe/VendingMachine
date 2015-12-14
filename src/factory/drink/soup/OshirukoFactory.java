package factory.drink.soup;

import item.drink.Drink;
import item.drink.soup.Oshiruko;

/**
 * おしるこ生産工場
 * @author keisuke
 *
 */
public class OshirukoFactory extends AbstractSoupFactory {

	/**
	 * おしるこ生産工場を構築します。
	 */
	public OshirukoFactory() {
		super("おしるこ生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Oshiruko();
	}

}
