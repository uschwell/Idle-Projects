/*****************************
*Student Name:Uriel Schwell  *
*Student ID: 327349031       *
*Course Exercise Group: 01   *
*Exercise Name: ex6          *
******************************/

#include <stdarg.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "person.h"
#include "utils.h"
#define MAX_LENGTH_OF_NAME 80
#define Receive_Name "Name:\n"
#define Receive_ID "ID:\n"
#define Receive_Num_Of_Kids "Num of kids:\n"
#define Receive_Kids_Names "Kid #%d name:\n"
#define Print_Name "Name: %s\n"
#define Print_ID "ID: %d\n"
#define Print_KIds "Kid #%d: %s\n"

/***************************
*Function Name: CreatePerson
*Input: none
*Output: Person data structure
*Function Operation: This function helps define the very first person in our list of 'Person' data structures
****************************/
Person* CreatePerson()
{
	Person* newPerson =(Person*)(malloc(sizeof(Person)));
	//we send to an assisting function to receive all relevant person input (IF the memory allocation worked)
	if(newPerson!=NULL)
	InitPersonValues(newPerson);
	return newPerson;
}


/***************************
*Function Name: SelfDestruct
*Input:Person *this
*Output:
*Function Operation:
****************************/
void SelfDestruct(struct Person* this)
{
	int i;
	//we remove the name of the given person
	free(this->name);
	/*we now delete the kids names.
	we know the length of the 'kids' array because of the numOfKids saved by each person*/
	for (i = (this->numOfKids); i > 0; i--)
	{
		free(this->kids[(i-1)]);
	}
	//we now remove the pointer for the kidsNames array itself
	free(this->kids);
	//now we remove the pointer for the person itself (along with all the remaining pointer)
	free(this);
}


/***************************
*Function Name: KillNext
*Input:Person *this
*Output:
*Function Operation: we 'kill' (remove) the next person on the list while insuring their destruction wont leave
any holes in the (circular) list
****************************/
void KillNext(struct Person* this)
{
	Person *tempNext;
	//we use tempNext to save the (pointer) address of the person to be deleted
	tempNext = this->next;
	//we link the current person to the next (undeleted) person on the list
	this->next = ((this->next)->next);
	//we now go and delete the current next person
	SelfDestruct(tempNext);
}


/***************************
*Function Name: Print
*Input:Person *person
*Output:
*Function Operation: receives a pointer to a specific 'Person' data structure and prints the name of the
relevant person
****************************/
void Print(struct Person *person)
{
	int i;
	//we confirm the given person does in fact exist
	if (person == NULL)
		return;
	//we print the name of the given person
	printf(Print_Name, person->name);
	//we print the ID of the given person
	printf(Print_ID, (person->id));
	//assuming the person HAS any kids, we print their number and name
	for (i = 0; i < (person->numOfKids); i++)
	{
		printf(Print_KIds,(i+1),(person->kids[i]));
	}

}


/***************************
*Function Name: CreateNext
*Input:Person *current; int isNextNext, Person* current->Next
*Output:
*Function Operation: Variadic function, recevies a location in a list, (either mid-list or at the end) composed
	of 'Person' data structures and either adds a new 'Person' onto the end, or the middle of said list
****************************/
void CreateNext(struct Person* current, int isNextNext, ...)
{
	//we define a new pointer
	Person *newPerson;
	Person *oldNextPointer = NULL;
	va_list ap;
	//we create a new person not linked to anything
	newPerson = CreatePerson();
	//in the event the memory allocation failed
	if (newPerson != NULL)
	{
		//in the event we are adding a new person onto the END of our current list
		if (isNextNext == 0)
		{
			//we tie our new person into the end of our current list
			current->next = newPerson;
		}
		//In the event we are adding our new person into the middle of our list
		else
		{
			va_start(ap, isNextNext);
			//we attach our new person to the previous person on our list
			current->next = newPerson;
			//we attach our new person to the 'old' link (towards the newly displaced next spot on the list)
			newPerson->next = va_arg(ap, Person*);
			va_end(ap);
		}
	}
}


/***************************
*Function Name: InitPersonValues
*Input:Person *person
*Output:
*Function Operation: Receives a 'Person' data structure, resets all its values to '0' (or NULL) and then proceeds
		to input into said structure all relevant information it requires to function
****************************/
void InitPersonValues(Person* person)
{
	//first we 'zero out' any preexisting information
	person->name =NULL;
	person->id = 0;
	person->numOfKids = 0;
	person->kids = NULL;
	person->next = NULL;
	person->Print = Print;
	person->KillNext = KillNext;
	person->SelfDestruct=SelfDestruct;
	person->CreateNext = CreateNext;
	//we input all the relevant information for a new person (name, id, kids, etc...)
	char tempName[MAX_LENGTH_OF_NAME] = { '\0' };
	int ID;
	int i;
	int numOfKids;
	//we receive the persons name
	printf(Receive_Name);
	scanf("%s", &tempName);
	//we define our final name size and information
	person->name = (char*)malloc(((strlen(tempName) + 1) * (sizeof(char))));
	//we copy the name from the temporary buffer to the final name (defined by the given pointer)
	strcpy(person->name, tempName);
	//we receive the ID as input
	printf(Receive_ID);
	scanf("%d",&ID);
	//we insert the ID number into the relevant person structure
	person->id = ID;
	printf(Receive_Num_Of_Kids);
	scanf("%d", &numOfKids);
	person->numOfKids = numOfKids;
	//we reset the buffer used for temporarily holding input names
	resetNameBuffer(tempName);
	//we expand the 'kids' array to the correct size to receive pointers for each child
	person->kids = (char**)malloc((numOfKids)*(sizeof(char*)));
	//we receive and save each kids names as they are received
	for (i=1;i <= numOfKids;i++)
	{
		//we receive each specific kids' name
		printf(Receive_Kids_Names,i);
		scanf("%s", &tempName);
		//we define the relevant space (for saving the kids names) to be the proper size for said name
		person->kids[i - 1] = (char*)(malloc((strlen(tempName) + 1) * sizeof(char)));
		//we insert the kids name into the relevant place in the kids 'array'
		strcpy(person->kids[i - 1], tempName);
		//we reset the buffer used for temporarily holding input names
		resetNameBuffer(tempName);
	}
}
