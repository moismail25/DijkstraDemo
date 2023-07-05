package com.example.dijkstrademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.dijkstrademo.model.Edge;
import com.example.dijkstrademo.model.Graph;
import com.example.dijkstrademo.model.Node;
import com.example.dijkstrademo.views.GraphView;

public class GameActivity extends AppCompatActivity {
    public static final int NODE_COLOR = Color.BLACK;
    public static final int NUM_OF_GRAPHS = 2;
    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Choose a graph randomly
        int selectedGraph = (int) Math.floor(Math.random() * NUM_OF_GRAPHS);

        graphView = findViewById(R.id.graphView);
        graphView.setGraph(getGraph(0));
    }

    public void submitSolution(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public Graph getGraph(int n) {
        Graph graph = new Graph();
        switch (n) {
            case 0: {
                // Create Nodes
                Node a = new Node(100, 100, 'A', Color.BLUE);
                Node b = new Node(100, 400, 'B', NODE_COLOR);
                Node c = new Node(950, 100, 'C', NODE_COLOR);
                Node d = new Node(500, 400, 'D', NODE_COLOR);
                Node e = new Node(950, 400, 'E', Color.BLUE);
                Node f = new Node(100, 700, 'F', NODE_COLOR);
                Node g = new Node(500, 700, 'G', NODE_COLOR);
                Node h = new Node(950, 700, 'H', NODE_COLOR);

                // Create Edges
                Edge ac = new Edge(a, c, 3);
                Edge ab = new Edge(a, b, 2);
                Edge bd = new Edge(b, d, 4);
                Edge dg = new Edge(d, g, 1);
                Edge de = new Edge(d, e, 2);
                Edge ce = new Edge(c, e, 5);
                Edge eh = new Edge(e, h, 3);
                Edge gh = new Edge(g, h, 1);
                Edge bf = new Edge(b, f, 7);
                Edge fg = new Edge(f, g, 2);

                // Add Nodes to the Graph
                graph.addNode(a);
                graph.addNode(b);
                graph.addNode(c);
                graph.addNode(d);
                graph.addNode(e);
                graph.addNode(f);
                graph.addNode(g);
                graph.addNode(h);

                // Add Edges to the Graph
                graph.addEdge(ac);
                graph.addEdge(ab);
                graph.addEdge(bd);
                graph.addEdge(dg);
                graph.addEdge(de);
                graph.addEdge(ce);
                graph.addEdge(eh);
                graph.addEdge(gh);
                graph.addEdge(bf);
                graph.addEdge(fg);
                break;
            }
            case 1: {
                // Create Nodes
                Node a = new Node(100, 100, 'A', Color.BLUE);
                Node b = new Node(500, 100, 'B', NODE_COLOR);
                Node c = new Node(950, 100, 'C', NODE_COLOR);
                Node d = new Node(100, 400, 'D', NODE_COLOR);
                Node h = new Node(500, 400, 'H', NODE_COLOR);
                Node f = new Node(950, 400, 'F', NODE_COLOR);
                Node g = new Node(100, 700, 'G', NODE_COLOR);
                Node e = new Node(500, 700, 'E', Color.BLUE);
                Node i = new Node(950, 700, 'I', NODE_COLOR);

                // Create Edges
                Edge ab = new Edge(a, b, 2);
                Edge ad = new Edge(a, d, 6);
                Edge bc = new Edge(b, c, 1);
                Edge bh = new Edge(b, h, 7);
                Edge dh = new Edge(d, h, 5);
                Edge dg = new Edge(d, g, 4);
                Edge cf = new Edge(c, f, 2);
                Edge he = new Edge(h, e, 1);
                Edge hf = new Edge(h, f, 3);
                Edge ge = new Edge(g, e, 2);
                Edge fi = new Edge(f, i, 5);

                // Add Nodes to the Graph
                graph.addNode(a);
                graph.addNode(b);
                graph.addNode(c);
                graph.addNode(d);
                graph.addNode(e);
                graph.addNode(f);
                graph.addNode(g);
                graph.addNode(h);
                graph.addNode(i);

                // Add Edges to the Graph
//                graph.addEdge(ac);
                graph.addEdge(ab);
                graph.addEdge(bc);
                graph.addEdge(bh);
                graph.addEdge(dh);
                graph.addEdge(cf);
                graph.addEdge(ad);
                graph.addEdge(dg);
                graph.addEdge(he);
                graph.addEdge(hf);
                graph.addEdge(ge);
                graph.addEdge(fi);
                break;
            }
            default:
                graph.addNode(new Node(100, 100, 'A', NODE_COLOR));
                graph.addNode(new Node(650, 100, 'C', NODE_COLOR));
                graph.addNode(new Node(400, 500, 'D', NODE_COLOR));
                break;
        }
        return graph;
    }
}