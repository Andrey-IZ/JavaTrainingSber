package plugins;

import com.sber.javaschool.hometask7.Plugin;

public class TestPlugin1 implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("I'm plugin1");
    }

    @Override
    public int hashCode() {
        return 0x5;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
