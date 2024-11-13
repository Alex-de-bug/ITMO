#include <iostream>
#include <queue>
#include <list>
#include <vector>

using namespace std;

struct Node {
    int cost;
    int marked;
    int x;
    int y;
    int predecessor;

    Node(int cost = -1, int marked = 0, int x = -1, int y = -1, int pred = -1) 
        : cost(cost), marked(marked), x(x), y(y), predecessor(pred) {}

    bool operator>(const Node& other) const {
        return this->cost > other.cost;
    }
};

using NodeQueue = priority_queue<Node, vector<Node>, greater<Node> >;

int main() {
    int N, M, start_x, start_y, finish_x, finish_y;
    cin >> N >> M >> start_x >> start_y >> finish_x >> finish_y;
    start_x--;
    start_y--;
    finish_x--;
    finish_y--;

    vector<vector<pair<int, int> > > map(N, vector<pair<int, int> >(M));

    string tmp;
    for (int i = 0; i < N; i++) {
        cin >> tmp;
        for (int j = 0; j < M; j++) {
            if (tmp[j] == '.') {
                map[i][j].first = 1;
                map[i][j].second = 0;
            } else if (tmp[j] == 'W') {
                map[i][j].first = 2;
                map[i][j].second = 0;
            } else {
                map[i][j].first = 666;
                map[i][j].second = 0;
            }
        }
    }

    NodeQueue pq_sorted;
    vector<Node> visited(N * M);

    pq_sorted.push(Node(0, 1, start_x, start_y));
    visited[start_x * M + start_y] = Node(0, 1, start_x, start_y, -1);

    while (!pq_sorted.empty()) {
        Node curr = pq_sorted.top();
        pq_sorted.pop();

        int curr_index = curr.x * M + curr.y;
        if (curr_index == finish_x * M + finish_y) break; // Exit if reached the goal

        // Directions arrays for moving in the 4 cardinal directions
        int directions[4][2] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (auto& dir : directions) {
            int nx = curr.x + dir[0];
            int ny = curr.y + dir[1];
            int next_index = nx * M + ny;

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny].first != 666 && !map[nx][ny].second) {
                int new_cost = curr.cost + map[nx][ny].first;
                if (visited[next_index].cost == -1 || visited[next_index].cost > new_cost) {
                    pq_sorted.push(Node(new_cost, 1, nx, ny, curr_index));
                    visited[next_index] = Node(new_cost, 1, nx, ny, curr_index);
                    map[nx][ny].second = 1;
                }
            }
        }
    }

    int goal = finish_x * M + finish_y;
    cout << visited[goal].cost << endl;
    if (visited[goal].cost == -1) return 0;  // Check if the goal was unreachable

    list<char> way;
    int curr = goal;
    while (curr != -1 && visited[curr].predecessor != -1) {
        int pred = visited[curr].predecessor;
        int diff = curr - pred;
        if (diff == 1) way.push_front('E');      // Moved left
        else if (diff == -1) way.push_front('W'); // Moved right
        else if (diff == M) way.push_front('S');  // Moved up
        else if (diff == -M) way.push_front('N'); // Moved down
        curr = pred;
    }

    for (char x : way) cout << x;
    cout << endl;  // Print the path as a sequence
}