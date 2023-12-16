package com.tiiqu.graphviz.service.impl;

import com.tiiqu.graphviz.model.Graph;
import lombok.extern.slf4j.Slf4j;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.io.database.drivers.PostgreSQLDriver;
import org.gephi.io.database.drivers.SQLUtils;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.importer.plugin.database.EdgeListDatabaseImpl;
import org.gephi.io.importer.plugin.database.ImporterEdgeList;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/12/2023, Tuesday
 **/

@Service
@Slf4j
public class GraphService {
    public Graph getGraph()
    {
        Graph g = new Graph();
        g.setGraphId(1);
        g.setGraphName("graph");
        g.setTime(new Date());
        return g;
    }
    public void updateGraph()
    {
        this.script();
    }
    public void script()
    {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
//        AttributeModel attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();

        //Import database
        EdgeListDatabaseImpl db = new EdgeListDatabaseImpl();
        db.setDBName("graphviz");
        db.setHost("localhost");
        db.setUsername("postgres");
        db.setPasswd("123456");
        //db.setSQLDriver(new MySQLDriver());
        db.setSQLDriver(new PostgreSQLDriver());
        //db.setSQLDriver(new SQLServerDriver());
        db.setPort(5432);
        db.setNodeQuery("SELECT nodes.id AS id, nodes.label AS label, nodes.url FROM nodes");
        db.setEdgeQuery("SELECT edges.source AS source, edges.target AS target, edges.name AS label, edges.weight AS weight FROM edges");
        ImporterEdgeList edgeListImporter = new ImporterEdgeList();

        Container container = importController.importDatabase(db, edgeListImporter);

        container.getLoader().setAllowAutoNode(false);      //Don't create missing nodes
        container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);   //Force UNDIRECTED

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);
        log.info(("go through here*************************************"));
        //See if graph is well imported
        UndirectedGraph graph = graphModel.getUndirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());

        //Layout - 100 Yifan Hu passes
        YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.initAlgo();
        for (int i = 0; i < 100 && layout.canAlgo(); i++) {
            layout.goAlgo();
        }
        layout.endAlgo();

        //Export X, Y position to the DB
        //Connect database
        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
        Connection connection = null;
        try {
            //System.err.println("Try to connect at " + url);
            connection = db.getSQLDriver().getConnection(url, db.getUsername(), db.getPasswd());
            //System.err.println("Database connection established");
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.close();
                    System.err.println("Database connection terminated");
                } catch (Exception e) { /* ignore close errors */ }
            }
            System.err.println("Failed to connect at " + url);
            ex.printStackTrace(System.err);
        }
        if (connection == null) {
            System.err.println("Failed to connect at " + url);
        }

        //Update
        int count = 0;
        for (Node node : graph.getNodes().toArray()) {
            String id = node.getId().toString();
            float x = node.x();
            float y = node.y();

            String query = "UPDATE " + "nodes SET x = '" + x + "', y = '" + y + "' WHERE nodes.id='" + id+"'";
            try {
//                log.info(query);
                Statement s = connection.createStatement();
                count += s.executeUpdate(query);
                s.close();

            } catch (SQLException e) {
                System.err.println("Failed to update line node id = " + id);
            }
        }
        System.err.println(count + " rows were updated");

        //Close connection
        if (connection != null) {
            try {
                connection.close();
                //System.err.println("Database connection terminated");
            } catch (Exception e) { /* ignore close errors */ }
        }
    }
}
