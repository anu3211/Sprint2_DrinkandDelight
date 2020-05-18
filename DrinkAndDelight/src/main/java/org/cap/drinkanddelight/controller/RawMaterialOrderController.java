package org.cap.drinkanddelight.controller;

import org.cap.drinkanddelight.dto.RawMaterialOrderDetailsDto;
import org.cap.drinkanddelight.dto.RawMaterialOrderRequestDto;
import org.cap.drinkanddelight.entities.RawMaterialOrderEntity;
import org.cap.drinkanddelight.exceptions.DateTimeException;
import org.cap.drinkanddelight.exceptions.DisplayException;
import org.cap.drinkanddelight.exceptions.EmptyDataException;
import org.cap.drinkanddelight.exceptions.RMOrderIDDoesNotExistException;
import org.cap.drinkanddelight.service.IRawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/rawMaterialOrder")
public class RawMaterialOrderController 
{
	
    @Autowired
    private IRawMaterialService service;

    @PostMapping("/placeOrder")
    public ResponseEntity<RawMaterialOrderDetailsDto> book(@RequestBody RawMaterialOrderRequestDto requestDto) {
					        String name = requestDto.getName();
					        String supplierId = requestDto.getSupplierId();
					        double quantityValue = requestDto.getQuantityValue();
					        String quantityUnit = requestDto.getQuantityUnit();
					        Date dateOfDelivery = requestDto.getDateOfDelivery();
					        double pricePerUnit = requestDto.getPricePerUnit();
					        String warehouseId = requestDto.getWarehouseId();
        RawMaterialOrderEntity rmoe=service.placeRawMaterialOrder(name, supplierId, quantityValue, quantityUnit, dateOfDelivery, pricePerUnit, warehouseId);
        RawMaterialOrderDetailsDto detailsDto=convertRawMaterialOrderDto(rmoe);
        ResponseEntity<RawMaterialOrderDetailsDto> response = new ResponseEntity<>(detailsDto, HttpStatus.OK);
        return response;

    }
    private RawMaterialOrderDetailsDto convertRawMaterialOrderDto(RawMaterialOrderEntity rmoe) {
		RawMaterialOrderDetailsDto dto=new RawMaterialOrderDetailsDto();
		dto.setOrderId(rmoe.getOrderId());
		dto.setName(rmoe.getName());
		dto.setSupplierId(rmoe.getSupplierId());
		dto.setQuantityValue(rmoe.getQuantityValue());
		dto.setQuantityUnit(rmoe.getQuantityUnit());
		dto.setDateOfOrder(rmoe.getDateOfOrder());
		dto.setDateOfDelivery(rmoe.getDateOfDelivery());
		dto.setPricePerUnit(rmoe.getPricePerUnit());
		dto.setTotalPrice(rmoe.getTotalPrice());
		dto.setDeliveryStatus(rmoe.getDeliveryStatus());
		dto.setWarehouseId(rmoe.getWarehouseId());
		return dto;
		
	}
   

	@GetMapping
    public ResponseEntity<List<RawMaterialOrderDetailsDto>> fetchAll() {
        List<RawMaterialOrderEntity> rawMaterials = service.fetchAll();
        List<RawMaterialOrderDetailsDto> list = convertRawMaterialOrderDto(rawMaterials);
        ResponseEntity<List<RawMaterialOrderDetailsDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
        return response;
    }


    private List<RawMaterialOrderDetailsDto> convertRawMaterialOrderDto(List<RawMaterialOrderEntity> rawMaterials) {
		List<RawMaterialOrderDetailsDto> list=new ArrayList();
		for (RawMaterialOrderEntity order : rawMaterials) {
			RawMaterialOrderDetailsDto details=convertRawMaterialOrderDto(order);
			list.add(details);
		}
		return list;
	} 
    
    @GetMapping("/getbyid/{orderId}")
    public ResponseEntity<RawMaterialOrderEntity>findRawMaterialById( @PathVariable("orderId")  String orderId){
    	RawMaterialOrderEntity rmoe= service.findById(orderId);
       ResponseEntity<RawMaterialOrderEntity>response=new ResponseEntity<>(rmoe,HttpStatus.OK);
       return response;
    }
	  
	@GetMapping("/checkbyid/{orderId}")
	public ResponseEntity<Boolean>check( @PathVariable("orderId")  String orderId){
	    	Boolean result= service.doesRawMaterialOrderIdExist(orderId);
	       ResponseEntity<Boolean>response=new ResponseEntity<>(result,HttpStatus.OK);
	       return response;
	    }
		
	 @PutMapping("/update/{orderId}/{status}") 
		  public ResponseEntity<RawMaterialOrderDetailsDto> update(@PathVariable("orderId") String orderId,@PathVariable("status")String status) 
		  { 
			  RawMaterialOrderEntity rmoe=service.findById(orderId);
			  rmoe=service.updateRawMaterialOrder(status, rmoe);
			  RawMaterialOrderDetailsDto detailsDto=convertRawMaterialOrderDto(rmoe);
			  ResponseEntity<RawMaterialOrderDetailsDto> response = new ResponseEntity<>(detailsDto, HttpStatus.OK); return response; 
		  }
		 
	 @GetMapping("/allSupplierIds") 
	 public ResponseEntity<List<String>>supplierIds()
	 {
		 List<String> list=service.fetchSupplierIds();
		 ResponseEntity<List<String>> response=new ResponseEntity<>(list,HttpStatus.OK);
		 return response; 
	 }
	
	 @GetMapping("/allWarehouseIds")
	 public ResponseEntity<List<String>>warehouseIds()
	 {
		 List<String> list=service.fetchWarehouseIds();
		 ResponseEntity<List<String>> response=new ResponseEntity<>(list,HttpStatus.OK);
		 return response; 
	 }
	 
	  @GetMapping("/allRawMaterialNames") 
	  public ResponseEntity<List<String>>rawMaterialsNames()
	  {
		  List<String> list=service.fetchRawMaterialNames();
		  ResponseEntity<List<String>> response=new ResponseEntity<>(list,HttpStatus.OK);
		  return response;
	  }
	  @ExceptionHandler(RMOrderIDDoesNotExistException.class)
	   public ResponseEntity<String>handleRMOrderIdNotFound(RMOrderIDDoesNotExistException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
	       return response;
	   }

	  @ExceptionHandler(DateTimeException.class)
	   public ResponseEntity<String>handleDateTime(DateTimeException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	  @ExceptionHandler(EmptyDataException.class)
	   public ResponseEntity<String>handleEmptyEntry(EmptyDataException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.PARTIAL_CONTENT);
	       return response;
	   }

	  @ExceptionHandler(Throwable.class)
	    public ResponseEntity<String>handleAll(Throwable ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
	       return response;
	    }

	  @ExceptionHandler(DisplayException.class)
	   public ResponseEntity<String>handleDisplay(DisplayException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }
	 
	 
}
