#include <iostream>
#include <cmath>

using namespace std;

long long queue[1000000];
int tail = 0;
int head = 0;
int tickets = 0;

void add(long long x){
    queue[head++] = x;
}

long long removeTail(){
    return queue[head--];
}
long long removeHead(){
    tickets++;
    return queue[tail++];
}
long long getNum(long long id){
    long long k = 0;
    for (int i = tail; i < head; i++){
        if (queue[i] == id){
            break;
        } else k++;
    }
    return k;
}




int main(){
    int n, p=0, tmp=0;
    cin>>n;
    for(int i=0; i<n; i++){
        cin>>p;
        if (p==1){
            cin>>tmp;
            add(tmp);
        }
        if (p==2){
            removeHead();
        }
        if (p==3){
            removeTail();
        }
        if (p==4){
            cin>>tmp;
            cout<<getNum(tmp)<<endl;
        }
        if (p==5){
            cout<<queue[tail]<<endl;
        }
    }
    return 0;
}