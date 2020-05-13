package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Credit_Calculator;
/**
* создание класса Test3
*/
class Test3 {
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_anuity(11000, 12, 4, 1, 1);
		/**
		* присвоение переменной overpay_anuity результатов вычисления переплаты по кредиту
		*/
		double overpay_anuity = test.getOverpay_anuity();
		/**
		* сравнение значения переменной overpay_anuity с верным значением
		*/
		assertEquals(overpay_anuity, 252.79);

	}

}
