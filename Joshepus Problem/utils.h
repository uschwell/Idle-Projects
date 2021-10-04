/*****************************
*Student Name:Uriel Schwell  *
*Student ID: 327349031       *
*Course Exercise Group: 01   *
*Exercise Name: ex6          *
******************************/
#ifndef UTILS_H
#define UTILS_H
#include "person.h"
#include<stdio.h>
#include <stdlib.h>
#define MAX_LENGTH_OF_NAME 80
#define PRINT_LINE "----------\n"

/***************************
*Function Name: PrintCircle
*Input: Person* head
*Output:
*Function Operation: Will receive a starting point in the circle of people and print every persons' information
(assuming the circle exits and contains people)
****************************/
void PrintCircle(Person* head);
//more functions declarations


/***************************
*Function Name: findPersonByID
*Input: Person *head, int id
*Output: Person *list
*Function Operation: Receives a 'list' of Person data structures and an ID number, the function checks
if a person with that ID number exists in the circle. If not, it will return NULL, if yes:it will return
the (pointer to) the person with that ID number
****************************/
Person* findPersonByID(Person* head, int id);

/***************************
*Function Name: findPreceedingPerson
*Input: Person *person
*Output: Person *person
*Function Operation: Receives a (not NULL) circular 'list' of Person data structures and a person located within
the given circle. The function returns the person who immedietly preceeded the given person in the given circle
(the function returns NULL if there is only ONE person in the list
****************************/
Person* findPreceedingPerson(person);

/***************************
*Function Name: resetNameBuffer
*Input:char array tempName[]
*Output:
*Function Operation: This function receives a used temporary Buffer (for names) and 'resets' it to '\0'
(i.e all values are now '\0')
****************************/
void resetNameBuffer(char tempName[]);

/***************************
*Function Name: swapForBubbleSort
*Input: Person *first, Person *second
*Output:
*Function Operation: Receives two 'person' data structures in a (circular) order and swaps their locations with each 
		other (while maintaining the circle and all other relations between data structures)
****************************/
void swapForBubbleSort(Person *first, Person *second);

/***************************
*Function Name: eraseCircle
*Input: Person* StartPoint
*Output:
*Function Operation: Receives the beginning of a circle that requires deletion, it will delete the entire
circle from the computers memory
****************************/
void eraseCircle(Person* StartPoint);

/***************************
*Function Name: sortCircle
*Input: Person* StartPoint, int sortType
*Output:
*Function Operation: Receives the beginning of a circle that requires sorting, it will sort the circle according to
either Names (lexographically) or ID (0/1 respectively) according to the sortType it also receives
****************************/
void sortCircle(Person* head, int sortType);

#endif
