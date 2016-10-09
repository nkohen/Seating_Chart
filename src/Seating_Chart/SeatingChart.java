/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seating_Chart;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 *
 * @author Nadav
 */
public class SeatingChart
{
	public static void main(String[] args)
	{
		ArrayList<Student> list = new ArrayList<Student>();
		ArrayList<Student> chart = new ArrayList<Student>();
		try
		{
			Scanner scn = new Scanner(new File("ClassList.txt"));

			while(scn.hasNext())
			{
				String s = scn.nextLine();
				int i;
				for(i = 0; i<s.length(); i++)
				{
					if(s.substring(i,i+1).equals(","))
						break;
				}
				String lastName = s.substring(0,i);
				String firstName = s.substring(i+2,s.length());
				list.add(new Student(firstName, lastName));
			}

			scn.close();
		}

		catch(IOException e)
		{
			System.out.println("The File was not found. Please make sure that the File is called ClassList.txt and is in the same folder as this program. Then try again");
		}
		while(list.size()>0)
		{
			int index = (int)(Math.random()*list.size());
			chart.add(list.get(index));
			list.remove(index);
		}
		for(Student stu: chart)
		{
			System.out.println(stu);
		}
                Display d = new Display(chart);
                d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                String[][] output = d.chart;
                int longest = d.longest;
                try {
                    FileWriter writer = new FileWriter("chart.txt");
                    PrintWriter out = new PrintWriter(writer);

                    for (int i = 0; i < output.length; i++) {
                        for (int k = 0; k < output[0].length; k++) {
                            out.print(output[i][k]);
                            if(k==7)
                                out.println();
                            else
                                out.print("|");
                        }
                        out.print(d.betweenLines(longest));
                        out.println();
                    }

                    out.close();
                }
                catch (IOException e) {
                    
                }
	}
}
