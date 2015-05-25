package libgdx.revolutiondancers.engine;

import static java.lang.Math.abs;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Vinícius
 */
public class CustomizandoDungeon {
    
    Random posicao = Globals.randomGenerator2;//new Random();
    private int inicio;
    private int totalChavePrata;
    private int totalGemas;
    private int totalMonstros;
    private int totalBaus;
    
    public String[][] organizaDungeon(String[][] tabuleiro, LinkedList<Rooms> salas, int dificuldade){
        
        inicio = Math.abs(posicao.nextInt(4));
        totalChavePrata = 0;
        totalGemas = dificuldade*2 + (dificuldade/2);
        totalMonstros = dificuldade*2 - (dificuldade/2);
        totalBaus = dificuldade/2;
        boolean correto = false;
        int x=0;
        int y=0;
        
        
        arrumaObjeto(salas, tabuleiro);
            
        for(Rooms r1:salas){
        	
        	int totalPortas = 1;
            
            int aux = Math.abs(posicao.nextInt(2));
            
            totalPortas = totalPortas + aux;
            
            while(totalPortas>0){
                int pos = Math.abs(posicao.nextInt(Math.abs(r1.buscaTodasLigacoes().size())));
                
                int type = Math.abs(posicao.nextInt(2));

                if(type==0){
                    tabuleiro[r1.buscaTodasLigacoes().get(pos).getX()][r1.buscaTodasLigacoes().get(pos).getY()] = "N";
                }
                else{
                    tabuleiro[r1.buscaTodasLigacoes().get(pos).getX()][r1.buscaTodasLigacoes().get(pos).getY()] = "T";
                    totalChavePrata++;
                }
                totalPortas--;
            }
        }
        
        if(inicio == 0 && !correto){
            for(int i =0;i < 6;i++){
                for(int j =0;j < 6;j++){
                    if(tabuleiro[i][j].equals("A") || tabuleiro[i][j].equals("B") || tabuleiro[i][j].equals("C") || tabuleiro[i][j].equals("D") || tabuleiro[i][j].equals("E")){
                        tabuleiro[i][j] = "I";
                        x = i;
                        y = j;
                        correto = true;
                        break;
                    }
                }
                if(correto){
                    break;
                }
            }
        }
        if(inicio == 1 && !correto){
            for(int i =0;i < 6;i++){
                for(int j =tabuleiro.length-1;j > tabuleiro.length-6;j--){
                    if(tabuleiro[i][j].equals("A") || tabuleiro[i][j].equals("B") || tabuleiro[i][j].equals("C") || tabuleiro[i][j].equals("D") || tabuleiro[i][j].equals("E")){
                        tabuleiro[i][j] = "I";
                        x = i;
                        y = j;
                        correto = true;
                        break;
                    }
                }
                if(correto){
                    break;
                }
            }
        }
        if(inicio == 2 && !correto){
            for(int i =tabuleiro.length-1;i > tabuleiro.length-6;i--){
                for(int j =tabuleiro.length-1;j > tabuleiro.length-6;j--){
                    if(tabuleiro[i][j].equals("A") || tabuleiro[i][j].equals("B") || tabuleiro[i][j].equals("C") || tabuleiro[i][j].equals("D") || tabuleiro[i][j].equals("E")){
                        tabuleiro[i][j] = "I";
                        x = i;
                        y = j;
                        correto = true;
                        break;
                    }
                }
                if(correto){
                    break;
                }
            }
        }
        if(inicio == 3 && !correto){
            for(int i =tabuleiro.length-1;i > tabuleiro.length-6;i--){
                for(int j =0;j < 6;j++){
                    if(tabuleiro[i][j].equals("A") || tabuleiro[i][j].equals("B") || tabuleiro[i][j].equals("C") || tabuleiro[i][j].equals("D") || tabuleiro[i][j].equals("E")){
                        tabuleiro[i][j] = "I";
                        x = i;
                        y = j;
                        correto = true;
                        break;
                    }
                }
                if(correto){
                    break;
                }
            }
        }
        
        addFim(x, y, salas, tabuleiro);
        
        while(totalChavePrata>0 && totalMonstros>0 && totalGemas>0 && totalBaus>0){
            
            int bau = 0;
            int item = 0;
            int posX = 0;
            int posY = 0;
            int monst = 0;
            
            for(Rooms r:salas){
                
                item = Math.abs(posicao.nextInt(3));
                
                if(item==0 && totalChavePrata >0){
                    bau = Math.abs(posicao.nextInt(3));
                    monst = Math.abs(posicao.nextInt(3));
                    if(bau==0){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "Z";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                        totalChavePrata++;
                    }
                    if(bau==1){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "P";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                    }
                    if(bau==2){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "X";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                    }
                    totalChavePrata--;
                }
                
                if(item==1 && totalGemas >0){
                    bau = Math.abs(posicao.nextInt(3));
                    monst = Math.abs(posicao.nextInt(3));
                    if(bau==0){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "H";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                        totalChavePrata++;
                    }
                    if(bau==1){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "G";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                    }
                    if(bau==2){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "J";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                    }
                    totalGemas--;
                }
                if(item==2 && totalBaus>0){
                    bau = Math.abs(posicao.nextInt(2));
                    monst = Math.abs(posicao.nextInt(3));
                    if(bau==0){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "L";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                        totalChavePrata++;
                    }
                    if(bau==1){
                        while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                            posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                            posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                        }
                        tabuleiro[r.getX()+posX][r.getY()+posY] = "O";
                        posX = 0;
                        posY = 0;
                        if(monst==0){
                            while(!tabuleiro[r.getX()+posX][r.getY()+posY].equals(r.getType())){
                                posX = Math.abs(posicao.nextInt(r.getWidth()-1));
                                posY = Math.abs(posicao.nextInt(r.getHeight()-1));
                            }
                            tabuleiro[r.getX()+posX][r.getY()+posY] = "M";
                        }
                    }
                    totalBaus--;
                }
            }
        }
        
