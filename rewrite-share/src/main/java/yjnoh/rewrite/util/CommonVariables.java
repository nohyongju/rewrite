package yjnoh.rewrite.util;

public class CommonVariables {
    public final static String[] TRANSACTIONAL_ANNOTATIONS = {"@Transactional(readOnly = true)", "@Transactional"};

    public final static String TRANSACTIONAL_IMPORT = "import org.springframework.transaction.annotation.Transactional;";

    public final static String TOBE_TRANSACTIONAL_IMPORT = "import spectra.attic.coreasset.share.boot.annotation.AtticTransactional;";

    public final static String NEW_LINE = "\r\n";

    public final static String TRANSACTION_ANNOTATION = "@Transactional";

    public final static String TOBE_TRANSACTION_ANNOTATION = "@AtticTransactional";
}
