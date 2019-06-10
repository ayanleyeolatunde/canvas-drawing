package com.deutsch.drawing;

import com.deutsch.drawing.facade.DrawingFacade;
import com.deutsch.drawing.factory.DrawingSingletonFactory;

import java.util.Scanner;

public class Application {

    final DrawingFacade drawingFacade;

    public Application() {
        this.drawingFacade = DrawingSingletonFactory.getDrawingFacade();
    }

    public static void main(String[] args) {

        Application application = new Application();

        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("enter command: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.exit(1);
            }
            application.drawingFacade.draw(input);
        }
    }
}
