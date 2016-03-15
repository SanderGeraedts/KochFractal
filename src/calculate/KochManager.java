/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

/**
 *
 * @author Sander Geraedts - Code Panda
 */
public class KochManager implements Observer{
    private JSF31KochFractalFX application;
    private KochFractal koch;
    private List<Edge> edges;

    public KochManager(JSF31KochFractalFX application) {
        this.application = application;
        this.koch = new KochFractal();
        this.edges = new ArrayList<>();
        koch.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        edges.add((Edge) arg);
    }

    public void changeLevel(int nxt) {
        edges.clear();
        koch.setLevel(nxt);
        TimeStamp calc = new TimeStamp();
        calc.setBegin("Level " + (koch.getLevel()-1));
        koch.generateBottomEdge();
        koch.generateLeftEdge();
        koch.generateRightEdge();
        calc.setEnd("Level " + koch.getLevel());
        application.setTextCalc(calc.toString());
        drawEdges();
    }

    public void drawEdges() {
        application.clearKochPanel();
        TimeStamp time = new TimeStamp();
        time.setBegin("Level " + (koch.getLevel()-1));
        for(Edge e : edges) {
            application.drawEdge(e);
        }
        time.setEnd("Level " + koch.getLevel());
        application.setTextDraw(time.toString());        
        application.setTextNrEdges("" + this.edges.size());
    }
    
    
}
