package org.cap.drinkanddelight.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.cap.drinkanddelight.dao.IRawMaterialOrdersDAO;
import org.cap.drinkanddelight.entities.RawMaterialOrderEntity;
import org.cap.drinkanddelight.exceptions.DateTimeException;
import org.cap.drinkanddelight.exceptions.DisplayException;
import org.cap.drinkanddelight.exceptions.EmptyDataException;
import org.cap.drinkanddelight.exceptions.LessThanZeroException;
import org.cap.drinkanddelight.exceptions.RMOrderIDDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RawMaterialServiceImpl  implements IRawMaterialService
{
	@Autowired
	private IRawMaterialOrdersDAO dao;

	@Override
	public RawMaterialOrderEntity placeRawMaterialOrder(String name, String supplierId, double quantityValue, String quantityUnit,
			Date dateOfDelivery, double pricePerUnit, String warehouseId) {
		
		RawMaterialOrderEntity rmoe=new RawMaterialOrderEntity();
		String id=generatedId(9);
		rmoe.setOrderId(generatedId(9));
		rmoe.setName(name);
		rmoe.setSupplierId(supplierId);
		rmoe.setQuantityValue(quantityValue);
		rmoe.setQuantityUnit(quantityUnit);
		rmoe.setDateOfOrder(new Date());
		rmoe.setDateOfDelivery(dateOfDelivery);
		rmoe.setPricePerUnit(pricePerUnit);
		rmoe.setWarehouseId(warehouseId);
		rmoe.setDeliveryStatus("PENDING");
		rmoe.setTotalPrice(quantityValue*pricePerUnit);	
		
		if(rmoe.getName().isEmpty() && rmoe.getSupplierId().isEmpty() && (rmoe.getQuantityValue()==0) && rmoe.getQuantityUnit().isEmpty() && dateOfDelivery.equals(null) && (rmoe.getPricePerUnit()==0) && rmoe.getWarehouseId().isEmpty())
			throw new EmptyDataException("Empty Data Exception");
		
		else if((rmoe.getQuantityValue()<0) && (rmoe.getPricePerUnit()<0))
			throw new LessThanZeroException("Values cannot be negative");
		
		else if((rmoe.getDateOfOrder().after(rmoe.getDateOfDelivery())))
			throw new DateTimeException("Delivery Date must be after the Order Date");
		
		else
		{
			rmoe=save(rmoe);
			return rmoe;	
		}
	}

	String generatedId(int digits) {
		StringBuilder id=new StringBuilder();
		for(int i=0;i<digits;i++)
		{
			Random random=new Random();
			int num=random.nextInt(9);
			id.append(num);
		}
		return id.toString();
	}
	
	@Override
	public RawMaterialOrderEntity save(RawMaterialOrderEntity rmoe) {
		rmoe=dao.save(rmoe);
		return rmoe;
	}

	@Override
	public List<RawMaterialOrderEntity>fetchAll()
	{ 
			List<RawMaterialOrderEntity>rmoes=dao.findAll();
		   
		   if (rmoes.isEmpty()) 
				throw new DisplayException("No Records found");
		   else 
			   	return rmoes; 	
	}
 
	@Override
	public RawMaterialOrderEntity findById(String orderId) {
		Optional<RawMaterialOrderEntity>optional=dao.findByOrderId(orderId);
	     if(optional.isPresent()){
	    	 RawMaterialOrderEntity rmoe=optional.get();
	         return rmoe;
	     }
	     throw new RMOrderIDDoesNotExistException("RawMaterial does not exist="+orderId);
	    }

		@Override
	public boolean doesRawMaterialOrderIdExist(String orderId) 
		{
		Optional<RawMaterialOrderEntity>optional=dao.findByOrderId(orderId);
	     if(optional.isPresent())
	    	 return true;
	     else
	    	 return false;

		}

		   @Override public RawMaterialOrderEntity updateRawMaterialOrder(String deliveryStatus, RawMaterialOrderEntity rmoe)
		  {
				rmoe.setDeliveryStatus(deliveryStatus);
				dao.save(rmoe);
				return rmoe;
		  }
		   
		   @Override 
		   public List<String> fetchSupplierIds()
		   { 
			   List<String>ids =dao.fetchSuppliersId(); 
			   
			   if (ids.isEmpty()) 
					throw new DisplayException("No Records found of Suppliers");
			   else 
					return ids; 
		   }

			@Override
			public List<String> fetchWarehouseIds() 
			{ 
					List<String> ids =dao.fetchWarehousesId();
				   
				   if (ids.isEmpty())
						throw new DisplayException("No Records found of Warehouses");
				   else 
						return ids; 
			}
			 

			@Override
			public List<String> fetchRawMaterialNames()
			{ 
				List<String> ids =dao.fetchNames();
				   
				   if (ids.isEmpty()) 
					   throw new DisplayException("No Records found of RawMaterial Names");
				   else 
					   return ids; 
			}
	}

