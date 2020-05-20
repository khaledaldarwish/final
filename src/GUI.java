import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class GUI extends JFrame{
    
    int aralik=1;
    
    Date time=new Date();
    Date bitirme_zaman;
    
    int yan_kare=0;
    String mesaj="Devam et";
    
    public int mx=-100;
    public int my=-100;
    
    public int mesafeX=90;
    public int mesafeY=10;
    
    public int kaznma_mesaj_pozX=750;
    public int kaznma_mesaj_pozY=-50;
    
   public int isaret_pozX= 445;
   public int isaret_pozY = 5;
    
   public int isaret_MX=isaret_pozX+35;
   public int isaret_MY=isaret_pozY+35;
   
   
    public int saniye=0;
    
    public int smileX=605;
    public int smileY=5;
    
    public int zamanX=1100;
    public int zamanY=5;
    
    public boolean isaret_kontrol= false;
    
    public boolean sifirlama = false;
    
    public boolean kazanma=false;
    public boolean kayibetme=false;
    
    public boolean smile_gulme=true;
            
    Random rnd=new Random();
   
    
    int [][]mayinler_matrisi=new int [16][9];
    int [][]yan_matris=new int [16][9];
    boolean [][]goster_matris=new boolean[16][9];
    boolean [][]isaret_matris=new boolean[16][9];
    public GUI(){
      this.setTitle("Minesweeper");
      this.setSize(1286, 829);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(false);
       
      for(int i=0;i<16;i++)
      {
          for(int j=0;j<9;j++)
          {
              if(rnd.nextInt(100)<20)
              {
                mayinler_matrisi[i][j]=1;  
              }
              else{
               mayinler_matrisi[i][j]=0;     
              }
              goster_matris[i][j]=false;
          }
      }
      
      for(int i=0;i<16;i++){
          for(int j=0;j<9;j++){
              yan_kare=0;
              for(int m=0;m<16;m++){
                  for(int n=0;n<9;n++){
                      if(!(m==i && n==j)){
                        if(yan_karem(i,j,m,n)==true) 
                         yan_kare++; 
                      } 
                  }
              }
             yan_matris[i][j]=yan_kare; 
          }
      }
      
      Board board =new Board();
      this.setContentPane(board);
      
      Move move=new Move();
      this.addMouseMotionListener(move);
      
      Click click=new Click();
      this.addMouseListener(click);
    }
    
    
    
    public class Board extends JPanel{
        public void paintComponent(Graphics g){
          g.setColor(Color.DARK_GRAY);
          g.fillRect(0, 0, 1280, 800);
          
          for(int i=0;i<16;i++){
              for(int j=0;j<9;j++){
                  g.setColor(Color.gray);
                 
                 
                  if(goster_matris[i][j] == true)
                  {
                      g.setColor(Color.white);
                      if(mayinler_matrisi[i][j] == 1)
                      {
                          g.setColor(Color.red);
                      }
                  }
                  if(mx >=aralik+i*80 && mx < i*80+80-aralik && my >=aralik+j*80+80+26  && my < j*80+186-aralik )
                  {
                    g.setColor(Color.lightGray);  
                  }
                  g.fillRect(aralik+i*80, aralik+j*80+80, 80-2*aralik,80-2*aralik );
                  if(goster_matris[i][j] == true)
                  {
                     g.setColor(Color.black);
                     if(mayinler_matrisi[i][j] == 0 && yan_matris[i][j] != 0){
                         if(yan_matris[i][j] == 1)
                         {
                             g.setColor(Color.CYAN);
                         }
                         else if(yan_matris[i][j] == 2)
                         {
                             g.setColor(Color.orange);
                         }
                         else if(yan_matris[i][j]== 3)
                         {
                            g.setColor(Color.red);   
                         }
                         else if(yan_matris[i][j] == 4)
                         {
                             g.setColor(new Color (0,0,128));
                         }
                         else if(yan_matris[i][j] == 5){
                              g.setColor(new Color (178,34,34));
                         }
                         else if(yan_matris[i][j] == 6){
                              g.setColor(new Color (72,209,204));
                         }
                         else if( yan_matris[i][j] == 8){
                             g.setColor(Color.darkGray);
                         }
                         
                         g.setFont(new Font("Tahoma", Font.BOLD,40));
                         g.drawString(Integer.toString(yan_matris[i][j]), i*80+27, j*80+80+55);
                     }
                     else if(mayinler_matrisi[i][j] == 1)
                     {
                         g.fillRect(i*80+10+20, j*80+80+20, 20, 40);
                         g.fillRect(i*80+20, j*80+80+10+20, 40, 20);
                         g.fillRect(i*80+5+20, j*80+80+5+20, 30, 30);
                         g.fillRect(i*80+38, j*80+80+15, 4, 50);
                         g.fillRect(i*80+15, j*80+80+38, 50, 4);
                     }
                      
                  }
                 
                  if(isaret_matris[i][j]== true){
                       g.setColor(Color.BLACK);
                       g.fillRect(i*80+36, j*80+80+15, 6, 50);
                       g.fillRect(i*80+24, j*80+80+60, 30, 10);
                       g.setColor(Color.red);
                       g.fillRect(i*80+12, j*80+80+15, 30, 20);
                       g.setColor(Color.BLACK);
                       g.drawRect(i*80+12, j*80+80+15, 29, 19);
                      g.drawRect(i*80+12, j*80+80+15, 28, 18);
                      g.drawRect(i*80+12, j*80+80+15, 27, 17);
                  }
                  
                  
              }
          }
          
          
          
          
          g.setColor(Color.yellow);
          g.fillOval(smileX, smileY, 70, 70);
          g.setColor(Color.BLACK);
          g.fillOval(smileX+15, smileY+15, 10, 10);
          g.fillOval(smileX+50, smileY+15, 10, 10);
          if(smile_gulme == true){
              
              g.fillRect(smileX+20, smileY+50, 30, 5);
              g.fillRect(smileX+15, smileY+45, 5, 5);
              g.fillRect(smileX+50, smileY+45, 5, 5);
          }
          else{
               g.fillRect(smileX+20, smileY+45, 30, 5);
              g.fillRect(smileX+15, smileY+50, 5, 5);
              g.fillRect(smileX+50, smileY+50, 5, 5);
          }
          
          
          g.setColor(Color.BLACK);
          g.fillRect(isaret_pozX+32, isaret_pozY+15, 5, 40);
          g.fillRect(isaret_pozX+20, isaret_pozY+50, 30, 5);
          g.setColor(Color.red);
          g.fillRect(isaret_pozX+16, isaret_pozY+15, 20, 15);
          g.setColor(Color.BLACK);
          g.drawRect(isaret_pozX+16, isaret_pozY+15, 20, 15);
          g.drawRect(isaret_pozX+17,  isaret_pozY+16,18, 13);
          g.drawRect(isaret_pozX+18,  isaret_pozY+17,16, 11);
           
           if(isaret_kontrol == true){
               g.setColor(Color.red); 
               
           }
           g.drawOval(isaret_pozX, isaret_pozY, 70, 70);
           g.drawOval(isaret_pozX+1, isaret_pozY+1, 68, 68);
           g.drawOval(isaret_pozX+2, isaret_pozY+2, 66, 66);
          
          
           
            
          g.setColor(Color.black);
          g.fillRect(zamanX,zamanY, 170, 70);
          if(kayibetme == false && kazanma == false){
          saniye=(int)(new Date().getTime()-time.getTime())/1000;
          }
          
          g.setColor(Color.WHITE);
          if(kazanma == true){
              g.setColor(Color.green);
          }
          else if(kayibetme == true){
              g.setColor(Color.red);
              
          }
          g.setFont(new Font("Thoma",Font.PLAIN,72));
          g.drawString(Integer.toString(saniye), zamanX,zamanY+60);
          
          if(kazanma == true){
              g.setColor(Color.GREEN);
               mesaj="Tebrikler kazandin";
          }
          else if(kayibetme == true){
              g.setColor(Color.red);
               mesaj="Oyun bitti";
          }
          
          
          if(kazanma == true || kayibetme == true){
             kaznma_mesaj_pozY=-50 +(int) (new Date().getTime() - bitirme_zaman.getTime()) /10; 
             if(kaznma_mesaj_pozY > 70){
                 kaznma_mesaj_pozY=70;
                 
             }
             g.setFont(new Font("Tahoma",Font.LAYOUT_NO_LIMIT_CONTEXT,70));
             g.drawString(mesaj, kaznma_mesaj_pozX, kaznma_mesaj_pozY);
          }
        }
    }
    
    public class Move implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx=e.getX();
            my=e.getY();
            
            
        }
        
    }
    
    public class Click implements  MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            mx=e.getX(); 
            my=e.getY();
            
           
          
            
            if(apsis()!= -1 && ordinat()!= -1){
           
            if(isaret_kontrol == true && goster_matris[apsis()][ordinat()] == false){
                if(isaret_matris[apsis()][ordinat()]== false){
                isaret_matris[apsis()][ordinat()] = true;
                }
                else{
                    isaret_matris[apsis()][ordinat()] = false;
                }
            }
            else{
                if(isaret_matris[apsis()][ordinat()] == false){
                goster_matris[apsis()][ordinat()]= true;
                }
            }
            }
           
            if(smileBotn() == true )
            {
                yeniden();
                
            }
            if(isaret_karesi() == true){
                
                if(isaret_kontrol == false){
                    isaret_kontrol=true;
                }
                else{
                    isaret_kontrol= false;
                }
                
            }
            
        }
        

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        public boolean smileBotn(){
        int dif=(int)Math.sqrt(Math.abs(mx-640)*Math.abs(mx-640)+Math.abs(my-40)*Math.abs(my-40));
        if(dif < 35)
        {
            return true;
        }
        return false;
    }
        public boolean isaret_karesi(){
        int dif=(int)Math.sqrt(Math.abs(mx-480)*Math.abs(mx-480)+Math.abs(my-40)*Math.abs(my-40));
        if(dif < 35)
        {
            return true;
        }
        return false;
    }
        
        
    public int apsis()
    {
        for(int i=0;i<16;i++){
            for(int j=0;j<9;j++){
               if(mx >=aralik+i*80 && mx <aralik+i*80+80-2*aralik && my >=aralik+j*80+80+26  && my < aralik+j*80+26+80+80-2*aralik )
               {
                   return i;
               }
            }
        }
        return -1;
    }
    public int ordinat()
    {
       for(int i=0;i<16;i++){
            for(int j=0;j<9;j++){
               if(mx >=aralik+i*80 && mx <aralik+i*80+80-2*aralik && my >=aralik+j*80+80+26  && my < aralik+j*80+26+80+80-2*aralik )
               {
                   return j;
               }
            }
        } 
       return -1;
    }
}
    public void kazanma_Durumu(){
        if(kayibetme == false){
         for(int i=0;i<16;i++)
            { 
              for(int j=0;j<9;j++)
              {
               if(goster_matris[i][j] == true && mayinler_matrisi[i][j] == 1){
                   kayibetme=true;
                   smile_gulme=false;
                   bitirme_zaman = new Date();
                   
               }
              }
        
            } 
        }
         if(tum_kareleri_goster() >= 144 - mayim_tumu() && kazanma == false){
             kazanma = true;
             bitirme_zaman = new Date();
         }
    }
        public int  mayim_tumu(){
       int toplam=0;
            for(int i=0;i<16;i++)
            { 
              for(int j=0;j<9;j++)
              {
                if( mayinler_matrisi[i][j]==1){
                    toplam++;
                }
              }
          }
            
            return toplam;
        }
        public int tum_kareleri_goster()
        {
            int toplam = 0;
             for(int i=0;i<16;i++)
            { 
              for(int j=0;j<9;j++)
              {
                if( goster_matris[i][j] == true){
                   toplam++;
                }
              }
          }
            return toplam;
        }
    public void yeniden()
    {
        sifirlama  = true;
        time=new Date();
        isaret_kontrol=false;
        kaznma_mesaj_pozY= -50;
        mesaj="Devam et";
        smile_gulme=true;
        kazanma=false;
        kayibetme=false;
        
        for(int i=0;i<16;i++)
      {
          for(int j=0;j<9;j++)
          {
              if(rnd.nextInt(100)<20)
              {
                mayinler_matrisi[i][j]=1;  
              }
              else{
               mayinler_matrisi[i][j]=0;     
              }
              goster_matris[i][j]=false;
              isaret_matris[i][j]=false;
          }
      }
      
      for(int i=0;i<16;i++){
          for(int j=0;j<9;j++){
              yan_kare=0;
              for(int m=0;m<16;m++){
                  for(int n=0;n<9;n++){
                      if(!(m==i && n==j)){
                        if(yan_karem(i,j,m,n)==true) 
                         yan_kare++; 
                      } 
                  }
              }
             yan_matris[i][j]=yan_kare; 
          }
      }
      sifirlama = false;
    }
   
    
    
    public boolean yan_karem(int aX,int aY,int bX,int bY)
    {
        if( aX-bX < 2 && aX-bX > -2 && aY-bY < 2 && aY-bY > -2  && mayinler_matrisi[bX][bY]==1)
        {
           return true; 
        }
       
        return false;
    }
}
