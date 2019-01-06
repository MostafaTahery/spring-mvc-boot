package co.nilin.mvc.tst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        new Example().logger.debug("Hello From Logger");
    }
}
