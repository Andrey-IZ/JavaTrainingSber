package plugins;

import com.sber.javaschool.hometask7.Plugin;

public class TestPlugin2 implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("I'm plugin2");
    }

    @Override
    public int hashCode() {
        return 0x6;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
