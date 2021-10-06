import Model.ExpressionParser;
import View.Runner;

public class Main {

    public static void main(String[] args) throws Exception {

        Test.testAll();
        Runner solver = new Runner(args);
        try{
            System.out.println(solver.run());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
