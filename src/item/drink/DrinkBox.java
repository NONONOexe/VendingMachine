package item.drink;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import item.Item;

/**
 * 飲み物を入れる箱。ダンボール1ケース
 * @author keisuke
 *
 */
public class DrinkBox extends Item{

	private static final int DRINK_MAX = 24;
	Queue<Drink> drinks = new LinkedList<>();

	/**
	 * この箱が満タンかどうかを返します。
	 * @return この箱が満タンの場合true、そうでない場合はfalse
	 */
	public boolean isFull() {
		return 24 <= drinks.size();
	}

	/**
	 *指定した飲み物をこの箱に追加します。<br>
	 *<b>必ずisFullメソッドで満タンかどうかを確認し、満タンでないときに使うようにしてください。</b><br>
	 *満タンの状態で追加しようとした場合はIllegalStateExceptionが呼ばれます。<br>
	 * @param drink 追加する飲み物
	 */
	public void addDrink(Drink drink) {
		if (drinks.size() < DRINK_MAX) {
			drinks.offer(drink);
		} else {
			throw new IllegalStateException("この箱は満タンです。これ以上飲み物を追加することはできません。");
		}
	}

	/**
	 * 飲み物を取り出す。先頭のDrinkを取得および削除します。
	 * @return 飲み物
	 */
	public Drink getDrink(){
		return drinks.poll();
	}

	@Override
	public String toString() {
		if (drinks.isEmpty()) {
			return "空のダンボール箱";
		}

		Map<String, Integer> nameCountMap = mapNameCountMap();
		String label = "ダンボール箱[";
		int i = 1;
		for(String drinkName : nameCountMap.keySet()){
			int drinkCount = nameCountMap.get(drinkName);
			label += drinkName + " x" + drinkCount;
			if(nameCountMap.keySet().size() == i){
				label+="]";
			}else{
				label+=",";
			}
			i++;
		}
		return label;
	}

	private Map<String, Integer> mapNameCountMap() {
		String drinkName = "";
		Map<String, Integer> nameCountMap = new TreeMap<>();
		for (Drink drink : drinks) {
			drinkName = drink.getName();
			if (!nameCountMap.containsKey(drinkName)) {
				nameCountMap.put(drinkName, 1);
			} else {
				nameCountMap.put(drinkName, nameCountMap.get(drinkName) + 1);
			}
		}
		return nameCountMap;
	}

	@Override
	public String getName() {
		return toString();
	}

	/**
	 * この箱が空かどうかを返します。
	 * @return この箱が空の場合はtrue、そうでない場合はfalse
	 */
	public boolean isEmpty() {
		return drinks.isEmpty();
	}
}
