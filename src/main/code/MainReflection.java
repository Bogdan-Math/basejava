package main.code;

import main.code.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        Object o = field.get(resume);
        System.out.println(o);

        Method toString = resume.getClass().getDeclaredMethod("toString");
        Object invoke = toString.invoke(resume);
        System.out.println(invoke);
    }
}
