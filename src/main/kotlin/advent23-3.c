main() {

  int candidate = 0;
  int end = 0;
  int h = 0;

  int DEBUG = 1;

  if (DEBUG) {   
    candidate = 105700;  
    end = 122700;
  } else {
    candidate = 57;        
    end = 57;         
  }     

  for (; candidate < end + 1; candidate += 17) {
    for (int factor = 2; factor < candidate; factor++) {
      if (candidate % factor == 0) {
        h++;
        break;
      }
    }
  } 

  printf("h is: %d\n",h);
}
