package de.fynn.mystic.mysticplaceholderapi.placeholder;

public interface Placeholder {

    <T> String getPlaceholder(T... values);

}
