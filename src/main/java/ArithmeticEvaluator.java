import java.util.Stack;

public class ArithmeticEvaluator {

    private static final double EPSILON = 1e-15;
    private static boolean deg = false;
    /**
     * Calculates the the Post Fix expression (Reverse polish Notation)
     * @param expression A mathematical expression
     * @return the result of the expression
     * @throws Exception if and Operand is missing, divide by zero etc...
     */
    public static String evaluate(String expression) throws Exception {
        String[] postFix = ArithmeticParser.toPostFix(expression);
        Stack<String> stack = new Stack<>();
        for (String s : postFix) {
            if (ArithmeticParser.isOperand(s)) {//if the current token is a number push it to the stack
                stack.push(s);
            } else {//if token is an operator pop the stack according to the operators arity (Binary or Unary)
                if (ArithmeticParser.isBinary(s) == ArithmeticParser.BINARY) {
                    if (stack.size() < 2) throw new EvaluatorException("Missing operand");
                    Double b = Double.parseDouble(stack.pop());
                    Double a = Double.parseDouble(stack.pop());
                    stack.push(String.valueOf(BinaryEval(a, b, s)));
                } else {
                    if (stack.size() < 1) throw new EvaluatorException("Missing operand");
                    Double a = Double.parseDouble(stack.pop());
                    stack.push(String.valueOf(UnaryEval(a, s)));
                }
            }
        }
        Double result = Double.parseDouble(stack.pop());
        return String.valueOf(evalError(result));
    }

    private static Double BinaryEval(double a,double b,String op) throws EvaluatorException{
        if(op.equals("+"))
            return (a+b);
        if(op.equals("-")) {
            return (a - b);
        }
        if(op.equals("*"))
            return (a*b);
        if(op.equals("/")) {
            if (b == 0) throw new EvaluatorException("Divide by 0");
            return (a / b);
        }
        if(op.equals("^"))
            return Math.pow(a,b);

        throw new IllegalArgumentException("Operation not found");//if non of the above where executed throw this
    }
    private static Double UnaryEval(double a,String op) throws EvaluatorException {
        if(op.equals("$"))
            return -a;

        if(op.equals("sqrt"))
            return Math.sqrt(a);

        if(op.equals("%"))
            return (a/100);
        return 0d;
    }

    private static Double evalError(Double number){
        if(Math.abs(Math.floor(number) - number) < EPSILON)//Error degree
            return Math.floor(number);
        return number;
    }

    /**
     * Checks if the number is an integer number
     * @param number a number
     * @return true if number is an integer or false if it is a float
     */
    private static boolean isInt(double number){
        return (number == Math.floor(number) && !Double.isInfinite(number));
    }

    public static void setDeg(boolean deg) {
        ArithmeticEvaluator.deg = deg;
    }

    public static class EvaluatorException extends Exception{
        @Override
        public StackTraceElement[] getStackTrace() {
            return super.getStackTrace();
        }

        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }

        public EvaluatorException(String message){
            super(message);
        }
    }
}
