package com.iiht.stock.repos;



import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.stock.model.StockDetails;


public interface StockRepository extends JpaRepository<StockDetails,Integer>{
	@Query("FROM StockDetails WHERE stockDate= (SELECT MAX(stockDate) FROM StockDetails where compnyCode=:compnyCode) and compnyCode = :compnyCode ")
	public StockDetails findByStockValue(int compnyCode);
	@Transactional
	@Modifying
	@Query("DELETE FROM StockDetails where compnyCode=:compnyCode")
	public void deleteByCompnyCode(int compnyCode);
	@Query("FROM StockDetails where compnyCode = :compnyCode and stockDate >= :startDate and stockDate <= :endDate")
	public List<StockDetails> findByStockDate(@Param("compnyCode") int compnyCode,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);
	
	
	
	

}
