package recursivedescent;

import static recursivedescent.RdpCharacterHelper.isCharacter;
import static recursivedescent.RdpCharacterHelper.isNumber;

/**
 * Created by ram on 10/7/17.
 */
public class RdpCalculator {
    private int currentPosition = -1, currentChar = -1;
    private String mainExpression;
    private String invalidText="";

    public String evaluate(String expression) throws RuntimeException{
        if(expression==null || expression.trim().length() == 0)
            throw new RuntimeException("Wrong mathematical expression. your expression is null or empty");
        expression = expression.toLowerCase();
        expression = expression.replace(" ","");
        mainExpression = expression;
        currentPosition = 0;
        currentChar = mainExpression.length()>0 ? mainExpression.charAt(0) : -1;
        return Double.toString(parseExpression());
    }

    private void nextChar() {
        currentPosition++;
        if(currentPosition < mainExpression.length()) {
            currentChar = mainExpression.charAt(currentPosition);
        } else {
            currentChar = -1;
        }
    }

    // Grammar:
    // expression = term | expression `+` term | expression `-` term
    // term = factor | term `*` factor | term `/` factor
    // factor = `+` factor | `-` factor | `(` expression `)`
    //        | number | functionName factor

    private double parseExpression() {
        double value = parseTerm();
        while(true) {
            if      (goToNextIfCurrentIndexIsEqual('+')) value += parseTerm();
            else if (goToNextIfCurrentIndexIsEqual('-')) value -= parseTerm();
            else if (isCharacter((char)currentChar)) value = parseTerm();
            else return value;
        }
    }

    private double parseTerm() {
        double value = parseFactor();
        while (true){
            if      (goToNextIfCurrentIndexIsEqual('*')) value *= parseFactor();
            else if (goToNextIfCurrentIndexIsEqual('/')) value /= parseFactor();
            else return value;
        }
    }

    private double parseFactor() {
        if (goToNextIfCurrentIndexIsEqual('+')) return parseFactor();
        if (goToNextIfCurrentIndexIsEqual('-')) return -parseFactor();
        double value;
        int startPos = currentPosition;
        int endPos = currentPosition;
        if (goToNextIfCurrentIndexIsEqual('(')) {
            value = parseExpression();
            goToNextIfCurrentIndexIsEqual(')');
        } else if (isNumber((char)currentChar)) {
            while (isNumber((char)currentChar)) {
                endPos++;
                nextChar();
            }
            value = Integer.parseInt(mainExpression.substring(startPos, endPos));
        } else if (isCharacter((char)currentChar)) {
            while (isCharacter((char)currentChar)) {
                invalidText+=(char)currentChar;
                endPos++;
                nextChar();
            }
            String func = mainExpression.substring(startPos, endPos);
            value = parseFactor();
            if (func.equals("sqrt")) value = Math.sqrt(value);
            else throw new RuntimeException("Unknown function: " + func);
        } else {
            throw new RuntimeException("Unexpected: " + invalidText);
        }

        return value;
    }


    private boolean goToNextIfCurrentIndexIsEqual(char c){
        if(currentChar == c){
            nextChar();
            return true;
        }
        return false;
    }
}
