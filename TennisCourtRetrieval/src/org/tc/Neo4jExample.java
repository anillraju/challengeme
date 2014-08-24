package org.tc;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jExample {
	static GraphDatabaseService graphDb;
	static {
		graphDb = new GraphDatabaseFactory()
				.newEmbeddedDatabase("/home/anil/sbin/neo4j-community-2.1.3/data/graph.db");
		registerShutdownHook(graphDb);
	}

	public static void writeToDb(String name, String streetAddress,
			String addressLocality, String addressRegion, String postalCode,
			String nationality, String map) {

		Node firstNode;

		try (Transaction tx = graphDb.beginTx()) {
			ResourceIterable<Node> nodes = graphDb.findNodesByLabelAndProperty(
					DynamicLabel.label(nationality), null, null);
			ResourceIterator<Node> it = nodes.iterator();
			if (it.hasNext()) {
				Node node = it.next();

				firstNode = graphDb.createNode();
				firstNode.setProperty("name", name);
				firstNode.setProperty("streetAddress", streetAddress);
				firstNode.setProperty("addressLocality", addressLocality);
				firstNode.setProperty("addressRegion", addressRegion);
				firstNode.setProperty("postalCode", postalCode);
				firstNode.setProperty("nationality", nationality);
				firstNode.setProperty("map", map);
				// node.createRelationshipTo(arg0, arg1)
			} else {
				firstNode = graphDb.createNode(DynamicLabel.label(nationality));
				
			}
			tx.success();
		}
	}

	private static enum RelTypes implements RelationshipType {
		KNOWS
	}

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running application).
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
}
