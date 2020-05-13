package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Credit_Calculator;
/**
* создание класса Test7
*/
class Test7 {
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_diff(12000, 7, 8, 1, 2);
		/**
		* присвоение переменной overpay_diff результатов вычисления переплаты по кредиту
		*/
		double overpay_diff = test.getOverpay_diff();
		/**
		* сравнение значения переменной overpay_diff с верным значением
		*/
		assertEquals(overpay_diff, 329.0);

	}

}
