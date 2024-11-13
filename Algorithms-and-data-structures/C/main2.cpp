#include <iostream>
#include <stack>
#include <unordered_map>

bool is_integer(const std::string &s)
{
    if (s.empty())
        return false;
    size_t start = (s[0] == '-' || s[0] == '+') ? 1 : 0;
    for (size_t i = start; i < s.length(); ++i)
    {
        if (!std::isdigit(s[i]))
        {
            return false;
        }
    }
    return true;
}

int main()
{
    std::unordered_map<std::string, std::stack<int>> map;
    std::stack<std::string> new_var;
    std::string line;

    while (std::cin >> line)
    {
        if (line.find('=') != std::string::npos)
        {
            std::string key = line.substr(0, line.find('='));
            std::string value = line.substr(line.find('=') + 1);
            // std::cout << "key: " << key << " value: " << value << std::endl;
            if (is_integer(value))
            {
                // std::cout << "число: " << intValue << std::endl;
                int int_value = std::stoi(value);
                map[key].push(int_value);
            }
            else
            {
                // std::cout << "строка: " << std::endl;
                if (!map[value].empty())
                {
                    map[key].push(map[value].top());
                    std::cout << map[value].top() << std::endl;
                }
                else
                {
                    map[key].push(0);
                    std::cout << 0 << std::endl;
                }
            }
            new_var.push(key);
        }
        else
        {
            if (line == "{")
            {
                // std::cout << "new area" << std::endl;
                new_var.push("E0F");
            }
            else if (line == "}")
            {
                // std::cout << "end new area" << std::endl;
                while (new_var.top() != "E0F")
                {
                    // std::cout << "clear " << new_var.top() << " : " << map[new_var.top()].top() << std::endl;
                    map[new_var.top()].pop();
                    new_var.pop();
                }
                new_var.pop();
            }
            else
            {
                // {b=1000} ?? error 1: incorrect cin
                return 1;
            }
        }
    }
    return 0;
}