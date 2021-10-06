package Model;

public class ExpressionFactory {

    private static final ExpressionFactory instance = new ExpressionFactory();

    private ExpressionFactory(){} // private constructor => force the user to not instantiate this king of object anywhere outside this class

    static ExpressionFactory getInstance(){
        return instance;

    }
    /**
     *
     * @param op - enum type Operation
     * @param complexNumbersList - array of ComplexNumbers
     * @return ComplexExpression type depending on the operation
     */
    public ComplexExpression createExpresion(Operation op, ComplexNumber[] complexNumbersList){

        return switch (op) {
            case ADDITION -> new AddittionExpression(op, complexNumbersList);
            case SUBTRACTION -> new SubtractionExpression(op, complexNumbersList);
            case DIVISION -> new DivisionExpression(op, complexNumbersList);
            case MULTIPLICATION -> new MultiplicationExpression(op, complexNumbersList);
        };

    }
}
