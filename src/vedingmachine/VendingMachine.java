package vedingmachine;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import item.VendingMachineKey;
import item.drink.Drink;
import money.Money;
import money.bill.Bill1000;
import money.bill.Bill10000;
import money.bill.Bill2000;
import money.bill.Bill5000;
import money.coin.Coin10;
import money.coin.Coin100;
import money.coin.Coin50;
import money.coin.Coin500;

/**
 * 自販機。カレーも販売する予定
 * @author keisuke
 *
 */
public class VendingMachine {

	private Set<String> classNameList = new HashSet<>();
	private Map<String, Integer> namePriceMap = new LinkedHashMap<>();
	private CashBox cashBox = new CashBox();
	private boolean isLock = true;
	private Queue<Money> insertedMonies = new LinkedList<>();
	private ReturnSlot returnSlot = new ReturnSlot();
	private DispensingSlot dispensingSlot = new DispensingSlot();
	private Storage storage = new Storage();

	/**
	 * 自販機を構築します。
	 */
	public VendingMachine() {
		classNameList.add(Bill10000.class.getSimpleName());
		classNameList.add(Bill5000.class.getSimpleName());
		classNameList.add(Bill2000.class.getSimpleName());
		classNameList.add(Bill1000.class.getSimpleName());
		classNameList.add(Coin500.class.getSimpleName());
		classNameList.add(Coin100.class.getSimpleName());
		classNameList.add(Coin50.class.getSimpleName());
		classNameList.add(Coin10.class.getSimpleName());
	}

	/**
	 * 商品一覧を返します。商品一覧の文字列を生成し、返します。
	 * @return 商品一覧の文字列
	 */
	public String showProdacts() {
		String prodacts = "商品一覧";
		int i = 0;
		if (!namePriceMap.isEmpty()) {
			prodacts += "\n";
		}
		for (String drinkName : namePriceMap.keySet()) {
			i++;
			prodacts += " - " + drinkName + " : ";
			prodacts += namePriceMap.get(drinkName) + "円";
			if (i != namePriceMap.keySet().size()) {
				prodacts += "\n";
			}
		}
		return prodacts;
	}

	/**
	 * 販売商品の一覧を返します。商品名に価格を関連付けたマップを返します。
	 * @return 商品名と価格を関連付けたマップ
	 */
	public Map<String, Integer> getDrinkNamePriceMap() {
		return namePriceMap;
	}

	/**
	 * 指定された金額を投入します。
	 * @param monies 投入金額
	 */
	public void insertMoney(Queue<Money> monies) {
		while (!monies.isEmpty()) {
			if (classNameList.contains(monies.peek().getClass().getSimpleName())) {
				insertedMonies.offer(monies.poll());
			} else {
				System.out.println(monies.peek() + "が投入されました。この自販機では使用できません。釣り銭口に戻します。");
				returnSlot.addMoney(monies.poll());
			}
		}
	}

	/**
	 * 指定された商品ボタンを押します。押された商品を取り出し口に出します。ただし次の場合は商品を出すことができません。<br>
	 * <br>
	 * <ul>
	 * 	<li>この自販機の商品一覧にない商品を指定した場合</li>
	 * 	<li>指定された商品が売り切れていた場合</li>
	 * 	<li>投入金額が指定された商品の金額に足りていない場合</li>
	 * 	<li>この自販機が釣り切れを起こしている場合</li>
	 * 	<li>取り出し口に商品が8本溜まっている場合</li>
	 * </ul>
	 * <br>
	 *  これらの場合は決済は行われず、商品も出てきません。返金を使用してお金を戻すか、別の商品を指定してください。
	 * @param selectedDrinkName 押されたボタンの商品名
	 */
	public void selectDrink(String selectedDrinkName) {

		if (!namePriceMap.containsKey(selectedDrinkName)) {
			System.out.println(selectedDrinkName + "は取り扱っていません。");
			return;
		}

		Queue<Drink> drinkStock = storage.getDrinks(selectedDrinkName);
		if (drinkStock.isEmpty()) {
			System.out.println(selectedDrinkName + "は売り切れています。");
			return;
		}

		int insertMoneyValue = getInsertedMoneyValue();
		if (insertMoneyValue < drinkStock.peek().getValue()) {
			System.out.println("お金が足りません。");
			return;
		}

		int leftOver = getInsertedMoneyValue() - drinkStock.peek().getValue();
		if (!cashBox.canPay(leftOver)) {
			System.out.println("申し訳ありません。釣り切れです。");
			return;
		}

		if (!dispensingSlot.canOffer()) {
			System.out.println("取り出し口の商品がいっぱいです。");
			return;
		}

		peyment(drinkStock.peek());
		System.out.println(drinkStock.peek().getName() + "を取出口に出しました");
		dispensingSlot.addDrink(drinkStock.poll());
	}

