#include <stdio.h>

#define VALID_PIN 0x00344347

struct cred_t
{
    char password[1024];
    int pin; //MAX FF FF FF FF
};

int main(void)
{
    struct cred_t credentials;

    printf("Password: ");
    scanf("%s", credentials.password);

    printf("\n%d\n", &credentials.pin);
    printf("\n%d\n", credentials.password+1024);

    printf("valid pin:%d\n",VALID_PIN);

    int* pointer = (int *)(credentials.password+1024);
    //printf("sss %s", );
    printf("calculated pointer value %d\n", *pointer);
    printf("actual value: %d \n", credentials.pin);

    if (credentials.pin == VALID_PIN)
    {
        printf("Correct\n");
        return 0;
    }
    printf("Wrong\n");
    return 1;
}
