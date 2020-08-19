#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;
 

int swap(int* a,  int* b);
int main() {

    int a, b;

    cout << "Enter number 1 : ";
    cin >> a;
    cout << endl << "Enter number 2 : ";
    cin >> b;
    
    swap(&a, &b);

    cout << endl << "a = " << a << " b = " << b << endl;

    return 0;
}

int swap(int* a,  int* b){
    int t = *a;
    *a = *b;
    *b = t;
    return 0;
}