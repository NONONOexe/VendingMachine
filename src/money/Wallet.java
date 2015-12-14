package money;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import money.bill.Bill1000;
import money.bill.Bill10000;
import money.bill.Bill2000;
import money.bill.Bill5000;
import money.coin.Coin1;
import money.coin.Coin10;
import money.coin.Coin100;
import money.coin.Coin5;
import money.coin.Coin50;
import money.coin.Coin500;
import nature.Fireble;

/**
 * 財布。夢もお金もつめられる財布
 * @author keisuke
 *
 */
public class Wallet {

	private String[] classNameList = {
			Bill10000.class.getSimpleName(),
			Bill5000.class.getSimpleName(),
			Bill2000.class.getSimpleName(),
			Bill1000.class.getSimpleName(),
			Coin500.class.getSimpleName(),
			Coin100.class.getSimpleName(),
			Coin50.class.getSimpleName(),
			Coin10.class.getSimpleName(),
			Coin5.class.getSimpleName(),
			Coin1.class.getSimpleName() };
	private Map<String, Queue<Money>> classNameMoneyQueueMap;
	private Queue<Gift> giftList;

	/**
	 * 財布オブジェクトを構築します。
	 */
	public Wallet() {
		giftList = new LinkedList<>();
		classNameMoneyQueueMap = new HashMap<>();
		for (String className : classNameList) {
			classNameMoneyQueueMap.put(className, new LinkedList<>());
		}
	}

	@Override
	public String toString() {
		return "Wallet: 合計金額=" + getSum();
	}

	/**
	 * 財布の中に入っている合計金額を返します。
	 * @return 財布の中に入っている合計金額
	 */
	public int getSum() {
		int sum = 0;
		for (String className : classNameMoneyQueueMap.keySet()) {
			for (Money money : classNameMoneyQueueMap.get(className)) {
				sum += money.getValue();
			}
		}
		for (Gift gift : giftList) {
			sum += gift.getValue();
		}
		return sum;
	}

	/**
	 * 指定されたお金をこの財布に追加します。
	 * @param money 追加されるお金
	 */
	public void addMoney(Money money) {
		String className = money.getClass().getSimpleName();
		Queue<Money> moneyList = classNameMoneyQueueMap.get(className);
		if (moneyList != null) {
			moneyList.offer(money);
		} else {
			System.err.println("");
		}
	}

	/**
	 * 指定された商品券をこの財布に追加します。
	 * @param gift 追加される商品券
	 */
	public void addGift(Gift gift) {
		giftList.add(gift);
	}

	/**
	 * 指定された複数のお金をこの財布に追加します。
	 * @param monies お金のリスト
	 */
	public void addMoney(Queue<Money> monies) {
		for (Money money : monies) {
			this.addMoney(money);
		}
	}

	/**
	 * 指定された複数のお金をこの財布に追加します。
	 * @param monies お金の配列
	 */
	public void addMoney(Money[] monies) {
		for (Money money : monies) {
			this.addMoney(money);
		}
	}

	/**
	 * 指定された金額がこの財布から支払えるかどうかを示します
	 * @param price 支払えるかどうかを調べる金額
	 * @return 指定された金額が支払えるかどうか
	 */
	public boolean canPay(int price) {
		if (price < 0) {
			return false;
		}
		for (String className : classNameList) {
			for (Money money : classNameMoneyQueueMap.get(className)) {
				if (money.getValue() <= price) {
					price -= money.getValue();
				} else {
					break;
				}
			}
		}
		return price == 0;
	}

	/**
	 * 指定された金額分のお金をこの財布から取り出し返します。取り出せない場合はnullを返します。
	 * @param price 支払う金額
	 * @return 指定された金額分のお金のリスト。取り出せない場合はnull
	 */
	public Queue<Money> getMonies(int price) {
		Queue<Money> monies = new LinkedList<>();
		if (!canPay(price)) {
			System.out.println(price + "円を財布から取り出すことができません。小銭がない、もしくはお金が足りていません。財布の中身を確認してみてください。");
			return monies;
		}
		for (String className : classNameList) {
			Queue<Money> moneyList = classNameMoneyQueueMap.get(className);
			while (!moneyList.isEmpty()) {
				if (moneyList.peek().getValue() <= price) {
					Money money = moneyList.poll();
					monies.offer(money);
					price -= money.getValue();
				} else {
					break;
				}
			}
		}
		return monies;
	}

	/**
	 * 中に入っているものを全て表示します
	 */
	public void printAllMoney() {
		for (String className : classNameMoneyQueueMap.keySet()) {
			for (Money money : classNameMoneyQueueMap.get(className)) {
				System.out.println(money);
			}
		}
		for (Gift gift : giftList) {
			System.out.println(gift);
		}
	}

	/**
	 * 財布を燃やします。可燃物は価値が消滅します。
	 */
	public void fire() {
		for (String className : classNameMoneyQueueMap.keySet()) {
			for (Money money : classNameMoneyQueueMap.get(className)) {
				if (money instanceof Fireble) {
					((Fireble) money).fire();
				}
			}
		}
		for (Gift gift : giftList) {
			gift.fire();
		}
	}

}
