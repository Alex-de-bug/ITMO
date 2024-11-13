#include <iostream>
#include <map>
#include <set>
#include <vector>
#include <functional>

struct SortedY {
    std::set<int> elements;

    void insert(int y) {
        elements.insert(y);
    }

    int first() const {
        return elements.empty() ? std::numeric_limits<int>::max() : *elements.begin();
    }
};

// Компаратор для сортировки по первому значению в SortedY
struct CompareFirstY {
    bool operator()(const std::pair<int, SortedY>& a, const std::pair<int, SortedY>& b) const {
        return a.second.first() < b.second.first();
    }
};

int main() {
    std::map<int, SortedY> myMap;

    // Добавление элементов
    myMap[10].insert(3);
    myMap[10].insert(4);
    myMap[5].insert(2);
    myMap[3].insert(4);
    myMap[3].insert(2);

    // Переносим элементы в вектор для сортировки по первому значению y
    std::vector<std::pair<int, SortedY> > sortedItems(myMap.begin(), myMap.end());
    std::sort(sortedItems.begin(), sortedItems.end(), CompareFirstY());

    // Вывод результатов
    for (const auto& item : sortedItems) {
        std::cout << "x: " << item.first << " y: ";
        for (int y : item.second.elements) {
            std::cout << y << " ";
        }
        std::cout << std::endl;
    }

    return 0;
}
