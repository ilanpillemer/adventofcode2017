main() {
  int a = 0;
  int b = 0;
  int c = 0;
  int d = 0;
  int e = 0;
  int f = 0;
  int g = 0;
  int h = 0;
  int mult = 0;

  b = 57;               /* set b 57 */
  c = b;                /* set c b */
  if (a!=0) goto one;   /* jnz a 2 */
  goto two;             /* jnz 1 5 */
 one: b = b * 100; mult++;      /* mul b 100 */
  b = b - -100000;      /* sub b -100000 */
  c = b;                /* set c b */
  c = c - -17000;       /* sub c -17000 */
 two: f = 1;            /* set f 1 */
  d = 2;                /* set d 2 */
 five: e = 2;           /* set e 2 */
 four: g = d;           /* set g d */
  g = g * e; mult++;           /* mul g e */
  g = g - b;            /* sub g b */
  if (g!=0) goto three; /* jnz g 2 */
  f = 0;                /* set f 0 */
 three: e = e - -1;     /* sub e -1 */
  g = e;                /* set g e */
  g = g -b;             /* sub g b */
  if (g!=0) goto four;  /* jnz g -8 */
  d = d - -1;           /* sub d -1 */
  g = d;                /* set g d */
  g = g - b;            /* sub g b */
  if (g!=0) goto five;  /* jnz g -13 */
  if (f!=0) goto six;   /* jnz f 2 */
  h = h - -1;           /* sub h -1 */
 six: g = b;            /* set g b */
  g = g - c;            /* sub g c */
  if (g!=0) goto seven; /* jnz g 2 */
  if (1!=0) goto eight; /* jnz 1 3 */
 seven: b = b - -17;    /* sub b -17 */
  if (1!=0) goto two;   /* jnz 1 -23 */
 eight:printf("mults: %d\n",mult);
  printf("h is: %d\n",h);
}
