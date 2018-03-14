package org.uva.jomi.ql.error;

import java.util.ArrayList;
import java.util.List;

public abstract class ErrorReporter<T> {
	
	private final List<T> reports;
	
	public ErrorReporter() {
		reports = new ArrayList<>();
	}
	
	public void displayReport(T report) {
		System.err.println(report);
	}
	
	public void displayReports() {
		reports.forEach(report -> System.err.println(report));
	}
	
	public void addReport(T report) {
		reports.add(report);
	}
	
	public T getReport(int index) {
		return reports.get(index);
	}
	
	public void clearAllReports() {
		reports.clear();
	}
	
	public int getNumberOfReports() {
		return this.reports.size();
	}
}

