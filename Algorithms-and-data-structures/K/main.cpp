#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main() {
    int size, count;
    cin >> size >> count;

    map<int, int> free_segments_index; // Позиция, размер
    multimap<int, int> free_segments_size; // Размер, позиция

    free_segments_index.insert(make_pair(1, size));
    free_segments_size.insert(make_pair(size, 1));
    
    vector<pair<int, int> > history(count);

    for (int i = 0; i < count; i++) {
        int req;
        cin >> req;
        if (req > 0) {
            auto it = free_segments_index.begin();
            while (it != free_segments_index.end() && it->second < req) {
                ++it;
            }

            if (it != free_segments_index.end()) {
                cout << it->first << endl; // Выводим начальный индекс сегмента
                history[i] = make_pair(it->first, req);
                int newStart = it->first + req;
                int newSize = it->second - req;
                free_segments_size.erase(free_segments_size.find(it->second)); // Удаляем старый сегмент по размеру
                free_segments_index.erase(it); // Удаляем старый сегмент по индексу
                
                if (newSize > 0) {
                    free_segments_index.insert(make_pair(newStart, newSize));
                    free_segments_size.insert(make_pair(newSize, newStart));
                }
            } else {
                cout << "-1" << endl; // Нет подходящего сегмента
                history[i] = make_pair(-1, req);
            }
        } else {
            req = -req;
            int start = history[req - 1].first;
            int length = history[req - 1].second;
            auto it = free_segments_index.lower_bound(start);
            // cout << "start: " << start << "size: " << length << "elem" << it->first << endl;
            if(start == -1){
                continue;
            }
            if (it != free_segments_index.begin()) {
                auto prev_it = prev(it); //++it 5 тест
                if (prev_it->first + prev_it->second == start) { 
                    length += prev_it->second;
                    start = prev_it->first;
                    free_segments_size.erase(free_segments_size.find(prev_it->second));
                    free_segments_index.erase(prev_it);
                }
            }

            if (it != free_segments_index.end() && start + length == it->first) { 
                length += it->second;
                free_segments_size.erase(free_segments_size.find(it->second));
                free_segments_index.erase(it);
            }

            free_segments_index[start] = length;
            free_segments_size.insert(make_pair(length, start));
        }
    }
    return 0;
}
