#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main() {
    int size, count;
    cin >> size >> count;

    map<int, int> free_segments_index; // Позиция, размер
    multimap<int, int> free_segments_size; // Размер, позиция

    free_segments_index[1] = size; // Инициализируем сегмент всей доступной памяти
    free_segments_size.insert(make_pair(size, 1)); // Добавляем в multimap

    vector<pair<int, int> > history(count); // Для отслеживания аллокаций

    for (int i = 0; i < count; i++) {
        int req;
        cin >> req;
        if (req > 0) { // Аллокация
            auto it = free_segments_size.lower_bound(req);
            if (it != free_segments_size.end()) {
                int start = it->second, segSize = it->first;
                cout << start << "\n";
                history[i] = make_pair(start, req);

                free_segments_index.erase(start); // Удаляем старый сегмент
                free_segments_size.erase(it); // Удаляем запись из multimap

                if (segSize > req) { // Если размер сегмента больше запроса, обновляем оставшуюся часть
                    free_segments_index[start + req] = segSize - req;
                    free_segments_size.insert(make_pair(segSize - req, start + req));
                }
            } else {
                cout << "-1\n";
                history[i] = make_pair(-1, req);
            }
        } else { // Освобождение
            req = -req;
            int start = history[req - 1].first, len = history[req - 1].second;

            auto next = free_segments_index.upper_bound(start);
            int newStart = start, newSize = len;
            
            if (next != free_segments_index.begin()) {
                auto prev_e = prev(next);
                if (prev_e->first + prev_e->second == start) { // Слева
                    newStart = prev_e->first;
                    newSize += prev_e->second;
                    free_segments_size.erase(free_segments_size.find(prev_e->second));
                    free_segments_index.erase(prev_e);
                }
            }

            if (next != free_segments_index.end() && start + len == next->first) { // Справа
                newSize += next->second;
                free_segments_size.erase(free_segments_size.find(next->second));
                free_segments_index.erase(next);
            }

            free_segments_index[newStart] = newSize;
            free_segments_size.insert(make_pair(newSize, newStart));
        }
    }
    return 0;
}
