package org.codejargon.feather;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Author : YiZ
 * Date   : 3/13/2017
 * Time   : 3:58 PM
 */
public class Binding {

    private Set<Key> keys = new HashSet<>();


    public <T> void bind(Class<T> from, Class<? extends T> to) {
        Key key = Key.of(from);
        key.setToType(to);
        keys.add(key);
    }

    public <T> void bind(Class<T> from, Class<? extends T> to, Class<? extends Annotation> qualifier) {
        Key key = Key.of(from, qualifier);
        key.setToType(to);
        keys.add(key);
    }

    public <T> void bind(Class<T> from, Class<? extends T> to, String name) {
        Key key = Key.of(from, name);
        key.setToType(to);
        keys.add(key);
    }

    public Set<Key> getKeys() {
        return keys;
    }
}
