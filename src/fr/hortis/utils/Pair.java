package fr.hortis.utils;

public class Pair<K, V> {

    /*
     * @Imported from javafx.util
     */

    /**
     * Key of this <code>Pair</code>.
     */
    private final K key;
    /**
     * Value of this this <code>Pair</code>.
     */
    private final V value;

    public Pair(@NamedArg("key") K key, @NamedArg("value") V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key for this pair.
     *
     * @return key for this pair
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the value for this pair.
     *
     * @return value for this pair
     */
    public V getValue() {
        return value;
    }

}
