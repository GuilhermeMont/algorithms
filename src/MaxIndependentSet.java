import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;

public class MaxIndependentSet {


    private ArrayList<Node> removeNeighbors(ArrayList<Node> nodes, ArrayList<Integer> edges) {
        ArrayList<Node> n = new ArrayList<>();

        for (Node node : nodes) {
            if (!edges.contains(node.node)) {
                n.add(node);
            }
        }

        return n;
    }


    public ArrayList<Node> greedy(ArrayList<Node> graph) {

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
            graph = removeNeighbors(newGraph.getGraph(), minNode.edges);

        }

        return independent;
    }


    private ArrayList<Integer> transformToBBSolution(ArrayList<Node> solution, Integer numberOfVariables) {
        ArrayList<Integer> initialSolution = new ArrayList<>();
        ArrayList<Integer> n = new ArrayList<>();

        for (Node node : solution) {
            n.add(node.node);
        }

        for (int i = 0; i < numberOfVariables; i++) {
            if (n.contains(i)) {
                initialSolution.add(1);
            } else {
                initialSolution.add(0);
            }
        }

        return initialSolution;
    }

    ;


    private boolean checkNode(int nodeNumber, ArrayList<Integer> solution, Graph graph) {

        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == 1) {
                if (graph.getGraph().get(i).edges.contains(nodeNumber)) {
                    return false;
                }
            }
        }

        return true;
    }


    private boolean isValid(ArrayList<Integer> solution, Graph graph) {

        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == 1) {
                if (!checkNode(i, solution, graph)) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean isGoodSolution(ArrayList<Integer> solution, ArrayList<Integer> bestSolution) {
        int solutionScore = 0;
        int bestSolutionScore = 0;

        for (int i = 0; i < bestSolution.size(); i++) {
            solutionScore += solution.get(i) == 1 ? 1 : 0;
            bestSolutionScore += bestSolution.get(i) == 1 ? 1 : 0;
        }

        return solutionScore >= bestSolutionScore;
    }


    private int generateSolution(ArrayList<Integer> solution, int pos, Graph graph) {


        int score = 0;
        solution.set(pos, 1);
        //Tentar inserir mais um vertice no grafo.
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == 0) {
                solution.set(i, 1);
                if (isValid(solution, graph)) {
                    score++;
                }
                solution.set(i, 0);
            }
            if (solution.get(i) == 1) {
                score++;
            }
        }

        return score;
    }


    private ArrayList<Integer> BBoptimization(ArrayList<Integer> solution, ArrayList<Integer> bestSolution
            , ArrayList<Integer> pos, Graph graph) {

        if (isGoodSolution(solution, bestSolution) && isValid(solution, graph)) {
            return solution;
        } else {
            int maxScore = 0;
            int localPos = 0;
            for (int i = 0; i < graph.getGraph().size(); i++) {
                if (!pos.contains(i)) {
                    int score = generateSolution(solution, i, graph);
                    if (score > maxScore) {
                        {
                            solution.set(i, 1);
                            if (isValid(solution, graph)) {
                                maxScore = score;
                                localPos = i;
                            } else {
                                solution.set(i, 0);
                            }
                        }

                    }
                }
            }


            if (pos.size() == graph.getGraph().size()){
                return solution;
            }


            pos.add(localPos);
            solution.set(localPos, 1);

            BBoptimization(solution, bestSolution, pos, graph);


        }

        return solution;
    }


    private ArrayList<Integer> fillInitialSolution(int graphSize) {
        ArrayList<Integer> is = new ArrayList<>();
        for (int i = 0; i < graphSize; i++) {
            is.add(0);
        }
        return is;
    }


    public void branchAndBound(File graphInstance, ArrayList<Node> bestGreedySolution) {

        Graph graph = new Graph();

        try {
            graph.loadGraph(graphInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Integer> bestSolution = transformToBBSolution(bestGreedySolution, graph.getGraph().size());
        ArrayList<Integer> initialSolution = fillInitialSolution(graph.getGraph().size());

        ArrayList<Integer> solutionFound = BBoptimization(initialSolution, bestSolution, new ArrayList<>(), graph);

        System.out.println(" ");
        System.out.println("MELHOR SOLUCAO BB :");
        System.out.println(solutionFound);
        System.out.println(" ");


    }


}
