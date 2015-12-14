import factory.drink.curry.PorkCurryFactory;
import factory.drink.soda.CCLemonFactory;
import factory.drink.soda.CocaCoraFactory;
import factory.drink.tea.AfternoonTeaFactory;
import vedingmachine.VendingMachine;
import vendor.Vendor;

public class VendorTest {

	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine();

		// 自販機業者
		Vendor vendor = new Vendor(vendingMachine);

		// 工場の情報を伝える
		vendor.addDrinkFactory(new PorkCurryFactory());
		vendor.addDrinkFactory(new CCLemonFactory());
		vendor.addDrinkFactory(new CocaCoraFactory());
		vendor.addDrinkFactory(new AfternoonTeaFactory());

		// 生産工場リストの表示
		System.out.println(vendor.getDrinkFactoryList() + "\n");

		// 発注させる
		vendor.receiveOrder();

		// 自販機に商品を補充
		vendor.fillUpDrink();

		// 商品一覧の表示
		System.out.println(vendingMachine.showProdacts() + "\n");

		// お釣りを調整
		vendor.adjustReturn();

		// 金庫内のお金の表示
		System.out.println(vendor.showCashBox());
	}

}
