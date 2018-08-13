package domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"expressions", "expressionResults"})
@XmlRootElement(name = "simpleCalculator")
public class SimpleCalculator {

    protected SimpleCalculator.Expressions expressions;
    protected SimpleCalculator.ExpressionResults expressionResults;

    public SimpleCalculator.Expressions getExpressions() {
        return expressions;
    }

    public void setExpressions(SimpleCalculator.Expressions value) {
        this.expressions = value;
    }

    public SimpleCalculator.ExpressionResults getExpressionResults() {
        return expressionResults;
    }

    public void setExpressionResults(SimpleCalculator.ExpressionResults value) {
        this.expressionResults = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"expressionResult"})
    public static class ExpressionResults {

        @XmlElement(required = true)
        protected List<SimpleCalculator.ExpressionResults.ExpressionResult> expressionResult;

        public List<SimpleCalculator.ExpressionResults.ExpressionResult> getExpressionResult() {
            if (expressionResult == null) {
                expressionResult = new ArrayList<>();
            }
            return this.expressionResult;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(propOrder = {"result"})
        public static class ExpressionResult {

            protected Double result;

            public Double getResult() {
                return result;
            }

            public void setResult(Double value) {
                this.result = value;
            }

        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"expression"})
    public static class Expressions {

        @XmlElement(required = true)
        protected List<SimpleCalculator.Expressions.Expression> expression;

        public List<SimpleCalculator.Expressions.Expression> getExpression() {
            if (expression == null) {
                expression = new ArrayList<>();
            }
            return this.expression;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(propOrder = {"operation"})
        public static class Expression {

            @XmlElement(required = true)
            protected Term operation;

            public Term getOperation() {
                return operation;
            }

            public void setOperation(Term value) {
                this.operation = value;
            }

        }

    }

}
