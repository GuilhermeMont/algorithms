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

            Graph newGraph = new Graph(graph);
            newGraph.getGraph().remove(minNode);
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

        for (int i = 0; i <= numberOfVariables ; i++) {
            if (n.contains(i)){
                initialSolution.add(1);
            }else {
                initialSolution.add(0);
            }
        }

        return initialSolution;
    };


    private boolean checkNode (int nodeNumber, ArrayList<Integer> solution , Graph graph ){
        for (int i = 0 ; i < solution.size() ; i++){
            if (solution.get(i) == 1) {
                System.out.println("EDGES " + graph.getGraph().get(i).edges);
                System.out.println("NODE NUMBER " + nodeNumber);
                if (graph.getGraph().get(i).edges.contains(nodeNumber)){
                    return false;
                }
            }
        }

        return true;
    }


    private boolean isValid (ArrayList<Integer> solution , Graph graph) {

        for (int i = 0 ; i < solution.size() ; i++){
            if (solution.get(i) == 1) {
                if (!checkNode(i,solution,graph)) {
                    return false;
                }
            }
        }

        return true;


    }


    public void branchAndBound (ArrayList<Node> initialSolution) {

        Graph graph = new Graph();

        try {
            graph.loadGraph();
        }catch (Exception e ){
            e.printStackTrace();
        }

        ArrayList<Integer> bestSolution = transformToBBSolution(initialSolution,graph.getGraph().size());

        System.out.println(bestSolution);

        System.out.print(isValid(bestSolution,graph));

    }



}
