/*
PROG: runround
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <set>

bool isRunaround(unsigned long n) {
    std::vector<int> digits;
    std::set<int> seen_digits;
    unsigned long temp = n;

    while (temp > 0) {
        int d = temp % 10;
        if (d == 0 || seen_digits.count(d)) return false;
        digits.insert(digits.begin(), d);
        seen_digits.insert(d);
        temp /= 10;
    }

    int len = digits.size();
    std::vector<bool> visited(len, false);
    int pos = 0;

    for (int step = 0; step < len; ++step) {
        if (visited[pos]) return false;
        visited[pos] = true;
        pos = (pos + digits[pos]) % len;
    }

    return pos == 0;
}

int main() {
    std::ifstream in("runround.in");
    std::ofstream out("runround.out");

    unsigned long M;
    in >> M;

    unsigned long candidate = M + 1;
    while (true) {
        if (isRunaround(candidate)) {
            out << candidate << "\n";
            break;
        }
        ++candidate;
    }

    return 0;
}
