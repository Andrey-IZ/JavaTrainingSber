package com.sber.javaschool.hometask8.serialization;

import java.util.Map;

public interface ISerializer {
    void save(Object object, String filename, boolean isZip);

    Map<Object, Object> load(String filename, boolean isZip);

}
