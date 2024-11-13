#include <iostream>
#include <vector>
#include <map>
#include <queue>
using namespace std;

struct CompareSegmentSize {
    bool operator()(pair<int, int> a, pair<int, int> b) const {
        return a.first < b.first;
    }
};

int main() {
    int size, count;
    cin >> size >> count;

    map<int, int> free_segments_index; // Позиция, размер
    multimap<int, int> free_segments_size; // Размер, позиция

    priority_queue<pair<int, int>, vector<pair<int, int> >, CompareSegmentSize> queue;

    free_segments_index.insert(make_pair(1, size));
    free_segments_size.insert(make_pair(size, 1));
    
    vector<pair<int, int> > history(count);

    for (int i = 0; i < count; i++) {
        int req;
        cin >> req;
        if (req > 0) {
            auto minimax = free_segments_size.lower_bound(req);
            if(next(minimax) != free_segments_size.end()){
                minimax = next(minimax);
            }

            if(minimax!= free_segments_size.end()){
                cout << minimax->second << endl;
                history[i] = make_pair(minimax->second, req);
                if(minimax->first - req > 0){
                    free_segments_index.insert(make_pair(minimax->second + req, minimax->first - req));
                    free_segments_size.insert(make_pair(minimax->first - req, minimax->second + req));
                    queue.push(make_pair(minimax->first - req, minimax->second + req));
                }
                free_segments_index.erase(minimax->second);
                free_segments_size.erase(minimax);
            }else{
                history[i] = make_pair(-1, req);
                cout << "-1" << endl;
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
