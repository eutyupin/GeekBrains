package ru.geekbrains.java3.lesson7.tester;

import ru.geekbrains.java3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java3.lesson7.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.;

import java.util.*;

public class TesterApp {

    public static void start(Class<?> currentClass) {
        checkSuiteCounts(currentClass.getDeclaredMethods());
        runClassMethods(currentClass);
    }

    private static int checkSuite(Method[] methods, Class annotationClass) {
        int count = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                    count++;
            }
        }
        return count;
    }

    private static void runClassMethods(Class<?> currentClass) {
        try {
            runMethods(currentClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private static <T> void runMethods(Class<T> currentClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = currentClass.getConstructor();
        T classInstance = (T) constructor.newInstance();
        Method[] methods = currentClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (classInstance != null) method.invoke(classInstance);
            }
        }

        for (Method method : sortMethods(methods)) {
            if (classInstance != null) method.invoke(classInstance, method.getAnnotation(Test.class).value());
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (classInstance != null) method.invoke(classInstance);
            }
        }
    }

    private static ArrayList<Method> sortMethods(Method[] methods) {
        ArrayList<Method> sortedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) sortedMethods.add(method);
        }
        Collections.sort(sortedMethods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                int a = o1.getAnnotation(Test.class).value();
                int b = o2.getAnnotation(Test.class).value();
                return (a < b ? -1 : (a > b) ? 1 :0);
            }
        });
        return sortedMethods;
    }

    private static void checkSuiteCounts(Method[] methods) {
            if (checkSuite(methods, BeforeSuite.class) > 1) throw new RuntimeException("More than 1 BeforeSuite annotation");
            if (checkSuite(methods, AfterSuite.class) > 1) throw new RuntimeException("More than 1 AfterSuite annotation");
    }
}
