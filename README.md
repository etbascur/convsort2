# convsort2
Attempt to make a more efficient merge sort
Link to google docs that says the same thing

https://docs.google.com/document/d/1GmQy7XFIYnPn9BmhwYE2bZ1PRgN3Z13saiOBWdy4x-Y/edit?usp=sharing

Enrique Bascur
Cs 245
Assignment 1

As an in place sorting algorithm I decided to use insertion sort, this is because at it’s best it gives me an O(n) run time, and compared to other in place algorithms we have tested, it frequently ran faster than other O(n^2) algorithms, likely because it minimizes the amount of “swaps” compared to selection sort or bubble sort, that being said, for small run sizes, it probably did not make too big a difference. 
My algorithm consistently ran slower than the mergesort algorithm, I believe this is because I created and copied two extra Arraylists that stored indices, which during the sorting process, were being sorted along side the main array that was holding the elements to be sorted, this triple sorting I believe slowed down the whole algorithm. 
During the testing process, at arrays bigger than 100k, a higher run Sizes would make my algorithm work faster, while at smaller run sizes it would slow down. However it was rare that my algorithm would find a run size of larger than 8, and unfortunately my algorithm does not work well if it does not find runs within the array. 
At lower run sizes both the merge function and the insertion sort have to be called many more times, versus at a higher run size. At worst case there are no runs, and the whole array is divided into sets of run sizes or smaller, which means merge would have to be called (arraySize/runSize) times  approximately, at best case you have O(n), in which the array is just one giant run and returns it.
This algorithm does outperform O(n^2) algorithms such as insertion sort, bubble sort and selection sort.
​
