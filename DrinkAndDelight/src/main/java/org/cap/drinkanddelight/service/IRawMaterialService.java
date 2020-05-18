package org.cap.drinkanddelight.service;

import java.util.Date;
import java.util.List;
import org.cap.drinkanddelight.entities.RawMaterialOrderEntity;

public interface IRawMaterialService {

	/* Place Raw Material */
	RawMaterialOrderEntity placeRawMaterialOrder(String name, String supplierId, double quantityValue,
			String quantityUnit, Date dateOfDelivery, double pricePerUnit, String warehouseId);

	/* Save Raw Material */
	RawMaterialOrderEntity save(RawMaterialOrderEntity rmoe);

	/* displayRawmaterialOrders */
	List<RawMaterialOrderEntity> fetchAll();

	/* trackRawMaterialOrder */
	RawMaterialOrderEntity findById(String orderId);

	/* doesRawMaterialOrderIdExist */
	public boolean doesRawMaterialOrderIdExist(String id);

	/* updateStatusRawMaterialOrder */
	RawMaterialOrderEntity updateRawMaterialOrder(String deliveryStatus, RawMaterialOrderEntity rmoe);

	/* fetchSupplierIds */
	public List<String> fetchSupplierIds();
	
	/* fetchWarehouseIds */
	public List<String> fetchWarehouseIds();
	
	/* fetchRawMaterialNames */ 
	public List<String> fetchRawMaterialNames();
	 
}
