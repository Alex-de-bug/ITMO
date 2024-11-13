#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>

#define _print(type, x) type##_print(x)

void int64_t_print(int64_t i) { printf("%" PRId64, i); }
void double_print(double d) { printf("%lf", d); }
void newline_print() { puts(""); }
