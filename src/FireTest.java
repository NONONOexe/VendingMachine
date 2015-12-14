

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;

import money.Gift;
import money.Money;
import money.Wallet;
import money.bill.Bill1000;
import money.bill.Bill10000;
import money.bill.Bill2000;
import money.bill.Bill5000;
import money.coin.Coin1;
import money.coin.Coin10;
import money.coin.Coin100;
import money.coin.Coin5;
import money.coin.Coin500;

/**
 * お金と財布の実行テスト
 * @author keisuke
 *
 */
public class FireTest {
	public static void main(String[] args) {
		Wallet wallet = new Wallet();
		for (int i = 0; i < 2; i++) {
			wallet.addMoney(new Bill10000());
			wallet.addMoney(new Bill5000());
			wallet.addMoney(new Bill2000());
			wallet.addMoney(new Bill1000());
			wallet.addMoney(new Coin500());
			wallet.addMoney(new Coin100());
			wallet.addMoney(new Coin10());
			wallet.addMoney(new Coin5());
			wallet.addMoney(new Coin1());
		}
		wallet.addGift(new Gift(1000));
		wallet.addGift(new Gift(10000));
		System.out.println(wallet);
		System.out.print("いくら取り出しますか？ > ");
		int value = getInputNumber();
		Queue<Money> safeList = wallet.getMonies(value);
		if (safeList != null) {
			safeList.forEach(money -> System.out.println(money));
			System.out.println("以上を取り出しました。");
		} else {
			System.out.println("その金額をちょうど取り出すことはでき。");
		}
		System.out.println("燃やします。");
		wallet.fire();
		System.out.println("燃やしました。紙幣は全部燃えました");
		System.out.println(wallet);
		Queue<Money> moneyList = wallet.getMonies(wallet.getSum());
		moneyList.forEach(money -> {
			if (money.getValue() != 0) {
				System.out.println(money);
			}
		});;
	}

	/**
	 * 入力した数字を返します。
	 * @return 入力した数字
	 * @author keisuke
	 */
	private static int getInputNumber() {
		int num = 0;
		String str = getInputString();
		try {
			num = Integer.valueOf(str);
		} catch (NumberFormatException ife) {
			System.err.println("数字を入力してください。");
		}
		return num;
	}

	/**
	 * 入力した文字列を返します。
	 * @return 入力した文字列
	 * @author keisuke
	 */
	private static String getInputString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
