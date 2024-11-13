#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>
#include <functional>

using namespace std;

struct Car {
    int type;
    int next_use_index; 
    bool operator<(const Car& other) const {
        return next_use_index < other.next_use_index;
    }
};

int main() {
    int N, K, size;
    cin >> N >> K >> size;

    vector<int> queue_start(size); //очередь машинок
    unordered_map<int, queue<int> > map_indx_cars; //машинка + её индексы использования
    for (int i = 0; i < size; ++i) {
        cin >> queue_start[i];
        map_indx_cars[queue_start[i]].push(i);
    }

    priority_queue<Car> cars; //сортированная очередь машинок, которые будут использоваться
    unordered_map<int, int> floor; //машинки на полу

    int counter = 0;

    for (int i = 0; i < size; ++i) {
        Car newCar;
        newCar.type = queue_start[i];
        map_indx_cars[newCar.type].pop();

        if (floor.find(newCar.type) == floor.end()) { 
            if (floor.size() == K) {                 
                floor.erase(cars.top().type);
                cars.pop();
            }
            floor[newCar.type] = i;
            counter++;
        }

        newCar.next_use_index = map_indx_cars[newCar.type].front(); 
        if (map_indx_cars[newCar.type].empty()) {
            newCar.next_use_index = size+1; 
        }
        cars.push(newCar);
    }

    cout << counter;
    return 0;
}
