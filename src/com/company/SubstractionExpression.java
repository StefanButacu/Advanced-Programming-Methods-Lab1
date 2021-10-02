package com.company;

public class SubstractionExpression extends ComplexExpression{
    SubstractionExpression(Operation op, ComplexNumber[] arg) {
        super(op, arg);
    }

    @Override
    public ComplexNumber executeOneOperation() {
        ComplexNumber rez =  new ComplexNumber();
        for(ComplexNumber c: args){
            rez = rez.scadere(c);
        }
        return rez;
    }
}