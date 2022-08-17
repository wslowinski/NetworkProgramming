public class Pair<K, V> {
    private final K first;
    private final V second;
    public static <K, V> Pair<K, V> makePair(K first, V second) {
        return new Pair<K, V>(first, second);
    }
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
    public K getFirst() {
        return first;
    }
    public V getSecond() {
        return second;
    }
    @Override
    public String toString() {
        return "Pair<" + first + ", " + second + ">";
    }

}
