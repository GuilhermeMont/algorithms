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

            MaxIndependentSet MIS = new MaxIndependentSet();

            System.out.println("Greedy solution");
            ArrayList<Node> greedySolution = MIS.greedy(graph.getGraph());
            for (Node n : greedySolution){
                System.out.print(" " + n.node + ", ");
            }


            MIS.branchAndBound(graphInstances[i],greedySolution);

            Clique clique = new Clique();

            ArrayList<Node> cliqueSolution = clique.cliqueByReduction(graphInstances[i]);

            System.out.println("Instância" + i + "- Solução para o problema do clique");
            for (Node node : cliqueSolution) {
                System.out.print(" " + node.node + " ");
            }
            System.out.println(" ");

        }

    }

    public static void main(String[] args) {

        File[] graphInstances = { new File("graph.txt"),
                new File("graph3.txt"),
                new File("graph2.txt") };

        generateProblems(graphInstances);



    }
}
