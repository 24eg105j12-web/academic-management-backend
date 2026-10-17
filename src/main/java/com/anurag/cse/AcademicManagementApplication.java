package com.anurag.cse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class AcademicManagementApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(AcademicManagementApplication.class, args);
        } catch (Throwable t) {
            System.err.println("\n\n==================================================================");
            System.err.println(">>>> ACADEMIQ AI STARTUP FAILED. ROOT CAUSE:");
            System.err.println(">>>> " + t.getMessage());
            Throwable cause = t;
            while (cause.getCause() != null) {
                cause = cause.getCause();
                System.err.println(">>>> CAUSED BY: " + cause.getClass().getName() + ": " + cause.getMessage());
            }
            System.err.println("==================================================================\n\n");

            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                t.printStackTrace(pw);
                Files.writeString(Paths.get("startup-error.txt"), sw.toString());
            } catch (Exception ignored) {}
            throw t;
        }
    }
}
