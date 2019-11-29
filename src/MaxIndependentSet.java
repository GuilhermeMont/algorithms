import java.util.ArrayList;
import java.io.File;

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

        for (int i = 0; i < numberOfVariables ; i++) {
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

    private boolean isGoodSolution (ArrayList<Integer> solution, ArrayList<Integer> bestSolution) {
        int solutionScore = 0;
        int bestSolutionScore = 0;

        for (int i = 0 ; i< bestSolution.size() ; i++) {
            solutionScore += solution.get(i) == 1 ? 1 : 0;
            bestSolutionScore += bestSolution.get(i) == 1 ? 1 : 0;
        }

        return solutionScore > bestSolutionScore;
    }


    private ArrayList<Integer> generateSolution (ArrayList<Integer> solution,Graph graph) {


        //Tentar inserir mais um vertice no grafo.
        for (int i = 0 ; i < solution.size() ; i++) {
            if (solution.get(i) == 0) {
                solution.set(i,1);
                if (isValid(solution,graph)){
                    return solution;
                }
                else {
                    solution.set(i,0);
                }
            }
        }

        //Tentar trocar algum vertice de posição de forma de que retorne uma solução válida.

        return null;
    }


    private ArrayList<Integer> BBoptimization (ArrayList<Integer> solution,ArrayList<Integer> bestSolution,Graph graph) {

        if (isGoodSolution(solution,bestSolution) && isValid(solution,graph)) {
            return solution;
        }
        else{
            ArrayList<Integer> possibleSolution = generateSolution(solution,graph);
            if (possibleSolution == null ) {
                return bestSolution;
            }
            else BBoptimization(possibleSolution,bestSolution,graph);
        }

        return bestSolution;
    }


    public void branchAndBound (File graphInstance,ArrayList<Node> bestGreedySolution) {

        Graph graph = new Graph();

        try {
            graph.loadGraph(graphInstance);
        }catch (Exception e ){
            e.printStackTrace();
        }

        ArrayList<Integer> bestSolution = transformToBBSolution(bestGreedySolution,graph.getGraph().size());
        ArrayList<Integer> initialSolution = bestSolution;

        ArrayList<Integer> solutionFound = BBoptimization(initialSolution,bestSolution,graph);

        System.out.println("MELHOR SOLUCAO BB :");
        System.out.println(solutionFound);
        System.out.println(" ");


    }



}
