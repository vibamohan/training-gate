/*
PROG: preface
LANG: C++
*/

#include <bits/stdc++.h>
using namespace std;

int main() {
    freopen("preface.in", "r", stdin);
    freopen("preface.out", "w", stdout);

    int N;
    cin >> N;

    std::vector<int> values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    std::vector<string> symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    std::map<char,int> counts;

    for(int i=1;i<=N;i++){
        int num = i;
        for(int j=0;j<values.size();j++){
            while(num >= values[j]){
                num -= values[j];
                for(char c : symbols[j]) counts[c]++;
            }
        }
    }

    std::string order = "IVXLCDM";
    for(char c : order){
        if(counts[c] > 0)
            cout << c << " " << counts[c] << "\n";
    }

    return 0;
}
