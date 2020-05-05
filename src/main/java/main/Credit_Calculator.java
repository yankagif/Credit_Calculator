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
