/*
PROG: lamps
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <algorithm>

int main() {
    std::ifstream in("lamps.in");
    std::ofstream out("lamps.out");

    int n, C;
    in >> n >> C;

    std::vector<int> on_lamps;
    std::vector<int> off_lamps;

    int x;
    while (in >> x && x != -1) on_lamps.push_back(x-1);
    while (in >> x && x != -1) off_lamps.push_back(x-1);

    std::set<std::string> results;

    for (int mask = 0; mask < 16; mask++) {
        int presses = __builtin_popcount(mask);
        if (presses > C || (C - presses) % 2 != 0) continue;

        std::vector<int> lamps(n, 1);

        if (mask & 1) for (int i = 0; i < n; i++) lamps[i] ^= 1;
        if (mask & 2) for (int i = 0; i < n; i += 2) lamps[i] ^= 1;
        if (mask & 4) for (int i = 1; i < n; i += 2) lamps[i] ^= 1;
        if (mask & 8) for (int i = 0; i < n; i += 3) lamps[i] ^= 1;

        bool valid = true;
        for (int i : on_lamps) if (lamps[i] != 1) valid = false;
        for (int i : off_lamps) if (lamps[i] != 0) valid = false;

        if (valid) {
            std::string s;
            for (int v : lamps) s += (v + '0');
            results.insert(s);
        }
    }

    if (results.empty()) {
        out << "IMPOSSIBLE\n";
    } else {
        for (const auto &s : results) out << s << "\n";
    }

    return 0;
}
