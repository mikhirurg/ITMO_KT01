#include <iostream>
#include <cmath>

using namespace std;
struct stack {
    long data;
    struct stack *next;
};

void push(stack *&next, const long long val) {
    auto *newStack = new stack;
    newStack->data = val;
    newStack->next = next;
    next = newStack;
}

long long pop(stack *&next) {
    long long tmp = next->data;
    stack *newStack = next;
    next = next->next;
    delete[](newStack);
    return tmp;
}

bool hasElements(stack *&next) {
    return next != nullptr;
}

long long getTop(stack *&next) {
    return next->data;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, p = 0, ans = 0;
    long long read;
    cin >> n;
    stack *stk1{};
    stack *stk2{};
    for (int i = 0; i < n; i++) {
        cin >> read;
        push(stk1, read);
    }
    long long tmp = 0;
    bool hasSeq = true;
    while (hasSeq) {
        hasSeq = false;
        while (hasElements(stk1)) {
            if (!hasElements(stk2)) {
                tmp = pop(stk1);
                push(stk2, tmp);
            } else {
                if (getTop(stk1) == getTop(stk2)) {
                    p++;
                    tmp = pop(stk1);
                    push(stk2, tmp);
                } else {
                    if (p >= 2) {
                        hasSeq = true;
                        for (int j = 0; j <= p; j++) {
                            tmp = pop(stk2);
                        }
                        while (hasElements(stk2)) {
                            tmp = pop(stk2);
                            push(stk1, tmp);
                        }
                        p = 0;
                        break;
                    } else {
                        p = 0;
                        tmp = pop(stk1);
                        push(stk2, tmp);
                    }
                }
            }
        }
    }

    while (hasElements(stk1)) {
        tmp = pop(stk1);
        push(stk2, tmp);
    }
    p = 0;
    push(stk1, pop(stk2));
    while (hasElements(stk2)) {
        if (getTop(stk1) == getTop(stk2)) {
            p++;
        } else break;
        push(stk1, pop(stk2));
    }

    if (p >= 2) {
        cout << n;
        return 0;
    }
    while (hasElements(stk1)) {
        tmp = pop(stk1);
        push(stk2, tmp);
    }

    while (hasElements(stk2)) {
        pop(stk2);
        ans++;
    }
    cout << n - ans;
    return 0;
}