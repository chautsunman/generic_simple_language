package org.example;

import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class SimpleLangEvalVisitorTest extends TestCase {
    public void testBasicMath() {
        assertEquals(3.0, evalExpression("1 + 2").getNum(), UnitTestUtils.EPSILON);
        assertEquals(-1.0, evalExpression("1 - 2").getNum(), UnitTestUtils.EPSILON);
        assertEquals(2.0, evalExpression("1 * 2").getNum(), UnitTestUtils.EPSILON);
        assertEquals(0.5, evalExpression("1 / 2").getNum(), UnitTestUtils.EPSILON);
    }

    public void testFuncName() {
//        assertEquals("foo", evalExpression("foo(1, 2)").getFuncName());
    }

    private EvalRes evalExpression(String expressionStr) {
        final calculatorLexer lexer = new calculatorLexer(CharStreams.fromString(expressionStr));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final calculatorParser parser = new calculatorParser(tokens);
        final ParseTree tree = parser.expression();
        final SimpleLangEvalVisitor evalVisitor = new SimpleLangEvalVisitor();
        return evalVisitor.visit(tree);
    }
}
