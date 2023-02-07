package com.mhj.s1.bankbook;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mhj.s1.MyTestCase;

public class BankbookDAOTest extends MyTestCase {
	
	@Autowired
	private BankbookDAO bankbookDAO;
	
	@Test
	public void getBankbookListTest() throws Exception {
		List<BankbookDTO> ar = bankbookDAO.getBankbookList();
		assertNotEquals(0, ar.size());
	}

}
