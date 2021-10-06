package Model;

public class AddittionExpression extends ComplexExpression{


    public AddittionExpression(Operation op, ComplexNumber[] arg) {
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
