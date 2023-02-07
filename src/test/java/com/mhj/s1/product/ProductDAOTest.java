package com.mhj.s1.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mhj.s1.MyTestCase;
import com.mhj.s1.product.ProductDAO;
import com.mhj.s1.product.ProductDTO;

public class ProductDAOTest extends MyTestCase {

	@Autowired
	private ProductDAO productDAO;
	
	@Test
	public void getProductListTest() throws Exception {
		List<ProductDTO> ar = productDAO.getProductList();
		//단정문
		assertNotEquals(0, ar.size());
	}
	
	@Test
	public void getProductDetailTest() throws Exception {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductNum(48L);
		productDAO.getProductDetail(productDTO);
		assertNotNull(productDTO);
	}

	//insert
	
	@Test
	public void setProductAddTest() throws Exception {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductNum(10L);
		productDTO.setProductName("kong pencil");
		productDTO.setProductDetail("so cute");
		int result = productDAO.setProductAdd(productDTO);
		assertEquals(1, result);
	}

}	