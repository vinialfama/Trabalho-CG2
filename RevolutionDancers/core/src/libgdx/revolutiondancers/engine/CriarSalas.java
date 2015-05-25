package libgdx.revolutiondancers.engine;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Vinícius
 */
public class CriarSalas {
    
   
    Random posicao = Globals.randomGenerator2;//new Random();
    
    LinkedList<Rooms> salasCriadas = new LinkedList<Rooms>();
    
    String[][] tabuleiro;
    
    private int nextInt(int bound){
    	return Math.abs(posicao.nextInt(bound));
    }
    
    public String[][] DesenhaSala(int dificuldade){
        
        int sala1=0;
        int sala2=0;
        int sala3=0;
        int sala4=0;
        int totalSalas = 0;
        int cont=0;
        
        tabuleiro = new String[dificuldade*10][dificuldade*10];
        
        iniciaTabuleiro(tabuleiro);
        
        cont = (dificuldade*10)/4;
        
        while(cont >0){
            int width = 5 + nextInt(2);
            int height = 5 + nextInt(2);
            
            int x = nextInt(dificuldade*10);
            int y = nextInt(dificuldade*10);
            
            Rooms atual = new Rooms(x,y,width,height);
            
            if(x > 0 && ((x + width) < (dificuldade*10)-1)){

                if(y >0 &&((y + height) < (dificuldade*10)-1)){

                    if(verificaSala(atual, salasCriadas)){
                        salasCriadas.add(atual);
                        cont--;
                    }
                }
            }
        }
        
        totalSalas = (dificuldade*10)/4;
        
        while(totalSalas > 0){
            
            int type = nextInt(4);
            if(type==0){
                sala1++;
                totalSalas--;
            }
            else if(type==1){
                sala2++;
                totalSalas--;
            }
            else if(type==2){
                sala3++;
                totalSalas--;
            }
            else if(type==4){
                sala4++;
                totalSalas--;
            }
            
        }
        
        if(sala1>0){

            for (int n=0;n<sala1;n++) {

                salasCriadas.get(n).setType("A");

                for(int j=salasCriadas.get(n).getX();j<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());j++){
                    for(int k=salasCriadas.get(n).getY();k<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());k++){
                        tabuleiro[j][k] = "A";
                    }
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()] = "1";
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight()] = "1";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()][i] = "1";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth()][i] = "1";
                }
            }
        }
        else{
            sala1 = 0;
        }
        if(sala2 > 0){
            for (int n=sala1;n<sala1+sala2;n++) {
                salasCriadas.get(n).setType("B");

                for(int j=salasCriadas.get(n).getX();j<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());j++){
                    for(int k=salasCriadas.get(n).getY();k<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());k++){
                        tabuleiro[j][k] = "B";
                    }
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()] = "2";
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight()] = "2";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()][i] = "2";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth()][i] = "2";
                }
            }
        }
        else {
            sala2 = 0;
        }
        if(sala3 > 0){
            for (int n=sala1+sala2;n<sala1+sala2+sala3;n++) {
                salasCriadas.get(n).setType("C");

                for(int j=salasCriadas.get(n).getX();j<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());j++){
                    for(int k=salasCriadas.get(n).getY();k<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());k++){
                        tabuleiro[j][k] = "C";
                    }
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()] = "3";
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight()] = "3";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()][i] = "3";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth()][i] = "3";
                }
            }
        }
        else{
            sala3 = 0;
        }
        if(sala4>0){
            for (int n=sala1+sala2+sala3;n<sala1+sala2+sala3+sala4;n++) {
                salasCriadas.get(n).setType("D");

                for(int j=salasCriadas.get(n).getX();j<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());j++){
                    for(int k=salasCriadas.get(n).getY();k<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());k++){
                        tabuleiro[j][k] = "D";
                    }
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()] = "4";
                }
                for(int i=salasCriadas.get(n).getX();i<=(salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth());i++){
                    tabuleiro[i][salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight()] = "4";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()][i] = "4";
                }
                for(int i=salasCriadas.get(n).getY();i<=(salasCriadas.get(n).getY()+salasCriadas.get(n).getHeight());i++){
                    tabuleiro[salasCriadas.get(n).getX()+salasCriadas.get(n).getWidth()][i] = "4";
                }
            }
        }
               
        return tabuleiro;
    }
    
    public boolean verificaSala(Rooms a, LinkedList<Rooms> lista){
        
        boolean adiciona = true;
        
        if(lista.isEmpty()){
            return adiciona;
        }
        else{
            for(Rooms b : lista){

                if((a.getX() <= b.getX() && (a.getX() + a.getWidth()) >= b.getX()) && (a.getY() <= b.getY() && (a.getY() + a.getHeight()) >= b.getY()) || (a.getX() <= (b.getX() + b.getWidth()) && (a.getX() + a.getWidth()) >= (b.getX() + b.getWidth())) && (a.getY() <= (b.getY() + b.getHeight()) && (a.getY() + a.getHeight()) >= (b.getY() + b.getHeight())) || (a.getX() <= b.getX() && (a.getX() + a.getWidth()) >= b.getX()) && (a.getY() <= (b.getY() + b.getHeight()) && (a.getY() + a.getHeight()) >= (b.getY() + b.getHeight())) || (a.getX() <= (b.getX() + b.getWidth()) && (a.getX() + a.getWidth()) >= (b.getX() + b.getWidth())) && (a.getY() <= b.getY() && (a.getY() + a.getHeight()) >= b.getY())){
                    adiciona = false;
                    break;
                }
            }
        }
        return adiciona;
    }
    
    public void iniciaTabuleiro(String[][] tabuleiro){
        
        
        for(int i=0;i < tabuleiro.length;i++){
            for(int j=0;j<tabuleiro.length;j++){
            
                if(i==0){
                    tabuleiro[i][j] = "5";
                }
                else if(i==tabuleiro.length-1){
                    tabuleiro[i][j] = "5";
                }
                else if(j==0){
                    tabuleiro[i][j] = "5";
                }
                else if(j==tabuleiro.length-1){
                    tabuleiro[i][j] = "5";
                }
                else{
                    tabuleiro[i][j] = "-";
                }
            }
        }
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
    
    public LinkedList<Rooms> getSalas(){
        return salasCriadas;
    }
}