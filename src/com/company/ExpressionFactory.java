package com.company;

public class ExpressionFactory {

    private static final ExpressionFactory instance = new ExpressionFactory();

    private ExpressionFactory(){} // private constructor => force the user to not instantiate this king of object anywhere outside this class

    static ExpressionFactory getInstance(){
        return instance;

    }
    /**
     *
     * @param op - enum type Operation
     * @param args - array of ComplexNumbers
     * @return ComplexExpression type depending on the operation
     */
    public ComplexExpression createExpresion(Operation op, ComplexNumber[] args){

        return switch (op) {
            case ADDITION -> new AddittionExpression(op, args);
            case SUBTRACTION -> new SubtractionExpression(op, args);
            case DIVISION -> new DivisionExpression(op, args);
            case MULTIPLICATION -> new MultiplicationExpression(op, args);
        };

    }
}
