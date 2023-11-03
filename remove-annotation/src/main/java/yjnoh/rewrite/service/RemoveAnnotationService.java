package yjnoh.rewrite.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class RemoveAnnotationService {
    private final String[] TRANSACTIONAL_ANNOTATIONS = {"@Transactional(readOnly = true)", "@Transactional"};

    private final String TRANSACTIONAL_IMPORT = "import org.springframework.transaction.annotation.Transactional;";

    private final String NEW_LINE = "\r\n";

    public void remove(List<String> repositoryPath) {
        repositoryPath.forEach(this::remove);
    }

    public void remove(String path) {
        String content = readFile(path);
        if (hasAnnotation(content)) {
            content = removeAnnotation(content);
            content = removeImport(content);
            content = content + NEW_LINE;
            writeFile(path, content);
        }
    }

    public boolean hasAnnotation(String content) {
        return Arrays.stream(TRANSACTIONAL_ANNOTATIONS)
            .anyMatch(content::contains);
    }

    public String removeAnnotation(String content) {
        for (String annotation : TRANSACTIONAL_ANNOTATIONS) {
            content = content.replace(NEW_LINE+annotation, "");
        }

        return content;
    }

    public String removeImport(String content) {
        if (!hasAnnotation(content)) {
            return content.replace(NEW_LINE+TRANSACTIONAL_IMPORT, "");
        }
        return content;
    }

    public String readFile(String path) {
        String content = null;
        try {
            content = Files.lines(Paths.get(path))
                .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public void writeFile(String path, String content) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(content);
            System.out.println("[RemoveAnnotation]" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
