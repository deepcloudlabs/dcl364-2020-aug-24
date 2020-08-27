package com.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class TcKimlikNoValidator implements ConstraintValidator<TcKimlik, String> {

	@Override
	public void initialize(TcKimlik arg0) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return false;
		if (!value.matches("^\\d{11}$")) {
			return false;
		}
		int[] digits = new int[11];
		for (int i = 0; i < digits.length; ++i) {
			digits[i] = value.charAt(i) - '0';
		}
		int x = digits[0];
		int y = digits[1];
		for (int i = 1; i < 5; i++) {
			x += digits[2 * i];
		}
		for (int i = 2; i <= 4; i++) {
			y += digits[2 * i - 1];
		}
		int c1 = 7 * x - y;
		if (c1 % 10 != digits[9]) {
			return false;
		}
		int c2 = 0;
		for (int i = 0; i < 10; ++i) {
			c2 += digits[i];
		}
		if (c2 % 10 != digits[10]) {
			return false;
		}
		return true;
	}

}
