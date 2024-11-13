#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>
#include <functional>
#include <map>
#include <set>

using namespace std;

class Compare {
public:
    bool operator()(int below, int above)
    {
        if (below > above) {
            return true;
        }
        return false;
    }
};

class CompareInv {
public:
    bool operator()(int below, int above)
    {
        if (below < above) {
            return true;
        }
        return false;
    }
};

int main() {
    int N, K, total;
    cin >> N >> K >> total;
    
    map<int, priority_queue<int, vector<int>, Compare> > cars;
    vector<int> order;
    for (int i = 0; i < total; ++i) {
        int newCar;
        cin >> newCar;
        order.push_back(newCar);
        cars[newCar].push(i);     
    }

    int counter = 0;
    set<int> onFloor;
    for(int i = 0; i < total; i++){
        int car = order[i];
        if(onFloor.find(car) == onFloor.end()){
            if (onFloor.size() == K) {
                int carRem;
                int max = 0;
                for(int x : onFloor){
                    if(cars[x].top() > max){
                        carRem = x;
                        max = cars[x].top();
                    }
                }
                onFloor.erase(carRem);
            }
            onFloor.insert(car);
            cars[car].pop();
            if(cars[car].empty()){
                cars[car].push(__INT_MAX__);
            }
            counter++;
        }else{
            cars[car].pop();
        }
    }

    cout << counter;
    return 0;
}
