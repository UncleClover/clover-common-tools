package test.com.clover.constants;

import org.junit.Test;

import com.clover.api.tools.common.utils.IDCardUtil;

public class IDCardUtilTest {

	@Test
	public void testIsIdCardNo() {
		System.out.println(IDCardUtil.isIdCardNo("653561193707256964"));
	}

	@Test
	public void TestRandom() {
		// System.out.println(IDCardUtil.random("412727", null,null));
		for (int i = 0; i < 1000; i++)
			System.out.println(IDCardUtil.random());
	}
}
