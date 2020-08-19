#include <iostream>
#include <string>


using namespace std; 

int main() {   
    ////ввод строки 
    cout << "enter the string: " << endl;
    string str;
    getline(cin, str);

    ////подсчет четных и нечетных позиций
    int evenLenght = str.length() / 2;
    int unevenLenght = str.length() - evenLenght;
    cout << "even: " << evenLenght << endl;
    cout << "uneven: " << unevenLenght << endl;

    ////дешифровка
    const char* charStr = str.c_str();
    char* newStr = new char[str.length()];
    for(int i=1, j=0; i<evenLenght*2, j<evenLenght; i+=2, j++){
        newStr[i] = charStr[j];
    }

    for(int i=0, j=evenLenght; i<str.length(), j<str.length(); i+=2, j++)
    {
        newStr[i] = charStr[j];
    }

    cout << "newStr: " << newStr << endl;
    return 0;
}