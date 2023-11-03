package yjnoh.rewrite.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import yjnoh.rewrite.util.CommonVariables;

@Service
public class ShareService {
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

    public boolean hasAnnotation(String content) {
        return Arrays.stream(CommonVariables.TRANSACTIONAL_ANNOTATIONS)
            .anyMatch(content::contains);
    }
}
