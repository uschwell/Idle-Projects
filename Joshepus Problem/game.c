/*****************************
*Student Name:Uriel Schwell  *
*Student ID: 327349031       *
*Course Exercise Group: 01   *
*Exercise Name: ex6          *
******************************/
#include<stdio.h>
#include <stdlib.h>
#include "game.h"
#include "utils.h"
#include "person.h"
#define LATE "Are you late? (0|1)\n"
#define BEST_FRIENDS_ID "Insert your best friend's ID:\n"
#define ADD_A_PERSON "Add a person to the game? (0|1)\n"
#define STAYING_ALIVE "Ah, ha, ha, ha, stayin' alive, stayin' alive! Ah, ha, ha, ha, \"%s\" stayin' alive!\n"
#define A_KILLS_B "%s kills %s"
#define DEAD_KIDS " and %s"
#define ANY_COWARDS "Is there a coward in here? (0|1)\n"
#define INSERT_COWARDS_ID "Insert your ID, you, lame pudding-heart coward:\n"
#define NO_ID "No Such ID: %d\n"
#define ADMIT_YOURE_A_COWARD "Let the world know that you are a coward, %s!\n"
#define RETURN_A_LINE "\n"

/***************************
*Function Name: InsertLaters
*Input: Person* head
*Output: none
*Function Operation: Receives an existing circle, and adds any person who was late to the circle into the circle
		right after their best friends location in said circle (assuming the circle exists to begin with) AND that
		the best friend exists in the circle
****************************/
void InsertLaters(Person* head)
{
	Person* bestFriend=NULL;
	int latePeople = 0;
	int bestFriendID;
	int addedPerson = 0;
	int firstEntry = 0;
	//the entire function only occurs IF the circle exists to begin with
	if (head != NULL)
	{
		//we see if we need to enter at least one latecomer to the circle
		printf(LATE);
		scanf("%d", &latePeople);
		//we start/continue to add late people until we receive a '0' 
		while (latePeople)
		{
			//we receive the ID of the latecomers best friend
			printf(BEST_FRIENDS_ID);
			scanf("%d", &bestFriendID);

			//we use an assisting function to find the location of the friend we want to find
			bestFriend = findPersonByID(head, bestFriendID);
			//the function only returns NULL if the best friend did NOT exist
			if (bestFriend == NULL)
			{
				printf(NO_ID, bestFriendID);
			}
			//otherwise, the best friend DID exist, we insert our latecomer after his friend
			else
			{
				CreateNext(bestFriend, 1,bestFriend->next);
			}
			//we see if we need to enter any more latecomers to the circle
			printf(LATE);
			scanf("%d", &latePeople);
		}

	}
	//in the event the circle we received was empty
	else
		return;
}


/***************************
*Function Name: RemoveCowards
*Input: Person* head
*Output:
*Function Operation: Function receives a list of 'People' data structures, and removes from the list all cowards
	who are afraid to participate in the game. The function returns either the head of the new circle (after removal
	of all cowards) or NULL in the event we received either an empty list or removed all people from the given list
****************************/
Person* RemoveCowards(Person* head)
{
	int anyCowards = 1;
	int cowardID;
	Person* coward = NULL;
	Person* preceedCoward = NULL;
	Person* headOfCircle = head;
	//the entire function only runs if/while the circle still exists AND we still have cowards to remove
	while((headOfCircle != NULL)&&(anyCowards))
	{
		printf(ANY_COWARDS);
		scanf("%d", &anyCowards);
		//if we have a coward we need to remove
		if(anyCowards)
		{
			printf(INSERT_COWARDS_ID);
			scanf("%d", &cowardID);
			//we reset to begin our search at the start of the circle for each new cowards
			coward = headOfCircle;
			//we send to an assisting function that finds the person who matches the given ID number
			coward=findPersonByID(coward,cowardID);

			//if the coward doesnt exist we print that he doesnt exist
			if (coward == NULL)
			{
				printf(NO_ID, cowardID);
			}
			else
			{
				//now that we have the coward, we use an assisting function to find whoever preceeds the coward
				preceedCoward = findPreceedingPerson(coward);
				//if the function returned  NULL then we know we only had one person in our list, we delete him
				if (preceedCoward == NULL)
				{
					printf(ADMIT_YOURE_A_COWARD, coward->name);
					SelfDestruct(coward);
					return NULL;
				}

				//if the coward is the head of the circle we designate a NEW head of the circle before deleting him
				if (coward == headOfCircle)
				{
					headOfCircle = coward->next;
					printf(ADMIT_YOURE_A_COWARD, coward->name);
					KillNext(preceedCoward);
				}
				//otherwise we can use killNext to remove our coward and have the circular chain unbroken
				else
				{
					printf(ADMIT_YOURE_A_COWARD, coward->name);
					KillNext(preceedCoward);
				}
			}
		}
		//we have removed our coward (if he existed) we now repeat to check if we have any more cowards to remove
	}

	//if we emptied the entire circle or received an empty circle to begin with
	if (headOfCircle == NULL)
		return NULL;
	//otherwise we return whatever the final head of our circle is
	else
		return headOfCircle;
}






										//NEED TO CREATE THE NULL INPUT CHECKER!!!!
