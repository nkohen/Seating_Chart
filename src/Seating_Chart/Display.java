/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seating_Chart;

import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JTextArea;
import java.awt.Font;
/**
 *
 * @author Nadav
 */
public class Display extends JFrame {

    ArrayList<Student> list;
    String[][] chart;
    int longest;
    
    public Display(ArrayList<Student> list)
    {
        super();
        this.list = list;
        JTextArea j = new JTextArea();
        getContentPane().add(j);
        j.setFont(new Font("Monospaced", Font.PLAIN, 10));
        longest = longestName();
        j.setText("Seating Chart:\n");
        
        chart = new String[5][8];
        for(int i = 0; i<chart.length; i++)
        {
            for(int k = 0; k<chart[0].length; k++)
            {
                chart[i][k] = "   " + addSpaces(longest, new Student("","")) + "   ";
            }
        }
        int front = 4 + 4*chart[0][0].length();
        String spaces = "";
        for(int i =0; i<front; i++)
            spaces = spaces + " ";
        j.append(spaces + "    Front of Room\n");
        
        chart[0][0] = "   " + addSpaces(longest, new Student("X","")) + "   ";
        chart[1][0] = "   " + addSpaces(longest, new Student("X","")) + "   ";
        chart[0][4] = "   " + addSpaces(longest, new Student("A","")) + "   ";
        chart[1][4] = "   " + addSpaces(longest, new Student("i","")) + "   ";
        chart[2][4] = "   " + addSpaces(longest, new Student("s","")) + "   ";
        chart[3][4] = "   " + addSpaces(longest, new Student("l","")) + "   ";
        chart[4][4] = "   " + addSpaces(longest, new Student("e","")) + "   ";
        //chart[5][4] = "   " + addSpaces(longest, new Student(".","")) + "   ";
        
        int index = 0;
        for(int i = 0; i<chart.length; i++)
        {
            for(int k = 1; k<chart[0].length; k++)
            {
                if(chart[i][k].equals("   " + addSpaces(longest, new Student("","")) + "   "))
                {
                    chart[i][k] = "   " + addSpaces(longest,list.get(index)) + "   ";
                    index++;
                }
                if(index>=list.size())
                    break;
            }
            if(index>=list.size())
                break;
        }
        
        while(index<list.size())
        {
            try
            {
                chart[index - (chart[0].length-2)*chart.length+2][0] = "   " + addSpaces(longest,list.get(index)) + "   ";
                index++;
            }
            catch(Exception e)
            {
                System.out.println(e);
                j.append("\nCLASS SIZE TOO LARGE\n\n");
                break;
            }
        }
        for(int i = 0; i<chart.length; i++)
        {
            String temp = chart[i][1];
            chart[i][1] = chart[i][3];
            chart[i][3] = temp;
        }
        
        for(int i = 0; i<chart.length; i++)
        {
            for(int k = 0; k<chart[0].length; k++)
            {
                j.append(chart[i][k] + ((k==7)?"\n":"|"));
            }
            j.append(betweenLines(longest) + "\n");
        }
        setSize(1150,300);
        setVisible(true);
    }
    
    int longestName()
    {
        int c = 0;
        for(Student s: list)
        {
            if(s.length()>c)
                c = s.length();
        }
        return c;
    }
    
    String addSpaces(int x, Student s)
    {
        String ans = s.toString();
        for(int i = 0; i<(x-s.length())/2; i++)
            ans = " " + ans + " ";
        if(ans.length() < x)
            ans += " ";
        return ans;
    }
    
    String betweenLines(int x)
    {
        String thing = "   " + addSpaces(x, new Student("","")) + "   ";
        int length = thing.length();
        thing = "";
        for(int i = 0; i<=length; i++)
            thing = thing + "-";
        for(int i = 0; i<3; i++)
            thing = thing + thing;
        
        return thing;
    }
}
