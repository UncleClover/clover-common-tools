package test.com.clover.constants;

import static org.junit.Assert.*;

import org.junit.Test;

import com.clover.tools.common.constants.ProvinceCode;

public class ProvinceCodeTest {

	@Test
	public void testGetCode() {
		System.out.println(ProvinceCode.gd.getCode());
	}

	@Test
	public void testGetName() {
		System.out.println(ProvinceCode.gd.getName());
	}

	@Test
	public void testGetNameByCode() {
		assertNotNull(ProvinceCode.getNameByCode("41"));
	}

	@Test
	public void testGetCodeByName() {
		assertNotNull(ProvinceCode.getCodeByName("河南"));
	}
	
	@Test
	public void testLength() {
		System.out.println(ProvinceCode.length());
	}

	@Test
	public void testRandom() {
		System.out.println(ProvinceCode.random());
	}
}
