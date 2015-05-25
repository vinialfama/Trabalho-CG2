package libgdx.revolutiondancers.engine;

import java.util.LinkedList;

/**
 *
 * @author Vinícius
 */
public class Rooms {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private String type;
    private LinkedList<Ligacoes> ligacoes = new LinkedList<Ligacoes>();
    
    
    public Rooms(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return type;
    }
    
    public void addLigacao(Ligacoes l){
        if(l != null){
            ligacoes.add(l);
        }
    }
    
    public Ligacoes buscaLigacao(Ligacoes l){
        
        Ligacoes aux = null;
        
        if(l != null){
            for(Ligacoes a: ligacoes){
                if(a.getX()==l.getX() && a.getY()==l.getY()){
                    aux = l;
                }
            }
        }
        
        return aux;
    }
    
    public LinkedList<Ligacoes> buscaTodasLigacoes(){
        return ligacoes;
    }
}