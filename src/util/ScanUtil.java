package util;

import java.util.Scanner;

public class ScanUtil   {
	// 자주 사용하는 것들을 여기서 메서드로 분리해서 사용
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
	
	
	
	public static int menu() {
		return nextInt("메뉴 >> ");
	}
	
	

	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}
	
	// 안내문구가 필요없는 경우, 아래 메서드를 public으로 바꿔서 사용하면 됨
	private static String nextLine() {
		return sc.nextLine();
	}
	
	// 안내문구를 적지 않으면 실행이 되지 않게 구성됐으며, 안내문구를 적지 않으면 빈칸이 뜨게 됨
	// 안내문구와 입력을 한번에 하기 위한 메서드이며, 코드를 간결하게 만들 수 있게 함
	public static int nextInt(String print) {
		System.out.print(print);
		return nextInt();
	}
	
	private static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
