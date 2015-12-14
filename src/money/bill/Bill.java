package money.bill;

import money.Money;
import nature.Fireble;

/**
 * 紙幣
 * @author keisuke
 *
 */
public class Bill extends Money implements Fireble {

	/**
	 * 指定された金額の紙幣を構築します。
	 * @param value 構築する金額
	 */
	public Bill(int value) {
		super(value);
	}

	@Override
	public String toString() {
		return value + "円紙幣";
	}

	@Override
	public void fire() {
		this.value = 0;
	}
}
