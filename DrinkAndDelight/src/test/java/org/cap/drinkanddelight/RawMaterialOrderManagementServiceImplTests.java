package org.cap.drinkanddelight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.cap.drinkanddelight.entities.RawMaterialOrderEntity;
import org.cap.drinkanddelight.exceptions.RMOrderIDDoesNotExistException;
import org.cap.drinkanddelight.service.IRawMaterialService;
import org.cap.drinkanddelight.service.RawMaterialServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest// for jpa tests
@ExtendWith(SpringExtension.class)// integrate spring test framework with junit5
@Import(RawMaterialServiceImpl.class)
// importing RoomServiceImpl class as @DatajpaTest will only only search for repositories
class RawMaterialOrderManagementServiceImplTests {

    @Autowired
    private IRawMaterialService rawMaterialOrderService;

    @Autowired
    private EntityManager entityManager;

    /**
     * case when rawmaterialorder does not exist in database before
     */
    @Test
    public void testSaveRawMaterialOrderEntity_1() {
    	String name="wood",supplierId="12",quantityUnit="2",warehouseId="4",dateOfDelivery="2020-05-14";
    	double quantityValue = 12, pricePerUnit = 2;
    	Date d;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfDelivery);
		
    	RawMaterialOrderEntity order=rawMaterialOrderService.placeRawMaterialOrder(name, supplierId, quantityValue, quantityUnit, d, pricePerUnit, warehouseId);
    	List<RawMaterialOrderEntity> list = entityManager.createQuery("from RawMaterialOrderEntity").getResultList();
		Assertions.assertEquals(1, list.size());
		RawMaterialOrderEntity expected = list.get(0);
		Assertions.assertEquals(expected, order);
		Assertions.assertEquals(name, expected.getName());
		Assertions.assertEquals(supplierId, expected.getSupplierId());
		
		System.out.println("RawMaterialOrderEntity Added Successfully!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    	// when id is wrong or supplier doesn't exist.
 		@Test
 		public void test_fetchRawMaterialById() {
 			Executable executable = () -> rawMaterialOrderService.findById("222");
 			Assertions.assertThrows(RMOrderIDDoesNotExistException.class, executable);
 		}
 		
 	// when id is correct and RawMaterialOrderEntity is fetched.
 			@Test
 			public void test_fetchSupplierById2() {
 				String id="1", name="wood",supplierId="12",quantityUnit="2",warehouseId="4",dateOfDelivery="2020-05-14";
 		    	double quantityValue = 12, pricePerUnit = 2;
 		    	RawMaterialOrderEntity rmoe=new RawMaterialOrderEntity();
 				rmoe.setOrderId(id);
 				rmoe.setName(name);
 				rmoe.setSupplierId(supplierId);
 				rmoe.setQuantityValue(quantityValue);
 				rmoe.setQuantityUnit(quantityUnit);
 				rmoe.setDateOfOrder(new Date());
 				Date d;
 				try {
 					d = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfDelivery);
 				rmoe.setDateOfDelivery(d);
 				rmoe.setPricePerUnit(pricePerUnit);
 				rmoe.setWarehouseId(warehouseId);
 				rmoe.setDeliveryStatus("PENDING");
 				rmoe.setTotalPrice(quantityValue*pricePerUnit);	
 				
 				rmoe = entityManager.merge(rmoe);

 				String id2 = rmoe.getOrderId();
 				RawMaterialOrderEntity rmoe2 = rawMaterialOrderService.findById(id2);
 				Assertions.assertEquals(rmoe, rmoe2);
 				Assertions.assertEquals(name, rmoe.getName());
 				} catch (ParseException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			}
}
