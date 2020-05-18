package org.cap.drinkanddelight.dao;

import java.util.List;
import java.util.Optional;

import org.cap.drinkanddelight.entities.RawMaterialOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRawMaterialOrdersDAO extends JpaRepository<RawMaterialOrderEntity, Integer> {
	
	 Optional<RawMaterialOrderEntity> findByOrderId(String orderId);

	 @Query("Select supplierId from RawMaterialOrderEntity") 
	 List<String> fetchSuppliersId();
	 
	 @Query("Select warehouseId from RawMaterialOrderEntity") 
	 List<String> fetchWarehousesId();
	 
	 @Query("Select name from RawMaterialOrderEntity")
	 List<String> fetchNames();
	
}
