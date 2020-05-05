package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Credit_Calculator;
/**
* создание класса Test2
*/
class Test2 {
	
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_anuity(250000, 18, 12, 2, 0);		
		/**
		* присвоение переменной overpay_anuity результатов вычисления переплаты по кредиту
		*/
		double overpay_anuity = test.getOverpay_anuity();
		/**
		* сравнение значения переменной overpay_anuity с верным значением
		*/
		assertEquals(overpay_anuity, 24455.22);

	}

}
