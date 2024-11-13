#include <iostream>
#include <stack>
#include <unordered_map>

int main()
{
    int start;
    int new_vir;
    int delete_vir;
    int place_cont;
    int days;

    std::stack<int> curr;

    std::cin >> start >> new_vir >> delete_vir >> place_cont >> days;

    int test = start;

    for (int i = 0; i < days; i++)
    {
        start = start * new_vir;
        if (start <= delete_vir)
        {
            std::cout << 0;
            return 0;
        }
        else
        {
            start = start - delete_vir;
        }
        if (start >= place_cont)
        {
            start = place_cont;
        }
        if (test == start)
        {
            std::cout << start;
            return 0;
        }
    }
    std::cout << start;
    return 0;
}