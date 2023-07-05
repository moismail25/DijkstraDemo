package com.example.dijkstrademo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.dijkstrademo.model.Edge;
import com.example.dijkstrademo.model.Graph;
import com.example.dijkstrademo.model.Node;

public class GraphView extends View {
    public static final int RADIUS = 80;
    public static final int EDGE_OFFSET = 60;
    private Graph graph;
    private Paint nodePaint;

    private Paint edgePaint;
    private Paint textPaint;
    private Paint weightPaint;

    public GraphView(Context context) {
        super(context);
        init(null);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        // Initialize node paint
        nodePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nodePaint.setStyle(Paint.Style.FILL);

        // Initialize text paint
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(140f);
        textPaint.setTextAlign(Paint.Align.CENTER);

        // Initialize edge paint
        edgePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        edgePaint.setColor(Color.BLACK);
        edgePaint.setStyle(Paint.Style.STROKE);
        edgePaint.setStrokeWidth(10);

        // Initialize weight paint
        weightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        weightPaint.setColor(Color.BLACK);
        weightPaint.setTextSize(60f);
        weightPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (graph == null) {
            return;
        }

        // Draw edges
        for (Edge edge : graph.getEdges()) {
            if(edge.getStartNode().getX() == edge.getEndNode().getX()) {
                canvas.drawLine(
                        edge.getStartNode().getX()- EDGE_OFFSET, edge.getStartNode().getY(),
                        edge.getEndNode().getX()- EDGE_OFFSET, edge.getEndNode().getY(),
                        edgePaint
                );
                canvas.drawLine(
                        edge.getStartNode().getX()+ EDGE_OFFSET, edge.getStartNode().getY(),
                        edge.getEndNode().getX()+ EDGE_OFFSET, edge.getEndNode().getY(),
                        edgePaint
                );
            }else {
                canvas.drawLine(
                        edge.getStartNode().getX(), edge.getStartNode().getY()- EDGE_OFFSET,
                        edge.getEndNode().getX(), edge.getEndNode().getY()- EDGE_OFFSET,
                        edgePaint
                );
                canvas.drawLine(
                        edge.getStartNode().getX(), edge.getStartNode().getY()+ EDGE_OFFSET,
                        edge.getEndNode().getX(), edge.getEndNode().getY()+ EDGE_OFFSET,
                        edgePaint
                );
            }
            canvas.drawText(String.valueOf(edge.getWeight()), ((edge.getStartNode().getX()+edge.getEndNode().getX())/2), ((edge.getStartNode().getY()+edge.getEndNode().getY())/2)+20, weightPaint);
//            // Draw weights
//            if(edge.getStartNode().getX() == edge.getEndNode().getX()) {
//                canvas.drawText(String.valueOf(edge.getWeight()), ((edge.getStartNode().getX()+edge.getEndNode().getX())/2)-40, ((edge.getStartNode().getY()+edge.getEndNode().getY())/2)+20, weightPaint);
//            }else {
//                canvas.drawText(String.valueOf(edge.getWeight()), (edge.getStartNode().getX()+edge.getEndNode().getX())/2, ((edge.getStartNode().getY()+edge.getEndNode().getY())/2)-20, weightPaint);
//            }
        }

        // Draw nodes
        for (Node node : graph.getNodes()) {
            nodePaint.setColor(node.getColor());
            // Draw a circle
            canvas.drawCircle(node.getX(), node.getY(), RADIUS, nodePaint);
            // Draw the value
            canvas.drawText(String.valueOf(node.getVal()), node.getX(), node.getY() + 50, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();

            // Iterate over the nodes and check if the touch coordinates are within a node
            for (Node node : graph.getNodes()) {
                if (isWithinNode(touchX, touchY, node)) {
                    node.setColor(Color.LTGRAY); // Change the color of the touched node
                    invalidate();
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }
    private boolean isWithinNode(float touchX, float touchY, Node node) {
        float nodeX = node.getX();
        float nodeY = node.getY();
        float radius = RADIUS; // Adjust the radius of the node as needed

        float dx = touchX - nodeX;
        float dy = touchY - nodeY;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= radius * radius;
    }
}
