

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import factory.drink.curry.BeefCurryFactory;
import factory.drink.curry.CheeseCurryFactory;
import factory.drink.curry.PorkCurryFactory;
import factory.drink.soda.CCLemonFactory;
import factory.drink.soda.CocaCoraFactory;
import factory.drink.soda.FantaFactory;
import factory.drink.tea.AfternoonTeaFactory;
import factory.drink.tea.AyatakaFactory;
import factory.drink.tea.IemonFactory;
import factory.drink.water.CrystalGeyserFactory;
import factory.drink.water.IrohasFactory;
import item.Item;
import item.drink.Drink;
import money.Money;
import money.Wallet;
import money.coin.Coin1;
import money.coin.Coin10;
import money.coin.Coin100;
import money.coin.Coin5;
import money.coin.Coin500;
import vedingmachine.VendingMachine;
import vendor.Vendor;

public class Player {

	private String name;
	private List<Item> itemList = new ArrayList<>();
	private Wallet wallet = new Wallet();
	private VendingMachine vendingMachine = new VendingMachine();

	public Player(String name) {
		this.name = name;
		for (int i = 0; i < 10; i++) {
			wallet.addMoney(new Coin500());
			wallet.addMoney(new Coin100());
			wallet.addMoney(new Coin10());
			wallet.addMoney(new Coin5());
			wallet.addMoney(new Coin1());
		}
	}

	public void buyDrink() throws IOException {

		Vendor vendor = new Vendor(vendingMachine);
		vendor.addDrinkFactory(new CrystalGeyserFactory());
		vendor.addDrinkFactory(new IrohasFactory());
		vendor.addDrinkFactory(new AfternoonTeaFactory());
		vendor.addDrinkFactory(new AyatakaFactory());
		vendor.addDrinkFactory(new IemonFactory());
		vendor.addDrinkFactory(new CCLemonFactory());
		vendor.addDrinkFactory(new FantaFactory());
		vendor.addDrinkFactory(new CocaCoraFactory());
		vendor.addDrinkFactory(new BeefCurryFactory());
		vendor.addDrinkFactory(new PorkCurryFactory());
		vendor.addDrinkFactory(new CheeseCurryFactory());
		vendor.receiveOrder();
		vendor.fillUpDrink();
		vendor.adjustReturn();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputNum = 0;

		System.out.println("商品一覧");
		for (String drinkName : vendingMachine.getDrinkNamePriceMap().keySet()) {
			System.out.print(drinkName);
			System.out.println(" ── " + vendingMachine.getDrinkNamePriceMap().get(drinkName) + "円");
		}
		while (true) {
			System.out.print("どうしますか?\n"
					+ "0.何もしない\n"
					+ "1.お金を入れる\n"
					+ "2.飲み物のボタンを押す\n"
					+ "3.お釣り返却ボタンを押す\n"
					+ "4.釣り銭口から釣り銭を取る\n"
					+ "5.飲み物を取り出し口から取り出す\n"
					+ "6.もう買わない\n > ");
			try {
				inputNum = Integer.valueOf(br.readLine());
				if (inputNum == 0) {
					continue;
				} else if (inputNum == 1) {
					insertMoney(br);
				} else if (inputNum == 2) {
					pushDrinkButton(br);
				} else if (inputNum == 3) {
					vendingMachine.returnMoney();
					System.out.println("返却ボタンを押しました。");
				} else if (inputNum == 4) {
					Queue<Money> returnMonies = vendingMachine.getReturn();
					if (!returnMonies.isEmpty()) {
						System.out.println(returnMonies + "を財布にしまいます。");
					} else {
						System.out.println("釣り銭には何もありません。");
					}
					wallet.addMoney(returnMonies);
				} else if (inputNum == 5) {
					Queue<Drink> drinks = vendingMachine.getDrink();
					if (!drinks.isEmpty()) {
						System.out.println(drinks + "を手に入れました。");
						itemList.addAll(drinks);
					} else {
						System.out.println("取り出し口には何もありません。");
					}
				} else if (inputNum == 6) {
					System.out.println("自販機を離れます。");
					return;
				}
			} catch (NumberFormatException ife) {
				System.err.println("数字で入力してください。");
			}
		}
	}

	private void pushDrinkButton(BufferedReader br) throws IOException {
		int inputNum;
		while (true) {
			try {
				int i = 0;
				for (String drinkName : vendingMachine.getDrinkNamePriceMap().keySet()) {
					System.out.print(i + ". " + drinkName);
					System.out.println(" ── " + vendingMachine.getDrinkNamePriceMap().get(drinkName) + "円");
					i++;
				}
				System.out.print("どのボタンを押しますか？\n > ");
				inputNum = Integer.valueOf(br.readLine());
				if (inputNum < vendingMachine.getDrinkNamePriceMap().size()) {
					List<String> drinkList = new ArrayList<>();
					drinkList.addAll(vendingMachine.getDrinkNamePriceMap().keySet());
					vendingMachine.selectDrink(drinkList.get(inputNum));
				} else {
					System.out.println("0から" + (vendingMachine.getDrinkNamePriceMap().size() - 1) + "で入力してください。");
				}
				break;
			} catch (NumberFormatException ife) {
				System.err.println("数字で入力してください。");
			}
		}
	}

	private void insertMoney(BufferedReader br) throws IOException {
		int inputNum;
		while (true) {
			try {
				System.out.print("いくら投入しますか？\n > ");
				inputNum = Integer.valueOf(br.readLine());
				System.out.println(inputNum + "円投入します");
				vendingMachine.insertMoney(wallet.getMonies(inputNum));
				break;
			} catch (NumberFormatException ife) {
				System.err.println("数字で入力してください。");
			}
		}
	}

	@Override
	public String toString() {
		return "私の名前は" + name + "!";
	}

	public void printAllItem() {
		for (Item item : itemList) {
			System.out.println(item.getName());
		}
	}

	public void printHavingMonies() {
		System.out.println(wallet);
		wallet.printAllMoney();
	}
}
