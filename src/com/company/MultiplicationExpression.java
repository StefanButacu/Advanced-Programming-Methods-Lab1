package com.company;

public class MultiplicationExpression extends ComplexExpression{

    MultiplicationExpression(Operation op, ComplexNumber[] args) {
        super(op, args);
    }

    /**
     *
     * @return - a ComplexNumber which is the result of multiplying all ComplexNumbers from args[] array
     */
    @Override
    public ComplexNumber executeOneOperation() {
        ComplexNumber rez = new ComplexNumber(1,0);
        for(ComplexNumber x: args){
            rez = rez.inmultire(x);
        }
        return rez;
    }
}
