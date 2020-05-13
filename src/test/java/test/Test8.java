package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Credit_Calculator;
/**
* создание класса Test8
*/
class Test8 {
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_anuity(13500, 12, 9, 1, 0);	
		/**
		* присвоение переменной summ_anuity результатов вычисления аннуитетного платежа 
		*/
		double summ_anuity = test.getSumm_anuity();
		/**
		* сравнение значения переменной summ_anuity с верным значением
		*/
		assertEquals(summ_anuity, 14179.14);

	}

}
