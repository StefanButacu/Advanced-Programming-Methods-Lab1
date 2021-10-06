package Model;

public class ExpressionParser {
    private static final ExpressionFactory expFactory = ExpressionFactory.getInstance();



    public boolean checkOperator(String[] args){
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
     * @param s - string
     * @return - double = the real part of the complex number
     * @throws Exception - if the real part contains invalid character
     */
    public double convertStringToDouble(String s) throws Exception{
        int i = 0;
        boolean isNegative = false;

        if(s.charAt(i) == '-'){
            isNegative = true;
            i++;
        }
        else if(s.charAt(i) == '+'){
            // remove the + sign
            s = s.substring(1);
        }
        // compute the 10 power
        double pow10 = 1;
        while(i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '.' && s.charAt(i) != '*') {
            pow10 *= 10;
            i++;
        }
        pow10 /= 10;
        if(isNegative)
            i = 1;
        else
            i = 0;
        double result = 0.0;
        while(i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*'){
            if(s.charAt(i) == 'i'){
                result = 1;
                break;
            }
            if(s.charAt(i) == '.')
               i++;
            int digit = s.charAt(i) - '0';
            if(! (0 <= digit && digit <= 9))
                throw new Exception("Invalid number");

            result += digit * pow10;
            i++;
            pow10 /= 10;
        }
        if(isNegative)
            result = 0 - result;

        return result;
    }

    /**
     *
     * @param s - string matching this pattern [-]digits+/-digits*i
     * @return  ComplexNumber by extracting the real part and imaginary part from string
     */
    public ComplexNumber convertStringToComplex(String s) throws Exception {
        // realPartFunction() ... imgPartFunction() ...
        double realPart = convertStringToDouble(s);
        int i = 0;
        if(s.charAt(i) == '-')
            i++;
        while(s.charAt(i) != '+' && s.charAt(i) != '-')
            i++;
        s = s.substring(i);
        double imgPart = convertStringToDouble(s);


        return new ComplexNumber(realPart, imgPart);
    }

    public ComplexNumber[] getComplexNumbers(String[] args) throws Exception {
        ComplexNumber[] complexArgs =  new ComplexNumber[args.length/2+1];
        int nrOfComplexArgs = 0;
        for(int i = 0 ; i < args.length; i+=2){
            complexArgs[nrOfComplexArgs++] = convertStringToComplex(args[i]);
        }
        return complexArgs;
    }

    public ComplexExpression getComplexExpression(String[] args) throws Exception {
        if(args.length < 3 || args.length % 2 == 0)
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
