package test.com.clover.constants;

import org.junit.Test;

import com.clover.tools.common.utils.IDCardUtil;

public class IDCardUtilTest {

	@Test
	public void testIsIdCardNo() {
		System.out.println(IDCardUtil.isIdCardNo("230826199901016693"));
	}
}
