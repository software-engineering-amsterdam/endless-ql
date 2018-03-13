package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class CyclicDependencyChecker {
	
	private final CycleDetector<String, DefaultEdge> cycleDetector;
	private final DefaultDirectedGraph<String, DefaultEdge> graph;
	private final boolean printCycles;
	
	// This filed was added for testing purposes.
	private final List<String> errors = new ArrayList<>();
	
	
	// This method was introduced for testing purposes.
	public List<String> getErrors() {
		return errors;
	}
	
	// This method was introduced for testing purposes.
	public String getErrorAtIndex(int index) {
		if (index >= 0 && index < this.errors.size()) {
			return this.errors.get(index);
		}
		
		return "Index is out of range";
	}
	
	public int getNumberofErrors() {
		return errors.size();
	}

	public CyclicDependencyChecker(boolean printCycles) {
		graph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		cycleDetector = new CycleDetector<String, DefaultEdge>(graph);
		this.printCycles = printCycles;
	}
	
	public boolean check(HashMap<String, List<String>> identifierMap) {
		buildGraph(identifierMap);
		return hasCycles();
	}
	
	public void buildGraph(HashMap<String, List<String>> identifierMap) {
		// Clear all the errors.
		this.errors.clear();
		
		// Clear all the vertices and all the edges in the graph.
		graph.vertexSet().forEach(vertex -> graph.removeVertex(vertex));
		graph.edgeSet().forEach(edge -> graph.removeEdge(edge));
		
		// Every identifier is a vertex in the graph.
		for (String identifier : identifierMap.keySet()) {
			graph.addVertex(identifier);
		}
		
		// Every edge in the graph represents a dependency between identifiers.
		for (String identifier : identifierMap.keySet()) {
			// Get a list of dependencies for a specific identifier.
			List<String> dependencies = identifierMap.get(identifier);
			dependencies.forEach( dependecie -> graph.addEdge(identifier, dependecie));
		}
	}

	private boolean hasCycles() {
		if (cycleDetector.detectCycles()) {	
			findCycles();
			
			if (printCycles) {
				errors.forEach(error -> System.err.println(error));
			}
			return true;
		} else {
			return false;
		}
	}
	
	private void findCycles() {
		ArrayList<Set<String>> cycleSet = new ArrayList<Set<String>>();
		Set<String> subCycle;
		Iterator<String> iterator;
		String vertex;
		
		// Get all the vertices involved in cycles.
		 Set<String> cycleVertices = cycleDetector.findCycles();
		 
		 // Loop through the vertices in order to find disjoint cycles.
		 while (!cycleVertices.isEmpty()) {
			 
			 // Select a vertex involved in a cycle.
			 iterator = cycleVertices.iterator();
			 
			 // Get a vertex
			 vertex = iterator.next();
			 
			 subCycle = cycleDetector.findCyclesContainingVertex(vertex);
			 
			 if (!cycleSet.contains(subCycle))
				 cycleSet.add(subCycle);
			 
			 cycleVertices.remove(vertex);
		 }
		 
		 if (!cycleSet.isEmpty()) {
			 for (Set<String> set : cycleSet) {
				String error = String.format("[CyclicDependencyChecker] A cyclic dependency was found between the following questions: %s",
						String.join(", ", set));
				errors.add(error);
			 }
		 }
	}
}
