#include <iostream>
#include <cmath>
using namespace std;

struct stack{
    long data;
    long min;
    struct stack *next;
};

void push(stack* &next, const long long val){
    auto *newStack = new stack;
    newStack->data = val;
    newStack->next = next;
    if (next != nullptr){
        newStack->min = fmin(next->min, val);
    } else {
        newStack->min = val;
    }
    next = newStack;
}

long long pop(stack* &next){
    long long tmp = next->data;
    stack *newStack = next;
    next = next->next;
    delete[](newStack);
    return tmp;
}

long long getMin(stack* &next){
    return next->min;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, p;
    long long read;
    cin>>n;
    stack *stk{};
    for (int i = 0; i < n; i++){
        cin>>p;
        if (p == 1){
            cin>>read;
            push(stk, read);
        } else {
            if (p == 2) {
                pop(stk);
            } else {
                if (p == 3) {
                    cout<<getMin(stk)<<endl;
                }
            }
        }
    }
    delete[](stk);
    return 0;
}