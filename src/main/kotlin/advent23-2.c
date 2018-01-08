main() {

  int candidate = 0;
  int end = 0;
  int factor1 = 0;
  int factor2 = 0;
  int FLAG = 0;
  int g = 0;
  int h = 0;

  int mult = 0;
  int DEBUG = 0;
  int FLAG_OFF = 0;
  int FLAG_ON = 1;

  // INITIALISATION
  if (DEBUG) {   
    candidate = 105700;  
    end = 122700;
    mult++;
  } else {
    candidate = 57;        
    end = 57;         
  }     

  do {
    FLAG = FLAG_OFF;          
    factor1 = 2;              
    factor2 = 2;          
  four: 

    mult++;
    if (factor1 * factor2 == candidate) FLAG = FLAG_ON;
  
    factor2++;  
    if (factor2 != candidate ) {
      goto four;
    } 

    factor1++;           
    if (factor1 != candidate ) {
      factor2 = 2;
      goto four;
    }

    if (FLAG) {
      h++;
    }
  
    if (candidate!=end) {
      candidate = candidate + 17;
    }
  } while (candidate!=end);

  printf("mults: %d\n",mult);
  printf("h is: %d\n",h);
}
