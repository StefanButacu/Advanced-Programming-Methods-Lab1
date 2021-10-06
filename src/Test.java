import Model.*;

public class Test {


    public static void testAll() throws Exception {
        testComplexNumber();
        testComplexExpression();
        testExpressionParser();
        testExecution();
    }

    private static void testExecution() throws Exception {
        ExpressionParser p = new ExpressionParser();
        // substraction
        String[] args = new String[]{"1+1*i","-", "2+2*i", "-", "-1-1*i","-", "0-3*i" };
        ComplexNumber rez =  p.getComplexExpression(args).execute();
        assert(rez.getRe() == 0);
        assert(rez.getIm() == 3);
        // adition
        args = new String[] { "1+1*i", "+", "2+2*i", "+", "-1-1*i", "+", "0+3*i"};
        rez =  p.getComplexExpression(args).execute();
        assert(rez.getRe() == 2);
        assert(rez.getIm() == 5 );
        // multiply
        args = new String[] { "1+1*i", "*", "2+2*i", "*", "-1-1*i", "*", "0+3*i"};
        rez =  p.getComplexExpression(args).execute();
        assert(rez.getRe() == 12);
        assert(rez.getIm() == 12 );
        // * 0
        args = new String[] { "1+1*i", "*", "2+2*i", "*", "0+0*i", "*", "0+3*i"};
        rez =  p.getComplexExpression(args).execute();
        assert(rez.getRe() == 0);
        assert(rez.getIm() == 0);

        // division

        args = new String[] { "1+1*i", "*", "2+2*i", "*", "0+0*i", "*", "0+3*i"};
        rez =  p.getComplexExpression(args).execute();
        assert(rez.getRe() - 0.0833 <= 0.1);
        assert(rez.getIm() - 0.0833 <= 0.1);


    }


    private static void testInvalidExpression() throws Exception {
        ExpressionParser p = new ExpressionParser();
        String[] args = new String[]{"1+3i", "-", "1+51i", "*", "1+421i"};
        assert(!p.checkOperator(args));
        args = new String[]{"1+3*i", "ab", "1+51i", "*", "1+421*i"};
        assert(!p.checkOperator(args));
        args = new String[]{"1+21saf*i", "*", "21+fsa+" ,"*", "21"};
        try{
            p.getComplexExpression(args);
        } catch (Exception e) {
            assert(e.getMessage().equals("Invalid number"));
        }
        args = new String[]{"1+2*i", "*", "21-i" ,"*"};
        try{
            p.getComplexExpression(args);
        } catch (Exception e) {
            assert(e.getMessage().equals("Invalid number of args"));
        }
        args = new String[]{"2+3*i", "+",  "5-6*i", "+", "-2-i"};
        p.getComplexExpression(args);

    }
    private static void testExpressionParser() throws Exception {
        ExpressionParser p = new ExpressionParser();

        // test for operator
        String[] args = new String[]{"1+3i", "+","2+5i", "+","1+i"};
        assert(p.checkOperator(args));

        testStringToComplex();
        args = new String[]{"2+3*i", "+", "5-6*i", "+", "-2+1*i"};
        ComplexNumber[] complexArgs = p.getComplexNumbers(args);
        assert(complexArgs[0].getRe() == 2);
        assert(complexArgs[0].getIm() == 3);
        assert(complexArgs[2].getRe() == -2);
        assert(complexArgs[2].getIm() == 1);
        // test for invalid nr of args
        testInvalidExpression();

        // test for good calculation
        testGoodCalculation();

    }
    private static void testAdd() throws Exception{
        ExpressionParser p = new ExpressionParser();
        String[] args = new String[]{"2+3*i", "+",  "5-6*i", "+", "-2-i"};
        ComplexNumber rez = p.getComplexExpression(args).execute();
        assert(Math.abs(rez.getRe() - 5) <= 0.1);
        assert(Math.abs(rez.getIm() - (-4)) <= 0.1);


    }  private static void testSub() throws Exception{
        ExpressionParser p = new ExpressionParser();
        String[] args = new String[]{"2+3*i", "-",  "5-6*i", "-", "-2-i"};
        ComplexNumber rez = p.getComplexExpression(args).execute();
        assert(Math.abs(rez.getRe() - (-1)) <= 0.1);
        assert(Math.abs(rez.getIm() - 10) <= 0.1);

    }
    private static void testMul() throws Exception{
        ExpressionParser p = new ExpressionParser();
        String[] args = new String[]{"2+3*i", "*",  "5-6*i", "*", "-2-i"};
        ComplexNumber rez = p.getComplexExpression(args).execute();
        assert(Math.abs(rez.getRe() - (-53)) <= 0.1);
        assert(Math.abs(rez.getIm() - (-34)) <= 0.1);
    }
    private static void testDiv() throws Exception{
        ExpressionParser p = new ExpressionParser();                // 0 - is a must
        String[] args = new String[]{"1+i", "/",  "2+2*i", "/", "2-1*i", "/", "0+3*i"};
        ComplexNumber rez = p.getComplexExpression(args).execute();
        assert(Math.abs(rez.getRe() - 0.033) <= 0.1);
        assert(Math.abs(rez.getIm() - (-0.066)) <= 0.1);



    }
    private static void testGoodCalculation() throws Exception{
        testAdd();
        testSub();
        testMul();
        testDiv();



    }
    private static void testStringToRealPart() throws Exception {
        ExpressionParser p = new ExpressionParser();
        double realPart = p.convertStringToDouble("123.412+3*i");
        assert (realPart - 123.412 <= 0.1);

        realPart = p.convertStringToDouble("-123.412+3*i");
        assert (realPart - (-123.412) <= 0.1);

        try{
            p.convertStringToDouble("1saf.421+2*i");
        }
        catch (Exception e){
            assert(e.getMessage().equals("Invalid number"));

        }
    }
    private static void testStringToComplex() throws Exception {
        ExpressionParser p = new ExpressionParser();
        testStringToRealPart();


        ComplexNumber r =  p.convertStringToComplex("124+12*i");
        assert(r.getRe() == 124);
        assert(Math.abs(r.getIm() - 12) <= 0.1);
        r =  p.convertStringToComplex("-1+12*i");
        assert(r.getRe() == -1);

        assert(r.getIm() - 12 <= 0.1);
        r =  p.convertStringToComplex("-1-i");
        assert(r.getRe() == -1);
        assert(r.getIm() == -1);

        r = p.convertStringToComplex("1+i");
        assert(r.getRe() == 1);
        assert(r.getIm() == 1);


        r = p.convertStringToComplex("-1+i");
        assert(r.getRe() == -1);
        assert(r.getIm() == 1);


        r = p.convertStringToComplex("-1-i");
        assert(r.getRe() == -1);
        assert(r.getIm() == -1);

        r = p.convertStringToComplex("5-6*i");
        assert(Math.abs(r.getRe() - 5) <= 0.1);
        assert(Math.abs(r.getIm() - (-6)) <= 0.1);

    }

