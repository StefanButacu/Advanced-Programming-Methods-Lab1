package Model;

public abstract class ComplexExpression {
    Operation op;
    ComplexNumber[] args;


    ComplexExpression(Operation op, ComplexNumber[] args){
        this.op = op;
        this.args = args;

    }
    public ComplexNumber execute() throws Exception {
        return executeOneOperation();


    }

    public abstract ComplexNumber executeOneOperation() throws Exception;


}
