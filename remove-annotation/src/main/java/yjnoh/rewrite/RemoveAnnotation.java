package yjnoh.rewrite;

import java.io.File;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yjnoh.rewrite.service.RewriteService;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "yjnoh.rewrite")
public class RemoveAnnotation implements CommandLineRunner {

    private final RewriteService rewriteService;

    public static void main(String[] args) {
        SpringApplication.run(RemoveAnnotation.class, args);
    }

    public void run(String... args) throws Exception {
        File path = new File("D:\\_VICTORY\\core\\core-asset-messaging");
        rewriteService.process(path);

        System.exit(0);
    }
}
