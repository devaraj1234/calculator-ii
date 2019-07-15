package com.calculator_ii;

import java.util.ArrayList;

public class Calculator2 implements java.io.Serializable {
//	Create ArrayList for the operands and for the operations. Create booleans to say if the ArrayLists have something in them. Create a size counter for the ArrayLists.
	private ArrayList<Double> operands = new ArrayList<>();
	private boolean operandsSet = false;
	private int operandsSize = 0;
	private ArrayList<String> operations = new ArrayList<>();
	private boolean operationsSet = false;
	private int operationsSize = 0;
//	Create a total and a boolean to see if total has been set.
	private double total;
	private boolean totalSet = false;
	
	public Calculator2() {
	}
//	If a double is entered:
	public void performOperation(double operand) {
//		If the ArrayList for operands does not contain anything add the entered operand to the ArrayList, set operandsSet to true, and increment the size count
		if(!operandsSet) {
			operands.add(operand);
			operandsSet = true;
			operandsSize++;
//		Else: check to see that the ArrayList for operations contains something
		} else if(operationsSet) {
//			Check if the ArrayLists are the same size
			if(operandsSize == operationsSize) {
//				if the last operation entered was "*" set the last operand to be that value multiplied by the entered operand
				if(operations.get(operandsSize-1) == "*") {
					operands.set(operandsSize-1, (operands.get(operandsSize-1)*operand));
					operations.remove(operationsSize-1);
					operationsSize--;
//				if the last operation entered was "/" set the last operand to be that value divided by the entered operand
				} else if(operations.get(operationsSize-1) == "/") {
					operands.set(operandsSize-1, (operands.get(operandsSize-1)/operand));
					operations.remove(operationsSize-1);
					operationsSize--;
//				if the last operation entered wasn't "*" or "/" add the entered operand to the operands ArrayList and increment the operands size
				} else {
					operands.add(operand);
					operandsSize++;
				}
			}
//		If there is something in the operands ArrayList but nothing in the operations ArrayList then another operand shouldn't be added so throw and error
		} else {
			throw new IllegalThreadStateException();
		}
	}
//	if a string is entered:
	public void performOperation(String operation) {
//		check to make sure the entered operation string is "=" "-" "+" "*" or "/". If it isn't throw an error.
		if(operation != "=" && operation != "+" && operation != "-" && operation != "*" && operation != "/") {
			throw new UnsupportedOperationException();
//		if the entered operation is "=" calculate the sum
		} else if(operation == "=") {
//			if there have been no operands entered then there is nothing to calculate so throw an error
			if(!operandsSet) {
				throw new IllegalThreadStateException();
//			if there are operands entered set the total to the first operand in the operands ArrayList then add or subtract all subsequent operands based on the operation that was entered directly before them
			} else {
				this.total = operands.get(0);
				for(int i=1; i<operandsSize; i++) {
					if(operations.get(i-1) == "+") {
						this.total += operands.get(i);
					} else {
						this.total -= operands.get(i);
					}
				}
//				set that a total has been set
				this.totalSet = true;
			}
//		if the operation entered is "+" "-" "*" or "/" and the size of the operations ArrayList is equal to the size of the operands ArrayList - 1 add the operation to the operations ArrayList, set that an operation has been entered, and increment the size of the operations ArrayList by 1
		} else if(operationsSize == operandsSize-1) {
			operations.add(operation);
			operationsSet = true;
			operationsSize++;
//		if the size of the operations ArrayList is not the size of the operands ArrayList - 1 then another operation shouldn't be added so throw and error
		} else {
			throw new IllegalThreadStateException();
		}
	}
//	If there has been a total set print the total. If not then nothing has been calculated so throw and error
	public void getResults() {
		if(totalSet == true) {
			System.out.println(this.total);
		} else {
			throw new IllegalThreadStateException();
		}
	}
}