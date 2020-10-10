import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Stack;

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
	JTextField pressing;
	JTextField inputs;
	JTextField alert;
	JTextField resultValue;
	
	// default constructor
	public Calculator() { 
		
		setSize(500,400);
		setTitle("POSTFIX CALCULATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		// buttons for entering a number
		JButton enter = new JButton("Enter a number");
		
		// buttons for operators
		JButton add = new JButton("Add");
		JButton subtract = new JButton("Subtract");
		JButton multiply = new JButton("Multiply");
		JButton divide = new JButton("Divide");
		
		// buttons for numbers
		JButton number0 = new JButton("0");
		JButton number1 = new JButton("1");
		JButton number2 = new JButton("2");
		JButton number3 = new JButton("3");
		JButton number4 = new JButton("4");
		JButton number5 = new JButton("5");
		JButton number6 = new JButton("6");
		JButton number7 = new JButton("7");
		JButton number8 = new JButton("8");
		JButton number9 = new JButton("9");
		
		// clear button
		JButton clear = new JButton("Clear");
		
		// panel for the title
		JLabel titleLabel = new JLabel("<Postfix Calculator - click your input!>");
		JPanel titlePanel = new JPanel();
		titlePanel.add(titleLabel);
		this.add(titlePanel);
		
		// panel for currently pressing number
		JLabel pressingLabel = new JLabel("Number");
		pressing = new JTextField(20);
		pressing.setEditable(false);
		JPanel pressingPanel = new JPanel();
		pressingPanel.add(pressingLabel);
		pressingPanel.add(pressing);
		pressingPanel.add(enter);
		this.add(pressingPanel);
		
		// panel for user input
		JLabel inputsLabel = new JLabel("Input");
		inputs = new JTextField(20);
		inputs.setEditable(false);
		JPanel inputsPanel = new JPanel();
		inputsPanel.add(inputsLabel);
		inputsPanel.add(inputs);
		this.add(inputsPanel);
		
		// panel for the result
		JLabel resultLabel = new JLabel("Result");
		resultValue = new JTextField(20);
		resultValue.setEditable(false);
		JPanel resultPanel = new JPanel();
		resultPanel.add(resultLabel);
		resultPanel.add(resultValue);
		this.add(resultPanel);
		
		// panel for operators
		JPanel operatorPanel = new JPanel();
		operatorPanel.add(add); 
		operatorPanel.add(subtract);
		operatorPanel.add(multiply); 
		operatorPanel.add(divide);
		operatorPanel.add(clear);
		this.add(operatorPanel); 

		// two panels for numbers
		JPanel numberPanel1 = new JPanel();
		numberPanel1.add(number0); 
		numberPanel1.add(number1);
		numberPanel1.add(number2); 
		numberPanel1.add(number3);
		numberPanel1.add(number4); 
		this.add(numberPanel1);
		
		JPanel numberPanel2 = new JPanel();
		numberPanel2.add(number5); 
		numberPanel2.add(number6);
		numberPanel2.add(number7); 
		numberPanel2.add(number8);
		numberPanel2.add(number9); 
		this.add(numberPanel2);
		
		// panel for alert
		JLabel alertLabel = new JLabel("Alert");
		alert = new JTextField(20);
		alert.setEditable(false);
		JPanel alertPanel = new JPanel();
		alertPanel.add(alertLabel);
		alertPanel.add(alert);
		this.add(alertPanel);
		
		// set action commands & add action listeners
		add.setActionCommand("+");
		subtract.setActionCommand("-");
		multiply.setActionCommand("*");
		divide.setActionCommand("/");
		enter.setActionCommand("Enter");
		clear.setActionCommand("Clear");
		
		number0.setActionCommand("0");
		number1.setActionCommand("1");
		number2.setActionCommand("2");
		number3.setActionCommand("3");
		number4.setActionCommand("4");
		number5.setActionCommand("5");
		number6.setActionCommand("6");
		number7.setActionCommand("7");
		number8.setActionCommand("8");
		number9.setActionCommand("9");
		
		add.addActionListener(this);
		subtract.addActionListener(this);
		multiply.addActionListener(this);
		divide.addActionListener(this);
		enter.addActionListener(this);
		clear.addActionListener(this);
		
		number0.addActionListener(this);
		number1.addActionListener(this);
		number2.addActionListener(this);
		number3.addActionListener(this);
		number4.addActionListener(this);
		number5.addActionListener(this);
		number6.addActionListener(this);
		number7.addActionListener(this);
		number8.addActionListener(this);
		number9.addActionListener(this);
		
		setVisible(true);
	}

	String numberPressing = ""; // number that user is pressing
	String pressed = ""; // what user pressed
	
	Stack<Number> operands = new Stack<>(); // stack for operands
	
	double operand1;
	double operand2;
	double result = 0.0; // calculation result
	boolean dividedByZero = false; // if user tried to divide an operand by zero
	
	// for action events
	public void actionPerformed(ActionEvent e) {

		// if user presses "clear", go back to default setting
		if (e.getActionCommand().equals("Clear")) {
			inputs.setText("");
			alert.setText("");
			resultValue.setText("");
			
			// empty the operand stack
			if (operands.size() != 0) {
				for (int i=0;i<operands.size()+1;i++) {
					operands.pop();
					System.out.println(operands.size());
				}
			}
			
			numberPressing = "";
			pressing.setText("");
			
			pressed = "";
			inputs.setText(pressed);
			
			result = 0.0;
			
			dividedByZero = false;
		}
		
		// if user presses "enter a number", add the currently pressed number to the stack
		else if (e.getActionCommand().equals("Enter")) {
			// append the number to the user input text field
			if (pressed != "") {
				pressed = pressed + " " + numberPressing;
			}
			else {
				pressed = pressed + numberPressing;
			}
			
			operands.add(Double.parseDouble(numberPressing)); // append the number to the operand stack
			
			// clear the "Enter a number" text field
			numberPressing = "";
			pressing.setText(numberPressing);
			
			if (alert.getText().equals("Please enter an integer")) {
				alert.setText("");
			}
			
			inputs.setText(pressed); // append the user input to the user input text field
			
			System.out.println(operands); // just for checking
		}
		
		// otherwise
		else {
			// if user had not tried to divide an operand by zero
			if (dividedByZero == false) {
				// add
				if (e.getActionCommand().equals("+") ) {
					// if it's calculable; there's enough operands(at least 2) for the operators
					if (operands.size() >= 2) {
						pressed = pressed + " " + e.getActionCommand(); // append it to the user input text field
						operand2 = operands.pop().doubleValue(); // pop
						operand1 = operands.pop().doubleValue(); // pop
						result = operand1 + operand2; // calculate
						operands.add(result); // push the result to the stack 

						alert.setText(""); // remove alerts if there's any
						resultValue.setText(Double.toString(result)); // append the result to the result value text field
						
						System.out.println(operands); // just for checking
					}
					// if it's not calculable; there's only one or no operand
					else {
						alert.setText("Please enter an integer"); 
					}
				}
				// subtract
				else if (e.getActionCommand().equals("-")) {
					// if it's calculable; there's enough operands for the operators
					if (operands.size() >= 2) {
						pressed = pressed + " " + e.getActionCommand(); // append it to the user input text field
						operand2 = operands.pop().doubleValue(); // pop
						operand1 = operands.pop().doubleValue(); // pop 
						result = operand1 - operand2; // calculate
						operands.add(result); // push the result to the stack
						
						alert.setText(""); // remove alerts if there's any
						resultValue.setText(Double.toString(result)); // append the result to the result value text field
						
						System.out.println(operands); // just for checking
					}
					// if it's not calculable; there's only one or no operand
					else {
						alert.setText("Please enter an integer"); 
					}
				}
				// multiply
				else if (e.getActionCommand().equals("*")) {
					// if it's calculable; there's enough operands for the operators
					if (operands.size() >= 2) {
						pressed = pressed + " " + e.getActionCommand(); // append it to the user input text field
						operand2 = operands.pop().doubleValue(); // pop
						operand1 = operands.pop().doubleValue(); // pop
						result = operand1 * operand2; // calculate
						operands.add(result); // push the result to the stack
						
						alert.setText(""); // remove alerts if there's any
						resultValue.setText(Double.toString(result)); // append the result to the result value text field
						System.out.println(operands);
					}
					// if it's not calculable; there's only one or no operand
					else {
						alert.setText("Please enter an integer"); 
					}		
				}
				// divide
				else if (e.getActionCommand().equals("/")) {
					// if it's calculable; there's enough operands for the operators
					if (operands.size() >= 2) {
						pressed = pressed + " " + e.getActionCommand(); // append it to the user input text field
						operand2 = operands.pop().doubleValue(); // pop
						operand1 = operands.pop().doubleValue(); // pop
						
						// if user tried to divide the operand by zero.
						if (operand2 == 0.0) {
							dividedByZero = true;
							alert.setText("Please press 'Clear' and start again.");
							resultValue.setText("cannot divide by zero.");
						}
						// otherwise
						else {
							result = operand1 / operand2; // calculate
							operands.add(result); // push the result to the stack
							alert.setText(""); // remove alerts if there's any
							resultValue.setText(Double.toString(result)); // append the result to the result value text field
						}
						System.out.println(operands); // just for checking
					}
					// if it's not calculable; there's only one or no operand
					else {
						alert.setText("Please enter an integer"); 
					}
				}
				// if user input is a number
				else {
					numberPressing = numberPressing + e.getActionCommand();
					pressing.setText(numberPressing);
				}
			}
		
			inputs.setText(pressed); // append the user input to the user input text field
		}
		
	}

}
