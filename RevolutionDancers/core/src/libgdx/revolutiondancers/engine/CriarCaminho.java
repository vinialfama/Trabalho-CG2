package libgdx.revolutiondancers.engine;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Vinícius
 */
public class CriarCaminho {
    
    Random posicao = Globals.randomGenerator2;//new Random();
    
    private LinkedList<Nodes> cells = new LinkedList<Nodes>();
    
    private LinkedList<Nodes> node = new LinkedList<Nodes>();
    
    public String[][] DesenhaCaminho(String[][] tabuleiro){
        
        String resultado="";
        
        arrumaObjeto(tabuleiro);
        
        int aux = nextInt(node.size());
        
        criaCorredor(node.get(aux), tabuleiro);

        return tabuleiro;
    }
    
    private int nextInt(int bound){
    	return Math.abs(posicao.nextInt(bound));
    }
    
    public void criaCorredor(Nodes n, String[][] tabuleiro){
        n.visit();
        cells.add(n);
        tabuleiro[n.getX()][n.getY()] = "E";
        //imprimeTabuleiro(tabuleiro);
        int rand=0;
            
        n=verificaNode(n);
        
        if(cells.size()>0){

            rand = nextInt(n.buscarTodosNodes().size());

            while(n.buscarNodePos(rand).getVisited()){
                rand = nextInt(n.buscarTodosNodes().size()); 
            }

            if(n.getX()>n.buscarNodePos(rand).getX()){
                for(Nodes no:n.buscarTodosNodes()){
                    if(no.getY()<n.getY()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                    if(no.getY()>n.getY()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                }
            }
            else if(n.getX()<n.buscarNodePos(rand).getX()){
                for(Nodes no:n.buscarTodosNodes()){
                    if(no.getY()>n.getY()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                    if(no.getY()<n.getY()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                }
            }
            else if(n.getY()>n.buscarNodePos(rand).getX()){
                for(Nodes no:n.buscarTodosNodes()){
                    if(no.getX()>n.getX()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                    if(no.getX()<n.getX()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                }
            }
            else if(n.getY()<n.buscarNodePos(rand).getX()){
                for(Nodes no:n.buscarTodosNodes()){
                    if(no.getX()<n.getX()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                    if(no.getX()>n.getX()){
                        if(tabuleiro[no.getX()][no.getY()].equals("-")){
                            no.visit();
                            tabuleiro[no.getX()][no.getY()] = "5";
                        }
                    }
                }
            }

            if(!n.buscarNodePos(rand).getVisited()){
                criaCorredor(n.buscarNodePos(rand), tabuleiro);
            }
        }
    }
    
    public void arrumaObjeto(String[][] tabuleiro){
        
        Nodes au = null;
        
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if(tabuleiro[i][j].equals("-")){
                    
                    Nodes nodei = new Nodes(i,j);
                    node.add(nodei);
                }
            }
        }
        
        for(Nodes n : node){
            
            if(n.getX()-1 >= 0){
                au = procuraNode(n.getX()-1,n.getY());
                if(au != null){
                    n.addVizinho(au);
                }
            }
            au = null;
            if(n.getX()+1 < tabuleiro.length){
                au = procuraNode(n.getX()+1,n.getY());
                if(au != null){
                    n.addVizinho(au);
                }
            }
            au = null;
            if(n.getY()-1 >= 0){
                au = procuraNode(n.getX(),n.getY()-1);
                if(au != null){
                    n.addVizinho(au);
                }
            }
            au = null;
            if(n.getX()+1 < tabuleiro.length){
                au = procuraNode(n.getX(),n.getY()+1);
                if(au != null){
                    n.addVizinho(au);
                }
            }
            au = null;
        }
    }
    
    public Nodes procuraNode(int x, int y){
        
        Nodes aux = null;
        
        for(Nodes node:node){
            if(node.getX() == x && node.getY() == y){
                aux = node;
            }
        }
        return aux;
    }
    
    public String imprimeTabuleiro(String[][] tabuleiro){
        String resultado="";
        
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[j][i].equals("1")) {
                    resultado = resultado + "1";
                } 
                else if (tabuleiro[j][i].equals("A")) {
                    resultado = resultado + "A";
                }
                else if (tabuleiro[j][i].equals("B")) {
                    resultado = resultado + "B";
                }
                else if (tabuleiro[j][i].equals("2")) {
                    resultado = resultado + "2";
                }
                else if (tabuleiro[j][i].equals("C")) {
                    resultado = resultado + "C";
                }
                else if (tabuleiro[j][i].equals("3")) {
                    resultado = resultado + "3";
                }
                else if (tabuleiro[j][i].equals("D")) {
                    resultado = resultado + "D";
                }
                else if (tabuleiro[j][i].equals("4")) {
                    resultado = resultado + "4";
                }
                else if (tabuleiro[j][i].equals("5")) {
                    resultado = resultado + "5";
                }
                else if (tabuleiro[j][i].equals("E")) {
                    resultado = resultado + "E";
                }
                else {
                    resultado = resultado + "-";
                }
            }
            resultado = resultado + "\n";
        }
        return resultado;
    }
    
    public Nodes verificaNode(Nodes n){
        Nodes aux =null;
        int cont=0;
        
        for(Nodes node: n.buscarTodosNodes()){
            if(!node.getVisited()){
                cont++;
            }
        }
        
        if(cont>0){
            aux =n;
        }
        else{
            cells.remove(cells.size()-1);
            if(cells.size()>0){
                aux = cells.getLast();
                aux = verificaNode(aux);
            }
        }
        
        return aux;
    }
}
