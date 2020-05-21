package main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class Credit_Calculator {
	/** создание переменных, доступных 
	 *  в любом методе класса **/
	
	/** Переменная - сумма кредита **/
	private static double 	var_sum;
	/** Переменная - срок кредита **/
	private static int 		var_period;
	/** Переменная - проценты по кредиту **/
	private static double 	var_percent;
	/** Переменная - ежемесячная комиссия **/
	private static double 	var_month_com;
	/** Переменная - единовременная комиссия **/
	private static double 	var_onetime_com;
	/** Переменная - еденицы измерения срока кредита **/
	private static String 	var_box_period;
	/** Переменная - вид платежей кредита **/
	private static String 	var_box_type;
	/** Массив из 3 переменных для записи даты начала выплат **/
	private static int [] 	var_box_date = new int [3];

	/** Экземпляр класса Григорианский календарь **/
	private static GregorianCalendar calendar = new GregorianCalendar();
	/** Переменная, содержащая номер дня текущей даты **/
	private static int day_now = calendar.get(Calendar.DAY_OF_MONTH);
	/** Переменная, содержащая номер месяца текущей даты **/
	private static int month_now = calendar.get(Calendar.MONTH);
	/** Переменная, содержащая номер года текущей даты **/
	private static int year_now = calendar.get(Calendar.YEAR);
		
	public static void main(String[] args) {

		/* графическое окно с интерфейсом программы */
		JFrame window = new JFrame("Кредитный калькулятор");
		/* выход из программы при закрытии окна */
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* задание размеров окна */
		window.setSize(360, 560);
		/* запрет на изменение размеров окна */
		window.setResizable(false);
		/* расположение окна по центру экрана */
		window.setLocationRelativeTo(null);
		
		/* панель для расположения графических элементов интерфейса */
		JPanel mainPanel=new JPanel();
		/* отключение диспетчеров размещения:
		 * расположение элементов производится вручную */
		mainPanel.setLayout(null);
		
		/* стиль текста для текстовых полей ввода 
		 * шрифт Dialog, начертание обычное, размер 25 */
		Font textfield_font = new Font("Dialog", Font.PLAIN, 25);
		
		/* создание текстовых полей ввода*/
		
		/* поле — сумма кредита */
		final JTextField SummaField = new JTextField();
		/* поле — срок кредита */
		final JTextField PeriodField = new JTextField();
		/* поле — процент по кредиту */
		final JTextField PercentField = new JTextField();
		/* поле — ежемесячная комиссия */
		final JTextField CommissionField = new JTextField();
		/* поле — единовременная комиссия */
		final JTextField OnetimeField = new JTextField();
		
		/* экземпляр класса CreateFields */		
		CreateFields create = new CreateFields();
		/* выбор панели для размещения объектов */
		create.addPanel(mainPanel);
		
		/* задание параметров для текстовых полей ввода
		 * по схеме (имя поля, координата Х, координата У, ширина, высота, стиль) */
		create.CreateTextField(SummaField, 20, 20, 300, 30, textfield_font);
		create.CreateTextField(PeriodField, 20, 80, 218, 30, textfield_font);
		create.CreateTextField(PercentField, 20, 140, 300, 30, textfield_font);
		create.CreateTextField(CommissionField, 20, 345, 300, 30, textfield_font);
		create.CreateTextField(OnetimeField, 20, 405, 300, 30, textfield_font);
		
		/* стиль текста для текстовых полей надписей 
		 * шрифт Arial, начертание жирное, размер 15 */
		Font label_font = new Font("Arial", Font.BOLD, 15);
		
		/* создание текстовых полей надписей */
		JLabel sum_label = new JLabel("Сумма кредита, руб");
		JLabel period_label = new JLabel("Срок кредита");
		JLabel percent_label = new JLabel("Процентная ставка (в год), %");
		JLabel type_label = new JLabel("Вид платежей");
		JLabel day_label = new JLabel("День");
		JLabel month_label = new JLabel("Месяц");
		JLabel year_label = new JLabel("Год");
		JLabel date_label = new JLabel("Дата выдачи кредита");
		JLabel comission_label = new JLabel("Ежемесячная комиссия, %");
		JLabel onetime_label = new JLabel("Единовременная комиссия, %");
		
		/* задание параметров для текстовых полей надписей
		 * по схеме (имя поля, координата Х, координата У, ширина, высота, стиль) */
		create.CreateLabel(sum_label, 20, 55, 390, 15, label_font);
		create.CreateLabel(period_label, 20, 115, 390, 15, label_font);
		create.CreateLabel(percent_label, 20, 175, 390, 15, label_font);
		create.CreateLabel(type_label, 20, 235, 390, 15, label_font);
		create.CreateLabel(day_label, 20, 295, 390, 15, label_font);
		create.CreateLabel(month_label, 100, 295, 390, 15, label_font);
		create.CreateLabel(year_label, 180, 295, 360, 15, label_font);
		create.CreateLabel(date_label, 20, 320, 390, 15, label_font);
		create.CreateLabel(comission_label, 20, 380, 390, 15, label_font);
		create.CreateLabel(onetime_label, 20, 440, 390, 15, label_font);

		/* стиль текста для выпадающих списков
		 * шрифт Dialog, начертание обычное, размер 20 */
		Font combobox_font = new Font("Dialog", Font.PLAIN, 20);
		
        /* текстовый массив — единицы измерения срока кредита */
		String[] period = {
				"мес.",
				"год"
		};
		
		/* выпадающий список — единицы измерения срока кредита */
		JComboBox period_box = new JComboBox(period);		
		/* запись выбранного элемента из списка в переменную */
		var_box_period = (String)period_box.getSelectedItem();
		
		/* создание слушателя действий */
		ActionListener period_Listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* добавление объекта события */
		    	JComboBox box = (JComboBox)e.getSource();
		    	
		    	/* запись выбранного элемента из списка в переменную */
		    	var_box_period = (String)box.getSelectedItem();
		    }
		};
		/* добавление слушателя к объекту */
		period_box.addActionListener(period_Listener);

		
		/* текстовый массив — виды платежей по кредиту */
		String[] type = {
				"Аннуитетный",
				"Дифференцированный"
		};
		
		/* выпадающий список — виды платежей по кредиту */
		JComboBox type_box = new JComboBox(type);
		/* запись выбранного элемента из списка в переменную */
		var_box_type = (String)type_box.getSelectedItem();
		
		/* создание слушателя действий */	
		ActionListener type_Listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/* добавление объекта события */
		    	JComboBox box = (JComboBox)e.getSource();
		    	
		    	/* запись выбранного элемента из списка в переменную */
		    	var_box_type = (String)box.getSelectedItem();
		    }
		};
		/* добавление слушателя к объекту */
		type_box.addActionListener(type_Listener);
				
		/* текстовый массив — номера дней месяца */
		String[] days = new String [31];
		/* заполнение массива числами от 1 до 31 */
		for (int i = 1; i<=31; i++) {
			days[i-1] = "" + i;
		}
		
		/* выпадающий список — номер дня даты начала выплат */
		final JComboBox day_box = new JComboBox(days);
		/* установка выбранного элемента на текущую дату */
		day_box.setSelectedIndex(day_now-1);
		/* запись выбранного элемента из списка в переменную */
		var_box_date[0] = Integer.parseInt((String)day_box.getSelectedItem());

				/* создание слушателя дейтсвий */
		ActionListener day_Listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* добавление объекта события */
		    	JComboBox box = (JComboBox)e.getSource();
		    	
		    	/* запись выбранного элемента из списка в переменную */
		    	String str = (String)box.getSelectedItem();
		    	/* перевод из строкового типа в целочисленный */
		    	var_box_date[0] = Integer.parseInt(str);
		    }
		};
		/* добавление слушателя к объекту */
		day_box.addActionListener(day_Listener);
		
		/* текстовый массив — номера месяцев */
		String[] month = new String [12];
		/* заполнение массива числами от 1 до 12 */
		for (int i = 1; i<=12; i++) {
			month[i-1] = "" + i;
		}
		
		/* выпадающий список — номер месяца даты начала выплат */
		final JComboBox month_box = new JComboBox(month);
		/* установка выбранного элемента на текущую дату */
		month_box.setSelectedIndex(month_now);
		/* запись выбранного элемента из списка в переменную */
		var_box_date[1] = Integer.parseInt((String)month_box.getSelectedItem());
		
		/* создание слушателя дейтсвий**/
		ActionListener month_Listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/* добавление объекта события */
		    	JComboBox box = (JComboBox)e.getSource();
		    	
		    	/* запись выбранного элемента из списка в переменную */
		    	String str = (String)box.getSelectedItem();
		    	/* перевод из строкового типа в целочисленный */
		    	var_box_date[1] = Integer.parseInt(str);
		    }
		};
		/* добавление слушателя к объекту */
		month_box.addActionListener(month_Listener);
		
		/* текстовый массив — номера годов, 
		 * когда можно оформить кредит */
		String[] years = new String [10];
		/* заполнение массива числами: 
		 * нынешний год + 9 последующих лет */
		for (int i = 0; i<10; i++) {
			years[i] = "" + (i + year_now);
		}
		
		/* выпадающий список — номер года даты начала выплат */
		final JComboBox year_box = new JComboBox(years);
		/* запись выбранного элемента из списка в переменную */
		var_box_date[2] = Integer.parseInt((String)year_box.getSelectedItem());
		
		/* создание слушателя дейтсвий */
		ActionListener year_Listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/* создание объекта события */
		    	JComboBox box = (JComboBox)e.getSource();
		    	
		    	/* запись выбранного элемента из списка в переменную */
		    	String str = (String)box.getSelectedItem();
		    	/* перевод из строкового типа в целочисленный */
		    	var_box_date[2] = Integer.parseInt(str);
		    }
		};
		/* добавление слушателя к объекту */
		year_box.addActionListener(year_Listener);
		
		/* задание параметров для выпадающих списков
		 * по схеме (имя поля, координата Х, координата У, ширина, высота, стиль) */
		create.CreateComboBox(period_box, 243, 80, 77, 30, combobox_font);
		create.CreateComboBox(type_box, 20, 200, 300, 30, combobox_font);
		create.CreateComboBox(day_box, 20, 260, 60, 30, combobox_font);
		create.CreateComboBox(month_box, 100, 260, 60, 30, combobox_font);
		create.CreateComboBox(year_box, 180, 260, 85, 30, combobox_font);	

		/* стиль текста для кнопки
		 * шрифт Arial, начертание обычное, размер 25 */
		Font button_font = new Font("Arial", Font.PLAIN, 25);
		
		/* кнопка для расчета и вывода результатов */
		JButton result_button = new JButton ("Расcчитать");
		/* задание параметров для кнопки по схеме 
		 * (имя поля, координата Х, координата У, ширина, высота, стиль) */
		create.CreateButton(result_button, 20, 465, 300, 45, button_font);

		/* добавление слушателя для кнопки */
		result_button.addMouseListener( new MouseAdapter()			
		{
			/** Метод обработки нажатия на кнопку **/
			public void mouseClicked(MouseEvent event) {
				/* задание значения переменной */
				check = true;
				
				/* вызов метода проверки введенных данных
				 * по схеме (имя поля, минимальное значение, максимальное значение) */
				Credit_Calculator.control(SummaField, 10000, 5000000);
				Credit_Calculator.control(PeriodField, 1, 360);
				if (var_box_period == "год") Credit_Calculator.control(PeriodField, 1, 30);
				Credit_Calculator.control(PercentField, 5, 40);
				Credit_Calculator.control(CommissionField, 0, 5);
				Credit_Calculator.control(OnetimeField, 0, 3);
				/* остановка выполнения метода при наличии ошибок ввода */
				if (!check) return;

				/* присвоение переменным введенных значений из полей */
				var_sum = Double.parseDouble(SummaField.getText());
				var_period = Integer.parseInt(PeriodField.getText());
				var_percent  = Double.parseDouble(PercentField.getText());
				var_month_com = Double.parseDouble(CommissionField.getText());
				var_onetime_com = Double.parseDouble(OnetimeField.getText());
				var_box_date[0] = Integer.parseInt((String)day_box.getSelectedItem());
				var_box_date[1] = Integer.parseInt((String)month_box.getSelectedItem());
				var_box_date[2] = Integer.parseInt((String)year_box.getSelectedItem());
				
				/* вызов метода обработки даты */
				Credit_Calculator.time();
				/* вызов метода расчета сумм платежей по комиссиям*/
				Credit_Calculator.commission();
				
				/* вызов определенного метода, в зависимости от выбранного вида платежей */
				if (var_box_type.equals("Аннуитетный")) {
					Credit_Calculator.calculate_anuity(var_sum, var_period, var_percent, var_month_com, var_onetime_com);
					Credit_Calculator.window_anuity();
				}else {
					Credit_Calculator.calculate_diff(var_sum, var_period, var_percent, var_month_com, var_onetime_com);
					Credit_Calculator.window_diff();
				}
			}
		});
		/* добавление панели в окно */
		window.setContentPane(mainPanel);
		/* задание видимости окна */
		window.setVisible(true);
	}
	
	/** Объект – формат записи даты **/
	private static DateFormat date_format = new SimpleDateFormat("dd.MM.yyyy");
	/** Метод обработки даты**/
	private static void time() {
		/* Присвоение текущей даты объекту календарь */
		calendar.set(Calendar.YEAR, var_box_date[2]);
		calendar.set(Calendar.MONTH, var_box_date[1]-1);
		calendar.set(Calendar.DAY_OF_MONTH, var_box_date[0]);
		
		if (var_box_period == "год") {
			var_period *= 12;
		}
	}
	
	/** Метод расчета сумм выплат по комиссиям **/
	private static void commission() {
		/* вычисление суммы платежа по ежемесячной комиссии */
		if (var_month_com != 0) {
			var_month_com = (Math.rint(100*((var_sum * var_month_com)/100)))/100;
		}
		/* вычисление суммы платежа по единовременной комиссии */
		if (var_onetime_com != 0) {
			var_onetime_com = (Math.rint(100*((var_sum * var_onetime_com)/100)))/100;
		}
	}
	

	/** Строковый массив - результаты расчетов аннуитетных платежей **/
	public static String [] result_anuity = new String[4];
	
	/** Публичный метод расчета аннуитетных платежей **/
	public static void calculate_anuity(double var_sum, int var_period, double var_percent, double var_month_com, double var_onetime_com) {
	
		/* расчет даты конца выплат */
		calendar.add(Calendar.MONTH, var_period);
		/* запись конечной даты в строковую переменную */
		String end_date = date_format.format(calendar.getTime());
		
		/* переменная - ежемесячный платеж */
		double payment = 0;
		/* переменная - сумма выплат */
		double sum_payment;
		/* переменная - переплата */
		double overpay;
		
		/* расчет ежемесячной процентной ставки */
		var_percent = (Math.rint(100000000*(var_percent/(100*12))))/10000000;
		
		/* расчет ежемесячного платежа, суммы выплат и переплаты */
		payment = (var_sum*var_percent)/(1 - Math.pow((1+var_percent), -var_period)) + var_month_com;
		sum_payment = payment*var_period + var_onetime_com;
		sum_payment = (Math.rint(100*(sum_payment)))/100;
		payment = (Math.rint(100*(payment)))/100;
		overpay = sum_payment - var_sum;
	
		/* запись результатов в объекты BigDecimal
		 * для корректного вывода больших чисел*/
		BigDecimal big_summ = new BigDecimal(sum_payment);
		BigDecimal big_overpay = new BigDecimal(overpay);
		/* задание параметров вывода: 
		 * количество цифр после запятой, тип округления*/
		big_summ = big_summ.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		big_overpay = big_overpay.setScale(2, BigDecimal.ROUND_HALF_UP);
	
		/* запись переменных в массив результатов */
		result_anuity[0] = big_summ.toString();
		result_anuity[1] = big_overpay.toString();
		result_anuity[2] = Double.toString(payment);
		result_anuity[3] = end_date;
	}
	
	/** Публичный метод, возвращающий значение 
	 *  суммы выплат по аннуитетному кредиту **/
	public static double getSumm_anuity() {
		double summ = Double.parseDouble(result_anuity[0]);
		return summ;
	}
	
	/** Публичный метод, возвращающий значение 
	 *  переплаты по аннуитетному кредиту **/
	public static double getOverpay_anuity() {
		double overpay= Double.parseDouble(result_anuity[1]);
		return overpay;
	}
	
	/** Публичный метод, возвращающий значение 
	 *  ежемесячного платежа по аннуитетному кредиту **/
	public static double getPayment_anuity() {
		double payment= Double.parseDouble(result_anuity[2]);
		return payment;
	}
	
	/** Метод, выводящий на экран в новом окне
	 *  результаты расчетов по аннуитетному платежу **/
	static void window_anuity() {

		/* стиль текста для текстовых полей надписей
		 * шрифт Arial, начертание обычное, размер 16 */
		Font label_result_font = new Font("Arial", Font.PLAIN, 16);
		
		/* графическое окно с результатами расчетов */
		JFrame frame = new JFrame("Аннуитетный кредит");
		/* задание размера окна */
		frame.setSize(360, 200);
		/* запрет на изменение размеров окна */
		frame.setResizable(false);
		/* расположение окна по центру экрана */
		frame.setLocationRelativeTo(null);
		
		/* панель для расположения надписей с результатами расчетов */
		JPanel panel_result = new JPanel();
		/* отключение диспетчеров размещения:
		 * расположение элементов производится вручную */
		panel_result.setLayout(null);
		
		/* создание текстовых полей надписей */
		JLabel labelsum = new JLabel();
        JLabel labelpereplata = new JLabel();
        JLabel labelm_plata = new JLabel();
        JLabel labeldata = new JLabel();
        JLabel labelres = new JLabel("РЕЗУЛЬТАТЫ РАСЧЕТОВ:");
        
        /* экземпляр класса CreateFields */	
        CreateFields creator = new CreateFields();
		/* выбор панели для размещения объектов */
		creator.addPanel(panel_result);
		
        /* задание параметров для текстовых полей надписей
		 * по схеме (имя поля, координата Х, координата У, ширина, высота, стиль) */
        creator.CreateLabel(labelsum, 20, 55, 390, 15, label_result_font);
        creator.CreateLabel(labelpereplata, 20, 80, 390, 15, label_result_font);
        creator.CreateLabel(labelm_plata, 20, 105, 390, 15, label_result_font);
        creator.CreateLabel(labeldata, 20, 130, 390, 15, label_result_font);
        creator.CreateLabel(labelres, 60, 20, 390, 15, new Font("Arial", Font.PLAIN, 18));
		
        /* заполнение текстом надписей */
		labelsum.setText("Итоговая сумма выплат: " + result_anuity[0] + " руб.");
		labelpereplata.setText("Переплата: " + result_anuity[1] + " руб.");
		labelm_plata.setText("Ежемесячный платеж: " + result_anuity[2] + " руб."); 
		labeldata.setText("Дата окончания выплат: " + result_anuity[3]);
		
		/* добавление панели в окно */
		frame.add(panel_result);
		/* задание видимости окна */		
		frame.setVisible(true);
	}

	/** Строковый массив - названия столбцов итоговой таблицы **/
	static String [] name_column_table = new String [1];
	/** Двумерный строковый массив - данные итоговой таблицы **/
	static String [][] data_table = new String [1][1];
	
	/** Метод расчета дифференцированных платежей**/
	public static void calculate_diff(double var_sum, int var_period, double var_percent, double var_month_com, double var_onetime_com) {
		
		/* переменные - размеры таблицы */
		/* количество строк */
		int difsize_line = var_period+3;
		/* количество столбцов */
		int difsize_column = 5;
		
		/* изменение размеров таблицы при наличии комиссий */
		if (var_onetime_com != 0) {
			difsize_line++;
			difsize_column=6;
		}
		if (var_month_com != 0) {
			difsize_column=6;
		}
		
		/* строковый массив - названия столбцов */
		String [] name_column_dif = new String [difsize_column];
		/* двумерный строковый массив - данные таблицы */
		String [][] data_dif = new String [difsize_line][difsize_column];
		
		/* массив - платежи по кредиту */
		double [] payment_dif = new double[difsize_line-2];
		/* массив - значения выплаченных процентов */
		double [] percent_dif = new double[difsize_line-2];
		/* массив - значения оставшейся суммы кредита */
		double [] balance_dif = new double[difsize_line-2];
		
		/* переменная - сумма выплат */
		double sum_payment = var_sum;
		/* переменная - сумма выплаченных процентов */
		double sum_percent = 0;
		/* переменная - сумма выплат по комиссиям */
		double sum_commission = var_month_com*var_period + var_onetime_com;;
		/* переменная переплаты по кредиту */
		double overpay = 0;
		/* переменные - тело кредита */
		double сredit_body = var_sum/var_period;
		double сredit_body_first = 0;
		
		/* заполнение названий столбцов таблицы */
		name_column_dif[0] = "Дата";
		name_column_dif[1] = "Платеж";
		name_column_dif[2] = "Проценты";
		name_column_dif[3] = "Тело кредита";
		name_column_dif[4] = "Остаток";
		
		/* расчет ежемесячной процентной ставки */
		var_percent = (Math.rint(100000000*(var_percent/(100*12))))/100000000;
		
		/* цикл расчета выплат, процентов за весь период, и запись значений */
		for (int i = 0; i < var_period; i++) {
			сredit_body = (Math.rint(100*(сredit_body)))/100;
			if(i == var_period-1) {
				сredit_body_first = сredit_body;
				сredit_body = sum_payment;
			}
			payment_dif[i] = сredit_body + sum_payment*var_percent + var_month_com;
			percent_dif[i] = sum_payment*var_percent;
			sum_percent += percent_dif[i];
			balance_dif[i] = sum_payment;
			sum_payment -= сredit_body;
			sum_payment= (Math.rint(100*(sum_payment)))/100;
			
			payment_dif[i]= (Math.rint(100*(payment_dif[i])))/100;
			percent_dif[i]= (Math.rint(100*(percent_dif[i])))/100;
			balance_dif[i]= (Math.rint(100*(balance_dif[i])))/100;
		}
		balance_dif[var_period] = sum_payment;

				/* расчет общей суммы выплат и переплаты */
		for (int i=0; i < var_period; i++) {
			sum_payment += payment_dif[i];
		}
		sum_payment += var_onetime_com;
		overpay = sum_payment - var_sum;
						
		/* заполнение всех ячеек таблиц соответствующими данными */
		data_dif[0][0] = date_format.format(calendar.getTime());
		for (int i = 0; i<var_period; i++) {
			calendar.add(Calendar.MONTH, 1);
			data_dif[i+1][0] = date_format.format(calendar.getTime());
			data_dif[i+1][1] = "" + payment_dif[i];
			data_dif[i+1][2] = "" + percent_dif[i];
			data_dif[i+1][3] = "" + сredit_body_first;
			data_dif[i][4] = "" + balance_dif[i];
		}
		data_dif[var_period][3] = "" + сredit_body;		
		data_dif[difsize_line-2][4] = "" + balance_dif[var_period];	
				
		/* запись результатов в объекты BigDecimal
		 * для корректного вывода больших чисел */
		BigDecimal big_summ = new BigDecimal (sum_payment);
		BigDecimal big_percent = new BigDecimal(sum_percent);
		BigDecimal big_overpay = new BigDecimal(overpay);
		BigDecimal big_commission = new BigDecimal(sum_commission);
		/* задание параметров вывода: 
		 * количество цифр после запятой, тип округления */
		big_summ = big_summ.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		big_percent = big_percent.setScale(2, BigDecimal.ROUND_HALF_UP);
		big_overpay = big_overpay.setScale(2, BigDecimal.ROUND_HALF_UP);
		big_commission = big_commission.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		/* изменение значений таблицы при наличии ежемесячной комиссии */
		if (var_month_com != 0.0) {
			for (int i = 0; i<var_period; i++) {
				data_dif[i+1][4] = "" + var_month_com;
				data_dif[i][5] = "" + balance_dif[i];
			}
			data_dif[0][4] = "0";
			data_dif[difsize_line-2][4] = "" + big_commission;
			data_dif[difsize_line-2][5] = "" + balance_dif[var_period];
			
			name_column_dif[4] = "Комиссия";
			name_column_dif[5] = "Остаток";
		}
		
		/* изменение значений таблицы при наличии единовременной комиссии */
		if (var_onetime_com != 0) {			
			/* смещение строк на одну вниз */
			for (int i = var_period+1; i>=0; i--) {
				for (int j = 0; j<4; j++) {
					data_dif[i+1][j] = data_dif[i][j];
				}				
			}
			for (int i = 1; i<var_period+2; i++) {
				data_dif[i][4] = "" + var_month_com;
				data_dif[i][5] = "" + balance_dif[i-1];
			}
			data_dif[0][4] = "0";
			data_dif[0][0] = data_dif[1][0];
			data_dif[1][5] = data_dif[0][5] = "" + var_sum;
			data_dif[1][1] = "" + var_onetime_com;
			data_dif[1][4] = data_dif[1][1];
			data_dif[difsize_line-2][4] = "" + big_commission;
			data_dif[difsize_line-2][5] = "" + balance_dif[var_period];
			
			name_column_dif[4] = "Комиссия";
			name_column_dif[5] = "Остаток";
		} 
		
		/* добавление конечных значений в таблицу */
		data_dif[difsize_line-2][0] = "Всего выплат";
		data_dif[difsize_line-2][1] = "" + big_summ;
		data_dif[difsize_line-2][2] = "" + big_percent;
		data_dif[difsize_line-2][3] = "" + var_sum;
		data_dif[difsize_line-1][0] = "Переплата";
		data_dif[difsize_line-1][1] = "" + big_overpay;
		
		/* заполнение пустых ячеек нулями */
		for (int i=0; i<difsize_line-1; i++) {
			for (int j=0; j<difsize_column; j++) {
				if (data_dif[i][j] == null) {
					data_dif[i][j] = "0.0";
				}
			}
		}
		
 		/* запись полученных данных в итоговую таблицу для вывода */
		data_table = data_dif;
		name_column_table = name_column_dif;
		
		/* запись расчетов суммы выплат и переплаты в публичную переменную */
		result_diff[0] = sum_payment;
		result_diff[1] = overpay;
	}
	/** Массив - результаты расчетов по дифференцированному кредиту **/
	static double  [] result_diff = new double [2];
	
	/** Метод, возвращающий сумму выплат
	 * 	по дифференцированному кредиту **/
	public static double getSumm_diff() {
		return result_diff[0];
	}
	/** Метод, возвращающий переплату
	 *  по дифференцированному кредиту **/
	public static double  getOverpay_diff() {
		return result_diff[1];
	}
		
	
	/** Метод для вывода на экран
	 *  окна с таблицей, содержащей
	 *  результаты расчетов
	 *  по дифференцированному кредиту**/
	static void window_diff () {
		
		/* графическое окно с результатами расчетов */
		JFrame frame = new JFrame("План выплат дифференцированных платежей");
		/* задание предпочтительного размера окна */
		frame.setPreferredSize(new Dimension (770, 318));
		
		/* таблица, содержащая план выплат
		 * по дифференцированному кредиту */
		JTable table = new JTable(data_table, name_column_table);
		
		/* установка размеров окна, подходящих под размеры содержимого */
		frame.pack();
		/* добавление панели прокрутки, содержащую таблицу */
		frame.add(new JScrollPane(table));

		/* добавление рендера для расположения текста в таблице по центру ячеек */
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x=0;x<table.getColumnCount();x++){
	         table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}
		
		/* расположение окна по центру экрана */
		frame.setLocationRelativeTo(null);
		/* задание видимости окна */		
		frame.setVisible(true);
	}
	
	
	/** Переменная, говорящая о наличии ошибок в введенных данных.  
	 *  Значение true означает отсутствие ошибок, false - их наличие**/
	private static boolean check = true;
	
	/** Метод проверки текстовых полей ввода с входными параметрами: 
	 *  имя поля ввода, максимальное значение, минимальное значение**/
	public static boolean control(JTextField field, int min, int max) {
		
		/* проверка поля ввода на наличие символов */
		try {
			switch(field.getText()) {
			case(""):	throw new Exception();}
		}
		catch(Exception ex) {
			if (check) {
				JOptionPane.showMessageDialog(null, "Заполните все поля!", "Внимание!" , JOptionPane.INFORMATION_MESSAGE);
			}			
			field.setText("0");
			check=false;
			return false;
		}
		
		/* проверка поля ввода на тип введенных данных */
		try {Double.parseDouble(field.getText());}
		catch (NumberFormatException ex) {
			if (check) {
				JOptionPane.showMessageDialog(null, "Можно вводить только числа, повторите ввод", "Внимание!" , JOptionPane.INFORMATION_MESSAGE);
			}
			check=false;
			return false;
		}
		
		/* нормирование введенных данных, согласно крайним значениям */
		try {
			/* числовая переменная для проверки значений */
			double x = Double.parseDouble(field.getText());
			if (x < min) {
				field.setText(Integer.toString(min));
				throw new Exception();
			}
			if (x > max) {
				field.setText(Integer.toString(max));
				throw new Exception();
			}
			/* обработка значений срока кредита */
			if (max == 360 | max == 30)	{
				/* округление введенного значения в большую сторону, 
				 * так как срок кредита может быть только целым числом*/
				x = Math.ceil(x);
				int x_int = (int) x;
				field.setText(Integer.toString(x_int));
			}else {
				x-=0;
				field.setText(Double.toString(x));
			}			
		}
		
		catch (Exception ex) {
			if (check) {
				JOptionPane.showMessageDialog(null, "Введены некорректные данные", "Внимание!" , JOptionPane.INFORMATION_MESSAGE);
			}
			check=false;
			return false;
		}
		return check;
	}
}
