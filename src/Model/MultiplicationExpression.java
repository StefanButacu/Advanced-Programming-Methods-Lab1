package Model;

public class MultiplicationExpression extends ComplexExpression{

    public MultiplicationExpression(Operation op, ComplexNumber[] args) {
        super(op, args);
    }

    /**
     *
     * @return - a ComplexNumber which is the result of multiplying all ComplexNumbers from args[] array
     */
    @Override
    public ComplexNumber executeOneOperation() {
        // what happens if the args is empty??? => in parser
        ComplexNumber rez = new ComplexNumber(1,0);
        for(ComplexNumber x: args){
            rez = rez.inmultire(x);
        }
        return rez;
    }
}
