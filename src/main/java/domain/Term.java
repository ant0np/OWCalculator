package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "term", propOrder = {
        "arg",
        "arg1",
        "arg2",
        "operation",
        "operation1",
        "operation2"
})
public class Term {

    @XmlSchemaType(name = "nonNegativeInteger")
    protected List<BigDecimal> arg;
    protected List<Term> operation;

    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigDecimal arg1;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigDecimal arg2;

    protected Term operation1;
    protected Term operation2;

    @XmlAttribute(name = "OperationType")
    protected String operationType;


    public List<BigDecimal> getArg() {
        if (arg == null) {
            arg = new ArrayList<>();
        }
        return this.arg;
    }

    public BigDecimal getArg1() {
        return arg1;
    }

    public void setArg1(BigDecimal arg1) {
        this.arg1 = arg1;
    }

    public BigDecimal getArg2() {
        return arg2;
    }

    public void setArg2(BigDecimal arg2) {
        this.arg2 = arg2;
    }

    public domain.Term getOperation1() {
        return operation1;
    }

    public void setOperation1(domain.Term operation1) {
        this.operation1 = operation1;
    }

    public domain.Term getOperation2() {
        return operation2;
    }

    public void setOperation2(domain.Term operation2) {
        this.operation2 = operation2;
    }

    public List<Term> getOperation() {
        if (operation == null) {
            operation = new ArrayList<>();
        }
        return this.operation;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }


}
