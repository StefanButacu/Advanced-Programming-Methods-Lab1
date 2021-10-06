package Model;

public class SubtractionExpression extends ComplexExpression{
    public SubtractionExpression(Operation op, ComplexNumber[] arg) {
        super(op, arg);
    }

    @Override
    public ComplexNumber executeOneOperation() {
        ComplexNumber rez =  new ComplexNumber(args[0].getRe(), args[0].getIm());

        for(int i = 1; i < args.length; i++){
            rez = rez.scadere(args[i]);
        }
        return rez;
    }
}