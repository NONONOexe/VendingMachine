package vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import factory.drink.AbstractDrinkFactory;
import item.Item;
import item.VendingMachineKey;
import item.drink.Drink;
import item.drink.DrinkBox;
import money.Money;
import money.bill.Bill1000;
import money.bill.Bill10000;
import money.bill.Bill5000;
import money.coin.Coin10;
import money.coin.Coin100;
import money.coin.Coin50;
import money.coin.Coin500;
import vedingmachine.VendingMachine;

/**
 * 自販機業者
 * @author keisuke
 *
 */
public class Vendor {

	private VendingMachine vendingMachine;
	private List<Item> itemList = new LinkedList<>();
	private List<AbstractDrinkFactory> factoryList = new ArrayList<>();
	private Queue<Money> capital = new LinkedList<>();
	private Queue<Money> benefit = new LinkedList<>();
	private static final int PREPARE = 20;

	/**
	 * 指定された自販機に対する業者を構築します。
	 * @param vendingMachine 業者が操作する自販機
	 */
	public Vendor(VendingMachine vendingMachine) {
		itemList.add(new VendingMachineKey());
		setVendingMachine(vendingMachine);
		prepareCapital();
	}

	/**
	 * 以下の資金を準備します。<br>
	 * <ul>
	 * <li>10000円札 - 20枚
	 * <li>5000円札 - 20枚
	 * <li>1000円札 - 20枚
	 * <li>500円硬貨 - 20枚
	 * <li>100円硬貨 - 20枚
	 * <li>50円硬貨 - 20枚
	 * <li>10円硬貨 - 20枚
	 *</ul>
	 */
	private void prepareCapital() {
		for (int i = 0; i < PREPARE; i++) {
			capital.offer(new Bill10000());
			capital.offer(new Bill5000());
			capital.offer(new Bill1000());
			capital.offer(new Coin500());
			capital.offer(new Coin100());
			capital.offer(new Coin50());
			capital.offer(new Coin10());
		}
	}

	/**
	 * 業者に自販機を設定します。
	 * @param vendingMachine 自販機
	 */
	public void setVendingMachine(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	/**
	 * 飲み物の生産工場をリストに追加します。すでにリストにその工場がある場合は追加しません。
	 * @param drinkFactory 飲みもの生産工場
	 */
	public void addDrinkFactory(AbstractDrinkFactory drinkFactory) {
		if (!factoryList.contains(drinkFactory)) {
			factoryList.add(drinkFactory);
		}
	}

	/**
	 * この業者が知っている生産工場リストを返します。
	 * @return 生産工場リスト
	 */
	public String getDrinkFactoryList() {
		String list = "生産工場リスト";
		int i = 0;
		if (!factoryList.isEmpty()) {
			list += "\n";
		}
		for (AbstractDrinkFactory factory : factoryList) {
			i++;
			list += " - ";
			list += factory.toString();
			if (i != factoryList.size()) {
				list += "\n";
			}
		}
		return list;
	}

	/**
	 * 生産工場リストから1ケースずつ飲み物を発注します。
	 */
	public void receiveOrder() {
		for (AbstractDrinkFactory factory : factoryList) {
			itemList.add(factory.pack());
		}
	}

	/**
	 * 金庫にあるお金を調整します。
	 */
	public void adjustReturn() {
		vendingMachine.unlock(findKey());
		Queue<Money> moniesInCashBox = vendingMachine.getMonies();
		if (moniesInCashBox.isEmpty()) {
			vendingMachine.addMonies(capital);
			return;
		}

		Queue<Money> benefit = new LinkedList<>();
		Map<String, Integer> moneyCountMap = countMoney(moniesInCashBox);
		for (String moneyKind : moneyCountMap.keySet()) {
			int count = moneyCountMap.get(moneyKind);
			if (20 < count) {
				int overCount = count - PREPARE;
				for (Money money : moniesInCashBox) {
					if (moneyKind.equals(money.toString())) {
						benefit.offer(money);
						moniesInCashBox.remove(money);
						overCount--;
						if (overCount == 0) {
							break;
						}
					}
				}
			}
		}
		vendingMachine.addMonies(moniesInCashBox);
		this.benefit.addAll(benefit);
		vendingMachine.lock(findKey());
	}

	/**
	 * 持ち物から自販機の鍵を探し、その鍵を返します。
	 * @return 自販機の鍵
	 */
	private VendingMachineKey findKey() {
		VendingMachineKey key = null;
		for (Item item : itemList) {
			if (item.getName() == "自販機の鍵") {
				key = (VendingMachineKey) item;
				break;
			}
		}
		return key;
	}

	/**
	 * お金を数え上げます。何円札、何円硬貨が何枚あるかを関連付けたマップを返します。
	 * @param monies 数え上げるお金
	 * @return 数え上げたお金のマップ
	 */
	private Map<String, Integer> countMoney(Queue<Money> monies) {
		Map<String, Integer> moneyCount = new HashMap<>();
		for (Money money : monies) {
			if (moneyCount.containsKey(money.toString())) {
				moneyCount.put(money.toString(), moneyCount.get(money.toString()) + 1);
			} else {
				moneyCount.put(money.toString(), 1);
			}
		}
		return moneyCount;
	}

	/**
	 * 自販機に飲み物を補充します。
	 */
	public void fillUpDrink() {
		vendingMachine.unlock(findKey());
		Queue<DrinkBox> drinkboxes = new LinkedList<>();
		for (Item item : itemList) {
			if (item.getName().contains("ダンボール箱")) {
				drinkboxes.offer((DrinkBox) item);
			}
		}
		while (!drinkboxes.isEmpty()) {
			DrinkBox drinkbox = drinkboxes.poll();
			while (!drinkbox.isEmpty()) {
				{
					Drink drink = drinkbox.getDrink();
					if (vendingMachine.canAddDrink(drink)) {
						vendingMachine.addDrink(drink);
					} else {
						break;
					}
				}
			}
		}
		vendingMachine.lock(findKey());
	}

	/**
	 * 金庫内のお金の情報を文字列にして返します。
	 * @return 金庫内のお金の情報
	 */
	public String showCashBox() {
		String cashBox = "金庫内のお金\n";
		vendingMachine.unlock(this.findKey());
		cashBox += vendingMachine.getCashBoxInfo();
		vendingMachine.lock(this.findKey());
		return cashBox;
	}
}
