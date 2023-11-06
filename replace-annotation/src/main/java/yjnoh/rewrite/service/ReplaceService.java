package yjnoh.rewrite.service;

import static yjnoh.rewrite.util.CommonVariables.TOBE_TRANSACTIONAL_IMPORT;
import static yjnoh.rewrite.util.CommonVariables.TOBE_TRANSACTION_ANNOTATION;
import static yjnoh.rewrite.util.CommonVariables.TRANSACTIONAL_IMPORT;
import static yjnoh.rewrite.util.CommonVariables.TRANSACTION_ANNOTATION;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplaceService {
    private final ShareService shareService;

    public void process(File path) {
        List<String> targetPath = new ArrayList<>();

        for (final File fileEntry : path.listFiles()) {
            if (fileEntry.isDirectory()) {
                process(fileEntry);
            } else {
                if (fileEntry.getName().endsWith(".java")) {
                    targetPath.add(fileEntry.getAbsolutePath());
                }
            }
        }
        process(targetPath);
    }

    public void process(List<String> path) {
        path.forEach(this::rewrite);
    }

    public void rewrite(String path) {
        String content = shareService.readFile(path);

        if (shareService.hasAnnotation(content)) {
            content = replaceImport(content);
            content = replaceAnnotation(content);
            content = shareService.addNewLine(content);
            shareService.writeFile(path, content);
            System.out.println("[ReplaceAnnotation]" + path);
        }
    }

    public String replaceImport(String content) {
        return content.replaceAll(TRANSACTIONAL_IMPORT, TOBE_TRANSACTIONAL_IMPORT);
    }

    public String replaceAnnotation(String content) {
        return content.replaceAll(TRANSACTION_ANNOTATION, TOBE_TRANSACTION_ANNOTATION);
    }
}