	private void peyment(Drink drink) {
		int leftOver = getInsertedMoneyValue() - drink.getValue();
		while (!insertedMonies.isEmpty()) {
			cashBox.addMoney(insertedMonies.poll());
		}
		Queue<Money> leftOverMonies = cashBox.getMonies(leftOver);
		while (!leftOverMonies.isEmpty()) {
			this.insertedMonies.offer(leftOverMonies.poll());
		}
	}

	/**
	 * 現在の投入金額を返します。
	 * @return 現在の投入金額
	 */
	public int getInsertedMoneyValue() {
		int insertMoneyValue = 0;
		for (Money money : insertedMonies) {
			insertMoneyValue += money.getValue();
		}
		return insertMoneyValue;
	}

	/**
	 * お金を返却します。返却されたお金は取り出し口に出ます。
	 */
	public void returnMoney() {
		while (!insertedMonies.isEmpty()) {
			returnSlot.addMoney(insertedMonies.poll());
		}
	}

	/**
	 * お釣りを返します。<br>
	 * <br>
	 * 釣り銭口内のお金のキューを取得及び削除します。
	 * @return お釣りのキュー
	 */
	public Queue<Money> getReturn() {
		return returnSlot.getMonies();
	}

	/**
	 * 取り出し口の商品を返します。<br>
	 * <br>
	 * 取り出し口内の飲み物のキューを取得及び削除します。
	 * @return 取り出し口の商品のキュー
	 */
	public Queue<Drink> getDrink() {
		return dispensingSlot.getDrinks();
	}

	/**
	 * 指定された商品を追加できるかどうかを返します。以下の場合には、商品が追加できません。<br>
	 * <ul>
	 * 	<li>指定された商品を追加した時に、この自販機で販売する商品が20種類より多くなる場合</li>
	 * 	<li>指定された商品を追加した時に、この自販機のその商品の在庫数が30個より多くなる場合</li>
	 * </ul>
	 * @param drink 追加する商品
	 * @return 指定された商品を追加できるかどうか
	 */
	public boolean canAddDrink(Drink drink) {
		return storage.canAddDrink(drink);
	}

	/**
	 * 鍵が開いていれば商品を追加します。そうでない場合はできません。<br>
	 * <b>鍵が掛かっているかどうかはisLockメソッドで確認するようにしてください。</b>
	 * @param drink 追加する商品
	 */
	public void addDrink(Drink drink) {
		if (isLock) {
			return;
		}
		if (!namePriceMap.containsKey(drink.getName())) {
			namePriceMap.put(drink.getName(), drink.getValue());
		}
		storage.addDrink(drink);
	}

	/**
	 * 自販機の鍵が掛かっているかどうかを示します。
	 * @return 掛かっている場合はtrue、そうでない場合はfalse
	 */
	public boolean isLock() {
		return isLock;
	}

	/**
	 * 鍵でロックを掛ける
	 * @param key 鍵
	 */
	public void lock(VendingMachineKey key) {
		if (key != null) {
			isLock = true;
		}
	}

	/**
	 * 鍵でロックを開ける
	 * @param key 鍵
	 */
	public void unlock(VendingMachineKey key) {
		if (key != null) {
			isLock = false;
		}
	}

	/**
	 * 鍵が開いていれば金庫にお金を追加します。そうでない場合はできません。<br>
	 * <b>鍵が掛かっているかどうかはisLockメソッドで確認するようにしてください。</b>
	 * @param monies 追加するお金のキュー
	 */
	public void addMonies(Queue<Money> monies) {
		if (isLock) {
			return;
		}
		while (!monies.isEmpty()) {
			cashBox.addMoney(monies.poll());
		}
	}

	/**
	 * 鍵が開いていて、金庫にお金があればそのお金すべてを返します。そうでない場合はnullを返します。
	 * @return 金庫内のお金
	 */
	public Queue<Money> getMonies() {
		if (isLock) {
			return null;
		} else {
			return cashBox.getMonies(cashBox.getSum());
		}
	}

	@Override
	public String toString() {
		return "自動販売機\n\t" + cashBox + "\n\t" + returnSlot + "\n\t" + dispensingSlot;
	}

	public String getCashBoxInfo() {
		return cashBox.toString();
	}
}

/**
 * 商品貯蔵室
 * @author keisuke
 *
 */
class Storage {
	private static final int DRINK_MAX = 30;
	private static final int DRINK_KIND = 20;
	private Map<String, Queue<Drink>> nameDrinksMap = new TreeMap<>();

	public boolean canAddDrink(Drink drink) {
		if (!isFull(drink)) {
			if (nameDrinksMap.keySet().size() <= DRINK_KIND) {
				return true;
			} else {
				throw new IllegalStateException("飲み物の種類が上限を超えています。");
			}
		}
		return false;
	}

