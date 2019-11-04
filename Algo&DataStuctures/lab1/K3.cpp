#include <iostream>
#include <algorithm>

using namespace std;

struct jewelry{
    int v;
    int w;
};

jewelry* jew;
double* num;

double calculate(int n, int k, double M){
    for (int i = 0; i<n; i++){
        num[i] = jew[i].v - jew[i].w * M;
    }
    nth_element(num, num + n - k, num + n);

    double sum = 0;

    for (int i = n-k; i<n; i++){
        sum += num[i];
    }
    return sum;
}

bool comp(pair<double, int> o1, pair<double, int> o2){
    if (o1.first == o2.first){
        return o1.second < o2.second;
    } else {
        return o1.first < o2.first;
    }
}

int main(){
    int n, k;
    cin>> n >> k;
    jew = new jewelry[n];
    num = new double[n];
    for (int i = 0 ; i < n; i++){
        cin >> jew[i].v >> jew[i].w;
    }

    double l = 0;
    double r = 1000000000000000;
    int inter = 100;
    double mid = (l+r)/2;
    for (int i = 0; i < inter; i++){
        mid = (l+r)/2;
        if (calculate(n,k,mid) < 0){
            r = mid;
        } else l = mid;
    }

    double M = l;

    pair<double, int>ans [n];
    for (int i = 0; i < n; i++){
        ans[i].first = jew[i].v - jew[i].w * M;
        ans[i].second = i+1;
    }

    nth_element(ans, ans + n - k, ans + n, comp);

    for (int i = n-k; i < n; i++){
        cout << ans[i].second << endl;
    }

    delete[](jew);
    delete[](num);
    return 0;
}