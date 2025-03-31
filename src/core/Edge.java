package core;

public class Edge<T> implements Comparable<Edge<T>> {
    T base;
    T neighbor;
    double weight;

    public Edge(T base, T neighbor, double weight) {
        this.base = base;
        this.neighbor = neighbor;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge<T> other) {
        return (int) Math.round(weight - other.weight);
    }

}
