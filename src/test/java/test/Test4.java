package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Credit_Calculator;
/**
* создание класса Test4
*/
class Test4 {
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_anuity(550000, 24, 10, 2, 1);	
		/**
		* присвоение переменной payment_anuity результатов вычисления аннуитетного платежа 
		*/
		double payment_anuity = test.getPayment_anuity();
		/**
		* сравнение значения переменной payment_anuity с верным значением
		*/
		assertEquals(payment_anuity, 25381.71);

	}

}
