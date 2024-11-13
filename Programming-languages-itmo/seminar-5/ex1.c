#include <stdio.h>
#define print_var(x) printf(#x " is %d\n", x )

int main(){
    size_t tmp = 123;
    const int64_t CONSTANT  = 456;

    print_var(tmp);
    print_var(CONSTANT);
    print_var(print_var(CONSTANT));

    return 0;
}