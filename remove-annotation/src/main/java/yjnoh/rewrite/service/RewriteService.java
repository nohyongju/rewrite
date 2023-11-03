package yjnoh.rewrite.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewriteService {
    private final RemoveAnnotationService removeAnnotationService;

    public void process(File path) {
        List<String> repositoryPath = new ArrayList<>();

        for (final File fileEntry : path.listFiles()) {
            if (fileEntry.isDirectory()) {
                process(fileEntry);
            } else {
                if (fileEntry.getName().endsWith("Repository.java")) {
                    repositoryPath.add(fileEntry.getAbsolutePath());
                }
            }
        }
        removeAnnotationService.remove(repositoryPath);
    }
}
