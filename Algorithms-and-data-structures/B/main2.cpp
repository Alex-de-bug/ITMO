#include <iostream>
#include <stack>

int main()
{
    std::string input_str;

    std::cin >> input_str;

    std::stack<int> upper_chars;
    std::stack<int> lower_chars;
    std::stack<char> current_str;
    int answer[input_str.length() / 2];
    int up_chars = 0;
    int lo_chars = 0;
    for (int i = 0; i < input_str.length(); i++)
    {
        if (isupper(input_str[i]))
        {
            up_chars++;
            upper_chars.push(up_chars);
            // std::cout << "add upper - " << input_str[i] << " number - " << up_chars << std::endl;
        }
        else
        {
            lo_chars++;
            lower_chars.push(lo_chars);
            // std::cout << "add lower - " << input_str[i] << " number - " << lo_chars << std::endl;
        }
        if (current_str.empty())
        {
            // std::cout << "empty" << std::endl;
            current_str.push(input_str[i]);
        }
        else
        {
            char last = current_str.top();
            if ((abs(last - input_str[i]) == 32))
            {
                answer[upper_chars.top() - 1] = lower_chars.top();
                upper_chars.pop();
                lower_chars.pop();
                current_str.pop();
            }
            else
            {
                current_str.push(input_str[i]);
            }
        }
    }
    if (!current_str.empty())
    {
        std::cout << "Impossible" << std::endl;
        return 0;
    }
    std::cout << "Possible" << std::endl;
    for (int i = 0; i < input_str.length() / 2; i++)
    {
        std::cout << answer[i] << " ";
    }
    std::cout << std::endl;
    return 0;
}