    private static void testComplexExpression() throws Exception {
        testAdditionExpression();
        testSubtractionExpression();
        testMultiplicationExpression();
        testDivisionExpression();

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
     }


    private static void testDivisionExpression1() throws Exception{
        ComplexNumber[] arg = new ComplexNumber[] { new ComplexNumber(1,1), new ComplexNumber(2,2), new ComplexNumber(2,-1), new ComplexNumber(0,3)};
        ComplexExpression calculator = new DivisionExpression(Operation.DIVISION, arg);
        ComplexNumber rez =  calculator.execute();
        assert(rez.getRe() - 0.0833 <= 0.1);
        assert(rez.getIm() - 0.0833 <= 0.1);
    }

    private static void testDivisionExpression2(){
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

    private static void testDivisionExpression() throws Exception {
        testDivisionExpression1();
        testDivisionExpression2();
    }

    private static void testComplexNumberAdd(){
        ComplexNumber rez = new ComplexNumber();
        rez = rez.adunare(new ComplexNumber(2, 3));
        assert(rez.getRe() - 2.0 <= 0.1);
        assert(rez.getIm() - 3.0 <= 0.1);

    }
    private static void testComplexNumberSub() {
        ComplexNumber rez = new ComplexNumber(2,3);
        rez = rez.scadere(new ComplexNumber(-1, 2));
        assert(rez.getRe() == 3);
        assert(rez.getIm() == 1);

    }
    private static void testComplexNumberMultiply() {
        // (a+bi) * (c+di) = ac + (a*d) i + (b*c) i - d*b;

        ComplexNumber rez = new ComplexNumber(3,1);
        rez = rez.inmultire(new ComplexNumber(2, -2));
        assert(rez.getRe() == 8);
        assert(rez.getIm() == -4);

        rez = rez.inmultire(new ComplexNumber(0,0));
        assert(rez.getRe() == 0);
        assert(rez.getIm() == 0);
    }

    private static void testComplexNumberDiv() throws Exception {
        ComplexNumber rez = new ComplexNumber(8,-4);
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


    }

    private static void testComplexNumber() throws Exception{
        //  teste separate

        testComplexNumberAdd();
        testComplexNumberSub();
        testComplexNumberMultiply();
        testComplexNumberDiv();

    }


}
