package com.company;

public class ExpressionFactory {

    private Operation op;
    private ComplexNumber[] args;

    /**
     *
     * @param op - enum type Operation
     * @param args - array of ComplexNumbers
     * @return ComplexExpression type depending on the operation
     */
    public ComplexExpression createExpresion(Operation op, ComplexNumber[] args){

        if(op == Operation.ADDITION){
            // do the adition
            return new AddittionExpression(op, args);
        }

        return new AddittionExpression(op, args);
    }
}