        return tabuleiro;
    }
    
    public void addFim(int x, int y, LinkedList<Rooms> salas, String[][] tabuleiro){
        
        int atualX = 0;
        int atualX2 = 0;
        int atualY = 0;
        int bau = 0;
        int monst = 0;
        int posX = 0;
        int posY = 0;
        Rooms auxX=null;
        Rooms auxY=null;
        
        for(Rooms r: salas){
            if(abs(r.getX() - x) > atualX){
                atualX = abs(r.getX() - x);
                auxX = r;
            }
        }
        
        bau = Math.abs(posicao.nextInt(3));
        monst = Math.abs(posicao.nextInt(2));
        if(bau==0){
            while(!tabuleiro[auxX.getX()+posX][auxX.getY()+posY].equals(auxX.getType())){
                posX = Math.abs(posicao.nextInt(auxX.getWidth()-1));
                posY = Math.abs(posicao.nextInt(auxX.getHeight()-1));
            }
            tabuleiro[auxX.getX()+posX][auxX.getY()+posY] = "V";
            posX = 0;
            posY = 0;
            if(monst==0){
                while(!tabuleiro[auxX.getX()+posX][auxX.getY()+posY].equals(auxX.getType())){
                    posX = Math.abs(posicao.nextInt(auxX.getWidth()-1));
                    posY = Math.abs(posicao.nextInt(auxX.getHeight()-1));
                }
                tabuleiro[auxX.getX()+posX][auxX.getY()+posY] = "M";
            }
            totalChavePrata++;
        }
        if(bau==1){
            while(!tabuleiro[auxX.getX()+posX][auxX.getY()+posY].equals(auxX.getType())){
                posX = Math.abs(posicao.nextInt(auxX.getWidth()-1));
                posY = Math.abs(posicao.nextInt(auxX.getHeight()-1));
            }
            tabuleiro[auxX.getX()+posX][auxX.getY()+posY] = "K";
            posX = 0;
            posY = 0;
            if(monst==0){
                while(!tabuleiro[auxX.getX()+posX][auxX.getY()+posY].equals(auxX.getType())){
                    posX = Math.abs(posicao.nextInt(auxX.getWidth()-1));
                    posY = Math.abs(posicao.nextInt(auxX.getHeight()-1));
                }
                tabuleiro[auxX.getX()+posX][auxX.getY()+posY] = "M";
            }
        }
        if(bau==2){
            while(!tabuleiro[auxX.getX()+posX][auxX.getY()+posY].equals(auxX.getType())){
                posX = Math.abs(posicao.nextInt(auxX.getWidth()-1));
                posY = Math.abs(posicao.nextInt(auxX.getHeight()-1));
            }
            tabuleiro[auxX.getX()+posX][auxX.getY()+posY] = "R";
            posX = 0;
            posY = 0;
            if(monst==0){
                while(!tabuleiro[auxX.getX()+posX][auxX.getY()+posY].equals(auxX.getType())){
                    posX = Math.abs(posicao.nextInt(auxX.getWidth()-1));
                    posY = Math.abs(posicao.nextInt(auxX.getHeight()-1));
                }
                tabuleiro[auxX.getX()+posX][auxX.getY()+posY] = "M";
            }
        }
        posX = 0;
        posY = 0;
        
        if(abs(x)<6){
        	if(abs(y)<6){
        		for(int i =1;i<7;i++){
                    if((tabuleiro[i][tabuleiro.length-1].equals("5")) && (tabuleiro[i][tabuleiro.length-2].equals("E"))){
                    	if(abs(i - x) > atualX2 && abs((tabuleiro.length-1) - y) > atualY){
                            atualX2 = abs(i - x);
                            atualY = abs((tabuleiro.length-1) - y);
                            posX = i;
                            posY = tabuleiro.length-1;
                        }
                    }
                }
        		for(int i =tabuleiro.length-2;i<tabuleiro.length-7;i--){
                    if((tabuleiro[0][i].equals("5")) && (tabuleiro[1][i].equals("E"))){
                    	if(abs(i - y) > atualY && abs(0 - x) > atualX2){
                            atualY = abs(i - y);
                            atualX2 = abs(0 - x);
                            posX = 0;
                            posY = i;
                        }
                    }
                }
        	}
        	else{
        		for(int i =tabuleiro.length-2;i<tabuleiro.length-7;i--){
                    if((tabuleiro[i][0].equals("5")) && (tabuleiro[i][1].equals("E"))){
                    	if(abs(0 - y) > atualY && abs(i - x) > atualX2){
                            atualY = abs(0 - y);
                            atualX2 = abs(i - x);
                            posX = i;
                            posY = 0;
                        }
                    }
                }
        		for(int i =1;i<7;i++){
                    if((tabuleiro[tabuleiro.length-1][i].equals("5")) && (tabuleiro[tabuleiro.length-2][i].equals("E"))){
                    	if(abs(tabuleiro.length-1 - x) > atualX2 && abs(i - y) > atualY){
                            atualX2 = abs(tabuleiro.length-1 - x);
                            atualY = abs(i - y);
                            posX = tabuleiro.length-1;
                            posY = i;
                        }
                    }
                }
        	}
        }
        else{
        	if(abs(y)<6){
        		for(int i =1;i<7;i++){
                    if((tabuleiro[i][tabuleiro.length-1].equals("5")) && (tabuleiro[i][tabuleiro.length-2].equals("E"))){
                    	if(abs(i - x) > atualX2 && abs((tabuleiro.length-1) - y) > atualY){
                            atualX2 = abs(i - x);
                            atualY = abs((tabuleiro.length-1) - y);
                            posX = i;
                            posY = tabuleiro.length-1;
                        }
                    }
                }
        		for(int i =tabuleiro.length-2;i<tabuleiro.length-7;i--){
                    if((tabuleiro[0][i].equals("5")) && (tabuleiro[1][i].equals("E"))){
                    	if(abs(i - y) > atualY && abs(0 - x) > atualX2){
                            atualY = abs(i - y);
                            atualX2 = abs(0 - x);
                            posX = 0;
                            posY = i;
                        }
                    }
                }
        		
        	}
        	else{
        		for(int i =1;i<7;i++){
                    if((tabuleiro[i][0].equals("5")) && (tabuleiro[i][1].equals("E"))){
                    	if(abs(i - x) > atualX2 && abs(0 - y) > atualY){
                            atualX2 = abs(i - x);
                            atualY = abs(0 - y);
                            posX = i;
                            posY = 0;;
                        }
                    }
                }
        		for(int i =1;i<7;i++){
                    if((tabuleiro[0][i].equals("5")) && (tabuleiro[1][i].equals("E"))){
                    	if(abs(i - y) > atualY && abs(0 - x) > atualX2){
                            atualY = abs(i - y);
                            atualX2 = abs(0 - x);
                            posX = 0;
                            posY = i;
                        }
                    }
                }
        	}
        }
        tabuleiro[posX][posY] = "F";
    }
    
    public void arrumaObjeto(LinkedList<Rooms> salas, String[][] tabuleiro){
        
        for(Rooms r:salas){
            for(int i =r.getX()+1;i<(r.getX()+r.getWidth());i++){
                if((tabuleiro[i][r.getY()-1].equals("1") || tabuleiro[i][r.getY()-1].equals("2") || tabuleiro[i][r.getY()-1].equals("3") || tabuleiro[i][r.getY()-1].equals("4") || tabuleiro[i][r.getY()-1].equals("E")) && (tabuleiro[i][r.getY()].equals("1") || tabuleiro[i][r.getY()].equals("2") || tabuleiro[i][r.getY()].equals("3") || tabuleiro[i][r.getY()].equals("4"))){
                    Ligacoes li = new Ligacoes(i, r.getY());
                    r.addLigacao(li);
                }
                if((tabuleiro[i][r.getY()+r.getHeight()+1].equals("1") || tabuleiro[i][r.getY()+r.getHeight()+1].equals("2") || tabuleiro[i][r.getY()+r.getHeight()+1].equals("3") || tabuleiro[i][r.getY()+r.getHeight()+1].equals("4") || tabuleiro[i][r.getY()+r.getHeight()+1].equals("E")) && (tabuleiro[i][r.getY()+r.getHeight()].equals("1") || tabuleiro[i][r.getY()+r.getHeight()].equals("2") || tabuleiro[i][r.getY()+r.getHeight()].equals("3") || tabuleiro[i][r.getY()+r.getHeight()].equals("4"))){
                    Ligacoes li = new Ligacoes(i, r.getY()+r.getHeight());
                    r.addLigacao(li);
                }
            }
            for(int i =r.getY()+1;i<(r.getY()+r.getHeight());i++){
                if((tabuleiro[r.getX()-1][i].equals("1") || tabuleiro[r.getX()-1][i].equals("2") || tabuleiro[r.getX()-1][i].equals("3") || tabuleiro[r.getX()-1][i].equals("4") || tabuleiro[r.getX()-1][i].equals("E")) && (tabuleiro[r.getX()][i].equals("1") || tabuleiro[r.getX()][i].equals("2") || tabuleiro[r.getX()][i].equals("3") || tabuleiro[r.getX()][i].equals("4"))){
                    Ligacoes li = new Ligacoes(r.getX(), i);
                    r.addLigacao(li);
                }
                if((tabuleiro[r.getX()+r.getWidth()+1][i].equals("1") || tabuleiro[r.getX()+r.getWidth()+1][i].equals("2") || tabuleiro[r.getX()+r.getWidth()+1][i].equals("3") || tabuleiro[r.getX()+r.getWidth()+1][i].equals("4") || tabuleiro[r.getX()+r.getWidth()+1][i].equals("E")) && (tabuleiro[r.getX()+r.getWidth()][i].equals("1") || tabuleiro[r.getX()+r.getWidth()][i].equals("2") || tabuleiro[r.getX()+r.getWidth()][i].equals("3") || tabuleiro[r.getX()+r.getWidth()][i].equals("4"))){
                    Ligacoes li = new Ligacoes(r.getX()+r.getWidth(), i);
                    r.addLigacao(li);
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
                else if (tabuleiro[j][i].equals("I")) {
                    resultado = resultado + "I";
                }
                else if (tabuleiro[j][i].equals("N")) {
                    resultado = resultado + "N";
                }
                else if (tabuleiro[j][i].equals("T")) {
                    resultado = resultado + "T";
                }
                else if (tabuleiro[j][i].equals("L")) {
                    resultado = resultado + "L";
                }
                else if (tabuleiro[j][i].equals("O")) {
                    resultado = resultado + "O";
                }
                else if (tabuleiro[j][i].equals("H")) {
                    resultado = resultado + "H";
                }
                else if (tabuleiro[j][i].equals("J")) {
                    resultado = resultado + "J";
                }
                else if (tabuleiro[j][i].equals("Z")) {
                    resultado = resultado + "Z";
                }
                else if (tabuleiro[j][i].equals("X")) {
                    resultado = resultado + "X";
                }
                else if (tabuleiro[j][i].equals("V")) {
                    resultado = resultado + "V";
                }
                else if (tabuleiro[j][i].equals("R")) {
                    resultado = resultado + "R";
                }
                else if (tabuleiro[j][i].equals("P")) {
                    resultado = resultado + "P";
                }
                else if (tabuleiro[j][i].equals("K")) {
                    resultado = resultado + "K";
                }
                else if (tabuleiro[j][i].equals("G")) {
                    resultado = resultado + "G";
                }
                else if (tabuleiro[j][i].equals("M")) {
                    resultado = resultado + "M";
                }
                else if (tabuleiro[j][i].equals("F")) {
                    resultado = resultado + "F";
                }
                else {
                    resultado = resultado + "-";
                }
            }
            resultado = resultado + "\n";
        }
        return resultado;
    }
    
}
