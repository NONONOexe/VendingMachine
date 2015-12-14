package money.coin;

import money.Money;

/**
 * 硬貨
 * @author keisuke
 *
 */
public class Coin extends Money {

	/**
	 * 指定された金額の硬貨オブジェクトを構築します。
	 * @param value 金額
	 */
	public Coin(int value) {
		super(value);
	}

	@Override
	public String toString() {
		return value + "円硬貨";
	}
}
