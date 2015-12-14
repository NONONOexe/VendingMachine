

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VendingMachineTest {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		String myName = "";
		Player player;

		LABEL1: while (true) {
			System.out.print("あなたの名前は？\n > ");
			myName = br.readLine();

			while (true) {
				System.out.print(myName + "で間違いありませんか? y/n\n > ");
				input = br.readLine();
				if (input.equals("y")) {
					player = new Player(myName);
					break LABEL1;
				} else if (input.equals("n")) {
					System.out.println("もう一度名前を入力してください。");
					break;
				} else {
					System.out.println("y(yes)またはn(no)で入力してください。");
				}
			}

		}

		while (true) {

			System.out.print("何をしますか？ 0.何もしない 1.自販機に行く 2.持ち物を見る 3.所持金を見る 4.名乗る 5.やめる\n > ");
			input = br.readLine();
			if (input.equals("0")) {
				continue;
			} else if (input.equals("1")) {

				while (true) {
					System.out.print("何をしますか？ 0.何もしない 1.飲み物を買う 2.帰る 3.自販機を眺める\n > ");
					input = br.readLine();
					if (input.equals("0")) {
						continue;
					} else if (input.equals("1")) {
						player.buyDrink();
					} else if (input.equals("2")) {
						break;
					} else if (input.equals("3")) {
						System.out.println(" ┌──────────────────────┐");
						System.out.println(" |┌────────────────────┐|");
						System.out.println(" ||[][][][][][][][][][]||");
						System.out.println(" ||====================||");
						System.out.println(" ||[][][][][][][][][][]||");
						System.out.println(" ||====================||");
						System.out.println(" ||                 [=]||");
						System.out.println(" ||___[==========]_____||");
						System.out.println(" |______________________|");
					} else {
						System.out.println("0から3で選択してください。");
					}
				}
			} else if (input.equals("2")) {
				player.printAllItem();
			} else if (input.equals("3")) {
				player.printHavingMonies();
			} else if (input.equals("4")) {
				System.out.println(player);
			} else if (input.equals("5")) {
				System.out.println("終了します。");
				br.close();
				break;
			} else {
				System.out.println("0から5で選択してください。");
			}

		}

	}
}
