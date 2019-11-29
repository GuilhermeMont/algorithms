import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        Graph graph = new Graph();

        try {
            graph.loadGraph();
        }catch (Exception e){
            e.printStackTrace();
        }

//        graph.checkGraph();


        MaxIndependentSet MIS = new MaxIndependentSet();

        ArrayList<Node> greedySolution = MIS.greedy(graph.getGraph());

        MIS.branchAndBound(greedySolution);

    }
}
