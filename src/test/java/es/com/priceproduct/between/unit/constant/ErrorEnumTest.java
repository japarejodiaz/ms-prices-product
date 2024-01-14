package es.com.priceproduct.between.unit.constant;

import es.com.priceproduct.between.constant.ErrorEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ErrorEnumTest {

	@Test
	public void testGetMessage() {
		Assert.assertEquals("error.not.found.price", ErrorEnum.NOT_FOUND_PRICE.getMessage());
	}
}