#include <iostream>
#include <vector>
#include <map>
#include <queue>
using namespace std;

struct Value {
    int index;
    int obj; 
    bool operator<(const Value& other) const {
        return obj > other.obj;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int size, count, x;
    cin >> count >> size;


    priority_queue<Value> values; 

    for (int i = 0; i < count; i++)
    {
        cin >> x;
        Value new_value;
        new_value.index = i;
        new_value.obj = x;
        values.push(new_value);
        if(i>=size-1){
            while(true){
                if(values.top().index >= i-(size-1)){
                    cout << values.top().obj << " ";
                    break;
                }else if(values.empty()){
                    break;
                }else{
                    values.pop();
                }
            }
            
        }
    }
    return 0;
}