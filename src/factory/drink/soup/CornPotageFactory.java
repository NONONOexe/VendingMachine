package factory.drink.soup;

import item.drink.Drink;
import item.drink.soup.CornPotage;

/**
 * コーンポタージュ生産工場
 * @author keisuke
 *
 */
public class CornPotageFactory extends AbstractSoupFactory{

	/**
	 * コーンポタージュ生産工場を構築します。
	 */
	public CornPotageFactory() {
		super("コーンポタージュ生産工場");
	}

	@Override
	protected Drink createDrink() {
		return new CornPotage();
	}

}
