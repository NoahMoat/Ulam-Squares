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


void setup() {
 size(1080,720); 
 points = new Points(0,20000);
 gens = new Generations(); 
 gens.CreateSquares(points);
}

void draw() {
  background(0);
  fill(255,191,191);
  for(int i = points.start; i<points.size;i++) {
     int[] cords = points.GetValueAt(i);
     point((cords[0]*=multiplyer)+offset_x,(cords[1]*=multiplyer)+offset_y);
     
    if (i*i < points.size) {
    stroke(255);
    line(
     (cords[0]+offset_x),
     (cords[1]+offset_y),
     (points.GetValueAt(i*i)[0]*multiplyer+offset_x),
     (points.GetValueAt(i*i)[1]*multiplyer+offset_y)
    );
    }
    }
  }


public boolean isPrimeNumber(int number) {
 for(int i=0; i <sqrt(number) ; i++){
    if(number%i ==0){
       return false;
    }
 }
 return true;
}


void mousePressed() {
 if (mouseButton == LEFT && mouse_button_released) {
    mouse_init_x = mouseX;
    mouse_init_y = mouseY;
    offset_init_x = offset_x;
    offset_init_y = offset_y;
 }
}

void mouseReleased() {
   if (mouseButton == LEFT) {
    mouse_button_released = true;
    print(mouse_init_x,mouse_init_y);
    offset_init_x = offset_x;
    offset_init_y = offset_y;
 }
}

void mouseDragged() {
  if (mouseButton == LEFT) {
    
    int mx = mouseX;
    int my = mouseY;
    int tempx = offset_init_x+(mx-mouse_init_x);
    int tempy = offset_init_y+(my-mouse_init_y);
    offset_x = tempx;
    offset_y = tempy;
  }
}

void mouseWheel(MouseEvent event) {
 float e = event.getCount();
 float temp = this.multiplyer/4;
 if (temp <0.1) temp=0.1;
 if (e == 1.0) {
  this.multiplyer -= temp; 
 }
 if (e == -1.0) {
  this.multiplyer += temp;
 }
}
