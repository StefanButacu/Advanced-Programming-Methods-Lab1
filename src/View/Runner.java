package View;

import Model.ComplexNumber;
import Model.ExpressionParser;

public class Runner {

    ExpressionParser p = new ExpressionParser();
    private final String[] complexArguments;
    public Runner(String[] arguments){
        this.complexArguments = arguments;
    }

    public ComplexNumber run() throws Exception {
        return p.getComplexExpression(complexArguments).execute();
    }
}
