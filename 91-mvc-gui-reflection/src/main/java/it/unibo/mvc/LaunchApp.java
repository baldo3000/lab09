package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private final static String VIEWS_PACKAGE = "it.unibo.mvc.view";

    private LaunchApp() {
    }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException    if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException     if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException    if the constructor throws exceptions
     * @throws IllegalAccessException    in case of reflection issues
     * @throws IllegalArgumentException  in case of reflection issues
     */
    public static void main(final String... args)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, ClassNotFoundException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final List<Class<?>> views = new ArrayList<>();
        for (var view : List.of("DrawNumberOutputView", "DrawNumberSwingView")) {
            views.add(loadView(view));
        }
        for (int i = 0; i < 3; i++) {
            for (var view : views) {
                newView(app, view);
            }
        }
    }

    private static Class<?> loadView(String className) throws ClassNotFoundException {
        Class<?> classToCheck = Class.forName(VIEWS_PACKAGE + "." + className);
        if (!DrawNumberView.class.isAssignableFrom(classToCheck)) {
            throw new IllegalArgumentException("This class does not implement class DrawNumberView");
        }
        return classToCheck;
    }

    private static void newView(DrawNumberController app, Class<?> view)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        app.addView((DrawNumberView) view.getConstructor().newInstance());
    }
}
