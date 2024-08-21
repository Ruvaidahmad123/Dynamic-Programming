class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : expression.toCharArray()) {
            if (ch == ',') {
                continue;
            } else if (ch != ')') {
                stack.push(ch);
            } else {
                // We encountered a ')', so we need to evaluate the expression inside the parentheses.
                Stack<Character> subExpr = new Stack<>();
                while (stack.peek() != '(') {
                    subExpr.push(stack.pop());
                }
                stack.pop(); // pop the '('
                char operator = stack.pop();
                boolean result = evaluateSubExpression(operator, subExpr);
                stack.push(result ? 't' : 'f');
            }
        }
        
        return stack.pop() == 't';
    }
    
    private boolean evaluateSubExpression(char operator, Stack<Character> subExpr) {
        boolean result = (operator == '&') ? true : (operator == '|') ? false : false;
        
        while (!subExpr.isEmpty()) {
            char ch = subExpr.pop();
            boolean value = (ch == 't');
            
            if (operator == '&') {
                result &= value;
            } else if (operator == '|') {
                result |= value;
            } else if (operator == '!') {
                result = !value;
            }
        }
        
        return result;
    }
}
