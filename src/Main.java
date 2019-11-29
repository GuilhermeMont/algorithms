import java.io.File;
import java.util.ArrayList;

public class Main {


    public static void  generateProblems (File[] graphInstances){

        for (int i = 0 ; i < graphInstances.length; i++) {

            Graph graph = new Graph();

            try {
                graph.loadGraph(graphInstances[i]);
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("Instância " + i + "  - Grafo");
            graph.checkGraph();
            System.out.println(" ");

            MaxIndependentSet MIS = new MaxIndependentSet();

            ArrayList<Node> greedySolution = MIS.greedy(graph.getGraph());

            MIS.branchAndBound(graphInstances[i],greedySolution);

            Clique clique = new Clique();

            ArrayList<Node> cliqueSolution = clique.cliqueByReduction(graphInstances[i]);

            System.out.println("Instância" + i + "- Solução para o problema do clique");
            for (Node node : cliqueSolution) {
                System.out.print(" " + node.node + " ");
            }

        }

    }

    public static void main(String[] args) {

        File[] graphInstances = { new File("/Users/gmonteiro/Downloads/algorithms/src/graph.txt"),
                                  new File("/Users/gmonteiro/Downloads/algorithms/src/graph2.txt"),
                                  new File("/Users/gmonteiro/Downloads/algorithms/src/graph3.txt") };


        generateProblems(graphInstances);



    }
}
