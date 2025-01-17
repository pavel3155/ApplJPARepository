package org.example.command;

import org.example.db.ConnectJDBC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Command {
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup(){
        System.out.println("Выполняется runAfterStartup.....");
    }
}

