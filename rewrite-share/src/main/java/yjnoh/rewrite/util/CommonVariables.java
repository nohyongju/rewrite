package yjnoh.rewrite.util;

public class CommonVariables {
    public final static String[] TRANSACTIONAL_ANNOTATIONS = {"@Transactional(readOnly = true)", "@Transactional"};

    public final static String TRANSACTIONAL_IMPORT = "import org.springframework.transaction.annotation.Transactional;";

    public final static String NEW_LINE = "\r\n";
}
