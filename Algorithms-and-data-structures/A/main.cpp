#include <iostream>
#include <vector>

int main()
{
    int n;
    std::cin >> n;
    std::vector<int> numbers(n);
    for (int i = 0; i < n; ++i)
    {
        std::cin >> numbers[i];
    }
    if (n == 1)
    {
        std::cout << "1 1";
        return 0;
    }
    if (n == 2)
    {
        std::cout << "1 2";
        return 0;
    }
    if (n == 3)
    {
        if (numbers[0] == numbers[1] && numbers[1] == numbers[2] && numbers[2] == numbers[0])
        {
            std::cout << "1 2";
            return 0;
        }
        std::cout << "1 3";
        return 0;
    }
    std::vector<int> ans = {1, 2};
    std::vector<int> max = {1, 2};
    for (int i = 2; i < n; i++)
    {
        if (!(numbers[i - 2] == numbers[i - 1] && numbers[i - 1] == numbers[i] && numbers[i - 2] == numbers[i]))
        {
            ans[1] = i + 1;
        }
        else
        {
            ans[0] = i;
            ans[1] = i + 1;
        }
        if ((max[1] - max[0] + 1) < (ans[1] - ans[0] + 1))
        {
            max = ans;
        }
    }
    std::cout << max[0] << " " << max[1];
    return 0;
}
