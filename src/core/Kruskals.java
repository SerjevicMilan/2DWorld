    package core;

    import java.util.ArrayList;
    import java.util.List;

    public class Kruskals<T> {
        Graph<T> graph;

        public Kruskals (Graph<T> graph) {
            this.graph = graph;
        }

        public List<Edge<T>> getMST() {
            List<Edge<T>> MST = new ArrayList<>();
            UnionFind<T> uf = new UnionFind<>();
            List<Edge<T>> edges = graph.getAllEdges();

            if (graph == null || graph.getAllVertices().isEmpty()) {
                return new ArrayList<>();
            }


            for (T vertice : graph.getAllVertices()) {
                uf.addItem(vertice);
            }

            edges.sort(null);
            for(Edge<T> edge : edges) {
                if (MST.size() == graph.size() - 1) {
                    break;
                }
                if (uf.union(edge.base, edge.neighbor)) {
                    MST.add(edge);
                }
            }
            return MST;
        }
    }
