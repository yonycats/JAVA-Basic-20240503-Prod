package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.MemberService;
import service.ProdService;
import util.ScanUtil;
import util.View;
import view.Print;
 
public class MainController extends Print {
	
	static public Map<String, Object> sessionStorage = new HashMap<>();
	// 개발 완료됐을 때, 사용하지 않는 개발용 메시지들을 한꺼번에 비활성화하기 위한 용도
	boolean debug = true;
	
	MemberService memberService = MemberService.getInstance();
	ProdService prodService = ProdService.getInstance();

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMIN:
				view = admin();
				break;
			case MEMBER:
				view = insert();
				break;
			case LOGIN:
				view = login();
				break;
			case ADMIN_PROD_INSERT:
				view = insert();
				break;
			case ADMIN_PROD_DELETE:
				view = delete();
				break;
			case ADMIN_PROD_UPDATE:
				view = update();
				break;
			case ADMIN_PROD_LIST:
				view = adminList();
				break;
			case ADMIN_PROD_DETAIL:
				view = login();
				break;
			default:
				break;
			}
		}
	}
	
	
	private View update() {
		List<Map<String, Object>> list = prodService.list();

		for (Map<String, Object> map : list) {
			System.out.println(map);
		}

			int prodNo = ScanUtil.nextInt("상품 번호 : ");
			String name = ScanUtil.nextLine("상품명 : ");
			String type = ScanUtil.nextLine("타입 : ");
			int price = ScanUtil.nextInt("가격 : ");

			List<Object> param = new ArrayList<Object>();

			param.add(name);
			param.add(type);
			param.add(price);
			param.add(prodNo);
			
			prodService.update(param);

			return View.ADMIN_PROD_LIST;
	}
	
	
	private View delete() {
		List<Map<String, Object>> list = prodService.list();
		
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
		int prodNo = ScanUtil.nextInt("상품 번호 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(prodNo);
		
		int result = prodService.delete(param);
		
		if(result == 0) {
			System.out.println("삭제에 실패했습니다.");
			return View.ADMIN_PROD_DELETE;
		}
		
		return View.ADMIN_PROD_LIST;
		}
	
	
	private View insert() {
		List<Object> param = new ArrayList<Object>();
		
		String name = ScanUtil.nextLine("상품명 : ");
		String type = ScanUtil.nextLine("타입 : ");
		int price = ScanUtil.nextInt("가격 : ");
		
		param.add(name);
		param.add(type);
		param.add(price);
		
		prodService.insert(param);
		
		return View.ADMIN_PROD_LIST;
	}
	
	
	private View adminList() {
		List<Map<String, Object>> list = prodService.list();
		
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		System.out.println("1. 다음 페이지");
		System.out.println("2. 이전 페이지");
		System.out.println("3. 홈(관리자)");
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_PROD_LIST;
		else if (sel==2) return View.ADMIN_PROD_LIST;
		else if (sel==3) return View.ADMIN;
		else return View.ADMIN_PROD_LIST;
	}

	private View login() {
		
		String id = ScanUtil.nextLine("ID >>");
		String pw = ScanUtil.nextLine("PASS >>");
		
		int role = (int) sessionStorage.get("role");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		param.add(role);
		
		boolean loginChk = memberService.login(param, role);
		
		// sessionStorage 안에 어떤 key들과 value가 들어가있는지 확인하는 용 
		/*
		 * for (String key : sessionStorage.keySet()) { System.out.println("출력!! Key: "
		 * + key);
		 * 
		 * } for (String key : sessionStorage.keySet()) { Object value =
		 * sessionStorage.get(key); System.out.println("Key: " + key + ", Value: " +
		 * value); }
		 */
		
		
		if(!loginChk) {
			System.out.println("로그인 실패");
			return View.LOGIN;
		}
		if(role == 1) return View.MEMBER;
		else return View.ADMIN;
	}
	
	
	private View admin() {
		
		if(!sessionStorage.containsKey("admin")) {
			sessionStorage.put("role", 2);
			return View.LOGIN;
		}
		
		
		if (debug) System.out.println("=========관리자=========");
		
		System.out.println("1. 상품 추가");
		System.out.println("2. 상품 삭제");
		System.out.println("3. 상품 변경");
		System.out.println("4. 상품 전체 출력");
		System.out.println("5. 로그아웃");
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_PROD_INSERT;
		else if (sel==2) return View.ADMIN_PROD_DELETE;
		else if (sel==3) return View.ADMIN_PROD_UPDATE;
		else if (sel==4) return View.ADMIN_PROD_LIST;
		else if (sel==5) {
			sessionStorage.remove("admin");
			return View.HOME;
		}
		else return View.ADMIN;
	}
	
	
	private View home() {
		if (debug) System.out.println("==========홈==========");
		
		System.out.println("1. 관리자");
		System.out.println("2. 일반 회원");
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN;
		else if (sel==2) return View.PROD_NORMALMEM;
		return View.HOME;
	}
	
	
}