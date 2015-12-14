package item.drink;

import item.Item;

/**
 * 飲み物
 * @author keisuke
 *
 */
public class Drink extends Item{

	private String name = "";
	private int value = 0;

	/**
	 * 指定された価格と名前を持つ飲み物を構築します。
	 * @param name 名前
	 * @param value 価格
	 */
	public Drink(String name, int value) {
		this.setValue(value);
		this.setName(name);
	}

	/**
	 * この飲み物の価値を返します。
	 * @return この飲み物の価値
	 */
	public int getValue() {
		return value;
	}

	/**
	 * この飲み物の名前を返します。
	 * @return この飲み物の名前
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	private void setValue(int value) {
		this.value = value;
	}

	private void setName(String name) {
		this.name = name;
	}

}
