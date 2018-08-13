package utils;

import domain.Term;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculatorService {

    static BigDecimal calculation(Term outer) {
        if (!outer.getArg().isEmpty()) {
            return calc(outer.getArg().get(0), outer.getArg().get(1), outer.getOperationType());
        }
        if (outer.getArg1() != null) {
            return calc(outer.getArg1(), outer.getOperation1(), outer.getOperationType());
        }
        if (outer.getArg2() != null) {
            return calc(outer.getArg2(), outer.getOperation2(), outer.getOperationType());
        }
        if (!outer.getOperation().isEmpty()) {
            return calc(outer.getOperation(), outer.getOperationType());
        }
        return new BigDecimal("0.0");
    }

    private static BigDecimal calc(BigDecimal a, BigDecimal b, String operationType) {
        return operator(operationType, a, b);
    }
    private static BigDecimal calc(BigDecimal arg, Term operation, String operationType) {
        return operator(operationType, arg, calculation(operation));
    }
    private static BigDecimal calc(List<Term> operation, String operationType) {
        return operator(operationType, calculation(operation.get(0)), calculation(operation.get(1)));
    }

    private static BigDecimal operator(String type, BigDecimal v1, BigDecimal v2) {
        if (type.equals("SUB")) {
            return sub(v1,v2);
        }

        if (type.equals("SUM")) {
            return sum(v1,v2);
        }

        if (type.equals("DIV")) {
            return div(v1,v2);
        }
        if (type.equals("MUL")) {
            return mul(v1,v2);
        }

        return new BigDecimal("0.0");

    }

    private static BigDecimal sum(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    private static BigDecimal sub(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    private static BigDecimal mul(BigDecimal value1, BigDecimal value2) {
        return value1.multiply(value2);
    }

    private static BigDecimal div(BigDecimal value1, BigDecimal value2) {
        return value1.divide(value2, 2, RoundingMode.HALF_UP);
    }








}
