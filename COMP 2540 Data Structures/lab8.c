/*
     Name: Keerthana Madhavan
     Program:
     Date: 07/9/19
 */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int size = 0;

struct node{
    int data;
    struct node *leftChild;
    struct node *rightChild;
};

int binarySearch(int array[], int leftV, int rightV, int key);

int sortArrayIncreaseing(int array[], int size);

struct node *insertNode(int key);
struct node *insertLeftNode(struct node* root, int key);
struct node *insertRightNode(struct node* root, int key);
void preorder(struct node *root);
int getLeafNodeCount(struct node* leafNode);
int hasOnlyOneChild(struct node* node);
int exactTwoChild( struct node* node);

int main(void)
{
     srand(time(NULL));
    //asks the user for the size of the array
    printf("Enter the size of the array: ");
    scanf("%d", &size);
    
     int array[size];
    int randomNum, flag = 0;
    
    for(int i = 0; i < size; i++)
    {//sets the size of the array as the random number range from 0
        array[i] =  rand() % size;
    }
    //outputs the array
    for( int x = 0; x < size; x++)
    {
        printf("%d ", array[x]);
    }
    //sorts the array in increasing order
    sortArrayIncreaseing(array, size);
    printf("\n");
   
    int key = 0; //the key value to search in the array
    int keyResult = binarySearch(array, 0, size -1, key);
    
    if(keyResult == -1)
    {
        printf("The key %d is not found", key);
    }
    else
    {
        printf("THe key %d is found", key);
    }
    
/* what to include for part 2
    // the number of leaves in a tree T
    // the number of nodes in T with one child
    // the numbers of nodes in T that contain exactly two children
    */
    printf("\n\n");
    
     //part 2 tester
        struct node* root = insertNode(10); // the root node
        insertLeftNode(root, 15); // left child of the root
        insertRightNode(root,6); // right child of the root
        insertLeftNode(root->leftChild, 7); // node added to the left child of the root
       // insertRightNode(root->leftChild, 10);
        insertLeftNode(root->rightChild, 8); // left node to the right child of the root
        insertRightNode(root->rightChild, 3);// right node to the right child of the root.
    insertLeftNode(root->rightChild->rightChild, 1);
        printf("preorder traversal is of the binary tree is: ");
        preorder(root);
        printf("\nThe leaf count of the tree is %d\n", getLeafNodeCount(root));
        printf("The number of nodes with only one child is %d", hasOnlyOneChild(root));
        printf("\nThe numner of nodes with exact two child is %d", exactTwoChild(root));
    printf("\n");
   
    
    return 0;
}

 
int sortArrayIncreaseing(int array[], int size)
{ // sort the array of random numbers using bubble sort
    int temp, j, i;
    for (i = 0; i <= size ; i++)
    {
        for (j = 0; j < (size - i - 1); j++)
        {
            if (array[j] > array[j + 1])
            {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
    return 0;
        
}
// to find the key.
int binarySearch(int array[], int leftV, int rightV, int key)
{
    while(leftV <= rightV)
    {
       int middle = leftV + (rightV - leftV) /2;

        if(array[middle] == key)
        {
            return middle;
        }
        
        if(array[middle] < key)
        {
            leftV = middle + 1;
        }
        else
        {
            rightV = middle - 1;
        }
        
    }
    return -1; // if left is greater than the right
}

//Part 2 funcitons

struct node *insertNode(int key)
{
    struct node *newNode =  (struct node*) malloc(sizeof(struct node));

    newNode->data = key;
    newNode->leftChild=NULL;
    newNode->rightChild=NULL;
    
    return newNode;
}
//insert a left node
struct node *insertLeftNode(struct node* root, int key)
{
    root->leftChild = insertNode(key);
    return root->leftChild;
}
//insert a right node
struct node *insertRightNode(struct node* root, int key)
{
    root->rightChild = insertNode(key);
    return root->rightChild;
}

// prints in the preorder form
void preorder(struct node *root)
{
    if(root == NULL) // if there is no key
        return;
    
    printf("%d ", root->data); // prints the value pf the root
    preorder(root->leftChild);
    preorder(root->rightChild);
}

int getLeafNodeCount(struct node* leafNode)
{
    if(leafNode == NULL)
    {
        return 0; // no left child, so no right child
    }
    if(leafNode->leftChild == NULL && leafNode->rightChild == NULL)
    {
        return 1; // only one node
    }
    else
    {
        return getLeafNodeCount(leafNode->leftChild) + getLeafNodeCount(leafNode->rightChild);
    }
}

int hasOnlyOneChild(struct node* node)
{
    if(node == NULL)
    {
        return 0;
    }
    
    if((node->leftChild == NULL && node->rightChild != NULL) || (node->leftChild != NULL && node->rightChild == NULL))
    {
        return 1;
    }
    else
    {
        return hasOnlyOneChild(node->leftChild) + hasOnlyOneChild(node->rightChild);
    }
}

int exactTwoChild( struct node* node)
{
    if(node == NULL)
    {
        return 0;
    }
    if(node->leftChild != NULL && node->rightChild != NULL)
    {
        return 1;
    }
    else{
        return exactTwoChild(node->leftChild) + exactTwoChild(node->rightChild);
    }
}

