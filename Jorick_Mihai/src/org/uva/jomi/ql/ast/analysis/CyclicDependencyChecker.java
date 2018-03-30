package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.uva.jomi.ql.error.ErrorHandler;

public class CyclicDependencyChecker {

	private final CycleDetector<String, DefaultEdge> cycleDetector;
	private final DefaultDirectedGraph<String, DefaultEdge> graph;
	private final ErrorHandler errors;

	public CyclicDependencyChecker() {
		this(false);
	}

	public CyclicDependencyChecker(boolean printCycles) {
		this.graph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		this.cycleDetector = new CycleDetector<String, DefaultEdge>(graph);
		this.errors = new ErrorHandler(this.getClass().getSimpleName(), printCycles);
	}

	// This method was introduced for testing purposes.
	public List<String> getErrors() {
		return errors.getReports();
	}

	// This method was introduced for testing purposes.
	public String getErrorAtIndex(int index) {
		return this.errors.getErrorAtIndex(index);
	}

	public int getNumberofErrors() {
		return this.errors.getNumberOfErrors();
	}

	public void reset() {
		// Clear all the errors.
		this.errors.clear();

		// Clear all the vertices and all the edges in the graph.
		graph.vertexSet().forEach(vertex -> graph.removeVertex(vertex));
		graph.edgeSet().forEach(edge -> graph.removeEdge(edge));
	}

	public boolean check(HashMap<String, List<String>> identifierMap) {
		reset();
		buildGraph(identifierMap);
		if (hasCycles()) {
			findCycles();
			return true;
		}

		return false;
	}

	public void buildGraph(HashMap<String, List<String>> identifierMap) {
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
			this.errors.addCyclicQuestionError(cycleSet);
		}
	}
}
