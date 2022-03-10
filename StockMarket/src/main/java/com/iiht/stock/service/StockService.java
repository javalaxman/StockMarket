package com.iiht.stock.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.iiht.stock.model.StockDetails;

public interface StockService {
	public String addStock(StockDetails stock) throws ParseException;
	public StockDetails lastestStockValue(int compnyCode);
	public String deleteStock(int compnyCode);
	public List<StockDetails> getStockByDate(String startdate,String enddate) throws ParseException;
	List<StockDetails> getStockByDate(int compnyCode, String startDate, String endDate) throws ParseException;

}
