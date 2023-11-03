package yjnoh.rewrite;

import java.io.File;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yjnoh.rewrite.service.ReplaceService;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "yjnoh.rewrite")
public class ReplaceAnnotation implements CommandLineRunner {

    private final ReplaceService replaceService;

    public static void main(String[] args) {
        SpringApplication.run(ReplaceAnnotation.class, args);
    }

    public void run(String... args) throws Exception {
        if (args.length > 0) {
            File path = new File(args[0]);
            replaceService.process(path);
        }

        System.exit(0);
    }
}
