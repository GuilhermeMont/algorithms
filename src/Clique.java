import java.util.ArrayList;
import java.io.File;

public class Clique {


    private ArrayList<Node> fillGraph (int size) {

        ArrayList<Node> filledGraph = new ArrayList<>();

        for (int i = 0 ; i<size ; i++) {

            ArrayList<Integer> edges = new ArrayList<>();

            for (int j = 0 ; j<size ; j++){
                edges.add(j);
            }
            filledGraph.add(new Node(i,edges));
        }

        return filledGraph;
    }


    private ArrayList<Integer> removeCommom (ArrayList<Integer> list1 , ArrayList<Integer> list2) {
        ArrayList<Integer> interception = new ArrayList<>();

        for (int i = 0 ; i < list1.size() ; i++) {
            if (!list2.contains(list1.get(i))) {
                interception.add(i);
            }
        }

        return interception;
    }


    private ArrayList<Node> inverseGraph (ArrayList<Node> graph) {

        ArrayList<Node> inverseGraph = fillGraph(graph.size());
//        ArrayList<Node> inverseGraph = new ArrayList<>();
        for (int i =0; i<graph.size();i++){
            inverseGraph.get(i).edges.remove(i);
            inverseGraph.get(i).edges = removeCommom(inverseGraph.get(i).edges,graph.get(i).edges);
        }

        return inverseGraph;
    };



    public ArrayList<Node> cliqueByReduction (File graphInstance) {

        Graph graph = new Graph();

        try {
            graph.loadGraph(graphInstance);
        }catch (Exception e) {
            e.printStackTrace();
        }


        Graph inverse = new Graph(inverseGraph(graph.getGraph()));

        MaxIndependentSet MIS = new MaxIndependentSet();

        return MIS.greedy(inverse.getGraph());

    }



}
