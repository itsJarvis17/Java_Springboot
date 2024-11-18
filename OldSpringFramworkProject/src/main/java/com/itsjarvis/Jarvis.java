package com.itsjarvis;

import java.beans.ConstructorProperties;

public class Jarvis {

    private JarvisFunctions jarvisFunctions;
    private String version;
    public JarvisFunctions getJarvisFunctions() {
        return jarvisFunctions;
    }
    @ConstructorProperties({"jarvisFunctions","version"})
    public Jarvis(JarvisFunctions jarvisFunctions, String version) {
        this.jarvisFunctions = jarvisFunctions;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setJarvisFunctions(JarvisFunctions jarvisFunctions) {
        this.jarvisFunctions = jarvisFunctions;
    }

    public void sayHiJarvis() {
        System.out.println("Hello I am Jarvis...."+this.version);
            jarvisFunctions.listenCommands();
            jarvisFunctions.cook();
            jarvisFunctions.walk();
            jarvisFunctions.fight();
    }
}
