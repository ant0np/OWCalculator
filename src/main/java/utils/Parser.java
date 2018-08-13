package utils;

import domain.ObjectFactory;
import domain.SimpleCalculator;

import javax.xml.bind.*;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    public static final String location = "\\src\\main\\resources\\testcases";

    private ObjectFactory objectFactory;

    public Parser() {
        objectFactory = new ObjectFactory();
    }

    public File evaluateResult(File file) {
        SimpleCalculator simpleCalculator = unmarshal(file);
        return marshal(simpleCalculator);
    }

    private SimpleCalculator unmarshal(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(SimpleCalculator.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (SimpleCalculator) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    private File marshal(SimpleCalculator sc) {

        try {

            File file = new File(System.getProperty("user.dir") + location + "\\result.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(SimpleCalculator.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            SimpleCalculator result = objectFactory.createSimpleCalculator();
            SimpleCalculator.ExpressionResults expressionResults = objectFactory.createSimpleCalculatorExpressionResults();

            List<SimpleCalculator.Expressions.Expression> expressionList = sc.getExpressions().getExpression();

            for (SimpleCalculator.Expressions.Expression expression : expressionList) {

                SimpleCalculator.ExpressionResults.ExpressionResult expressionResult =
                        objectFactory.createSimpleCalculatorExpressionResultsExpressionResult();

                expressionResult.setResult(CalculatorService.calculation(expression.getOperation()).doubleValue());

                expressionResults.getExpressionResult().add(expressionResult);

            }

            result.setExpressionResults(expressionResults);
            jaxbMarshaller.marshal(result, file);
            return file;

        } catch (JAXBException e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
