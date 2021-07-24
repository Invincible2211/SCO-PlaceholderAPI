package de.fynn.mystic.mysticplaceholderapi.utils;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RuntimeAnalyser {

    public List<URL> getRootUrls () {
        List<URL> result = new ArrayList<>();
        Set<Thread> activeThreads = Thread.getAllStackTraces().keySet();
        for (Thread t:
             activeThreads) {
            ClassLoader cl = t.getContextClassLoader();
            while (cl != null) {
                System.out.println(cl.toString()); // LOG //
                if (cl instanceof URLClassLoader) {
                    URL[] urls = ((URLClassLoader) cl).getURLs();
                    result.addAll (Arrays.asList (urls));
                }
                cl = cl.getParent();
            }
        }
        return result;
    }

}
