package com.sber.javaschool.hometask5;

public class MyClass {
    private static final String fieldFinalStringMyclass = "fieldFinalStringMyclass";
    private int privateIntMyClassField;

    public MyClass(int privateIntMyClassField) {
        this.privateIntMyClassField = privateIntMyClassField;
    }

    public int getPrivateIntMyClassField() {
        return privateIntMyClassField;
    }

    public void setPrivateIntMyClassField(int privateIntMyClassField) {
        this.privateIntMyClassField = privateIntMyClassField;
    }

    public String toString() {
        return String.format("field1: %s, field2: %d", fieldFinalStringMyclass, privateIntMyClassField);
    }
}

class MyClassChild extends MyClass {
    public static final String STATIC_FIELD = "STATIC_FIELD_VALUE";
    private int privateIntMyClassChildField;


    public MyClassChild(int privateInt) {
        super(privateInt);
    }

    public int getPrivateIntMyClassChildField() {
        return privateIntMyClassChildField;
    }

    public void setPrivateIntMyClassChildField(int privateIntMyClassChildField) {
        this.privateIntMyClassChildField = privateIntMyClassChildField;
    }
}
