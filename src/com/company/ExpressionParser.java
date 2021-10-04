package com.company;

public class ExpressionParser {
    private static final ExpressionFactory expFactory = ExpressionFactory.getInstance();



    boolean checkOperator(String[] args){
        String op = args[1];
        // if is a valid operator
        if(!(op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*")))
            return false;
        // if is the same everywhere
        for(int i = 3; i < args.length; i+=2){
            if(!op.equals(args[i]))
                return false;
        }
        return true;
    }

    /**
     *
     * @param s - string matching this pattern [-]digits+/-digits*i
     * @return  ComplexNumber by extracting the real part and imaginary part from string
     */
    ComplexNumber convertStringToComplex(String s){
        int i = 0;
        boolean realPartNegative = false;
        if(s.charAt(i) == '-'){
            realPartNegative = true;
            i++;
        }
        int pow10 = 1;
        while( s.charAt(i) != '+' && s.charAt(i) != '-') {
            pow10 *= 10;
            i++;
        }
        pow10 /= 10;
        if(realPartNegative)
            i = 1;
        else
            i = 0;
        double realPart = 0.0;
        while(s.charAt(i) != '+' && s.charAt(i) != '-'){
            realPart += (s.charAt(i) - '0') * pow10;
            i++;
            pow10 /= 10;
        }
        if(realPartNegative)
            realPart = 0 - realPart;
        boolean imgPartNegative = false;
        double imgPart = 0.0;
        int j = i + 1;
        if(s.charAt(i) == '-')
            imgPartNegative = true;
        i++; // pass the sign of imaginary part
        pow10 = 1;
        while(s.charAt(i) != '*'){
            pow10 *= 10;
            i++;
        }
        pow10 /= 10;
        while(j < s.length() &&  s.charAt(j) != '*'){  // if is not found it means that is +/-1 * i
            imgPart += (s.charAt(j) - '0') * pow10;
            pow10 /= 10;
            j++;
        }
        if( j == s.length()) // i found the *
            imgPart = 1;
        if( imgPartNegative)
            imgPart = 0 - imgPart;
        return new ComplexNumber(realPart, imgPart);
    }

    ComplexNumber[] getComplexNumbers (String[] args){
        ComplexNumber[] complexArgs =  new ComplexNumber[args.length/2+1];
        int nrOfComplexArgs = 0;
        for(int i = 0 ; i < args.length; i+=2){
            complexArgs[nrOfComplexArgs++] = convertStringToComplex(args[i]);
        }
        return complexArgs;
    }

    ComplexExpression getComplexExpression(String[] args) throws Exception {
        if(args.length < 3)
            throw new Exception("Invalid number of args");
        ComplexNumber[] complexArgs = getComplexNumbers(args);

        return switch (args[1]) {
            case "+" -> expFactory.createExpresion(Operation.ADDITION, complexArgs);
            case "-" -> expFactory.createExpresion(Operation.SUBTRACTION, complexArgs);
            case "/" -> expFactory.createExpresion(Operation.DIVISION, complexArgs);
            case "*" -> expFactory.createExpresion(Operation.MULTIPLICATION, complexArgs);
            default -> null;
        };

    }
}
