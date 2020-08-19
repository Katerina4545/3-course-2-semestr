#include <iostream>
#include <string>
using namespace std;
 
bool correct(int date_one, int mounth_one, int year_one, 
                int date_two, int mounth_two, int year_two)
{
    if (!(date_one >= 1 && date_one <= 31)) return false;
    if (!(date_two >= 1 && date_two <= 31)) return false;

    if(!(mounth_one >= 1 && mounth_one <=12)) return false;
    if(!(mounth_two >= 1 && mounth_two <=12)) return false;

    if(!(year_one >= 0 && year_one <= 50)) return false;
    if(!(year_two >= 0 && year_two <= 50)) return false;

    return true;
}

int main() {

    int date_one, mounth_one, year_one;
    int date_two, mounth_two, year_two;

    cout << "enter first date : ";
    scanf("%d.%d.%d", &date_one, &mounth_one, &year_one);
    cout << endl;

    cout << "enter second date : ";
    scanf("%d.%d.%d", &date_two, &mounth_two, &year_two);
    cout << endl;


    //проверка на правильность
    bool right = correct(date_one, mounth_one, year_one, date_two, mounth_two, year_two);
    if (!right)
    {
        cout << "dates entered not correct";
        return 0;
    }

    //сравнение
    bool first = 0, second = 0, equals = 0;
    if (year_one > year_two) 
    {
        cout << "first date is more early" << endl;
        return 0;
    } else if(year_one < year_two)
    {
        cout << "second date is more early" << endl;
        return 0;
    }

    if(mounth_one > mounth_two)
    {
        cout << "first date is more early" << endl;
        return 0;
    }else if(mounth_one < mounth_two)
    {
        cout << "second date is more early" << endl;
        return 0;
    }

    if (date_one > date_two)
    {
        cout << "first date is more early" << endl;
        return 0;
    } else if(date_one < date_two)
    {
        cout << "second date is more early" << endl;
        return 0;
    }

    cout << "dates is equals" << endl;
    return 0;
    }