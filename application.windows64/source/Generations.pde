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
    int[] cords = int(val.split(","));
    return cords;
  }
  
}
