package com.iiht.stock.controller;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.stock.model.StockDetails;
import com.iiht.stock.model.Successresponse;
import com.iiht.stock.service.StockService;

//import io.swagger.annotations.Api;

//import io.swagger.annotations.Api;
@RestController
@RequestMapping
//@Api
@CrossOrigin("http://localhost:4200")
public class StockController {
	//select *from stock_details where compny_code=6 order by stock_date DESC limit 1 
	@Autowired
	public StockService service;
	@GetMapping("/msg")
	public String welcome() {
		return "welcome to stock";
	}
	@PostMapping("/add")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Successresponse> addStock(@RequestBody StockDetails details) throws ParseException{
		String msg = service.addStock(details);
		
		return new ResponseEntity<>(new Successresponse(msg,200),HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{compnyCode}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<StockDetails> getStock(@PathVariable int compnyCode) throws ParseException{
		StockDetails stock = service.lastestStockValue(compnyCode);
		
		return new ResponseEntity<>(stock,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{compnyCode}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Successresponse> deleteStock(@PathVariable int compnyCode) throws ParseException{
		String stock = service.deleteStock(compnyCode);
		
		return new ResponseEntity<>(new Successresponse(stock,200),HttpStatus.OK);
		
	}
	@GetMapping("/get/{compnycode}/{startdate}/{enddate}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<List<StockDetails>> getStockByDate(@PathVariable int compnycode,@PathVariable String startdate,@PathVariable String enddate) throws ParseException {
		List<StockDetails> stocks = service.getStockByDate(compnycode,startdate, enddate);
		return new ResponseEntity<>(stocks,HttpStatus.OK);
		
	}

}
