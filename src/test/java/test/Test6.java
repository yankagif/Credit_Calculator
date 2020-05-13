package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Credit_Calculator;
/**
* создание класса Test6
*/
class Test6 {
	@Test
	void test() {
		/**
		* создание экземпляра класса Credit_Calculator
		*/
		Credit_Calculator test = new Credit_Calculator();
		/**
		* вызов метода расчета кредита с введенными данными
		*/
		test.calculate_diff(178000, 7, 5, 0, 0);	
		/**
		* присвоение переменной summ_diff результатов вычисления аннуитетного платежа 
		*/
		double summ_diff = test.getSumm_diff();
		/**
		* сравнение значения переменной summ_diff с верным значением
		*/
		assertEquals(summ_diff, 180966.66);

	}

}
