package factory.drink.soup;

import item.drink.Drink;
import item.drink.soup.Oden;

/**
 * おでん缶生産工場
 * @author keisuke
 *
 */
public class OdenFactory extends AbstractSoupFactory {

	/**
	 * おでん缶生産工場を構築します。
	 */
	public OdenFactory() {
		super("おでん缶生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new Oden();
	}

}
