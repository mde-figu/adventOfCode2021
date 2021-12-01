# include <unistd.h>
# include <stdlib.h>
# include <stdio.h>

unsigned int concatenate(unsigned int x, unsigned int y)
{
	unsigned pow = 10;

	while(y >= pow)
		pow *= 10;
	return x * pow + y;
}

int	main(void)
{
	FILE *fp;
   int c;
   int increase;
   int decrease;
   unsigned int	i;
   unsigned int last;
   unsigned int actual;


	last = 0;
	actual = 0;
	increase = 0;
	decrease = 0;
	fp = fopen("./input", "r+");
	while(1)
	{
		c = fgetc(fp);
		i = c - 48;
		if( feof(fp) )
		{ 
			break ;
		}
		if (i != -38)
		{
			//printf("%i", i);
			actual = concatenate(actual, i);
			//printf(":%i", actual);
		}
		else
		{
			if (actual >= last && actual != 0)
				increase++;
			else if (actual <= last && actual != 0)
				decrease++;
			last = actual;
			actual = 0;
			//printf("\n");
		}
		i = 0;
   }
   printf("Increments:%i Decrements:%i", increase - 1, decrease);
   fclose(fp);
   
   return(0);
}