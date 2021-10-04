package com.company;

public class DivisionExpression extends ComplexExpression{

    DivisionExpression(Operation op, ComplexNumber[] args) {
        super(op, args);
    }

    /**
     *
     * @return - a complex number equal to the division of all elements from ComplexNumber[] args
     * @throws Exception - if one of the args is 0 value
     */
    @Override
    public ComplexNumber executeOneOperation() throws Exception {
        ComplexNumber rez = args[0];
        for(int i = 1; i < args.length; i++){
            rez = rez.impartire(args[i]);
        }
        return rez;
    }
}
