package com.company;

public class Test {

    public static void testAll() throws Exception {
        System.out.println("Start Basic operations test...");
        testComplexNumber();
        System.out.println("End Basic operations test");
        System.out.println("Start Complex Expression test...");
        testComplexExpression();
    }

    private static void testComplexExpression() {
        System.out.println("Start Addition Expression test...");
        testAdditionExpression();
        System.out.println("End Addition Expression test");
        System.out.println("Start Substraction Expression test...");
        testSubtractionExpression();
        System.out.println("End Substraction Expression test");


    }

    private static void testAdditionExpression() {
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(-1,-1), new ComplexNumber(0,3)};
         ComplexExpression calculator = new AddittionExpression(Operation.ADDITION, arg);
        assert(calculator.execute().getRe() == 2);
        assert(calculator.execute().getIm() == 5 );
    }

    private static void testSubtractionExpression() {
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(-1,-1), new ComplexNumber(0,3)};
        ComplexExpression calculator = new SubtractionExpression(Operation.SUBTRACTION, arg);
        assert(calculator.execute().getRe() == 0);
        assert(calculator.execute().getIm() == -3);
    }


    private static void testComplexNumber() throws Exception{

        ComplexNumber rez = new ComplexNumber();
        rez = rez.adunare(new ComplexNumber(2, 3));
        assert(rez.getRe() - 2.0 <= 0.1);
        assert(rez.getIm() - 3.0 <= 0.1);

        rez = rez.scadere(new ComplexNumber(-1, 2));
        assert(rez.getRe() == 3);
        assert(rez.getIm() == 1);

        rez = rez.inmultire(new ComplexNumber(2, -2));
        assert(rez.getRe() == 8);
        assert(rez.getIm() == -4);

        // (a+bi) * (c+di) = ac + (a*d) i + (b*c) i - d*b;
        rez = rez.impartire(new ComplexNumber(1, -1));
        assert(rez.getRe() == 6);
        assert(rez.getIm() == 2);
        try{
            rez.impartire(new ComplexNumber(0,0));
        }
        catch (Exception e){
            assert(e.getMessage().equals("Division by 0"));
        }
    }
}
