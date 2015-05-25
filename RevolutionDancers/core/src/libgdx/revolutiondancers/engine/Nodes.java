package libgdx.revolutiondancers.engine;

import java.util.LinkedList;

/**
 *
 * @author Vinícius
 */
public class Nodes {
    
    LinkedList<Nodes> vizinhos = new LinkedList<Nodes>();
    private boolean visited = false;
    private int x;
    private int y;
    
    public Nodes(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean getVisited(){
        return visited;
    }
    
    public Nodes buscarNode(Nodes n){
        
        Nodes aux = null;
        
        for(Nodes no : vizinhos){
            if(no.equals(n)){
                aux = n;
            }
        }
        return aux;
    }
    
    public Nodes buscarNodePos(int n){
        Nodes aux = null;
        
        for(int i = 0; i < vizinhos.size();i++){
            if(i==n){
                aux = vizinhos.get(i);
            }
        }
        return aux;
    }
    
    public LinkedList<Nodes> buscarTodosNodes(){
        return vizinhos;
    }
    
    public void visit(){
        visited = true;
    }
    
    public void addVizinho(Nodes node){
        vizinhos.add(node);
    }
}
