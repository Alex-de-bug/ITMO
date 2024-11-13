#include <iostream>
#include <deque>
#include <vector>
using namespace std;

int main() {
    int count, size;
    cin >> count >> size;

    vector<int> values(count);
    for (int i = 0; i < count; i++) {
        cin >> values[i];
    }

    deque<int> dq;

    for (int i = 0; i < count; i++) {
        if (!dq.empty() && dq.front() == i - size) {
            dq.pop_front();
        }
        while (!dq.empty() && values[dq.back()] >= values[i]) {
            dq.pop_back();
        }

        dq.push_back(i);
        if (i >= size - 1) {
            cout << values[dq.front()] << " ";
        }
    }

    return 0;
}
