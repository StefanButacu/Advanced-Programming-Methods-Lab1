package com.company;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
       /* int[] arr = new int[] {10, -1, 50, 21, 21 , 14, 1521};
        if(arr.length == 0)
            return;

        int min = arr[0];
        int max = arr[0];
        for(int x: arr){
            if( x < min)
                min = x;
            if( x > max)
                max = x;
        }
        System.out.println("Min = " + min);
        System.out.println("Max = "+ max);
*/
        //   System.out.println(Arrays.toString("10+25*i".split("[\\+\\*-]")));


        ComplexNumber rez = new ComplexNumber(0, 0);
        rez = rez.adunare(new ComplexNumber(2, 3));
        System.out.println(rez);
        rez = rez.scadere(new ComplexNumber(-1, 2));
        System.out.println(rez);
        rez = rez.inmultire(new ComplexNumber(2, 2));
        System.out.println(rez);   // (a+bi) * (c+di) = ac + (a*d) i + (b*c) i - d*b;
        rez = rez.impartire(new ComplexNumber(1, -1));
        System.out.println(rez);


    }
}
