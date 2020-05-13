package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Credit_Calculator;
/**
* создание класса Test5
*/
class Test5 {
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_anuity(15000, 11, 7, 1, 1);
		/**
		* присвоение переменной payment_anuity результатов вычисления переплаты по кредиту
		*/
		double payment_anuity = test.getPayment_anuity();
		/**
		* сравнение значения переменной payment_anuity с верным значением
		*/
		assertEquals(payment_anuity, 1412.83);

	}

}
