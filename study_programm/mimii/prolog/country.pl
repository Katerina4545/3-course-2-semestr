country(������, ������).
country(������, �����).
country(��������, ������).
country(������, ����).
country(�������, ���������).
country(�������, ������).
country(����, ��������).
country(������, ���������).
country(�����, �����).
country(��������, ������).
country(_, _) :- writeln("����� ������/������ � ���� ���."), fail.

print_capital :- write("������� �������� ������:"),read(Country), country(Country, Capital), writeln(Capital), true.

print_country :- write("������� �������� ������:"),read(Capital), country(Country, Capital), writeln(Country), true.
