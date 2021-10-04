/*****************************
*Student Name:Uriel Schwell  *
*Student ID: 327349031       *
*Course Exercise Group: 01   *
*Exercise Name: ex6          *
******************************/
#include "sorting.h"
#include "person.h"
#include <stdlib.h>
							
/***************************
*Function Name: SortCircleByID
*Input: Person* head
*Output: Person* head
*Function Operation: Will receive a circle and sort said circle into numerical order (based on each persons ID number)
	using a buble sort algorithm
	(added note: while the code is similar between the two sort functions, they are different enough that it
	was not worth creating a third sorting function (diffrentiating between the sorting types took up almost as
	many lines of code as I saved) therefore while there is a large overlap they ARE different functions)
****************************/
Person* SortCircleByID(Person* head)
{
	Person* endList = head;
	Person* startList = NULL;
	Person* advanceList;
	//this integer will be used to confirm we managed an entire pass without a single swap
	int didWeSwap = 1;
	//The entire function only runs in the event we received a circle with at LEAST two people in it
	if ((endList != NULL)&&(startList!=endList))
	{
		//we start at the head of the (existing) list
		startList = (head->next);
		//first, to give us an easy endpoint, we 'break' our circle into a list again
		endList->next = NULL;
		//we start our sort from the start of our new list
		advanceList = startList;
		while (didWeSwap!=0)
		{
			//we reset the didWeSwap every time we start a new sort
			didWeSwap = 0;
			while ((advanceList->next)!= NULL)
			{
				//check if we NEED to swap (i.e. the current id is larger than the the next id)
				if ((advanceList->id) > ((advanceList->next)->id))
				{
					swapForBubbleSort(advanceList, advanceList->next);
					//if we used even one swap we need to show it-since it means the sort isnt finished
					didWeSwap = 1;
				}
				//we advance to the next part of the list
				advanceList = (advanceList->next);
			}
			//if we reached the end without needing a single swap, we have finished our sort! (and break the loop)
			if (didWeSwap == 0)
			{
				//reattach the end of our list into a circle and return the new start of the circle (lowest ID person)
				advanceList->next = startList;
				return startList;
			}
			//otherwise we need to repeat the list so we will reset to the beginning for each repition of the sort
			else
				advanceList = startList;
		}
	}
	//if we are here it means we received either an empty list or one consisting of 1 person
	else
	{
		return endList;
	}
	//we reattach the end of our list into a circle and return the new start of the circle (lowest ID person)
	advanceList->next = startList;
	return startList;
}


/***************************
*Function Name: SortCircleByName
*Input: Person* head
*Output: Person* head
*Function Operation: Will receive a circle and sort it into order (based on each persons name lexographically)
		(added note: while the code is similar between the two sort functions, they are different enough that it
		was not worth creating a third sorting function (diffrentiating between the sorting types took up almost as
		many lines of code as I saved) therefore while there is a large overlap they ARE different functions)
****************************/
Person* SortCircleByName(Person* head)
{
	Person* endList = head;
	Person* startList = NULL;
	Person* advanceList;
	//this integer will be used to confirm we managed an entire pass without a single swap
	int didWeSwap = 1;
	//we only need to continue in the event our circle has at LEAST one person in it
	if (head != NULL)
	 {
		startList = (head->next);
		//The entire function only runs in the event we received a circle with at LEAST two people in it
		if ((endList != NULL) && (startList != endList))
		{
			//first, to give us an easy endpoint, we 'break' our circle into a list again
			endList->next = NULL;
			//we start our sort from the start of our new list
			advanceList = startList;
			while (didWeSwap != 0)
			{
				//we reset the didWeSwap every time we start a new sort
				didWeSwap = 0;
				while ((advanceList->next) != NULL)
				{
					//check if we NEED to swap (i.e. current name is larger than the the next name {lexographically})
					if (strcmp(advanceList->next->name, advanceList->name) <= 0)
					{
						swapForBubbleSort(advanceList, advanceList->next);
						//if we used even one swap we need to show it-since it means the sort isnt finished
						didWeSwap = 1;
					}
					//we advance to the next part of the list
					advanceList = (advanceList->next);
				}
				//if we reached the end without needing a single swap, we have finished our sort! (and break the loop)
				if (didWeSwap == 0)
				{
					//reattach the end of list into a circle. return the new start of the circle (lowest ID person)
					advanceList->next = startList;
					return startList;
				}
				//otherwise we need to repeat the list so we will reset to the beginning for each repition of the sort
				else
					advanceList = startList;
			}
		}
		//if we are here it means we received either an empty list or one consisting of 1 person
		else
		{
			return endList;
		}
		//we reattach the end of our list into a circle and return the new start of the circle (lowest ID person)
		advanceList->next = startList;
		return startList;
	}
	else
		return NULL;
}