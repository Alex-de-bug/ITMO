#include <iostream>
#include <stack>
#include <unordered_map>


bool is_integer(const std::string &s) {
    if (s.empty()) return false; 
    size_t start = (s[0] == '-' || s[0] == '+') ? 1 : 0; 
    for (size_t i = start; i < s.length(); ++i) {
        if (!std::isdigit(s[i])) {
            return false;
        }
    }
    return true;
}


int main() {
    std::stack<std::unordered_map<std::string, int > > current_area;
    std::unordered_map<std::string, int > first_area;
    current_area.push(first_area);

    std::string line;

    while (std::cin >> line){
        if(line.find('=') != std::string::npos){
            std::string key = line.substr(0, line.find('='));
            std::string value = line.substr(line.find('=') + 1);
            //std::cout << "key: " << key << " value: " << value << std::endl;
            if (is_integer(value)) {
                //std::cout << "число: " << intValue << std::endl;
                int int_value = std::stoi(value);
                current_area.top()[key] = int_value;
            } else {
                //std::cout << "строка: " << std::endl;
                int new_value = current_area.top()[value];
                current_area.top()[key] = new_value;
                std::cout << new_value << std::endl;
            }
        }else{
            if (line == "{"){
                //std::cout << "new area" << std::endl;
                current_area.push(current_area.top());
            }else if (line == "}"){
                //std::cout << "end new area" << std::endl;
                current_area.pop();
            }else{
                // {b=1000} ?? error 1: incorrect cin
                return 1;
            }
        }
    }
    return 0;
}