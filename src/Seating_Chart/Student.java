/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seating_Chart;

/**
 *
 * @author Nadav
 */
public class Student
{
	String first, last;
	public Student(String first, String last)
	{
		this.first = first;
		this.last = last;
	}

	public String toString()
	{
		return first + " " + last;
	}
        
        public int length()
        {
            return toString().length();
        }
}