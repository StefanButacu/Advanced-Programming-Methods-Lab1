package com.company;

public class Test {


    public static void testAll() throws Exception {
        System.out.println("Start Basic operations test...");
        testComplexNumber();
        System.out.println("End Basic operations test");
        System.out.println("Start Complex Expression test...");
        testComplexExpression();
        testExpressionFactory();
    }

    private static void testExpressionFactory() {
        ExpressionParser p = new ExpressionParser();
        // test for operator
        String[] args = new String[]{"1+3i", "+","2+5i", "+","1+i"};
        assert(p.checkOperator(args));
        args = new String[]{"1+3i", "-", "1+51i", "*", "1+421i"};
        assert(!p.checkOperator(args));
        args = new String[]{"1+3i", "ab", "1+51i", "*", "1+421i"};
        assert(!p.checkOperator(args));
        testStringToComplex();

    }

    private static void testStringToComplex() {

        ExpressionParser p = new ExpressionParser();
        ComplexNumber r =  p.convertStringToComplex("124+12*i");
        assert(r.getRe() == 124);
        assert(r.getIm() == 12);
        r =  p.convertStringToComplex("-1+12*i");
        assert(r.getRe() == -1);
        assert(r.getIm() == 12);
        r =  p.convertStringToComplex("-1-12*i");
        assert(r.getRe() == -1);
        assert(r.getIm() == -12);

    }

    private static void testComplexExpression() throws Exception {
        System.out.println("Start Addition Expression test...");
        testAdditionExpression();
        System.out.println("End Addition Expression test");
        System.out.println("Start Subtraction Expression test...");
        testSubtractionExpression();
        System.out.println("End Subtraction Expression test");
        System.out.println("Start Multiplication Expression test...");
        testMultiplicationExpression();
        System.out.println("End Multiplication Expression test");
        System.out.println("Start Division Expression test...");
        testDivisionExpression();
        System.out.println("End Division Expression test");

    }

    private static void testAdditionExpression() throws Exception {
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(-1,-1), new ComplexNumber(0,3)};
         ComplexExpression calculator = new AddittionExpression(Operation.ADDITION, arg);
        assert(calculator.execute().getRe() == 2);
        assert(calculator.execute().getIm() == 5 );
    }

    private static void testSubtractionExpression() throws Exception {
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(-1,-1), new ComplexNumber(0,3)};
        ComplexExpression calculator = new SubtractionExpression(Operation.SUBTRACTION, arg);
        assert(calculator.execute().getRe() == 0);
        assert(calculator.execute().getIm() == -3);
    }

    private static void testMultiplicationExpression() throws Exception {
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(-1,-1), new ComplexNumber(0,3)};
        ComplexExpression calculator = new MultiplicationExpression(Operation.MULTIPLICATION, arg);
        ComplexNumber rez =  calculator.execute();
        assert(rez.getRe() == 12);
        assert(rez.getIm() == 12);
        ComplexNumber[] arg2 = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(0,0)};
        ComplexExpression calculator2 = new MultiplicationExpression(Operation.MULTIPLICATION, arg2);
        ComplexNumber rez2 =  calculator2.execute();
        assert(rez2.getRe() == 0);
        assert(rez2.getIm() == 0);
    }

    private static void testDivisionExpression() throws Exception {
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(2,-1), new ComplexNumber(0,3)};
        ComplexExpression calculator = new DivisionExpression(Operation.DIVISION, arg);
        ComplexNumber rez =  calculator.execute();
        assert(rez.getRe() - 0.0833 <= 0.1);
        assert(rez.getIm() - 0.0833 <= 0.1);
        // try catch 0 division
        ComplexNumber[] arg2 = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(0,0)};
        ComplexExpression calculator2 = new DivisionExpression(Operation.DIVISION, arg2);

        try{
            ComplexNumber rez2 =  calculator2.execute();
        }
        catch (Exception e){
            assert(e.getMessage().equals("Division by 0"));

        }
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
        // 1 + 0 * i =  neutral element
        rez = rez.impartire(new ComplexNumber(1,0));
        assert(rez.getRe() == 6);
        assert(rez.getIm() == 2);

        rez = rez.inmultire(new ComplexNumber(0,0));
        assert(rez.getRe() == 0);
        assert(rez.getIm() == 0);

    }
}
