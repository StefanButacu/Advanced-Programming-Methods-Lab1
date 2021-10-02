package com.company;

public class AddittionExpression extends ComplexExpression{


    AddittionExpression(Operation op, ComplexNumber[] arg) {
        super(op, arg);
    }

    @Override
    public ComplexNumber executeOneOperation() {
        ComplexNumber rez =  new ComplexNumber();
        for(ComplexNumber c: args){
            rez = rez.adunare(c);
        }
        return rez;
    }
}