/***************************
*Function Name: InitTheHungerGame
*Input:none
*Output: Person* StartPoint
*Function Operation: Inputs (legal) participants to the 'Hunger Games', creates memory space to hold them
		and then organizes them into a circle so the game can begin.
****************************/
Person* InitTheHungerGame()
{
	int boolean=1;
	Person *StartPoint=NULL;
	Person *nextPerson=NULL;
	Person *lastPerson=NULL;
	char tempname[80] = { '\0' };
	//we add our first person to the game list
	printf(ADD_A_PERSON);
	scanf("%d", &boolean);
	//checks we are supposed to enter ANYONE into the current list (if not we end our game)
	if (boolean)
	{
		StartPoint = CreatePerson(StartPoint, 0);
		//if the memory allocation failed we delete whatever we have and end our function
		if (StartPoint == NULL)
		{
			SelfDestruct(StartPoint);
			return NULL;
		}
	}
	else
		return NULL;

	//we define nextPerson so we can continue creating our list while still 'remembering' our starting point
	nextPerson = StartPoint;
	//loop continues until we finish setting up our list
	while (boolean)
	{
		printf(ADD_A_PERSON);
		scanf("%d", &boolean);
		if (boolean)
		{
			CreateNext(nextPerson, 0);
			//if at any point the memory allocation fails, we delete whatever we already have, and end our function
			if (nextPerson->next == NULL)
			{
				eraseCircle(StartPoint);
				return NULL;
			}


			//after we add a new person, we advance the list to its new endpoint
			nextPerson = nextPerson->next;
		}
	}
	//now that we have our starting list, we link the final member on that list to the startpoint (forming a circle)
	lastPerson = StartPoint;
	//this loop continues until we find the final person on the list
	while (lastPerson->next!=NULL)
	{
		lastPerson = lastPerson->next;
	}
	//we link the final person to the start of our list, creating a cirlce
	lastPerson->next = StartPoint;
	return StartPoint;
}


/***************************
*Function Name: LetTheHungerGameBegin
*Input:Person* head
*Output: none
*Function Operation: Receives the start of a circle of 'People' and plays the 'joshephus' game, printing
	who kills who at each point and who survives at the end
****************************/
void LetTheHungerGameBegin(Person* head)
{	
	Person* start=head;
	Person* advance=NULL;
	//we check we did in fact receive a circle (otherwise there is no need for the entire function to run)
	if (start != NULL)
	{
		advance = (head->next);
		//we keep running until we have only one person left alive
		while (advance != start)
		{
			//we print who kills who
			printf(A_KILLS_B, (start->name), (advance->name));
			//we print the names of all the kids who have just been killed 
			for (int i = 0; i < (advance->numOfKids); i++)
			{
				printf(DEAD_KIDS, advance->kids[i]);
			}
			//we return to the next line
			printf(RETURN_A_LINE);
			KillNext(start);
			//we advance to the next (alive) person in the circle
			start = start->next;
			advance = start->next;
		}
		//once we reach this point, we have only one person left alive
		printf(STAYING_ALIVE, start->name);
		//now that we have found and printed our final survivor we need to delete him to clear our memory
		SelfDestruct(start);
	}
} 