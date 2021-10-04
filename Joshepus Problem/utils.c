/*****************************
*Student Name:Uriel Schwell  *
*Student ID: 327349031       *
*Course Exercise Group: 01   *
*Exercise Name: ex6          *
******************************/
#include "utils.h"

/***************************
*Function Name: resetNameBuffer
*Input:char array tempName[]
*Output:
*Function Operation: This function receives a used temporary Buffer (for names) and 'resets' it to '\0'
	(i.e all values are now '\0')
****************************/
void resetNameBuffer(char tempName[])
{
	int i;
	for (i = 0; i < MAX_LENGTH_OF_NAME; i++)
	{
		tempName[i] = '\0';
	}
}


/***************************
*Function Name: PrintCircle
*Input: Person* head
*Output:
*Function Operation: Will receive a starting point in the circle of people and print every persons' information
		(assuming the circle exits and contains people)
****************************/
void PrintCircle(Person* head)
{
	Person *advanceCircle = head;
	//all this only happens if the circle of people actually exists (i.e. contains people)
	if (head != NULL)
	{
		//we write the line before the first person is printed
		printf(PRINT_LINE);
		//we print the first person in the circle
		head->Print(advanceCircle);
		//we print the bottom line
		printf(PRINT_LINE);
		//we advance to the next person (assuming he/she exists)
		advanceCircle = advanceCircle->next;
		//we print each person once (assuming the circle existed to begin with)
		while (advanceCircle != head)
		{
			//we print each new person in the circle
			advanceCircle->Print(advanceCircle);
			//we advance to the next person (assuming he/she exists and isnt the first person again)
			advanceCircle = advanceCircle->next;
			//we print the next line (either between each person or the bottom line)
			printf(PRINT_LINE);
		}
	}
}


/***************************
*Function Name: findPersonByID
*Input: Person *head, int id
*Output: Person *list
*Function Operation: Receives a (not NULL) 'list' of Person data structures and an ID number, the function checks
	if a person with that ID number exists in the circle. If not, it will return NULL, if yes:it will return
	the (pointer to) the person with that ID number
****************************/
Person* findPersonByID(Person* head, int ID)
{
	Person* findPerson;
	int foundPerson = 0;
	//we check if the first person has the proper ID
	findPerson = head;
	if ((findPerson->id) == ID)
	{
		//if we found the person we are looking for at the head of the circle
		return findPerson;
	}
	//otherwise we advance to the next person in the circle
	findPerson = findPerson->next;
	//we search for the best friend by his ID number (assuming we havent already added someone)
	while (findPerson != head)
	{
		//we see if the current person is the best Friend we are looking for
		if ((findPerson->id) == ID)
		{
			//now that we found what we needed we can end our search
			return findPerson;
		}
		//we advance to the next person in the circle
		findPerson = findPerson->next;
	}
	//if we never found the friends ID number by this point, he didnt exist in the circle so we return a NULL value
	return NULL;
}


/***************************
*Function Name: findPreceedingPerson
*Input: Person *person
*Output: Person *person
*Function Operation: Receives a (not NULL) circular 'list' of Person data structures and a person located within
	the given circle. The function returns the person who immedietly preceeded the given person in the given circle
	(the function returns NULL if there is only ONE person in the list
****************************/
Person* findPreceedingPerson(Person* person)
{
	Person* head = person;
	Person* preceed = NULL;
	//first we insure the list contained more than one person
	if ((person==NULL)||(person->next==person))
	{
		return NULL;
	}
	else
	{
		preceed = (head->next);
		//we progress until we find the last person before reaching the head person again (i.e whoever preceeds him)
		while ((preceed->next) != head)
		{
			preceed = preceed->next;
		}
		//at this point preceed is the answer we wanted so we return it
		return preceed;
	}
}


/***************************
*Function Name: swapForBubbleSort
*Input: Person *first, Person *second
*Output:
*Function Operation: Receives two 'person' data structures in a list and swaps their information with each
other (while maintaining the list and all other relations between data structures)
****************************/
void swapForBubbleSort(Person *first, Person *second)
{
	/*we copy all the information (that needs altering) onto it own temporary place and switch said information 
	between the relevant data structures*/
	char* tempName;
	int tempID;
	int tempNumOfKids;
	char** tempKids;
	tempName = first->name;
	tempID = first->id;
	tempNumOfKids = first->numOfKids;
	tempKids = first->kids;
	//we copy everything from the second onto the 'first' data structure
	first->name = second->name;
	first->id = second->id;
	first->numOfKids = second->numOfKids;
	first->kids = second->kids;
	//we copy everything from the temporary holders onto the 'second' data structure
	second->name = tempName;
	second->id = tempID;
	second->numOfKids = tempNumOfKids;
	second->kids = tempKids;
}

/***************************
*Function Name: eraseCircle
*Input: Person* StartPoint
*Output:
*Function Operation: Receives the beginning of a circle that requires deletion, it will delete the entire
	circle from the computers memory
****************************/
void eraseCircle(Person* StartPoint)
{
	while (StartPoint->next != NULL)
	{
		//until we reach the end of our (unfinished circle) we can use KillNext function to remove people
		KillNext();
	}
	//once we are left with only one person, we remove them using SelfDestruct
	SelfDestruct(StartPoint);
}
