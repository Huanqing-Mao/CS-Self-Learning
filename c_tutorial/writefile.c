#include <stdio.h>

int main() {
    FILE *fptr; // declare a pointer of type FILE
    fptr = fopen("trial.txt", "w");
    fprintf(fptr, "HQ is very smart."); // write to file

    fclose(fptr);
    return 0;
}
