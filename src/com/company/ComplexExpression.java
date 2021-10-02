package com.company;

public abstract class ComplexExpression {
    Operation op;
    ComplexNumber[] args;


    ComplexExpression(Operation op, ComplexNumber[] args){
        this.op = op;
        this.args = args;

    }
    public ComplexNumber execute() {
        return new ComplexNumber();


    }

    public abstract ComplexNumber executeOneOperation();


}
