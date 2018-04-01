package org.uva.jomi.ui.storage;

public class StorageFactory {

	public enum StorageType {
		JSON,
		TEXT
	}
	
	public static Storage storageWithType(StorageType type) {
		switch (type) {
		case JSON:
			return new JSONStorage();
		case TEXT:
			return new TextStorage();
		default:
			return null;
		}
	}
	
}