	Queue<Drink> getDrinks(String selectedDrinkName) {
		return nameDrinksMap.get(selectedDrinkName);
	}

	private boolean isFull(Drink drink) {
		if (nameDrinksMap.containsKey(drink.getName())) {
			if (DRINK_MAX == nameDrinksMap.get(drink.getName()).size()) {
				return true;
			} else if (DRINK_MAX < nameDrinksMap.get(drink.getName()).size()) {
				throw new IllegalStateException("飲み物の数が在庫上限を超えています。");
			}
		}
		return false;
	}

	void addDrink(Drink drink) {
		if (nameDrinksMap.containsKey(drink.getName())) {
			Queue<Drink> drinks = nameDrinksMap.get(drink.getName());
			if (drinks.size() < DRINK_MAX) {
				drinks.offer(drink);
			} else {
				throw new IllegalStateException("飲み物の数が在庫上限を超えています。");
			}
		} else {
			if (nameDrinksMap.keySet().size() < DRINK_KIND) {
				Queue<Drink> drinks = new LinkedList<>();
				drinks.offer(drink);
				nameDrinksMap.put(drink.getName(), drinks);
			} else {
				throw new IllegalStateException("飲み物の種類が上限を超えています。");
			}
		}
	}
}

/**
 * 金庫
 * @author keisuke
 *
 */
class CashBox {

	private String[] moneyKindList = {
			"10000円紙幣",
			"5000円紙幣",
			"2000円紙幣",
			"1000円紙幣",
			"500円硬貨",
			"100円硬貨",
			"50円硬貨",
			"10円硬貨",
	};
	private Map<String, Queue<Money>> moneyKindMoneyQueueMap;

	CashBox() {
		moneyKindMoneyQueueMap = new LinkedHashMap<>();
		for (String className : moneyKindList) {
			moneyKindMoneyQueueMap.put(className, new LinkedList<>());
		}
	}

	@Override
	public String toString() {
		String str = "";
		for (String className : moneyKindMoneyQueueMap.keySet()) {
			str += " - " + className + " : ";
			str += moneyKindMoneyQueueMap.get(className).size() + "枚\n";
		}
		return str;
	}

	int getSum() {
		int sum = 0;
		for (String className : moneyKindMoneyQueueMap.keySet()) {
			for (Money money : moneyKindMoneyQueueMap.get(className)) {
				sum += money.getValue();
			}
		}
		return sum;
	}

	void addMoney(Money money) {
		String moneyKind = money.toString();
		Queue<Money> moneyList = moneyKindMoneyQueueMap.get(moneyKind);
		moneyList.offer(money);
	}

	boolean canPay(int price) {
		if (price < 0) {
			return false;
		}
		for (String className : moneyKindList) {
			for (Money money : moneyKindMoneyQueueMap.get(className)) {
				if (money.getValue() <= price) {
					price -= money.getValue();
				} else {
					break;
				}
			}
		}
		return price == 0;
	}

	Queue<Money> getMonies(int price) {
		Queue<Money> monies = new LinkedList<>();
		if (!canPay(price)) {
			return null;
		}
		for (String className : moneyKindList) {
			Queue<Money> moneyList = moneyKindMoneyQueueMap.get(className);
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

}

/**
 * 飲み物取り出し口
 * @author keisuke
 *
 */
class DispensingSlot {

	private static final int MAX_DRINK_NUM = 8;
	private Queue<Drink> drinks = new LinkedList<>();

	void addDrink(Drink drink) {
		if (canOffer()) {
			drinks.offer(drink);
		}
	}

	boolean canOffer() {
		return drinks.size() < MAX_DRINK_NUM;
	}

	Queue<Drink> getDrinks() {
		Queue<Drink> drinks = new LinkedList<>();
		drinks.addAll(this.drinks);
		this.drinks.clear();
		return drinks;
	}

	@Override
	public String toString() {
		return "自販機取り出し口の中身は" + drinks + "の" + drinks.size() + "本です。";
	}

}

/**
 * 釣り銭口
 * @author keisuke
 *
 */
class ReturnSlot {

	private Queue<Money> monies = new LinkedList<>();

	void addMoney(Money money) {
		monies.offer(money);
	}

	Queue<Money> getMonies() {
		Queue<Money> monies = new LinkedList<>();
		monies.addAll(this.monies);
		this.monies.clear();
		return monies;
	}

	int getSum() {
		int sum = 0;
		for (Money money : monies) {
			sum += money.getValue();
		}
		return sum;
	}

	@Override
	public String toString() {
		return "釣り銭口のお金は" + getSum() + "です。";
	}
}
