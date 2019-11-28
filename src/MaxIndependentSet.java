import java.util.ArrayList;

public class MaxIndependentSet {



    private ArrayList<Node> removeNeighbors (ArrayList<Node> nodes,ArrayList<Integer> edges)  {
        ArrayList<Node> n = new ArrayList<>();

        for (Node node : nodes){
            if (!edges.contains(node.node)){
                n.add(node);
            }
        }

        return n;
    }


    public ArrayList<Node> greedy (ArrayList<Node> graph) {

        ArrayList<Node> independent = new ArrayList<>();

        while (graph.size() != 0) {

            Node minNode = graph.get(0);
            for (Node node : graph) {
                if (node.edges.size() < minNode.edges.size()) {
                    minNode = node;
                }

            }

            independent.add(minNode);

            //delete the min node and all its neighbors
            graph.remove(minNode);
            Graph newGraph = new Graph(graph);
            graph = removeNeighbors(newGraph.getGraph(),minNode.edges);

        }

        return independent;
    }


    private ArrayList<Integer> transformToBBSolution (ArrayList<Node> solution, Integer numberOfVariables){
        ArrayList<Integer> initialSolution = new ArrayList<>();
        ArrayList<Integer> n = new ArrayList<>();

        for (Node node : solution){
            n.add(node.node);
        }

        for (int i = 0; i < numberOfVariables ; i++) {
            if (n.contains(i)){
                initialSolution.add(1);
            }else {
                initialSolution.add(0);
            }
        }

        return initialSolution;
    };


    public void branchAndBound (ArrayList<Node> initialSolution , Graph graph) {

        ArrayList<Integer> bestSolution = transformToBBSolution(initialSolution,graph.getGraph().size());

        System.out.println(bestSolution);

    }



}
