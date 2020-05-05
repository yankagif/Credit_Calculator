package main;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateFields{
	JPanel panel = new JPanel();
	
	/** Мметод добавления, указанной при вызове метода, панели **/
	protected void addPanel (JPanel panel) {
		this.panel = panel;
	}
	
	/** Метод, задающий параметры для текстового поля надписи
	 *  по схеме (имя поля, координата Х, координата Y, ширина, высота, стиль) **/
	public void CreateLabel(JLabel label, int locationX, int locationY, int windt, int height, Font font) {
		label.setLocation(locationX, locationY);
		label.setSize(windt, height);
		label.setFont(font);
		panel.add(label);
	}

	/** Метод, задающий параметры для выпадающего списка
	 *  по схеме (имя поля, координата Х, координата Y, ширина, высота, стиль) **/
	public void CreateComboBox(JComboBox combo_box, int locationX, int locationY, int windt, int height, Font font) {
		combo_box.setLocation(locationX, locationY);
		combo_box.setSize(windt, height);
		combo_box.setFont(font);
		panel.add(combo_box);
	}
	
	/** Метод, задающий параметры для текстового поля ввода
	 *  по схеме (имя поля, координата Х, координата Y, ширина, высота, стиль) **/
	public void CreateTextField(JTextField text_field, int locationX, int locationY, int windt, int height, Font font) {
		text_field.setLocation(locationX, locationY);
		text_field.setSize(windt, height);
		text_field.setFont(font);
		panel.add(text_field);
	}

	/** Метод, задающий параметры для кнопки
	 *  по схеме (имя поля, координата Х, координата Y, ширина, высота, стиль) **/
	public void CreateButton(JButton button, int locationX, int locationY, int windt, int height, Font font) {
		button.setLocation(locationX, locationY);
		button.setSize(windt, height);
		button.setFont(font);
		panel.add(button);
	}	
}
