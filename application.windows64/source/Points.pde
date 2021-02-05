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
    int[] cords = int(val.split(","));
    int[] result = {cords[0],cords[1]};
    return result; 
  }
  
}
