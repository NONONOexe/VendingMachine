package money;

/**
 * お金の抽象クラス。きれいなお金も汚いお金もこのクラス
 * @author keisuke
 *
 */
public class Money {

	protected int value;

	/**
	 * 指定された金額のお金オブジェクトを構築します。
	 * @param value お金の金額
	 */
	public Money(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Money: 金額=" + value;
	}

	/**
	 * 金額を返します。
	 * @return 金額
	 */
	public int getValue() {
		return value;
	}
}
