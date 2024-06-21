#include <stdio.h>

int main() {
    FILE *filepointer;
    filepointer = fopen("trial.txt", "r");

    char myString[1000];
    fgets(myString, 1000, filepointer);
    printf("%s", myString);
    fclose(filepointer);

    return 0;
}