#include <stdio.h>

int main() {
    int myAge = 21;

    int* ptr = &myAge; // store the address of myAge

    printf("%d\n", myAge);
    printf("%p\n", &myAge);
    printf("%p\n", ptr);
    return 0;

}
