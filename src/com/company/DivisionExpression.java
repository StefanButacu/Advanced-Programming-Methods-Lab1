package com.company;

public class DivisionExpression extends ComplexExpression{

    DivisionExpression(Operation op, ComplexNumber[] args) {
        super(op, args);
    }

    /**
     *
     * @return - a complex number equal to the divivision of all elements from ComplexNumber[] arg
     */
    @Override
    public ComplexNumber executeOneOperation() {
        return null;
    }
}
