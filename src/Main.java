import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {



    private static void  generateProblems (File[] graphInstances, File[] satInstances){

        for (int i = 0 ; i < graphInstances.length; i++) {

            System.out.println("---- EXECUTION FOR --- " +graphInstances[i].getName()  );

            Graph graph = new Graph();

            try {
                graph.loadGraph(graphInstances[i]);
            }catch (Exception e){
                e.printStackTrace();
            }

            MaxIndependentSet MIS = new MaxIndependentSet();

            //Excução do máximo conjunto independente - guloso
            long startTime = System.nanoTime();

            System.out.println("Greedy solution " + graphInstances[i].getName());
            ArrayList<Node> greedySolution = MIS.greedy(graph.getGraph());

//            for (Node n : greedySolution){
//                System.out.print(" " + n.node + ", ");
//            }

            long endTime = System.nanoTime();

            // get difference of two nanoTime values
            long timeElapsed = endTime - startTime;

            System.out.println("Execution time in milliseconds : " +
                    timeElapsed / 100000);

            //Inicio da medicao de tempo solucao por branch and bound

            startTime = System.nanoTime();

            System.out.println("Branch and bound solution " + graphInstances[i].getName());
            MIS.branchAndBound(graphInstances[i],greedySolution);

            endTime = System.nanoTime();

            // get difference of two nanoTime values
            timeElapsed = endTime - startTime;

            System.out.println("Execution time in milliseconds : " +
                    timeElapsed / 1000000);


            Clique clique = new Clique();

            startTime = System.nanoTime();

            System.out.println("Clique solution " + graphInstances[i].getName());
            ArrayList<Node> cliqueSolution = clique.cliqueByReduction(graphInstances[i]);

            endTime = System.nanoTime();

            // get difference of two nanoTime values
            timeElapsed = endTime - startTime;

            System.out.println("Execution time in milliseconds : " +
                    timeElapsed / 1000000);

//            codigo para printar na tela a solucao do problema
//            System.out.println("Instância" + i + "- Solução para o problema do clique");
//            for (Node node : cliqueSolution) {
//                System.out.print(" " + node.node + " ");
//            }
            System.out.println(" ");

        }

        for(int i = 0; i<satInstances.length; i++) {
            Graph satGraph = new Graph();

            long startTime = System.nanoTime();
            try {
                satGraph.loadGraphSat(satInstances[i]);
            }catch (Exception e){
                e.printStackTrace();
            }

            long endTime = System.nanoTime();

            // get difference of two nanoTime values
            long timeElapsed = endTime - startTime;

            System.out.println("Execution time of SAT in milliseconds : " +
                    timeElapsed / 100000);

            MaxIndependentSet MISsat = new MaxIndependentSet();

            ArrayList<Node> greedySolution = MISsat.greedy(satGraph.getGraph());

            /*for(int j = 0; j < greedySolution.size(); j++){
                System.out.println(greedySolution.get(j).);
            }*/

            MISsat.branchAndBound(satInstances[i],greedySolution);

            /*System.out.println("SOLUCAO");
            for (Node n : greedySolution){
                System.out.print(" " + n.node + ", ");
            }*/
        }
    }

    public static void main(String[] args) {

        File[] graphInstances = {

                new File("graph100.txt"),
                new File("graph100-1.txt"),
                new File("graph100-2.txt"),
                new File("graph200.txt"),
                new File("graph200-1.txt"),
                new File("graph200-2.txt"),
                new File("graph300.txt"),
                new File("graph300-1.txt"),
                new File("graph300-2.txt"),
                new File("graph400.txt"),
                new File("graph400-1.txt"),
                new File("graph400-2.txt"),
                new File("graph500.txt"),
                new File("graph500-1.txt"),
                new File("graph500-2.txt"),

        };

        File[] satInstances = {new File("SatExpression.txt"),
                new File("SatExpression4.txt")
                ,new File("SatExpression3.txt")};

        generateProblems(graphInstances, satInstances);



    }
}
