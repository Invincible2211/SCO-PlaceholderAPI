package de.fynn.mystic.mysticplaceholderapi.utils;


import de.fynn.mystic.mysticplaceholderapi.placeholder.Placeholder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class AnnotationController {

  private final RuntimeAnalyser runtimeAnalyser = new RuntimeAnalyser();
  private final HashMap<String,List<Class<Placeholder>>> classListHashmap = new HashMap<>();

  {
    try {
      collectAnnotaitedClasses();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void collectAnnotaitedClasses() throws IOException, ClassNotFoundException {
    for (URL url:
         runtimeAnalyser.getRootUrls()) {
      File f = new File (url.getPath());
      if (!f.isDirectory()) {
        checkJar (url);
      }
    }
  }

  private void checkJar(URL url) throws IOException, ClassNotFoundException {
    try (InputStream urlIn = url.openStream ();
         JarInputStream jarIn = new JarInputStream (urlIn)) {
      JarEntry entry;
      String plugin = null;
      List<Class<Placeholder>> classes = new ArrayList<>();
      while ((entry = jarIn.getNextJarEntry ()) != null) {
        if (entry.getName ().endsWith (".class")) {
          Class<Placeholder> checkedClass = checkClass(entry.getName());
          if (checkedClass!=null){
            classes.add(checkedClass);
          }
        }else if(entry.getName().contains("plugin.yml")){
          File file = new File(url.getFile());
          JarFile jarFile = new JarFile(file);
          FileConfiguration pluginYML = YamlConfiguration.loadConfiguration(new InputStreamReader(jarFile.getInputStream(entry)));
          plugin = pluginYML.getString("name");
        }
      }
      if(!classes.isEmpty()&&plugin!=null)classListHashmap.put(plugin,classes);
    }
  }

  private Class<Placeholder> checkClass(String className) throws ClassNotFoundException {
      Class<Placeholder> cls = (Class<Placeholder>) Class.forName(className);
      return cls.getAnnotation(PlaceholderHook.class) != null ? cls : null;
  }

  public HashMap<String,List<Class<Placeholder>>> getPlaceholderClasses(){
    return classListHashmap;
  }

}
