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

/* int partTwo(int actual, int count)
{
	if(count %3)
}
 */
int	main(void)
{
	FILE *fp;
   int c;
   int increase;
   int decrease;
	int blockIncrease;
   int blockDecrease;
   unsigned int	i;
   unsigned int forelast;
   unsigned int last;
   unsigned int actual;
   int *arrTmp;
   int block1;
   int block2;
   int	j;

	j = 1;
	block1 = 0;
	block2 = 0;
	blockIncrease = 0;
	blockDecrease = 0;
	forelast = 0;
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
		}
		else
		{
			//printf("%i\t", forelast);
			//printf("%i\t", last);
			//printf("%i\t", actual);
			if (actual >= last && actual != 0)
				increase++;
			else if (actual <= last && actual != 0)
				decrease++;
			if (j <= 3)
				block1 = block1 + actual;
			else if (j > 3)
			{
				block1 = forelast + last + actual;
			}
			forelast = last;
			last = actual;
			actual = 0;
			if (j >= 3)
			{
				if (block1 > block2 && block2 != 0)
					blockIncrease++;
				else if (block1 < block2 && block2 != 0)
					blockDecrease++;
				//printf("\n");
				printf("%i\t %i\t inc%i\n", block2, block1, blockIncrease);
				block2 = block1;
				block1 = 0;
			}
			j++;
		}
		i = 0;
   }
   printf("Part 1 = Increments:%i Decrements:%i\n", increase - 1, decrease);
   printf("Part 2 = Block Increments:%i Block Decrements:%i\n", blockIncrease, blockDecrease);
   fclose(fp);
   
   return(0);
}