import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import util.JDBCUtil;

public class Test {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Test test = new Test();
//		test.method1();
//		test.method2();
//		test.method3();
		test.method4();
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	
	public void method1() {
		// selectOne(String sql) - DB에서 데이터를 한줄만 갖고오는 것
		// selectList(String sql) - DB에서 데이터를 여러줄 갖고오는 것
		// update(String sql) - 데이터를 변경하는 기능을 갖고있는 모든 것
		
		// selectList 사용 => MEMBER의 데이터를 전부 가져옴
		// value 타입이 Object인 이유 => 어떤 타입의 데이터가 올지 모르기 때문
		// List<Map<String = 컬럼이름, Object = 컬럼의 데이터 값>>
		String sql = "SELECT * FROM MEMBER";
		List<Map<String, Object>> list = jdbc.selectList(sql);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}

	}	

	
	public void method2() {
		String sql = "SELECT * \r\n" + 
					 "FROM MEMBER\r\n" +
					 "WHERE MEM_ID = 'a001'\r\n" + 
					 "AND MEM_PASS = 'asdfasdf'";
		
		Map<String, Object> map = jdbc.selectOne(sql);
		System.out.println(map);
	}
	
	
	// 동적으로 데이터를 넣어주기 위해서는 ?를 사용함
	public void method3() {
		String sql = "SELECT * \r\n" + 
					 "FROM MEMBER\r\n" +
					 "WHERE MEM_ID = ? \r\n" + 
					 "AND MEM_PASS = ? ";
		
		System.out.println("아이디");
		String id = sc.next();
		System.out.println("패스워드");
		String pw = sc.next();
		
		List<Object> param = new ArrayList();
		// 입력받는 순서는 물음표의 순서대로
		param.add(id);
		param.add(pw);
		
		Map<String, Object> map = jdbc.selectOne(sql, param);
		System.out.println(map);
	}
	
	
	// 데이터베이스의 데이터를 UPDATE하는 방법
	// 실행 후, 데이터베이스를 커밋이 되어 있어야 변경된 데이터를 확인 가능함
	// 데이터를 삭제하는 것은 신중해야 하는 일이고, 
	// 외부참조된 데이터까지 모두 삭제하는 것은 어려운 일이기 때문에
	// 일반적으로는 삭제가 아닌 비활성화시키는 방법을 사용함
	// update와 delete는 신중하게 사용해야 함
	public void method4() {
		String sql = "UPDATE MEMBER \r\n" + 
					 "SET MEM_PASS = '1234'\r\n" +
					 "WHERE MEM_ID = 'a001'";
		// update => update, delete, insert 모두 update로 처리하면 됨
		// update 주석에 뜨는 int값이 0 또는 1인지에 따라 성공여부를 파악할 수 있음
		jdbc.update(sql);
		
	}
	
	// DB에서 컬럼을 가져올 때 AS를 사용하여 해당 AS명이 자바의 키값이 됨 
	//while(rs.next()) {
	//	row = new HashMap<>();
	//	for(int i = 1; i <= columnCount; i++) {
	//		String key = rsmd.getColumnLabel(i);
	//		// getColumnName vs getColumnLabel
	//		// getColumnName : 원본 컬럼명을 가져옴
	//		// getColumnLabel : as로 선언된 별명을 가져옴, 없으면 원본 컬럼명
	//		Object value = rs.getObject(i);
	//		row.put(key,value);
	//	}
	//}
	
}

