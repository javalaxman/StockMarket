package com.iiht.stock.serviceimpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.stock.model.StockDetails;
import com.iiht.stock.repos.StockRepository;
import com.iiht.stock.service.StockService;
@Service
public  class StockServiceImpl implements StockService{
	@Autowired
	public StockRepository stockRepo;
	
	@Override
	public String addStock(StockDetails details) throws ParseException {
		String msg = "";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String convDate =(formatter.format(date));
		System.out.println(convDate);
		Date date1=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(convDate);  
		java.sql.Timestamp sqlDate =  new java.sql.Timestamp(date1.getTime());
		System.out.println(formatter.format(date));
		// TODO Auto-generated method stub
		details.setStockDate(sqlDate);
		StockDetails stock = stockRepo.save(details);
		if(stock!=null) {
			msg="Stock Details Saved Successfully";
		}
		else {
			msg = "Unable to save Stock Details";
		}
		return msg;
		
	}
	@Override
	public StockDetails lastestStockValue(int compnyCode) {
		// TODO Auto-generated method stub
		StockDetails stock = stockRepo.findByStockValue(compnyCode);
		return stock;
	}
	@Transactional
	@Override
	public String deleteStock(int compnyCode) {
		// TODO Auto-generated method stub
		String msg="";
		try {
			stockRepo.deleteByCompnyCode(compnyCode);
			msg="Stock details deleted successfully";
		}catch(Exception e) {
			System.out.println(e);
			msg="Unable to delete Stock Details";
		}
		return msg;
	}
	public static String covDate(String date) throws ParseException {

		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		String inputText = "2012-11-17T00:00:00.000-05:00";
		java.util.Date cdate = inputFormat.parse(date);
		String outputText = outputFormat.format(cdate);
		return outputText;
	}
	
	@Override
	public List<StockDetails> getStockByDate(int compnyCode,String startDate, String endDate) throws ParseException {
		// TODO Auto-generated method stub
		String cSdate = covDate(startDate);
		String cEdate = covDate(endDate);
		 Timestamp timestamp1 = Timestamp.valueOf(cSdate);
		 Timestamp timestamp2 = Timestamp.valueOf(cEdate);
		List<StockDetails> stocks = stockRepo.findByStockDate(compnyCode,timestamp1, timestamp2);
		System.out.println("stocks by date->"+ stocks);
		return stocks;

	}
	@Override
	public List<StockDetails> getStockByDate(String startdate, String enddate) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
