package de.fynn.sco.placeholderapi.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 public @Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface PlaceholderHook{

}
