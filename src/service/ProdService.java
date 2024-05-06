package service;

import java.util.List;
import java.util.Map;

import dao.ProdDao;

public class ProdService {
	private static ProdService instance;

	private ProdService() {

	}

	public static ProdService getInstance() {
		if (instance == null) {
			instance = new ProdService();
		}
		return instance;
	}
	
	
	ProdDao prodDao = ProdDao.getInstance();
	
	public List<Map<String, Object>> list() {
		return prodDao.list();
	}

	public void insert(List<Object> param) {
		prodDao.insert(param);
	}

	public int delete(List<Object> param) {
		return prodDao.delete(param);
	}

	public void update(List<Object> param) {
		prodDao.update(param);
		
	}
	
	
}
