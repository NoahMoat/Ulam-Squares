import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class main extends PApplet {

private Points points;
private Generations gens;

private float multiplyer = 14;
private int WIDTH = 1080;
private int HEIGHT = 720;
private int offset_x = WIDTH/2;
private int offset_y = HEIGHT/2;

private boolean mouse_button_released = true;
private int mouse_init_x;
private int mouse_init_y;
private int offset_init_x;
private int offset_init_y;

public int TYPE = 2;


public void setup() {
  
 points = new Points(0,30000);
 gens = new Generations(); 
 gens.CreateSquares(points);
}

public void draw() {
  background(0);
  fill(255,191,191);
  
  for(int i = points.start; i<points.size;i++) {
    stroke(255);
     int[] cords = points.GetValueAt(i);
     if(multiplyer > 5 && TYPE!=1) {
     point((cords[0]*=multiplyer)+offset_x,(cords[1]*=multiplyer)+offset_y);
     } else {
      cords[0]*=multiplyer;
      cords[1]*=multiplyer;
     }
     
  stroke(255, 255, 255, 50);
    switch(TYPE) {
      case 0:
        if (i*i < points.size) {stroke(255);line((cords[0]+offset_x),(cords[1]+offset_y),(points.GetValueAt(i*i)[0]*multiplyer+offset_x),(points.GetValueAt(i*i)[1]*multiplyer+offset_y));}
      break;
      case 1:
    float t = sqrt(i);  
    int t_1 = floor(t);

    float t_2 = t-t_1;
    int[] c1 = points.GetValueAt(t_1);
    int[] c2 = points.GetValueAt(t_1+1);
    if (t_2 == 0) {

      //print(c1[0]+":"+c1[1]+ " ");
      line(cords[0]+offset_x,cords[1]+offset_y,c1[0]*multiplyer+offset_x,c1[1]*multiplyer+offset_y);
    } else 
    if (c1[0] != c2[0]) {
      //print(c1[0] + ":" +c2[0] + " ");
      int dir = c2[0] - c1[0];
      t_2 *= dir;
      line(cords[0]+offset_x,cords[1]+offset_y,(t_2+c1[0])*multiplyer+offset_x,c1[1]*multiplyer+offset_y);
      
    } else 
    if (c1[1] != c2[1]) {
      int dir = c2[1] - c1[1];
      t_2 *= dir;
      line(cords[0]+offset_x,cords[1]+offset_y,c1[0]*multiplyer+offset_x,(t_2+c1[1])*multiplyer+offset_y);
    }
    break;
    case 2:
    if(i>1 && isPrimeNumber(i)) {
      fill(255,191,191);
      circle(cords[0]+offset_x,cords[1]+offset_y,multiplyer/1.5f);
      if (multiplyer > 10) {
      textSize(multiplyer/1.5f);
      fill(0,102,153);
      text(i,cords[0]+offset_x-multiplyer/2,cords[1]+offset_y);
      }
      
    }
    break;
    }
      
  }
  }


public boolean isPrimeNumber(int number) {
 boolean flag = true;
 for (int i=2; i <=number /2; ++i) {
  if (number%i == 0) {
   flag = false;
   break;
  }
 }
 return flag;
}

public void keyPressed() {
 if (keyCode == LEFT) {
   points = new Points(0,30000);
   TYPE=2;
 }
  if (keyCode == RIGHT) {
   points = new Points(0,30000);
   TYPE=0;
 }
   if (keyCode == UP) {
   points = new Points(0,2500);
   TYPE=1;
 }
}


public void mousePressed() {
 if (mouseButton == LEFT && mouse_button_released) {
    mouse_init_x = mouseX;
    mouse_init_y = mouseY;
    offset_init_x = offset_x;
    offset_init_y = offset_y;
 }
}

public void mouseReleased() {
   if (mouseButton == LEFT) {
    mouse_button_released = true;
    print(mouse_init_x,mouse_init_y);
    offset_init_x = offset_x;
    offset_init_y = offset_y;
 }
}

public void mouseDragged() {
  if (mouseButton == LEFT) {
    
    int mx = mouseX;
    int my = mouseY;
    int tempx = offset_init_x+(mx-mouse_init_x);
    int tempy = offset_init_y+(my-mouse_init_y);
    offset_x = tempx;
    offset_y = tempy;
  }
}

public void mouseWheel(MouseEvent event) {
 float e = event.getCount();
 float temp = this.multiplyer/4;
 if (temp <0.1f) temp=0.1f;
 if (e == 1.0f) {
  this.multiplyer -= temp; 
 }
 if (e == -1.0f) {
  this.multiplyer += temp;
 }
}
class Generations {
  public HashMap<Integer,String> squares = new HashMap<Integer,String>();
  
  
  public void CreateSquares(Points points) {
     for(int i = points.start; i<points.size;i++) {
     int[] cords = points.GetValueAt(i);
     if (i*i < points.size) {
    squares.put(i,
     (cords[0]+offset_x)+","+
     (cords[0]+offset_y)+","+
     (points.GetValueAt(i*i)[0]*multiplyer+offset_x)+","+
     (points.GetValueAt(i*i)[1]*multiplyer+offset_y)
     );
     }
     }
  }
  
  public int[] GetValueAt(int i,HashMap<Integer,String> x) {
    String val = x.get(i);
    int[] cords = PApplet.parseInt(val.split(","));
    return cords;
  }
  
}
class Points {
  public int start;
  public int size;
  public HashMap<Integer,String> points = new HashMap<Integer,String>();
  private boolean left,right,up,down = false;
  
  public Points(int start, int size) {
    this.start = start;
    this.size = size;
    createPoints();
  }
  
  
  private void createPoints() {
    int ringSize = 0;
    int ringCount = 0;
    int x = 0;
    int y = 0;
    String position = "0,0";
    this.right = true;
    
    
    for(int i =0; i < this.size; i++) {
      
      
      
      position = x+","+y;
      this.points.put(i,position); 
      //print(position + " rs:" + ringSize + " rc:" + ringCount + " ");
     
      ringCount++;
      
      if(this.up) {
        y--;
      }
      if (this.right) {
        x++;
      }
      if (this.down) {
        y++;
      }
      if (this.left) {
        x--;
      }
      
      
       if (ringCount == ringSize+1) {
          set();
          this.up = true;
           
          ringCount = 1;

          ringSize += 8;
          if (ringSize == 0) {
            ringSize = 8;
          }
        }
        
      if (ringCount == ringSize/4) {
        set();
        this.left = true;
      }
      if (ringCount == ringSize/4 + ringSize/4) {
        set();
        this.down = true;
      }
      if (ringCount == ringSize/4 + ringSize/4+ ringSize/4) {
        set();
        this.right = true;
      }  
      //print(ringCount + "," + ringSize + " ");
      }
  }
  
  private void set() {
    this.left = false;
    this.right = false;
    this.up = false;
    this.down = false;
  }
  
  public int[] GetValueAt(int i) {
    String val = points.get(i);
    int[] cords = PApplet.parseInt(val.split(","));
    int[] result = {cords[0],cords[1]};
    return result; 
  }
  
}
  public void settings() {  size(1080,720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
