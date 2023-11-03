package yjnoh.rewrite.service;

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
        path.forEach(this::replace);
    }

    public void replace(String path) {
        String content = shareService.readFile(path);

        if (shareService.hasAnnotation(content)) {
            // todo
        }
    }
}
