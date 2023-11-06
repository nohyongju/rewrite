package yjnoh.rewrite.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yjnoh.rewrite.util.CommonVariables;

@Service
@RequiredArgsConstructor
public class RemoveAnnotationService {
    private final ShareService shareService;

    public void remove(List<String> repositoryPath) {
        repositoryPath.forEach(this::remove);
    }

    public void remove(String path) {
        String content = shareService.readFile(path);
        if (shareService.hasAnnotation(content)) {
            content = removeAnnotation(content);
            content = removeImport(content);
            content = shareService.addNewLine(content);
            shareService.writeFile(path, content);
            System.out.println("[RemoveAnnotation]" + path);
        }
    }

    public String removeAnnotation(String content) {
        for (String annotation : CommonVariables.TRANSACTIONAL_ANNOTATIONS) {
            String origin = CommonVariables.NEW_LINE + annotation;
            content = content.replace(origin, "");
        }

        return content;
    }

    public String removeImport(String content) {
        if (!shareService.hasAnnotation(content)) {
            String origin = CommonVariables.NEW_LINE + CommonVariables.TRANSACTIONAL_IMPORT;
            return content.replace(origin, "");
        }
        return content;
    }

}
