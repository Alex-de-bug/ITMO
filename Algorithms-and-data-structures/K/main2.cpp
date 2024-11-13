#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
#include <queue>
#include <stack>

using namespace std;

struct Segment {
    int start;
    int length;
    bool isFree;
    int requestID;

    Segment(int s, int l, bool free = true, int rid = -1) : start(s), length(l), isFree(free), requestID(rid) {}
    bool operator==(const Segment& rhs) const {
    return start == rhs.start && length == rhs.length && isFree == rhs.isFree && requestID == rhs.requestID;
}

};

struct CompareSegment {
    bool operator()(const Segment* lhs, const Segment* rhs) const {
        return lhs->length < rhs->length;
    }
};

list<Segment> freeSegments;
vector<Segment*> allocatedSegments;
priority_queue<Segment*, vector<Segment*>, CompareSegment> freeSegmentsHeap;
vector<int> ans;

bool allocateMemory(int requestID, int size) {
    if (freeSegmentsHeap.empty())
    {
        ans.push_back(-1);
        return 0;
    }
    Segment* maxSeg = freeSegmentsHeap.top();
    freeSegmentsHeap.pop();
    if (maxSeg->length < size) {
        freeSegmentsHeap.push(maxSeg);
        ans.push_back(-1);
        return 0;
    }

    stack<Segment*> tmp;

    while(!freeSegmentsHeap.empty() && freeSegmentsHeap.top()->length >= size ) {
        tmp.push(maxSeg);
        maxSeg = freeSegmentsHeap.top();
        freeSegmentsHeap.pop();
    }

    while (!tmp.empty())
    {
        freeSegmentsHeap.push(tmp.top());
        tmp.pop();
    }

    

    int start = maxSeg->start;
    int remainingLength = maxSeg->length - size;
    maxSeg->isFree = false;
    maxSeg->length = size;
    maxSeg->requestID = requestID;

    allocatedSegments.push_back(maxSeg);

    if (remainingLength > 0) {
        // Добавляем оставшийся свободный сегмент обратно в кучу
        Segment newFreeSegment(start + size, remainingLength);
        freeSegments.push_back(newFreeSegment);
        freeSegmentsHeap.push(&freeSegments.back());
    }
    // cout << "alloc:   seg id:"<< maxSeg->requestID <<"; start: "<< maxSeg->start<< endl;
    ans.push_back(start);
    return 1;
}

void freeMemory(int requestID) {

    for (auto it = allocatedSegments.begin(); it != allocatedSegments.end(); ++it) {
        if ((*it)->requestID == requestID) {
            Segment* segToFree = *it;
            auto nextIt = next(it);
            auto prevIt = it == allocatedSegments.begin() ? allocatedSegments.end() : std::prev(it);

            Segment *leftSegment = nullptr, *rightSegment = nullptr;
            if (prevIt != allocatedSegments.end() && (*prevIt)->isFree) {
                leftSegment = *prevIt;
                cout << "find left seq" <<leftSegment->requestID<<endl;

            }
            if (nextIt != allocatedSegments.end() && (*nextIt)->isFree) {
                cout << "find right seq" <<rightSegment->requestID<<endl;
                rightSegment = *nextIt;
            }

            if (!leftSegment && !rightSegment) {
                // Случай 1: Свободных сегментов нет ни слева, ни справа
                cout << "free:   seg id:"<< segToFree->requestID <<"; start: "<< segToFree->start<< endl;
                segToFree->isFree = true;
                segToFree->requestID = -1;
                freeSegmentsHeap.push(segToFree);
            } else if (!leftSegment && rightSegment) {
                // Случай 2: Свободный сегмент только справа
                rightSegment->start = segToFree->start;
                rightSegment->length += segToFree->length;
            } else if (leftSegment && !rightSegment) {
                // Случай 3: Свободный сегмент только слева
                leftSegment->length += segToFree->length;
            } else if (leftSegment && rightSegment) {
                // Случай 4: Свободные сегменты и слева, и справа
                leftSegment->length += segToFree->length + rightSegment->length;
                // Удаляем правый сегмент из списка
                freeSegments.erase(find(freeSegments.begin(), freeSegments.end(), *rightSegment));
            }
            allocatedSegments.erase(it);
            break;
        }
    }
}


int main() {
    int totalMemory, count;
    cin >> totalMemory >> count;

    freeSegments.push_back(Segment(1, totalMemory, true));
    freeSegmentsHeap.push(&freeSegments.back());

    int i = 1;
    int id = 1;
    while(i <= count){
        int query;
        cin >> query;
        if (query > 0) { // Запрос на выделение памяти
            allocateMemory(i, query);
            id++;
        } else if (query < 0) { // Запрос на освобождение памяти
            freeMemory(-query); 
        }
        i++;
    }

    for (int num : ans) {
        cout << num << endl;
    }


    return 0;
}
