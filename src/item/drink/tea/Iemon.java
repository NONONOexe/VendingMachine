package item.drink.tea;

/**
 * 伊右衛門
 * @author keisuke
 *
 */
public class Iemon extends Tea {

	/**
	 * 伊右衛門を構築します。
	 */
	public Iemon() {
		super("伊右衛門", 150);
	}

	/**
	 * 伊右衛門の別バージョンを指定された名前、価格で構築します。
	 * @param name 構築する伊右衛門の名前
	 * @param value 構築する伊右衛門の価格
	 */
	public Iemon(String name, int value) {
		super(name, value);
	}

}
